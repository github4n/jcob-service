package com.aicai.jcob.test.member;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.manager.MemberChargeChannelManager;
import com.aicai.jcob.test.TestBase;

public class MemberChargeChannelTest extends TestBase {

	@Autowired
	@Qualifier("memberChargeChannelManagerImpl")
	private MemberChargeChannelManager memberChargeChannelManager;
	
	@Test
	public void testInsert() {
		MemberChargeChannel channel = new MemberChargeChannel();
		channel.setChargeChannelIndex(21);
		channel.setChargeChannelName("测试渠道");
		channel.setStatus(5);
		channel.setFeeRatio(new BigDecimal(2.3));
		channel.setMaxFeeAmount(new BigDecimal(5.8));
		channel.setMinFeeAmount(new BigDecimal(1.5));
		memberChargeChannelManager.insertMemberChargeChannel(channel);
		System.out.println(channel.getId());
	}
	
	@Test
	public void testQuery() {
		MemberChargeChannel channel = memberChargeChannelManager.queryMemberChargeChannelById(2l);
		System.out.println(channel.getChargeChannelName());
	}
	
	@Test
	public void testQueryByChannel() {
		MemberChargeChannel channel = memberChargeChannelManager.queryMemberChargeChannelByChannelIndex(21);
		System.out.println(channel.getChargeChannelName());
	}
}
