package com.aicai.jcob.membercharge.manager;

import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;

public interface MemberChargeChannelManager {

	MemberChargeChannel insertMemberChargeChannel(MemberChargeChannel channel);
	
	MemberChargeChannel queryMemberChargeChannelById(long id);
	
	MemberChargeChannel queryMemberChargeChannelByChannelIndex(int channelIndex);
}
