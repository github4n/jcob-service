package com.aicai.jcob.memberwallet.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.utils.MD5;

public class MemberWallet implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 6286085266724214058L;

	/**  */
    private Long id;

    /** 用户id */
    private Long memberId;

    /** 可用现金余额 */
    private BigDecimal ableBalance = new BigDecimal("0.000");

    /** 冻结现金余额 */
    private BigDecimal freezedBalance = new BigDecimal("0.000");

    /** 已消费总额 */
    private BigDecimal heapBalance = new BigDecimal("0.000");

    /** 资金安全校验，当前数据签名md5(member_id+able_balance+freezed_balance) */
    private String verifyMd5;

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

    public BigDecimal getAbleBalance() {
        return ableBalance;
    }

    public void setAbleBalance(BigDecimal ableBalance) {
        this.ableBalance = ableBalance;
    }

    public BigDecimal getFreezedBalance() {
        return freezedBalance;
    }

    public void setFreezedBalance(BigDecimal freezedBalance) {
        this.freezedBalance = freezedBalance;
    }

    public BigDecimal getHeapBalance() {
        return heapBalance;
    }

    public void setHeapBalance(BigDecimal heapBalance) {
        this.heapBalance = heapBalance;
    }

    public String getVerifyMd5() {
        return verifyMd5;
    }

    public void setVerifyMd5(String verifyMd5) {
        this.verifyMd5 = verifyMd5 == null ? null : verifyMd5.trim();
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
    
    public String getCalculateVerifyMd5(){
    	return MD5.md5Encode("md5"+memberId.toString() + ableBalance.toString() + freezedBalance.toString());
    }
}