package com.aicai.jcob.tjplan.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
/**
 * 我查看的 推荐分页查询参数
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月24日
 */
public class PageLookedTjPlanOption implements Serializable{
	private static final long serialVersionUID = 6294056666933296582L;

	/**
	 * 赛事状态
	 */
	private TjRaceStatus tjRaceStatus;
	
	/**
	 * 是否命中
	 */
	private Boolean isHit;
	
	/**
	 * 起始时间
	 */
	private Calendar startDate;
	/**
	 * 截至时间
	 */
	private Calendar endDate;


	public TjRaceStatus getTjRaceStatus() {
		return tjRaceStatus;
	}

	public void setTjRaceStatus(TjRaceStatus tjRaceStatus) {
		this.tjRaceStatus = tjRaceStatus;
	}

	public Boolean getIsHit() {
		return isHit;
	}

	public void setIsHit(Boolean isHit) {
		this.isHit = isHit;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

}
 