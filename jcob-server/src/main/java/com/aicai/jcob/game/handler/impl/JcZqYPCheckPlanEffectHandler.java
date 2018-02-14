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
 * @author jing.ming
 * @version 创建时间：2016年2月20日 上午10:09:24 
 * 足球外围亚盘玩法,检查是否命中
 */
public class JcZqYPCheckPlanEffectHandler implements
		JjGameCheckPlanEffectHandler {

	@Override
	public TjPlan checkPlanEffect(TjPlan plan, List<TjPlanItem> itemList,
			Map<String, RaceScoreResult> matchNo2RaceScoreMap) {
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
		String itemContent = item.getContent();// 110212302[+1/+1.5](0-1.3)
		String matchNo = ItemContentUtil.getMatchNo(itemContent);
		String[] optionArray = ItemContentUtil.getTouZuOptions(itemContent);
		String handicap = ItemContentUtil.getHandicap(itemContent); //   +1/+1.5 ,+1
		String wholeScore = matchNo2RaceScoreMap.get(matchNo).getWholeScore();
		String handicapResult = this.getHandicapResult(handicap, wholeScore);
		item.setupFeature(FeaturesKeyConstant.handicapResult, handicapResult); 
		if(handicapResult.equals(GameOptionConstant.race_cancel_result )){
			item.setWinStatus(TjPlanItemWinStatus.cancel.getIndex());
			return 0f;
		}
		if(ArrayUtils.contains(optionArray, handicapResult)){
			item.setWinStatus(TjPlanItemWinStatus.award.getIndex());
			return 1f;
		}else if((handicapResult.equals(GameOptionConstant.zszp_result)|| handicapResult.equals(GameOptionConstant.zpzs_result))
				&& ArrayUtils.contains(optionArray, GameOptionConstant.yapan_zs)){
			item.setWinStatus(TjPlanItemWinStatus.award.getIndex());
			return 1f;
		}else if((handicapResult.equals(GameOptionConstant.zfzp_result)|| handicapResult.equals(GameOptionConstant.zpzf_result))
				&& ArrayUtils.contains(optionArray, GameOptionConstant.yapan_zf)){
			item.setWinStatus(TjPlanItemWinStatus.award.getIndex());
			return 1f;
		}else if(handicapResult.equals(GameOptionConstant.zp_result)){ //走盘也算命中
			item.setWinStatus(TjPlanItemWinStatus.award.getIndex());
			return 1f;
		}
		item.setWinStatus(TjPlanItemWinStatus.not_award.getIndex());
		return 0f;
	}
	
	/**
	 * 根据比分与盘口获得盘口结果
	 * 
	 * @return
	 */
	private String getHandicapResult(String handicap, String wholeScore) {// handicap:
																			// +1/+1.5 ,+1
		if (StringUtils.contains(wholeScore, "*")) {
			return GameOptionConstant.race_cancel_result;
		}
		String[] scoreTowPart = wholeScore.split(":");
		int hostTeamScore = Integer.parseInt(scoreTowPart[0]);
		int guestTeamScore = Integer.parseInt(scoreTowPart[1]);
		
		if (handicap.indexOf("/") == -1) {
			double panKou = Double.valueOf(handicap);
			
			double rqhost = hostTeamScore+panKou ;
			if(rqhost>guestTeamScore){
				return GameOptionConstant.yapan_zs ;
			}else if(rqhost<guestTeamScore){
				return GameOptionConstant.yapan_zf ;
			}
			return GameOptionConstant.zp_result ;
		} else {
			double panFirst = Double.valueOf(handicap.split("/")[0]);// 2
			double panSec = Double.valueOf(handicap.split("/")[1]);// 2.5
			double rqhostFir = hostTeamScore+panFirst ;
			double rqhostSec = hostTeamScore+panSec ;
            if(rqhostFir>guestTeamScore&&rqhostSec>guestTeamScore){
            	return GameOptionConstant.yapan_zs ;
            }else if(rqhostFir<guestTeamScore&&rqhostSec<guestTeamScore){
            	return GameOptionConstant.yapan_zf ;
            }else if(rqhostFir>guestTeamScore||rqhostSec>guestTeamScore){//rqhostFir,rqhostSec有一个必会和guestTeamScore相等 ,
            	if(rqhostFir>guestTeamScore){
            		return GameOptionConstant.zszp_result ;
            	}else{
            		return GameOptionConstant.zpzs_result;
            	}
            }else if(rqhostFir<guestTeamScore||rqhostSec<guestTeamScore){//rqhostFir,rqhostSec有一个必会和guestTeamScore相等 ,
            	if(rqhostFir<guestTeamScore){
            		return GameOptionConstant.zfzp_result ;
            	}else{
            		return GameOptionConstant.zpzf_result;
            	}
            }
			return "";

		}

	}
	
}
 