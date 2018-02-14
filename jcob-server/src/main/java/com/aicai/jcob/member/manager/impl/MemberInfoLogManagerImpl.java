package com.aicai.jcob.member.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.member.common.domain.MemberInfoLog;
import com.aicai.jcob.member.common.domain.option.MemberInfoLogOption;
import com.aicai.jcob.member.manager.MemberInfoLogManager;

public class MemberInfoLogManagerImpl implements MemberInfoLogManager {

//	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberDbDao;
	
	@Override
	public MemberInfoLog insertMemberInfoLog(MemberInfoLog infoLog) {
		memberDbDao.insertAndSetupId("MemberInfoLogMapper.insertSelective", infoLog);
		return infoLog;
	}

	@Override
	public MemberInfoLog queryMemberInfoLogById(long id) {
		return (MemberInfoLog)memberDbDao.queryUnique("MemberInfoLogMapper.selectByPrimaryKey", id);
	}

	/*@Override
	public MemberInfoLog updateMemberInfoLog(MemberInfoLog infoLog) {
		memberDbDao.updateByObj("MemberInfoLogMapper.updateByPrimaryKeySelective",infoLog);
		return infoLog;
	}*/

	@Override
	public List<MemberInfoLog> queryMemberInfoLogWithOption(
			MemberInfoLogOption option) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(null != option.getMemberId()) param.put("memberId", option.getMemberId());
		if(null != option.getBeginTime()) param.put("beginTime", option.getBeginTime());
		if(null != option.getEndTime()) param.put("endTime", option.getEndTime());
		if(StringUtils.isNotBlank(option.getNewInfo())) param.put("newInfo", option.getNewInfo());
		
		return memberDbDao.queryList("MemberInfoLogMapper.queryMemberInfoLogWithOption", param);
	}

	@Override
	public List<MemberInfoLog> queryNotCurrentMemberInfoLog(Long memberId,
			String newValue) {
		Map<String,Object> param = new HashMap<String,Object>();
		
		if(null != memberId) param.put("memberId", memberId);
		if(StringUtils.isNotBlank(newValue)) param.put("newInfo", newValue);
		
		return memberDbDao.queryList("MemberInfoLogMapper.queryNotCurrentMemberInfoLog", param);
	}

}
