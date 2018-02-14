package com.aicai.jcob.tjplan.service.impl;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjplan.common.service.TjPlanItemWriteService;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月18日 上午11:25:31 
 * 程序的简单说明 
 */
public class TjPlanItemWriteServiceImpl implements TjPlanItemWriteService {

	@Autowired
	@Qualifier("tjPlanItemManagerImpl")
	private TjPlanItemManager tjPlanItemManager;
	
	@Override
	public PageResult<Long> queryPlanIdsByRaceId(Long raceId, DataPage<Long> page) {
		PageResult<Long> pageResult = new PageResult<Long>();
		pageResult.setPage(tjPlanItemManager.queryPlanIdsByRaceId(raceId, page));
		return pageResult;
	}

}
 