package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;
import java.util.Date;

public class FeedbackSearchOption implements Serializable {
	private static final long serialVersionUID = 4277585423820604780L;

	private Long memberId;

	/** 昵称 */
	private String nickName;

	/** 身份 */
	private Integer level;

	/** 电话 */
	private String phone;

	/** 客户端类型 */
	private Integer clientType;

	private Date startDate;

	private Date endDate;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
