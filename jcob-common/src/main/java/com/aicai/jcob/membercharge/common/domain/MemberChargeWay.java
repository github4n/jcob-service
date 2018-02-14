package com.aicai.jcob.membercharge.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeWay;

public class MemberChargeWay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3194353815228273635L;

	/**  */
    private Long id;

    /**  */
    private Integer chargeWayIndex = ChargeWay.zfb_sdk.getIndex();

    /**  */
    private String chargeWayName = ChargeWay.zfb_sdk.getDescription();

    /**  */
    private Integer clientType = ClientType.android.getIndex();

    /**  */
    private Integer chargeChannelIndex;

    /**  */
    private String chargeChannelName;

    /** 手续费收取标识：0不收取，1收取 */
    private Integer feeFlag;

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

    public Integer getChargeWayIndex() {
        return chargeWayIndex;
    }

    public void setChargeWayIndex(Integer chargeWayIndex) {
        this.chargeWayIndex = chargeWayIndex;
    }

    public String getChargeWayName() {
        return chargeWayName;
    }

    public void setChargeWayName(String chargeWayName) {
        this.chargeWayName = chargeWayName == null ? null : chargeWayName.trim();
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
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

    public Integer getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(Integer feeFlag) {
        this.feeFlag = feeFlag;
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