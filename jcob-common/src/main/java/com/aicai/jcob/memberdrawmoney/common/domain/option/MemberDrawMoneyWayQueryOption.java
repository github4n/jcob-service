package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;
import java.util.Date;

public class MemberDrawMoneyWayQueryOption implements Serializable {

	private static final long serialVersionUID = -5394877375209124537L;
	/** 昵称 */
	private String nickName;

	/** 身份 */
	private Integer level;

	/** 用户ID */
	private Long memberId;

	/** 电话 */
	private String phone;

	private Date startDate;

	private Date endDate;

	private String realName;

	private Integer bankCode;

	/** 银行 */
	private String bankName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "MemberDrawMoneyWayQueryOption [nickName=" + nickName + ", level=" + level + ", memberId=" + memberId + ", phone="
				+ phone + ", startDate=" + startDate + ", endDate=" + endDate + ", realName=" + realName + ", bankCode="
				+ bankCode + "]";
	}

}
