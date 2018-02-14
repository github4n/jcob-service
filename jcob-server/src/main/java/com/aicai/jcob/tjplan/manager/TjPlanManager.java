package com.aicai.jcob.tjplan.manager;  

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.aicai.appmodel.domain.GenericThree;
import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageLookedTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyAttentionExpertTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageTjPlanToHallOption;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.PageMyLookedTjplanResult;
import com.aicai.jcob.tjplan.common.domain.result.TjPlanVo;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 上午11:20:23 
 * 程序的简单说明 
 */
public interface TjPlanManager {
	/**
	 * 推荐方案
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
	public ModelResult<TjPlanVo> lookPlan(Member member,String planNo,Boolean isPay,MemberOperOption operParam);
	/**
	 * 保存方案，设置id返回方案对象
	 * @param tjPlan
	 * @return
	 */
	public TjPlan saveTjPlanAndSetId(TjPlan tjPlan);
	/**
	 * 通过方案id查询方案
	 * @param planId
	 * @return
	 */
	public TjPlan queryTjPlanById(Long planId);
	
	public DataPage<Long> queryIdListByMaxRaceId(Long maxRaceId, DataPage<Long> page);
	/**
	 * 通过方案编号查询方案
	 * @param planNo
	 * @return
	 */
	public TjPlan queryTjPlanByPlanNo(String planNo);
	
	ModelResult<TjPlan> selectFirstPlanByMemberId(Long memberId);
	
	public TjPlan selectFirstPlanByMemberIdStartTime(Long memberId,Calendar startTime);
	
	
	/**
	 * 推荐大亭列表查询
	 * @param pageTjPlanToHallOption
	 * @param dataPage
	 * @return
	 * @create_time 2016年2月4日 下午2:27:02
	 */
	public DataPage<TjPlan> pageTjPlanToHall(PageTjPlanToHallOption pageTjPlanToHallOption,
			DataPage<TjPlan> dataPage);
	/**
	 * 计算排序字段并修改到数据库
	 * @param tjPlan
	 */
	public void calculateSortNoAndUpdateToDb(TjPlan tjPlan);
	
	public int updateTjPlanForCheckEfect(TjPlan tjPlan);
	
	/**
	 * 更新方案赛事状态
	 * @param planId
	 * @return
	 */
	public boolean updateRaceStatus(Long planId, Integer raceStatus);
	
	/**
	 * 查询一段时间所有方案
	 * @param memberId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TjPlan> queryTjPlanByTime(Long memberId,Calendar startTime,Calendar endTime) ;
	
	/**
	 * 分页查询我发布的推荐
	 * @param memberId
	 * @param pageMyTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<TjPlan> pageMyPublishPlan(Long memberId,PageMyTjPlanOption pageMyTjPlanOption,DataPage<TjPlan> dataPage);
	
	public GenericThree<BigDecimal,Integer,String> countWinRatioAndOpenedPlanCountByTime(Calendar startTime,
			Calendar endTime, Long memberId);
	
	public BigDecimal countWinRatioByTime(Calendar startTime,Calendar endTime, Long memberId) ;
	/**
	 * 分页查询我查看过的推荐
	 * @param memberId
	 * @param pageLookedTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageMyLookedTjplanResult> pageMyLookedPlan(Long memberId,PageLookedTjPlanOption pageLookedTjPlanOption,DataPage<PageMyLookedTjplanResult> dataPage);
	
	/**
	 * 分页查询我关注的专家发布的推荐
	 * @param memberId
	 * 用户id
	 * @param pageMyTjPlanOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<TjPlan> pageMyAttentionExpertPublishPlan(Long memberId,PageMyAttentionExpertTjPlanOption pageMyAttentionExpertTjPlanOption,DataPage<TjPlan> dataPage);
	
	/**
	 * 批量修改方案赛事状态
	 * @param planIdList
	 * @param tjRaceStatus
	 */
	public void updatePlanListRaceStatus(List<Long> planIdList,TjRaceStatus tjRaceStatus);
	
	/**
	 * 通过方案编号列表查询方案
	 * @param planNoList
	 * @return
	 */
	public List<TjPlan> queryTjPlanByPlanNoList(List<String> planNoList);
	
	/**
	 * 后台分页查询方案
	 * @param pageAdminPlanOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageAdminPlanVo> adminPagePlan(PageAdminPlanOption pageAdminPlanOption ,DataPage<PageAdminPlanVo> dataPage);

	/**
	 * 前端首页，自动推荐查询
	 * @param rule
	 * 1：查看人数降序，前N条未开赛的数据
	 * 2：发布数据降序，前N条未开赛的数据
	 * @param size
	 * 数据条数
	 * @return
	 */
	public List<TjPlan> queryTjplanToIndexAutoRecommend(Integer rule,Integer size);
	/**
	 * 用来修改用户升级降级，方案不显示
	 * @param memberId
	 * @param level
	 * @param raceStatus
	 * @return
	 */
	public int updateTjPlanNoShowByMemberId(Long memberId,Integer level,Integer raceStatus);

}
 