package com.aicai.jcob.member.common.result;

import java.io.Serializable;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.member.common.domain.MemberFeedback;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;

public class MemberFeedbackResult extends MemberFeedback implements Serializable {
	private static final long serialVersionUID = 1900523931670060133L;

	/** 昵称 */
	private String nickName;

	/** 身份 */
	private Integer level;

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

	public String getLevelStr() {
		if (level == null) {
			return "";
		}
		return TjExpertLevel.valueOf(level).getDescription();
	}

	public String getClientTypeStr() {
		return ClientType.valueOf(getClientType()).getDescription();
	}

}
