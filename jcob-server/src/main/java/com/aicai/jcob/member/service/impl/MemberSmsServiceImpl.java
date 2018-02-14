package com.aicai.jcob.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.member.common.service.MemberSmsService;
import com.aicai.jcob.member.manager.MemberSmsManager;

public class MemberSmsServiceImpl implements MemberSmsService {

	@Autowired
	@Qualifier("memberSmsManagerImpl")
	private MemberSmsManager memberSmsManager;

	@Override
	public ModelResult<Boolean> sendAuthCode(String phoneNo) {
		return memberSmsManager.sendAuthCode(phoneNo);
	}

	@Override
	public ModelResult<Boolean> validateAuthCode(String phoneNo, String authCode) {
		return memberSmsManager.validateAuthCode(phoneNo, authCode);
	}

}
