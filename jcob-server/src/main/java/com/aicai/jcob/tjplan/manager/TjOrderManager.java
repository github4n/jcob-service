package com.aicai.jcob.tjplan.manager;

import java.util.List;

import com.aicai.jcob.tjplan.common.domain.TjOrder;

/**
 * 付费订单管理
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月29日
 */
public interface TjOrderManager {

	public TjOrder saveTjOrderAndSetId(TjOrder tjOrder);
	
	public int updateOrderPayStatus(Long orderId,Integer newPayStatus,Integer oldPayStatus);
	
	public List<TjOrder> queryOrderByPlanId(Long planId);
}
