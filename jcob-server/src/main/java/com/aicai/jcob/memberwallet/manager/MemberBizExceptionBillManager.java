package com.aicai.jcob.memberwallet.manager;

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.option.PageMemberBizExceptionBillOption;

public interface MemberBizExceptionBillManager {
	
	/**
	 * 保存单据
	 * @param bill
	 * @return
	 */
	public MemberBizExceptionBill saveBizExceptionBill(MemberBizExceptionBill bill);
	/**
	 * 修改单据状态
	 * @param id
	 * @param newStatus
	 * @param oldStatus
	 * @return
	 */
	public Boolean updateBizExceptionBillStatus(Long id,Integer newStatus,Integer oldStatus);
	
	
	/**
	 * 分页查询
	 * @param billOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<MemberBizExceptionBill> pageBizExceptonBill(PageMemberBizExceptionBillOption billOption,DataPage<MemberBizExceptionBill> dataPage);
	
	public MemberBizExceptionBill queryBizExceptionBillById(Long id);
}
