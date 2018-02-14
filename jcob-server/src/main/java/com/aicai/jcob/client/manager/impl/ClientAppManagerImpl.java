package com.aicai.jcob.client.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.client.common.domain.ClientAppUseLog;
import com.aicai.jcob.client.common.domain.ClientAppUser;
import com.aicai.jcob.client.manager.ClientAppManager;

public class ClientAppManagerImpl implements ClientAppManager {
	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Override
	public ClientAppUseLog saveClientAppUseLog(ClientAppUseLog clientAppUseLog) {
		memberDbDao.insertAndSetupId("ClientAppUseLogMapper.saveClientAppUseLog", clientAppUseLog);
		return clientAppUseLog;
	}

	@Override
	public ClientAppUser saveClientAppUser(ClientAppUser clientAppUser) {
		memberDbDao.insertAndSetupId("ClientAppUserMapper.saveClientAppUser", clientAppUser);
		return clientAppUser;
	}

	@Override
	public Integer countStartUp(ClientAppUseLog clientAppUseLog) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("imei", clientAppUseLog.getImei());
		map.put("channel", clientAppUseLog.getChannel());
		map.put("clientType", clientAppUseLog.getClientType());
		map.put("version", clientAppUseLog.getVersion());
		return memberDbDao.queryCount("ClientAppUseLogMapper.countAppUseLog", map);
	}

	@Override
	public ClientAppUser queryClientAppUser(ClientAppUser clientAppUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("imei", clientAppUser.getImei());
		map.put("memberId", clientAppUser.getMemberId());
		return (ClientAppUser) memberDbDao.queryObject("ClientAppUserMapper.queryClientAppUser", map);
	}

}
