package com.aicai.jcob.member.common.domain.constant;
/**
 * 用户属性信息标记
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class MemberFlagBit {

	public final static long defualt = 0;
	/**手机验证标识**/
	public final static long phone_valid = 1;
	/**邮箱验证标识**/
	public final static long email_valid = 1 << 1;
	/**实名信息认证验证标识**/
	public final static long certification = 1 << 2;
	
	/**
	 * 验证  optionFlagbit操作标识是否已经存在userFlagbit 中
	 * @param optionFlagbit
	 * @param userFlagbit
	 * @return
	 */
	public static boolean isExistFlagBit(long optionFlagbit,long userFlagbit) {
		return 	(optionFlagbit & userFlagbit )> 0;
	}
	
}
