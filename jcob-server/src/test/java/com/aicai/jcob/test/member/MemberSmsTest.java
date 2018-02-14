package com.aicai.jcob.test.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.member.manager.MemberSmsManager;
import com.aicai.jcob.test.TestBase;

public class MemberSmsTest extends TestBase {

	@Autowired
	@Qualifier("memberSmsManagerImpl")
	private MemberSmsManager memberSmsManager;
	
	@Test
	public void sendAuthCode() {
		memberSmsManager.sendAuthCode("13421800408");
	}
	
	@Test
	public void validateAuthCode() {
		memberSmsManager.validateAuthCode("15988888888","394400");
	}
	
	
}
