package com.aicai.jcob.tjplan.common.domain.result;

import java.io.Serializable;
import java.util.List;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjrace.common.domain.TjRace;

public class PageTjplanToHallResult implements Serializable {
	private static final long serialVersionUID = -3166596558767137936L;
	
	private TjPlan tjPlan;
	
	private TjExpertInfo tjExpertInfo;
	
	private List<TjRace> tjRaceList;
	
	private List<TjPlanItem> tjPlanItemList;
	
	private Member member;
	/**
	 * 是否已经查看
	 */
	private boolean isLooked = false;
	public TjPlan getTjPlan() {
		return tjPlan;
	}

	public void setTjPlan(TjPlan tjPlan) {
		this.tjPlan = tjPlan;
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

	public boolean isLooked() {
		return isLooked;
	}

	public void setLooked(boolean isLooked) {
		this.isLooked = isLooked;
	}

	public List<TjPlanItem> getTjPlanItemList() {
		return tjPlanItemList;
	}

	public void setTjPlanItemList(List<TjPlanItem> tjPlanItemList) {
		this.tjPlanItemList = tjPlanItemList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
