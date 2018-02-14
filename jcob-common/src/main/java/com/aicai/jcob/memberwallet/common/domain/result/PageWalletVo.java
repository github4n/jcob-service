package com.aicai.jcob.memberwallet.common.domain.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class PageWalletVo implements Serializable {

	private static final long serialVersionUID = 8191533453386298947L;

	private Long memberId;
	
	private String nickName;
	
	private Integer expertLevel;
	
	private BigDecimal ableBalance;

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

	public BigDecimal getAbleBalance() {
		return ableBalance;
	}

	public void setAbleBalance(BigDecimal ableBalance) {
		this.ableBalance = ableBalance;
	}
	
	
}
