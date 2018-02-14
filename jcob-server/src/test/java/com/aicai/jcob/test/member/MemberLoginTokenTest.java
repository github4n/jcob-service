package com.aicai.jcob.test.member;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.member.common.domain.MemberLoginToken;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.manager.MemberLoginTokenManager;
import com.aicai.jcob.test.TestBase;

public class MemberLoginTokenTest extends TestBase {

	@Autowired
	@Qualifier("memberLoginTokenManagerImpl")
	private MemberLoginTokenManager memberLoginTokenManager;
	
	@Test
	public void testInsert() {
		MemberLoginToken token = new MemberLoginToken();
		token.setMemberId(2l);
		token.setMachineId("po12365");
		token.setLoginType(MemberLoginType.login_phone.getIndex());
		token.setToken("token000254");
		memberLoginTokenManager.insertMemberLoginToken(token);
		System.out.println(token.getId());
	}
	
	@Test
	public void testQuery() {
		MemberLoginToken token = memberLoginTokenManager.queryMemberLoginTokenById(4l);
		System.out.println(token.getToken());
	}
	
	@Test
	public void testUpdateStatus() {
		int result = memberLoginTokenManager.updateMemberLoginTokenStatus(4l, 52);
		System.out.println(result);
	}
	
	@Test
	public void testUpdateToken() {
		int result = memberLoginTokenManager.updateMemberLoginTokenToken(4l, "aefa874562145");
		System.out.println(result);
	}
	
	@Test
	public void testUpdate() {
		MemberLoginToken token = new MemberLoginToken();
		token.setId(4l);
		token.setMemberId(22l);
		token.setMachineId("ufo4452");
		token.setLoginType(MemberLoginType.aici_login_third.getIndex());
		token.setToken("huahua999");
		token.setStatus(68);
//		memberLoginTokenManager.updateMemberLoginToken(token);
	}
	
	@Test
	public void testQueryByMemberIdMachineId() {
		MemberLoginToken token = memberLoginTokenManager.queryMemberLoginTokenByMemberIdAndMachineId(22, "ufo4452", 3);
		System.out.println(token.getStatus());
	}
	
	@Test
	public void testQueryByTokenMachineId() {
		MemberLoginToken token = memberLoginTokenManager.queryMemberLoginTokenByTokenAndMachineId("huahua999", "ufo4452");
		System.out.println(token.getStatus());
	}
	
	@Test
	public void testQueryByMemberId() {
		List<MemberLoginToken> list = memberLoginTokenManager.queryMemberLoginTokenByMemberId(22);
		System.out.println(list.size());
	}
}
