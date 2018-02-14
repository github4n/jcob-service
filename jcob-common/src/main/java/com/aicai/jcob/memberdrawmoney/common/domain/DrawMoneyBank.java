package com.aicai.jcob.memberdrawmoney.common.domain;

import java.io.Serializable;

/**
 * 银行信息
 * 
 * @author alanpeng
 * 
 */
public class DrawMoneyBank implements Serializable {

	private static final long serialVersionUID = 2539044534166086975L;

	private int id;

	// 收款银行 如：中国银行
	private String receiveBank;

	// 收款银行接口名,如:宁波银行和杭州银行,实际提交到支付接口银行名称 如城市商业银行
	private String receiveBankInter;

	// 收款银行短名,用于前端页面显示缩写
	private String receiveBankShort;

	// 收款账号类型 1企业 0个人
	private int bankAccountType;

	// 需求省市信息 1要 0不要
	private int provinceCityFlag;

	// 需求支行信息 1要 0不要
	private int branchFlag;

	// 是否支持信用卡 1支持 0不支持
	private int creditCardFlag;

	// 是否支持存折
	private int bankBookFlag;

	// 节假日是否服务 1服务 0不服务
	private int holidayServiceFlag;

	// 服务起始时间 如 0:00-20:30的 0:00
	private String serviceTimeFrom;

	// 服务结束时间 如 0:00-20:30中 20:30
	private String serviceTimeTo;

	// 特殊工作日（由节假日或者普通周末转成的 工作日） 如 08-22,08-23
	private String holidayToWork;

	// 特殊节假日（由工作日转为休假日） 如 08-22,08-23
	private String workToHoliday;

	// 到帐时间 以小时为单位  如 4，8，12，24，48小时
	private int hour;
	/**
	 * 提款银行标记 （如果是未迁移的用户 只会有0、1的银行数据,如果是已迁移 只会有0、2的银行数据）
	 * 0、全部支持
	 * 1、只支持支付宝提款
	 * 2、只支持新浪支付提款
	 * */
	private int bankFlag;
	/**
	 * 银行编码
	 */
	private String bankCode;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiveBank() {
		return receiveBank;
	}

	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}

	public String getReceiveBankInter() {
		return receiveBankInter;
	}

	public void setReceiveBankInter(String receiveBankInter) {
		this.receiveBankInter = receiveBankInter;
	}

	public String getReceiveBankShort() {
		return receiveBankShort;
	}

	public void setReceiveBankShort(String receiveBankShort) {
		this.receiveBankShort = receiveBankShort;
	}

	public int getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(int bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public int getProvinceCityFlag() {
		return provinceCityFlag;
	}

	public void setProvinceCityFlag(int provinceCityFlag) {
		this.provinceCityFlag = provinceCityFlag;
	}

	public int getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(int branchFlag) {
		this.branchFlag = branchFlag;
	}

	public int getCreditCardFlag() {
		return creditCardFlag;
	}

	public void setCreditCardFlag(int creditCardFlag) {
		this.creditCardFlag = creditCardFlag;
	}

	public int getBankBookFlag() {
		return bankBookFlag;
	}

	public void setBankBookFlag(int bankBookFlag) {
		this.bankBookFlag = bankBookFlag;
	}

	public int getHolidayServiceFlag() {
		return holidayServiceFlag;
	}

	public void setHolidayServiceFlag(int holidayServiceFlag) {
		this.holidayServiceFlag = holidayServiceFlag;
	}

	public String getServiceTimeFrom() {
		return serviceTimeFrom;
	}

	public void setServiceTimeFrom(String serviceTimeFrom) {
		this.serviceTimeFrom = serviceTimeFrom;
	}

	public String getServiceTimeTo() {
		return serviceTimeTo;
	}

	public void setServiceTimeTo(String serviceTimeTo) {
		this.serviceTimeTo = serviceTimeTo;
	}

	public String getHolidayToWork() {
		return holidayToWork;
	}

	public void setHolidayToWork(String holidayToWork) {
		this.holidayToWork = holidayToWork;
	}

	public String getWorkToHoliday() {
		return workToHoliday;
	}

	public void setWorkToHoliday(String workToHoliday) {
		this.workToHoliday = workToHoliday;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(int bankFlag) {
		this.bankFlag = bankFlag;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
