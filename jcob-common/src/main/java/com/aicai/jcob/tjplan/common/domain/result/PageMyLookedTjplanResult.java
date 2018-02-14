package com.aicai.jcob.tjplan.common.domain.result;

import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjrace.common.domain.TjRace;

public class PageMyLookedTjplanResult extends TjPlan {
	private static final long serialVersionUID = 4404888275255210046L;
	
	private TjExpertInfo tjExpertInfo;
	
	private List<TjRace> tjRaceList;
	
	private Member member;
	/**
	 * 查看时间
	 */
	private Calendar lookedTime;
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
	public Calendar getLookedTime() {
		return lookedTime;
	}
	public void setLookedTime(Calendar lookedTime) {
		this.lookedTime = lookedTime;
	}
	
}
