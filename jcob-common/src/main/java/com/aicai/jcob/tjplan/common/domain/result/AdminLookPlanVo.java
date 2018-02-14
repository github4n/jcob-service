package com.aicai.jcob.tjplan.common.domain.result;

import java.io.Serializable;
import java.util.List;

import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.TjPlanItem;
import com.aicai.jcob.tjrace.common.domain.TjRace;
/**
 * 后台查看推荐log方案详情
 * @project jcob-common
 * @author duannp
 * @date 2016年3月3日
 */
public class AdminLookPlanVo implements Serializable{
	private static final long serialVersionUID = -5802825726349454388L;
	
	private Member  lookerMember;
	
	private TjExpertInfo lookerTjExpertInfo;
	
	private Member expertMember;
	
	private TjExpertInfo expertTjExpertInfo;
	
	private TjPlan tjPlan;
	
	private TjLookLog tjLookLog;
	
	private List<TjRace> tjRaceList;
	
	private List<TjPlanItem> planItemList;

	public Member getLookerMember() {
		return lookerMember;
	}

	public void setLookerMember(Member lookerMember) {
		this.lookerMember = lookerMember;
	}

	public TjExpertInfo getLookerTjExpertInfo() {
		return lookerTjExpertInfo;
	}

	public void setLookerTjExpertInfo(TjExpertInfo lookerTjExpertInfo) {
		this.lookerTjExpertInfo = lookerTjExpertInfo;
	}

	public Member getExpertMember() {
		return expertMember;
	}

	public void setExpertMember(Member expertMember) {
		this.expertMember = expertMember;
	}

	public TjExpertInfo getExpertTjExpertInfo() {
		return expertTjExpertInfo;
	}

	public void setExpertTjExpertInfo(TjExpertInfo expertTjExpertInfo) {
		this.expertTjExpertInfo = expertTjExpertInfo;
	}

	public TjPlan getTjPlan() {
		return tjPlan;
	}

	public void setTjPlan(TjPlan tjPlan) {
		this.tjPlan = tjPlan;
	}

	public TjLookLog getTjLookLog() {
		return tjLookLog;
	}

	public void setTjLookLog(TjLookLog tjLookLog) {
		this.tjLookLog = tjLookLog;
	}

	public List<TjRace> getTjRaceList() {
		return tjRaceList;
	}

	public void setTjRaceList(List<TjRace> tjRaceList) {
		this.tjRaceList = tjRaceList;
	}

	public List<TjPlanItem> getPlanItemList() {
		return planItemList;
	}

	public void setPlanItemList(List<TjPlanItem> planItemList) {
		this.planItemList = planItemList;
	}

}
