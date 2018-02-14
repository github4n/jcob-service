package com.aicai.jcob.tjexpert.manager.impl;  

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;
import com.aicai.jcob.tjexpert.common.domain.constant.ExpertApplyProgress;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExepertLevelHandleType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertApplyCheckStatus;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertApplyInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.ApplyProgressResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertApplyAdminResult;
import com.aicai.jcob.tjexpert.manager.TjExpertApplyManager;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午3:18:06 
 * 程序的简单说明 
 */
public class TjExpertApplyManagerImpl implements TjExpertApplyManager {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao dao;

	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager ;
	
	@Autowired
	@Qualifier("tjPlanManagerImpl")
	private TjPlanManager tjPlanManager ;
	
	@Autowired
	@Qualifier("tjExpertLevelLogManagerImpl")
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager ;
	
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	protected TransactionTemplate transactionTemplate;
	
	@Override
	public ModelResult<Boolean> checkExpert(Integer checkStaus,String cancelReason,
			Long memberId) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					int update = updateExpertApply(checkStaus,cancelReason,memberId);
					if(update<=0){
						logger.info("更新专家审核出错!");
						throw new JcobServerException(ExceptionCode.EXPERT_APPLY_UPDATE_ERROR);	
					}
					TjExpertApplyCheckStatus expertApplyCheckStatus = TjExpertApplyCheckStatus.valueOf(checkStaus);
					if(expertApplyCheckStatus.equals(TjExpertApplyCheckStatus.pass_check)){ 
						//审核通过,更新专家级别(实验室专家)
						tjExpertInfoManager.updateExpertLevel(memberId, TjExpertLevel.test_expert.getIndex(), TjExpertLevel.normal.getIndex()) ;
						//审核通过,增加专家升级日志
						TjExpertLevelLog tjExpertLevelLog = new TjExpertLevelLog();
						tjExpertLevelLog.setMemberId(memberId);
						tjExpertLevelLog.setHandleType(TjExepertLevelHandleType.up.getIndex());
						tjExpertLevelLog.setNewLevel(TjExpertLevel.test_expert.getIndex());
						tjExpertLevelLog.setOldLevel(TjExpertLevel.normal.getIndex());
						tjExpertLevelLog.setCreateTime(new Date());
						tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog);
					}
				}
			});
		}catch(JcobServerException e){
			result.setModel(Boolean.FALSE);
			logger.error(e.getMessage());
			return result.withError("error", e.getMessage());
		}catch (Exception e){
			result.setModel(Boolean.FALSE);
			logger.error(e.getMessage());
			return result.withError("error", "系统处理异常");
		}
		result.setModel(Boolean.TRUE);
		return result;
		
	}
	
	private int updateExpertApply(Integer checkStaus,String cancelReason,
			Long memberId){
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("checkStatus", checkStaus);
		paras.put("cancelReason", cancelReason);
		paras.put("memberId", memberId);
		return dao.update("TjExpertApplyInfoMapper.updateCheckStatus", paras);
	}

	@Override
	public DataPage<TjExpertApplyAdminResult> pageTjExpertApplyInfo(DataPage<TjExpertApplyInfo> dataPage,
			TjExpertApplyInfoQueryOption option) {
		DataPage<TjExpertApplyAdminResult> resultPage = new DataPage<TjExpertApplyAdminResult>() ;
		resultPage.setPageNo(dataPage.getPageNo());
		resultPage.setPageSize(dataPage.getPageSize());
		Long[] memberIdArr = null ;
		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getNickName())||!StringUtils.isEmpty(option.getPhone())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setPhone(option.getPhone());
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(phoneList.size()<=0){
				return resultPage ;
			}
			memberIdArr = joinMemberIdStr(phoneList) ;
		}
		DataPage<TjExpertApplyInfo> applyInfoPage = pageApplyInfo(memberIdArr,dataPage,option.getCheckStatus(),option.getStartTime(),option.getEndTime()) ;
		return  comboApplyDetailField(resultPage,applyInfoPage);
	}

	private DataPage<TjExpertApplyAdminResult> comboApplyDetailField(DataPage<TjExpertApplyAdminResult> resultPage,DataPage<TjExpertApplyInfo> applyInfoPage){
		try {
			BeanUtils.copyProperties(resultPage, applyInfoPage);
		} catch (Exception e) {}
		
		List<TjExpertApplyInfo> attentList = applyInfoPage.getDataList() ;
		if(CollectionUtils.isEmpty(attentList)) return resultPage ;
		List<TjExpertApplyAdminResult> reslutList = new ArrayList<TjExpertApplyAdminResult>() ;
		for(TjExpertApplyInfo applyInfo:attentList){
			TjExpertApplyAdminResult adminResult = new TjExpertApplyAdminResult(); 
			adminResult.setId(applyInfo.getId());
			adminResult.setMemberId(applyInfo.getMemberId());
			adminResult.setCreateTime(applyInfo.getCreateTime());
			adminResult.setCheckStatus(applyInfo.getCheckStatus());
			ModelResult<Member> memberResult =memberManager.queryMemberById(applyInfo.getMemberId()) ;
			if(!memberResult.isSuccess()){
				throw  new JcobServerException(memberResult.getErrorCode(), memberResult.getErrorMsg()) ;
			}
			Member member = memberResult.getModel() ;
			if(member==null){
				throw  new JcobServerException("TjExpertApplyInfo.memberManager.queryMemberById.null", "根据memberId:["+applyInfo.getMemberId()+"]查询专家申请,用户为空！") ;
			}
			adminResult.setNickName(member.getNickName());
			reslutList.add(adminResult) ;
		}
		resultPage.setDataList(reslutList);
		return resultPage;
		
	}
	private DataPage<TjExpertApplyInfo> pageApplyInfo(Long[] memberIdArr,DataPage<TjExpertApplyInfo> dataPage,Integer checkStatus,Calendar startTime,Calendar endTime){
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberIdArr", memberIdArr) ;
		paras.put("checkStatus", checkStatus) ;
		paras.put("startTime", startTime) ;
		paras.put("endTime", endTime) ;
		return dao.queryPage("TjExpertApplyInfoMapper.queryCount", "TjExpertApplyInfoMapper.pageExpertApplyInfo", paras, dataPage);
	}
	@Override
	public ModelResult<List<TjExpertApplyInfo>> queryExpertApllyByMemberId(Long memberId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
	    List<TjExpertApplyInfo> tjExpertApplyInfoList = dao.queryList("TjExpertApplyInfoMapper.selectBymemberId", params);    
	    return new ModelResult< List<TjExpertApplyInfo>>(tjExpertApplyInfoList);
	}
	
	@Override
	public TjExpertApplyInfo queryUnderWayExpertAplly(Long memberId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
	    TjExpertApplyInfo tjExpertApplyInfo = dao.queryUnique("TjExpertApplyInfoMapper.queryUnderWayExpertAplly", params);    
	    return tjExpertApplyInfo; 
	}
	
	@Override
	public TjExpertApplyInfo queryLastExpertAplly(Long memberId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
	    TjExpertApplyInfo tjExpertApplyInfo = dao.queryUnique("TjExpertApplyInfoMapper.queryLastExpertAplly", params);    
	    return tjExpertApplyInfo; 
	}
	
	@Override
	public ModelResult<TjExpertApplyInfo> insertApplyExpertInfo(
			TjExpertApplyInfo tjExpertApplyInfo) {
		tjExpertApplyInfo.setCreateTime(Calendar.getInstance());
		tjExpertApplyInfo.setUpdateTime(Calendar.getInstance());
		if(tjExpertApplyInfo.getCheckStatus()==null){
			tjExpertApplyInfo.setCheckStatus(TjExpertApplyCheckStatus.wait_check.getIndex());
		}
		dao.insertAndSetupId("TjExpertApplyInfoMapper.insertExpertApplyInfo", tjExpertApplyInfo);
		return new ModelResult<TjExpertApplyInfo>(tjExpertApplyInfo);
	}

	@Override
	public ModelResult<TjExpertApplyAdminResult> queryExpertDetailById(Long id) {
		ModelResult<TjExpertApplyAdminResult>  modelResult = new ModelResult<TjExpertApplyAdminResult> () ;
		TjExpertApplyInfo tjExpertApplyInfo = this.queryApplayInfoById(id);
		TjExpertApplyAdminResult tjExpertApplyAdminResult = new TjExpertApplyAdminResult() ;
		if(tjExpertApplyInfo==null){
			throw new JcobServerException("TjExpertApplyInfoManager.queryApplayInfoById.null", "根据id:["+id+"]查询专家申请为空！") ;
		}
		tjExpertApplyAdminResult.setApplyReason(tjExpertApplyInfo.getApplyReason());
		tjExpertApplyAdminResult.setWinImgPath(tjExpertApplyInfo.getWinImgPath()) ;
		tjExpertApplyAdminResult.setMemberId(tjExpertApplyInfo.getMemberId());
		ModelResult<Member> memberResult =memberManager.queryMemberById(tjExpertApplyInfo.getMemberId()) ;
		if(!memberResult.isSuccess()){
			throw  new JcobServerException(memberResult.getErrorCode(), memberResult.getErrorMsg()) ;
		}
		Member member = memberResult.getModel() ;
		if(member==null){
			throw  new JcobServerException("TjExpertApplyInfo.memberManager.queryMemberById.null", "根据memberId:["+tjExpertApplyInfo.getMemberId()+"]查询专家申请,用户为空！") ;
		}
		tjExpertApplyAdminResult.setNickName(member.getNickName());
		tjExpertApplyAdminResult.setPhone(member.getPhone());
		tjExpertApplyAdminResult.setCertNo(member.getCertNo());
		tjExpertApplyAdminResult.setRealName(member.getRealName());
		modelResult.setModel(tjExpertApplyAdminResult);
		return modelResult ;
   
	}
	private TjExpertApplyInfo queryApplayInfoById(Long id){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return dao.queryUnique("TjExpertApplyInfoMapper.selectDetail", params); 
	}
	
	/**
	 *
     * 未申请 （终止）0
     * 申请提交 1 
     * 申请提交   -> 未通过（终止）2
     * 申请提交   —> 通过 3
     * 申请提交   —> 通过－>正式专家失败（终止）4
     * 申请提交   —> 通过－>发布了一条方案 5
     * 申请提交   —> 通过－>发布了一条方案－》正式专家失败（终止）6
     * 申请提交   —> 通过－>发布了一条方案－》正式专家（终止）7
	 * @param memberId
	 * @return
	 */
	public ApplyProgressResult queryApplyProgressInfo(Long memberId){
		
		ApplyProgressResult applyProgressResult = new ApplyProgressResult();
		TjExpertApplyInfo tjExpertApplyInfo = this.queryLastExpertAplly(memberId);
		if(tjExpertApplyInfo == null){
			applyProgressResult.setApplyProgress(ExpertApplyProgress.NOT_APPLY.getIndex());
			return applyProgressResult;
		}
		applyProgressResult.setApplyTime(tjExpertApplyInfo.getCreateTime());
		
		if(tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.wait_check.getIndex()){//申请中，未审核
			applyProgressResult.setApplyProgress(ExpertApplyProgress.IN_CHECK.getIndex());
			return applyProgressResult;
		}
		
		if(tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.cancel_check.getIndex()){//审核未通过
			applyProgressResult.setApplyProgress(ExpertApplyProgress.NOT_PASS_FIRST_CHECK.getIndex());
			applyProgressResult.setCancelReason(tjExpertApplyInfo.getCancelReason());
			applyProgressResult.setCheckTime(tjExpertApplyInfo.getUpdateTime());
			return applyProgressResult;
		}
		
		
		TjExpertLevelLog tjExpertLevelLog =tjExpertLevelLogManager.queryLastTestExpertLog(memberId).getModel();
		Calendar lastBeTestExpertTime = null; //最后一次成为的测试专家
		if(tjExpertLevelLog != null){
			lastBeTestExpertTime = DateUtil.date2Calendar(tjExpertLevelLog.getCreateTime());
		}
		//成为测试专家的时间后第个方案
		TjPlan firstPlan = tjPlanManager.selectFirstPlanByMemberIdStartTime(memberId,lastBeTestExpertTime);
		
		if(tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.pass_check.getIndex() 
			&& firstPlan == null ){//审核通过，实验室专家,没有发过方案
			applyProgressResult.setApplyProgress(ExpertApplyProgress.BE_TEST_EXPERT.getIndex());
			applyProgressResult.setCheckTime(lastBeTestExpertTime);
			return applyProgressResult;
		}
		if(tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.pass_check.getIndex() 
				&& firstPlan != null ){//审核通过，实验室专家,发过方案
			applyProgressResult.setCheckTime(lastBeTestExpertTime);
			applyProgressResult.setApplyProgress(ExpertApplyProgress.test_expert_PUBLISH_TJPLAN.getIndex());
			applyProgressResult.setFirstPublishPlanTime(firstPlan.getCreateTime());
			return applyProgressResult;
		}
		if(tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.check_normal.getIndex()){
			tjExpertLevelLog =tjExpertLevelLogManager.queryLastDropInNomalLog(memberId);
			Calendar dropInNomalTime = null; //最后一次成为的测试专家
			if(tjExpertLevelLog != null){
				dropInNomalTime = DateUtil.date2Calendar(tjExpertLevelLog.getCreateTime());
				applyProgressResult.setFormalExpertChekcTime(dropInNomalTime);
			}
			if(firstPlan == null ){//审核通过，,没有发过方案,又降为普通用户了
				applyProgressResult.setCheckTime(lastBeTestExpertTime);
				applyProgressResult.setApplyProgress(ExpertApplyProgress.NOt_EXPERT_after_test_expert.getIndex());
				return applyProgressResult;
			}
			if(firstPlan != null ){//审核通过，,发过方案,又降为普通用户了
				applyProgressResult.setCheckTime(lastBeTestExpertTime);
				applyProgressResult.setApplyProgress(ExpertApplyProgress.NOt_EXPERT_after_publish_plan.getIndex());
				applyProgressResult.setFirstPublishPlanTime(firstPlan.getCreateTime());
				return applyProgressResult;
			}
		}
		if((tjExpertApplyInfo.getCheckStatus().intValue() == TjExpertApplyCheckStatus.check_format_EXPERT.getIndex())){//审核通过，实验室专家,发过方案
			tjExpertLevelLog =tjExpertLevelLogManager.queryLastFormalExpertLog(memberId).getModel();
			Calendar lastBeFormatExpertTime = null; //最后一次成为的测试专家
			if(tjExpertLevelLog != null){
				lastBeFormatExpertTime = DateUtil.date2Calendar(tjExpertLevelLog.getCreateTime());
				applyProgressResult.setFormalExpertChekcTime(lastBeFormatExpertTime);
			}
			applyProgressResult.setApplyProgress(ExpertApplyProgress.BE_FORMAL_EXPERT.getIndex());
			if(firstPlan != null){
				applyProgressResult.setFirstPublishPlanTime(firstPlan.getCreateTime());
			}
			return applyProgressResult;
		}
		return applyProgressResult;
	}
	private Long[] joinMemberIdStr(List<Member> list) {
		List<Long> idlist = new ArrayList<Long>();
		for(Member member : list) idlist.add(member.getId());
		return idlist.toArray(new Long[]{});
	}
}
 