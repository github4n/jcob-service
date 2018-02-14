package com.aicai.jcob.test.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.manager.MemberChargeReturnLogManager;
import com.aicai.jcob.test.TestBase;

public class MemberChargeReturnLogTest extends TestBase {

	@Autowired
	@Qualifier("memberChargeReturnLogManagerImpl")
	private MemberChargeReturnLogManager memberChargeReturnLogManager;
	
	@Test
	public void testInsert() {
		MemberChargeReturnLog log = new MemberChargeReturnLog();
		log.setChargeId(999l);
		log.setChargeReturnInfo("charge succ");
		memberChargeReturnLogManager.insertMemberChargeReturnLog(log);
		System.out.println(log.getId());
	}
	
	@Test
	public void testQuery() {
		MemberChargeReturnLog log = memberChargeReturnLogManager.queryMemberChargeReturnLogById(1l);
		System.out.println(log.getChargeReturnInfo());
	}
	
	@Test
	public void testQueryByChargeId() {
		MemberChargeReturnLog log = memberChargeReturnLogManager.queryMemberChargeReturnLogByChargeId(999l);
		System.out.println(log.getChargeReturnInfo());
	}
}
