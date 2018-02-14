package com.aicai.jcob.member.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 充值渠道网关
 * 
 * @project jcob-common
 * @author zhanghl
 * @date 2016-01-22
 */
public class ChannelProxy extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1922063074896475747L;
	
	public static ChannelProxy ALIPAY = new ChannelProxy(1, "支付宝");
	
	public static ChannelProxy WEIXIN_PAY = new ChannelProxy(2, "微信支付");
	
	public static ChannelProxy LIANLIAN_PAY = new ChannelProxy(3, "连连支付");
	
	protected ChannelProxy(int index, String description) {
		super(index, description);
	}	

	public static List<ChannelProxy> getAllList() {
		return getAll(ChannelProxy.class);
	}
	public static  ChannelProxy valueOf(int index){
		return valueOf(ChannelProxy.class, index);
	}
}
