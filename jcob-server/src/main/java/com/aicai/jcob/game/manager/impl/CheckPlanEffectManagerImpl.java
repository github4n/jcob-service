package com.aicai.jcob.game.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.datachange.common.constant.jcob.JcobDataType;
import com.aicai.jcob.common.utils.GroupingUtil;
import com.aicai.jcob.datachange.DataChangeFactory;
import com.aicai.jcob.game.common.domain.CheckPlanEffectContext;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.game.handler.JjGameCheckPlanEffectHandler;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanOpenStatus;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.domain.constant.RaceTaskFeatureKeyConstant;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;
import com.aicai.jcob.tjrace.manager.TjRaceTaskManager;

@Component
public class CheckPlanEffectManagerImpl {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "gameCheckPlanEffectHandlerMap")
	protected Map<GameType, JjGameCheckPlanEffectHandler> gameCheckPlanHandleMap;
	@Autowired
	private TjPlanItemManager tjPlanItemManager ;
	@Autowired
	private TjPlanManager tjPlanManager ;
	@Autowired
	private TjRaceTaskManager tjRaceTaskManager ;
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	private TransactionTemplate transactionTemplateTjplan;
	@Autowired
	private DataChangeFactory dataChangeFactory;
	
	public List<TjPlan> checkPlanEffect(List<TjPlan> planList,List<RaceScoreResult> raceScoreList,CheckPlanEffectContext checkPlanEffectContext){
		Map<String,RaceScoreResult> matchNo2RaceScoreMap = this.toMatchNo2RaceScoreMap(raceScoreList);
		List<TjPlan> openedPlanList = new ArrayList<>();
		
		for(TjPlan tjPlan:planList){
		
			if(tjPlan.getTjPlanItemList() == null){
				List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
				tjPlan.setTjPlanItemList(tjPlanItemList);
			}
			GameType game = GameType.valueOf(tjPlan.getGameId());
			JjGameCheckPlanEffectHandler checkPlanEffectHandler = gameCheckPlanHandleMap.get(game);
			try{
				checkPlanEffectHandler.checkPlanEffect(tjPlan,tjPlan.getTjPlanItemList(), matchNo2RaceScoreMap);
				openedPlanList.add(tjPlan);
			}catch(Exception e){
				this.updateRaceTask(checkPlanEffectContext, 0, 1);
				String str = String.format("{'planId':'%s';'msg':'%s'}", new Object[]{tjPlan.getId(),e.getMessage()});
				logger.info(str,e);
			}
		}
		 
		Map<Integer,List<TjPlan>> index2PlanListMap = GroupingUtil.toGroupsByIndexOrder(openedPlanList, 900000, 10000);
		for(Integer index: index2PlanListMap.keySet()){
			List<TjPlan> subPlanList = index2PlanListMap.get(index);
			//批量修改
			transactionTemplateTjplan.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus arg0) {
						for(TjPlan tjPlan:subPlanList){
							tjPlanManager.updateTjPlanForCheckEfect(tjPlan);
							for(TjPlanItem item:tjPlan.getTjPlanItemList()){
								tjPlanItemManager.updateTjPlanItemWinStatus(item.getId(),item.getWinStatus(),item.getFeatures());
							}
							
						}
					}
						
				}
			);
			//保存结果到RaceTask
			this.updateRaceTask(checkPlanEffectContext, subPlanList.size(), 0);
			//事务提交后发DataChange消息
			for(TjPlan tjPlan:subPlanList){
				if(tjPlan.getOpenStatus().intValue() == TjPlanOpenStatus.opened.intValue()){
					sendPlanOpenStatusDataChange(tjPlan);
				}
			}
			
		}
		
		return planList;
	}
	
	private void updateRaceTask(CheckPlanEffectContext context, int addSuccNum,int addFailedNum) {
		Long taskId = context.getTaskId();
		int totalNum = context.getTotalNum();
		TjRaceTask raceTask = tjRaceTaskManager.queryRaceTaskById(taskId);
		
		String succStr = raceTask.getFeature(RaceTaskFeatureKeyConstant.DRAW_SUCCESS);
		String failedStr =  raceTask.getFeature(RaceTaskFeatureKeyConstant.DRAW_FAILED);
		if (StringUtils.isBlank(succStr)) {
			succStr= "0";
		}
		if(StringUtils.isBlank(failedStr)){
			failedStr="0";
		}
		int succNum = Integer.parseInt(succStr) + addSuccNum;
		int failedNum =  Integer.parseInt(failedStr) + addFailedNum;
		raceTask.setupFeature(RaceTaskFeatureKeyConstant.DRAW_SUCCESS,String.valueOf(succNum));
		raceTask.setupFeature(RaceTaskFeatureKeyConstant.DRAW_FAILED, String.valueOf(failedNum));// 未处理失败方案
		raceTask.setupFeature(RaceTaskFeatureKeyConstant.DRAW_TOTAL, String.valueOf(totalNum));
		
		tjRaceTaskManager.updateFeatures(taskId, raceTask.getFeatures(),raceTask.getFeaturesVersion());
	}
	
	private Map<String,RaceScoreResult> toMatchNo2RaceScoreMap(List<RaceScoreResult> raceScoreList){
		Map<String,RaceScoreResult> matchNo2RaceScoreMap = new HashMap<>();
		for(RaceScoreResult raceScore:raceScoreList){
			matchNo2RaceScoreMap.put(raceScore.getUniqueMatchNo(), raceScore);
		}
		return matchNo2RaceScoreMap;
	}
	
	// 发送消息
	private void sendPlanOpenStatusDataChange(TjPlan plan){
		dataChangeFactory.sendDataChangeForModify(plan.getId(), JcobDataType.jcob_tjplan, TjRaceStatus.draw.getIndex(), null, null, null);
	}
	
}
