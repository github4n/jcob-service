package com.aicai.jcob.tjexpert.common.domain.result;

import java.io.Serializable;

import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjexpert.common.domain.utils.ExpertRecordUtils;

/**
 * @author jing.ming
 * @version 创建时间：2016年2月24日 下午5:21:40 后台admin专家战绩查询result
 */
public class TjExpertAdminWinRatioResult extends TjExpertInfo implements Serializable {
	private static final long serialVersionUID = 8966396011635011691L;

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public String getFormatRecord() {
		return ExpertRecordUtils.getFormatRecord(getRecord());
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLevelStr() {
		if (getLevel() == null) {
			return "";
		}
		return TjExpertLevel.valueOf(getLevel()).getDescription();
	}

}
