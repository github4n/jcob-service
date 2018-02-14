package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

public class MemberDrawMoneyInfoUpdateOption implements Serializable {

	private static final long serialVersionUID = -9051069064183429428L;
	/**
	 * 提款流水
	 */
	private Long drawMoneyLogId;

	/**
	 * 更新状态
	 */
	private Integer newStatus;
	/**
	 * 原始状态
	 */
	private Integer oldStatus;

	/**
	 * 提款请求第三方时间
	 */
	private Calendar requestTime;

	/**
	 * 提款 第三方异步响应时间
	 */
	private Calendar responseTime;

	private String responseMsg;
	
	private String responseCode;
	
	private String thirdDrawNo;

	public Long getDrawMoneyLogId() {
		return drawMoneyLogId;
	}

	public void setDrawMoneyLogId(Long drawMoneyLogId) {
		this.drawMoneyLogId = drawMoneyLogId;
	}


	public Integer getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	public Integer getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}

	public Calendar getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Calendar requestTime) {
		this.requestTime = requestTime;
	}

	public Calendar getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Calendar responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getThirdDrawNo() {
		return thirdDrawNo;
	}

	public void setThirdDrawNo(String thirdDrawNo) {
		this.thirdDrawNo = thirdDrawNo;
	}
	
}
