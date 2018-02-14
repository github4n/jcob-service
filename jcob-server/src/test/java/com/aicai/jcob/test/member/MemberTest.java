package com.aicai.jcob.test.member;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.common.domain.constant.RegisterType;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.common.domain.result.MemberResult;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.test.TestBase;

public class MemberTest extends TestBase {

	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Test
	public void testQuery(){
		Member member = memberManager.queryMemberById(1138l).getModel();
		System.out.println(member.getNickName());
	}
	
	@Test
	public void testInsert() {
		Member member = new Member();
		member.setNo("jcob_id");
		member.setPassword("654321");
		member.setNickName("昵称01");
		member.setPhone("13500001111");
		member.setIcon("/head/...jpg");
		member.setEmail("member001@aicai.com");
		member.setClientType(ClientType.android.getIndex());
		member.setCertNo("44121125412563011145");
		member.setRealName("测试001");
		member.setClientVersion("0.1.1");
		memberManager.insertMember(member);
		System.out.println(member.getId());
	}
	
	@Test
	public void testUpdateStatus() {
		int result = memberManager.updateMemberStatusById(3l, 5);
		System.out.println(result);
	}
	
	@Test
	public void testUpdate() {
		Member member = new Member();
		member.setId(3l);
		member.setNo("aicai8547");
		member.setCertNo("7777777");
		member.setPassword("pwdqq541");
		member.setNickName("新昵称032");
		member.setIcon("/home/3l.png");
		member.setEmail("member003@hotmail.com");
		member.setClientType(ClientType.ios.getIndex());
		member.setCertNo("33121125412563011145");
		member.setRealName("测试003");
		member.setClientVersion("1.2.3");
//		memberManager.updateMember(member);
	}
	
	@Test
	public void testQueryMemberVo() {
		MemberResult vo = memberManager.queryMemberVoByMemberId(1110l).getModel();
		System.out.println(vo.getRealName());
	}
	
	@Test
	public void testPage1() {
		MemberExpertOption option = new MemberExpertOption();
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DAY_OF_YEAR, -30);
		option.setStartTime(startTime);
		option.setEndTime(Calendar.getInstance());
		option.setClientType(1);
		
		DataPage<Member> page = new DataPage<Member>(1,20);
		
		DataPage<MemberResult> pages = memberManager.pageMemberVoByOption(option,page);
		
		System.out.println(pages.getDataList().size());
	}
	
	@Test
	public void testPage2() {
		MemberExpertOption option = new MemberExpertOption();
		option.setLevel(2);
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DAY_OF_YEAR, -30);
		option.setStartTime(startTime);
		option.setEndTime(Calendar.getInstance());
		option.setClientType(1);
		
		DataPage<Member> page = new DataPage<Member>(1,20);
		
		DataPage<MemberResult> pages = memberManager.pageMemberVoByOption(option,page);
		
		System.out.println(pages.getDataList().size());
	}
	
	@Test
	public void testRegister() {
		String phone = "13600000001";
		Member member = new Member();
		member.setNo("jcob");
		member.setPassword("123456");
		member.setPhone(phone);
		
		RegisterOption registerOption = new RegisterOption();
		registerOption.setRegisterId(phone);
		registerOption.setRegisterType(RegisterType.phone_register.getIndex());
		
		MemberOperOption memberOperOption = new MemberOperOption(1,ClientType.android.getIndex(),"192.168.3.108",null);
		
		
		
		ModelResult<Member> r = memberManager.register(member, registerOption, memberOperOption);
		
		System.out.println(r.getModel());
		
	}
	
	@Test
	public void testLoginByPassword() {
		MemberOperOption mop = new MemberOperOption(1,2,"192.168.5.6",null);
		
		ModelResult<Member> m = memberManager.queryMemberByPassword(MemberLoginType.login_phone.getIndex(),"13500001111","123456",mop);
		System.out.println(m.getModel());
	}
	
	@Test
	public void testModifyNickName(){
//		memberManager.modifyMemberNickName(1125l, "tanglong2");
		memberManager.modifyMemberNickName(1130l, "郁郁葱葱");
	}
	
	@Test
	public void testUpdatePasswordById(){
		int result = memberManager.updateMemberPasswordById(1138l, "test123+?.");
		System.out.println(result);
	}
	
	@Test
	public void testThirdRegister(){
		Member member = new Member();
		member.setPhone("15989567406");
		member.setNickName("tttddttt");
		
		RegisterOption r = new RegisterOption();
		r.setRegisterType(RegisterType.aicai_login_register.getIndex());
		r.setRegisterId("100457");
		r.setPassword("548745+-*");
		
		MemberOperOption m = new MemberOperOption(null,null,null,null);
		
		memberManager.thirdRegister(member, r, m);
	}
	
	@Test
	public void testReset(){
		MemberOperOption option = new MemberOperOption(0,0,"","");
		memberManager.updateMemberPasswordByMobile("13380340367","a111111",option);
	}
	
	@Test
	public void testLogin(){
		MemberOperOption option = new MemberOperOption(0,0,"","");
		memberManager.queryMemberByPassword(MemberLoginType.login_phone.getIndex(),"13380340367","a111111",option);
	}
}
