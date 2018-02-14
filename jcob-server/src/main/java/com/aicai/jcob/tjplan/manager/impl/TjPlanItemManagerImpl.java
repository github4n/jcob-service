package com.aicai.jcob.tjplan.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;

public class TjPlanItemManagerImpl implements TjPlanItemManager {
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao tjplanDbDao;
	@Override
	public void saveTjplanItem(TjPlanItem tjplanItem) {
		tjplanDbDao.insertAndSetupId("TjPlanItemMapper.saveTjPlanItem", tjplanItem);

	}
	@Override
	public List<TjPlanItem> queryTjPlanItemByPlanId(Long planId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("planId", planId);
		return tjplanDbDao.queryList("TjPlanItemMapper.queryTjPlanItemByPlanId", paramMap);
	}
	
	@Override
	public int updateTjPlanItemWinStatus(Long planItemId,Integer newWinStatus){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planItemId);
		paramMap.put("newWinStatus", newWinStatus);
		return tjplanDbDao.update("TjPlanItemMapper.updateTjPlanItemWinStatus", paramMap);
	}
	
	@Override
	public int updateTjPlanItemWinStatus(Long planItemId,Integer newWinStatus,String newfeatures){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planItemId);
		paramMap.put("newWinStatus", newWinStatus);
		paramMap.put("features", newfeatures);
		return tjplanDbDao.update("TjPlanItemMapper.updateTjPlanItemWinStatus", paramMap);
	}
	@Override
	public DataPage<Long> queryPlanIdsByRaceId(Long raceId, DataPage<Long> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("raceId", raceId);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return tjplanDbDao.queryPage("TjPlanItemMapper.queryPlanIdsCount", "TjPlanItemMapper.queryPlanIdsByRaceId", map, page);
	}
	
	public TjPlanItem queryPlanItemByMemberIdAndRaceId(Long memberId,Long raceId,Integer gameId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("raceId", raceId);
		paramMap.put("gameId",gameId);
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		paramMap.put("startDate", startDate);
		return tjplanDbDao.queryUnique("TjPlanItemMapper.queryPlanItemByMemberIdAndRaceId", paramMap);
	}

}
