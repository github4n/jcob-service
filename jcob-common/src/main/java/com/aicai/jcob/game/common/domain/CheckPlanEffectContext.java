package com.aicai.jcob.game.common.domain;

import java.io.Serializable;

/**
 * 开奖上下文的一些东西放在这
 * 
 * @author fx
 *
 */

public class CheckPlanEffectContext implements Serializable {

	private static final long serialVersionUID = 3566713732829067680L;

	private Long taskId;

	/** 本次开奖总方案数 **/
	private Integer totalNum = 0;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
