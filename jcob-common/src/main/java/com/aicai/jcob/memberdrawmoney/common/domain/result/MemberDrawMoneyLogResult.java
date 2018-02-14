package com.aicai.jcob.memberdrawmoney.common.domain.result;

import java.io.Serializable;
import java.math.BigDecimal;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;

public class MemberDrawMoneyLogResult extends MemberDrawMoneyLog implements Serializable {

	private static final long serialVersionUID = 4539638359429142249L;

	private String bankName;

	private String realName;

	private String drawBankInfo;

	/**
	 * 提款总数
	 */
	private Integer withdrawCount;

	/**
	 * 提款金额总计
	 */
	private BigDecimal amountSum;

	/**
	 * 提款手续费总计
	 */
	private BigDecimal handingCostSum;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDrawBankInfo() {
		return drawBankInfo;
	}

	public String[] getDrawBankInfoArray() {
		return drawBankInfo.split("\\|");
	}

	public void setDrawBankInfo(String drawBankInfo) {
		this.drawBankInfo = drawBankInfo;
	}

	public String getStatusStr() {
		return DrawMoneyStatus.valueOf(getStatus()).getDescription();
	}

	public String getAuditStatusStr() {
		return AuditStatus.valueOf(getAuditStatus()).getDescription();
	}

	public String getClientTypeStr() {
		if (getClientType() == null) {
			return "";
		}
		return ClientType.valueOf(getClientType()).getDescription();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(Integer withdrawCount) {
		this.withdrawCount = withdrawCount;
	}

	public BigDecimal getAmountSum() {
		return amountSum;
	}

	public void setAmountSum(BigDecimal amountSum) {
		this.amountSum = amountSum;
	}

	public BigDecimal getHandingCostSum() {
		return handingCostSum;
	}

	public void setHandingCostSum(BigDecimal handingCostSum) {
		this.handingCostSum = handingCostSum;
	}

}
