package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;
/**
 * 用户提款参数
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月22日
 */
public class MemberDrawMoneyWayAddOption implements Serializable {
	private static final long serialVersionUID = 2960113648004285945L;

	/**省:code 格式  湖南省:123456 **/
	private String province;
	
	/**市:code  格式 长沙市:45678 **/
	private String city;

	/**银行 **/
	private String bankName;
	
	/**支行信息 **/
	private String subbankInfo;
	/** 提款卡号/支付宝帐号**/
	private String bankCardNo;
	/** 银行代码**/
	private String bankCode;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSubbankInfo() {
		return subbankInfo;
	}
	public void setSubbankInfo(String subbankInfo) {
		this.subbankInfo = subbankInfo;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
}
