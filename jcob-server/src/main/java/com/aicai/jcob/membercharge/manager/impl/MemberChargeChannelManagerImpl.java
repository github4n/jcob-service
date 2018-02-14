package com.aicai.jcob.membercharge.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.manager.MemberChargeChannelManager;

public class MemberChargeChannelManagerImpl implements
		MemberChargeChannelManager {

	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberChargeChannelDao;
	
	@Override
	public MemberChargeChannel insertMemberChargeChannel(
			MemberChargeChannel channel) {
		memberChargeChannelDao.insertAndSetupId("MemberChargeChannelMapper.insertSelective", channel);
		return channel;
	}

	@Override
	public MemberChargeChannel queryMemberChargeChannelById(long id) {
		return memberChargeChannelDao.queryUnique("MemberChargeChannelMapper.selectByPrimaryKey", id);
	}

	@Override
	public MemberChargeChannel queryMemberChargeChannelByChannelIndex(
			int channelIndex) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("chargeChannelIndex", channelIndex);
		return memberChargeChannelDao.queryUnique("MemberChargeChannelMapper.queryMemberChargeChannelByChannelIndex", param);
	}

}
