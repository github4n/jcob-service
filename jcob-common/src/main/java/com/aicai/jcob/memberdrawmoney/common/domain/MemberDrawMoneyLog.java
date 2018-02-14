package com.aicai.jcob.memberdrawmoney.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;

public class MemberDrawMoneyLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6443290520586134180L;

	/**  */
	private Long id;

	/** 第三方平台流水号 */
	private String thirdDrawNo;

	/**  */
	private Long memberId;

	/** 提款金额 */
	private BigDecimal amount;

	/** 手续费 */
	private BigDecimal handingCost;

	/** 提款状态：0提交申请，1提款已受理，2提款成功，3提款失败 */
	private Integer status = DrawMoneyStatus.submit.getIndex();

	/** 财务审核状态：0审核中，1审核通过，2审核未通过 */
	private Integer auditStatus = AuditStatus.auditing.getIndex();

	/** 审核人 */
	private String auditor;

	/** 审核人 */
	private String auditInfo;

	/**  */
	private Calendar auditTime;

	/** 提款描述，成功或者失败信息 */
	private String drawInfo;

	/**  */
	private Calendar createTime = Calendar.getInstance();

	/**  */
	private Calendar updateTime = Calendar.getInstance();

	/**
	 * 第三方通知响应时间
	 */
	private Calendar responseTime;
	/**
	 * 下发提款时间
	 */
	private Calendar requestTime;
	/**
	 * 提款方式id
	 */
	private Long drawMoneyWayId;

	private Integer clientType = ClientType.android.getIndex();
	private Integer channel = 1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThirdDrawNo() {
		return thirdDrawNo;
	}

	public void setThirdDrawNo(String thirdDrawNo) {
		this.thirdDrawNo = thirdDrawNo == null ? null : thirdDrawNo.trim();
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

	public BigDecimal getHandingCost() {
		return handingCost;
	}

	public void setHandingCost(BigDecimal handingCost) {
		this.handingCost = handingCost;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor == null ? null : auditor.trim();
	}

	public Calendar getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Calendar auditTime) {
		this.auditTime = auditTime;
	}

	public String getDrawInfo() {
		return drawInfo;
	}

	public void setDrawInfo(String drawInfo) {
		this.drawInfo = drawInfo == null ? null : drawInfo.trim();
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

	public Long getDrawMoneyWayId() {
		return drawMoneyWayId;
	}

	public void setDrawMoneyWayId(Long drawMoneyWayId) {
		this.drawMoneyWayId = drawMoneyWayId;
	}

	public String getDrawMoneyNo() {
		// 格式：时间+id
		String no = null;
		if (createTime != null && id != null) {
			no = DateUtil.dateToString(createTime.getTime(), "yyyyMMdd") + id;
		}
		return no;
	}

	public Calendar getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Calendar responseTime) {
		this.responseTime = responseTime;
	}

	public Calendar getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Calendar requestTime) {
		this.requestTime = requestTime;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
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

	public String getStatusStr() {
		return DrawMoneyStatus.valueOf(status).getDescription();
	}

	public String getAuditStatusStr() {
		return AuditStatus.valueOf(auditStatus).getDescription();
	}

	public String getClientTypeStr() {
		if (clientType == null) {
			return "";
		}
		return ClientType.valueOf(clientType).getDescription();
	}

}