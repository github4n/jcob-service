package com.aicai.jcob.member.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.member.common.domain.option.FeedbackSearchOption;
import com.aicai.jcob.member.common.result.MemberFeedbackResult;
import com.aicai.jcob.member.common.service.MemberFeedbackWriteService;
import com.aicai.jcob.member.manager.MemberFeedbackManager;

public class MemberFeedbackWriteServiceImpl implements MemberFeedbackWriteService {
	private transient Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("memberFeedbackManagerImpl")
	private MemberFeedbackManager memberFeedbackManager;

	@Override
	public ModelResult<MemberFeedback> createMemberFeedback(MemberFeedback memberFeedback) {
		ModelResult<MemberFeedback> result = new ModelResult<MemberFeedback>();
		try {
			result.setModel(memberFeedbackManager.createMemberFeedback(memberFeedback));
		} catch (Exception e) {
			result.withError("err", "创建反馈出错");
			logger.error("创建反馈出错", e);
		}
		return result;
	}

	@Override
	public PageResult<MemberFeedbackResult> queryMemberFeedbackByPage(FeedbackSearchOption searchOption, DataPage<MemberFeedbackResult> page) {
		PageResult<MemberFeedbackResult> result = new PageResult<MemberFeedbackResult>();
		try {
			result.setPage(memberFeedbackManager.queryMemberFeedbackByPage(searchOption, page));
		} catch (Exception e) {
			result.withError("err", "查询用户反馈出错");
			logger.error("查询用户反馈出错", e);
		}
		return result;
	}

}
