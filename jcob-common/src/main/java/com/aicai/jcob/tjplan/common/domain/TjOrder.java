package com.aicai.jcob.tjplan.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.tjplan.common.domain.constant.TjOrderPayStatus;

public class TjOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3523629784299062390L;

	/**  */
    private Long id;

    /** 方案id */
    private Long planId;

    /** 金额 */
    private BigDecimal amount;

    /** 用户id */
    private Long memberId;

    /** 支付状态：1待支付，2已支付 */
    private Integer payStatus = TjOrderPayStatus.wait_pay;
    
    /**
     * 现金换火眼比例
     */
    private BigDecimal cashTOHuoyanRatio = BigDecimal.valueOf(1);

    /** 创建时间 */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();
    private Integer clientType = ClientType.android.getIndex();
    private Integer channel = 1;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getCashTOHuoyanRatio() {
		return cashTOHuoyanRatio;
	}

	public void setCashTOHuoyanRatio(BigDecimal cashTOHuoyanRatio) {
		this.cashTOHuoyanRatio = cashTOHuoyanRatio;
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
    
    public String getTjOrderNo(){
    	//格式：时间 + id
    	String no = null;
    	if (createTime != null && id != null) {
    		no = DateUtil.dateToString(createTime.getTime(),"yyyyMMdd")+id;
		}
    	return no;
    }

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
    
    
}