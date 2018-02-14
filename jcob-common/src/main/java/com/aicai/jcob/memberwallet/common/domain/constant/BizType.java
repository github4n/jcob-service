package com.aicai.jcob.memberwallet.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 钱包变动业务类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class BizType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public BizType(Integer status, String description) {
		super(status, description);
	}
	public static BizType look_plan = new BizType(1, "查看付费推荐");
	public static BizType charge = new BizType(2, "购买火眼");
	public static BizType draw_money = new BizType(3, "提款");
	public static BizType exception = new BizType(4, "异常处理");
	public static List<BizType> getAllList() {
		return getAll(BizType.class);
	}
	public static  BizType valueOf(Integer index){
		return valueOf(BizType.class, index);
	}
}
