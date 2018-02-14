package com.aicai.jcob.tjexpert.common.domain.result;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.tjexpert.common.domain.constant.ExpertApplyProgress;

public class ApplyProgressResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long memberId;
	private Integer applyProgress = ExpertApplyProgress.NOT_APPLY.getIndex();
	
	private Calendar applyTime ;
	private Calendar checkTime;//第一次审核时间
	private Calendar firstPublishPlanTime;
	private Calendar formalExpertChekcTime;//第二次审核时间
	private String cancelReason;
	
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getApplyProgress() {
		return applyProgress;
	}
	public void setApplyProgress(Integer applyProgress) {
		this.applyProgress = applyProgress;
	}
	public Calendar getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Calendar applyTime) {
		this.applyTime = applyTime;
	}
	public Calendar getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Calendar checkTime) {
		this.checkTime = checkTime;
	}
	public Calendar getFirstPublishPlanTime() {
		return firstPublishPlanTime;
	}
	public void setFirstPublishPlanTime(Calendar firstPublishPlanTime) {
		this.firstPublishPlanTime = firstPublishPlanTime;
	}
	public Calendar getFormalExpertChekcTime() {
		return formalExpertChekcTime;
	}
	public void setFormalExpertChekcTime(Calendar formalExpertChekcTime) {
		this.formalExpertChekcTime = formalExpertChekcTime;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	
}
