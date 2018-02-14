package com.aicai.jcob.tjrace.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.option.TjRaceSearchOption;
import com.aicai.jcob.tjrace.manager.TjRaceManager;

public class TjRaceManagerImpl implements TjRaceManager {

	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericDao dao;

	@Override
	public TjRace createRace(TjRace tjRace) {
		dao.insertAndSetupId("TjRaceMapper.insert", tjRace);
		return tjRace;
	}

	@Override
	public boolean isRaceExist(String matchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchId", matchId);
		return dao.queryCount("TjRaceMapper.countRaceByMatchId", map) > 0;
	}

	@Override
	public TjRace queryRaceById(Long id) {
		return dao.queryUnique("TjRaceMapper.queryRaceById", id);
	}

	@Override
	public TjRace queryRaceByMatchId(String matchId) {
		return dao.queryUnique("TjRaceMapper.queryRaceByMatchId", matchId);
	}

	@Override
	public boolean deleteRaceById(Long id) {
		return dao.updateByObj("TjRaceMapper.deleteById", id) > 0;
	}

	@Override
	public boolean updateRace(TjRace tjRace) {
		return dao.updateByObj("TjRaceMapper.updateRace", tjRace) > 0;
	}

	@Override
	public boolean updateRaceStatus(Long id, Integer oldStatus, Integer newStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("oldStatus", oldStatus);
		map.put("newStatus", newStatus);
		return dao.update("TjRaceMapper.updateStatus", map) > 0;
	}

	@Override
	public TjRace queryRaceByUniqueMatchNo(Long uniqueMatchNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uniqueMatchNo", uniqueMatchNo);
		return (TjRace) dao.queryObject("TjRaceMapper.queryRaceByUniqueMatchNo", paramMap);
	}

	@Override
	public DataPage<TjRace> queryRaceByPage(TjRaceSearchOption option, DataPage<TjRace> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return dao.queryPage("TjRaceMapper.queryByPageCount", "TjRaceMapper.queryByPage", map, page);
	}

	@Override
	public List<TjRace> queryRaceByIdList(List<Long> raceIdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("raceIdList", raceIdList);
		return dao.queryList("TjRaceMapper.queryRaceByIdList", map);
	}

	@Override
	public boolean updateScoreByMatchId(String matchId, String wholeScore) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchId", matchId);
		map.put("wholeScore", wholeScore);
		return dao.update("TjRaceMapper.updateScoreByMatchId", map) > 0;
	}

	@Override
	public boolean updateScoreById(Long id, String wholeScore) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("wholeScore", wholeScore);
		return dao.update("TjRaceMapper.updateScoreById", map) > 0;
	}

	public boolean updateMatchTimeByMatchId(String matchId, Date matchTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchId", matchId);
		map.put("matchTime", matchTime);
		return dao.update("TjRaceMapper.updateMatchTimeByMatchId", map) > 0;
	}

	@Override
	public boolean updateFxIdByMatchId(String matchId, String fxId, String guestShortName, String homeShortName,
			String guestImgPath, String homeImgPath) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(fxId)) {
			map.put("fxId", fxId);
		}
		if (StringUtils.isNotBlank(guestShortName)) {
			map.put("guestShortName", guestShortName);
		}
		if (StringUtils.isNotBlank(homeShortName)) {
			map.put("homeShortName", homeShortName);
		}
		if (StringUtils.isNotBlank(guestImgPath)) {
			map.put("guestImgPath", guestImgPath);
		}
		if (StringUtils.isNotBlank(homeImgPath)) {
			map.put("homeImgPath", homeImgPath);
		}
		map.put("matchId", matchId);
		return dao.update("TjRaceMapper.updateFxIdByMatchId", map) > 0;
	}

	@Override
	public boolean updateHandicapByMatchId(String matchId, String handicap, String sp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchId", matchId);
		map.put("handicap", handicap);
		map.put("sp", sp);
		return dao.update("TjRaceMapper.updateHandicapByMatchId", map) > 0;
	}

	@Override
	public List<Integer> queryStatusByIdList(List<Long> raceIdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("raceIdList", raceIdList);
		return dao.queryList("TjRaceMapper.queryStatusByIdList", map);
	}

	@Override
	public boolean stopRaceByMatchId(String matchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("matchId", matchId);
		map.put("status", TjRaceStatus.matched.getIndex());
		return dao.update("TjRaceMapper.stopRaceByMatchId", map) > 0;
	}

}
