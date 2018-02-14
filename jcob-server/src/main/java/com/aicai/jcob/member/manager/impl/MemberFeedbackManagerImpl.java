package com.aicai.jcob.member.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.member.common.domain.option.FeedbackSearchOption;
import com.aicai.jcob.member.common.result.MemberFeedbackResult;
import com.aicai.jcob.member.manager.MemberFeedbackManager;

public class MemberFeedbackManagerImpl implements MemberFeedbackManager {

    @Autowired
    @Qualifier("memberDbDao")
    private GenericDao dao;

    @Override
    public MemberFeedback createMemberFeedback(MemberFeedback memberFeedback) {
        dao.insertAndSetupId("memberFeedbackMapper.insert", memberFeedback);
        return memberFeedback;
    }

    @Override
    public DataPage<MemberFeedbackResult> queryMemberFeedbackByPage(FeedbackSearchOption searchOption,
            DataPage<MemberFeedbackResult> page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", searchOption);
        map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
        map.put("pageSize", page.getPageSize());
        return dao.queryPage("memberFeedbackMapper.queryByPageCount",
                "memberFeedbackMapper.queryByPage", map, page);
    }

}
