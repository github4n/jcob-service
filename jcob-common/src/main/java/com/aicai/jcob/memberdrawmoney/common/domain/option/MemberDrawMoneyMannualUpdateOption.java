package com.aicai.jcob.memberdrawmoney.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
/**
 * 人工下发提款，修改信息
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月22日
 */
public class MemberDrawMoneyMannualUpdateOption implements Serializable {

	private static final long serialVersionUID = -9051069064183429428L;
	/**
	 * 提款流水id
	 */
	private Long drawMoneyLogId;
	
	/**
	 * 后台操作员
	 */
	private String operator;
	/**
	 * 人工后台操作，确认提款订单状态
	 */
	private Integer status = DrawMoneyStatus.success.getIndex();
	
	/**
	 * 备注
	 */
	private String remark;
	

	public Long getDrawMoneyLogId() {
		return drawMoneyLogId;
	}

	public void setDrawMoneyLogId(Long drawMoneyLogId) {
		this.drawMoneyLogId = drawMoneyLogId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
