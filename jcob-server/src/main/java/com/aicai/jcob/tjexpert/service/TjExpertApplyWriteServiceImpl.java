package com.aicai.jcob.tjexpert.service;  

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.tjexpert.common.domain.TjExpertApplyInfo;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertApplyInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.ApplyProgressResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertApplyAdminResult;
import com.aicai.jcob.tjexpert.common.service.TjExpertApplyWriteService;
import com.aicai.jcob.tjexpert.manager.TjExpertApplyManager;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;
import com.aicai.jcob.tjplan.manager.TjPlanManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月27日 下午6:42:28 
 *专家申请
 */
public class TjExpertApplyWriteServiceImpl implements TjExpertApplyWriteService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager ;
	
	@Autowired
	@Qualifier("tjExpertApplyManagerImpl")
	private TjExpertApplyManager tjExpertApplyManager ;
	
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager ;
	
	@Autowired
	@Qualifier("tjPlanManagerImpl")
	private TjPlanManager tjPlanManager ;
	
	@Autowired
	@Qualifier("tjExpertLevelLogManagerImpl")
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	
	@Override
	public ModelResult<TjExpertApplyInfo> applyTobeExpert(
			TjExpertApplyInfo tjExpertApplyInfo,Long memberId, String realName, String certNo) {		
		Member member = memberManager.queryMemberById(memberId).getModel();
		if(member==null){ //不允许后台设置未注册的专家
			logger.info("专家申请memberId错误!");
			throw new JcobServerException(ExceptionCode.EXPERT_APPLY_MEMBERID_ERROR);	
		}
		if(StringUtils.isEmpty(tjExpertApplyInfo.getApplyReason())){
			logger.info("专家申请理由不能为空!");
			throw new JcobServerException(ExceptionCode.EXPERT_APPLY_REASON_NOT_NULL);
		}
		if(StringUtils.isBlank(member.getRealName())||!member.getRealName().equals(realName)
				||StringUtils.isBlank(member.getCertNo())||!member.getCertNo().equals(certNo)){
			memberManager.updateMemberCertNoRealNameById(memberId,certNo,realName);	
		}	
		tjExpertApplyInfo.setMemberId(memberId);
		return tjExpertApplyManager.insertApplyExpertInfo(tjExpertApplyInfo);
	}

	@Override
	public ModelResult<Boolean> checkExpert(Integer checkStaus,String cancelReason,
			Long memberId) {
		return tjExpertApplyManager.checkExpert(checkStaus, cancelReason, memberId);
	}

	@Override
	public ModelResult<ApplyProgressResult> queryApplyProgressInfo(Long memberId){
		 ModelResult<ApplyProgressResult>   modelResult = new ModelResult<>();
		 try{
			 ApplyProgressResult applyProgress = this.tjExpertApplyManager.queryApplyProgressInfo(memberId);
			 modelResult.setModel(applyProgress);
		 }catch(JcobServerException e){
			 modelResult.withError(e.getCode(), e.gerMsg());
			 return modelResult;
		 }
		 return modelResult;
	}
	
	@Override
	public PageResult<TjExpertApplyAdminResult> pageTjExpertApplyInfo(DataPage<TjExpertApplyInfo> page,
			TjExpertApplyInfoQueryOption tjExpertInfoQueryOption) {
		PageResult<TjExpertApplyAdminResult> pageResult = new PageResult<TjExpertApplyAdminResult>() ;
		try{
			pageResult.setPage(tjExpertApplyManager.pageTjExpertApplyInfo(page, tjExpertInfoQueryOption));
		}catch(JcobServerException e){
			pageResult.withError(e.getCode(), e.gerMsg());
			return pageResult;
		}
		return pageResult ;
	}
	@Override
	public ModelResult<List<TjExpertApplyInfo>> queryExpertApllyByMemberId(Long memberId){		
		ModelResult<List<TjExpertApplyInfo>> result =  tjExpertApplyManager.queryExpertApllyByMemberId(memberId);
		return result ;
		
	}
	@Override
	public ModelResult<TjExpertApplyAdminResult> queryExpertApplyInfoDetail(Long id) {
		ModelResult<TjExpertApplyAdminResult> result = new ModelResult<TjExpertApplyAdminResult>();
		try {
			result = tjExpertApplyManager.queryExpertDetailById(id);
		} catch (JcobServerException e) {
			result.withError(e.getCode(), e.gerMsg());
			return result;
		}
		return result ;
	}
}
 