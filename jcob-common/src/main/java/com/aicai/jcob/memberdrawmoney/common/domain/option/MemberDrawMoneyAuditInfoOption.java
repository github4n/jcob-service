package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;

import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.alibaba.fastjson.JSON;

public class MemberDrawMoneyAuditInfoOption implements Serializable {

	private static final long serialVersionUID = -9051069064183429428L;
	/**
	 * 提款流水
	 */
	private Long drawMoneyLogId;

	/**
	 * 审核状态
	 */
	private Integer auditStatus;

	/**
	 * 审核人
	 */
	private String auditor;

	/**
	 * 记录审核失败内容
	 */
	private String auditInfo;

	public MemberDrawMoneyAuditInfoOption(Long drawMoneyLogId, Integer auditStatus, String auditor, String auditInfo) {
		this.drawMoneyLogId = drawMoneyLogId;
		this.auditStatus = auditStatus;
		this.auditor = auditor;
		this.auditInfo = auditInfo;
	}

	public MemberDrawMoneyAuditInfoOption() {

	}

	public Long getDrawMoneyLogId() {
		return drawMoneyLogId;
	}

	public void setDrawMoneyLogId(Long drawMoneyLogId) {
		this.drawMoneyLogId = drawMoneyLogId;
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
		this.auditor = auditor;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	@Override
	public String toString() {
		return "MemberDrawMoneyAuditInfoOption [drawMoneyLogId=" + drawMoneyLogId + ", auditStatus=" + auditStatus + ", auditor="
				+ auditor + ", auditInfo=" + auditInfo + "]";
	}
	
	public static void main(String[] args){
		MemberDrawMoneyAuditInfoOption auditInfoOption = new MemberDrawMoneyAuditInfoOption();
		auditInfoOption.setAuditor("test");
		auditInfoOption.setAuditInfo("连连提款测试");
		auditInfoOption.setAuditStatus(AuditStatus.audit_pass.getIndex());
		auditInfoOption.setDrawMoneyLogId(61L);
		System.out.println(JSON.toJSON(auditInfoOption));
	}

}
