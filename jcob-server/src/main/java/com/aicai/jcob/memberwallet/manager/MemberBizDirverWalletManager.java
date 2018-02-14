package com.aicai.jcob.memberwallet.manager;

import org.springframework.ui.Model;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
/**
 * 用户业务操作驱动钱包管理接口
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月27日
 * Copyright (C) 2010-2012 www.2caipiao.com Inc. All rights reserved.
 */
public interface MemberBizDirverWalletManager {

	/**
	 * 用户充值钱包操作
	 * @param memberChargeLog
	 * @return
	 */
	public ModelResult<Boolean> memberCharge(MemberChargeLog memberChargeLog);
	/**
	 * 用户提款钱包操作
	 * @param memberDrawMoneyLog
	 * @return
	 */
	public ModelResult<Boolean> memberDrawMoney(MemberDrawMoneyLog memberDrawMoneyLog);
	/**
	 * 用户查看付费方案钱包操作
	 * @param tjPlan
	 * @param tjOrder
	 * @return
	 */
	public ModelResult<Boolean> memberLookPlan(TjPlan tjPlan,TjOrder tjOrder);
	/**
	 * 退款用户提款钱包操作
	 * @param memberDrawMoneyLog
	 * @return
	 */
	public ModelResult<Boolean> refundMemberDrawMoney(MemberDrawMoneyLog memberDrawMoneyLog);
	
	/**
	 * 退款用户查看付费方案钱包操作
	 * @param tjPlan
	 * @param tjOrder
	 * @return
	 */
	public ModelResult<Boolean> refundMemberLookPlan(TjPlan tjPlan,TjOrder tjOrder);
	
	/**
	 * 异常单据操作-加款/扣款
	 * @param billId
	 * @return
	 */
	public ModelResult<Boolean> handleMemberBizExceptionBill(Long billId);
}
