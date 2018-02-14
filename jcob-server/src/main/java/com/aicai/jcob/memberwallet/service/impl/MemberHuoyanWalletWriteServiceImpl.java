package com.aicai.jcob.memberwallet.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWallet;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletResult;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletWriteService;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletManager;

public class MemberHuoyanWalletWriteServiceImpl implements MemberHuoyanWalletWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberHuoyanWalletManager memberHuoyanWalletManager;
	@Override
	public ModelResult<MemberHuoyanWallet> queryMemberHuoyanWalletByMemberId(Long memberId) {
		ModelResult<MemberHuoyanWallet> modelResult = new ModelResult<MemberHuoyanWallet>();
		try {
			if (memberId == null) {
				return modelResult.withError("param.not.null","参数不能为null");
			}
			MemberHuoyanWallet memberHuoyanWallet = memberHuoyanWalletManager.queryMemberHuoyanWalletByMemberId(memberId);
			modelResult.setModel(memberHuoyanWallet);
		} catch (Exception e) {
			logger.error("接口memberHuoyanWalletServiceImpl.queryMemberHuoyanWalletByMemberId异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageWalletResult<PageWalletVo> pageMemberHuoyanWallet(PageWalletOption pageWalletOption,
			DataPage<PageWalletVo> dataPage) {
		PageWalletResult<PageWalletVo> pageResult = new PageWalletResult<PageWalletVo>();
		try {
			DataPage<PageWalletVo> retDataPage = memberHuoyanWalletManager.pageMemberHuoyanWallet(pageWalletOption, dataPage);
			pageResult.setPage(retDataPage);
			BigDecimal sumHuoyanAmount = memberHuoyanWalletManager.sumMemberHuoyanWallet(pageWalletOption);
			pageResult.setTotalAmount(sumHuoyanAmount);
		} catch (Exception e) {
			logger.error("接口memberHuoyanWalletServiceImpl.pageMemberHuoyanWallet异常:", e);
			pageResult.withError("runtime.exception", "运行时异常!");
		}
		return pageResult;
	}

}
