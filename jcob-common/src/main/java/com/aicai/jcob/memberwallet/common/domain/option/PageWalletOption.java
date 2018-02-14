package com.aicai.jcob.memberwallet.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;

public class PageWalletOption implements Serializable {
	private static final long serialVersionUID = -2304519202265824539L;
	private String phone;

	private String nickName;

	private List<Long> memberIdList;

	private BigDecimal leftAndEqualAmount;

	private BigDecimal rightAndEqualAmount;

	private List<TjExpertLevel> tjExpertLevelList;

	private Long memberId;

	private Integer expertLevel;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public BigDecimal getLeftAndEqualAmount() {
		return leftAndEqualAmount;
	}

	public void setLeftAndEqualAmount(BigDecimal leftAndEqualAmount) {
		this.leftAndEqualAmount = leftAndEqualAmount;
	}

	public BigDecimal getRightAndEqualAmount() {
		return rightAndEqualAmount;
	}

	public void setRightAndEqualAmount(BigDecimal rightAndEqualAmount) {
		this.rightAndEqualAmount = rightAndEqualAmount;
	}

	public List<TjExpertLevel> getTjExpertLevelList() {
		return tjExpertLevelList;
	}

	public void setTjExpertLevelList(List<TjExpertLevel> tjExpertLevelList) {
		this.tjExpertLevelList = tjExpertLevelList;
	}

	public List<Long> getMemberIdList() {
		return memberIdList;
	}

	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getExpertLevel() {
		return expertLevel;
	}

	public void setExpertLevel(Integer expertLevel) {
		this.expertLevel = expertLevel;
	}

}
