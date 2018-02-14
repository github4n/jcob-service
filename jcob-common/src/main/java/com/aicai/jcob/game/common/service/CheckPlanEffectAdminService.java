package com.aicai.jcob.game.common.service;

import java.util.List;

import com.aicai.jcob.game.common.domain.CheckPlanEffectContext;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;

/**
 * 核对推荐方案的推荐效果
 * 也叫开奖
 * @author fx
 *
 */
public interface CheckPlanEffectAdminService {
	
	
	public void checkPlanEffect(List<Long> planIdList,List<RaceScoreResult> raceScoreList,CheckPlanEffectContext checkPlanEffectContext );
	
	
	
}
