package com.aicai.jcob.memberwallet.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.common.service.MemberWalletWriteService;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;

public class MemberWalletWriteServiceImpl implements MemberWalletWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberWalletManager memberWalletManager;
	@Override
	public ModelResult<MemberWallet> queryMemberWalletByMemberId(Long memberId) {
		ModelResult<MemberWallet> modelResult = new ModelResult<MemberWallet>();
		try {
			if (memberId == null) {
				return modelResult.withError("param.not.null","参数不能为null");
			}
			MemberWallet memberWallet = memberWalletManager.queryMemberWalletByMemberId(memberId);
			modelResult.setModel(memberWallet);
		} catch (Exception e) {
			logger.error("接口memberWalletServiceImpl.queryMemberWalletByMemberId异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageResult<PageWalletVo> pageMemberWallet(PageWalletOption pageWalletOption, DataPage<PageWalletVo> dataPage) {
		PageResult<PageWalletVo> pageResult = new PageResult<PageWalletVo>();
		try {
			pageResult.setPage(memberWalletManager.pageMemberWallet(pageWalletOption, dataPage));
		} catch (Exception e) {
			logger.error("接口memberWalletServiceImpl.pageMemberWallet异常:", e);
			pageResult.withError("runtime.exception", "运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<BigDecimal> sumMemberWallet(PageWalletOption pageWalletOption) {
		ModelResult<BigDecimal> pageResult = new ModelResult<BigDecimal>();
		try {
			pageResult.setModel(memberWalletManager.sumMemberWallet(pageWalletOption));
		} catch (Exception e) {
			logger.error("接口memberWalletServiceImpl.sumMemberWallet异常:", e);
			pageResult.withError("runtime.exception", "运行时异常!");
		}
		return pageResult;
	}
}
