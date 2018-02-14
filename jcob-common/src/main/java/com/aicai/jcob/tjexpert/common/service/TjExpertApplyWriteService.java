package com.aicai.jcob.tjexpert.common.service;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.tjexpert.common.domain.TjExpertApplyInfo;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertApplyInfoQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.ApplyProgressResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertApplyAdminResult;

/**
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午2:32:12 专家申请service
 */
public interface TjExpertApplyWriteService {

	/**
	 * 申请成为专家(升级或者申请审核需后台操作)
	 * 
	 * @param tjExpertApplyInfo
	 *            申请信息类(填入申请理由和战绩照片)
	 * @param realName
	 *            真实姓名
	 * @param certNo
	 *            身份证号
	 * @return
	 */
	public ModelResult<TjExpertApplyInfo> applyTobeExpert(
			TjExpertApplyInfo tjExpertApplyInfo, Long memberId,
			String realName, String certNo);

	/**
	 * 后台专家申请审核
	 * 
	 * @param checkStaus
	 *            审核状态
	 *            {@link com.aicai.jcob.tjexpert.common.domain.constant.TjExpertApplyCheckStatus}
	 * @param memberId
	 *            用户id
	 * @return
	 */
	public ModelResult<Boolean> checkExpert(Integer checkStaus,
			String cancelReason, Long memberId);

	public ModelResult<ApplyProgressResult> queryApplyProgressInfo(Long memberId);
	/**
	 * 后台分页查询专家申请信息
	 * 
	 * @param page
	 * @param tjExpertInfoQueryOption
	 * @return
	 */
	public PageResult<TjExpertApplyAdminResult> pageTjExpertApplyInfo(
			DataPage<TjExpertApplyInfo> page,
			TjExpertApplyInfoQueryOption tjExpertInfoQueryOption);

	/**
	 * 根据memberId查询专家申请信息
	 * 
	 * @param memberId
	 * @return
	 */
	public ModelResult<List<TjExpertApplyInfo>> queryExpertApllyByMemberId(Long memberId);

	/**
	 * 根据id查询专家申请详细信息,包括用户信息
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<TjExpertApplyAdminResult> queryExpertApplyInfoDetail(
			Long id);
	
}
