package com.aicai.jcob.memberwallet.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 钱包操作类型
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年1月22日
 */
public class WalletOpType extends BaseType {
	private static final long serialVersionUID = 3402750231176272364L;
	public WalletOpType(Integer status, String description) {
		super(status, description);
	}
	 /** 钱包操作类型：1扣款，2加钱 */
	public static WalletOpType deduct_money = new WalletOpType(1, "扣款");
	public static WalletOpType add_money = new WalletOpType(2, "加钱");
	public static List<WalletOpType> getAllList() {
		return getAll(WalletOpType.class);
	}
	public static  WalletOpType valueOf(Integer index){
		return valueOf(WalletOpType.class, index);
	}
}
