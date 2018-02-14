package com.aicai.jcob.membercharge.common.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberChargeLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.option.MemberChargeOption;
import com.aicai.jcob.membercharge.common.result.MemberChargeResult;

public interface MemberChargeLogWriteService {

	
//	ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeLog log,MemberOperOption memberOperOption);
	
	/**
	 * 保存充值单信息
	 * @param memberChargeWay 用户充值方式
	 * @param chargeChannel 用户充值渠道
	 * @param option 充值单数据
	 * @param extraMap 额外数据
	 * @param userIp 用户请救IP
	 * @param frontServerIp 前端IP
	 * @return
	 */
	ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeWay memberChargeWay,MemberChargeChannel chargeChannel,
			MemberChargeLogOption option , Map<String, Object> extraMap ,String userIp,String frontServerIp);
	
	/**
	 * 根据chargeNo查询充值单
	 * @param chargeNo
	 * @return
	 */
	ModelResult<MemberChargeLog> queryMemberChargeLogByChargeNo(String chargeNo);
	
	/**
	 * 合作方返回处理
	 * 如果合作方返回的充值结果成功
	 * 修改充值单与充值返回单信息，钱包，火眼钱包金额
	 * @param returnLog
	 * @param memberOperOption
	 * @return
	 */
	boolean chargeThird(MemberChargeReturnLog returnLog,MemberOperOption memberOperOption,String thirdChargeNo,String acctName,String certNo);
	
	/**
	 * 自动补单
	 * @param returnLog 充值返回单
	 * @param memberChargeLog 充值单
	 * @param memberOperOption 用户操作记录
	 * @return
	 */
	boolean chargeThird(MemberChargeReturnLog returnLog,MemberChargeLog memberChargeLog,MemberOperOption memberOperOption);
	
	/**
	 * 
	 * @param chargeNo 订单号
	 * @param nickName 昵称
	 * @param memberId 会员ID
	 * @param amount 金额
	 * @param handingCost 手续费
	 * @param memberOperOption 操作记录
	 * @return
	 */
//	ModelResult<Boolean> chargeThird(String chargeNo,String nickName,Long memberId,BigDecimal amount,BigDecimal handingCost,MemberOperOption memberOperOption);
	
	/**
	 * 手工补单
	 * @param memberChargeLogId 充值单号
	 * @return
	 */
	ModelResult<Boolean> chargeThird(long memberChargeLogId);
	
	/**
	 * 分页查询专家+用户信息
	 * @param option 查询参数集
	 * @param page 分页条件
	 * @return
	 */
	PageResult<MemberChargeResult> queryMemberChargeByPage(MemberChargeOption option, DataPage<MemberChargeResult> page);
	
	/**
	 * 统计充值的金额总和
	 * @param option
	 * @return
	 */
	ModelResult<MemberChargeResult> queryMemberChargeAmountSum(MemberChargeOption option);
	
	/**
	 * 根据memberChargeLog与晌应信息，初始化memberChargeReturnLog(充值返回单)
	 * @param chargeLog 充值
	 * @param queryString 响应原始数据
	 * @param responseJsonTxt 响应信息
	 * @return
	 */
	ModelResult<MemberChargeReturnLog> initChargeReturnLog(MemberChargeLog chargeLog,String queryString,String responseJsonTxt);
	
	/**
	 * 连连充值成功后会返回BankCode
	 * 更新MemberChargeLogBankCode的bankCode
	 * @param id
	 * @param bankCode
	 * @return
	 */
	ModelResult<Boolean> updateMemberChargeLogBankCodePayType(Long id,String bankCode,Integer payType);
	
	/**
	 * 查询用户连连银行充值的日总金额
	 * @param memberId 用户ID
	 * @param bankCard 银行卡号
	 * @param status 充值单状态
	 * @param createDate 充值单创建时间
	 * @return 用户当前银行卡连连充值的单日总金额
	 */
	ModelResult<BigDecimal> queryMemberLianLianCardChargeDayTotalAmount(long memberId,String bankCard,int status,Calendar createDate);
	
	/**
	 * 查询用户连连银行充值的月总金额
	 * @param memberId 用户ID
	 * @param bankCard 银行卡号
	 * @param status 充值单状态
	 * @param createDate 充值单创建时间
	 * @return 用户当前银行卡连连充值的单月总金额
	 */
	ModelResult<BigDecimal> queryMemberLianLianCardChargeMonthTotalAmount(long memberId,String bankCard,int status,Calendar createDate);
}
