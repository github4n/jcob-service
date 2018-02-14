package com.aicai.jcob.member.handler.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.MemberLoginType;
import com.aicai.jcob.member.common.domain.constant.RegisterType;
import com.aicai.jcob.member.common.domain.option.RegisterOption;
import com.aicai.jcob.member.handler.RegisterHandler;

/**
 * 手机注册用户,验证手机号码格式是否合法
 * @author hailong.zhang
 *
 */
public class MobileRegisterHandlerImpl implements RegisterHandler {

	@Override
	public void verify(Member member,RegisterOption registerOption) {
		if(StringUtils.isBlank(member.getPhone())){
			throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_UNEFFECTIVE);
		}
		if(!member.getPhone().equals(registerOption.getRegisterId())){
			throw new JcobServerException(ExceptionCode.paramError);
		}
		Pattern pattern = Pattern.compile("^(1(([3][0-9])|([5][0123456789])|([4][57])|([7][0-9])|[8][0123456789]))\\d{8}$");
		Matcher matcher = pattern.matcher(member.getPhone());
		if(!matcher.matches()) throw new JcobServerException(ExceptionCode.MEMBER_MOBILE_UNEFFECTIVE);
	}

	@Override
	public int getRegisterType() {
		return RegisterType.phone_register.getIndex();
	}

	@Override
	public int getLoginType() {
		
		return MemberLoginType.login_phone.getIndex();
	}
	
}
