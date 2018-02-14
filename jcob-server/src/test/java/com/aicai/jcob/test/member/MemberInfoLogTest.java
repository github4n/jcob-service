package com.aicai.jcob.test.member;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.member.common.domain.MemberInfoLog;
import com.aicai.jcob.member.common.domain.option.MemberInfoLogOption;
import com.aicai.jcob.member.manager.MemberInfoLogManager;
import com.aicai.jcob.test.TestBase;

public class MemberInfoLogTest extends TestBase {

	@Autowired
	@Qualifier("memberInfoLogManagerImpl")
	private MemberInfoLogManager memberInfoLogManager;
	
	@Test
	public void testInsert() {
		MemberInfoLog info = new MemberInfoLog();
		info.setMemberId(1112l);
		info.setStatus(5);
		info.setInfoType(3);
		info.setOldInfo("old info");
		info.setNewInfo("new info");
		memberInfoLogManager.insertMemberInfoLog(info);
		System.out.println(info.getId());
	}
	
	@Test
	public void testQuery() {
		MemberInfoLog info = memberInfoLogManager.queryMemberInfoLogById(3l);
		System.out.println(info.getMemberId());
	}
	
	@Test
	public void testUpdate() {
		MemberInfoLog info = new MemberInfoLog();
		info.setId(3l);
		info.setMemberId(7788l);
		info.setStatus(82);
		info.setInfoType(9);
		info.setOldInfo("iqnerr");
		info.setNewInfo("oopelkxerew");
//		memberInfoLogManager.updateMemberInfoLog(info);
	}
	
	@Test
	public void testQueryByMemberId() {
		MemberInfoLogOption option = new MemberInfoLogOption();
		option.setMemberId(7788l);
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DAY_OF_YEAR, -1);
		option.setBeginTime(startTime);
		option.setEndTime(Calendar.getInstance());
		List<MemberInfoLog> list = memberInfoLogManager.queryMemberInfoLogWithOption(option);
		System.out.println(list.size());
	}
}
