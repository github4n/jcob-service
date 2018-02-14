package com.aicai.jcob.member.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.member.common.domain.MemberLoginToken;
import com.aicai.jcob.member.common.domain.constant.MemberLoginTokenStatus;
import com.aicai.jcob.member.manager.MemberLoginTokenManager;

public class MemberLoginTokenManagerImpl implements MemberLoginTokenManager {

	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberDbDao;
	
	@Override
	public MemberLoginToken insertMemberLoginToken(MemberLoginToken token) {
		memberDbDao.insertAndSetupId("MemberLoginTokenMapper.insertSelective", token);
		return token;
	}

	/*@Override
	public MemberLoginToken updateMemberLoginToken(MemberLoginToken token) {
		memberDbDao.updateByObj("MemberLoginTokenMapper.updateByPrimaryKeySelective", token);
		return token;
	}*/

	@Override
	public MemberLoginToken queryMemberLoginTokenById(long id) {
		return (MemberLoginToken)memberDbDao.queryUnique("MemberLoginTokenMapper.selectByPrimaryKey", id);
	}

	@Override
	public MemberLoginToken queryMemberLoginTokenByMemberIdAndMachineId(
			long memberId, String machineId,int loginType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", memberId);
		param.put("machineId", machineId);
		param.put("loginType", loginType);
		return (MemberLoginToken)memberDbDao.queryUnique("MemberLoginTokenMapper.queryMemberLoginTokenByMemberIdAndMachineId",param);
	}

	@Override
	public List<MemberLoginToken> queryMemberLoginTokenByMemberId(long memberId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", memberId);
		return memberDbDao.queryList("MemberLoginTokenMapper.queryMemberLoginTokenByMemberId", param);
	}

	@Override
	public int updateMemberLoginTokenStatus(long id, int status) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("status", status);
		return memberDbDao.update("MemberLoginTokenMapper.updateMemberLoginTokenStatus", param);
	}

	@Override
	public int updateMemberLoginTokenToken(long id, String token) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("token", token);
		param.put("status", MemberLoginTokenStatus.valid);
		return memberDbDao.update("MemberLoginTokenMapper.updateMemberLoginTokenToken", param);
	}

	@Override
	public MemberLoginToken queryMemberLoginTokenByTokenAndMachineId(
			String token, String machineId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("token", token);
		param.put("machineId", machineId);
		return (MemberLoginToken)memberDbDao.queryUnique("MemberLoginTokenMapper.queryMemberLoginTokenByTokenAndMachineId",param);
	}

	@Override
	public int updateMemberLoginTokenByMemberIdLoginTypeInValid(long memberId,
			int loginType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", memberId);
		param.put("loginType", loginType);
		param.put("status", MemberLoginTokenStatus.valid);
		param.put("newStatus", MemberLoginTokenStatus.invalid);
		return memberDbDao.update("MemberLoginTokenMapper.updateMemberLoginTokenByMemberIdLoginTypeInValid", param);
	}

	@Override
	public List<MemberLoginToken> queryMemberOtherLoginTypeMemberLoginToken(
			long memberId, int loginType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", memberId);
		param.put("loginType", loginType);
		return memberDbDao.queryList("MemberLoginTokenMapper.queryMemberOtherLoginTypeMemberLoginToken", param);
	}

}
