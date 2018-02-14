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
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
import com.aicai.jcob.memberwallet.common.service.MemberWalletLogWriteService;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;

public class MemberWalletLogWriteServiceImpl implements MemberWalletLogWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberWalletLogManager memberWalletLogManager;
	@Override
	public PageResult<MemberWalletLog> pageMemberWalletLog(Long memberId,List<BizType> bizTypeList, List<WalletOpType> walletOpTypeList,
			Calendar beginTime, Calendar endTime,DataPage<MemberWalletLog> dataPage) {
		PageResult<MemberWalletLog> pageResult = new PageResult<MemberWalletLog>();
		try {
			if (memberId == null || beginTime == null || endTime == null || dataPage == null) {
				return pageResult.withError("param.not.null","memberId/beginTime/endTime/dataPage参数不能为null");
			}
			dataPage = memberWalletLogManager.pageMemberWalletLog(memberId, bizTypeList, walletOpTypeList, beginTime, endTime, dataPage);
			pageResult.setPage(dataPage);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.pageMemberWalletLog异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<MemberWalletLog> queryMemberWalletLogByBiz(Long memberId, BizType bizType, Long bizId,WalletOpType walletOpType) {
		ModelResult<MemberWalletLog> modelResult = new ModelResult<MemberWalletLog>();
		try {
			if (memberId == null || bizType == null || walletOpType == null) {
				return modelResult.withError("param.not.null","memberId/bizType/walletOpType参数不能为null");
			}
			MemberWalletLog memberWalletLog = memberWalletLogManager.queryMemberWalletLogByBiz(memberId, bizType, bizId, walletOpType);
			modelResult.setModel(memberWalletLog);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.queryMemberWalletLogByBiz异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<BigDecimal> sumMemberTodayProfit(Long memberId) {
		ModelResult<BigDecimal> modelResult = new ModelResult<BigDecimal>();
		try {
			Calendar startDate = DateUtil.getTheDayZero();
			Calendar endDate = Calendar.getInstance();
			BigDecimal todayProfit = memberWalletLogManager.sumMemberProfit(memberId, startDate, endDate);
			modelResult.setModel(todayProfit);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.sumMemberTodayProfit异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Map<Integer, BigDecimal>> sumMemberWalletLogAmount(
			Long memberId, Calendar startDate, Calendar endDate) {
		ModelResult<Map<Integer, BigDecimal>> modelResult = new ModelResult<Map<Integer,BigDecimal>>();
		if (memberId == null || startDate == null || endDate == null) {
			return modelResult.withError("param.not.null","memberId/startDate/endDate参数不能为null");
		}
		try {
			Map<Integer, BigDecimal> resultMap = memberWalletLogManager.sumMemberWalletLogAmount(memberId, startDate, endDate);
			modelResult.setModel(resultMap);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.sumMemberWalletLogAmount异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}
	
	@Override
	public PageResult<PageWalletLogVo> adminPageMemberWalletLog(PageWalletLogOption pageWalletLogOption,
			DataPage<PageWalletLogVo> dataPage) {
		PageResult<PageWalletLogVo> pageResult = new PageResult<PageWalletLogVo>();
		try {
			if (pageWalletLogOption == null || dataPage == null) {
				return pageResult.withError("param.not.null","参数不能为null");
			}
			dataPage = memberWalletLogManager.adminPageMemberWalletLog(pageWalletLogOption, dataPage);
			pageResult.setPage(dataPage);
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.adminPageMemberWalletLog异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}
	
	@Override
	public ModelResult<PageWalletLogVo> adminSumMemberWalletLog(PageWalletLogOption pageWalletLogOption) {
		ModelResult<PageWalletLogVo> modelResult = new ModelResult<PageWalletLogVo>();
		try {
			modelResult.setModel(memberWalletLogManager.adminSumMemberWalletLog(pageWalletLogOption));
		} catch (Exception e) {
			logger.error("接口MemberWalletLogServiceImpl.adminSumMemberWalletLog异常:", e);
			modelResult.withError("runtime.exception", "运行时异常!");
		}
		return modelResult;
	}

}
