package com.aicai.jcob.member.manager;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.member.common.domain.option.FeedbackSearchOption;
import com.aicai.jcob.member.common.result.MemberFeedbackResult;

public interface MemberFeedbackManager {

    public MemberFeedback createMemberFeedback(MemberFeedback memberFeedback);

    public DataPage<MemberFeedbackResult> queryMemberFeedbackByPage(FeedbackSearchOption searchOption,
            DataPage<MemberFeedbackResult> page);
}
