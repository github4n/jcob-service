package com.aicai.jcob.member.manager;

import java.util.List;

import com.aicai.jcob.member.common.domain.MemberLoginToken;

public interface MemberLoginTokenManager {

	MemberLoginToken insertMemberLoginToken(MemberLoginToken token);
	
//	MemberLoginToken updateMemberLoginToken(MemberLoginToken token);
	
	int updateMemberLoginTokenStatus(long id,int status);
	
	int updateMemberLoginTokenToken(long id,String token);
	
	int updateMemberLoginTokenByMemberIdLoginTypeInValid(long memberId,int loginType);
	
	MemberLoginToken queryMemberLoginTokenById(long id);
	
	MemberLoginToken queryMemberLoginTokenByMemberIdAndMachineId(long memberId,String machineId,int loginType);
	
	MemberLoginToken queryMemberLoginTokenByTokenAndMachineId(String token,String machineId);
	
	List<MemberLoginToken> queryMemberLoginTokenByMemberId(long memberId);
	
	/**
	 * 查询用户非指定 loginType 的有效MemberLoginToken列表
	 * @param memberId
	 * @param loginType
	 * @return
	 */
	List<MemberLoginToken> queryMemberOtherLoginTypeMemberLoginToken(long memberId,int loginType);
}
