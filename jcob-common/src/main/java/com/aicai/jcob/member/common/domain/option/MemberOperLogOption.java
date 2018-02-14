package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

public class MemberOperLogOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 982171612686757385L;

	private Long memberId;
	
	private Integer operType;
	
	private Calendar beginTime;
	
	private Calendar endTime;

	public MemberOperLogOption(){}
	
	public MemberOperLogOption(Long memberId,Integer operType,Calendar beginTime,Calendar endTime) {
		this.setMemberId(memberId);
		this.setOperType(operType);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getOperType() {
		return operType;
	}

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public Calendar getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	
}
