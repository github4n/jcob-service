package com.aicai.jcob.memberdrawmoney.common.domain.result;

import java.io.Serializable;

import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;

public class MemberDrawMoneyWayResult extends MemberDrawMoneyWay implements Serializable {

	private static final long serialVersionUID = 5289498806882796267L;

	/** 昵称 */
	private String nickName;

	/** 真实姓名 */
	private String realName;

	/** 身份 */
	private Integer level;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
