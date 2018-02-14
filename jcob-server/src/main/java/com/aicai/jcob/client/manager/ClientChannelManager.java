package com.aicai.jcob.client.manager;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientChannel;

public interface ClientChannelManager {
	/**
	 * 创建渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public ClientChannel createClientChannel(ClientChannel clientChannel);

	/**
	 * 创建渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public boolean deleteClientChannelById(Integer id);

	/**
	 * 更新渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public boolean updateClientChannel(ClientChannel clientChannel);

	/**
	 * 按渠道号查找
	 * 
	 * @param channelNo
	 * @return
	 */
	public ClientChannel queryByChannelNo(Integer channelNo);

	/**
	 * 更新渠道名称
	 * 
	 * @param parentName
	 * @return
	 */
	public boolean updateChannelNameByParentNo(Integer parentNo, String parentName);

	/**
	 * 按ID查找
	 * 
	 * @param id
	 * @return
	 */
	public ClientChannel queryById(Integer id);

	/**
	 * 分页查找
	 * 
	 * @param clientChannel
	 * @return
	 */
	public DataPage<ClientChannel> queryByPage(ClientChannel clientChannel, DataPage<ClientChannel> page);

	/**
	 * 查询最大渠道号
	 * 
	 * @return
	 */
	public Integer queryMaxChannelNo();

	/**
	 * 判断渠道号是否存在
	 * 
	 * @param channelNo
	 * @return
	 */
	public boolean isChannelExist(Integer channelNo);
}
