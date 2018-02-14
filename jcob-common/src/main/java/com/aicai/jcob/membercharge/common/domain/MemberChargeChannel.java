package com.aicai.jcob.membercharge.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;

public class MemberChargeChannel implements Serializable{
	private static final long serialVersionUID = -6675482081040392304L;

	/**  */
    private Long id;

    /**  */
    private Integer chargeChannelIndex = ChargeChannel.zfb_sdk.getIndex();

    /**  */
    private String chargeChannelName;

    /** 充值费率(本系统收用户的) */
    private BigDecimal feeRatio;

    /**  */
    private BigDecimal minFeeAmount;

    /**  */
    private BigDecimal maxFeeAmount;

    /** 渠道状态：0不可用，1可用 */
    private Integer status;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    /**  */
    private Calendar updateTime = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChargeChannelIndex() {
        return chargeChannelIndex;
    }

    public void setChargeChannelIndex(Integer chargeChannelIndex) {
        this.chargeChannelIndex = chargeChannelIndex;
    }

    public String getChargeChannelName() {
        return chargeChannelName;
    }

    public void setChargeChannelName(String chargeChannelName) {
        this.chargeChannelName = chargeChannelName == null ? null : chargeChannelName.trim();
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }

    public BigDecimal getMinFeeAmount() {
        return minFeeAmount;
    }

    public void setMinFeeAmount(BigDecimal minFeeAmount) {
        this.minFeeAmount = minFeeAmount;
    }

    public BigDecimal getMaxFeeAmount() {
        return maxFeeAmount;
    }

    public void setMaxFeeAmount(BigDecimal maxFeeAmount) {
        this.maxFeeAmount = maxFeeAmount;
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
}