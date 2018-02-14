package com.aicai.jcob.common;

import java.math.BigDecimal;


public class GlobalParam {

	/**
	 * 系统用户id
	 */
	private Long sysMemberId;
	
	/**
	 * 现金兑换火眼的比例
	 */
	private BigDecimal cashTOHuoyanRatio = BigDecimal.valueOf(1) ;

	public Long getSysMemberId() {
		return sysMemberId;
	}

	public void setSysMemberId(Long sysMemberId) {
		this.sysMemberId = sysMemberId;
	}

	public BigDecimal getCashTOHuoyanRatio() {
		return cashTOHuoyanRatio;
	}

	public void setCashTOHuoyanRatio(BigDecimal cashTOHuoyanRatio) {
		this.cashTOHuoyanRatio = cashTOHuoyanRatio;
	}
	
	
}
