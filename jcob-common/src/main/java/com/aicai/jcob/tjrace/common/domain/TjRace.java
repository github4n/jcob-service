package com.aicai.jcob.tjrace.common.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceWeek;

public class TjRace implements Serializable {

	private static final long serialVersionUID = -5915174616863309044L;

	/** 主键 */
	private Long id;

	/** 赛事类别：1竞足赛事，2竞篮赛事 */
	private Integer raceType;

	/** 联赛类型 */
	private Integer leagueType;

	/**
	 * 比赛场次 北单是场次号 x/xxx 如 1/2/3/...99/101，竞彩足球XXX 如1/2.../46 ,竞彩篮球是 XXX
	 * 如301/302
	 */
	private Integer matchNo;

	/** 比赛ID，和竞彩网的比赛ID对应，用于判断同一场比赛。在投注中没作用 */
	private String matchId;

	/** 赛事 */
	private String matchName;

	/** 开赛状态 */
	private Integer status = TjRaceStatus.not_match.getIndex();

	/** 比赛时间 */
	private Date matchTime;

	/** 赛事发布时间 */
	private Date publishTime;

	/** 主队名称 */
	private String homeTeam;

	/**
	 * 盘口标记:单场胜平负；单场让球胜平负；亚盘让球盘口;大小球盘口;串关让球数
	 * 字符串格式：0;让球数;亚盘盘口;大小球盘口;
	 * 举例说明：_;-1;1/1.5;2/2.5;-1(单场胜平负没有开用"_"表示)
	 * 举例说明：0;_;1/1.5;2/2.5;-1(单场让球胜平负没有开用"_"表示，开了用"0"表示)
	 * 举例说明：0;_;1/1.5;2/2.5;0(串关让球数没有用"0"或者"_"表示)
	 * */
	private String handicap;

	/** 客队名称 */
	private String guestTeam;

	/** 全场比分 */
	private String wholeScore;

	/**
	 * 字符串格式：竞彩主胜SP,竞彩平SP,竞彩主负SP;亚盘主胜SP,亚盘主负SP;大小球主胜SP,大小球主负SP
	 * 举例说明：1.25,0.9,1.26;_;1.25,0.9;1.25,0.9，如果让球胜平负没有开用"_"表示
	 * */
	private String sp;

	/** 数据抓取ID */
	private String fxId;

	/** 关联bet007即时比分ID */
	private String bfId;

	/** 赛事名称简写 */
	private String matchShortName;

	/** 主队对阵名称简写 */
	private String homeTeamShortName;

	/** 客队对阵名称简写 */
	private String guestTeamShortName;

	/** 主队图片地址 */
	private String homeImgPath;

	/** 客队图片地址 */
	private String guestImgPath;

	/** 哪一年的第几周,e.g. 1032表示2010年的第32周 **/
	private String weekOfYear;

	/** 篮球每节比分,例如26:20;18:25;26:29;26:23,足球上下场比分,比如0：0;1：0 */
	private String sectionScore;

	/***
	 * 唯一对阵编号 北单 彩期+match_no 如2015001029 竞足 yymmdd(publish_time)+match_no 如
	 * 150801001 竞篮 yymmdd(publish_time)+match_no 如 150801301
	 */
	private Long uniqueMatchNo;

	/** 是否中立场 1中立0不是 */
	private Integer isMiddle;

	/** 主客反了 1主0否 */
	private Integer isOwner;

	private Integer isDel = 0;// 是否删除 值为 1（删除）或0（正常）

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRaceType() {
		return raceType;
	}

	public void setRaceType(Integer raceType) {
		this.raceType = raceType;
	}

	public Integer getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(Integer matchNo) {
		this.matchNo = matchNo;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getLeagueType() {
		return leagueType;
	}

	public void setLeagueType(Integer leagueType) {
		this.leagueType = leagueType;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public String getWholeScore() {
		return wholeScore;
	}

	public void setWholeScore(String wholeScore) {
		this.wholeScore = wholeScore;
	}

	public String getFxId() {
		return fxId;
	}

	public void setFxId(String fxId) {
		this.fxId = fxId;
	}

	public String getBfId() {
		return bfId;
	}

	public void setBfId(String bfId) {
		this.bfId = bfId;
	}

	public String getMatchShortName() {
		return matchShortName;
	}

	public void setMatchShortName(String matchShortName) {
		this.matchShortName = matchShortName;
	}

	public String getHomeTeamShortName() {
		return homeTeamShortName;
	}

	public void setHomeTeamShortName(String homeTeamShortName) {
		this.homeTeamShortName = homeTeamShortName;
	}

	public String getGuestTeamShortName() {
		return guestTeamShortName;
	}

	public void setGuestTeamShortName(String guestTeamShortName) {
		this.guestTeamShortName = guestTeamShortName;
	}

	public String getHomeImgPath() {
		return homeImgPath;
	}

	public void setHomeImgPath(String homeImgPath) {
		this.homeImgPath = homeImgPath;
	}

	public String getGuestImgPath() {
		return guestImgPath;
	}

	public void setGuestImgPath(String guestImgPath) {
		this.guestImgPath = guestImgPath;
	}

	public String getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(String weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public String getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(String sectionScore) {
		this.sectionScore = sectionScore;
	}

	public Long getUniqueMatchNo() {
		return uniqueMatchNo;
	}

	public void setUniqueMatchNo(Long uniqueMatchNo) {
		this.uniqueMatchNo = uniqueMatchNo;
	}

	public Integer getIsMiddle() {
		return isMiddle;
	}

	public void setIsMiddle(Integer isMiddle) {
		this.isMiddle = isMiddle;
	}

	public Integer getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getHandicap() {
		return handicap;
	}

	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString() {
		return "TjRace [id=" + id + ", raceType=" + raceType + ", matchNo=" + matchNo + ", matchName=" + matchName + ", status="
				+ status + ", matchTime=" + matchTime + ", publishTime=" + publishTime + ", homeTeam=" + homeTeam + ", handicap="
				+ handicap + ", guestTeam=" + guestTeam + ", wholeScore=" + wholeScore + ", sp=" + sp + ", fxId=" + fxId
				+ ", bfId=" + bfId + ", matchShortName=" + matchShortName + ", homeTeamShortName=" + homeTeamShortName
				+ ", guestTeamShortName=" + guestTeamShortName + ", homeImgPath=" + homeImgPath + ", guestImgPath="
				+ guestImgPath + ", weekOfYear=" + weekOfYear + ", sectionScore=" + sectionScore + ", uniqueMatchNo="
				+ uniqueMatchNo + ", isMiddle=" + isMiddle + ", isOwner=" + isOwner + ", isDel=" + isDel + "]";
	}

	// -----------------------辅助方法-----------------------------
	public String getWeekName() {
		if (publishTime == null)
			return "";
		// 星期日返回1，星期一返回2，依次类推
		int week = DateUtil.getCalenderByDate(this.publishTime).get(Calendar.DAY_OF_WEEK);
		if (week == 1) {
			week = 6;
		} else {
			week = week - 2;
		}
		return TjRaceWeek.valueOf(week).getDescription();
	}

	public String getStatusStr() {
		return TjRaceStatus.valueOf(status).getDescription();
	}
}
