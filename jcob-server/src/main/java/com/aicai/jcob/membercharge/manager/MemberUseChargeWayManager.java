package com.aicai.jcob.membercharge.manager;

import java.util.Calendar;
import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.member.common.domain.option.MemberUseChargeWayOption;

public interface MemberUseChargeWayManager {

	MemberUseChargeWay insertMemberUseChargeWay(MemberUseChargeWay way);
	
	MemberUseChargeWay queryMemberUseChargeWayWithOption(MemberUseChargeWayOption option);
	
	void saveMemberUseChargeWay(MemberUseChargeWay way);
	
	int updateMemberUseChargeWayUpdateTime(Long id,Calendar updateTime);
	
	ModelResult<List<MemberUseChargeWay>> queryMemberUseChargeWayTop5(Long memberId);
	
	int updateMemberUseChargeWayStatusFailById(long id,long memberId);
}
