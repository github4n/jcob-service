package com.aicai.jcob.tjplan.common.domain.result;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjrace.common.domain.TjRace;

/**
 * 前端方案view object
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月1日
 */
public class TjPlanVo implements Serializable {
	private static final long serialVersionUID = -2927928652630937964L;
	
	private TjPlan tjPlan;
	private List<TjPlanItem> tjPlanItemList;
	
	private TjExpertInfo tjExpertInfo;
	
	private List<TjRace> tjRaceList;
	
	private Member member;
	
	private Boolean isLooked = false;
	private Calendar lookedTime = Calendar.getInstance();
	public TjPlan getTjPlan() {
		return tjPlan;
	}
	public void setTjPlan(TjPlan tjPlan) {
		this.tjPlan = tjPlan;
	}
	public List<TjPlanItem> getTjPlanItemList() {
		return tjPlanItemList;
	}
	public void setTjPlanItemList(List<TjPlanItem> tjPlanItemList) {
		this.tjPlanItemList = tjPlanItemList;
	}
	public TjExpertInfo getTjExpertInfo() {
		return tjExpertInfo;
	}
	public void setTjExpertInfo(TjExpertInfo tjExpertInfo) {
		this.tjExpertInfo = tjExpertInfo;
	}
	public List<TjRace> getTjRaceList() {
		return tjRaceList;
	}
	public void setTjRaceList(List<TjRace> tjRaceList) {
		this.tjRaceList = tjRaceList;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Boolean getIsLooked() {
		return isLooked;
	}
	public void setIsLooked(Boolean isLooked) {
		this.isLooked = isLooked;
	}
	public Calendar getLookedTime() {
		return lookedTime;
	}
	public void setLookedTime(Calendar lookedTime) {
		this.lookedTime = lookedTime;
	}
	
	
}
