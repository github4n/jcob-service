package com.aicai.jcob.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicai.jcob.game.common.domain.CheckPlanEffectContext;
import com.aicai.jcob.game.common.service.CheckPlanEffectAdminService;
import com.aicai.jcob.game.manager.impl.CheckPlanEffectManagerImpl;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;
@Component
public class CheckPlanEffectAdminServiceImpl implements CheckPlanEffectAdminService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CheckPlanEffectManagerImpl checkPlanEffectManagerImpl ;
	@Autowired
	private TjPlanManager tjPlanManager ;
	@Override
	public void checkPlanEffect(List<Long> planIdList, List<RaceScoreResult> raceScoreList,CheckPlanEffectContext checkPlanEffectContext) {
		if(planIdList==null||planIdList.size()==0){
		   logger.info("方案开奖checkPlanEffect,方案planIdList不能为空");
		   return ;
		}
		List<TjPlan> tjPlanList  = new ArrayList<TjPlan>() ;
		for(Long planId:planIdList){
			TjPlan tjPlan= tjPlanManager.queryTjPlanById(planId);
			tjPlanList.add(tjPlan) ;
		}
		checkPlanEffectManagerImpl.checkPlanEffect(tjPlanList, raceScoreList,checkPlanEffectContext) ;
		
	}

}
