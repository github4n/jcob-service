package com.aicai.jcob.memberwallet.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletLogWriteService;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletLogManager;

public class MemberHuoyanWalletLogWriteServiceImpl implements MemberHuoyanWalletLogWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberHuoyanWalletLogManager memberHuoyanWalletLogManager;
	@Override
	public PageResult<MemberHuoyanWalletLog> pageMemberHuoyanWalletLog(Long memberId, List<BizType> bizTypeList,
			List<WalletOpType> walletOpTypeList, Calendar beginTime,Calendar endTime, DataPage<MemberHuoyanWalletLog> dataPage) {
		PageResult<MemberHuoyanWalletLog> pageResult = new PageResult<MemberHuoyanWalletLog>();
		try {
			if (memberId == null || beginTime == null || endTime == null || dataPage == null) {
				return pageResult.withError("param.not.null","memberId/beginTime/endTime/dataPage参数不能为null");
			}
			dataPage = memberHuoyanWalletLogManager.pageMemberHuoyanWalletLog(memberId, bizTypeList, walletOpTypeList, beginTime, endTime, dataPage);
			pageResult.setPage(dataPage);
		} catch (Exception e) {
			logger.error("接口MemberHuoyanWalletLogServiceImpl.pageMemberHuoyanWalletLog异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<MemberHuoyanWalletLog> queryMemberHuoyanWalletLogByBiz(
			Long memberId, BizType bizType, Long bizId,WalletOpType walletOpType) {
		ModelResult<MemberHuoyanWalletLog> modelResult = new ModelResult<MemberHuoyanWalletLog>();
		try {
			if (memberId == null || bizType == null || walletOpType == null) {
				return modelResult.withError("param.not.null","memberId/bizType/walletOpType参数不能为null");
			}
			MemberHuoyanWalletLog memberHuoyanWalletLog = memberHuoyanWalletLogManager.queryMemberWalletLogByBiz(memberId, bizType, bizId, walletOpType);
			modelResult.setModel(memberHuoyanWalletLog);
		} catch (Exception e) {
			logger.error("接口MemberHuoyanWalletLogServiceImpl.queryMemberHuoyanWalletLogByBiz异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Map<Integer, BigDecimal>> sumMemberHuoyanWalletLogAmount(
			Long memberId, Calendar startDate, Calendar endDate) {
		ModelResult<Map<Integer, BigDecimal>> modelResult = new ModelResult<Map<Integer,BigDecimal>>();
		if (memberId == null || startDate == null || endDate == null) {
			return modelResult.withError("param.not.null","memberId/startDate/endDate参数不能为null");
		}
		try {
			Map<Integer, BigDecimal> resultMap = memberHuoyanWalletLogManager.sumMemberHuoyanWalletLogAmount(memberId, startDate, endDate);
			modelResult.setModel(resultMap);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.sumMemberWalletLogAmount异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageResult<PageWalletLogVo> adminPageMemberHuoyanWalletLog(PageWalletLogOption pageWalletLogOption,
			DataPage<PageWalletLogVo> dataPage) {
		PageResult<PageWalletLogVo> pageResult = new PageResult<PageWalletLogVo>();
		try {
			if (pageWalletLogOption == null || dataPage == null) {
				return pageResult.withError("param.not.null", "参数不能为null");
			}
			dataPage = memberHuoyanWalletLogManager.adminPageMemberHuoyanWalletLog(pageWalletLogOption, dataPage);
			pageResult.setPage(dataPage);
		} catch (Exception e) {
			logger.error("接口MemberHuoyanWalletLogServiceImpl.adminPageMemberHuoyanWalletLog异常:", e);
			pageResult.withError("runtime.exception", "运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<PageWalletLogVo> adminSumMemberHuoyanWalletLog(PageWalletLogOption pageWalletLogOption) {
		ModelResult<PageWalletLogVo> modelResult = new ModelResult<PageWalletLogVo>();
		try {
			PageWalletLogVo pageWalletLogVo = memberHuoyanWalletLogManager.adminSumMemberHuoyanWalletLog(pageWalletLogOption);
			modelResult.setModel(pageWalletLogVo);
		} catch (Exception e) {
			logger.error("接口MemberHuoyanWalletLogServiceImpl.adminSumMemberHuoyanWalletLog异常:", e);
			modelResult.withError("runtime.exception", "运行时异常!");
		}
		return modelResult;
	}
	
}
