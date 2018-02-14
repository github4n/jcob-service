package com.aicai.jcob.client.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientSecretKey;
import com.aicai.jcob.client.common.service.ClientSecretWriteService;
import com.aicai.jcob.client.manager.ClientSecretManager;

import javax.annotation.Resource;
import java.util.Map;


public class ClientSecretWriteServiceImpl implements ClientSecretWriteService {

	@Resource
	private ClientSecretManager clientSecretManager ;
	
	@Override
	public ModelResult<ClientSecretKey> addClientSecret(ClientSecretKey key) {
		ClientSecretKey clientSecret = clientSecretManager.selectOne(key.getSellChannel());
		if(clientSecret !=null){
			return new ModelResult<ClientSecretKey>().withError("addException", "渠道号已经存在");
		}
		clientSecretManager.addClientSecret(key);
		return new ModelResult<ClientSecretKey>(key);
	}

	@Override
	public ModelResult<DataPage<ClientSecretKey>> queryByPage(Map<String, Object> map, DataPage<ClientSecretKey> dataPage) {
		ModelResult<DataPage<ClientSecretKey>> result = new ModelResult<DataPage<ClientSecretKey>>();
		result.setReadFromCache(false);
		result.setModel(clientSecretManager.selectByPage(map, dataPage));
		return result;
	}

	@Override
	public ModelResult<Boolean> deleteInId(Long[] ids) {
		ModelResult<Boolean> result = new ModelResult<Boolean>();
		try {
			int resultCount = clientSecretManager.delete(ids);
			if(resultCount == 0){
				result.setModel(Boolean.FALSE);
				result.withError("deleteException", "未删除一条信息");
				return result ;
			}
			
			if(resultCount == ids.length){
				result.setModel(Boolean.TRUE);
				return result ;
			}
			result.withError("deleteException", "只删除了"+resultCount+"条信息");
		} catch (RuntimeException e) {
			result.withError("runtimeException", e.getMessage());
		}
		return result;
	}

	@Override
	public ModelResult<ClientSecretKey> update(ClientSecretKey key) {
		int count = clientSecretManager.update(key);
		if(count ==1){
			return new ModelResult<ClientSecretKey>(key);
		}
		return new ModelResult<ClientSecretKey>().withError("updateException", "更新失败");
	}

	@Override
	public ModelResult<ClientSecretKey> queryOne(String sellChannel) {
		ModelResult<ClientSecretKey> result = new ModelResult<ClientSecretKey>();
		result.setReadFromCache(false);
		
		ClientSecretKey clientSecretKey = clientSecretManager.selectOne(sellChannel);
		if(clientSecretKey == null ){
			return new ModelResult<ClientSecretKey>().withError("queryException", "没有数据");
		}
		result.setModel(clientSecretKey);
		
		return result;
	}

	@Override
	public ModelResult<ClientSecretKey> queryOne(Long id) {
		ModelResult<ClientSecretKey> result = new ModelResult<ClientSecretKey>();
		result.setReadFromCache(false);
		
		ClientSecretKey clientSecretKey = clientSecretManager.selectOne(id);
		if(clientSecretKey == null ){
			return new ModelResult<ClientSecretKey>().withError("queryException", "没有数据");
		}
		result.setModel(clientSecretKey);
		
		return result;
	}

}
