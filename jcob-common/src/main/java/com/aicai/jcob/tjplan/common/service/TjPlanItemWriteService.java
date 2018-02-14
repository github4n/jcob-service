package com.aicai.jcob.tjplan.common.service;  

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月18日 上午11:22:33 
 * 程序的简单说明 
 */
public interface TjPlanItemWriteService {

	/**
	 * 根据raceId查询专家推荐
	 * @param raceId
	 * @return
	 */
	PageResult<Long> queryPlanIdsByRaceId(Long raceId, DataPage<Long> page);
}
 