package com.aicai.jcob.tjrace.common.service;

import java.util.Date;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.domain.option.TjRaceSearchOption;

public interface TjRaceWriteService {

	/**
	 * 保存比赛,如果状态为未开赛则创建开赛任务
	 * 
	 * @param tjRace
	 * @return
	 */
	public ModelResult<TjRace> createRace(TjRace tjRace);

	/**
	 * 根据matchId判断赛事是否入库
	 * 
	 * @param matchId
	 * @return
	 */
	public ModelResult<Boolean> isRaceExist(String matchId);

	/**
	 * 根据ID查询比赛
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<TjRace> queryRaceById(Long id);

	/**
	 * 根据matchId查询比赛
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<TjRace> queryRaceByMatchId(String matchId);

	/**
	 * 根据matchId更新竞足比分
	 * 
	 * @param matchId
	 * @return
	 */
	public ModelResult<Boolean> updateScoreByMatchId(String matchId, String score);

	/**
	 * 根据Id更新比分
	 * 
	 * @param raceId
	 * @return
	 */
	public ModelResult<Boolean> updateScoreById(Long raceId, String score);

	/**
	 * 根据matchId更新fxId和球队简称
	 * 
	 * @param matchId
	 * @param fxId
	 * @param guestShortName
	 * @param hostShortName
	 * @return
	 */
	public ModelResult<Boolean> updateFxIdByMatchId(String matchId, String fxId, String guestShortName, String homeShortName,
			String guestImgPath, String homeImgPath);

	/**
	 * 根据matchId更新比赛时间,取消旧的开赛任务，创建新的开赛任务
	 * 
	 * @param matchId
	 * @return
	 */
	public ModelResult<Boolean> updateMatchTimeByMatchId(String matchId, Date matchTime);

	/**
	 * 根据id更新盘口和赔率
	 * 
	 * @param matchId
	 * @return
	 */
	public ModelResult<Boolean> updateHandicapByMatchId(String matchId, String handicap, String sp);

	/**
	 * 删除比赛对阵
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<Boolean> deleteRaceById(Long id);

	/**
	 * 修改赛事
	 * 
	 * @param tjRace
	 * @return
	 */
	public ModelResult<Boolean> updateRace(TjRace tjRace);

	/**
	 * 更新赛事状态
	 * 
	 * @param raceId
	 *            赛事ID
	 * @param oldStatus
	 *            旧状态
	 * @param newStatus
	 *            新状态
	 * @return
	 */
	public ModelResult<Boolean> updateRaceStatus(Long raceId, Integer oldStatus, Integer newStatus);

	/**
	 * 设置比赛完场
	 * 
	 * @param matchId
	 * @return
	 */
	public ModelResult<Boolean> stopRaceByMatchId(String matchId);

	/**
	 * 分页查询赛事
	 * 
	 * @param option
	 * @return
	 */
	public PageResult<TjRace> queryRaceByPage(TjRaceSearchOption option, DataPage<TjRace> page);

	/**
	 * 赛事开奖：将赛事改为开奖中,创建开奖任务,发送消息给jcob-task执行，开奖后才将赛事改为已开奖
	 * 
	 * @param option
	 * @return
	 */
	public ModelResult<TjRaceTask> raceDrawById(Long raceId);

	/**
	 * 取消赛事：将赛事改为已取消，创建取消赛事任务，发送消息给jcob-task执行方案退款
	 * 
	 * @param option
	 * @return
	 */
	public ModelResult<Boolean> raceCancelById(Long raceId, Integer oldStatus);

}
