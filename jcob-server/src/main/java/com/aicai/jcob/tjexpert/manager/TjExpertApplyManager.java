package com.aicai.jcob.tjexpert.manager;  

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.TjExpertApplyInfo;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertApplyInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.ApplyProgressResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertApplyAdminResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午3:17:25 
 * 程序的简单说明 
 */
public interface TjExpertApplyManager {
	
	ModelResult<TjExpertApplyInfo> insertApplyExpertInfo(
			TjExpertApplyInfo tjExpertApplyInfo);

	ModelResult<Boolean> checkExpert(Integer checkStaus,String cancelReason,Long memberId);
	
	DataPage<TjExpertApplyAdminResult> pageTjExpertApplyInfo(
			DataPage<TjExpertApplyInfo> page,
			TjExpertApplyInfoQueryOption tjExpertInfoQueryOption);
	
	public ModelResult<List<TjExpertApplyInfo>> queryExpertApllyByMemberId(Long memberId);
	/**
	 * 查询最后一个申请单
	 * @param memberId
	 * @return
	 */
	public TjExpertApplyInfo queryLastExpertAplly(Long memberId);
	/**
	 * 查询在进行中的申请单
	 * @param memberId
	 * @return
	 */
	public TjExpertApplyInfo queryUnderWayExpertAplly(Long memberId);
	
	ModelResult<TjExpertApplyAdminResult> queryExpertDetailById(Long id);
	public ApplyProgressResult queryApplyProgressInfo(Long memberId);
	
}
 