package com.aicai.jcob.member.manager;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.MemberOperLog;
import com.aicai.jcob.member.common.domain.option.MemberOperLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperLogQueryOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;

public interface MemberOperLogManager {

	MemberOperLog insertMemberOperLog(MemberOperLog operLog);
	
//	MemberOperLog updateMemberOperLog(MemberOperLog operLog);
	
	MemberOperLog queryMemberOperLogById(long id);
	
	List<MemberOperLog> queryMemberOperLogByMemberAndOperTypeCreateTime(MemberOperLogOption option);
	
	public void asyncSaveMemberOperLog(Long memberId,MemberOperOption memberOperOption,String remark);
	
	DataPage<TjAttentionLogAdminResult> pageAttentionOperLog(MemberOperLogQueryOption option,DataPage<TjAttentionLogAdminResult> page) ;
	
	public ModelResult<String> getMemberRegisterUserIp(long memberId);
	
	public void saveSystemAutoLoginLog(long memberId);
}
