package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 用户信息修改类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberLoginType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public MemberLoginType(Integer status, String description) {
		super(status, description);
	}
    /** 登录类型：1手机号登录,2邮箱登录,3第三方id登录 */
	public static MemberLoginType login_phone = new MemberLoginType(1, "手机号登录");
	public static MemberLoginType login_email = new MemberLoginType(2, "邮箱登录");
	public static MemberLoginType aici_login_third = new MemberLoginType(3, "爱彩投注系统授权登录");
	public static List<MemberLoginType> getAllList() {
		return getAll(MemberLoginType.class);
	}
	public static  MemberLoginType valueOf(Integer index){
		return valueOf(MemberLoginType.class, index);
	}
}
