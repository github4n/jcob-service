package com.aicai.jcob.memberwallet.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.memberwallet.common.domain.constant.AmountType;
import com.aicai.jcob.memberwallet.common.domain.constant.MemberBizExceptionBillStatus;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
/**
 * 异常加款扣款，异常单据
 * 
 * @author duannp
 * @date 2016年3月2日
 */
public class MemberBizExceptionBill implements Serializable {
	private static final long serialVersionUID = -1008650766684236396L;

	private Long id;
	
	private Long memberId;

    /** 异常单据金额 */
    private BigDecimal amount = BigDecimal.valueOf(0);

    /** 款项类型:1现金，2火眼 */
    private Integer amountType = AmountType.huoyan.getIndex();

    /** 加款，扣款 */
    private Integer walletOpType = WalletOpType.deduct_money.getIndex();

    /** 单据状态：0创建，1处理完成，2无效 */
    private Integer status = MemberBizExceptionBillStatus.create.getIndex();

    /** 操作员 */
    private String operator;

    /** 创建时间 */
    private Calendar createTime = Calendar.getInstance();

    /** 修改时间 */
    private Calendar updateTime = Calendar.getInstance();

    /** 异常单据说明 */
    private String remark;

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

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public Integer getWalletOpType() {
        return walletOpType;
    }

    public void setWalletOpType(Integer walletOpType) {
        this.walletOpType = walletOpType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}