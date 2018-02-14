package com.aicai.jcob.member.common.domain.option;  

import java.io.Serializable;
import java.util.Calendar;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月27日 下午3:35:37 
 * 程序的简单说明 
 */
public class MemberOperLogQueryOption  implements Serializable{

	private static final long serialVersionUID = -2454598717521049242L;
	
	private Long[] memberIdArr ;
	
	private Calendar beginTime;
	
	private Calendar endTime;
	
	private String remark ;

	public Long[] getMemberIdArr() {
		return memberIdArr;
	}

	public void setMemberIdArr(Long[] memberIdArr) {
		this.memberIdArr = memberIdArr;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Calendar getBeginTime() {
		return beginTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	
	

}
 