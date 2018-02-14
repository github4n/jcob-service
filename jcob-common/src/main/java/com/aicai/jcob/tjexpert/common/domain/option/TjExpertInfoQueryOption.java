package com.aicai.jcob.tjexpert.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月25日 下午4:02:35 
 * 专家成长信息查询option
 */
public class TjExpertInfoQueryOption implements Serializable{
	private static final long serialVersionUID = 1L;
	/**用户id*/
	private Long memberId ;
	/**手机号*/
	private String phone ;
	/**昵称*/
	private String nickName ;
	/**专家等级*/
	private Integer level ;
	/**查询开始开始*/
	private Calendar startTime ;
	
	/**查询结束时间*/
	private Calendar endTime ;
	
	public String getPhone() {
		return phone;
	}
	public String getNickName() {
		return nickName;
	}
	public Integer getLevel() {
		return level;
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
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public Calendar getStartTime() {
		return startTime;
	}
	public Calendar getEndTime() {
		return endTime;
	}
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
}
 