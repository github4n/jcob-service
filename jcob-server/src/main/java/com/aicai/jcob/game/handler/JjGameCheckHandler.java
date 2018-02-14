package com.aicai.jcob.game.handler;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.domain.TjplanCheckResult;

/**
 * 推荐验证相关操作
 * 
 * @project jcob-server
 */
public interface JjGameCheckHandler {
	
	public Integer getGameId();
	/**
	 * 检查发布推荐的方案内容
	 * @param tjPlanItem
	 */
	public ModelResult<TjplanCheckResult> checkPlanItem(List<TjPlanItem> tjPlanItem);
	
}