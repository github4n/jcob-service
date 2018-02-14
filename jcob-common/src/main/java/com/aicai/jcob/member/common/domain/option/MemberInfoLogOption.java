package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author hailong.zhang
 * MemberInfoLog表的查询选项
 */
public class MemberInfoLogOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1461830682472773426L;

	private Long memberId;
	
	private Calendar beginTime;

	private Calendar endTime;
	
	private String newInfo;
	
	public MemberInfoLogOption(){}
	
	public MemberInfoLogOption(Long memberId,Calendar beginTime,Calendar endTime){
		this.setMemberId(memberId);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Calendar getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}

	public String getNewInfo() {
		return newInfo;
	}

	public void setNewInfo(String newInfo) {
		this.newInfo = newInfo;
	}
	
}
