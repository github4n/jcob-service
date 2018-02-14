package com.aicai.jcob.tjplan.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceType;

public class PageAdminLookLogOption implements Serializable{
	private static final long serialVersionUID = -3793027941878839274L;

	private Calendar startDate = DateUtil.getCurrentDayFirstTime();
	
	private Calendar endDate = Calendar.getInstance();

	private List<Long> memberIdList;
	
	private List<TjExpertLevel> expertLevelList;
	
	private List<TjRaceType> raceTypeList;
	
	private List<GameType> gameTypeList;
	
	private List<ClientType> clientTypeList;
	
	private BigDecimal leftAndEqualAmount;
	
	private BigDecimal rightAndEqualAmount;
	
	private String tjPlanNo;
	
	private List<TjRaceStatus> raceStatuList;
	/**
	 * 0:待开奖
	 * 1：已命中
	 * 2：未命中
	 */
	private Integer winstatus;
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public List<Long> getMemberIdList() {
		return memberIdList;
	}
	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
	}
	public List<TjExpertLevel> getExpertLevelList() {
		return expertLevelList;
	}
	public void setExpertLevelList(List<TjExpertLevel> expertLevelList) {
		this.expertLevelList = expertLevelList;
	}
	public List<TjRaceType> getRaceTypeList() {
		return raceTypeList;
	}
	public void setRaceTypeList(List<TjRaceType> raceTypeList) {
		this.raceTypeList = raceTypeList;
	}
	public List<GameType> getGameTypeList() {
		return gameTypeList;
	}
	public void setGameTypeList(List<GameType> gameTypeList) {
		this.gameTypeList = gameTypeList;
	}
	public List<ClientType> getClientTypeList() {
		return clientTypeList;
	}
	public void setClientTypeList(List<ClientType> clientTypeList) {
		this.clientTypeList = clientTypeList;
	}
	public BigDecimal getLeftAndEqualAmount() {
		return leftAndEqualAmount;
	}
	public void setLeftAndEqualAmount(BigDecimal leftAndEqualAmount) {
		this.leftAndEqualAmount = leftAndEqualAmount;
	}
	public BigDecimal getRightAndEqualAmount() {
		return rightAndEqualAmount;
	}
	public void setRightAndEqualAmount(BigDecimal rightAndEqualAmount) {
		this.rightAndEqualAmount = rightAndEqualAmount;
	}
	public String getTjPlanNo() {
		return tjPlanNo;
	}
	public void setTjPlanNo(String tjPlanNo) {
		this.tjPlanNo = tjPlanNo;
	}
	
	public List<TjRaceStatus> getRaceStatuList() {
		return raceStatuList;
	}
	public void setRaceStatuList(List<TjRaceStatus> raceStatuList) {
		this.raceStatuList = raceStatuList;
	}
	public Integer getWinstatus() {
		return winstatus;
	}
	public void setWinstatus(Integer winstatus) {
		this.winstatus = winstatus;
	}
}
