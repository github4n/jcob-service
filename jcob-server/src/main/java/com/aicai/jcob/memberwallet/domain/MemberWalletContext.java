package com.aicai.jcob.memberwallet.domain;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;

/**
 * 钱包业务处理，上下文参数传递
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月26日
 */
public class MemberWalletContext {

	private MemberChargeLog memberChargeLog;
	
	private TjPlan tjPlan;
	
	private TjOrder tjOrder;
	
	private MemberDrawMoneyLog memberDrawMoneyLog;
	
	private Integer clientType = ClientType.android.getIndex();
	
	private Integer channel = 1;

	public MemberChargeLog getMemberChargeLog() {
		return memberChargeLog;
	}

	public void setMemberChargeLog(MemberChargeLog memberChargeLog) {
		this.memberChargeLog = memberChargeLog;
	}

	public TjPlan getTjPlan() {
		return tjPlan;
	}

	public void setTjPlan(TjPlan tjPlan) {
		this.tjPlan = tjPlan;
	}

	public TjOrder getTjOrder() {
		return tjOrder;
	}

	public void setTjOrder(TjOrder tjOrder) {
		this.tjOrder = tjOrder;
	}

	public MemberDrawMoneyLog getMemberDrawMoneyLog() {
		return memberDrawMoneyLog;
	}

	public void setMemberDrawMoneyLog(MemberDrawMoneyLog memberDrawMoneyLog) {
		this.memberDrawMoneyLog = memberDrawMoneyLog;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
}
