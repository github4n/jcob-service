package com.aicai.jcob.membercharge.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;

public interface MemberChargeWayWriteService {

	/**
	 * 根据chargeWayIndex与clientType查询chargechannel
	 * @param chargeWayIndex
	 * @param clientType 客户端类别
	 * @return
	 */
	public ModelResult<MemberChargeChannel> queryMemberChargeChannel(int chargeWayIndex,int clientType);
	
	public ModelResult<MemberChargeWay> queryMemberChargeWay(int chargeWayIndex,int clientType);
}
