package com.aicai.jcob.membercharge.manager.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.common.domain.BankLimitType;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.member.common.domain.option.MemberUseChargeWayOption;
import com.aicai.jcob.membercharge.manager.MemberUseChargeWayManager;
import com.alibaba.fastjson.JSON;

public class MemberUseChargeWayManagerImpl implements MemberUseChargeWayManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberChargeLogDao;
	
	@Override
	public MemberUseChargeWay insertMemberUseChargeWay(MemberUseChargeWay way) {
		memberChargeLogDao.insertAndSetupId("MemberUseChargeWayMapper.insertSelective", way);
		return way;
	}

	@Override
	public MemberUseChargeWay queryMemberUseChargeWayWithOption(MemberUseChargeWayOption option) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("memberId", option.getMemberId());
		param.put("chargeIndex", option.getChargeIndex());
		param.put("bankCode", option.getBankCode());
		param.put("bankCard", option.getBankCard());
		return memberChargeLogDao.queryUnique("MemberUseChargeWayMapper.queryMemberUseChargeWayWithOption", param);
	}

	@Override
	public void saveMemberUseChargeWay(MemberUseChargeWay way) {
		logger.info("saveMemberUseChargeWay begin , way :[{}]",JSON.toJSON(way));
		MemberUseChargeWayOption option = new MemberUseChargeWayOption(way.getMemberId(), way.getChargeIndex());
		option.setClientType(way.getClientType());
		option.setBankCode(way.getBankCode());
		option.setBankCard(way.getBankCard());
		MemberUseChargeWay usedWay = this.queryMemberUseChargeWayWithOption(option);
		logger.info("usedWay : [{}]",JSON.toJSON(usedWay));
		if(null == usedWay) 
			this.insertMemberUseChargeWay(way);
		else
			this.updateMemberUseChargeWayUpdateTime(usedWay.getId(), Calendar.getInstance());
	}

	@Override
	public int updateMemberUseChargeWayUpdateTime(Long id, Calendar updateTime) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("updateTime", updateTime);
		return memberChargeLogDao.updateByObj("MemberUseChargeWayMapper.updateMemberUseChargeWayUpdateTime", param);
	}

	@Override
	public ModelResult<List<MemberUseChargeWay>> queryMemberUseChargeWayTop5(
			Long memberId) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("memberId", memberId);
		
		List<MemberUseChargeWay> memberUsechargeWays = memberChargeLogDao.queryList("MemberUseChargeWayMapper.queryMemberUseChargeWayTop5", param);
		for(MemberUseChargeWay way : memberUsechargeWays){
			BankLimitType limitType = BankLimitType.getBankLimitType(way.getBankCode(), String.valueOf(way.getPayType()));
			if(null != limitType) way.setSingleLimitAmount(limitType.getSingleLimit());
		}
		
		return new ModelResult<List<MemberUseChargeWay>>(memberUsechargeWays);
	}

	@Override
	public int updateMemberUseChargeWayStatusFailById(long id,long memberId) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("memberId", memberId);
		return memberChargeLogDao.updateByObj("MemberUseChargeWayMapper.updateMemberUseChargeWayStatusFailById", param);
	}


}
