package com.aicai.jcob.memberwallet.common.service;

import java.math.BigDecimal;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;

/**
 * 用户现金钱包service
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月29日
 */
public interface MemberWalletWriteService {
	/**
	 * 用户id查询用户现金钱包
	 * @param memberId
	 * @return
	 */
	public ModelResult<MemberWallet> queryMemberWalletByMemberId(Long memberId);
	
	/**
	 * 分页查询用户钱包列表
	 * @param pageWalletOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageWalletVo> pageMemberWallet(PageWalletOption pageWalletOption ,DataPage<PageWalletVo> dataPage);
	
	
	/**
	 * 统计用户钱包余额
	 * @param pageWalletOption
	 * @param dataPage
	 * @return
	 */
	public ModelResult<BigDecimal> sumMemberWallet(PageWalletOption pageWalletOption);
}
