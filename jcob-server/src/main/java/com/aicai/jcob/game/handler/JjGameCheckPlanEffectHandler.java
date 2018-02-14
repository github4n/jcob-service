package com.aicai.jcob.game.handler;

import java.util.List;
import java.util.Map;

import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;

public interface JjGameCheckPlanEffectHandler {
	
	
	public TjPlan checkPlanEffect(TjPlan plan,List<TjPlanItem>itemList,Map<String,RaceScoreResult> matchNo2RaceScoreMap);
}