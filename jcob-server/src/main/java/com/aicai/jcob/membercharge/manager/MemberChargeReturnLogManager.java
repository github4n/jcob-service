package com.aicai.jcob.membercharge.manager;

import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;

public interface MemberChargeReturnLogManager {

	MemberChargeReturnLog insertMemberChargeReturnLog(MemberChargeReturnLog returnLog);
	
	MemberChargeReturnLog queryMemberChargeReturnLogById(long id);
	
	MemberChargeReturnLog queryMemberChargeReturnLogByChargeId(long chargeId);
}
