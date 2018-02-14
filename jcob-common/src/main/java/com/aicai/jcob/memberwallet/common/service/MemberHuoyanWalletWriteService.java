package com.aicai.jcob.memberwallet.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWallet;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletResult;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;

/**
 * 用户现金钱包service
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月29日
 */
public interface MemberHuoyanWalletWriteService {
	/**
	 * 用户id查询用户现金钱包
	 * @param memberId
	 * @return
	 */
	public ModelResult<MemberHuoyanWallet> queryMemberHuoyanWalletByMemberId(Long memberId);
	/**
	 * 分页查询用户钱包列表
	 * @param pageWalletOption
	 * @param dataPage
	 * @return
	 */
	public PageWalletResult<PageWalletVo> pageMemberHuoyanWallet(PageWalletOption pageWalletOption ,DataPage<PageWalletVo> dataPage);
}
