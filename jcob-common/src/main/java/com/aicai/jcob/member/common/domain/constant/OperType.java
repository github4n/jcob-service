package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 用户操作类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class OperType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public OperType(Integer status, String description) {
		super(status, description);
	}
	public static OperType register = new OperType(0, "注册");
	public static OperType login = new OperType(1, "登录");
	public static OperType publish_plan = new OperType(2, "专家发布方案");
	public static OperType look_plan = new OperType(3, "查看专家方案");
	public static OperType attention_expert = new OperType(4, "关注专家");
	public static OperType cancel_attention_expert = new OperType(5, "取消关注专家");
	public static OperType charge = new OperType(6, "充值");
	public static OperType draw_money = new OperType(7, "提款");
	public static OperType modify_member_info = new OperType(8, "修改用户信息");
	public static OperType logout = new OperType(9, "登出");
	public static OperType reset_password = new OperType(10, "重置密码");
	public static OperType third_bind = new OperType(11, "第三方信息绑定");
	public static OperType charge_notify_return = new OperType(12, "充值返回消息通知");
	public static OperType add_draw_money_way = new OperType(13, "添加提款方式");
	public static OperType del_draw_money_way = new OperType(14, "删除提款方式");
	public static OperType system_auto_login = new OperType(15, "系统自动登录");
	public static OperType third_login = new OperType(16, "第三方登录");
	public static List<OperType> getAllList() {
		return getAll(OperType.class);
	}
	public static  OperType valueOf(Integer index){
		return valueOf(OperType.class, index);
	}
}
