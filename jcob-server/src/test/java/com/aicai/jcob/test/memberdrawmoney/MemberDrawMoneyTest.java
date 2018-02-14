package com.aicai.jcob.test.memberdrawmoney;  

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBank;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyAuditInfoOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyMannualUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayAddOption;
import com.aicai.jcob.memberdrawmoney.common.service.MemberDrawMoneyWriteService;
import com.aicai.jcob.test.TestBase;
import com.alibaba.fastjson.JSON;


public class MemberDrawMoneyTest extends TestBase{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberDrawMoneyWriteService memberDrawMoneyWriteService;
	
	
	@Test
	public void savadrawmoneylog(){
		System.out.println("--------------------start---------------------");
		MemberDrawMoneyWayAddOption drawMoneyWayAddOption = new MemberDrawMoneyWayAddOption();
		drawMoneyWayAddOption.setBankCardNo("30000000000000101");
		drawMoneyWayAddOption.setBankName("乱七八糟");
		drawMoneyWayAddOption.setCity("胡建");
		drawMoneyWayAddOption.setProvince("大胡建");
		drawMoneyWayAddOption.setSubbankInfo("走起");
		MemberOperOption operOption = new MemberOperOption(1, 1, "1111111.1111", "1111.1111");
		ModelResult<MemberDrawMoneyWay> drawmoneyWay = memberDrawMoneyWriteService.saveMemberDrawMoneyWay(1238L, drawMoneyWayAddOption, operOption);
		memberDrawMoneyWriteService.memberDrawMoney(1238L, BigDecimal.valueOf(10), BigDecimal.valueOf(1), drawmoneyWay.getModel().getId(), operOption);
		System.out.println("--------------------end---------------------");
	}
	@Test
	public void audit(){
		System.out.println("--------------------start---------------------");
		MemberDrawMoneyAuditInfoOption option = new MemberDrawMoneyAuditInfoOption();
		option.setAuditInfo("好");
		option.setAuditor("大家好");
		option.setAuditStatus(AuditStatus.audit_pass.getIndex());
		option.setDrawMoneyLogId(40L);
		memberDrawMoneyWriteService.updateDrawMoneyLogAuditInfo(option);
		System.out.println("--------------------end---------------------");
	}
	@Test
	public void queryBank(){
		System.out.println("--------------------start---------------------");
		ModelResult<List<DrawMoneyBank>> drawmonModelResult = memberDrawMoneyWriteService.queryDrawMoneyBank();
		for (DrawMoneyBank drawMoneyBank : drawmonModelResult.getModel()) {
			System.out.println(JSON.toJSONString(drawMoneyBank));
		}
		System.out.println("--------------------end---------------------");
	}
	@Test
	public void updateDrawMoneyWithManual(){
		System.out.println("--------------------start---------------------");
		MemberDrawMoneyMannualUpdateOption updateOption = new MemberDrawMoneyMannualUpdateOption();
		updateOption.setDrawMoneyLogId(42L);
		updateOption.setStatus(DrawMoneyStatus.success.getIndex());
		updateOption.setOperator("小段");
		updateOption.setRemark("mannual***");
		ModelResult<Boolean> modelResult = memberDrawMoneyWriteService.updateDrawMoneyWithManual(updateOption);
		System.out.println("--------------------end---------------------" + modelResult.getModel());
	}
	
	
	
	
}
 