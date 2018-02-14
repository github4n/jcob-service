package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;

public class MemberUseChargeWay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5641365181672591199L;

	private Long id;

    private Long memberId;
    
    private Integer chargeIndex;
    
    private String bankName;
    
    private String bankCode;
    
    private String bankCard;
    
    private Integer clientType = ClientType.android.getIndex();
    
    private Integer status;
    
    /**
     * 2 : 借记卡
     * 3 : 信用卡
     */
    private Integer payType;
    
    /** 注册创建时间 */
    private Calendar createTime =  Calendar.getInstance();

    /** 信息修改时间 */
    private Calendar updateTime = Calendar.getInstance();

    private BigDecimal singleLimitAmount;
    
    public MemberUseChargeWay(){}
    
    public MemberUseChargeWay(long memberId,int chargeIndex,int clientType,int status){
    	this.setMemberId(memberId);
    	this.setChargeIndex(chargeIndex);
    	this.setClientType(clientType);
    	this.setStatus(status);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getChargeIndex() {
		return chargeIndex;
	}

	public void setChargeIndex(Integer chargeIndex) {
		this.chargeIndex = chargeIndex;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getSingleLimitAmount() {
		return singleLimitAmount;
	}

	public void setSingleLimitAmount(BigDecimal singleLimitAmount) {
		this.singleLimitAmount = singleLimitAmount;
	}
    
	
}
