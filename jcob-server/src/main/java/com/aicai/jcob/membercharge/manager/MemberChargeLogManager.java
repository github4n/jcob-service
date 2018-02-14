package com.aicai.jcob.membercharge.manager;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberChargeLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.option.MemberChargeOption;
import com.aicai.jcob.membercharge.common.result.MemberChargeResult;

/**
 * 用户充值流水表管理接口
 * @author hailong.zhang
 *
 */
public interface MemberChargeLogManager {

	MemberChargeLog insertMemberChargeLog(MemberChargeLog memberChargeLog);
	
	MemberChargeLog queryMemberChargeLogById(long id);
	
	ModelResult<MemberChargeLog> queryMemberChargeLogByChargeNo(String chargeNo);
	
	int updateMemberChargeLogStatusById(long id,int status);
	
	int updateMemberChargeLogStatusThirdChargeNoById(long id,int status,String thirdChargeNo);
	
	int updateMemberChargeLogStatusThirdChargeNoRespTimeById(long id,int status,String thirdChargeNo,Calendar respTime);
	
	int updateMemberChargeLogStatusHandCostRespTimeById(long id,int status,BigDecimal handingCost,Calendar respTime);
	
	ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeLog memberChargeLog,MemberOperOption memberOperOption);
	
	ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeWay memberChargeWay,MemberChargeChannel chargeChannel,
			MemberChargeLogOption option , Map<String, Object> extraMap ,String userIp,String frontServerIp);
	
	boolean chargeThird(MemberChargeReturnLog returnLog,MemberChargeLog memberChargeLog,MemberOperOption memberOperOption);
	
	boolean chargeThird(MemberChargeReturnLog returnLog,MemberOperOption memberOperOption,String thirdChargeNo,String acctName,String certNo);
	
//	ModelResult<Boolean> chargeThird(String chargeNo,String nickName,Long memberId,BigDecimal amount,BigDecimal handingCost,MemberOperOption memberOperOption);
	
	ModelResult<Boolean> chargeThird(long memberChargeLogId);
	
	DataPage<MemberChargeResult> queryMemberChargeByPage(MemberChargeOption option, DataPage<MemberChargeResult> page);
	
	MemberChargeResult queryMemberChargeAmountSum(MemberChargeOption option);
	
	ModelResult<MemberChargeReturnLog> initChargeReturnLog(MemberChargeLog chargeLog,String queryString,String responseJsonTxt);
	
	public ModelResult<Boolean> updateMemberChargeLogBankCodePayType(Long id, String bankCode,Integer payType);
	
	ModelResult<BigDecimal> queryMemberCardChargeDayTotalAmount(long memberId,String bankCard,int status,Calendar createDate,int[] channelArray);
	
	ModelResult<BigDecimal> queryMemberCardChargeMonthTotalAmount(long memberId,String bankCard,int status,Calendar createDate,int[] channelArray);
}
