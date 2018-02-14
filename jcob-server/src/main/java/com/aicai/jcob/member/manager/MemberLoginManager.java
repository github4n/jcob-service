package com.aicai.jcob.member.manager;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.member.common.domain.MemberLogin;

public interface MemberLoginManager {

	MemberLogin insertMemberLogin(MemberLogin memberLogin);
	
//	MemberLogin updateMemberLogin(MemberLogin memberLogin);
	
	int updateMemberLoginStatusById(int id,int status);
	
	int updateMemberLoginLoginIdById(int id,String loginId);
	
	MemberLogin queryMemberLoginById(int id);
	
	MemberLogin queryMemberLoginByLoginTypeAndLoginId(int loginType,String loginId);
	
	List<MemberLogin> queryMemberLoginByThirdLoginTypeAndLoginId(int loginType,String loginId);
	
	ModelResult<MemberLogin> thirdIsAlreadyRegister(String loginId);
	
	List<MemberLogin> queryMemberLoginTokenByMemberId(Long memberId);
}
