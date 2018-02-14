package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberDrawMoneyLogQueryOption implements Serializable {

	private static final long serialVersionUID = -5394877375209124537L;

	/** 用户名 */
	private String account;

	/** 用户ID */
	private Long memberId;

	/** 提款开始时间 */
	private Date startDate;

	/** 提款结束时间 */
	private Date endDate;
	
	/** 响应开始时间 */
	private Date responseStartDate;

	/** 响应结束时间 */
	private Date responseEndDate;

	/** 提款状态 */
	private Integer status;

	/** 审核状态 */
	private Integer auditStatus;

	/** 提款金额 */
	private BigDecimal minAmount;

	/** 提款金额 */
	private BigDecimal maxAmount;

	/** 手机号*/
	private String phone;
	
	/** 真实姓名 */
	private String realName;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getResponseStartDate() {
		return responseStartDate;
	}

	public void setResponseStartDate(Date responseStartDate) {
		this.responseStartDate = responseStartDate;
	}

	public Date getResponseEndDate() {
		return responseEndDate;
	}

	public void setResponseEndDate(Date responseEndDate) {
		this.responseEndDate = responseEndDate;
	}

}
