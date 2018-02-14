package com.aicai.jcob.tjrace.common.domain.option;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TjRaceSearchOption implements Serializable {

	private static final long serialVersionUID = 5352621008088136897L;

	/**
	 * 赛事分类 足球/篮球 参考com.aicai.jcob.tjrace.common.domain.constant.TjRaceType
	 */
	private Integer raceType;

	/**
	 * 哪一年的第几周,1032表示2010年的第32周
	 */
	private String weekOfYear;

	/**
	 * 赛事状态 参考com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus
	 */
	private Integer status;

	/**
	 * 多个赛事状态，如果statusList有值,则status无效
	 */
	private List<Integer> statusList;

	/**
	 * 比赛时间-起始
	 */
	private Date startMatchTime;

	/**
	 * 比赛时间-结束
	 */
	private Date endMatchTime;

	/**
	 * 排序方式 0 按matchNo和matchTime顺序 ; 1 按matchNo顺序，matchTime倒序
	 */
	private Integer order = 0;

	/**
	 * fxID是否存在 false 查询fxid为空或者亚盘大小球盘口为空的赛事
	 */
	private Boolean fxIdExist;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(String weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartMatchTime() {
		return startMatchTime;
	}

	public void setStartMatchTime(Date startMatchTime) {
		this.startMatchTime = startMatchTime;
	}

	public Date getEndMatchTime() {
		return endMatchTime;
	}

	public void setEndMatchTime(Date endMatchTime) {
		this.endMatchTime = endMatchTime;
	}

	public Integer getRaceType() {
		return raceType;
	}

	public void setRaceType(Integer raceType) {
		this.raceType = raceType;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
		this.status = null;
	}

	public Boolean getFxIdExist() {
		return fxIdExist;
	}

	public void setFxIdExist(Boolean fxIdExist) {
		this.fxIdExist = fxIdExist;
	}

	@Override
	public String toString() {
		return "TjRaceSearchOption [raceType=" + raceType + ", weekOfYear=" + weekOfYear + ", status=" + status
				+ ", startMatchTime=" + startMatchTime + ", endMatchTime=" + endMatchTime + "]";
	}

}
