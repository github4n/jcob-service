package com.aicai.jcob.memberdrawmoney.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 提款状态
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class DrawMoneyStatus extends BaseType {

	private static final long serialVersionUID = 668731864604313378L;

	public DrawMoneyStatus(Integer status, String description) {
		super(status, description);
	}

	public static DrawMoneyStatus submit = new DrawMoneyStatus(0, "提交提款");
	public static DrawMoneyStatus accept = new DrawMoneyStatus(1, "提款已受理");
	public static DrawMoneyStatus success = new DrawMoneyStatus(2, "提款成功");
	public static DrawMoneyStatus fail = new DrawMoneyStatus(3, "提款失败");

	public static List<DrawMoneyStatus> getAllList() {
		return getAll(DrawMoneyStatus.class);
	}

	public static DrawMoneyStatus valueOf(Integer index) {
		return valueOf(DrawMoneyStatus.class, index);
	}
}
