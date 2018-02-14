package com.aicai.jcob.client.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.client.common.constant.AppUseType;
import com.aicai.jcob.client.common.domain.ClientAppUseLog;
import com.aicai.jcob.client.common.domain.ClientAppUser;
import com.aicai.jcob.client.common.service.ClientAppWriteService;
import com.aicai.jcob.client.manager.ClientAppManager;

public class ClientAppWriteServiceImpl implements ClientAppWriteService {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ClientAppManager clientAppManager;
	@Override
	public ModelResult<ClientAppUseLog> saveClientAppUseLog(ClientAppUseLog clientAppUseLog) {
		ModelResult<ClientAppUseLog> modelResult = new ModelResult<ClientAppUseLog>();
		try {
			Integer startUpCount = clientAppManager.countStartUp(clientAppUseLog);
			if (startUpCount != null && startUpCount > 0) {
				clientAppUseLog.setAppUseType(AppUseType.startup.getIndex());
			}
			ClientAppUseLog result = clientAppManager.saveClientAppUseLog(clientAppUseLog);
			modelResult.setModel(result);
		} catch (Exception e) {
			logger.error("ClientAppWriteServiceImpl.saveClientAppUseLog调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<ClientAppUser> saveClientAppUser(ClientAppUser clientAppUser) {
		ModelResult<ClientAppUser> modelResult = new ModelResult<ClientAppUser>();
		try {
			ClientAppUser clientAppUserFromDb = clientAppManager.queryClientAppUser(clientAppUser);
			if (clientAppUserFromDb != null) {
				modelResult.setModel(clientAppUser);
			}else {
				clientAppUser = clientAppManager.saveClientAppUser(clientAppUser);
				modelResult.setModel(clientAppUser);
			}
		} catch (Exception e) {
			logger.error("ClientAppWriteServiceImpl.saveClientAppUser调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

}
