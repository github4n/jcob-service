package com.aicai.jcob.tjexpert.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月26日 下午2:02:59 
 * 后台关注操作管理query option
 */
public class TjAttentionExpertQueryOption implements Serializable{

	private static final long serialVersionUID = 3052081670859796060L;
	/**用户id*/
	private Long memberId ;
	/**
	 * 关注的人的memberId
	 */
	private Long attentionMemberId ;
	/**手机号*/
	private String phone ;
	/**昵称*/
	private String nickName ;
	/**关注的人的昵称*/
	private String attentionNickName ;
	/**专家等级*/
	private Integer level ;
	/**最近关注记录开始时间*/
    private Calendar startDate ;
	
	/**最近关注记录结束时间*/
    private Calendar endDate ;

	public Long getMemberId() {
		return memberId;
	}

	public String getPhone() {
		return phone;
	}

	public String getNickName() {
		return nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public Long getAttentionMemberId() {
		return attentionMemberId;
	}

	public String getAttentionNickName() {
		return attentionNickName;
	}

	public void setAttentionMemberId(Long attentionMemberId) {
		this.attentionMemberId = attentionMemberId;
	}

	public void setAttentionNickName(String attentionNickName) {
		this.attentionNickName = attentionNickName;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	

}
 