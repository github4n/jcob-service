package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 客户端类型
 * 
 * @project jcob-common
 * @author fx
 * @date 2016年2月18日
 */
public class PasswordStrength extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	
	public PasswordStrength(Integer status, String description) {
		super(status, description);
	}
	
	public static PasswordStrength weak = new PasswordStrength(1, "弱");
	public static PasswordStrength middle = new PasswordStrength(2, "中");
	public static PasswordStrength strong = new PasswordStrength(3, "强");
	
	
	public static List<PasswordStrength> getAllList() {
		return getAll(PasswordStrength.class);
	}
	public static  PasswordStrength valueOf(Integer index){
		return valueOf(PasswordStrength.class, index);
	}
	
	public static int CheckPasswordSecurityLevel(String password){
		String bigCharctorMatch = "^(.)*([A-Z])+(.)*$";
		String smallCharctorMatch = "^(.)*([a-z])+(.)*$";
    	String numberMatch = "^(.)*([0-9])+(.)*$";
		int tag = 0;
		
		if(password.length() <=6 ) return ++tag;
		if(password.matches(bigCharctorMatch)) tag++;
		if(password.matches(smallCharctorMatch)) tag++;
		if(password.matches(numberMatch)) tag++;
		
		return tag;
	}
	
}
