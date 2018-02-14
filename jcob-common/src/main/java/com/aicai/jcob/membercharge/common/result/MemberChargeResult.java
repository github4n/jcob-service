package com.aicai.jcob.membercharge.common.result;

import java.io.Serializable;
import java.math.BigDecimal;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeWay;

public class MemberChargeResult extends MemberChargeLog implements Serializable {
	private static final long serialVersionUID = 1900523931670060133L;

	/** 昵称 */
	private String nickName;

	/**
	 * 成功
	 */
	private BigDecimal totalSuccess = new BigDecimal(0);

	/**
	 * 失败
	 */
	private BigDecimal totalFailed = new BigDecimal(0);

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRequestTimeStr() {
		if (this.getRequestTime() == null) {
			return "";
		}
		return DateUtil.getDateString2(this.getRequestTime().getTime());
	}

	public String getResponseTimeStr() {
		if (this.getResponseTime() == null) {
			return "";
		}
		return DateUtil.getDateString2(this.getResponseTime().getTime());
	}

	public String getStatusStr() {
		if (this.getStatus() == 1) {
			return "成功";
		} else {
			return "失败";
		}
	}

	public String getChargeChannelStr() {
		if (this.getChargeChannelIndex() == null) {
			return "";
		}
		return ChargeChannel.valueOf(getChargeChannelIndex()).getDescription();
	}

	public String getChargeWayStr() {
		if (this.getChargeWayIndex() == null) {
			return "";
		}
		return ChargeWay.valueOf(getChargeWayIndex()).getDescription();
	}

	public String getClientTypeStr() {
		return ClientType.valueOf(getClientType()).getDescription();
	}

	public BigDecimal getTotalSuccess() {
		return totalSuccess;
	}

	public void setTotalSuccess(BigDecimal totalSuccess) {
		this.totalSuccess = totalSuccess;
	}

	public BigDecimal getTotalFailed() {
		return totalFailed;
	}

	public void setTotalFailed(BigDecimal totalFailed) {
		this.totalFailed = totalFailed;
	}

}
