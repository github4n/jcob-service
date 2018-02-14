package com.aicai.jcob.tjplan.common.domain.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.tjplan.common.domain.constant.TjPlanOpenStatus;

/**
 * 后台查看推荐记录
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月3日
 */
public class PageAdminLookLogVo implements Serializable{
	private static final long serialVersionUID = -5769318567185937238L;
	private Long looklogId;
	private Calendar lookedTime;
	private Long lookerMemberId;
	private String lookerNickName;
	private String planNo;
	private Integer raceType;
	private Integer gameType;
	private BigDecimal amount;
	private Integer raceStatus;
	private Integer clientType;
	private Long expertMemberId;
	private String expertNickName;
	private Integer expertLevel;
	private Calendar planCreateTime;
	private Long planId;
	private Integer openStatus;
	private BigDecimal winRaceCount;
	public Calendar getLookedTime() {
		return lookedTime;
	}
	public void setLookedTime(Calendar lookedTime) {
		this.lookedTime = lookedTime;
	}
	public Long getLookerMemberId() {
		return lookerMemberId;
	}
	public void setLookerMemberId(Long lookerMemberId) {
		this.lookerMemberId = lookerMemberId;
	}
	public String getLookerNickName() {
		return lookerNickName;
	}
	public void setLookerNickName(String lookerNickName) {
		this.lookerNickName = lookerNickName;
	}
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public Integer getRaceType() {
		return raceType;
	}
	public void setRaceType(Integer raceType) {
		this.raceType = raceType;
	}
	public Integer getGameType() {
		return gameType;
	}
	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getRaceStatus() {
		return raceStatus;
	}
	public void setRaceStatus(Integer raceStatus) {
		this.raceStatus = raceStatus;
	}

	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public Long getExpertMemberId() {
		return expertMemberId;
	}
	public void setExpertMemberId(Long expertMemberId) {
		this.expertMemberId = expertMemberId;
	}
	public String getExpertNickName() {
		return expertNickName;
	}
	public void setExpertNickName(String expertNickName) {
		this.expertNickName = expertNickName;
	}
	public Integer getExpertLevel() {
		return expertLevel;
	}
	public void setExpertLevel(Integer expertLevel) {
		this.expertLevel = expertLevel;
	}
	public Calendar getPlanCreateTime() {
		return planCreateTime;
	}
	public void setPlanCreateTime(Calendar planCreateTime) {
		this.planCreateTime = planCreateTime;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Integer getOpenStatus() {
		return openStatus;
	}
	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}
	public BigDecimal getWinRaceCount() {
		return winRaceCount;
	}
	public void setWinRaceCount(BigDecimal winRaceCount) {
		this.winRaceCount = winRaceCount;
	}
	public Long getLooklogId() {
		return looklogId;
	}
	public void setLooklogId(Long looklogId) {
		this.looklogId = looklogId;
	}
	public Integer getWinStatus() {
		if (openStatus == TjPlanOpenStatus.not_open) {
			return 0;
		}else if (openStatus == TjPlanOpenStatus.opened && winRaceCount.compareTo(BigDecimal.valueOf(0)) > 0) {
			return 2;
		}else if (openStatus == TjPlanOpenStatus.opened && winRaceCount.compareTo(BigDecimal.valueOf(0)) <= 0) {
			return 1;
		}
		return 0;
	}
}
