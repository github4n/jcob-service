package com.aicai.jcob.tjplan.manager;

import java.util.List;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;

/**
 * 推荐方案内容列表详情管理
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月29日
 */
public interface TjPlanItemManager {

	/**
	 * 保存方案详情信息
	 * @param tjplanItem
	 */
	public void saveTjplanItem(TjPlanItem tjplanItem);
	
	public List<TjPlanItem> queryTjPlanItemByPlanId(Long planId);
	
	public int updateTjPlanItemWinStatus(Long planItemId,Integer newWinStatus);
	
	public int updateTjPlanItemWinStatus(Long planItemId,Integer newWinStatus,String newfeatures);
	
	public DataPage<Long> queryPlanIdsByRaceId(Long raceId, DataPage<Long> page);
	
	public TjPlanItem queryPlanItemByMemberIdAndRaceId(Long memberId,Long raceId,Integer gameId);

}
