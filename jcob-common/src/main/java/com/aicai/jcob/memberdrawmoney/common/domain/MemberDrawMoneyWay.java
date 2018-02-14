package com.aicai.jcob.memberdrawmoney.common.domain;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.memberdrawmoney.common.domain.constant.MemberDrawMoneyWayStatus;

public class MemberDrawMoneyWay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8994165379959252122L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /**  */
    private String bankCode;

    /**  */
    private String bankName;

    /** 支行信息:省|市|支行名称|提款帐号 */
    private String drawBankInfo;

    /** 状态：0无效，1有效 */
    private Integer status = MemberDrawMoneyWayStatus.valid;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getDrawBankInfo() {
        return drawBankInfo;
    }

    public void setDrawBankInfo(String drawBankInfo) {
        this.drawBankInfo = drawBankInfo == null ? null : drawBankInfo.trim();
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