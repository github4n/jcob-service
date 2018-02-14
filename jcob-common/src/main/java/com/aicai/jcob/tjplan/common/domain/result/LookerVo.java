package com.aicai.jcob.tjplan.common.domain.result;

import java.io.Serializable;
import java.util.Calendar;

public class LookerVo implements Serializable {

	private static final long serialVersionUID = -1651960355663910525L;

	private Long memberId;
	
	private String nickName;
	
	private String phone;
	
	private Integer level;
	
    /** 用户头像路径 */
    private String icon;
    
    private Calendar lookedTime;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Calendar getLookedTime() {
		return lookedTime;
	}

	public void setLookedTime(Calendar lookedTime) {
		this.lookedTime = lookedTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	


}
