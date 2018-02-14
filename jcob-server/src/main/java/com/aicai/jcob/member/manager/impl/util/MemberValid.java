package com.aicai.jcob.member.manager.impl.util;

import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;

/**
 * 用户注册校验
 * @project coreService
 * @author szk
 * @date 2010-11-27
 * Copyright (C) 2010-2012 www.2caipiao.com Inc. All rights reserved.
 */
public class MemberValid {
	/**
	 * 产品需求，用户注册和修改密码不能为以下网络排名比较高的
	 * 这个比较固定，不建表，搞后台维护，有要加的，这里加
	 */
	/*public final static String[] moreUsePassword = { "112233", "123123",
			"123321", " abcabc", "abc123", "a1b2c3", "aaa111", "123qwe",
			"qwerty", "qweasd", " admin", "password", "passwd", "iloveyou",
			"5201314", "adobe123", "letmein", "photoshop", "monkey", "shadow",
			"sunshine", "password1", "princess", "azerty", "trustno1" };*/

    /**
     * 验证用户名
     *
     * @param account
     *            用户名
     * @return
     */
    /*public static void validAccount(String account) throws JcobServerException {

    	validAccount(account,14);
        
    }*/
    
	/**
	 * 验证用户昵称
	 * @param account
	 * @throws JcobServerException
	 */
    public static void validNickName(String account) throws JcobServerException {

    	validNickName(account,14);
        
    }
    
    /**
     * 验证用户名
     *
     * @param account
     *            用户名
     * @return
     */
    /*public static void validAccount(String account,int lengthMax) throws JcobServerException {

        if (!MemberCenterValidUtil.validUserNameLength(account,lengthMax))
            throw new JcobServerException(MemberErrorTable.ACCOUNT_LENGTH_ERROR);

        if (MemberCenterValidUtil.containInvalidChars(account)) {
            throw new JcobServerException(MemberErrorTable.ACCOUNT_COMPONENT_ERROR);
        }
        if(!account.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")){
        	 throw new JcobServerException(MemberErrorTable.ACCOUNT_COMPONENT_ERROR);
        }
        
    }*/
    
    /**
     * 验证密码
     *
     * @param password
     *            密码
     * @return
     */
    /*public static void validPassword(String password) throws JcobServerException {
        if (StringUtils.isBlank(password))
            throw new JcobServerException(MemberErrorTable.PASSWORD_NOT_NULL);

        // 判断密码的长度
        if (password.length() < 6 || password.length() > 15)
            throw new JcobServerException(MemberErrorTable.PASSWORD_LENGTH_ERROR);

        if (!MemberCenterValidUtil.validPasswordLength(password))
            throw new JcobServerException(MemberErrorTable.PASSWORD_COMPONENT_ERROR);
    }*/

    /**
     * 验证用户的真实姓名
     *
     * @param realName
     *            真实姓名
     * @return
     * @throws MemberClientException
     */
    /*public static void validRealName(String realName) throws RegisterException {
        if (!StringUtils.isBlank(realName)) {
            if (!isChineseUserName(realName)){
                throw new JcobServerException(MemberErrorTable.REALNAME_COMPONENT_ERROR);
            }
        }
    }*/

    
    /**
     * 是否可用的真实姓名,同时判断是否新疆人的姓名
     * @param words 输入字符串
     * @return
     */
    /*public static boolean isChineseUserName(String words) {
        if(StringUtils.isBlank(words)){
            return false;
        }
        String reg1 = "^[\u4E00-\uFA29\uE7C7-\uE7F3]{2,10}$";
        String reg2 = "^[\u4E00-\uFA29\uE7C7-\uE7F3]{2,10}[·][\u4E00-\uFA29\uE7C7-\uE7F3]{2,9}$"; //新疆人姓名,以·连接
        String reg3 = "^[\u4E00-\uFA29\uE7C7-\uE7F3]{2,10}[●][\u4E00-\uFA29\uE7C7-\uE7F3]{2,9}$";//新疆人姓名,以●连接
        String reg4 = "^[\u4E00-\uFA29\uE7C7-\uE7F3]{2,10}[\\s][\u4E00-\uFA29\uE7C7-\uE7F3]{2,9}$";//新疆人姓名,以空格连接
        String reg5 = "^[\u4E00-\uFA29\uE7C7-\uE7F3]{2,10}[\\-][\u4E00-\uFA29\uE7C7-\uE7F3]{2,9}$";//新疆人姓名,以-连接
        
        Matcher m1 = Pattern.compile(reg1).matcher(words);
        Matcher m2 = Pattern.compile(reg2).matcher(words);
        Matcher m3 = Pattern.compile(reg3).matcher(words);
        Matcher m4 = Pattern.compile(reg4).matcher(words);
        Matcher m5 = Pattern.compile(reg5).matcher(words);
        
        if(!m1.find()&&!m2.find()&&!m3.find()&&!m4.find()&&!m5.find()){
            return false;
        }
        return true;
    }*/
    
