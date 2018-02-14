package com.aicai.jcob.membercharge.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

public class ChargeStatus extends BaseType {

	private static final long serialVersionUID = 3402750231176272364L;

	public ChargeStatus(Integer status, String description) {
		super(status, description);
	}

	public static ChargeStatus fail = new ChargeStatus(0, "失败");
	public static ChargeStatus success = new ChargeStatus(1, "成功");

	public static List<ChargeStatus> getAllList() {
		return getAll(ChargeStatus.class);
	}

	public static ChargeStatus valueOf(Integer index) {
		return valueOf(ChargeStatus.class, index);
	}
}
