package com.aicai.jcob.test.member;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.membercharge.common.domain.constant.MemberUseChargeWayStatus;
import com.aicai.jcob.membercharge.manager.MemberUseChargeWayManager;
import com.aicai.jcob.test.TestBase;

public class MemberUseChargeWayTest extends TestBase {

	@Autowired
	@Qualifier("memberUseChargeWayManagerImpl")
	private MemberUseChargeWayManager memberUseChargeWayManager;
	
	@Test
	public void testInsert(){
		MemberUseChargeWay way = new MemberUseChargeWay();
		way.setMemberId(120l);
		way.setChargeIndex(1);
		way.setClientType(2);
		way.setStatus(MemberUseChargeWayStatus.success);
		memberUseChargeWayManager.insertMemberUseChargeWay(way);
		System.out.println(way.getId());
	}
	
	@Test
	public void testQueryByMemberId() {
//		MemberUseChargeWay way = memberUseChargeWayManager.queryMemberUseChargeWayByMemberIdChargeWayClientType(120l, 1, 2);
//		System.out.println(way.getId());
	}
	
	@Test
	public void testQueryList() {
		ModelResult<List<MemberUseChargeWay>> r = memberUseChargeWayManager.queryMemberUseChargeWayTop5(1246l);
		System.out.println(r.getModel().size());
	}
}
