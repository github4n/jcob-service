package com.aicai.jcob.membercharge.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.manager.MemberChargeReturnLogManager;

public class MemberChargeReturnLogManagerImpl implements
		MemberChargeReturnLogManager {

	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberChargeLoReturngDao;
	
	@Override
	public MemberChargeReturnLog insertMemberChargeReturnLog(
			MemberChargeReturnLog returnLog) {		
		memberChargeLoReturngDao.insertAndSetupId("MemberChargeReturnLogMapper.insertSelective", returnLog);
		return returnLog;
	}

	@Override
	public MemberChargeReturnLog queryMemberChargeReturnLogById(long id) {
		return (MemberChargeReturnLog)memberChargeLoReturngDao.queryUnique("MemberChargeReturnLogMapper.selectByPrimaryKey", id);
	}

	@Override
	public MemberChargeReturnLog queryMemberChargeReturnLogByChargeId(long chargeId) {
		return (MemberChargeReturnLog)memberChargeLoReturngDao.queryUnique("MemberChargeReturnLogMapper.queryMemberChargeReturnLogByChargeId", chargeId);
	}

}
