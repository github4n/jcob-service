package com.aicai.jcob.test.member;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.member.common.domain.MemberOperLog;
import com.aicai.jcob.member.common.domain.option.MemberOperLogOption;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.test.TestBase;

public class MemberOperLogTest extends TestBase {

	@Autowired
	@Qualifier("memberOperLogManagerImpl")
	private MemberOperLogManager memberOperLogManager;
	
	@Test
	public void testInsert() {
		MemberOperLog ope = new MemberOperLog();
		ope.setMemberId(5677l);
		ope.setOperType(5);
		ope.setClientType(6);
		ope.setChannel(7);
		ope.setUserIp("userip 0001");
		ope.setFrontServerIp("fsi 0002");
		ope.setBackServerIp("bsi 0003");
		ope.setRemark("remark 0004");
		memberOperLogManager.insertMemberOperLog(ope);
		System.out.println(ope.getId());
	}
	
	@Test
	public void testQuery() {
		MemberOperLog ope = memberOperLogManager.queryMemberOperLogById(2l);
		System.out.println(ope.getFrontServerIp());
	}
	
	@Test
	public void testUpdate() {
		MemberOperLog ope = new MemberOperLog();
		ope.setId(2l);
		ope.setMemberId(15677l);
		ope.setOperType(15);
		ope.setClientType(16);
		ope.setChannel(17);
		ope.setUserIp("*userip 0001");
		ope.setFrontServerIp("*fsi 0002");
		ope.setBackServerIp("*bsi 0003");
		ope.setRemark("*remark 0004");
//		memberOperLogManager.updateMemberOperLog(ope);
	}
	
	@Test
	public void testQueryByMember() {
		MemberOperLogOption option = new MemberOperLogOption();
		option.setMemberId(15677l);
		option.setOperType(15);
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DAY_OF_YEAR, -1);
		option.setBeginTime(startTime);
		option.setEndTime(Calendar.getInstance());
		List<MemberOperLog> list = memberOperLogManager.queryMemberOperLogByMemberAndOperTypeCreateTime(option);
		System.out.println(list.size());
	}
}
