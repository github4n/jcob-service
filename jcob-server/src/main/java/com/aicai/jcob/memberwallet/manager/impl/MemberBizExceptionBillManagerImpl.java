package com.aicai.jcob.memberwallet.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.option.PageMemberBizExceptionBillOption;
import com.aicai.jcob.memberwallet.manager.MemberBizExceptionBillManager;

public class MemberBizExceptionBillManagerImpl implements MemberBizExceptionBillManager {
	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Override
	public MemberBizExceptionBill saveBizExceptionBill(MemberBizExceptionBill bill) {
		memberDbDao.insertAndSetupId("MemberBizExceptionBillMapper.saveBizExceptionBill", bill);
		return bill;
	}

	@Override
	public Boolean updateBizExceptionBillStatus(Long id, Integer newStatus,Integer oldStatus) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id",id );
		paramMap.put("newStatus", newStatus);
		paramMap.put("oldStatus", oldStatus);
		return memberDbDao.update("MemberBizExceptionBillMapper.updateBizExceptionBillStatus", paramMap) == 1?true:false;
	}

	@Override
	public DataPage<MemberBizExceptionBill> pageBizExceptonBill(PageMemberBizExceptionBillOption billOption,
			DataPage<MemberBizExceptionBill> dataPage) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", billOption.getStartDate());
		paramMap.put("endDate", billOption.getEndDate());
		paramMap.put("memberIdList", billOption.getMemberIdList());
		if (billOption.getAmountType() != null) {
			paramMap.put("amountType",billOption.getAmountType().getIndex());
		}
		if (billOption.getWalletOpType() != null) {
			paramMap.put("walletOpType",billOption.getWalletOpType().getIndex());
		}
		return memberDbDao.queryPage("MemberBizExceptionBillMapper.countBizExceptionBill", "MemberBizExceptionBillMapper.pageBizExceptionBill", paramMap, dataPage);
	}

	@Override
	public MemberBizExceptionBill queryBizExceptionBillById(Long id) {
		return memberDbDao.queryUnique("MemberBizExceptionBillMapper.selectByPrimaryKey", id);
	}
	
	

}
