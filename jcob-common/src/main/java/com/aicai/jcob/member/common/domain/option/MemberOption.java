package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;

public class MemberOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8740554521151092332L;

	private Long memberId;
	
	private String mobile;
	
	private String certNo;
	
	private String nickName;
	
	private String realName;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
}
