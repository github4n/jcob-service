package com.aicai.jcob.tjexpert.manager.impl;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import com.aicai.appmodel.domain.GenericThree;
import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertApplyInfo;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertApplyCheckStatus;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertWinRatioQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertAdminWinRatioResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertRecommendResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertWinRatioResult;
import com.aicai.jcob.tjexpert.manager.TjAttentionExpertManager;
import com.aicai.jcob.tjexpert.manager.TjExpertApplyManager;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;
import com.aicai.jcob.tjexpert.manager.TjExpertSetInfoManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午3:18:30 
 * 程序的简单说明 
 */
public class TjExpertInfoManagerImpl implements TjExpertInfoManager {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao dao;
	
	@Autowired
	@Qualifier("tjPlanManagerImpl")
	private TjPlanManager tjPlanManager ;
	
	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager ;
	
	@Autowired
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	
	@Autowired
	private TjExpertApplyManager tjExpertApplyManager ;
	
	@Autowired
	@Qualifier("tjAttentionExpertManagerImpl")
	private TjAttentionExpertManager tjAttentionExpertManager ;
	
	@Autowired
	@Qualifier("tjExpertSetInfoManagerImpl")
	private TjExpertSetInfoManager tjExpertSetInfoManager ;
	
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	protected TransactionTemplate transactionTemplate;
	
