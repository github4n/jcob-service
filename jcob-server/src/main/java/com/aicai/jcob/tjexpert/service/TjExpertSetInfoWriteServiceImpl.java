package com.aicai.jcob.tjexpert.service;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertSetInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertSetAdminResult;
import com.aicai.jcob.tjexpert.common.service.TjExpertSetInfoWriteService;
import com.aicai.jcob.tjexpert.manager.TjExpertInfoManager;
import com.aicai.jcob.tjexpert.manager.TjExpertLevelLogManager;
import com.aicai.jcob.tjexpert.manager.TjExpertSetInfoManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午11:01:04 
 * 程序的简单说明 
 */
public class TjExpertSetInfoWriteServiceImpl implements
		TjExpertSetInfoWriteService {
	
	@Autowired
	@Qualifier("tjExpertSetInfoManagerImpl")
	private TjExpertSetInfoManager expertSetInfoManager ;
	
	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager ;
	
	@Autowired
	@Qualifier("tjExpertInfoManagerImpl")
	private TjExpertInfoManager tjExpertInfoManager ;
	
	@Autowired
	@Qualifier("tjExpertLevelLogManagerImpl")
	private TjExpertLevelLogManager tjExpertLevelLogManager ;
	@Override
	public ModelResult<Boolean> insertPersonSetExpert(Long memberId,
			String realName, String certNo, String desc, TjExpertLevel expertLevel,String operaterName) {
		 ModelResult<Boolean>  modelResult = new  ModelResult<Boolean> () ;
		 try{
			 modelResult = expertSetInfoManager.insertPersonSetExpert(memberId, realName, certNo, desc, expertLevel, operaterName) ;
		 }catch(JcobServerException e){
			 modelResult.withError(e.getCode(), e.gerMsg());
			 modelResult.setModel(Boolean.FALSE);
			 return modelResult;
		 }catch(Exception e){
			 modelResult.withError("error",e.getMessage());
			 modelResult.setModel(Boolean.FALSE);
			 return modelResult;
		 }
		return modelResult;
	}
	
	@Override
	public PageResult<TjExpertSetAdminResult> pageTjPersonSetExpert(DataPage<TjExpertSetAdminResult> page,TjExpertSetInfoQueryOption option) {
		PageResult<TjExpertSetAdminResult> pageResult= new PageResult<TjExpertSetAdminResult>();
		try{
		DataPage<TjExpertSetAdminResult> personSetExpertpage = expertSetInfoManager.queryPage(page, option);
		pageResult.setPage(personSetExpertpage);
		}catch(JcobServerException e){
			 pageResult.withError(e.getCode(), e.gerMsg());
			 return pageResult;
		}
		return pageResult ;
	}

}
 