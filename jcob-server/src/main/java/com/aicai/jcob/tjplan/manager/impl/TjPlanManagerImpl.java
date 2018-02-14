package com.aicai.jcob.tjplan.manager.impl;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.GenericThree;
import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.common.GlobalParam;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.game.handler.JjGameCheckHandler;
import com.aicai.jcob.game.handler.JjGameHandlerFactory;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.OperType;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjplan.common.domain.constant.TjOrderPayStatus;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanLeagueTypeBit;
import com.aicai.jcob.tjplan.common.domain.constant.TjPlanOpenStatus;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageLookedTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyAttentionExpertTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageTjPlanToHallOption;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.PageMyLookedTjplanResult;
import com.aicai.jcob.tjplan.common.domain.result.TjPlanVo;
import com.aicai.jcob.tjplan.domain.TjplanCheckResult;
import com.aicai.jcob.tjplan.manager.TjLookLogManager;
import com.aicai.jcob.tjplan.manager.TjOrderManager;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.alibaba.fastjson.JSON;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月29日 上午11:20:49 
 * 程序的简单说明 
 */
public class TjPlanManagerImpl  implements TjPlanManager {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao tjplanDbDao;
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	private TransactionTemplate transactionTemplateTjplan;
	@Autowired
	private TjPlanItemManager tjPlanItemManager;
	@Autowired
	private TjOrderManager tjOrderManager;
	@Autowired
	private TjLookLogManager tjLookLogManager;
	@Autowired
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	@Autowired
	private GlobalParam globalParam;
	@Autowired
	private JjGameHandlerFactory jjGameHandlerFactory;
	@Autowired
	private MemberOperLogManager memberOperLogManager;
	@Autowired
	private TjExpertInfoManager tjExpertInfoManager;
	/**
	 * 专家发布推荐方案
	 * @param member
	 * @param plan
	 * @param planItemList
	 * @param operParam
	 * @return
	 */
	public ModelResult<TjPlan> publishTjPlan(Member member,TjPlan plan,List<TjPlanItem> planItemList,MemberOperOption operParam){
		ModelResult<TjPlan> modelResult = new ModelResult<TjPlan>();
		//基础参数验证
		if (member == null || plan == null || planItemList == null || operParam == null) {
			return modelResult.withError("param.not.null","member/plan/planItemList/operParam参数不能为null");
		}
		if (plan.getDescribeToDb().length() > 1000) {
			return modelResult.withError("plan.describe.too.long","方案描述超出最大范围:1000");
		}
		for (TjPlanItem tjPlanItem : planItemList) {
			TjPlanItem checkItem = tjPlanItemManager.queryPlanItemByMemberIdAndRaceId(plan.getMemberId(), tjPlanItem.getRaceId(),tjPlanItem.getGameId());
			if (checkItem != null) {
				logger.info("用户[{}]重复发布赛事推荐raceId[{}]",plan.getMemberId(),checkItem.getRaceId());
				return modelResult.withError("race.publish.is.exist","同一场比赛只能发布1次推荐!");
			}
		}
		//验证内容，调用gamehandle
		JjGameCheckHandler checkHandler = jjGameHandlerFactory.getTaskHandle(plan.getGameId());
		logger.info("用户:[{}]发布推荐方案[{}]checkHandler[{}]",plan.getMemberId(),checkHandler,JSON.toJSONString(planItemList));
		if (checkHandler == null) {
			return modelResult.withError("not.find.gameCheckHandler","找不到验证方法!");
		}
		ModelResult<TjplanCheckResult> checkResult = checkHandler.checkPlanItem(planItemList);
		if (!checkResult.isSuccess()) {
			return modelResult.withError(checkResult.getErrorCode(),checkResult.getErrorMsg());
		}
		
		setAttriButeWithPlan(checkResult.getModel(), plan);
		List<TjPlanItem> checkPlanItemList = checkResult.getModel().getTjPlanItemList();
		try {
			//事物，创建方案，创建方案详情，提取信息，修改专家信息
			transactionTemplateTjplan.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					TjPlan returnPlan = saveTjPlanAndSetId(plan);
					for (TjPlanItem tjPlanItem : checkPlanItemList) {
						tjPlanItem.setPlanId(returnPlan.getId());
						tjPlanItemManager.saveTjplanItem(tjPlanItem);
					}
					//计算排序字段
					calculateSortNoAndUpdateToDb(returnPlan);
					modelResult.setModel(returnPlan);
				}
			});
		} catch (Exception e) {
			modelResult.withError("tjplan.publish.exeception","推荐方案发布异常!");
			String errMsg = String.format("memberId[%s],发布方案planitem[{}]异常：",member.getId(),JSON.toJSONString(planItemList));
			logger.error(errMsg,e);
		}
		operParam.setOperType(OperType.publish_plan.getIndex());
		memberOperLogManager.asyncSaveMemberOperLog(member.getId(), operParam, OperType.publish_plan.getDescription());
		return modelResult;
	}
	
	private void  setAttriButeWithPlan(TjplanCheckResult tjplanCheckResult ,TjPlan tjPlan){
		tjPlan.setMinRaceMatchTime(tjplanCheckResult.getMinMatchTime());
		tjPlan.setMinRaceId(tjplanCheckResult.getMinRaceId());
		tjPlan.setMaxRaceId(tjplanCheckResult.getMinRaceId());
		tjPlan.setRaceIdList(StringUtils.join(tjplanCheckResult.getRaceIdList(),","));
	}
	
	public ModelResult<TjPlanVo> lookPlan(Member member,String planNo,Boolean isPay,MemberOperOption operParam){
		ModelResult<TjPlanVo> modelResult = new ModelResult<TjPlanVo>();
		TjPlanVo tjPlanVo = new TjPlanVo();
		//基础参数验证
		if (member == null || StringUtils.isBlank(planNo) || operParam == null) {
			return modelResult.withError("param.not.null","member/planNo/operParam参数不能为null");
		}
		//查询方案，验证状态
		TjPlan planFromDb = queryTjPlanByPlanNo(planNo);
		if (planFromDb == null) {
			logger.info("用户[{}]查看方案[{}],方案不存在!",member.getId(),planNo);
			return modelResult.withError("plan.not.find","方案不存在!");
		}
		List<TjPlanItem> tjPlanItemList = tjPlanItemManager.queryTjPlanItemByPlanId(planFromDb.getId());
		tjPlanVo.setTjPlan(planFromDb);
		tjPlanVo.setTjPlanItemList(tjPlanItemList);
		modelResult.setModel(tjPlanVo);
		TjLookLog lookLog = tjLookLogManager.queryTjLookLogByMemberIdAndPlanId(member.getId(), planFromDb.getId());//查询数据库 
		if (lookLog != null) {
			tjPlanVo.setLookedTime(lookLog.getCreateTime());
			tjPlanVo.setIsLooked(true);
		}
		if (lookLog != null
				|| member.getId().longValue() == planFromDb.getMemberId().longValue() 
				|| planFromDb.getRaceStatus() == TjRaceStatus.matched.getIndex()
				||(planFromDb.getRaceStatus() == TjRaceStatus.matching.getIndex() && planFromDb.getAmount().compareTo(BigDecimal.valueOf(0)) <= 0)) {
//			表示已经看过了，或者是自己发的单，直接返回,比赛中免费的也直接返回
			return modelResult;
		}
		if (!isPay) {
			//没有支付行为，直接返回,一般是概览,不查看详情
			return modelResult;
		}
		//付费的不是未比赛的，不能查看
		if (planFromDb.getRaceStatus() == TjRaceStatus.matching.getIndex() && planFromDb.getAmount().compareTo(BigDecimal.valueOf(0)) > 0) {
			return modelResult.withError("plan.racestatus.not.allow.look","比赛中的推荐方案不允许查看!");
		}
		try {
			transactionTemplateTjplan.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//创建查看记录
					TjLookLog tjLookLog = new TjLookLog();
					tjLookLog.setMemberId(member.getId());
					tjLookLog.setAmount(planFromDb.getAmount());
					tjLookLog.setExpertLevel(planFromDb.getExpertLevel());
					tjLookLog.setGameId(planFromDb.getGameId());
					tjLookLog.setPlanId(planFromDb.getId());
					tjLookLog.setRaceType(planFromDb.getRaceType());
					tjLookLog.setClientType(operParam.getClientType());
					tjLookLog.setChannel(operParam.getChannel());
					tjLookLogManager.saveTjLookLogAndSetId(tjLookLog);
					//修改方案查看次数
					addPlanLookCount(planFromDb.getId());
					//付费方案创建order,钱包操作，修改订单支付状态
					if (planFromDb.getAmount().compareTo(BigDecimal.valueOf(0)) > 0 ) {
						TjOrder tjOrder = new TjOrder();
						tjOrder.setPlanId(planFromDb.getId());
						tjOrder.setAmount(planFromDb.getAmount());
						tjOrder.setCashTOHuoyanRatio(globalParam.getCashTOHuoyanRatio());
						tjOrder.setMemberId(member.getId());
						tjOrder.setPayStatus(TjOrderPayStatus.wait_pay);
						tjOrder.setClientType(operParam.getClientType());
						tjOrder.setChannel(operParam.getChannel());
						tjOrder = tjOrderManager.saveTjOrderAndSetId(tjOrder);
						ModelResult<Boolean> payResult = memberBizDirverWalletManager.memberLookPlan(planFromDb, tjOrder);
						if (payResult.getModel()) {
							tjOrderManager.updateOrderPayStatus(tjOrder.getId(), TjOrderPayStatus.payed, tjOrder.getPayStatus());
						}else {
							logger.error("用户:[{}]查看方案[{}],支付异常[{}]",member.getId(),planFromDb.getId(),payResult.getErrorMsg());
							throw new JcobServerException(payResult.getErrorCode(), payResult.getErrorMsg());
						}
					}
				}
			});
		} catch (Exception e) {
			modelResult.withError("look.plan.exeception","查看方案异常!");
			String errMsg = String.format("memberId[%s],查看方案[{}]异常：",member.getId(),planNo);
			logger.error(errMsg,e);
		}
		TjPlan tjPlan = queryTjPlanById(planFromDb.getId());
		calculateSortNoAndUpdateToDb(tjPlan);
		operParam.setOperType(OperType.look_plan.getIndex());
		memberOperLogManager.asyncSaveMemberOperLog(member.getId(), operParam, OperType.look_plan.getDescription());
		return modelResult;
	}
	/**
	 *按付费——免费排列
	 *付费和免费推荐按查看人数降序排列
	 *查看人数相同，按专家命中率降序排列
	 *命中率相同按发布时间降序排列
	 */
	public void calculateSortNoAndUpdateToDb(TjPlan tjPlan){
		StringBuffer sortNoBuffer = new StringBuffer();
		//付费，免费
		if (tjPlan.getAmount().compareTo(BigDecimal.valueOf(0)) > 0) {
			sortNoBuffer.append(11);
		}else {
			sortNoBuffer.append(10);
		}
		//查看人数  不足补0  7位
		sortNoBuffer.append(String.format("%07d", tjPlan.getLookerCount()));
		TjExpertInfo expertInfo = tjExpertInfoManager.queryExpertInfoByMemberId(tjPlan.getMemberId());
		if (expertInfo == null) {
			return;
		}
		//命中率
		BigDecimal ratio = expertInfo.getWinRatio()==null?BigDecimal.ZERO: expertInfo.getWinRatio();
		sortNoBuffer.append(String.format("%03d", ratio.multiply(BigDecimal.valueOf(100)).intValue()));
		//创建时间
		Calendar cutDate = DateUtil.parse("2016-02-06", "yyyy-MM-dd");
		long cutTimeInMillis = cutDate.getTimeInMillis();
		long createTimeInMillis = tjPlan.getCreateTime().getTimeInMillis();
		long subtractTimeInMillis = createTimeInMillis - cutTimeInMillis;
		sortNoBuffer.append(String.format("%014d", subtractTimeInMillis));
		BigDecimal sortNoLong = new BigDecimal(sortNoBuffer.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sortNo", sortNoLong);
		paramMap.put("id", tjPlan.getId());
		tjplanDbDao.update("TjPlanMapper.updateSortNoByPlanId", paramMap);
	}

	@Override
	public TjPlan saveTjPlanAndSetId(TjPlan tjPlan) {
		tjplanDbDao.insertAndSetupId("TjPlanMapper.saveTjPlan", tjPlan);
		return tjPlan;
	}

	@Override
	public TjPlan queryTjPlanById(Long planId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planId);
		return (TjPlan) tjplanDbDao.queryObject("TjPlanMapper.queryTjPlanById", paramMap);
	}

	@Override
	public DataPage<Long> queryIdListByMaxRaceId(Long maxRaceId, DataPage<Long> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("maxRaceId", maxRaceId);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return tjplanDbDao.queryPage("TjPlanMapper.queryIdListByMaxRaceIdCount", "TjPlanMapper.queryIdListByMaxRaceId", map, page);
	}
	
	@Override
	public TjPlan queryTjPlanByPlanNo(String planNo) {
		return queryTjPlanById(Long.valueOf(StringUtils.substring(planNo, 8)));
	}
	
	public int addPlanLookCount(Long planId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planId);
		return tjplanDbDao.update("TjPlanMapper.addPlanLookCount", paramMap);
	}
	@Override
	public TjPlan selectFirstPlanByMemberIdStartTime(Long memberId,Calendar startTime){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		params.put("startTime", startTime);
		List<TjPlan> tjPlanList = tjplanDbDao.<TjPlan>queryList("TjPlanMapper.selectFirstPlanByMemberIdStartTime", params);
		if(tjPlanList.isEmpty()){
			return null;
		}
		return tjPlanList.get(0);
	}
	@Override
	public ModelResult<TjPlan> selectFirstPlanByMemberId(Long memberId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		List<TjPlan> tjPlanList = tjplanDbDao.<TjPlan>queryList("TjPlanMapper.selectFirstPlanByMemberId", params);
		if(tjPlanList==null||tjPlanList.size()==0){
			return new ModelResult<TjPlan>();
		}
		return new ModelResult<TjPlan>(tjPlanList.get(0));
	}

	
	@Override
	public DataPage<TjPlan> pageTjPlanToHall(
			PageTjPlanToHallOption pageTjPlanToHallOption,DataPage<TjPlan> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("raceStatus", pageTjPlanToHallOption.getTjRaceStatus());
		if (pageTjPlanToHallOption.getGameTypeList() != null && pageTjPlanToHallOption.getGameTypeList().size() > 0) {
			paramMap.put("gameTypeList", pageTjPlanToHallOption.getGameTypeList());
		}
		if (pageTjPlanToHallOption.getTjExpertLevelList() != null && pageTjPlanToHallOption.getTjExpertLevelList().size() >0) {
			paramMap.put("tjExpertLevelList", pageTjPlanToHallOption.getTjExpertLevelList());
		}
		if (pageTjPlanToHallOption.getPlanIdList() != null && pageTjPlanToHallOption.getPlanIdList().size() >0) {
			paramMap.put("planIdList", StringUtils.join(pageTjPlanToHallOption.getPlanIdList(),","));
		}
		if (TjPlanLeagueTypeBit.leagueTypeToLeagueTypeBit(pageTjPlanToHallOption.getLeagueTypeList()) != null && TjPlanLeagueTypeBit.leagueTypeToLeagueTypeBit(pageTjPlanToHallOption.getLeagueTypeList()).size() > 0) {
			paramMap.put("leagueTypeBitList", TjPlanLeagueTypeBit.leagueTypeToLeagueTypeBit(pageTjPlanToHallOption.getLeagueTypeList()));
		}
		return tjplanDbDao.queryPage("TjPlanMapper.countTjPlanToHall", "TjPlanMapper.pageTjPlanToHall", paramMap, dataPage);
	}

	@Override
	public int updateTjPlanForCheckEfect(TjPlan tjPlan) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("raceStatus", tjPlan.getRaceStatus());
		paramMap.put("openStatus", tjPlan.getOpenStatus());
		paramMap.put("winRaceCount", tjPlan.getWinRaceCount());
		paramMap.put("id", tjPlan.getId());
		return tjplanDbDao.update("TjPlanMapper.updateTjPlanForCheckEfect", paramMap);
	}

	@Override
	public boolean updateRaceStatus(Long planId, Integer raceStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", planId);
		paramMap.put("raceStatus", raceStatus);
		return tjplanDbDao.update("TjPlanMapper.updateRaceStatus", paramMap) > 0;
	}


	@Override
	public List<TjPlan> queryTjPlanByTime(Long memberId, Calendar startTime,
			Calendar endTime) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		paramMap.put("startTime", startTime.getTime());
		paramMap.put("endTime", endTime.getTime());
		return tjplanDbDao.queryList("TjPlanMapper.queryTjPlanByTime", paramMap) ;
	}

	@Override
	public DataPage<TjPlan> pageMyPublishPlan(Long memberId,PageMyTjPlanOption pageMyTjPlanOption, DataPage<TjPlan> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (pageMyTjPlanOption.getTjRaceStatus() != null) {
			paramMap.put("raceStatus", pageMyTjPlanOption.getTjRaceStatus());
		}
		if (pageMyTjPlanOption.getIsHit() != null) {
			paramMap.put("isHit", pageMyTjPlanOption.getIsHit());
		}
		paramMap.put("memberId", memberId);
		paramMap.put("startDate", pageMyTjPlanOption.getStartDate());
		paramMap.put("endDate", pageMyTjPlanOption.getEndDate());
		return tjplanDbDao.queryPage("TjPlanMapper.countMyPublishPlan", "TjPlanMapper.pageMyPublishPlan", paramMap, dataPage);
	}
	@Override
	public GenericThree<BigDecimal,Integer,String> countWinRatioAndOpenedPlanCountByTime(Calendar startTime,
			Calendar endTime, Long memberId) {
		List<TjPlan> tjPlanList = this.queryTjPlanByTime(memberId, startTime, endTime) ;
		int openPlanCount =0;
		BigDecimal winRatio = BigDecimal.ZERO;
		String recod = "";
		if(tjPlanList==null || tjPlanList.size()==0){
			return new GenericThree<BigDecimal,Integer,String>(winRatio,openPlanCount,recod);
		}
		
		Double totalWinCount = Double.valueOf(0) ;
		for(TjPlan tjPlan:tjPlanList){
			if(tjPlan.getRaceStatus() != TjRaceStatus.cancel.getIndex() && tjPlan.getOpenStatus() == TjPlanOpenStatus.opened ){
				openPlanCount += 1;
				totalWinCount+=tjPlan.getWinRaceCount() ;
				if(tjPlan.getWinRaceCount()>0){
					recod = recod +"1";
				}else{
					recod = recod +"0";
				}
			}
		}
		if(openPlanCount == 0l){
			return new GenericThree<BigDecimal,Integer,String>(winRatio,openPlanCount,recod);
		}
		//recod = recod.substring( recod.length()-7<0?0: recod.length()-7, recod.length());
		recod  = recod.substring(0, recod.length()-7<0?recod.length():7) ;
		winRatio = new BigDecimal(totalWinCount/openPlanCount);
		winRatio = winRatio.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return new GenericThree<BigDecimal,Integer,String>(winRatio,openPlanCount,recod);
	}
	
	
	@Override
	public DataPage<PageMyLookedTjplanResult> pageMyLookedPlan(Long memberId,
			PageLookedTjPlanOption pageLookedTjPlanOption,DataPage<PageMyLookedTjplanResult> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (pageLookedTjPlanOption.getTjRaceStatus() != null) {
			paramMap.put("raceStatus", pageLookedTjPlanOption.getTjRaceStatus());
		}
		if (pageLookedTjPlanOption.getIsHit() != null) {
			paramMap.put("isHit", pageLookedTjPlanOption.getIsHit());
		}
		paramMap.put("memberId", memberId);
		paramMap.put("startDate", pageLookedTjPlanOption.getStartDate());
		paramMap.put("endDate", pageLookedTjPlanOption.getEndDate());
		return tjplanDbDao.queryPage("TjPlanMapper.countMyLookedPlan", "TjPlanMapper.pageMyLookedPlan", paramMap, dataPage);
	
	}

	@Override
	public DataPage<TjPlan> pageMyAttentionExpertPublishPlan(Long memberId,
			PageMyAttentionExpertTjPlanOption pageMyAttentionExpertTjPlanOption,DataPage<TjPlan> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("expertMemberIdList", pageMyAttentionExpertTjPlanOption.getMyAttentionExpertMemberIdList());
		paramMap.put("startDate", pageMyAttentionExpertTjPlanOption.getStartDate());
		paramMap.put("endDate", pageMyAttentionExpertTjPlanOption.getEndDate());
		return tjplanDbDao.queryPage("TjPlanMapper.countMyAttentionExpertPublishPlan", "TjPlanMapper.pageMyAttentionExpertPublishPlan", paramMap, dataPage);
	
	}

	@Override
	public void updatePlanListRaceStatus(List<Long> planIdList,TjRaceStatus tjRaceStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("planIdList", planIdList);
		paramMap.put("raceStatus", tjRaceStatus.getIndex());
		paramMap.put("updateTime", Calendar.getInstance());
	    tjplanDbDao.update("TjPlanMapper.updatePlanListRaceStatus", paramMap);
	}

	@Override
	public List<TjPlan> queryTjPlanByPlanNoList(List<String> planNoList) {
		List<Long> planIdList = new ArrayList<Long>();
		for (String planNo : planNoList) {
			planIdList.add(Long.valueOf(StringUtils.substring(planNo, 8)));
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("planIdList", planIdList);
		return tjplanDbDao.queryList("TjPlanMapper.queryTjPlanByIdList",paramMap);
	}

	@Override
	public DataPage<PageAdminPlanVo> adminPagePlan(PageAdminPlanOption pageAdminPlanOption,
			DataPage<PageAdminPlanVo> dataPage) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startDate", pageAdminPlanOption.getStartDate());
		paraMap.put("endDate", pageAdminPlanOption.getEndDate());
		paraMap.put("expertLevelList", pageAdminPlanOption.getExpertLevelList());
		paraMap.put("leftAndEqualAmount", pageAdminPlanOption.getLeftAndEqualAmount());
		paraMap.put("rightAndEqualAmount", pageAdminPlanOption.getRightAndEqualAmount());
		paraMap.put("memberIdList", pageAdminPlanOption.getMemberIdList());
		paraMap.put("clientTypeList", pageAdminPlanOption.getClientTypeList());
		paraMap.put("raceStatuList", pageAdminPlanOption.getRaceStatuList());
		paraMap.put("winstatus", pageAdminPlanOption.getWinstatus());
		paraMap.put("raceTypeList", pageAdminPlanOption.getRaceTypeList());
		paraMap.put("gameTypeList", pageAdminPlanOption.getGameTypeList());
		if (StringUtils.isNotBlank(pageAdminPlanOption.getTjPlanNo())) {
			paraMap.put("planId", StringUtils.substring(pageAdminPlanOption.getTjPlanNo(), 8));
		}
		return tjplanDbDao.queryPage("TjPlanMapper.adminCountPlan", "TjPlanMapper.adminPagePlan", paraMap, dataPage);
	}

	@Override
	public List<TjPlan> queryTjplanToIndexAutoRecommend(Integer rule,Integer size) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		paramMap.put("startDate", startDate);
		paramMap.put("rule", rule);
		paramMap.put("size", size);
		return tjplanDbDao.queryList("TjPlanMapper.queryTjplanToIndexAutoRecommend",paramMap);
	}

	@Override
	public BigDecimal countWinRatioByTime(Calendar startTime, Calendar endTime,
			Long memberId) {
		List<TjPlan> tjPlanList = this.queryTjPlanByTime(memberId, startTime, endTime) ;
		int openPlanCount =0;
		BigDecimal winRatio = BigDecimal.ZERO;
		if(tjPlanList==null || tjPlanList.size()==0){
			return winRatio;
		}
		
		Double totalWinCount = Double.valueOf(0) ;
		for(TjPlan tjPlan:tjPlanList){
			if(tjPlan.getRaceStatus() != TjRaceStatus.cancel.getIndex() && tjPlan.getOpenStatus() == TjPlanOpenStatus.opened ){
				openPlanCount += 1;
				totalWinCount+=tjPlan.getWinRaceCount() ;
			}
		}
		if(openPlanCount == 0l){
			return winRatio;
		}
		
		winRatio = new BigDecimal(totalWinCount/openPlanCount);
		winRatio = winRatio.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return winRatio;
	}

	@Override
	public int updateTjPlanNoShowByMemberId(Long memberId, Integer level,
			Integer raceStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		paramMap.put("startDate", startDate);
		paramMap.put("memberId", memberId);
		paramMap.put("level", level);
		paramMap.put("raceStatus", raceStatus);
		paramMap.put("isShow", 0);
		return tjplanDbDao.update("TjPlanMapper.updateIsShowByMemberId",paramMap);
	}
	
}
 