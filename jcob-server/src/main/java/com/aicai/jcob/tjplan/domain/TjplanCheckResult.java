package com.aicai.jcob.tjplan.domain;

import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.tjplan.common.domain.TjPlanItem;

/**
 * 
 * 推荐方案check返回
 * @project jcob-server
 * @author duannp
 * @date 2016年2月3日
 */
public class TjplanCheckResult {

	private List<TjPlanItem> tjPlanItemList;
	
	private Calendar minMatchTime;
	
	private Calendar maxMatchTime;
	
	private Long minRaceId;
	
	private Long maxRaceId;
	
	private List<Long> raceIdList;

	public List<TjPlanItem> getTjPlanItemList() {
		return tjPlanItemList;
	}

	public void setTjPlanItemList(List<TjPlanItem> tjPlanItemList) {
		this.tjPlanItemList = tjPlanItemList;
	}

	public Calendar getMinMatchTime() {
		return minMatchTime;
	}

	public void setMinMatchTime(Calendar minMatchTime) {
		this.minMatchTime = minMatchTime;
	}

	public Calendar getMaxMatchTime() {
		return maxMatchTime;
	}

	public void setMaxMatchTime(Calendar maxMatchTime) {
		this.maxMatchTime = maxMatchTime;
	}

	public Long getMinRaceId() {
		return minRaceId;
	}

	public void setMinRaceId(Long minRaceId) {
		this.minRaceId = minRaceId;
	}

	public Long getMaxRaceId() {
		return maxRaceId;
	}

	public void setMaxRaceId(Long maxRaceId) {
		this.maxRaceId = maxRaceId;
	}

	public List<Long> getRaceIdList() {
		return raceIdList;
	}

	public void setRaceIdList(List<Long> raceIdList) {
		this.raceIdList = raceIdList;
	}
	
	
}