	@Override
	public DataPage<TjExpertInfoAdminResult> pageTjExpertInfo(DataPage<TjExpertInfo> dataPage,TjExpertInfoQueryOption option) {
		DataPage<TjExpertInfoAdminResult> datapage= new DataPage<TjExpertInfoAdminResult>() ;	
		datapage.setPageNo(dataPage.getPageNo());
		datapage.setPageSize(dataPage.getPageSize());
		
		Long[] memberIdArr = null ;
		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getNickName())||!StringUtils.isEmpty(option.getPhone())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setPhone(option.getPhone());
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(phoneList.size()<=0){
				return datapage ;
			}
			memberIdArr = joinMemberIdStr(phoneList) ;
		}
		dataPage = pageTjExpertGrowInfo(dataPage,memberIdArr,option.getStartTime(),option.getEndTime(),option.getLevel()) ;
		List<TjExpertInfo> infoList = dataPage.getDataList() ;
		if(CollectionUtils.isEmpty(infoList)) return datapage;
		try {
			BeanUtils.copyProperties(datapage, dataPage);
		}catch (Exception e) {}
		List<TjExpertInfoAdminResult> tjExpertInfoAdminResultList = new ArrayList<TjExpertInfoAdminResult>() ;
		for(TjExpertInfo infoResult:infoList){
			TjExpertInfoAdminResult tjExpertInfoAdminResult = new TjExpertInfoAdminResult() ;
			tjExpertInfoAdminResult.setMemberId(infoResult.getMemberId());
			tjExpertInfoAdminResult.setLevel(infoResult.getLevel());
			tjExpertInfoAdminResult.setTjExpertLevelLogList(tjExpertLevelLogManager.quertLevelLogByMemberId(infoResult.getMemberId()));
			ModelResult<Member> memberResult = memberManager.queryMemberById(infoResult.getMemberId());
			if(!memberResult.isSuccess()){
				return datapage ;
			}
			Member member = memberResult.getModel() ;
			if(member==null){
				throw  new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST) ;
			}
			tjExpertInfoAdminResult.setNickName(member.getNickName());
			tjExpertInfoAdminResultList.add(tjExpertInfoAdminResult) ;
		}
		datapage.setDataList(tjExpertInfoAdminResultList);
		return datapage ;
	}
	
	private  DataPage<TjExpertInfo> pageTjExpertGrowInfo(DataPage<TjExpertInfo> dataPage,
			Long[] memberIdArray,Calendar startTime,Calendar endTime,Integer level) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("startTime", startTime) ;
		paras.put("endTime", endTime) ;
		paras.put("memberIdArray", memberIdArray) ;
		paras.put("level", level) ;
		return dao.queryPage("TjExpertInfoMapper.queryCount", "TjExpertInfoMapper.pageExpertLog", paras, dataPage);
	}
	@Override
	public TjExpertInfo addTjexpert(Long memberId, String desc, TjExpertLevel expertLevel) {
		TjExpertInfo tjExpertInfo = new TjExpertInfo();
		tjExpertInfo.setMemberId(memberId);
		tjExpertInfo.setLevel(expertLevel.getIndex());
		if(desc==null){
			tjExpertInfo.setDescription("");
		}else{
			tjExpertInfo.setDescription(desc);
		}
		if(tjExpertInfo.getRecord()==null){
			tjExpertInfo.setRecord("");
		}
		if(tjExpertInfo.getCreateTime()==null){
			tjExpertInfo.setCreateTime(Calendar.getInstance().getTime());
		}
		if(tjExpertInfo.getUpdateTime()==null){
			tjExpertInfo.setUpdateTime(Calendar.getInstance().getTime());
		}
		if(tjExpertInfo.getWinRatio()==null){
			tjExpertInfo.setWinRatio(BigDecimal.valueOf(0));
		}
		if(tjExpertInfo.getHalfMonthWinRatio()==null){
			tjExpertInfo.setHalfMonthWinRatio(BigDecimal.valueOf(0));
		}
		if(tjExpertInfo.getMonthWinRatio()==null){
			tjExpertInfo.setMonthWinRatio(BigDecimal.valueOf(0));
		}
		if(tjExpertInfo.getAttentionMe()==null){
			tjExpertInfo.setAttentionMe(0);
		}
		if(tjExpertInfo.getAttentionOther()==null){
			tjExpertInfo.setAttentionOther(0);
		}
		if(tjExpertInfo.getMonthOpenedCount()==null){
			tjExpertInfo.setMonthOpenedCount(0);
		}
		if(tjExpertInfo.getHalfMonthOpenedCount()==null){
			tjExpertInfo.setHalfMonthOpenedCount(0);
		}
		if(tjExpertInfo.getOpenedCount()==null){
			tjExpertInfo.setOpenedCount(0);
		}		
		dao.insertAndSetupId("TjExpertInfoMapper.insertExpert", tjExpertInfo); 
		return tjExpertInfo;	
	}

	@Override
	public int updateExpertInfo(TjExpertInfo tjExpertInfo) {
		return dao.updateByObj("TjExpertInfoMapper.updateByMemberId", tjExpertInfo);
	}

	@Override
	public List<TjExpertInfo> queryTjExpertInfoByMemberIdArray(Long[] idArray) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("idArray", idArray);
		return dao.queryList("TjExpertInfoMapper.queryTjExpertInfoByMemberIdArray", param);
	}

	@Override
	public TjExpertInfo queryExpertInfoByMemberId(Long memberId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		return dao.queryUnique("TjExpertInfoMapper.selectBymemberId", params);
	}

	@Override
	public int updateExpertRecord(Long memberId, String newRecord,
			String oldRecord) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId);
		paras.put("newRecord", newRecord);
		paras.put("oldRecord", oldRecord);
		return dao.update("TjExpertInfoMapper.updateExpertRecord", paras) ;
	}

	@Override
	public DataPage<TjExpertInfo> pageExpertByLevel(Integer[] levelArray,
			DataPage<TjExpertInfo> page) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("levelArray", levelArray);
		return dao.queryPage("TjExpertInfoMapper.pageTjExpertInfoByLevelCount", "TjExpertInfoMapper.pageTjExpertInfoByLevelArray", param, page) ;
	}

	
	public int updateWinRatioAndRecord(TjExpertInfo tjExpertInfo,BigDecimal oldWinRatio) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("record", tjExpertInfo.getRecord());
		paras.put("memberId", tjExpertInfo.getMemberId());
		paras.put("winRatio", tjExpertInfo.getWinRatio());
		paras.put("oldWinRatio",oldWinRatio);
		paras.put("monthWinRatio", tjExpertInfo.getMonthWinRatio());
		paras.put("halfMonthWinRatio", tjExpertInfo.getHalfMonthWinRatio());
		paras.put("monthOpenedCount", tjExpertInfo.getMonthOpenedCount());
		paras.put("halfMonthOpenedCount", tjExpertInfo.getHalfMonthOpenedCount());
		paras.put("openedCount", tjExpertInfo.getOpenedCount());
		return dao.update("TjExpertInfoMapper.updateWinRatioAndRecord", paras) ;
	}

	@Override
	public DataPage<TjExpertAdminWinRatioResult> pageExpertWinRatio(DataPage<TjExpertAdminWinRatioResult> page,
			TjExpertWinRatioQueryOption option) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("condition", option);
		paras.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		paras.put("pageSize", page.getPageSize());
		return dao.queryPage("TjExpertInfoMapper.queryWinRatioCount", "TjExpertInfoMapper.pageExpertWinRatio", paras, page);
	}

	@Override
	public DataPage<TjExpertInfo> pageExpertInfo(DataPage<TjExpertInfo> page,Long[] memberIdArr
			,Integer level,Calendar startTime,Calendar endTime){
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberIdArr", memberIdArr) ;
		paras.put("level", level) ;
		paras.put("startTime", startTime) ;
		paras.put("endTime", endTime) ;
		return dao.queryPage("TjExpertInfoMapper.queryExpertInfoCount", "TjExpertInfoMapper.queryPageExpertInfo", paras, page);
	}
	@Override
	public int updateExpertLevel(Long memberId, Integer newLevel,
			Integer oldLevel) {
		
		try {
			 int updateRet = tjPlanManager.updateTjPlanNoShowByMemberId(memberId, oldLevel, TjRaceStatus.not_match.getIndex());
			 logger.info("用户[{}],oldLevel[{}],newLevel[{}],修改未开赛方案[{}]不显示",memberId,oldLevel,newLevel,updateRet);
		} catch (Exception e) {
			logger.info(memberId+"用户升降级，处理方案显示状态异常",e);
		}
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		paras.put("oldLevel", oldLevel) ;
		paras.put("newLevel", newLevel) ;
		return dao.update("TjExpertInfoMapper.updateExpertLevel", paras);
	}

	@Override
	public int attentionMeAdd(Long memberId) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		return dao.update("TjExpertInfoMapper.attentionMeAdd", paras) ;
	}

	@Override
	public int attentionMeSub(Long memberId) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		return dao.update("TjExpertInfoMapper.attentionMeSub", paras) ;
	}

	@Override
	public int attentionOtherAdd(Long memberId) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		return dao.update("TjExpertInfoMapper.attentionOtherAdd", paras) ;
	}

	@Override
	public int attentionOtherSub(Long memberId) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		return dao.update("TjExpertInfoMapper.attentionOtherSub", paras) ;
	}
	
	@Override
	public void updateTjExpertInfoWinRatioByMemberId(Long memberId){
		TjExpertInfo tjExpertInfo = this.queryExpertInfoByMemberId(memberId);
		if(tjExpertInfo==null){
			throw  new JcobServerException(ExceptionCode.EXPERT_NOT_EXIST) ;
		}
		
		BigDecimal oldWinRatio = tjExpertInfo.getWinRatio();
		
		Calendar endDate = DateUtil.getCurrentDayLastTime();
		Calendar before7Day = DateUtil.getSomeDayFirstTime(-6);
		Calendar before15Day = DateUtil.getSomeDayFirstTime(-14);
		Calendar before30Day = DateUtil.getSomeDayFirstTime(-29);
		
		GenericThree<BigDecimal,Integer,String> _7DayWinRation = tjPlanManager.countWinRatioAndOpenedPlanCountByTime(before7Day,endDate,memberId);
		GenericThree<BigDecimal,Integer,String> _15DayWinRation = tjPlanManager.countWinRatioAndOpenedPlanCountByTime(before15Day,endDate,memberId);
		GenericThree<BigDecimal,Integer,String> monthWinRation = tjPlanManager.countWinRatioAndOpenedPlanCountByTime(before30Day,endDate,memberId);
		
		 
		tjExpertInfo.setWinRatio(_7DayWinRation.getOne()); 
		tjExpertInfo.setMonthWinRatio(monthWinRation.getOne());
		tjExpertInfo.setHalfMonthWinRatio(_15DayWinRation.getOne());
		tjExpertInfo.setRecord(_7DayWinRation.getTree());//近几中几,最多近7场,取近一周数据
		tjExpertInfo.setMonthOpenedCount(monthWinRation.getTwo());
		tjExpertInfo.setHalfMonthOpenedCount(_15DayWinRation.getTwo());
		tjExpertInfo.setOpenedCount(_7DayWinRation.getTwo());
		int updateCount = this.updateWinRatioAndRecord(tjExpertInfo, oldWinRatio);
		if(updateCount != 1){
			throw  new JcobServerException(ExceptionCode.EXPERT_WINRATIO_UPDATE_ERROR) ;
		}
		//自动更新专家等级:正式专家-->正式专家（带V）：近1个月发布推荐≧30个且命中率≧80%的正式专家，其发布推荐时火眼设置范围为0-30个。此类专家的升降均系统自动完成
		//自动升级
		if(tjExpertInfo.getLevel()==2&&monthWinRation.getOne().compareTo(BigDecimal.valueOf(0.8))>=0&&monthWinRation.getTwo()>=30){
			logger.info("正式专家不带V-[{}]近1个月发布推荐≧30个且命中率≧80%,自动升为正式专家带V",tjExpertInfo.getMemberId());
			int updateLevel = this.updateExpertLevel(tjExpertInfo.getMemberId(), 3, 2);
			if(updateLevel!=1){
				throw new JcobServerException(ExceptionCode.EXPERT_LEVEL_AUTO_UPDATE_ERROR) ; 
			}
			TjExpertLevelLog tjExpertLevelLog = new TjExpertLevelLog() ;
			tjExpertLevelLog.setMemberId(tjExpertInfo.getMemberId());
			tjExpertLevelLog.setHandleType(1);
			tjExpertLevelLog.setNewLevel(3);
			tjExpertLevelLog.setOldLevel(2);
			int insertLog = tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog) ;
			if(insertLog!=1){
				throw new JcobServerException(ExceptionCode.EXPERT_LEVEL_LOG_INSERT_ERROR) ; 
			}
		}
		//自动降级
		if(tjExpertInfo.getLevel()==3&&(monthWinRation.getOne().compareTo(BigDecimal.valueOf(0.8))<0||monthWinRation.getTwo()<30)){
			logger.info("正式专家带V-[{}]近1个月发布推荐<30个或者命中率<80%,自动降为正式专家不带V",tjExpertInfo.getMemberId());
			int updateLevel = this.updateExpertLevel(tjExpertInfo.getMemberId(), 2, 3);
			if(updateLevel!=1){
				throw new JcobServerException(ExceptionCode.EXPERT_LEVEL_AUTO_UPDATE_ERROR) ; 
			}
			TjExpertLevelLog tjExpertLevelLog = new TjExpertLevelLog() ;
			tjExpertLevelLog.setMemberId(tjExpertInfo.getMemberId());
			tjExpertLevelLog.setHandleType(2);
			tjExpertLevelLog.setNewLevel(2);
			tjExpertLevelLog.setOldLevel(3);
			int insertLog = tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog) ;
			if(insertLog!=1){
				throw new JcobServerException(ExceptionCode.EXPERT_LEVEL_LOG_INSERT_ERROR) ; 
			}
		
		}
		
	}

	@Override
	public ModelResult<Map<Long, TjExpertRecommendResult>> queryRecommendExpertByMemberId(
			List<Long> memberIdList) {
		Long[] idArray = memberIdList.toArray(new Long[0]) ;
		if(idArray.length==0){
			throw  new JcobServerException("memberIdList.is.null", "参数memberId为空！") ;
		}
		List<TjExpertInfo> expertList = this.queryTjExpertInfoByMemberIdArray(idArray) ;
		if(CollectionUtils.isEmpty(expertList)) {
			throw  new JcobServerException(ExceptionCode.EXPERT_RECOMMENDRESULT_IS_NULL) ;
		}
		Map<Long, TjExpertRecommendResult> map = new HashMap<Long, TjExpertRecommendResult>() ;
		for(TjExpertInfo expert : expertList){
			ModelResult<Member> memberResult = memberManager.queryMemberById(expert.getMemberId());
			if(!memberResult.isSuccess()){
				throw  new JcobServerException(memberResult.getErrorCode(), memberResult.getErrorMsg()) ;
			}
			Member member = memberResult.getModel() ;
			if(member==null){
				throw  new JcobServerException(ExceptionCode.EXPERT_MEMBER_NOT_EXIST) ;
			}
			TjExpertRecommendResult tjExpertRecommendResult = new TjExpertRecommendResult() ;
			tjExpertRecommendResult.setId(expert.getId());
			tjExpertRecommendResult.setMemberId(expert.getMemberId());
			tjExpertRecommendResult.setNickName(member.getNickName());
			tjExpertRecommendResult.setIcon(member.getIcon());
			tjExpertRecommendResult.setLevel(expert.getLevel());
			tjExpertRecommendResult.setPhone(member.getPhone());
			map.put(expert.getMemberId(), tjExpertRecommendResult) ;
			
		}
		return new ModelResult<Map<Long, TjExpertRecommendResult>>(map);
	}




	@Override
	public ModelResult<Boolean> updateExpertLevelAndInsertLog(Long memberId,
			Integer newLevel, Integer oldLevel, Integer handType) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){

				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {			
					//更新申请审核状态applyCheckStatus
					if(handType==1){ //升级,专家申请审核状态改为:审核升为正式专家
						tjExpertApplyManager.checkExpert(TjExpertApplyCheckStatus.check_format_EXPERT.getIndex(), "", memberId) ;
					}else if(handType==2){ //降级,专家申请审核状态改为:正式降为普通用户
						//有最近的申请单，并且为正式专家不带V(3)或者实验室专家（1），修改审核状态降为普通用户
						TjExpertApplyInfo  info = tjExpertApplyManager.queryLastExpertAplly(memberId);
						if(info!=null&&(info.getCheckStatus()==3||info.getCheckStatus()==1)){
							tjExpertApplyManager.checkExpert(TjExpertApplyCheckStatus.check_normal.getIndex(), "", memberId) ;
						}
						//降为普通用户,不再有粉丝,粉丝的关注状态和关注人都要相对修改
						int zeroAttentionMe = zeroAttentionMe(memberId);//将专家的attentionMe置零
						if(zeroAttentionMe!=1){
							throw new JcobServerException(ExceptionCode.EXPERT_UPDATE_ATTENTIONME_TO_ZERO_ERROR);
						}
						//查询专家的fans,将fans的attention_other修改,并且取消关注状态
						List<Long> fansMemberIdList = tjAttentionExpertManager.queryFansByExpertMemberId(memberId);
						if(!CollectionUtils.isEmpty(fansMemberIdList)){
							for(Long fansMemberId:fansMemberIdList){
								int attentionStatus = tjAttentionExpertManager.updateAttentionMemberId(fansMemberId, memberId, 0);
								if(attentionStatus!=1){
									throw new JcobServerException(ExceptionCode.EXPERT_ATTENTIONSTATUS_UPDATE_ERROR);
								}
								int subAttention = attentionOtherSub(fansMemberId) ;
								if(subAttention!=1){
									throw new JcobServerException(ExceptionCode.EXPERT_ATTENTION_OTHER_UPDATE_ERROR);
								}
							}
						}
						
					}
					
					TjExpertLevelLog tjExpertLevelLog = new TjExpertLevelLog() ;
					tjExpertLevelLog.setMemberId(memberId);
					tjExpertLevelLog.setHandleType(handType);
					tjExpertLevelLog.setNewLevel(newLevel);
					tjExpertLevelLog.setOldLevel(oldLevel);
					tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog) ;
					updateExpertLevel(memberId, newLevel, oldLevel) ;		
					
				}
			});
				
			}catch(JcobServerException e){
				result.setModel(Boolean.FALSE);
				logger.error(e.getMessage());
				errMap.put("error", e.getMessage());
				result.setErrorList(errMap);
				return result;
			}catch (Exception e){
				result.setModel(Boolean.FALSE);
				logger.error(e.getMessage());
				errMap.put("error", "系统处理异常");
				result.setErrorList(errMap);
				return result;
			}
			result.setModel(Boolean.TRUE);
			return result;
	}




	@Override
	public DataPage<TjExpertWinRatioResult> pageExpertWinRatioByRank(
			DataPage<TjExpertWinRatioResult> dataPage, Integer winRatioRankField,Integer leastCountPlan) {
		DataPage<TjExpertWinRatioResult> page =pageWinRatioByRank(dataPage,winRatioRankField,leastCountPlan) ;
		if(page==null){
			return null ;
		}
		List<TjExpertWinRatioResult> listTjExpertWinRatio = page.getDataList() ;
		if(CollectionUtils.isEmpty(listTjExpertWinRatio)) return page;
		for(TjExpertWinRatioResult winRatioResult:listTjExpertWinRatio){
			ModelResult<Member> memberResult = memberManager.queryMemberById(winRatioResult.getMemberId()) ;
			if(!memberResult.isSuccess()){
				return page;
			}
			Member member = memberResult.getModel() ;
			if(member==null){
				return page ;
			}
			winRatioResult.setIcon(member.getIcon());
			winRatioResult.setNickName(member.getNickName());
			winRatioResult.setPhone(member.getPhone());
		}
		page.setDataList(listTjExpertWinRatio);
		return page;
	}
	
	public DataPage<TjExpertWinRatioResult>pageWinRatioByRank(DataPage<TjExpertWinRatioResult> dataPage, Integer winRatioRankField,Integer leastCountPlan){
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("leastCountPlan", leastCountPlan) ;
		if(winRatioRankField==0){ //一个月排序
			return dao.queryPage("TjExpertInfoMapper.rankMonthWinRatioCount", "TjExpertInfoMapper.pageTestExpertMonthWinRatioByRank", paras, dataPage);
		}else if(winRatioRankField==1){//半个月排序
			return dao.queryPage("TjExpertInfoMapper.rankHalfMonthWinRatioCount", "TjExpertInfoMapper.pageTestExpertHalfMonthWinRatioByRank", paras, dataPage);
		}else if(winRatioRankField==2){ //7天排序
			return dao.queryPage("TjExpertInfoMapper.rankWinRatioCount", "TjExpertInfoMapper.pageTestExpertWinRatioByRank", paras, dataPage);
		}
		return null;
	}
	
	/**
	 * 组合ID的组合字符串
	 * @param list
	 * @return
	 */
	private Long[] joinMemberIdStr(List<Member> list) {
		List<Long> idlist = new ArrayList<Long>();
		for(Member member : list) idlist.add(member.getId());
		return idlist.toArray(new Long[]{});
	}

	@Override
	public int zeroAttentionMe(Long memberId) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberId", memberId) ;
		return dao.update("TjExpertInfoMapper.zeroAttentionMe", paras) ;
	}

	@Override
	public DataPage<Long> queryExpertMemberId(DataPage<Long> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("expertLevel", TjExpertLevel.getExpertList());
		paras.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		paras.put("pageSize", page.getPageSize());
		return dao
				.queryPage("TjExpertInfoMapper.queryExpertMemberIdCount", "TjExpertInfoMapper.queryExpertMemberId", paras, page);
	}
}
 