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
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletLogManager;
/**
 * 火眼钱包流水管理实现
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月28日
 */
public class MemberHuoyanWalletLogManagerImpl implements MemberHuoyanWalletLogManager {

	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Override
	public void saveWalletLog(MemberHuoyanWalletLog memberHuoyanWalletLog) {
		memberDbDao.insertAndSetupId("MemberHuoyanWalletLogMapper.saveWalletLog", memberHuoyanWalletLog);
	}

	@Override
	public MemberHuoyanWalletLog queryMemberWalletLogByBiz(Long memberId,
			BizType bizType, Long bizId, WalletOpType walletOpType) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("bizType", bizType.getIndex());
		paramMap.put("bizId", bizId);
		paramMap.put("walletOpType", walletOpType.getIndex());
		return (MemberHuoyanWalletLog) memberDbDao.queryObject("MemberHuoyanWalletLogMapper.queryMemberWalletLogByBiz", paramMap);
	}
	@Override
	public DataPage<MemberHuoyanWalletLog> pageMemberHuoyanWalletLog(Long memberId,List<BizType> bizTypeList, List<WalletOpType> walletOpTypeList,
			Calendar beginTime, Calendar endTime,DataPage<MemberHuoyanWalletLog> dataPage) {
		if (memberId == null || beginTime == null || endTime == null || dataPage == null) {
			return dataPage;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("bizTypeList", bizTypeList);
		paramMap.put("walletOpTypeList", walletOpTypeList);
		paramMap.put("beginTime", beginTime);
		paramMap.put("endTime", endTime);
		return memberDbDao.queryPage("MemberHuoyanWalletLogMapper.countMemberHuoyanWalletLog", "MemberHuoyanWalletLogMapper.pageMemberHuoyanWalletLog", paramMap, dataPage);
	}

	@Override
	public Map<Integer, BigDecimal> sumMemberHuoyanWalletLogAmount(
			Long memberId, Calendar startDate, Calendar endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		Map<String, BigDecimal> resultMap = memberDbDao.queryOne("MemberHuoyanWalletLogMapper.sumMemberHuoyanWalletLogAmount", paramMap);
		Map<Integer, BigDecimal> realMap = new HashMap<Integer, BigDecimal>();
		if (resultMap != null) {
			for (Entry<String, BigDecimal> result : resultMap.entrySet()) {
				realMap.put(Integer.valueOf(result.getKey()), result.getValue());
			}
		}
		
		return realMap;
	}

	@Override
	public DataPage<PageWalletLogVo> adminPageMemberHuoyanWalletLog(PageWalletLogOption pageWalletLogOption,
			DataPage<PageWalletLogVo> dataPage) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletLogOption);
		return memberDbDao.queryPage("MemberHuoyanWalletLogMapper.adminCountMemberHuoyanWalletLog",
				"MemberHuoyanWalletLogMapper.adminPageMemberHuoyanWalletLog", paraMap, dataPage);
	}

	@Override
	public PageWalletLogVo adminSumMemberHuoyanWalletLog(PageWalletLogOption pageWalletLogOption) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletLogOption);
		return memberDbDao.queryUnique("MemberHuoyanWalletLogMapper.adminSumMemberHuoyanWalletLog", paraMap);
	}

}
