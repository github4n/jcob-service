package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 注册类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class RegisterType extends BaseType {
	private static final long serialVersionUID = 2576427763766956443L;
	public RegisterType(Integer status, String description) {
		super(status, description);
	}
	 /** 注册类型：1本站手机号注册，2本站邮件注册，3第三方登录注册 */
	public static RegisterType phone_register = new RegisterType(1, "手机注册");
	public static RegisterType email_register = new RegisterType(2, "邮箱注册");
	public static RegisterType aicai_login_register = new RegisterType(3, "爱彩投注系统第三方授权注册");
	public static List<RegisterType> getAllList() {
		return getAll(RegisterType.class);
	}
	public static  RegisterType valueOf(Integer index){
		return valueOf(RegisterType.class, index);
	}
}
