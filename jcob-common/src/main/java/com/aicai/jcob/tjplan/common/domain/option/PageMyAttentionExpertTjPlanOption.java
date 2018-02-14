package com.aicai.jcob.tjplan.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
/**
 * 我发布的 推荐分页查询参数
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年2月26日
 */
public class PageMyAttentionExpertTjPlanOption implements Serializable{
	private static final long serialVersionUID = -3256043981790843262L;
	/**
	 * 我关注的专家id列表
	 */
	private List<Long> myAttentionExpertMemberIdList;
	
	private Calendar startDate;
	
	private Calendar endDate;
	public List<Long> getMyAttentionExpertMemberIdList() {
		return myAttentionExpertMemberIdList;
	}
	public void setMyAttentionExpertMemberIdList(
			List<Long> myAttentionExpertMemberIdList) {
		this.myAttentionExpertMemberIdList = myAttentionExpertMemberIdList;
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
 