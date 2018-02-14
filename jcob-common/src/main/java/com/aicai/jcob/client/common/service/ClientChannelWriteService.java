package com.aicai.jcob.client.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientChannel;

public interface ClientChannelWriteService {

	/**
	 * 创建渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public ModelResult<ClientChannel> createClientChannel(ClientChannel clientChannel);

	/**
	 * 创建渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public ModelResult<Boolean> deleteClientChannelById(Integer id);

	/**
	 * 更新渠道号
	 * 
	 * @param ClientChannel
	 * @return
	 */
	public ModelResult<Boolean> updateClientChannel(ClientChannel clientChannel);

	/**
	 * 按渠道号查找
	 * 
	 * @param channelNo
	 * @return
	 */
	public ModelResult<ClientChannel> queryByChannelNo(Integer channelNo);

	/**
	 * 查询最大的渠道号,默认从100000开始
	 * 
	 * @param channelNo
	 * @return
	 */
	public ModelResult<Integer> queryMaxChannelNo();

	/**
	 * 按ID查找
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<ClientChannel> queryById(Integer id);

	/**
	 * 分页查找
	 * 
	 * @param clientChannel
	 * @return
	 */
	public PageResult<ClientChannel> queryByPage(ClientChannel clientChannel, DataPage<ClientChannel> page);
}
