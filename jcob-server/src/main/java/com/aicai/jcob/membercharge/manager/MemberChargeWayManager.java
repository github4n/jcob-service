package com.aicai.jcob.membercharge.manager;

import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;

public interface MemberChargeWayManager {

	MemberChargeWay insertMemberChargeWay(MemberChargeWay chargeWay);
	
	MemberChargeWay queryMemberChargeWayById(long id);
	
	MemberChargeWay queryMemberChargeWayByChargeWayIndexClientType(int chargeWayIndex,int clientType);
	
	int updateMemberChargeWayFeeFlag(long id,int feeFlag);
	
	MemberChargeChannel queryMemberChargeChannelByChargeWayClientType(int chargeWayIndex,int clientType);
}
