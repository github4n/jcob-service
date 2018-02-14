package com.aicai.jcob.member.manager.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.member.manager.MemberSmsManager;
import com.aicai.msgcenter.common.constants.AppCodeConstants;
import com.aicai.msgcenter.common.constants.jcob.JcobSmsAuthTypeConstants;
import com.aicai.msgcenter.common.constants.jcob.JcobTemplateIdConstants;
import com.aicai.msgcenter.common.service.MsgAuthCodeService;

public class MemberSmsManagerImpl implements MemberSmsManager {
	private transient Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("msgAuthCodeServiceClientImpl")
	private MsgAuthCodeService msgAuthCodeService;

	@Override
	public ModelResult<Boolean> sendAuthCode(String phoneNo) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>(true);
		ModelResult<Boolean> msgCenterResult = msgAuthCodeService.sendAuthCode(JcobSmsAuthTypeConstants.SMS_AUTH_TYPE, phoneNo,
				AppCodeConstants.APP_CODE_JCOB_APP, JcobTemplateIdConstants.TEMPLATE_ID_AUTH_CODE, null);
		if (!msgCenterResult.isSuccess() || !msgCenterResult.getModel()) {
			modelResult.setModel(false);
			logger.error("使用sendAuthCode方法出现错误:{}", msgCenterResult.getErrorMsg());
			Map<String, String> map = msgCenterResult.getErrorList();
			String error = map.get("msgcenter.authcode.typeblack");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_PHONE_BLACK_LIST.getCode(),
						ExceptionCode.MEMBER_PHONE_BLACK_LIST.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.authcode.phoneout");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_OVER_GET.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_OVER_GET.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.error");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_SYSTEM_ERROR.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_SYSTEM_ERROR.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.template.frequency");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_OVER_FREQUENCY.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_OVER_FREQUENCY.getMsg());
				return modelResult;
			}
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> validateAuthCode(String phoneNo, String authCode) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>(true);
		if (StringUtils.isAnyBlank(phoneNo, authCode)) {
			modelResult.setModel(false);
			modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_PARAM_ISNULL.getCode(),
					ExceptionCode.MEMBER_AUTHCODE_PARAM_ISNULL.getMsg());
			return modelResult;
		}
		@SuppressWarnings("deprecation")
		ModelResult<Boolean> msgCenterResult = msgAuthCodeService.validateAuthCode(JcobSmsAuthTypeConstants.SMS_AUTH_TYPE,
				phoneNo, AppCodeConstants.APP_CODE_JCOB_APP, authCode);
		if (!msgCenterResult.isSuccess() || !msgCenterResult.getModel()) {
			modelResult.setModel(false);
			logger.error("使用validateAuthCode方法出现错误:{}", msgCenterResult.getErrorMsg());
			Map<String, String> map = msgCenterResult.getErrorList();
			String error = map.get("msgcenter.authcode.validatelimit");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_OVER_VERIFY.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_OVER_VERIFY.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.expired");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_EXPIRED.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_EXPIRED.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.not");
			if (error != null) {
				modelResult
						.withError(ExceptionCode.MEMBER_AUTHCODE_ERROR.getCode(), ExceptionCode.MEMBER_AUTHCODE_ERROR.getMsg());
				return modelResult;
			}
			error = map.get("msgcenter.authcode.error");
			if (error != null) {
				modelResult.withError(ExceptionCode.MEMBER_AUTHCODE_SYSTEM_ERROR.getCode(),
						ExceptionCode.MEMBER_AUTHCODE_SYSTEM_ERROR.getMsg());
				return modelResult;
			}

		}
		return modelResult;
	}
}
