package com.aicai.jcob.test.memberwallet;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.constant.AmountType;
import com.aicai.jcob.memberwallet.common.domain.constant.MemberBizExceptionBillStatus;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageMemberBizExceptionBillOption;
import com.aicai.jcob.memberwallet.common.service.MemberBizExceptionBillWriteService;
import com.aicai.jcob.test.TestBase;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;


public class MemberBizExceptionBillTest extends TestBase{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberBizExceptionBillWriteService memberBizExceptionBillWriteService;

	@Test
	public void saveBizExceptionBill(){
		System.out.println("------------------start-------------------");
		MemberBizExceptionBill bill = new MemberBizExceptionBill();
		bill.setMemberId(1134L);
		bill.setAmount(BigDecimal.valueOf(22));
		bill.setAmountType(AmountType.huoyan.getIndex());
		bill.setWalletOpType(WalletOpType.add_money.getIndex());
		bill.setOperator("saniv");
		bill.setRemark("扣款");
		memberBizExceptionBillWriteService.saveBizExceptionBill(bill);
		System.out.println("------------------end-------------------");
	}
	@Test
	public void updateBizExceptionBillStatus(){
		System.out.println("------------------start-------------------");
		memberBizExceptionBillWriteService.updateBizExceptionBillStatus(2L, MemberBizExceptionBillStatus.invalid.getIndex(), MemberBizExceptionBillStatus.create.getIndex());
		System.out.println("------------------end-------------------");
	}
	
	@Test
	public void pageBizExceptonBill(){
		System.out.println("------------------start-------------------");
		
		PageMemberBizExceptionBillOption billOption = new PageMemberBizExceptionBillOption();
		billOption.setAmountType(AmountType.huoyan);
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1134L);
		billOption.setMemberIdList(memberIdList);;
		billOption.setWalletOpType(WalletOpType.add_money);
		DataPage<MemberBizExceptionBill> dataPage = new DataPage<MemberBizExceptionBill>();
		
		PageResult<MemberBizExceptionBill> retDataPage = memberBizExceptionBillWriteService.pageBizExceptonBill(billOption, dataPage);
		System.out.println("------------------end-------------------");
	}
	@Test
	public void queryBizExceptionBillById(){
		System.out.println("------------------start-------------------");
		MemberBizExceptionBill bill = memberBizExceptionBillWriteService.queryBizExceptionBillById(2L).getModel();
		System.err.println(com.alibaba.fastjson.JSON.toJSON(bill));
		System.out.println("------------------end-------------------");
	}
	@Test
	public void excuteBizExceptionBill(){
		System.out.println("------------------start-------------------");
		memberBizExceptionBillWriteService.excuteBizExceptionBill(3L);
		System.out.println("------------------end-------------------");
	}
	
	
}
 