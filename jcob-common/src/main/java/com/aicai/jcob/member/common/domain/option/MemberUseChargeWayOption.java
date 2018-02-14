package com.aicai.jcob.member.common.domain.option;

import java.io.Serializable;

public class MemberUseChargeWayOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2065318284876225550L;

	private Long memberId;
	
	private Integer chargeIndex;
	
	private Integer clientType;

	private String bankCode;
	
	private String bankCard;
	
	private String payType;
	
	public MemberUseChargeWayOption(){}
	
	public MemberUseChargeWayOption(Long memberId,Integer chargeIndex){
		this.setMemberId(memberId);
		this.setChargeIndex(chargeIndex);
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	

	public Integer getChargeIndex() {
		return chargeIndex;
	}

	public void setChargeIndex(Integer chargeIndex) {
		this.chargeIndex = chargeIndex;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer client_type) {
		this.clientType = client_type;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
}
