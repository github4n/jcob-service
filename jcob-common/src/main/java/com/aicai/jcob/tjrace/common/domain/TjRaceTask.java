package com.aicai.jcob.tjrace.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

public class TjRaceTask implements Serializable {

	private static final long serialVersionUID = 75053634628965517L;

	/** 主键 */
	private Long id;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 任务类型
	 */
	private Integer taskType;

	/**
	 * 任务状态
	 */
	private Integer status;

	/**
	 * 任务取消
	 */
	private Integer isCancel;

	/**
	 * 赛事ID
	 */
	private Long raceId;
	/**
	 * 任务触发时间
	 */
	private Date excuteTime;
	/**
	 * 任务执行毫秒数
	 */
	private Long excuteMillisecond;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 次要属性json格式
	 */
	private String features = "{}";

	/**
	 * features版本号
	 */
	private Integer featuresVersion = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRaceId() {
		return raceId;
	}

	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}

	public Date getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
	}

	public Long getExcuteMillisecond() {
		return excuteMillisecond;
	}

	public void setExcuteMillisecond(Long excuteMillisecond) {
		this.excuteMillisecond = excuteMillisecond;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Integer getFeaturesVersion() {
		return featuresVersion;
	}

	public void setFeaturesVersion(Integer featuresVersion) {
		this.featuresVersion = featuresVersion;
	}

	public void setupFeature(String columnName, String value) {
		JSONObject jsonO = JSONObject.parseObject(features);
		jsonO.put(columnName, value);
		features = jsonO.toJSONString();
	}

	public String getFeature(String columnName) {
		JSONObject jsonO = JSONObject.parseObject(features);
		return jsonO.getString(columnName);
	}

	@Override
	public String toString() {
		return "TjRaceTask [id=" + id + ", taskName=" + taskName + ", taskType=" + taskType + ", status=" + status + ", raceId="
				+ raceId + ", excuteTime=" + excuteTime + ", excuteMillisecond=" + excuteMillisecond + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

}
