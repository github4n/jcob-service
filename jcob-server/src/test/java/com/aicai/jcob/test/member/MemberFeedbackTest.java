package com.aicai.jcob.test.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.member.common.domain.option.FeedbackSearchOption;
import com.aicai.jcob.member.common.result.MemberFeedbackResult;
import com.aicai.jcob.member.manager.MemberFeedbackManager;
import com.aicai.jcob.test.TestBase;

public class MemberFeedbackTest extends TestBase {

	@Autowired
	@Qualifier("memberFeedbackManagerImpl")
	private MemberFeedbackManager memberFeedbackManager;

	@Test
	public void createMemberFeedback() {
		MemberFeedback memberFeedback = new MemberFeedback();
		memberFeedback.setMemberId(1L);
		memberFeedback.setClientType(1);
		memberFeedback.setContent("ABC");
		memberFeedbackManager.createMemberFeedback(memberFeedback);
		System.out.println(memberFeedback.toString());
	}

	@Test
	public void queryMemberFeedbackByPage() {
		DataPage<MemberFeedbackResult> page = new DataPage<MemberFeedbackResult>();
		FeedbackSearchOption searchOption = new FeedbackSearchOption();
		searchOption.setNickName("小土");
		searchOption.setLevel(-1);
		page = memberFeedbackManager.queryMemberFeedbackByPage(searchOption, page);
		for (MemberFeedbackResult result : page.getDataList()) {
			System.out.println(result.toString());
		}
	}

}
