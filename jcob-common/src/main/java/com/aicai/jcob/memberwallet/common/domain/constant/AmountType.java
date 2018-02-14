package com.aicai.jcob.memberwallet.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * amount类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月2日
 */
public class AmountType extends BaseType {
	private static final long serialVersionUID = 8925384174896988736L;
	public AmountType(Integer status, String description) {
		super(status, description);
	}
	public static AmountType cash = new AmountType(1, "现金");
	public static AmountType huoyan = new AmountType(2, "火眼");
	public static List<AmountType> getAllList() {
		return getAll(AmountType.class);
	}
	public static  AmountType valueOf(Integer index){
		return valueOf(AmountType.class, index);
	}
}
