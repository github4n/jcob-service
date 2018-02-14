package com.aicai.jcob.test.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.manager.MemberChargeWayManager;
import com.aicai.jcob.test.TestBase;

public class MemberChargeWayTest extends TestBase {

	@Autowired
	@Qualifier("memberChargeWayManagerImpl")
	private MemberChargeWayManager memberChargeWayManager;
	
	@Test
	public void testInsert() {
		MemberChargeWay way = new MemberChargeWay();
		way.setChargeWayIndex(6);
		way.setChargeWayName("WAY名字");
		way.setChargeChannelIndex(8);
		way.setChargeChannelName("channel名字");
		way.setFeeFlag(1);
		way.setClientType(9);
		memberChargeWayManager.insertMemberChargeWay(way);
		System.out.println(way.getId());
	}
	
	@Test
	public void testQuery() {
		MemberChargeWay way = memberChargeWayManager.queryMemberChargeWayById(1l);
		System.out.println(way.getChargeWayName());
	}
	
	@Test
	public void testQueryByWayChannel(){
		MemberChargeWay way = memberChargeWayManager.queryMemberChargeWayByChargeWayIndexClientType(6, 9);
		System.out.println(way.getChargeWayName());
	}
	
	@Test
	public void updateFee() {
		int result = memberChargeWayManager.updateMemberChargeWayFeeFlag(1l, 0);
		System.out.println(result);
	}
}
