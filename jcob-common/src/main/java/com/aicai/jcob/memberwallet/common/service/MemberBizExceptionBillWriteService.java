package com.aicai.jcob.memberwallet.common.service;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.option.PageMemberBizExceptionBillOption;

/**
 * 异常加款扣款单据管理接口
 * 
 * @project jcob-common
 * @author duannp
 * @date 2016年3月2日
 */
public interface MemberBizExceptionBillWriteService {

	/**
	 * 保存单据
	 * @param bill
	 * @return
	 */
	public ModelResult<MemberBizExceptionBill> saveBizExceptionBill(MemberBizExceptionBill bill);
	/**
	 * 修改单据状态
	 * @param id
	 * @param newStatus
	 * @param oldStatus
	 * @return
	 */
	public ModelResult<Boolean> updateBizExceptionBillStatus(Long id,Integer newStatus,Integer oldStatus);
	
	/**
	 * 执行异常单据，进行加款或者扣款
	 * @param id
	 * @return
	 */
	public ModelResult<Boolean> excuteBizExceptionBill(Long id);
	
	/**
	 * 分页查询
	 * @param billOption
	 * @param dataPage
	 * @return
	 */
	public PageResult<MemberBizExceptionBill> pageBizExceptonBill(PageMemberBizExceptionBillOption billOption,DataPage<MemberBizExceptionBill> dataPage);
	/**
	 * 按id查询对象
	 * @param id
	 * @return
	 */
	public ModelResult<MemberBizExceptionBill> queryBizExceptionBillById(Long id);
}
