package com.aicai.jcob.memberdrawmoney.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 提款审核状态
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class AuditStatus extends BaseType {

	private static final long serialVersionUID = 668731864604313378L;

	public AuditStatus(Integer status, String description) {
		super(status, description);
	}

	public static AuditStatus auditing = new AuditStatus(0, "审核中");
	public static AuditStatus audit_pass = new AuditStatus(1, "审核通过");
	public static AuditStatus audit_unpass = new AuditStatus(2, "审核不通过");

	public static List<AuditStatus> getAllList() {
		return getAll(AuditStatus.class);
	}

	public static AuditStatus valueOf(Integer index) {
		return valueOf(AuditStatus.class, index);
	}
}
