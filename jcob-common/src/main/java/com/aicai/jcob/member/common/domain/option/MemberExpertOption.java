package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

public class MemberExpertOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3449533934470704139L;
	
	private String phone;
	
	private String nickName;
	
	private Long memberId;
	
	private Integer level;
	
	private Integer status;
	
	private Calendar startTime;
	
	private Calendar endTime;
	
	private Integer clientType;
	
	private Integer registerType;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getRegisterType() {
		return registerType;
	}

	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
	
	
}
