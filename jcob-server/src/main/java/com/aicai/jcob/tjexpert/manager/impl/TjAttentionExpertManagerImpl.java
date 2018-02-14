package com.aicai.jcob.tjexpert.manager.impl;  

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.MemberExpertOption;
import com.aicai.jcob.member.common.domain.option.MemberOperLogQueryOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.tjexpert.common.domain.TjAttentionExpert;
import com.aicai.jcob.tjexpert.common.domain.constant.AttentionStatus;
import com.aicai.jcob.tjexpert.common.domain.option.TjAttentionExpertQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;
import com.aicai.jcob.tjexpert.manager.TjAttentionExpertManager;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月16日 下午2:13:49 
 * 程序的简单说明 
 */
public class TjAttentionExpertManagerImpl implements TjAttentionExpertManager {
	
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
	@Qualifier("memberOperLogManagerImpl")
	private MemberOperLogManager memberOperLogManager ;
	
	@Autowired
	@Qualifier("transactionTemplateTjplan")
	protected TransactionTemplate transactionTemplate;
	
	@Override
	public int updateAttentionMemberId(Long memberId, Long expertMemberId,
			Integer attentionStatus) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("attentionMemberId", expertMemberId);
		paras.put("memberId", memberId);
		paras.put("status", attentionStatus);
		return dao.update("TjAttentionExpertMapper.updateAttentionStatus", paras);
	}


	public int insertTjAttentionExpert(TjAttentionExpert tjAttentionExpert) {
		tjAttentionExpert.setCreateTime(new Date());
		tjAttentionExpert.setUpdateTime(new Date());
		return dao.insertAndSetupId("TjAttentionExpertMapper.insert", tjAttentionExpert);
	}

	@Override
	public List<Long> queryAttentionExpertByMemberId(Long memberId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("memberId", memberId);
		return  dao.queryList("TjAttentionExpertMapper.selectAttentionExpertByMemberId", paras);
	}

	@Override
	public boolean isAttention(Long memberId, Long expertMemberId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("memberId", memberId);
		paras.put("expertMemberId", expertMemberId);
		return dao.queryCount("TjAttentionExpertMapper.queryIsAttention", paras)>0;
	}

	@Override
	public boolean hasAttentionLog(Long memberId, Long expertMemberId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("memberId", memberId);
		paras.put("expertMemberId", expertMemberId);
		return dao.queryCount("TjAttentionExpertMapper.hasAttentionLog", paras)>0;
	}


	public DataPage<TjAttentionExpert> pageMyAttentionByMemberIdArray(
			Long[] memberIdArray,Calendar startTime,Calendar entTime,DataPage<TjAttentionExpert> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("memberIdArray", memberIdArray);
		paras.put("startTime", startTime);
		paras.put("endTime", entTime);
		return (DataPage<TjAttentionExpert>)dao.queryPage("TjAttentionExpertMapper.countMyAttention", "TjAttentionExpertMapper.pageMyAttentionByMemberIdArray", paras, page);
	}

	public DataPage<TjAttentionExpert> pageAttentioMeByMemberIdArray(
			Long[] attentionMemberId,Calendar startTime,Calendar entTime,DataPage<TjAttentionExpert> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("attentionMemberIdArray", attentionMemberId);
		paras.put("startTime", startTime);
		paras.put("endTime", entTime);
		return (DataPage<TjAttentionExpert>)dao.queryPage("TjAttentionExpertMapper.countAttentionMe", "TjAttentionExpertMapper.pageAttentionMeByattentionMemberIdArray", paras, page);
	}

	@Override
	public DataPage<TjAttentionExpertAdminResult> pageMyAttentionByOption(TjAttentionExpertQueryOption option,
			DataPage<TjAttentionExpert> page) {
		DataPage<TjAttentionExpertAdminResult> dataPage = new DataPage<TjAttentionExpertAdminResult>();
		dataPage.setPageNo(page.getPageNo());
		dataPage.setPageSize(page.getPageSize());
		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getPhone())||!StringUtils.isEmpty(option.getNickName())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setPhone(option.getPhone());
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(CollectionUtils.isEmpty(phoneList)) return dataPage;
			DataPage<TjAttentionExpert> attenExpertPage = pageMyAttentionByMemberIdArray(joinMemberIdStr(phoneList),option.getStartDate(),option.getEndDate(),page);
			return comboAttentionField(dataPage,attenExpertPage);
			
		}
		return null;
	}

	@Override
	public DataPage<TjAttentionExpertAdminResult> pageAttentionMeByOption(TjAttentionExpertQueryOption option,DataPage<TjAttentionExpert> page) {	
		DataPage<TjAttentionExpertAdminResult> dataPage = new DataPage<TjAttentionExpertAdminResult>();
		dataPage.setPageNo(page.getPageNo());
		dataPage.setPageSize(page.getPageSize());

		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getPhone())||!StringUtils.isEmpty(option.getNickName())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setPhone(option.getPhone());
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(CollectionUtils.isEmpty(phoneList)) return dataPage;
			DataPage<TjAttentionExpert> attenExpertPage = pageAttentioMeByMemberIdArray(joinMemberIdStr(phoneList),option.getStartDate(),option.getEndDate(), page);
			return comboAttentionField(dataPage,attenExpertPage);
			
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
	
	private DataPage<TjAttentionExpertAdminResult> comboAttentionField(DataPage<TjAttentionExpertAdminResult> dataPage,DataPage<TjAttentionExpert> attenExpertPage) {
		try {
			BeanUtils.copyProperties(dataPage, attenExpertPage);
		} catch (Exception e) {}
		
		List<TjAttentionExpert> attentList = attenExpertPage.getDataList() ;
		if(CollectionUtils.isEmpty(attentList)) return dataPage;
		List<TjAttentionExpertAdminResult> attentionAdminResultList = new ArrayList<TjAttentionExpertAdminResult>() ;
		for(TjAttentionExpert attentionExpert:attentList){
			TjAttentionExpertAdminResult attentionAdminResult = new TjAttentionExpertAdminResult();
			attentionAdminResult.setMemberId(attentionExpert.getMemberId());
			attentionAdminResult.setNickName(memberManager.queryMemberById(attentionExpert.getMemberId()).getModel().getNickName()) ;
			attentionAdminResult.setAttentionMemberId(attentionExpert.getAttentionMemberId());
			attentionAdminResult.setAttentionNickName(memberManager.queryMemberById(attentionExpert.getAttentionMemberId()).getModel().getNickName());
			attentionAdminResult.setAttentionStatus(attentionExpert.getStatus());
			attentionAdminResult.setAttentionTime(attentionExpert.getUpdateTime());
			attentionAdminResult.setLevel(tjExpertInfoManager.queryExpertInfoByMemberId(attentionExpert.getMemberId()).getLevel());
			attentionAdminResultList.add(attentionAdminResult) ;
		}
		dataPage.setDataList(attentionAdminResultList);
		return dataPage ;
		
	}

	@Override
	public DataPage<TjAttentionLogAdminResult> pageMyAttentionOperByOption( //禁用手机号查询
			TjAttentionExpertQueryOption option,
			DataPage<TjAttentionLogAdminResult> page) {	
		DataPage<TjAttentionLogAdminResult> logPage = new DataPage<TjAttentionLogAdminResult>() ;
		MemberOperLogQueryOption memberOperLogOption = new MemberOperLogQueryOption() ;
		if(option.getMemberId()!=null||!StringUtils.isEmpty(option.getNickName())){
			MemberExpertOption memberExpertOption = new MemberExpertOption() ;
			memberExpertOption.setNickName(option.getNickName());
			Long[] idArr = option.getMemberId()==null?null:new Long[]{option.getMemberId()};
			List<Member> phoneList = memberManager.queryMemberByIdArrayAndOption(idArr, memberExpertOption) ;
			if(phoneList.size()<=0){
				idArr=null ;
				return logPage ;
			}
			idArr = joinMemberIdStr(phoneList);
			memberOperLogOption.setMemberIdArr(idArr);
		}
		memberOperLogOption.setBeginTime(option.getStartDate());
		memberOperLogOption.setEndTime(option.getEndDate());

		String remark = option.getAttentionMemberId() == null ? "" : option.getAttentionMemberId().toString();
		remark += ",";
		remark += StringUtils.isEmpty(option.getAttentionNickName()) ? "": option.getAttentionNickName().toString();
		memberOperLogOption.setRemark(remark);
		
		logPage = memberOperLogManager.pageAttentionOperLog(memberOperLogOption, page);
		List<TjAttentionLogAdminResult> attentionLogList = logPage.getDataList() ;
		if(CollectionUtils.isEmpty(attentionLogList)) return logPage;
		for(TjAttentionLogAdminResult attentionLog:attentionLogList){
			Member member = memberManager.queryMemberById(attentionLog.getMemberId()).getModel();
			attentionLog.setNickName(member.getNickName());
		}
		logPage.setDataList(attentionLogList);
		return logPage;
	}
	

	@Override
	public DataPage<TjAttentionExpertResult> pageExpertAttention(Long memberId,
			Long expertMemberId, DataPage<TjAttentionExpertResult> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("expertMemberId", expertMemberId);
		DataPage<TjAttentionExpertResult> dataPage = dao.queryPage("TjAttentionExpertMapper.countExpertAttention", "TjAttentionExpertMapper.pageExpertAttention", paras, page);
		List<TjAttentionExpertResult> listAttentionExpert = dataPage.getDataList();
		if(CollectionUtils.isEmpty(listAttentionExpert)) return dataPage;
		for(TjAttentionExpertResult attentionExpert:listAttentionExpert){
			if(memberId!=null){
				attentionExpert.setAttention(this.isAttention(memberId,attentionExpert.getAttentionMemberId()));
			}
			ModelResult<Member> memberReslt = memberManager.queryMemberById(attentionExpert.getAttentionMemberId()) ;
			if(!memberReslt.isSuccess()){
				logger.info(memberReslt.getErrorCode(),memberReslt.getErrorMsg());
				return dataPage ;
			}
			Member member = memberReslt.getModel() ;
			attentionExpert.setId(member.getId());
			attentionExpert.setIcon(member.getIcon());
			attentionExpert.setPhone(member.getPhone());
			attentionExpert.setNickName(member.getNickName());
		}
		dataPage.setDataList(listAttentionExpert);
		return dataPage;
	}

	@Override
	public DataPage<TjAttentionExpertResult> pageExpertFans(Long memberId,
			Long expertMemberId, DataPage<TjAttentionExpertResult> page) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("expertMemberId", expertMemberId);
		DataPage<TjAttentionExpertResult> dataPage =dao.queryPage("TjAttentionExpertMapper.countExpertFans", "TjAttentionExpertMapper.pageExpertFans", paras, page);
		List<TjAttentionExpertResult> listAttentionExpert = dataPage.getDataList();
		if(CollectionUtils.isEmpty(listAttentionExpert)) return dataPage;
		for(TjAttentionExpertResult attentionExpert:listAttentionExpert){
			if(memberId!=null){
				attentionExpert.setAttention(this.isAttention(memberId,attentionExpert.getMemberId()));
			}
			ModelResult<Member> memberReslt = memberManager.queryMemberById(attentionExpert.getMemberId()) ;
			if(!memberReslt.isSuccess()){
				logger.info(memberReslt.getErrorCode(),memberReslt.getErrorMsg());
				return dataPage ;
			}
			Member member = memberReslt.getModel() ;
			attentionExpert.setId(member.getId());
			attentionExpert.setPhone(member.getPhone());
			attentionExpert.setIcon(member.getIcon());
			attentionExpert.setNickName(member.getNickName());
		}
		dataPage.setDataList(listAttentionExpert);
		return dataPage;
	}

	@Override
	public ModelResult<Boolean> attentionOperByMemberId(Long memberId,
			Long expertMemberId, Integer attentionStatus,
			MemberOperOption memberOperOption) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		Map<String,String> errMap = new HashMap<String,String>();
		if(memberId==null||expertMemberId==null||attentionStatus==null){
			errMap.put("parameter.is.null", "关注参数不能为空");
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		}
		if(memberId.equals(expertMemberId)){
			errMap.put("one.cannot.attention.himself", "自己不能关注自己");
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		}
		Boolean isAttention = isAttention(memberId,expertMemberId) ;
		if((isAttention&&attentionStatus==1)||(!isAttention&&attentionStatus==0)){
			errMap.put("cannot.repeat.attention.oper", "不能重复进行关注操作");
			result.setErrorList(errMap);
			result.setModel(Boolean.FALSE);
			return result;
		}
		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult(){
	
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//之前没有关注操作记录的直接插入关注操作
					if(!hasAttentionLog(memberId, expertMemberId)){
						TjAttentionExpert tjAttentionExpert = new TjAttentionExpert() ;
						tjAttentionExpert.setMemberId(memberId);
						tjAttentionExpert.setAttentionMemberId(expertMemberId);
						tjAttentionExpert.setStatus(attentionStatus);
						int insert = insertTjAttentionExpert(tjAttentionExpert) ;
						if(insert<=0){
							throw  new JcobServerException("insert.attention.expert.error","插入关注记录出错") ;
						}
						
					}else{
					    int update = updateAttentionMemberId(memberId, expertMemberId, attentionStatus);
						if(update<=0){
							throw  new JcobServerException("update.attention.status.error","更新专家关注状态出错") ;
						}
					}	
					//更新expert信息表的attention_me,attention_other
					if(attentionStatus== AttentionStatus.attention_cancel){ //取消关注
						//对用户而言,attention_other-1,我关注的人数减
						tjExpertInfoManager.attentionOtherSub(memberId) ;
						//对专家而言 ,attention_me-1
						tjExpertInfoManager.attentionMeSub(expertMemberId) ;
						
					}
					if(attentionStatus==AttentionStatus.attention){ //关注
						//对专家而言,attention_me+1,关注我的人数加
						tjExpertInfoManager.attentionMeAdd(expertMemberId) ;
						//对用户而言,attention_other+1,我关注的人数加
						tjExpertInfoManager.attentionOtherAdd(memberId) ;
					}
					//增加member_oper_log关注操作日志
					memberOperLogManager.asyncSaveMemberOperLog(memberId, memberOperOption, memberOperOption.getRemark());
					
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
	public List<Long> queryFansByExpertMemberId(Long expertMemberId) {
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("expertMemberId", expertMemberId);
		return  dao.queryList("TjAttentionExpertMapper.selectFansByExpertMemberId", paras);
	}
	
	
}
 