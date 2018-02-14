package com.aicai.jcob.membercharge.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberChargeLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;
import com.aicai.jcob.membercharge.common.option.MemberChargeOption;
import com.aicai.jcob.membercharge.common.result.MemberChargeResult;
import com.aicai.jcob.membercharge.common.service.MemberChargeLogWriteService;
import com.aicai.jcob.membercharge.manager.MemberChargeLogManager;

public class MemberChargeLogWriteServiceImpl implements MemberChargeLogWriteService {

	@Autowired
	@Qualifier("memberChargeLogManagerImpl")
	private MemberChargeLogManager memberChargeLogManager;
	
	/*@Override
	public ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeLog log, MemberOperOption memberOperOption) {
		return memberChargeLogManager.saveMemberChargeLog(log, memberOperOption);
	}*/

	@Override
	public ModelResult<MemberChargeLog> queryMemberChargeLogByChargeNo(
			String chargeNo) {
		return memberChargeLogManager.queryMemberChargeLogByChargeNo(chargeNo);
	}

	@Override
	public boolean chargeThird(MemberChargeReturnLog returnLog,MemberChargeLog memberChargeLog,MemberOperOption memberOperOption) {
		return memberChargeLogManager.chargeThird(returnLog,memberChargeLog,memberOperOption);
	}

	@Override
	public ModelResult<MemberChargeLog> saveMemberChargeLog(
			MemberChargeWay memberChargeWay, MemberChargeChannel chargeChannel,
			MemberChargeLogOption option, Map<String, Object> extraMap,String userIp, String frontServerIp) {
		return memberChargeLogManager.saveMemberChargeLog(memberChargeWay, chargeChannel, option, 
				extraMap, userIp, frontServerIp);
	}

	@Override
	public boolean chargeThird(MemberChargeReturnLog returnLog,
			MemberOperOption memberOperOption,String thirdChargeNo,String acctName,String certNo) {
		return memberChargeLogManager.chargeThird(returnLog,memberOperOption,thirdChargeNo,acctName,certNo);
	}

	/*@Override
	public ModelResult<Boolean> chargeThird(String chargeNo,String nickName,Long memberId,BigDecimal amount,BigDecimal handingCost,MemberOperOption memberOperOption) {
		return memberChargeLogManager.chargeThird(chargeNo, nickName, memberId, amount, handingCost, memberOperOption);
	}*/

	@Override
	public PageResult<MemberChargeResult> queryMemberChargeByPage(MemberChargeOption option, DataPage<MemberChargeResult> page) {
		PageResult<MemberChargeResult> pageResult = new PageResult<MemberChargeResult>();
		pageResult.setPage(memberChargeLogManager.queryMemberChargeByPage(option, page));
		return pageResult;
	}

	@Override
	public ModelResult<MemberChargeResult> queryMemberChargeAmountSum(MemberChargeOption option) {
		ModelResult<MemberChargeResult> result = new ModelResult<MemberChargeResult>();
		result.setModel(memberChargeLogManager.queryMemberChargeAmountSum(option));
		return result;
	}
	
	@Override
	public ModelResult<MemberChargeReturnLog> initChargeReturnLog(
			MemberChargeLog chargeLog, String queryString,
			String responseJsonTxt) {
		return memberChargeLogManager.initChargeReturnLog(chargeLog, queryString, responseJsonTxt);
	}

	@Override
	public ModelResult<Boolean> chargeThird(long memberChargeLogId) {
		return memberChargeLogManager.chargeThird(memberChargeLogId);
	}

	@Override
	public ModelResult<Boolean> updateMemberChargeLogBankCodePayType(Long id,String bankCode,Integer payType){
		return memberChargeLogManager.updateMemberChargeLogBankCodePayType(id, bankCode,payType);
	}

	@Override
	public ModelResult<BigDecimal> queryMemberLianLianCardChargeDayTotalAmount(
			long memberId, String bankCard, int status, Calendar createDate) {
		return memberChargeLogManager.queryMemberCardChargeDayTotalAmount(memberId, bankCard, status, createDate,
				new int[]{ ChargeChannel.lianlian_sdk.getIndex(),ChargeChannel.lianlian_wap.getIndex() });
	}

	@Override
	public ModelResult<BigDecimal> queryMemberLianLianCardChargeMonthTotalAmount(
			long memberId, String bankCard, int status, Calendar createDate) {
		return memberChargeLogManager.queryMemberCardChargeMonthTotalAmount(memberId, bankCard, status, createDate,
				new int[]{ ChargeChannel.lianlian_sdk.getIndex(),ChargeChannel.lianlian_wap.getIndex() });
	}

}
