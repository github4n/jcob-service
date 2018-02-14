package com.aicai.jcob.membercharge.common.domain;

import java.io.Serializable;
import java.util.Calendar;

public class MemberChargeReturnLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6372750344323638384L;

	/**  */
    private Long id;

    /**  */
    private Long chargeId;

    /** 充值返回信息 */
    private String chargeReturnInfo;

    /**  */
    private Calendar createTime = Calendar.getInstance();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeReturnInfo() {
        return chargeReturnInfo;
    }

    public void setChargeReturnInfo(String chargeReturnInfo) {
        this.chargeReturnInfo = chargeReturnInfo == null ? null : chargeReturnInfo.trim();
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }
}