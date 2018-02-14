package com.aicai.jcob.test.member;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.constant.MemberChargeWayFeeStatus;
import com.aicai.jcob.member.common.domain.option.MemberChargeLogOption;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeWay;
import com.aicai.jcob.membercharge.common.option.MemberChargeOption;
import com.aicai.jcob.membercharge.common.result.MemberChargeResult;
import com.aicai.jcob.membercharge.manager.MemberChargeLogManager;
import com.aicai.jcob.test.TestBase;

public class MemberChargeLogTest extends TestBase {

	@Autowired
	@Qualifier("memberChargeLogManagerImpl")
	private MemberChargeLogManager memberChargeLogManager;
	
	@Test
	public void testInsert() {
		MemberChargeLog charge = new MemberChargeLog();
		charge.setMemberId(1l);
		charge.setThirdChargeNo("tb90192");
		charge.setAmount(new BigDecimal(100));
		charge.setClientType(5);
		charge.setChannel(4);
		charge.setStatus(1);
		charge.setChargeWayIndex(1);
		charge.setChargeWayIndex(9);
		charge.setBankCode("8888");
		charge.setHandingCost(BigDecimal.ZERO);
		memberChargeLogManager.insertMemberChargeLog(charge);
	}
	
	@Test
	public void testQuery() {
		MemberChargeLog charge = memberChargeLogManager.queryMemberChargeLogById(1l);
		System.out.println(charge.getBankCode());
	}
	
	@Test
	public void testUpdateStatus() {
		int result = memberChargeLogManager.updateMemberChargeLogStatusById(1l, 25);
		System.out.println(result);
	}
	
	@Test
	public void testQueryByChargeNo() {
		String chargeNo = DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMdd") + "1";
		MemberChargeLog charge = memberChargeLogManager.queryMemberChargeLogByChargeNo(chargeNo).getModel();
		System.out.println(charge.getBankCode());
	}
	
	@Test
	public void testSaveMemberChargeLog(){
		MemberChargeWay way = new MemberChargeWay();
		way.setChargeWayIndex(ChargeWay.zfb_sdk.getIndex());
		way.setChargeWayName(ChargeWay.zfb_sdk.getDescription());
		way.setClientType(ClientType.android.getIndex());
		way.setChargeChannelIndex(ChargeChannel.zfb_sdk.getIndex());
		way.setChargeChannelName(ChargeChannel.zfb_sdk.getDescription());
		way.setFeeFlag(MemberChargeWayFeeStatus.free);
		
		MemberChargeChannel channel = new MemberChargeChannel();
		channel.setChargeChannelIndex(ChargeChannel.zfb_sdk.getIndex());
		
		MemberChargeLogOption log = new MemberChargeLogOption();
		log.setMemberId(1l);
		log.setAmount(BigDecimal.ZERO);
		log.setClientType(ClientType.android.getIndex());
		log.setReturnUrl("./r");
		log.setNotifyUrl("./n");
		
		
		
		memberChargeLogManager.saveMemberChargeLog(way, channel, log, new HashMap<String,Object>(), "", "");
	}
	
	@Test
	public void queryMemberChargeByPage() {
		MemberChargeOption option = new MemberChargeOption();
		DataPage<MemberChargeResult> page = new DataPage<MemberChargeResult>();
		page = memberChargeLogManager.queryMemberChargeByPage(option, page);
		for (MemberChargeResult item : page.getDataList()) {
			System.out.println(item.getId());
		}
	}
	
	@Test
	public void queryMemberChargeAmountSum() {
		MemberChargeOption option = new MemberChargeOption();
		MemberChargeResult memberChargeResult = memberChargeLogManager.queryMemberChargeAmountSum(option);
		System.out.println(memberChargeResult.getTotalFailed());
		System.out.println(memberChargeResult.getTotalSuccess());
	}
}
