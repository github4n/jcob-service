package com.aicai.jcob.client.manager.impl;


import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.client.common.domain.ClientSecretKey;
import com.aicai.jcob.client.manager.ClientSecretManager;
import com.aicai.jcob.common.utils.JSONUtils;

import javax.annotation.Resource;
import java.util.Map;


public class ClientSecretManagerImpl implements ClientSecretManager {

	@Resource(name = "tjplanDbDao")
	private GenericDao dao;

	
	@Override
	public int addClientSecret(ClientSecretKey key) {
		return dao.insertAndReturnAffectedCount("ClientSecretKeyMapper.insert", key);
	}

	@Override
	public DataPage<ClientSecretKey> selectByPage(Map<String, Object> map, DataPage<ClientSecretKey> dataPage) {
		map.put("startIndex", (dataPage.getPageNo()-1) * dataPage.getPageSize());
		map.put("pageSize", dataPage.getPageSize());
		return dao.queryPage("ClientSecretKeyMapper.countByPage", "ClientSecretKeyMapper.selectByPage", map, dataPage);
	}

	@Override
	public int update(ClientSecretKey key) {
		return dao.update("ClientSecretKeyMapper.update", JSONUtils.object2Map(key));
	}

	@Override
	public ClientSecretKey selectOne(String sellChannel) {
		return dao.queryUnique("ClientSecretKeyMapper.selectOne", sellChannel);
	}

	@Override
	public int delete(Long[] arrays) {
		return dao.updateByObj("ClientSecretKeyMapper.delete", arrays);
	}

	@Override
	public ClientSecretKey selectOne(Long id) {
		return dao.queryUnique("ClientSecretKeyMapper.selectOneById", id);
	}
}
