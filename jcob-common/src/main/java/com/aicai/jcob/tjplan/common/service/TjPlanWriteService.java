package com.aicai.jcob.tjplan.common.service;  

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminLookLogOption;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageLookedTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyAttentionExpertTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageTjPlanToHallOption;
import com.aicai.jcob.tjplan.common.domain.result.AdminLookPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.LookerVo;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminLookLogVo;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.PageMyLookedTjplanResult;
import com.aicai.jcob.tjplan.common.domain.result.PageTjplanToHallResult;
import com.aicai.jcob.tjplan.common.domain.result.TjPlanVo;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 上午11:16:35 
 * 程序的简单说明 
 */
public interface TjPlanWriteService {
	
	/**
	 * 发布推荐方案
	 * @param member
	 * @param plan
	 * @param planItemList
	 * @param operParam
	 * @return
	 */
	public ModelResult<TjPlan> publishTjPlan(Member member,TjPlan plan,List<TjPlanItem> planItemList,MemberOperOption operParam);
	
	/**
	 * 查看推荐方案
	 * @param member
	 * @param planNo
	 * @param operParam
	 * @return
	 */
	public ModelResult<TjPlanVo> lookPlan(Member member,String planNo,MemberOperOption operParam);
	
	/**
	 * 支付付费查看
	 * 0火眼的也需要
	 * @param member
	 * @param planNo
	 * @param operParam
	 * @return
	 */
	public ModelResult<TjPlanVo> payLook(Member member,String planNo,MemberOperOption operParam);
	
	/**
	 * 推荐大厅，分页查询
	 * @param pageTjPlanToHallOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageTjplanToHallResult> pageTjPlanToHall(PageTjPlanToHallOption pageTjPlanToHallOption,DataPage<TjPlan> dataPage);
	
	/**
	 * 根据方案ID查询
	 * @param planId
	 * @return
	 */
	public ModelResult<TjPlan> queryTjPlanById(Long planId);
	
	/**
	 * 根据raceId查询专家推荐
	 * @param raceId
	 * @return
	 */
	public PageResult<Long> queryIdListByMaxRaceId(Long maxRaceId, DataPage<Long> page);
	
	/**
	 * 更新方案赛事状态
	 * @param planId
	 * @return
	 */
	public void updatePlanListRaceStatus(List<Long> planIdList,TjRaceStatus tjRaceStatus);
	
	
	
	/**
	 * 分页查询我发布的推荐
	 * @param memberId
	 * @param pageMyTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageTjplanToHallResult> pageMyPublishPlan(Long memberId,PageMyTjPlanOption pageMyTjPlanOption,DataPage<TjPlan> dataPage);
	
	/**
	 * 分页查询我查看过的推荐方案
	 * @param memberId
	 * @param pageLookedTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageMyLookedTjplanResult> pageMyLookedPlan(Long memberId,PageLookedTjPlanOption pageLookedTjPlanOption,DataPage<PageMyLookedTjplanResult> dataPage);
	
	/**
	 * 分页查询我关注的专家发布的推荐
	 * @param memberId
	 * 用户id
	 * @param pageMyTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageTjplanToHallResult> pageMyAttentionExpertPublishPlan(Long memberId,PageMyAttentionExpertTjPlanOption pageMyAttentionExpertTjPlanOption,DataPage<TjPlan> dataPage);
	
	/**
	 * 取消赛事 方案处理
	 * @param planIdList
	 * 方案id列表
	 * @param operOption
	 */
	public void cancleRace(List<Long> planIdList,MemberOperOption operOption);
	
	
	/**
	 * 首页方案推荐，根据方案编号查询
	 * @param planNoList
	 * @return
	 */
	public ModelResult<Map<String, TjPlanVo>> queryIndexTjPlanList(List<String> planNoList);
	
	/**
	 * 后台查看流水分页查询
	 * @param pageAdminLookLogOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageAdminLookLogVo> adminPageLookLog(PageAdminLookLogOption pageAdminLookLogOption ,DataPage<PageAdminLookLogVo> dataPage);
	
	/**
	 * lookLog的Plan详情
	 * @param lookLogId
	 * @return
	 */
	public ModelResult<AdminLookPlanVo> adminLookPlanDetail(Long lookLogId);
	
	/**
	 * 后台分页查询方案
	 * @param pageAdminPlanOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<PageAdminPlanVo> adminPagePlan(PageAdminPlanOption pageAdminPlanOption ,DataPage<PageAdminPlanVo> dataPage);

	/**
	 * admin查看方案详情
	 * @param planId
	 * @return
	 */
	public ModelResult<AdminLookPlanVo> adminPlanDetail(Long planId);
	
	/**
	 * 前端首页，自动推荐查询
	 * @param rule
	 * 1：查看人数降序，前N条未开赛的付费数据
	 * 2：发布时间降序，前N条未开赛的付费数据
	 * @param size
	 * 数据条数
	 * @return
	 */
	public ModelResult<List<TjPlanVo>> queryTjplanToIndexAutoRecommend(Integer rule,Integer size);
	/**
	 * 查看方案用户列表
	 * @param planId
	 * @param dataPage
	 * @return
	 */
	public PageResult<LookerVo> pagePlanLooker(Long planId,DataPage<TjLookLog> dataPage);
	/**
	 * 专家是否已经发布过改赛事推荐
	 * @param memberId
	 * @param raceId
	 * @param gameId
	 * @return true 已发布，false未发布
	 */
	public ModelResult<Boolean> isPublished(Long memberId,Long raceId,Integer gameId);
	
	
	/**
	 * 后台查看流水分页查询金额统计
	 * @param pageAdminLookLogOption
	 * @param dataPage
	 * @return
	 */
	public ModelResult<BigDecimal> adminSumPageLookLogAmount(PageAdminLookLogOption pageAdminLookLogOption);
	
}
 