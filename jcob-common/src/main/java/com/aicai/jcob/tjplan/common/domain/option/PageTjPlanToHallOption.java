package com.aicai.jcob.tjplan.common.domain.option;  

import java.io.Serializable;
import java.util.List;

import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
/**
 * 推荐大厅分页查询参数
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月4日
 */
public class PageTjPlanToHallOption implements Serializable{
	private static final long serialVersionUID = -172063994612155974L;
	/**
	 * 赛事状态
	 * 比赛前：按付费-免费,查看方案人数降序,专家命中率降序，创建时间降序
	 * 比赛中：
	 *  1、按已查看——未查看排序
	 *  2、已查看和未查看按比赛时间顺序排列
	 * 比赛完:
	 * 1、按命中——未命中——待开奖排序
	 * 2、命中、未命中和待开奖按比赛时间倒序排列
	 */
	private TjRaceStatus tjRaceStatus;
	/**
	 * 用户id
	 */
	private Long memberId;
	
	/**
	 * 联赛类型
	 */
	private List<TjRaceLeagueType> leagueTypeList ;
	
	/**
	 * 玩法类型
	 */
	private List<GameType> gameTypeList;
	
	/**
	 * 用户已查看过的方案id列表 （内部会通过memberid去查询）
	 */
	private List<Long> planIdList;
	
	/**
	 * 专家类别
	 * 
	 */
	private List<TjExpertLevel> tjExpertLevelList;

	public TjRaceStatus getTjRaceStatus() {
		return tjRaceStatus;
	}

	public void setTjRaceStatus(TjRaceStatus tjRaceStatus) {
		this.tjRaceStatus = tjRaceStatus;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<TjRaceLeagueType> getLeagueTypeList() {
		return leagueTypeList;
	}

	public void setLeagueTypeList(List<TjRaceLeagueType> leagueTypeList) {
		this.leagueTypeList = leagueTypeList;
	}

	public List<GameType> getGameTypeList() {
		return gameTypeList;
	}

	public void setGameTypeList(List<GameType> gameTypeList) {
		this.gameTypeList = gameTypeList;
	}

	public List<Long> getPlanIdList() {
		return planIdList;
	}

	public void setPlanIdList(List<Long> planIdList) {
		this.planIdList = planIdList;
	}

	public List<TjExpertLevel> getTjExpertLevelList() {
		return tjExpertLevelList;
	}

	public void setTjExpertLevelList(List<TjExpertLevel> tjExpertLevelList) {
		this.tjExpertLevelList = tjExpertLevelList;
	}

}
 