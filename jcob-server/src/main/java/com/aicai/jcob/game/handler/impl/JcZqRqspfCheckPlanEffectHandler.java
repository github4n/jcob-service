package com.aicai.jcob.game.handler.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.aicai.jcob.game.common.domain.constant.GameOptionConstant;
import com.aicai.jcob.game.common.util.ItemContentUtil;
import com.aicai.jcob.game.handler.JjGameCheckPlanEffectHandler;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.constant.FeaturesKeyConstant;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanItemWinStatus;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanOpenStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;

/**
 * 竞彩足球让球胜平负检查是否命中，也称为开奖
 * 
 * @author fx
 *
 */
public class JcZqRqspfCheckPlanEffectHandler implements JjGameCheckPlanEffectHandler {

	@Override
	public TjPlan checkPlanEffect(TjPlan plan, List<TjPlanItem> itemList, Map<String,RaceScoreResult> matchNo2RaceScoreMap) {
		float winCount = 0f;
		for(TjPlanItem item:itemList){
			winCount += checkPlanItemEffect(item,matchNo2RaceScoreMap);
		}
		plan.setRaceStatus(TjRaceStatus.draw.getIndex());
		plan.setWinRaceCount(winCount);
		plan.setOpenStatus(TjPlanOpenStatus.opened);
		return plan;
	}
	
	/**
	 * 
	 * @param item
	 * @param matchNo2RaceScoreMap
	 * @return 中1，未中0,赛事取消race_cancel_result
	 */
	private	float checkPlanItemEffect(TjPlanItem item,Map<String,RaceScoreResult> matchNo2RaceScoreMap){
		String itemContent = item.getContent();// 2016001[+1](1-1.3)
		String matchNo = ItemContentUtil.getMatchNo(itemContent);
		String[] optionArray = ItemContentUtil.getTouZuOptions(itemContent);
		String handicap = ItemContentUtil.getHandicap(itemContent);
		String wholeScore = matchNo2RaceScoreMap.get(matchNo).getWholeScore();
		String handicapResult = this.getHandicapResult(handicap, wholeScore);
		item.setupFeature(FeaturesKeyConstant.handicapResult, handicapResult);
		if(handicapResult.equals(GameOptionConstant.race_cancel_result )){
			item.setWinStatus(TjPlanItemWinStatus.cancel.getIndex());
			return 0f;
		}
		if(ArrayUtils.contains(optionArray, handicapResult)){
			item.setWinStatus(TjPlanItemWinStatus.award.getIndex());
			//双选命中按1场计算
			/*if(optionArray.length == 2){
				return 0.5f;
			}*/
			return 1f;
		}
		item.setWinStatus(TjPlanItemWinStatus.not_award.getIndex());
		return 0f;
	}
	
	/**
	 * 根据比分与盘口获得盘口结果
	 * @return
	 */
	private String getHandicapResult(String handicap,String wholeScore){
		if(StringUtils.contains(wholeScore, "*")){
			return GameOptionConstant.race_cancel_result;
		}
		String[] scoreTowPart = wholeScore.split(":");
		int hostTeamScore = Integer.parseInt(scoreTowPart[0]);
		int guestTeamScore = Integer.parseInt(scoreTowPart[1]);
		int rq = Integer.parseInt(handicap);
		if(hostTeamScore+rq > guestTeamScore){
			return GameOptionConstant.jzrqspf_zs;
		}else if(hostTeamScore+rq == guestTeamScore){
			return GameOptionConstant.jzrqspf_zp;
		}
		return GameOptionConstant.jzrqspf_zf;
	}
	
	
	
	public static void main(String[] args){
		
	}
	
	
}
