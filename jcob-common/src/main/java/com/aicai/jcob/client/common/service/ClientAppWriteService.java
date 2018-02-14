package com.aicai.jcob.client.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.client.common.domain.ClientAppUseLog;
import com.aicai.jcob.client.common.domain.ClientAppUser;

/**
 * app相关辅助操作管理接口
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年4月12日
 */
public interface ClientAppWriteService {

	/**
	 * 保存app操作信息(内部会自动判断设置appUseType)
	 * @param clientAppUseLog
	 * @return
	 */
	public ModelResult<ClientAppUseLog> saveClientAppUseLog(ClientAppUseLog clientAppUseLog);
	/**
	 * 保存app和用户的相对应的信息（存在就直接返回）
	 * @param clientAppUser
	 * @return
	 */
	public ModelResult<ClientAppUser>  saveClientAppUser(ClientAppUser clientAppUser);

}
