package com.aicai.jcob.tjplan.service.impl;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.constant.TjOrderPayStatus;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanShowStatus;
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
import com.aicai.jcob.tjplan.common.service.TjPlanWriteService;
import com.aicai.jcob.tjplan.manager.TjLookLogManager;
import com.aicai.jcob.tjplan.manager.TjOrderManager;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.manager.TjRaceManager;
import com.alibaba.fastjson.JSON;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 上午11:21:49 
 * 程序的简单说明 
 */
public class TjPlanWriteServiceImpl implements TjPlanWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("tjPlanManagerImpl")
	private TjPlanManager tjPlanManager ;
	@Autowired
	private TjLookLogManager tjLookLogManager;
	@Autowired
	private TjExpertInfoManager tjExpertInfoManager;
	@Autowired
	private TjRaceManager tjRaceManager;
	@Autowired
	private TjPlanItemManager tjPlanItemManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private TjOrderManager tjOrderManager;
	@Autowired
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	
	@Override
	public ModelResult<TjPlan> publishTjPlan(Member member, TjPlan plan,
			List<TjPlanItem> planItemList, MemberOperOption operParam) {
		return tjPlanManager.publishTjPlan(member, plan, planItemList, operParam);
	}

	@Override
	public ModelResult<TjPlanVo> lookPlan(Member member, String planNo,
			MemberOperOption operParam) {
		ModelResult<TjPlanVo> modelResult = tjPlanManager.lookPlan(member, planNo, false,operParam);
		if (modelResult.isSuccess()) {
			TjPlanVo tjPlanVo = modelResult.getModel();
			//填充数据
			 TjExpertInfo tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlanVo.getTjPlan().getMemberId());
			 tjPlanVo.setTjExpertInfo(tjExpertInfo);
		     String[] raceIdStrList = tjPlanVo.getTjPlan().getRaceIdList().split(",");
		     List<Long> raceIdList = new ArrayList<Long>();
		     for (String raceIdStr : raceIdStrList) {
		    	raceIdList.add(Long.valueOf(raceIdStr));
			 }
			 List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
			 tjPlanVo.setTjRaceList(tjRaceList);
			 Member memberExpert = memberManager.queryMemberById(tjExpertInfo.getMemberId()).getModel();
			 tjPlanVo.setMember(memberExpert);
			 modelResult.setModel(tjPlanVo);
		}
		return modelResult;
	}
	
	

	@Override
	public ModelResult<TjPlanVo> payLook(Member member, String planNo,
			MemberOperOption operParam) {
		ModelResult<TjPlanVo> modelResult = tjPlanManager.lookPlan(member, planNo,true,operParam);
		if (modelResult.isSuccess()) {
			TjPlanVo tjPlanVo = modelResult.getModel();
			//填充数据
			 TjExpertInfo tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlanVo.getTjPlan().getMemberId());
			 tjPlanVo.setTjExpertInfo(tjExpertInfo);
		     String[] raceIdStrList = tjPlanVo.getTjPlan().getRaceIdList().split(",");
		     List<Long> raceIdList = new ArrayList<Long>();
		     for (String raceIdStr : raceIdStrList) {
		    	raceIdList.add(Long.valueOf(raceIdStr));
			 }
			 List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
			 tjPlanVo.setTjRaceList(tjRaceList);
			 Member memberExpert = memberManager.queryMemberById(tjExpertInfo.getMemberId()).getModel();
			 tjPlanVo.setMember(memberExpert);
			 modelResult.setModel(tjPlanVo);
		}
		return modelResult;
	}

	@Override
	public PageResult<PageTjplanToHallResult> pageTjPlanToHall(PageTjPlanToHallOption pageTjPlanToHallOption,
			DataPage<TjPlan> dataPage) {
		PageResult<PageTjplanToHallResult> pageResult = new PageResult<PageTjplanToHallResult>();
		if (pageTjPlanToHallOption == null || dataPage == null) {
			return pageResult.withError("param.not.null","pageTjPlanToHallOption/dataPage参数不能为null");
		}
		List<Long> lookedPlanIdList = null;
		if (pageTjPlanToHallOption.getMemberId() != null) {
			//表示排序要按已查看未查看
			lookedPlanIdList = tjLookLogManager.queryPlanIdByMemberId(pageTjPlanToHallOption.getMemberId());
			pageTjPlanToHallOption.setPlanIdList(lookedPlanIdList);
		}
		DataPage<TjPlan> dataPageFromDb = tjPlanManager.pageTjPlanToHall(pageTjPlanToHallOption, dataPage);
		if (dataPageFromDb == null) {
			dataPageFromDb = dataPage;
		}
		pageResult.setPage(setParamWithPageTjplanToHallResult(dataPageFromDb, lookedPlanIdList));
		return pageResult;
	}

	private DataPage<PageTjplanToHallResult> setParamWithPageTjplanToHallResult(DataPage<TjPlan> dataPage , List<Long> lookedPlanIdList){
		DataPage<PageTjplanToHallResult> pageResult = new DataPage<PageTjplanToHallResult>();
		try {
			BeanUtils.copyProperties(pageResult, dataPage);
		} catch (Exception e) {}
		if (dataPage == null || dataPage.getDataList() == null || dataPage.getDataList().size() == 0) {
			return pageResult;
		}
		List<PageTjplanToHallResult> resultList = new ArrayList<PageTjplanToHallResult>();
		PageTjplanToHallResult pageTjplanToHallResult = null;
		TjExpertInfo tjExpertInfo = null;
		if (dataPage != null && !CollectionUtils.isEmpty(dataPage.getDataList())) {
			for (TjPlan tjPlan : dataPage.getDataList()) {
				pageTjplanToHallResult = new PageTjplanToHallResult();
				pageTjplanToHallResult.setTjPlan(tjPlan);
			    tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
			    pageTjplanToHallResult.setTjExpertInfo(tjExpertInfo);
			    String[] raceIdStrList = tjPlan.getRaceIdList().split(",");
			    List<Long> raceIdList = new ArrayList<Long>();
			    for (String raceIdStr : raceIdStrList) {
			    	raceIdList.add(Long.valueOf(raceIdStr));
				}
			   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
			   pageTjplanToHallResult.setTjRaceList(tjRaceList);
			   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
			   pageTjplanToHallResult.setTjPlanItemList(tjPlanItemList);
			   Member member = memberManager.queryMemberById(tjPlan.getMemberId()).getModel();
			   pageTjplanToHallResult.setMember(member);
			   if (lookedPlanIdList != null && lookedPlanIdList.contains(tjPlan.getId())) {
				   pageTjplanToHallResult.setLooked(true);
			   }
			   resultList.add(pageTjplanToHallResult);
			}
		}
		
		pageResult.setDataList(resultList);
		return pageResult;
	}
	
	@Override
	public PageResult<Long> queryIdListByMaxRaceId(Long maxRaceId, DataPage<Long> page){
		PageResult<Long> pageResult = new PageResult<Long>();
		pageResult.setPage(tjPlanManager.queryIdListByMaxRaceId(maxRaceId, page));
		return pageResult;
	}
	
	@Override
	public ModelResult<TjPlan> queryTjPlanById(Long planId) {
		return new ModelResult<TjPlan>(tjPlanManager.queryTjPlanById(planId));
	}

	@Override
	public void updatePlanListRaceStatus(List<Long> planIdList, TjRaceStatus tjRaceStatus) {
		tjPlanManager.updatePlanListRaceStatus(planIdList, tjRaceStatus);
	}
    
	@Override
	public PageResult<PageTjplanToHallResult> pageMyPublishPlan(Long memberId,PageMyTjPlanOption pageMyTjPlanOption, DataPage<TjPlan> dataPage) {
		PageResult<PageTjplanToHallResult> pageResult = new PageResult<PageTjplanToHallResult>();
		if (pageMyTjPlanOption == null || dataPage == null
				|| pageMyTjPlanOption.getStartDate() == null
				|| pageMyTjPlanOption.getEndDate() == null){
				return pageResult.withError("param.not.null","startDate/endDate/pageLookedTjPlanOption/dataPage参数不能为null");		
		}
		DataPage<TjPlan> dataPageFromDb = tjPlanManager.pageMyPublishPlan(memberId, pageMyTjPlanOption, dataPage);
		if (dataPageFromDb == null) {
			dataPageFromDb = dataPage;
		}
		List<Long> lookedPlanIdList = null;
		if (pageMyTjPlanOption.getLookMemberId() != null && pageMyTjPlanOption.getLookMemberId() > 0 && pageMyTjPlanOption.getLookMemberId() != memberId) {
			//表示排序要按已查看未查看
			lookedPlanIdList = tjLookLogManager.queryPlanIdByMemberId(pageMyTjPlanOption.getLookMemberId());
		}
		pageResult.setPage(setParamWithPageTjplanToHallResult(dataPageFromDb, lookedPlanIdList));
		return pageResult;
	}

	@Override
	public PageResult<PageMyLookedTjplanResult> pageMyLookedPlan(Long memberId,
			PageLookedTjPlanOption pageLookedTjPlanOption,DataPage<PageMyLookedTjplanResult> dataPage) {
		PageResult<PageMyLookedTjplanResult> pageResult = new PageResult<PageMyLookedTjplanResult>();
		if (pageLookedTjPlanOption == null || dataPage == null
				|| pageLookedTjPlanOption.getStartDate() == null
				|| pageLookedTjPlanOption.getEndDate() == null){
				return pageResult.withError("param.not.null","expertMemberIdList/startDate/endDate/pageLookedTjPlanOption/dataPage参数不能为null");		
			}
		DataPage<PageMyLookedTjplanResult> dataPageFromDb = tjPlanManager.pageMyLookedPlan(memberId, pageLookedTjPlanOption, dataPage);
		if (dataPageFromDb == null) {
			dataPageFromDb = dataPage;
		}
		DataPage<PageMyLookedTjplanResult> dataPageResult = new DataPage<PageMyLookedTjplanResult>();
		try {
			BeanUtils.copyProperties(dataPageResult, dataPage);
		} catch (Exception e) {}
		List<PageMyLookedTjplanResult> resultList = new ArrayList<PageMyLookedTjplanResult>();
		TjExpertInfo tjExpertInfo = null;
		if (dataPageFromDb != null && !CollectionUtils.isEmpty(dataPageFromDb.getDataList())) {
			for (PageMyLookedTjplanResult pageMyLookedTjplanResult : dataPageFromDb.getDataList()) {
			    tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(pageMyLookedTjplanResult.getMemberId());
			    pageMyLookedTjplanResult.setTjExpertInfo(tjExpertInfo);
			    String[] raceIdStrList = pageMyLookedTjplanResult.getRaceIdList().split(",");
			    List<Long> raceIdList = new ArrayList<Long>();
			    for (String raceIdStr : raceIdStrList) {
			    	raceIdList.add(Long.valueOf(raceIdStr));
				}
			   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
			   pageMyLookedTjplanResult.setTjRaceList(tjRaceList);
			   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(pageMyLookedTjplanResult.getId());
			   pageMyLookedTjplanResult.setTjPlanItemList(tjPlanItemList);
			   Member member = memberManager.queryMemberById(pageMyLookedTjplanResult.getMemberId()).getModel();
			   pageMyLookedTjplanResult.setMember(member);
			   resultList.add(pageMyLookedTjplanResult);
			}
		}
		dataPageResult.setDataList(resultList);
		pageResult.setPage(dataPageResult);
		return pageResult;
	}

	@Override
	public PageResult<PageTjplanToHallResult> pageMyAttentionExpertPublishPlan(Long memberId,
			PageMyAttentionExpertTjPlanOption pageMyAttentionExpertTjPlanOption,DataPage<TjPlan> dataPage) {
		PageResult<PageTjplanToHallResult> pageResult = new PageResult<PageTjplanToHallResult>();
		if (pageMyAttentionExpertTjPlanOption == null || dataPage == null) {
			return pageResult.withError("param.not.null","pageMyAttentionExpertTjPlanOption/dataPage参数不能为null");
		}
		if (CollectionUtils.isEmpty(pageMyAttentionExpertTjPlanOption.getMyAttentionExpertMemberIdList()) 
			|| pageMyAttentionExpertTjPlanOption.getStartDate() == null
			|| pageMyAttentionExpertTjPlanOption.getEndDate() == null){
			return pageResult.withError("param.not.null","expertMemberIdList/startDate/endDate参数不能为null");		
		}
		DataPage<TjPlan> dataPageFromDb = tjPlanManager.pageMyAttentionExpertPublishPlan(memberId, pageMyAttentionExpertTjPlanOption, dataPage);
		if (dataPageFromDb == null) {
			dataPageFromDb = dataPage;
		}
		pageResult.setPage(setParamWithPageTjplanToHallResult(dataPageFromDb, null));
		return pageResult;
	}

	@Override
	public void cancleRace(List<Long> planIdList, MemberOperOption operOption) {
		if (CollectionUtils.isEmpty(planIdList)) {
			return;
		}
		logger.info("赛事取消退款，方案[{}]",JSON.toJSONString(planIdList));
		//处理方案的赛事状态为 赛事取消  批量update
		tjPlanManager.updatePlanListRaceStatus(planIdList, TjRaceStatus.cancel);
		//付费方案 对付费查看进行退款
		for (Long planId : planIdList) {
			//写到manager里去
			TjPlan tjPlan = tjPlanManager.queryTjPlanById(planId);
			if (tjPlan != null && tjPlan.getAmount().compareTo(BigDecimal.valueOf(0)) > 0) {
				//是付费方案要退款处理
				List<TjOrder> tjOrderList = tjOrderManager.queryOrderByPlanId(planId);
				if (CollectionUtils.isEmpty(tjOrderList)) {
					continue;
				}
				for (TjOrder tjOrder : tjOrderList) {
					ModelResult<Boolean> refundResult = memberBizDirverWalletManager.refundMemberLookPlan(tjPlan, tjOrder);
					if (refundResult.isSuccess()) {
						//退款成功，修改订单状态
						tjOrderManager.updateOrderPayStatus(tjOrder.getId(), TjOrderPayStatus.refund, tjOrder.getPayStatus());
					}
				}
			
			}
		}
	}

	@Override
	public ModelResult<Map<String, TjPlanVo>> queryIndexTjPlanList(List<String> planNoList) {
		ModelResult<Map<String, TjPlanVo>> modelResult = new ModelResult<Map<String,TjPlanVo>>();
		if (planNoList == null || planNoList.size() == 0) {
			return modelResult.withError("param.not.null","planNoList参数不能为null");
		}
		List<TjPlan> tjPlanList = tjPlanManager.queryTjPlanByPlanNoList(planNoList);
		if (tjPlanList == null) {
			tjPlanList = new ArrayList<TjPlan>();
		}
		Map<String, TjPlanVo> resultMap = new HashMap<String, TjPlanVo>();
		TjExpertInfo tjExpertInfo = null;
		TjPlanVo tjPlanVo = null;
		for (TjPlan tjPlan : tjPlanList) {
			if(tjPlan.getIsShow().intValue() == TjPlanShowStatus.not_show.getIndex()){
				continue;
			}
			tjPlanVo = new TjPlanVo();
			tjPlanVo.setTjPlan(tjPlan);
		    tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
		    tjPlanVo.setTjExpertInfo(tjExpertInfo);
		    String[] raceIdStrList = tjPlan.getRaceIdList().split(",");
		    List<Long> raceIdList = new ArrayList<Long>();
		    for (String raceIdStr : raceIdStrList) {
		    	raceIdList.add(Long.valueOf(raceIdStr));
			}
		   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
		   tjPlanVo.setTjRaceList(tjRaceList);
		   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
		   tjPlanVo.setTjPlanItemList(tjPlanItemList);
		   Member member = memberManager.queryMemberById(tjPlan.getMemberId()).getModel();
		   tjPlanVo.setMember(member);
		   resultMap.put(tjPlan.getTjPlanNo(), tjPlanVo);
		}
		modelResult.setModel(resultMap);
		return modelResult;
	}

	@Override
	public PageResult<PageAdminLookLogVo> adminPageLookLog(PageAdminLookLogOption pageAdminLookLogOption,
			DataPage<PageAdminLookLogVo> dataPage) {
		PageResult<PageAdminLookLogVo> pageResult = new PageResult<PageAdminLookLogVo>();
		try {
			if (dataPage == null) {
				return pageResult.withError("param.not.null","参数不能为null");
			}
			if (pageAdminLookLogOption == null) {
				pageAdminLookLogOption = new PageAdminLookLogOption();
			}
			DataPage<PageAdminLookLogVo> retDataPage = tjLookLogManager.adminPageLookLog(pageAdminLookLogOption, dataPage);
			if (retDataPage != null && !CollectionUtils.isEmpty(retDataPage.getDataList())) {
				//数据组装
				for (PageAdminLookLogVo lookLogVo : retDataPage.getDataList()) {
					Member lookerMember = memberManager.queryMemberById(lookLogVo.getLookerMemberId()).getModel();
					lookLogVo.setLookerNickName(lookerMember.getNickName());
					Member expertMember = memberManager.queryMemberById(lookLogVo.getExpertMemberId()).getModel();
					lookLogVo.setExpertNickName(expertMember.getNickName());
			    	String planNo = DateUtil.dateToString(lookLogVo.getPlanCreateTime().getTime(),"yyyyMMdd")+lookLogVo.getPlanId();
			    	lookLogVo.setPlanNo(planNo);
				}
			}
			pageResult.setPage(retDataPage);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.adminPageLookLog异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<AdminLookPlanVo> adminLookPlanDetail(Long lookLogId) {
		ModelResult<AdminLookPlanVo>  modelResult = new ModelResult<AdminLookPlanVo>();
		try {
			if (lookLogId == null) {
				return modelResult.withError("param.not.null","参数不能为null");
			}
			TjLookLog lookLog = tjLookLogManager.queryLookLogById(lookLogId);
			if (lookLog == null ) {
				return modelResult;
			}
			TjPlan tjPlan = tjPlanManager.queryTjPlanById(lookLog.getPlanId());
			AdminLookPlanVo adminLookPlanVo = new AdminLookPlanVo();
			Member  lookerMember = memberManager.queryMemberById(lookLog.getMemberId()).getModel();
			Member expertMember = memberManager.queryMemberById(tjPlan.getMemberId()).getModel();
			TjExpertInfo lookerExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(lookLog.getMemberId());
			TjExpertInfo expertTjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
		    String[] raceIdStrList = tjPlan.getRaceIdList().split(",");
		    List<Long> raceIdList = new ArrayList<Long>();
		    for (String raceIdStr : raceIdStrList) {
		    	raceIdList.add(Long.valueOf(raceIdStr));
			}
		   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
		   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
			
			adminLookPlanVo.setLookerMember(lookerMember);
			adminLookPlanVo.setExpertMember(expertMember);
			adminLookPlanVo.setLookerTjExpertInfo(lookerExpertInfo);
			adminLookPlanVo.setExpertTjExpertInfo(expertTjExpertInfo);
			adminLookPlanVo.setPlanItemList(tjPlanItemList);
			adminLookPlanVo.setTjRaceList(tjRaceList);
			adminLookPlanVo.setTjPlan(tjPlan);
			adminLookPlanVo.setTjLookLog(lookLog);
			modelResult.setModel(adminLookPlanVo);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.adminLookPlanDetail异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageResult<PageAdminPlanVo> adminPagePlan(PageAdminPlanOption pageAdminPlanOption,
			DataPage<PageAdminPlanVo> dataPage) {
		PageResult<PageAdminPlanVo> pageResult = new PageResult<PageAdminPlanVo>();
		try {
			if (dataPage == null) {
				return pageResult.withError("param.not.null","参数不能为null");
			}
			if (pageAdminPlanOption == null) {
				pageAdminPlanOption = new PageAdminPlanOption();
			}
			DataPage<PageAdminPlanVo> retDataPage = tjPlanManager.adminPagePlan(pageAdminPlanOption, dataPage);
			if (retDataPage != null && !CollectionUtils.isEmpty(retDataPage.getDataList())) {
				//数据组装
				for (PageAdminPlanVo planVo : retDataPage.getDataList()) {
					Member expertMember = memberManager.queryMemberById(planVo.getExpertMemberId()).getModel();
					planVo.setExpertNickName(expertMember.getNickName());
				}
			}
			pageResult.setPage(retDataPage);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.adminPagePlan异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<AdminLookPlanVo> adminPlanDetail(Long planId) {
		ModelResult<AdminLookPlanVo>  modelResult = new ModelResult<AdminLookPlanVo>();
		try {
			if (planId == null) {
				return modelResult.withError("param.not.null","参数不能为null");
			}
			
			TjPlan tjPlan = tjPlanManager.queryTjPlanById(planId);
			if (tjPlan == null) {
				return modelResult;
			}
			AdminLookPlanVo adminLookPlanVo = new AdminLookPlanVo();
			Member expertMember = memberManager.queryMemberById(tjPlan.getMemberId()).getModel();
			TjExpertInfo expertTjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
		    String[] raceIdStrList = tjPlan.getRaceIdList().split(",");
		    List<Long> raceIdList = new ArrayList<Long>();
		    for (String raceIdStr : raceIdStrList) {
		    	raceIdList.add(Long.valueOf(raceIdStr));
			}
		   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
		   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
			
			adminLookPlanVo.setExpertMember(expertMember);
			adminLookPlanVo.setExpertTjExpertInfo(expertTjExpertInfo);
			adminLookPlanVo.setPlanItemList(tjPlanItemList);
			adminLookPlanVo.setTjRaceList(tjRaceList);
			adminLookPlanVo.setTjPlan(tjPlan);
			modelResult.setModel(adminLookPlanVo);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.adminPlanDetail异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<List<TjPlanVo>> queryTjplanToIndexAutoRecommend(Integer rule, Integer size) {
		ModelResult<List<TjPlanVo>> modelResult = new ModelResult<List<TjPlanVo>>();
		if (rule == null) {
			rule = 1;
		}
		if (size == null) {
			size = 10;
		}
		try {
			List<TjPlan> planList = tjPlanManager.queryTjplanToIndexAutoRecommend(rule, size);
			List<TjPlanVo> tjPlanVoList = new ArrayList<TjPlanVo>();
			if (!CollectionUtils.isEmpty(planList)) {
				for (TjPlan tjPlan : planList) {
					if(tjPlan.getIsShow().intValue() == TjPlanShowStatus.not_show.getIndex() ){
						continue;
					}
					TjPlanVo tjPlanVo = new TjPlanVo();
					//填充数据
					tjPlanVo.setTjPlan(tjPlan);
				    TjExpertInfo tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
				    tjPlanVo.setTjExpertInfo(tjExpertInfo);
				    String[] raceIdStrList = tjPlan.getRaceIdList().split(",");
				    List<Long> raceIdList = new ArrayList<Long>();
				    for (String raceIdStr : raceIdStrList) {
				    	raceIdList.add(Long.valueOf(raceIdStr));
					}
				   List<TjRace> tjRaceList = tjRaceManager.queryRaceByIdList(raceIdList);
				   tjPlanVo.setTjRaceList(tjRaceList);
				   List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(tjPlan.getId());
				   tjPlanVo.setTjPlanItemList(tjPlanItemList);
				   Member member = memberManager.queryMemberById(tjPlan.getMemberId()).getModel();
				   tjPlanVo.setMember(member);
				   tjPlanVoList.add(tjPlanVo);
				}
			}
			modelResult.setModel(tjPlanVoList);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.queryTjplanToIndexAutoRecommend异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageResult<LookerVo> pagePlanLooker(Long planId,DataPage<TjLookLog> dataPage) {
		PageResult<LookerVo> pageResult = new PageResult<LookerVo>();
		try {
			if (dataPage == null || planId == null) {
				return pageResult.withError("param.not.null","参数不能为null");
			}
			DataPage<TjLookLog> retDataPage = tjLookLogManager.pageLookLogByPlanId(planId, dataPage);
			List<LookerVo> lookerVoList = new ArrayList<LookerVo>();
			if (retDataPage != null && !CollectionUtils.isEmpty(retDataPage.getDataList())) {
				//数据组装
				for (TjLookLog lookLog : retDataPage.getDataList()) {
					LookerVo lookerVo = new LookerVo();
					Member member = memberManager.queryMemberById(lookLog.getMemberId()).getModel();
					TjExpertInfo tjExpertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(lookLog.getMemberId());
					lookerVo.setMemberId(lookLog.getMemberId());
					lookerVo.setNickName(member.getNickName());
					lookerVo.setPhone(member.getPhone());
					lookerVo.setIcon(member.getIcon());
					lookerVo.setLevel(tjExpertInfo.getLevel());
					lookerVo.setLookedTime(lookLog.getCreateTime());
					lookerVoList.add(lookerVo);
				}
			}
			DataPage<LookerVo> lookerDataPage = new DataPage<LookerVo>();
			lookerDataPage.setPageSize(retDataPage.getPageSize());
			lookerDataPage.setPageNo(retDataPage.getPageNo());
			lookerDataPage.setTotalCount(retDataPage.getTotalCount());
			lookerDataPage.setDataList(lookerVoList);
			pageResult.setPage(lookerDataPage);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.pagePlanLooker异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}
	
	
	public ModelResult<Boolean> isPublished(Long memberId,Long raceId,Integer gameId){
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(false);
		try {
			TjPlanItem tjPlanItem = tjPlanItemManager.queryPlanItemByMemberIdAndRaceId(memberId, raceId, gameId);
			if (tjPlanItem != null) {
				modelResult.setModel(true);
			}
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.isPublished异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<BigDecimal> adminSumPageLookLogAmount(PageAdminLookLogOption pageAdminLookLogOption) {
		ModelResult<BigDecimal> modelResult = new ModelResult<BigDecimal>();
		try {
			BigDecimal retAmount = tjLookLogManager.adminSumPageLookLogAmount(pageAdminLookLogOption);
			modelResult.setModel(retAmount);
		} catch (Exception e) {
			logger.error("接口TjPlanWriteServiceImpl.adminSumPageLookLogAmount异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}
}
 