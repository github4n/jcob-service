package com.aicai.jcob.membercharge.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.manager.MemberChargeChannelManager;
import com.aicai.jcob.membercharge.manager.MemberChargeWayManager;

public class MemberChargeWayManagerImpl implements MemberChargeWayManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberChargeWayDao;
	
	@Autowired
	@Qualifier("memberChargeChannelManagerImpl")
	private MemberChargeChannelManager memberChargeChannelManager;
	
	@Override
	public MemberChargeWay insertMemberChargeWay(MemberChargeWay chargeWay) {
		memberChargeWayDao.insertAndSetupId("MemberChargeWayMapper.insertSelective", chargeWay);
		return chargeWay;
	}

	@Override
	public MemberChargeWay queryMemberChargeWayById(long id) {
		return (MemberChargeWay)memberChargeWayDao.queryUnique("MemberChargeWayMapper.selectByPrimaryKey", id);
	}

	@Override
	public MemberChargeWay queryMemberChargeWayByChargeWayIndexClientType(
			int chargeWayIndex, int clientType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("chargeWayIndex", chargeWayIndex);
		param.put("clientType", clientType);
		return (MemberChargeWay)memberChargeWayDao.queryUnique("MemberChargeWayMapper.queryMemberChargeWayByChargeWayIndexClientType", param);
	}

	@Override
	public int updateMemberChargeWayFeeFlag(long id, int feeFlag) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("feeFlag", feeFlag);
		return memberChargeWayDao.update("MemberChargeWayMapper.updateChargeWayFeeFlag", param);
	}

	@Override
	public MemberChargeChannel queryMemberChargeChannelByChargeWayClientType(
			int chargeWayIndex, int clientType) {
		MemberChargeWay chargeWay = this.queryMemberChargeWayByChargeWayIndexClientType(chargeWayIndex, clientType);
		if(null == chargeWay) {
			logger.error("chargeWay is null,chargeWayIndex : [{}],clientType : []",chargeWayIndex,clientType);
			return null;
		}
		return memberChargeChannelManager.queryMemberChargeChannelByChannelIndex(chargeWay.getChargeChannelIndex());
	}

}
