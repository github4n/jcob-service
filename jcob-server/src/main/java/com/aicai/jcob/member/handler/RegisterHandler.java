package com.aicai.jcob.member.handler;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.option.RegisterOption;

/**
 * member用户注册信息验证接口
 * @author hailong.zhang
 *
 */
public interface RegisterHandler {

	/**
	 * 验证member参数
	 * @param member
	 */
	public void verify(Member member, RegisterOption registerOption);
	
	/**
	 * 返回注册处理器的类型
	 * @return
	 */
	public int getRegisterType();
	
	public int getLoginType();
}