    /**
     * 验证身份证号码
     *
     * @param realName
     * @return
     * @throws MemberClientException
     */
    /*public static void validIDCard(String idCard) throws JcobServerException {

        if (idCard != null && idCard.length() != 15 && idCard.length() != 18)
            throw new JcobServerException(MemberErrorTable.IDCARD_LENGTH_ERROR);

        String regex = "([a-zA-Z0-9]){0,18}";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(idCard).matches())
            throw new JcobServerException(MemberErrorTable.IDCARD_COMPONENT_ERROR);

        if (!RegexUtils.isIdCard(idCard))
            throw new JcobServerException(MemberErrorTable.IDCARD_REAL_ERROR);
    }*/
    
    
    
 
    /**
     * 验证用户的电话(手机号码)
     *
     * @param contact
     *            联系方式
     * @return
     * @throws MemberClientException
     */
    /*public static void validContact(String contact) throws JcobServerException {
        if (StringUtils.isBlank(contact))
            throw new JcobServerException(MemberErrorTable.PHONE_NOT_NULL);
    }*/

    /**
     * 验证电子邮箱
     *
     * @param email
     * @return
     * @throws MemberClientException
     */
    /*public static void validEmail(String email) throws RegisterException {
        if (StringUtils.isBlank(email))
            throw new JcobServerException(MemberErrorTable.EMAIL_NOT_NULL);
        if (!RegexUtils.isEmail(email))
            throw new JcobServerException(MemberErrorTable.EMAIL_COMPONENT_ERROR);
    }*/

    /**
     * 市场渠道验证
     *
     * @param sellChannelId
     */
    /*public static void validSellChannel(Long sellChannelId) {
        if (sellChannelId == null)
            throw new JcobServerException(MemberErrorTable.SELLCHANNEL_NOT_NULL);
    }*/

    /**
     * 销售渠道验证
     */
    /*public static void validSellClient(SellClient client) {
        if (client == null)
            throw new JcobServerException(MemberErrorTable.SELLCLIENT_NOT_NULL);
    }*/
    
    /**
     * 验证用户注册和修改密码
     * 规则：
     * 密码不为6-15位
     * 密码为重复数字或字母
     * 密码为连续数字或字母（含倒序）
     * 含非法字符(字母、数字外的其它字符)
     * 密码不能为用户名
     */
    /*public static void validRegisteAndUpdatePassword(String account,String password){
    	//不能为空
		if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
			throw new JcobServerException(MemberErrorTable.ACCOUNT_PASS_ISNULL);
		}
		//不能和用户名相同
		if (account.toLowerCase().equals(password.toLowerCase())) {
			throw new JcobServerException(MemberErrorTable.ACCOUNT_PASS_EQUAL);
		}
		// 密码的长度6-15
		if (StringUtils.isBlank(password) || password.length() < 6
				|| password.length() > 15) {
			throw new JcobServerException(MemberErrorTable.PASSWORD_LENGTH_ERROR);
		}
		// 密码有效组合[a-zA-Z0-9]
		if (!MemberCenterValidUtil.validPasswordLength(password)) {
			throw new JcobServerException(MemberErrorTable.PASSWORD_COMPONENT_ERROR);
		}
		// 不能重复
		if (MemberCenterValidUtil.validateRepeat(password)) {
			throw new JcobServerException(MemberErrorTable.PASSWORD_REPEAT);
		}
		// 不能连续
		if (MemberCenterValidUtil.validateContinuous(password)) {
			throw new JcobServerException(MemberErrorTable.PASSWORD_CONTINUOUS);
		}
		if (ArrayUtils.contains(moreUsePassword, password)) {
			throw new JcobServerException(MemberErrorTable.PASSWORD_NOT_ALLOW);
		}
    }*/
    
    public static void validNickName(String account,int lengthMax) throws JcobServerException {

        if (!MemberCenterValidUtil.validUserNameLength(account,lengthMax))
            throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_LENGTH_ERROR);

        if (MemberCenterValidUtil.containInvalidChars(account)) {
            throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_COMPONENT_ERROR);
        }
        if(!account.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")){
        	 throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_COMPONENT_ERROR);
        }
        
    }
    
    /*public static void main(String[] args) {
		validRegisteAndUpdatePassword("ttttddttt","1dfdfd");
	}*/
}
