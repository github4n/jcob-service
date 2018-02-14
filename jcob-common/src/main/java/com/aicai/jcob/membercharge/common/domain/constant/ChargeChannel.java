package com.aicai.jcob.membercharge.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 充值渠道配置
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class ChargeChannel extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public ChargeChannel(Integer status, String description) {
		super(status, description);
	}
	public static ChargeChannel zfb_sdk = new ChargeChannel(1, "支付宝SDK");
	public static ChargeChannel zfb_wap = new ChargeChannel(2, "支付宝WAP");
	public static ChargeChannel zfb_web = new ChargeChannel(3, "支付宝WEB");
	public static ChargeChannel wx = new ChargeChannel(4, "微信");
	public static ChargeChannel lianlian_sdk = new ChargeChannel(5, "连连支付SDK");
	public static ChargeChannel lianlian_wap = new ChargeChannel(6, "连连支付WAP");
	public static List<ChargeChannel> getAllList() {
		return getAll(ChargeChannel.class);
	}
	public static  ChargeChannel valueOf(Integer index){
		return valueOf(ChargeChannel.class, index);
	}
}
