package com.aicai.jcob.client.manager;

import com.aicai.jcob.client.common.domain.ClientAppUseLog;
import com.aicai.jcob.client.common.domain.ClientAppUser;

/**
 * app相关辅助操作管理
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年4月12日
 */
public interface ClientAppManager {

	/**
	 * 保存app操作信息
	 * @param clientAppUseLog
	 * @return
	 */
	public ClientAppUseLog saveClientAppUseLog(ClientAppUseLog clientAppUseLog);
	/**
	 * 保存app和用户的相对应的信息
	 * @param clientAppUser
	 * @return
	 */
	public ClientAppUser  saveClientAppUser(ClientAppUser clientAppUser);
	/**
	 * 统计启动次数（用来判断是否第一次启动）
	 * @param clientAppUseLog
	 * @return
	 */
	public Integer countStartUp(ClientAppUseLog clientAppUseLog);
	/**
	 * 统计设备对应用户信息个数，用来排重
	 * @param clientAppUser
	 * @return
	 */
	public ClientAppUser queryClientAppUser(ClientAppUser clientAppUser);
}
