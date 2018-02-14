package com.aicai.jcob.memberwallet.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;
/**
 * 异常单据状态
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月2日
 */
public class MemberBizExceptionBillStatus extends BaseType {
	private static final long serialVersionUID = 3915548045992511782L;

	public MemberBizExceptionBillStatus(Integer status, String description) {
		super(status, description);
	}
	 /** 单据状态：0创建，1处理完成，2无效 */
	public static MemberBizExceptionBillStatus create = new MemberBizExceptionBillStatus(0, "创建");
	public static MemberBizExceptionBillStatus complete = new MemberBizExceptionBillStatus(1, "处理完成");
	public static MemberBizExceptionBillStatus invalid = new MemberBizExceptionBillStatus(2, "无效");

	public static List<MemberBizExceptionBillStatus> getAllList() {
		return getAll(MemberBizExceptionBillStatus.class);
	}

	public static MemberBizExceptionBillStatus valueOf(Integer index) {
		return valueOf(MemberBizExceptionBillStatus.class, index);
	}
}
