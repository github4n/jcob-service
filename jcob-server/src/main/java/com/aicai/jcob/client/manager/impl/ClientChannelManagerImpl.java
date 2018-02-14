package com.aicai.jcob.client.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.client.common.domain.ClientChannel;
import com.aicai.jcob.client.manager.ClientChannelManager;

public class ClientChannelManagerImpl implements ClientChannelManager {

	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericDao dao;

	@Override
	public ClientChannel createClientChannel(ClientChannel clientChannel) {
		dao.insertAndSetupId("ClientChannelMapper.insert", clientChannel);
		return clientChannel;
	}

	@Override
	public boolean deleteClientChannelById(Integer id) {
		return dao.updateByObj("ClientChannelMapper.deleteById", id) > 0;
	}

	@Override
	public boolean updateClientChannel(ClientChannel clientChannel) {
		updateChannelNameByParentNo(clientChannel.getChannelNo(), clientChannel.getChannelName());
		return dao.updateByObj("ClientChannelMapper.updateClientChannel", clientChannel) > 0;
	}

	@Override
	public ClientChannel queryByChannelNo(Integer channelNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("channelNo", channelNo);
		return dao.queryUnique("ClientChannelMapper.queryByChannelNo", paramMap);
	}

	@Override
	public ClientChannel queryById(Integer id) {
		return dao.queryUnique("ClientChannelMapper.queryById", Long.valueOf(id));
	}

	@Override
	public DataPage<ClientChannel> queryByPage(ClientChannel clientChannel, DataPage<ClientChannel> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", clientChannel);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return dao.queryPage("ClientChannelMapper.queryByPageCount", "ClientChannelMapper.queryByPage", map, page);
	}

	@Override
	public Integer queryMaxChannelNo() {
		Integer maxChannelNo = dao.queryUnique("ClientChannelMapper.queryMaxChannelNo", new HashMap<String, Object>());
		if (maxChannelNo == null) {
			maxChannelNo = 999999;
		}
		return maxChannelNo;
	}

	@Override
	public boolean isChannelExist(Integer channelNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelNo", channelNo);
		return dao.queryInt("ClientChannelMapper.isChannelExist", map) > 0;
	}

	@Override
	public boolean updateChannelNameByParentNo(Integer parentNo, String parentName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentNo", parentNo);
		map.put("parentName", parentName);
		return dao.update("ClientChannelMapper.updateChannelNameByParentNo", map) > 0;
	}

}
