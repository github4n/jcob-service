package com.aicai.jcob.member.manager.impl;

import java.util.HashMap;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.member.common.domain.MemberLogin;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.manager.MemberLoginManager;

public class MemberLoginManagerImpl implements MemberLoginManager {

	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberDbDao;
	
	@Override
	public MemberLogin insertMemberLogin(MemberLogin memberLogin) {
		memberDbDao.insertAndSetupId("MemberLoginMapper.insertSelective", memberLogin);
		return memberLogin;
	}

	/*@Override
	public MemberLogin updateMemberLogin(MemberLogin memberLogin) {
		memberDbDao.updateByObj("MemberLoginMapper.updateByPrimaryKeySelective",memberLogin);
		return memberLogin;
	}*/

	@Override
	public MemberLogin queryMemberLoginById(int id) {
		return (MemberLogin)memberDbDao.queryUnique("MemberLoginMapper.selectByPrimaryKey", id);
	}

	@Override
	public MemberLogin queryMemberLoginByLoginTypeAndLoginId(
			int loginType, String loginId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("loginType", loginType);
		param.put("loginId", loginId);
		return memberDbDao.queryUnique("MemberLoginMapper.queryMemberLoginByLoginTypeAndLoginId", param);
	}

	@Override
	public int updateMemberLoginStatusById(int id, int status) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("status", status);
		return memberDbDao.update("MemberLoginMapper.updateMemberLoginStatusById", param);
	}

	@Override
	public int updateMemberLoginLoginIdById(int id, String loginId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("loginId", loginId);
		return memberDbDao.update("MemberLoginMapper.updateMemberLoginLoginIdById", param);
	}

	@Override
	public ModelResult<MemberLogin> thirdIsAlreadyRegister(String loginId) {
		ModelResult<MemberLogin> result = new ModelResult<MemberLogin>();
		
		MemberLogin memberLogin = this.queryMemberLoginByLoginTypeAndLoginId(
				MemberLoginType.aici_login_third.getIndex(), loginId);
		
		result.setModel(memberLogin);
		return result;
	}

	@Override
	public List<MemberLogin> queryMemberLoginTokenByMemberId(Long memberId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", memberId);
		return memberDbDao.queryList("MemberLoginMapper.queryMemberLoginTokenByMemberId", param);
	}

	@Override
	public List<MemberLogin> queryMemberLoginByThirdLoginTypeAndLoginId(
			int loginType, String loginId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("loginType", loginType);
		param.put("loginId", loginId);
		return memberDbDao.queryList("MemberLoginMapper.queryMemberLoginByLoginTypeAndLoginId", param);
	}

}
