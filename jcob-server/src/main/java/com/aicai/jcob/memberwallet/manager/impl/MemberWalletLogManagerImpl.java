package com.aicai.jcob.memberwallet.manager.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;
/**
 * 用户现金钱包流水管理实现
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月28日
 */
public class MemberWalletLogManagerImpl implements MemberWalletLogManager {

	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Override
	public void saveWalletLog(MemberWalletLog memberWalletLog) {
		memberDbDao.insertAndSetupId("MemberWalletLogMapper.saveWalletLog", memberWalletLog);
	}

	@Override
	public MemberWalletLog queryMemberWalletLogByBiz(Long memberId,
			BizType bizType, Long bizId, WalletOpType walletOpType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("bizType", bizType.getIndex());
		paramMap.put("bizId", bizId);
		paramMap.put("walletOpType", walletOpType.getIndex());
		return (MemberWalletLog) memberDbDao.queryObject("MemberWalletLogMapper.queryMemberWalletLogByBiz", paramMap);
	}

	@Override
	public DataPage<MemberWalletLog> pageMemberWalletLog(Long memberId,List<BizType> bizTypeList, List<WalletOpType> walletOpTypeList,
			Calendar beginTime, Calendar endTime,DataPage<MemberWalletLog> dataPage) {
		if (memberId == null || beginTime == null || endTime == null || dataPage == null) {
			return dataPage;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("bizTypeList", bizTypeList);
		paramMap.put("walletOpTypeList", walletOpTypeList);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		return memberDbDao.queryPage("MemberWalletLogMapper.countMemberWalletLog", "MemberWalletLogMapper.pageMemberWalletLog", paramMap, dataPage);
	}

	@Override
	public BigDecimal sumMemberProfit(Long memberId, Calendar startDate,
			Calendar endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		BigDecimal profit = memberDbDao.queryOne("MemberWalletLogMapper.sumMemberProfit", paramMap);
		return profit;
	}

	@Override
	public Map<Integer, BigDecimal> sumMemberWalletLogAmount(Long memberId,Calendar startDate, Calendar endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		Map<String, BigDecimal> resultMap = memberDbDao.queryOne("MemberWalletLogMapper.sumMemberWalletLogAmount", paramMap);
		Map<Integer, BigDecimal> realMap = new HashMap<Integer, BigDecimal>();
		if (resultMap != null) {
			for (Entry<String, BigDecimal> result : resultMap.entrySet()) {
				realMap.put(Integer.valueOf(result.getKey()), result.getValue());
			}
		}
		
		return realMap;
	}
	
	@Override
	public DataPage<PageWalletLogVo> adminPageMemberWalletLog(PageWalletLogOption pageWalletLogOption,
			DataPage<PageWalletLogVo> dataPage) {
		Map<String, Object> paraMap = new HashMap<String, Object>();		
		paraMap.put("option", pageWalletLogOption);
		return memberDbDao.queryPage("MemberWalletLogMapper.adminCountMemberWalletLog", "MemberWalletLogMapper.adminPageMemberWalletLog", paraMap, dataPage);
	}

	@Override
	public PageWalletLogVo adminSumMemberWalletLog(PageWalletLogOption pageWalletLogOption) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletLogOption);
		return memberDbDao.queryUnique("MemberWalletLogMapper.adminSumMemberWalletLog", paraMap);
	}

}
