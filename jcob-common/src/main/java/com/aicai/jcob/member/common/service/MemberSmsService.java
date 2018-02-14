package com.aicai.jcob.member.common.service;

import com.aicai.appmodel.domain.result.ModelResult;

/**
 * 用户短信
 *
 */
public interface MemberSmsService {

	/**
	 * 发送验证码
	 * 
	 * @param phoneNo
	 */
	public ModelResult<Boolean> sendAuthCode(String phoneNo);

	/**
	 * 校验验证码
	 * 
	 * @param phoneNo
	 * @param authCode
	 * @return
	 */
	public ModelResult<Boolean> validateAuthCode(String phoneNo, String authCode);

}
