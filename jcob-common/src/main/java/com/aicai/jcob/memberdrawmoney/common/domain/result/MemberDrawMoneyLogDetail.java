package com.aicai.jcob.memberdrawmoney.common.domain.result;

import java.io.Serializable;

import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;

/**
 * 
 * 提款详情
 * @project jcob-common
 * @author duannp
 * @date 2016年2月23日
 */
public class MemberDrawMoneyLogDetail implements Serializable {
	private static final long serialVersionUID = -236694512473422974L;

	/**
	 * 提款记录
	 */
	private MemberDrawMoneyLog memberDrawMoneyLog;
	
	/**
	 * 提款方式
	 */
	private MemberDrawMoneyWay memberDrawMoneyWay;

	public MemberDrawMoneyLog getMemberDrawMoneyLog() {
		return memberDrawMoneyLog;
	}

	public void setMemberDrawMoneyLog(MemberDrawMoneyLog memberDrawMoneyLog) {
		this.memberDrawMoneyLog = memberDrawMoneyLog;
	}

	public MemberDrawMoneyWay getMemberDrawMoneyWay() {
		return memberDrawMoneyWay;
	}

	public void setMemberDrawMoneyWay(MemberDrawMoneyWay memberDrawMoneyWay) {
		this.memberDrawMoneyWay = memberDrawMoneyWay;
	}
	
	
}
