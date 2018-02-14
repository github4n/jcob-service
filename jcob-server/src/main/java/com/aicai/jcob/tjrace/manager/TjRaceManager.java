package com.aicai.jcob.tjrace.manager;

import java.util.Date;
import java.util.List;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.option.TjRaceSearchOption;

public interface TjRaceManager {

	public TjRace createRace(TjRace tjRace);

	public TjRace queryRaceById(Long id);

	public TjRace queryRaceByMatchId(String matchId);

	public boolean isRaceExist(String matchId);

	public boolean deleteRaceById(Long id);

	public boolean updateRace(TjRace tjRace);

	public boolean updateScoreByMatchId(String matchId, String score);

	public boolean updateScoreById(Long id, String score);

	public boolean updateMatchTimeByMatchId(String matchId, Date matchTime);

	public boolean updateFxIdByMatchId(String matchId, String fxId, String guestShortName, String hostShortName,
			String guestImgPath, String homeImgPath);

	public boolean updateHandicapByMatchId(String matchId, String handicap, String sp);

	public boolean updateRaceStatus(Long id, Integer oldStatus, Integer newStatus);

	public DataPage<TjRace> queryRaceByPage(TjRaceSearchOption option, DataPage<TjRace> page);

	public TjRace queryRaceByUniqueMatchNo(Long uniqueMatchNo);

	public List<TjRace> queryRaceByIdList(List<Long> raceIdList);

	public List<Integer> queryStatusByIdList(List<Long> raceIdList);

	public boolean stopRaceByMatchId(String matchId);
}
