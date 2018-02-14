package com.aicai.jcob.member.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.member.common.domain.option.FeedbackSearchOption;
import com.aicai.jcob.member.common.result.MemberFeedbackResult;

public interface MemberFeedbackWriteService {

	/**
	 * 创建反馈
	 * 
	 * @param memberFeedback
	 * @return
	 */
	public ModelResult<MemberFeedback> createMemberFeedback(MemberFeedback memberFeedback);

	/**
	 * 分页查询用户反馈
	 * 
	 * @param searchOption
	 * @param page
	 * @return
	 */
	public PageResult<MemberFeedbackResult> queryMemberFeedbackByPage(FeedbackSearchOption searchOption, DataPage<MemberFeedbackResult> page);
}
