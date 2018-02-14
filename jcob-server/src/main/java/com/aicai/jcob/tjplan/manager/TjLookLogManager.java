package com.aicai.jcob.tjplan.manager;

import java.math.BigDecimal;
import java.util.List;

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminLookLogOption;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminLookLogVo;

/**
 * 查看方案记录管理
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月29日
 */
public interface TjLookLogManager {
	
	/**
	 * 通过用户id和方案id查询查看记录
	 * @param memberId
	 * @param planId
	 * @return
	 */
	public TjLookLog queryTjLookLogByMemberIdAndPlanId(Long memberId,Long planId);
	
	/**
	 * 保存查看记录
	 * @param tjLookLog
	 */
	public void saveTjLookLogAndSetId(TjLookLog tjLookLog);
	
	/**
	 * 根据用户id查询用户已查看过的方案
	 * @param memberId
	 * @return
	 */
	public List<Long> queryPlanIdByMemberId(Long memberId);
	
	/**
	 * 后台查看流水分页查询
	 * @param pageAdminLookLogOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageAdminLookLogVo> adminPageLookLog(PageAdminLookLogOption pageAdminLookLogOption ,DataPage<PageAdminLookLogVo> dataPage);
	/**
	 * 后台查看流水分页查询金额统计
	 * @param pageAdminLookLogOption
	 * @param dataPage
	 * @return
	 */
	public BigDecimal adminSumPageLookLogAmount(PageAdminLookLogOption pageAdminLookLogOption);

	
	public TjLookLog queryLookLogById(Long lookLogId);
	
	public DataPage<TjLookLog> pageLookLogByPlanId(Long planId,DataPage<TjLookLog> dataPage);
}
