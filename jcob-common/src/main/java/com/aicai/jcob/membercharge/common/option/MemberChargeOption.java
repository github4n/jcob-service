package com.aicai.jcob.membercharge.common.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberChargeOption implements Serializable {
	private static final long serialVersionUID = 6777180070591944295L;

	/** 电话 */
	private String phone;

	/** 昵称 */
	private String nickName;

	/** 身份 */
	private Integer level;

	private Long memberId;

	/** 客户端类型 */
	private Integer clientType;

	private Date requestStartDate;

	private Date requestEndDate;

	private Date responseStartDate;

	private Date responseEndDate;

	private BigDecimal minAmount;

	private BigDecimal maxAmount;

	private Integer chargeWay;

	private Integer chargeChannel;

	private Integer status;

	private String thirdChargeNo;

	private Long chargeId;

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

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Date getRequestStartDate() {
		return requestStartDate;
	}

	public void setRequestStartDate(Date requestStartDate) {
		this.requestStartDate = requestStartDate;
	}

	public Date getRequestEndDate() {
		return requestEndDate;
	}

	public void setRequestEndDate(Date requestEndDate) {
		this.requestEndDate = requestEndDate;
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

	public Integer getChargeWay() {
		return chargeWay;
	}

	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	public Integer getChargeChannel() {
		return chargeChannel;
	}

	public void setChargeChannel(Integer chargeChannel) {
		this.chargeChannel = chargeChannel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getThirdChargeNo() {
		return thirdChargeNo;
	}

	public void setThirdChargeNo(String thirdChargeNo) {
		this.thirdChargeNo = thirdChargeNo;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

}
