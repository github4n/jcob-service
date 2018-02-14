package com.aicai.jcob.test.member;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.manager.MemberLoginManager;
import com.aicai.jcob.test.TestBase;

public class MemberLoginTest extends TestBase {

	@Autowired
	@Qualifier("memberLoginManagerImpl")
	private MemberLoginManager memberLoginManager;
	
	@Test
	public void testInsert() {
		MemberLogin memLogin = new MemberLogin();
		memLogin.setMemberId(1l);
		memLogin.setLoginId("135xxxxxxxx");
		memberLoginManager.insertMemberLogin(memLogin);
		System.out.println(memLogin.getId());
	}
	
	@Test
	public void testQuery() {
		MemberLogin log = memberLoginManager.queryMemberLoginById(3);
		System.out.println(log.getLoginId());
	}
	
	@Test
	public void testUpdateStatus() {
		int result = memberLoginManager.updateMemberLoginStatusById(3, 8);
		System.out.println(result);
	}
	
	@Test
	public void testUpdate() {
		MemberLogin memLogin = new MemberLogin();
		memLogin.setId(3);
		memLogin.setMemberId(9l);
		memLogin.setLoginId("180xxxxxxxx");
		memLogin.setLoginType(2);
		memLogin.setStatus(21);
//		memberLoginManager.updateMemberLogin(memLogin);
	}
	
	@Test
	public void testQueryByLoginType() {
		MemberLogin	mem = memberLoginManager.queryMemberLoginByLoginTypeAndLoginId(2, "180xxxxxxxx");
		System.out.println(mem.getMemberId());
	}
	
	@Test
	public void testQuery2() {
		List<MemberLogin> list = memberLoginManager.queryMemberLoginTokenByMemberId(1125l);
		System.out.println(list.size());
	}
}
