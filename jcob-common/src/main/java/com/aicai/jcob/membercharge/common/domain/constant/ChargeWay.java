package com.aicai.jcob.membercharge.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 充值方式
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class ChargeWay extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public ChargeWay(Integer status, String description) {
		super(status, description);
	}
	public static ChargeWay zfb_sdk = new ChargeWay(1, "支付宝sdk");
	public static ChargeWay zfb_wap = new ChargeWay(2, "支付宝wap");
	public static ChargeWay zfb_web = new ChargeWay(3, "支付宝web");
	public static ChargeWay weixin_pay = new ChargeWay(4, "微信支付sdk");
	public static ChargeWay lianlian_sdk_pay = new ChargeWay(5, "连连支付sdk");
	public static ChargeWay lianlian_wap_pay = new ChargeWay(6, "连连支付wap");
	
	public static List<ChargeWay> getAllList() {
		return getAll(ChargeWay.class);
	}
	public static  ChargeWay valueOf(Integer index){
		return valueOf(ChargeWay.class, index);
	}
}
