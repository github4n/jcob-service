package com.aicai.jcob.client.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.client.common.domain.ClientChannel;
import com.aicai.jcob.client.common.service.ClientChannelWriteService;
import com.aicai.jcob.client.manager.ClientChannelManager;

public class ClientChannelWriteServiceImpl implements ClientChannelWriteService {

	@Autowired
	@Qualifier("clientChannelManagerImpl")
	private ClientChannelManager clientChannelManager;

	@Override
	public ModelResult<ClientChannel> createClientChannel(ClientChannel clientChannel) {
		ModelResult<ClientChannel> modelResult = new ModelResult<ClientChannel>();
		if (StringUtils.isBlank(clientChannel.getChannelName())) {
			modelResult.withError("channel name.is.null", "渠道名称必须输入");
			return modelResult;
		}
		Integer channelNo = clientChannel.getChannelNo();
		if (clientChannel.getChannelNo() == null || channelNo == 0) {
			modelResult.withError("channel no.is.null", "渠道号必须输入");
			return modelResult;
		}
		boolean exist = clientChannelManager.isChannelExist(channelNo);
		if (exist) {
			modelResult.withError("channel no.is.exist", "渠道号已经存在");
			return modelResult;
		}
		modelResult.setModel(clientChannelManager.createClientChannel(clientChannel));
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> deleteClientChannelById(Integer id) {
		return new ModelResult<Boolean>(clientChannelManager.deleteClientChannelById(id));
	}

	@Override
	public ModelResult<Boolean> updateClientChannel(ClientChannel clientChannel) {
		return new ModelResult<Boolean>(clientChannelManager.updateClientChannel(clientChannel));
	}

	@Override
	public ModelResult<ClientChannel> queryByChannelNo(Integer channelNo) {
		return new ModelResult<ClientChannel>(clientChannelManager.queryByChannelNo(channelNo));
	}

	@Override
	public ModelResult<ClientChannel> queryById(Integer id) {
		return new ModelResult<ClientChannel>(clientChannelManager.queryById(id));
	}

	@Override
	public PageResult<ClientChannel> queryByPage(ClientChannel clientChannel, DataPage<ClientChannel> page) {
		PageResult<ClientChannel> pageResult = new PageResult<ClientChannel>();
		pageResult.setPage(clientChannelManager.queryByPage(clientChannel, page));
		return pageResult;
	}

	@Override
	public ModelResult<Integer> queryMaxChannelNo() {
		return new ModelResult<Integer>(clientChannelManager.queryMaxChannelNo());
	}

}
