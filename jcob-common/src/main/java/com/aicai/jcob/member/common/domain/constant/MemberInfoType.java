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
public class MemberInfoType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public MemberInfoType(Integer status, String description) {
		super(status, description);
	}
	 /** 信息类型:0：手机号，1：昵称，2：身份证，3：真实姓名 */
	public static MemberInfoType phone = new MemberInfoType(0, "手机号");
	public static MemberInfoType nick_name = new MemberInfoType(1, "昵称");
	public static MemberInfoType identity_card = new MemberInfoType(2, "身份证");
	public static MemberInfoType real_name = new MemberInfoType(3, "真实姓名");
	public static List<MemberInfoType> getAllList() {
		return getAll(MemberInfoType.class);
	}
	public static  MemberInfoType valueOf(Integer index){
		return valueOf(MemberInfoType.class, index);
	}
}
