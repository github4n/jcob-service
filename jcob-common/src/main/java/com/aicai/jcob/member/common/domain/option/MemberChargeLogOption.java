package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;

public class MemberChargeLogOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3728089146687165330L;

	private Long memberId;
	
	private BigDecimal amount;
	
    private String notifyUrl;

    private String returnUrl;
    
    /**  */
    private Integer clientType;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
    
    
}
