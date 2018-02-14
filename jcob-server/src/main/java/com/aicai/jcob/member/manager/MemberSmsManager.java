package com.aicai.jcob.member.manager;

import com.aicai.appmodel.domain.result.ModelResult;


public interface MemberSmsManager {
	/**
	 * 发送注册验证码
	 * 
	 * @param phoneNo
	 */
	public ModelResult<Boolean> sendAuthCode(String phoneNo);

	/**
	 * 校验验证码
	 * 
	 * @param phoneNo
	 */
	public ModelResult<Boolean> validateAuthCode(String phoneNo, String authCode);
}
