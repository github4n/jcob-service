package com.aicai.jcob.memberwallet.common.domain.result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class PageWalletLogVo implements Serializable {

	private static final long serialVersionUID = -618755541239852214L;

	private Long memberId;
	
	private String nickName;
	
	private Integer expertLevel;
	
	private BigDecimal endAbleBalance;
	
	private BigDecimal happenAmount;
	
	private Integer walletOpType;
	
	private Integer bizType;
	
	private String bizNo;
	
	private Long bizId;
	
	private Calendar createTime;
	
	private Integer clientType;
	
	/**
	 * 总收入
	 */
	private BigDecimal totalIncome = new BigDecimal(0);
	
	/**
	 * 总支出
	 */
	private BigDecimal totalSpending = new BigDecimal(0);

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

	public Integer getExpertLevel() {
		return expertLevel;
	}

	public void setExpertLevel(Integer expertLevel) {
		this.expertLevel = expertLevel;
	}

	public BigDecimal getEndAbleBalance() {
		return endAbleBalance;
	}

	public void setEndAbleBalance(BigDecimal endAbleBalance) {
		this.endAbleBalance = endAbleBalance;
	}

	public BigDecimal getHappenAmount() {
		return happenAmount;
	}

	public void setHappenAmount(BigDecimal happenAmount) {
		this.happenAmount = happenAmount;
	}

	public Integer getWalletOpType() {
		return walletOpType;
	}

	public void setWalletOpType(Integer walletOpType) {
		this.walletOpType = walletOpType;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(BigDecimal totalSpending) {
		this.totalSpending = totalSpending;
	}
	
}
