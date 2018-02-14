package com.aicai.jcob.tjexpert.service;  

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertWinRatioQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertAdminWinRatioResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertRecommendResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertWinRatioResult;
import com.aicai.jcob.tjexpert.common.service.TjExpertInfoWriteService;
import com.aicai.jcob.tjexpert.manager.TjAttentionExpertManager;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.manager.TjPlanManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午3:22:48 
 * 程序的简单说明 
 */
public class TjExpertInfoWriteServiceImpl implements TjExpertInfoWriteService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager ;
	@Autowired
	private MemberManager memberManager ;
	@Autowired
	private TjAttentionExpertManager tjAttentionExpertManager ;
	
	@Autowired
	@Qualifier("tjPlanManagerImpl")
	private TjPlanManager tjPlanManager ;
	
	@Override 
	public ModelResult<TjExpertInfo> queryTjExpertInfoById(Long memberId) {
		TjExpertInfo tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(memberId);
		return new ModelResult<TjExpertInfo>(tjExpertInfo);
	}


	@Override
	public ModelResult<Boolean> updateExpertDesc(Long memberId,
			String description) {
		ModelResult<Boolean> result = new ModelResult<Boolean>(false);
		TjExpertInfo tjExpertInfo = new TjExpertInfo();
		tjExpertInfo.setMemberId(memberId);
		tjExpertInfo.setDescription(description);
		int update = tjExpertInfoManager.updateExpertInfo(tjExpertInfo);
		if(update>0){
			result.setModel(true);
		}
		return result;
	}


	

	@Override
	public PageResult<TjExpertInfoAdminResult> pageTjExpertInfo(DataPage<TjExpertInfo> page,
			TjExpertInfoQueryOption tjExpertInfoQueryOption) {
		PageResult<TjExpertInfoAdminResult> pageResult = new PageResult<TjExpertInfoAdminResult>() ;
		pageResult.setPage(tjExpertInfoManager.pageTjExpertInfo(page, tjExpertInfoQueryOption));
		return pageResult ;
	}


	@Override
	public PageResult<TjExpertInfoResult> pageTjExpertByLevel(Integer[] expertLevelArray,Long memberId,DataPage<TjExpertInfo> page) {
		PageResult<TjExpertInfoResult> pageResult = new PageResult<TjExpertInfoResult>() ;
		if(expertLevelArray==null||expertLevelArray.length==0){
			pageResult.withError("param.not.null","listAllTjExpert/expertLevelArray参数不能为null");
		}
		DataPage<TjExpertInfo> expertList = tjExpertInfoManager.pageExpertByLevel(expertLevelArray,page);
		pageResult.withPage(expertInfoToExpertInfoResult(expertList,memberId));
		return pageResult;
	}
	
	private DataPage<TjExpertInfoResult> expertInfoToExpertInfoResult(DataPage<TjExpertInfo> expertList,Long memberId){
		DataPage<TjExpertInfoResult> pageResult = new DataPage<TjExpertInfoResult>() ;
		if (expertList == null || expertList.getDataList() == null || expertList.getDataList().size() == 0) {
			return pageResult;
		}
		try {
			BeanUtils.copyProperties(pageResult, expertList);
		} catch (Exception e) {}
		List<TjExpertInfoResult> listExpertResult = new ArrayList<TjExpertInfoResult>() ;
		for(TjExpertInfo expertInfo:expertList.getDataList()){
			TjExpertInfoResult tjExpertInfoResult = new TjExpertInfoResult() ;
			ModelResult<Member> memberResult = memberManager.queryMemberById(expertInfo.getMemberId());
			if(!memberResult.isSuccess()){
				logger.info("TjExpertInfo-memberid[{}]对应的member用户不存在",expertInfo.getMemberId());
			}
			Member member = memberResult.getModel() ;
			tjExpertInfoResult.setExpertMemberId(expertInfo.getMemberId());
			tjExpertInfoResult.setIcon(member.getIcon());
			tjExpertInfoResult.setPhone(member.getPhone());
			tjExpertInfoResult.setNickName(member.getNickName());
			tjExpertInfoResult.setExpertDesc(expertInfo.getDescription());
			tjExpertInfoResult.setExpertLevel(expertInfo.getLevel());
			tjExpertInfoResult.setRecord(expertInfo.getRecord());
			tjExpertInfoResult.setAttentionSum(expertInfo.getAttentionMe());
			if(memberId!=null&&memberId!=0){
				if(tjAttentionExpertManager.isAttention(memberId, expertInfo.getMemberId())){
					tjExpertInfoResult.setAttention(true);
				}
			}
			
			listExpertResult.add(tjExpertInfoResult) ;
		}
		pageResult.setDataList(listExpertResult) ;
		return pageResult ;
		
	}
	
	@Override
	public  ModelResult<Boolean>  updateTjExpertInfoWinRatioByMemberId(Long memberId){
		ModelResult<Boolean> result = new ModelResult<>();
		try{
			tjExpertInfoManager.updateTjExpertInfoWinRatioByMemberId(memberId);
			//专家战绩发生变化,专家的方案的排序要发生变化
			DataPage<TjPlan> planPage = new DataPage<TjPlan>(1000);
			PageMyTjPlanOption pageMyTjPlanOption = new PageMyTjPlanOption();
			Calendar startDate = Calendar.getInstance();
			startDate.set(Calendar.DAY_OF_YEAR, -90);
			pageMyTjPlanOption.setStartDate(startDate);
			pageMyTjPlanOption.setEndDate(Calendar.getInstance());
			do{
				planPage =tjPlanManager.pageMyPublishPlan(memberId, pageMyTjPlanOption, planPage);
				planPage.setPageNo(planPage.getNextPage());
				if(planPage.getDataList() == null || planPage.getDataList().isEmpty()){
					break;
				}
				for(TjPlan plan:planPage.getDataList()){
					tjPlanManager.calculateSortNoAndUpdateToDb(plan);
				}
			}while(planPage.isHasNext());
			
			
		}catch(JcobServerException e){
			result.withError(e.getCode(), e.getMessage());
			return result;
		}
		result.withModelFromDb(true);
		return result;
	}
	
	@Override
	public PageResult<TjExpertAdminWinRatioResult> pageExpertWinRatio(DataPage<TjExpertAdminWinRatioResult> page,
			TjExpertWinRatioQueryOption queryOption) {
		PageResult<TjExpertAdminWinRatioResult> pageResult = new PageResult<TjExpertAdminWinRatioResult>();
		pageResult.setPage(tjExpertInfoManager.pageExpertWinRatio(page, queryOption));
		return pageResult;
	}


	@Override
	public ModelResult<Boolean> updateExpertLevelAndInsertLog(Long memberId,
			Integer newLevel, Integer oldLevel, Integer handType) {
		
		return tjExpertInfoManager.updateExpertLevelAndInsertLog(memberId, newLevel, oldLevel, handType);
		
	}


	@Override
	public ModelResult<Map<Long, TjExpertRecommendResult>> queryRecommendExpertByMemberId(
			List<Long> memberIdList) {	
		return tjExpertInfoManager.queryRecommendExpertByMemberId(memberIdList);
	}


	@Override
	public PageResult<TjExpertWinRatioResult> pageExpertWinRatioByRank(
			DataPage<TjExpertWinRatioResult> dataPage, Integer winRatioRankField,Integer leastCountPlan) {
		PageResult<TjExpertWinRatioResult> pageResult = new PageResult<TjExpertWinRatioResult>() ;
		pageResult.setPage(tjExpertInfoManager.pageExpertWinRatioByRank(dataPage, winRatioRankField,leastCountPlan));
		return pageResult;
	}

	@Override
	public PageResult<Long> queryExpertMemberId(DataPage<Long> dataPage) {
		PageResult<Long> pageResult = new PageResult<Long>();
		pageResult.setPage(tjExpertInfoManager.queryExpertMemberId(dataPage));
		return pageResult;
	}
}
 