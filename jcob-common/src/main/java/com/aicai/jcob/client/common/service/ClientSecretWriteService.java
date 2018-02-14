package com.aicai.jcob.client.common.service;


import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientSecretKey;

import java.util.Map;

/**
 * 
 * 客户端密钥管理
 * @note admin 后台使用
 */
public interface ClientSecretWriteService {

	ModelResult<ClientSecretKey> addClientSecret(ClientSecretKey key);
	
	ModelResult<DataPage<ClientSecretKey>> queryByPage(Map<String, Object> map, DataPage<ClientSecretKey> dataPage);
	
	ModelResult<Boolean> deleteInId(Long[] ids);
	
	ModelResult<ClientSecretKey> update(ClientSecretKey key);
	
	ModelResult<ClientSecretKey> queryOne(String sellChannel);
	
	ModelResult<ClientSecretKey> queryOne(Long id);
	
}
