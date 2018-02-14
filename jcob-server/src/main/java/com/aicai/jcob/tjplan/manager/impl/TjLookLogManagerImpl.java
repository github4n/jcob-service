package com.aicai.jcob.tjplan.manager.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminLookLogOption;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminLookLogVo;
import com.aicai.jcob.tjplan.manager.TjLookLogManager;

public class TjLookLogManagerImpl implements TjLookLogManager {
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao tjplanDbDao;
	@Override
	public TjLookLog queryTjLookLogByMemberIdAndPlanId(Long memberId,Long planId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("planId", planId);
		return (TjLookLog) tjplanDbDao.queryObject("TjLookLogMapper.queryTjLookLogByMemberIdAndPlanId", paramMap);
	}

	@Override
	public void saveTjLookLogAndSetId(TjLookLog tjLookLog) {
		tjplanDbDao.insertAndSetupId("TjLookLogMapper.saveTjLookLog", tjLookLog);
	}

	@Override
	public List<Long> queryPlanIdByMemberId(Long memberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		return  tjplanDbDao.queryList("TjLookLogMapper.queryPlanIdByMemberId", paramMap);
	}

	@Override
	public DataPage<PageAdminLookLogVo> adminPageLookLog(PageAdminLookLogOption pageAdminLookLogOption,
			DataPage<PageAdminLookLogVo> dataPage) {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startDate", pageAdminLookLogOption.getStartDate());
		paraMap.put("endDate", pageAdminLookLogOption.getEndDate());
		paraMap.put("expertLevelList", pageAdminLookLogOption.getExpertLevelList());
		paraMap.put("leftAndEqualAmount", pageAdminLookLogOption.getLeftAndEqualAmount());
		paraMap.put("rightAndEqualAmount", pageAdminLookLogOption.getRightAndEqualAmount());
		paraMap.put("memberIdList", pageAdminLookLogOption.getMemberIdList());
		paraMap.put("clientTypeList", pageAdminLookLogOption.getClientTypeList());
		paraMap.put("raceStatuList", pageAdminLookLogOption.getRaceStatuList());
		paraMap.put("winstatus", pageAdminLookLogOption.getWinstatus());
		paraMap.put("raceTypeList", pageAdminLookLogOption.getRaceTypeList());
		paraMap.put("gameTypeList", pageAdminLookLogOption.getGameTypeList());
		if (StringUtils.isNotBlank(pageAdminLookLogOption.getTjPlanNo())) {
			paraMap.put("planId", StringUtils.substring(pageAdminLookLogOption.getTjPlanNo(), 8));
		}
		return tjplanDbDao.queryPage("TjLookLogMapper.adminCountLookLog", "TjLookLogMapper.adminPageLookLog", paraMap, dataPage);
	}

	@Override
	public TjLookLog queryLookLogById(Long lookLogId) {
		return tjplanDbDao.queryUnique("TjLookLogMapper.queryLookLogById", lookLogId);
	}

	@Override
	public DataPage<TjLookLog> pageLookLogByPlanId(Long planId,DataPage<TjLookLog> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("planId", planId);
		return tjplanDbDao.queryPage("TjLookLogMapper.countLookLogByPlanId", "TjLookLogMapper.pageLookLogByPlanId", paramMap, dataPage);
	}

	@Override
	public BigDecimal adminSumPageLookLogAmount(PageAdminLookLogOption pageAdminLookLogOption) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startDate", pageAdminLookLogOption.getStartDate());
		paraMap.put("endDate", pageAdminLookLogOption.getEndDate());
		paraMap.put("expertLevelList", pageAdminLookLogOption.getExpertLevelList());
		paraMap.put("leftAndEqualAmount", pageAdminLookLogOption.getLeftAndEqualAmount());
		paraMap.put("rightAndEqualAmount", pageAdminLookLogOption.getRightAndEqualAmount());
		paraMap.put("memberIdList", pageAdminLookLogOption.getMemberIdList());
		paraMap.put("clientTypeList", pageAdminLookLogOption.getClientTypeList());
		paraMap.put("raceStatuList", pageAdminLookLogOption.getRaceStatuList());
		paraMap.put("winstatus", pageAdminLookLogOption.getWinstatus());
		paraMap.put("raceTypeList", pageAdminLookLogOption.getRaceTypeList());
		paraMap.put("gameTypeList", pageAdminLookLogOption.getGameTypeList());
		if (StringUtils.isNotBlank(pageAdminLookLogOption.getTjPlanNo())) {
			paraMap.put("planId", StringUtils.substring(pageAdminLookLogOption.getTjPlanNo(), 8));
		}
		BigDecimal sumAmount = tjplanDbDao.queryUnique("TjLookLogMapper.adminSumPageLookLogAmount", paraMap);
		return sumAmount;
	}
	
	
}
