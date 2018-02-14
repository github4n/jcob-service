package com.aicai.jcob.tjexpert.manager.impl;  

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.TjExpertLevelLog;
import com.aicai.jcob.tjexpert.common.domain.TjExpertSetInfo;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExepertLevelHandleType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertSetInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertSetAdminResult;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;
import com.aicai.jcob.tjexpert.manager.TjExpertSetInfoManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午11:03:46 
 * 程序的简单说明 
 */
public class TjExpertSetInfoManagerImpl implements TjExpertSetInfoManager {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericMybatisDao dao;

	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager ;
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager ;
	@Autowired
	@Qualifier("tjExpertLevelLogManagerImpl")
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	protected TransactionTemplate transactionTemplate;
	@Autowired
	private TjPlanManager tjPlanManager;
	
	private DataPage<TjExpertSetAdminResult> queryExpertSetPage(
			DataPage<TjExpertSetAdminResult> page,
			Long[]memberIdArray,Calendar startTime,Calendar endTime) {
		Map<String,Object> paras = new HashMap<String,Object>();
		paras.put("memberIdArray", memberIdArray) ;
		paras.put("startTime", startTime) ;
		paras.put("endTime", endTime) ;
		return dao.queryPage("TjExpertSetInfoMapper.queryCount", "TjExpertSetInfoMapper.pageExpert", paras, page);
	}

	@Override
	public ModelResult<Boolean> insertPersonSetExpert(Long memberId,
			String realName, String certNo, String desc, TjExpertLevel expertLevel,String operaterName) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Member member = memberManager.queryMemberById(memberId).getModel();
		if(member==null){ //不允许后台设置未注册的专家
			logger.info("不允许后台设置未注册的专家!");
			throw new JcobServerException(ExceptionCode.EXPERT_NOT_REGISTER);	
		}
		TjExpertInfo info = tjExpertInfoManager.queryExpertInfoByMemberId(memberId);
		if(info==null){
			logger.info("专家不存在!");
			throw new JcobServerException(ExceptionCode.EXPERT_NOT_EXIST);	
		}
		if(info.getLevel()>=2){
			logger.info("后台人工设置专家，要求用户当前不是专家身份!");
			throw new JcobServerException(ExceptionCode.EXPERT_ADMIN_SET_REQUIRE_MEMBER_NOT_EXPERT);	
		}
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){

				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					if(StringUtils.isEmpty(member.getRealName())||!member.getRealName().equals(realName)
							||StringUtils.isEmpty(member.getCertNo())||!member.getCertNo().equals(certNo)){
						memberManager.updateMemberCertNoRealNameById(memberId,certNo,realName);	
					}
					
					TjExpertSetInfo TjExpertSetInfo = new TjExpertSetInfo();
					TjExpertSetInfo.setDescription(desc);
					TjExpertSetInfo.setMemberId(memberId);
					TjExpertSetInfo.setLevel(expertLevel.getIndex());
					TjExpertSetInfo.setOperaterName(operaterName);
					insertPersonSetExpert(TjExpertSetInfo);
					
					//更新专家信息
					TjExpertInfo tjExpertInfo = new TjExpertInfo();
					tjExpertInfo.setMemberId(memberId);
					tjExpertInfo.setDescription(desc);
					tjExpertInfo.setLevel(expertLevel.getIndex());
					tjExpertInfoManager.updateExpertInfo(tjExpertInfo);
					
					//新增专家成长日志
					TjExpertLevelLog tjExpertLevelLog = new TjExpertLevelLog() ;
					tjExpertLevelLog.setNewLevel(expertLevel.getIndex());
					tjExpertLevelLog.setOldLevel(TjExpertLevel.normal.getIndex());
					tjExpertLevelLog.setMemberId(memberId);
					tjExpertLevelLog.setHandleType(TjExepertLevelHandleType.up.getIndex());
					tjExpertLevelLogManager.insertExpertLevelLog(tjExpertLevelLog);
				}
			});
		}catch(JcobServerException e){
			result.setModel(Boolean.FALSE);
			result.withError(e.getCode(), e.getMessage());
			logger.error(e.getMessage());
			return result;
		}catch (Exception e){
			result.setModel(Boolean.FALSE);
			result.withError("error", "系统处理异常");
			logger.error(e.getMessage());
			return result;
		}

		try {
			 int updateRet = tjPlanManager.updateTjPlanNoShowByMemberId(memberId, info.getLevel(), TjRaceStatus.not_match.getIndex());
			 logger.info("用户[{}],oldLevel[{}],newLevel[{}],修改未开赛方案[{}]不显示",memberId,info.getLevel(),expertLevel.getIndex(),updateRet);
		} catch (Exception e) {
			logger.info(memberId+"用户升降级，处理方案显示状态异常",e);
		}
		result.setModel(Boolean.TRUE);
		return result;
	}
	
	private TjExpertSetInfo insertPersonSetExpert(TjExpertSetInfo tjPersonSetExpert) {
		tjPersonSetExpert.setCreateTime(new Date());
		tjPersonSetExpert.setUpdateTime(new Date());
		dao.insertAndSetupId("TjExpertSetInfoMapper.insertExpert", tjPersonSetExpert);
		return tjPersonSetExpert;
	}

	@Override
	public DataPage<TjExpertSetAdminResult> queryPage(DataPage<TjExpertSetAdminResult> dataPage,
			TjExpertSetInfoQueryOption option) {
		DataPage<TjExpertSetAdminResult> page= new DataPage<TjExpertSetAdminResult>() ;
		page.setPageNo(dataPage.getPageNo());
		page.setPageSize(dataPage.getPageSize());
		Long[] memberIdArr = null ;
		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getNickName())||!StringUtils.isEmpty(option.getPhone())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setPhone(option.getPhone());
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(phoneList.size()<=0){
				return page ;
			}
			memberIdArr = joinMemberIdStr(phoneList) ;
		}
		page = queryExpertSetPage(dataPage,memberIdArr,option.getStartTime(),option.getEndTime()) ;
		List<TjExpertSetAdminResult> infoList = page.getDataList() ;
		if(CollectionUtils.isEmpty(infoList)) return page;
		for(TjExpertSetAdminResult infoResult:infoList){
			ModelResult<Member> memberResult = memberManager.queryMemberById(infoResult.getMemberId());
			if(!memberResult.isSuccess()){
				return page ;
			}
			Member member = memberResult.getModel() ;
			if(member==null){
				throw  new JcobServerException("member.is.null", "用户不存在") ;
			}
			infoResult.setNickName(member.getNickName());
		}
		page.setDataList(infoList);
		return page;
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
}
 