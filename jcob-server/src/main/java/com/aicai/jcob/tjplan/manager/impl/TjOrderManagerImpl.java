package com.aicai.jcob.tjplan.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.manager.TjOrderManager;

public class TjOrderManagerImpl implements TjOrderManager {
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao tjplanDbDao;
	@Override
	public TjOrder saveTjOrderAndSetId(TjOrder tjOrder) {
		tjplanDbDao.insertAndSetupId("TjOrderMapper.saveTjOrder", tjOrder);
		return tjOrder;
	}

	@Override
	public int updateOrderPayStatus(Long orderId, Integer newPayStatus,Integer oldPayStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", orderId);
		paramMap.put("newPayStatus", newPayStatus);
		paramMap.put("oldPayStatus", oldPayStatus);
		paramMap.put("updateTime", Calendar.getInstance());
		return tjplanDbDao.update("TjOrderMapper.updateOrderPayStatus", paramMap);
	}

	@Override
	public List<TjOrder> queryOrderByPlanId(Long planId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("planId", planId);
		return tjplanDbDao.queryList("TjOrderMapper.queryOrderByPlanId", paraMap);
	}

}
