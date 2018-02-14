package com.aicai.jcob.member.manager;

import java.util.List;

import com.aicai.jcob.member.common.domain.MemberInfoLog;
import com.aicai.jcob.member.common.domain.option.MemberInfoLogOption;

public interface MemberInfoLogManager {

	MemberInfoLog insertMemberInfoLog(MemberInfoLog infoLog);
	
	MemberInfoLog queryMemberInfoLogById(long id);
	
//	MemberInfoLog updateMemberInfoLog(MemberInfoLog infoLog);
	
	/**
	 * 
	 * @param option
	 * memberId 用户id
	 * beginTime 开始时间
	 * endTime 结束时间
	 * newInfo 用户信息修改后的值
	 * @return
	 */
	List<MemberInfoLog> queryMemberInfoLogWithOption(MemberInfoLogOption option);
	
	/**
	 * 查询newValue是否非当前用户也有使用的历史记录
	 * @param memberId 当前用户的ID
	 * @param newValue 新修改的值
	 * @return
	 */
	List<MemberInfoLog> queryNotCurrentMemberInfoLog(Long memberId,String newValue);
}
