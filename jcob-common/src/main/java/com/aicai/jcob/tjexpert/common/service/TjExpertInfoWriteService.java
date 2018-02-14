package com.aicai.jcob.tjexpert.common.service;  

import java.util.List;
import java.util.Map;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertWinRatioQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertAdminWinRatioResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertRecommendResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertWinRatioResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月25日 下午3:19:39 
 * 程序的简单说明 
 */
public interface TjExpertInfoWriteService {
	
	/**
	 * 后台专家成长信息分页查询
	 * @param page 
	 * @param tjExpertInfoQueryOption
	 * @return
	 */
	public PageResult<TjExpertInfoAdminResult> pageTjExpertInfo(DataPage<TjExpertInfo> page,TjExpertInfoQueryOption tjExpertInfoQueryOption);
	

	/**
	 * 根据专家id查询专家信息
	 * @param memberId
	 * @return
	 */
	public ModelResult<TjExpertInfo> queryTjExpertInfoById(Long memberId);

	/**
	 * 更新专家描述
	 * @param memberId
	 * @param description
	 * @return
	 */
	public ModelResult<Boolean> updateExpertDesc(Long memberId,String description);
	
	
	/**
	 *  根据专家等级,前台专家分页
	 * @param expertLevelArray
	 * @param memberId 可选
	 * @param page
	 * @return
	 */
	public PageResult<TjExpertInfoResult> pageTjExpertByLevel(Integer[] expertLevelArray,Long memberId,DataPage<TjExpertInfo> page) ;
	
	/**
	 * task 接收到消息用来刷新用户战绩
	 * 
	 * @param newOpenedTjPlan
	 * @return
	 */
	public  ModelResult<Boolean>  updateTjExpertInfoWinRatioByMemberId(Long memberId);
	
	/**
	 * 后台专家战绩查询分页
	 * @param page
	 * @param tjExpertInfoQueryOption
	 * @return
	 */
	public PageResult<TjExpertAdminWinRatioResult> pageExpertWinRatio(DataPage<TjExpertAdminWinRatioResult> page,TjExpertWinRatioQueryOption tjExpertInfoQueryOption);


	/**
	 * 后台操作,根据战绩情况对专家升降级
	 * @param memberId 
	 * @param newLevel
	 * @param oldLevel
	 * @param handType
	 * @return
	 */
	public ModelResult<Boolean> updateExpertLevelAndInsertLog(Long memberId,Integer newLevel,Integer oldLevel,Integer handType);
		
	/**
	 * 根据专家的memberId list获取专家具体信息
	 * @param memberIdList
	 * @return
	 */
	public ModelResult<Map<Long,TjExpertRecommendResult>> queryRecommendExpertByMemberId(List<Long> memberIdList);


	/**
	 * 根据排序字段,查询时间段内的实验室专家的命中率
	 * @param dataPage
	 * @param winRatioRankField 排序统计时间标识{@link com.aicai.jcob.tjexpert.common.domain.constant.WinRatioRankField}
	 * @param leastCountPlan 最少发布方案统计个数
	 * @return
	 */
	public PageResult<TjExpertWinRatioResult> pageExpertWinRatioByRank(DataPage<TjExpertWinRatioResult> dataPage,Integer winRatioRankField,Integer leastCountPlan);
	
	/**
	 * 查询所有专家memberId
	 * @param dataPage
	 * @return
	 */
	public PageResult<Long> queryExpertMemberId(DataPage<Long> dataPage);
}
 