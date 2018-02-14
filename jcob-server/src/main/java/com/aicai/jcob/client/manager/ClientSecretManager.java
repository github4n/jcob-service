package com.aicai.jcob.client.manager;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientSecretKey;

import java.util.Map;

public interface ClientSecretManager {
	
	int addClientSecret(ClientSecretKey key);
	
	DataPage<ClientSecretKey> selectByPage(Map<String, Object> map, DataPage<ClientSecretKey> dataPage);
	
	int update(ClientSecretKey key);
	
	ClientSecretKey selectOne(String sellChannel);

	int delete(Long[] arrays);
	
	ClientSecretKey selectOne(Long id);
}
