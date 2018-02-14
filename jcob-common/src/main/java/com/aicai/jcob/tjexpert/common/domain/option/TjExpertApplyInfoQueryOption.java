package com.aicai.jcob.tjexpert.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月26日 下午2:49:19 
 * 专家申请审核查询option
 */
public class TjExpertApplyInfoQueryOption implements Serializable{

	private static final long serialVersionUID = 4346604457453215055L;
	
	/**用户id*/
	private Long memberId ;
	/**手机号*/
	private String phone ;
	/**昵称*/
	private String nickName ;
	/**专家审核状态*/
	private Integer checkStatus;
	/**专家审核查询开始开始*/
	private Calendar startTime ;
	/**专家审核查询结束时间*/
	private Calendar endTime ;
	
	public Long getMemberId() {
		return memberId;
	}
	public String getPhone() {
		return phone;
	}
	public String getNickName() {
		return nickName;
	}
	public Integer getCheckStatus() {
		return checkStatus;
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
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
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
 