package com.aicai.jcob.member.handler.impl;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.common.domain.constant.RegisterType;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.handler.RegisterHandler;

public class AicaiThirdRegisterHandlerImpl implements RegisterHandler {

	@Override
	public void verify(Member member, RegisterOption registerOption) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRegisterType() {
		return RegisterType.aicai_login_register.getIndex();
	}

	@Override
	public int getLoginType() {
		return MemberLoginType.aici_login_third.getIndex();
	}
	
}
