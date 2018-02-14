package com.aicai.jcob.membercharge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.service.MemberChargeWayWriteService;
import com.aicai.jcob.membercharge.manager.MemberChargeWayManager;

public class MemberChargeWayWriteServiceImpl implements MemberChargeWayWriteService {

	@Autowired
	@Qualifier("memberChargeWayManagerImpl")
	private MemberChargeWayManager memberChargeWayManager;
	
	@Override
	public ModelResult<MemberChargeChannel> queryMemberChargeChannel(
			int chargeWayIndex, int clientType) {
		return new ModelResult<MemberChargeChannel>(memberChargeWayManager.queryMemberChargeChannelByChargeWayClientType(chargeWayIndex, clientType));
	}

	@Override
	public ModelResult<MemberChargeWay> queryMemberChargeWay(
			int chargeWayIndex, int clientType) {
		return new ModelResult<MemberChargeWay>(memberChargeWayManager.queryMemberChargeWayByChargeWayIndexClientType(chargeWayIndex, clientType));
	}

}
