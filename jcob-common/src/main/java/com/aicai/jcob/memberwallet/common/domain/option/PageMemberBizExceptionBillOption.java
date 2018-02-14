package com.aicai.jcob.memberwallet.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.memberwallet.common.domain.constant.AmountType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;

public class PageMemberBizExceptionBillOption implements Serializable {
	private static final long serialVersionUID = -2304519202265824539L;

	private Calendar startDate = DateUtil.getCurrentDayFirstTime();
	
	private Calendar endDate = Calendar.getInstance();
	
	private List<Long> memberIdList;
	
	private AmountType amountType;
	
	private WalletOpType walletOpType;

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

	public List<Long> getMemberIdList() {
		return memberIdList;
	}

	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
	}

	public AmountType getAmountType() {
		return amountType;
	}

	public void setAmountType(AmountType amountType) {
		this.amountType = amountType;
	}

	public WalletOpType getWalletOpType() {
		return walletOpType;
	}

	public void setWalletOpType(WalletOpType walletOpType) {
		this.walletOpType = walletOpType;
	}
	
	
}
