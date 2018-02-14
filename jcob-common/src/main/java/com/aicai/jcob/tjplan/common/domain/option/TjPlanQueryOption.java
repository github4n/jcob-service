package com.aicai.jcob.tjplan.common.domain.option;  

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月30日 下午4:03:03 
 * 推荐单查询option
 */
public class TjPlanQueryOption implements Serializable{

	private static final long serialVersionUID = -5008222839902197013L;
	private Long memberId ; 
	private String nickName ; 
	private String phone ; //手机号
	private Integer level ; //身份
	private Calendar startTime ; //按时间查询发布开始时间
	private Calendar endTime ;//按时间查询发布结束时间
	
	private Integer raceType ; //推荐父类
	private Integer GameType ; //推荐子类
	private Integer openStatus ; //比赛状态
	private Integer winStatus ; //中奖状态
	
	private BigDecimal amountStart ; //按方案价格查询起始
	private BigDecimal amountEnd ; //按方案价格查询结束
	
	private Integer clientType ;  //方案发布终端:0 android,1 ios ,2 h5,3web

	private String tjPlanNo ; //推荐编号

	public Long getMemberId() {
		return memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhone() {
		return phone;
	}

	public Integer getLevel() {
		return level;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public Integer getRaceType() {
		return raceType;
	}

	public Integer getGameType() {
		return GameType;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public Integer getWinStatus() {
		return winStatus;
	}

	public BigDecimal getAmountStart() {
		return amountStart;
	}

	public BigDecimal getAmountEnd() {
		return amountEnd;
	}

	public Integer getClientType() {
		return clientType;
	}

	public String getTjPlanNo() {
		return tjPlanNo;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public void setRaceType(Integer raceType) {
		this.raceType = raceType;
	}

	public void setGameType(Integer gameType) {
		GameType = gameType;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public void setWinStatus(Integer winStatus) {
		this.winStatus = winStatus;
	}

	public void setAmountStart(BigDecimal amountStart) {
		this.amountStart = amountStart;
	}

	public void setAmountEnd(BigDecimal amountEnd) {
		this.amountEnd = amountEnd;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public void setTjPlanNo(String tjPlanNo) {
		this.tjPlanNo = tjPlanNo;
	}
	

}
 