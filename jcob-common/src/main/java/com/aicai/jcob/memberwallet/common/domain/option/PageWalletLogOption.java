package com.aicai.jcob.memberwallet.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;

public class PageWalletLogOption implements Serializable {
	private static final long serialVersionUID = -2304519202265824539L;
	private Calendar startDate = DateUtil.getCurrentDayFirstTime();

	private Calendar endDate = Calendar.getInstance();
	private String phone;

	private String nickName;

	private List<Long> memberIdList;

	private BigDecimal leftAndEqualAmount;

	private BigDecimal rightAndEqualAmount;
	/**
	 * 专家等级
	 */
	private List<TjExpertLevel> tjExpertLevelList;

	private List<BizType> bizTypeList;

	private Integer walletOpType;

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

	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}

	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Integer getWalletOpType() {
		return walletOpType;
	}

	public void setWalletOpType(Integer walletOpType) {
		this.walletOpType = walletOpType;
	}

	@Override
	public String toString() {
		return "PageWalletLogOption [startDate=" + DateUtil.getDateString2(startDate.getTime()) + ", endDate="
				+ DateUtil.getDateString2(endDate.getTime()) + ", phone=" + phone + ", nickName=" + nickName + ", memberIdList="
				+ memberIdList + ", leftAndEqualAmount=" + leftAndEqualAmount + ", rightAndEqualAmount=" + rightAndEqualAmount
				+ ", tjExpertLevelList=" + tjExpertLevelList + ", bizTypeList=" + bizTypeList + ", walletOpType=" + walletOpType
				+ "]";
	}

}
