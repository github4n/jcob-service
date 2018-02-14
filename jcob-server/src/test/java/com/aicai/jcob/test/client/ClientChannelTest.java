package com.aicai.jcob.test.client;

import javax.annotation.Resource;

import org.junit.Test;

import com.aicai.jcob.client.common.domain.ClientChannel;
import com.aicai.jcob.client.manager.ClientChannelManager;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.test.TestBase;

public class ClientChannelTest extends TestBase {
	@Resource
	private ClientChannelManager clientChannelManager;

	@Test
	public void createClientChannel() {
		ClientChannel clientChannel = new ClientChannel();
		clientChannel.setChannelName("测试");
		clientChannel.setChannelNo(10000);
		clientChannel.setClientType(ClientType.h5.getIndex());
		clientChannelManager.createClientChannel(clientChannel);
	}

	@Test
	public void updateClientChannel() {
		ClientChannel clientChannel = new ClientChannel();
		clientChannel.setId(1);
		clientChannel.setChannelNo(1000000);
		clientChannel.setChannelName("渠道1123");
		clientChannelManager.updateClientChannel(clientChannel);
	}

	@Test
	public void deleteClientChannelById() {
		clientChannelManager.deleteClientChannelById(1);
	}

	@Test
	public void queryMaxChannelNo() {
		clientChannelManager.queryMaxChannelNo();
	}

}
