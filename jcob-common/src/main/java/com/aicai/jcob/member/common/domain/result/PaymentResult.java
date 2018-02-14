package com.aicai.jcob.member.common.domain.result;

import java.math.BigDecimal;

/**
 * 充值平台返回json的处理结果
 * 
 * @author zhanghl
 */
public class PaymentResult {

	private String errMsg;
	private boolean success;
	private String chargeNo;
	private BigDecimal chargeAmount;
	private Integer chargeWay;
	private Integer clientType;
	/**同步返回一个地址时使用*/
	private String redirectUrl;
	
	private String reqData;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getChargeNo() {
		return chargeNo;
	}

	public void setChargeNo(String chargeNo) {
		this.chargeNo = chargeNo;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	/*public ChargeWay getChargeWay() {
		return chargeWay;
	}

	public void setChargeWay(ChargeWay chargeWay) {
		this.chargeWay = chargeWay;
	}*/

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Integer getChargeWay() {
		return chargeWay;
	}

	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getReqData() {
		return reqData;
	}

	public void setReqData(String reqData) {
		this.reqData = reqData;
	}
	
}
