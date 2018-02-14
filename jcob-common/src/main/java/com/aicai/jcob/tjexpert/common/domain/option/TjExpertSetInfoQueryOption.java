package com.aicai.jcob.tjexpert.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月3日 上午10:53:42 
 * 程序的简单说明 
 */
public class TjExpertSetInfoQueryOption implements Serializable {

	private static final long serialVersionUID = -7509137379824128519L;

	private Long memberId ;
	private String nickName ;
	private String phone ;
	/**查询开始开始*/
	private Calendar startTime  ;
	
	/**查询结束时间*/
	private Calendar endTime ;

	public Long getMemberId() {
		return memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public String getPhone() {
		return phone;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
 