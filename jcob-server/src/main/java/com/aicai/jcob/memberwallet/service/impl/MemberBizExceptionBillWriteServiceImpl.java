package com.aicai.jcob.memberwallet.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.constant.MemberBizExceptionBillStatus;
import com.aicai.jcob.memberwallet.common.domain.option.PageMemberBizExceptionBillOption;
import com.aicai.jcob.memberwallet.common.service.MemberBizExceptionBillWriteService;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberBizExceptionBillManager;

public class MemberBizExceptionBillWriteServiceImpl implements
		MemberBizExceptionBillWriteService {
	private final transient Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberBizExceptionBillManager memberBizExceptionBillManager;
	@Autowired
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	@Override
	public ModelResult<MemberBizExceptionBill> saveBizExceptionBill(MemberBizExceptionBill bill) {
		ModelResult<MemberBizExceptionBill> modelResult = new ModelResult<MemberBizExceptionBill>();
		if (bill == null) {
			return modelResult.withError("param.not.null","参数不能为null");
		}
		try {
			MemberBizExceptionBill retBill = memberBizExceptionBillManager.saveBizExceptionBill(bill);
			ModelResult<Boolean> bizResult = excuteBizExceptionBill(retBill.getId());
			if (!bizResult.isSuccess() || !bizResult.getModel()) {
				modelResult.withError(bizResult.getErrorCode(), bizResult.getErrorMsg());
			}
			modelResult.setModel(retBill);
		} catch (Exception e) {
			logger.error("接口MemberBizExceptionBillWriteServiceImpl.saveBizExceptionBill异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> updateBizExceptionBillStatus(Long id,Integer newStatus, Integer oldStatus) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		if (id == null || newStatus == null || oldStatus == null) {
			return modelResult.withError("param.not.null","参数不能为null");
		}
		try {
			Boolean ret = memberBizExceptionBillManager.updateBizExceptionBillStatus(id, newStatus, oldStatus);
			modelResult.setModel(ret);
		} catch (Exception e) {
			logger.error("接口MemberBizExceptionBillWriteServiceImpl.updateBizExceptionBillStatus异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> excuteBizExceptionBill(Long id) {
		ModelResult<Boolean> modelResult = memberBizDirverWalletManager.handleMemberBizExceptionBill(id);
		if (modelResult.isSuccess()) {
			memberBizExceptionBillManager.updateBizExceptionBillStatus(id, MemberBizExceptionBillStatus.complete.getIndex(), MemberBizExceptionBillStatus.create.getIndex());
		}else {
			memberBizExceptionBillManager.updateBizExceptionBillStatus(id, MemberBizExceptionBillStatus.invalid.getIndex(), MemberBizExceptionBillStatus.create.getIndex());
		}
		return modelResult;
	}

	@Override
	public PageResult<MemberBizExceptionBill> pageBizExceptonBill(PageMemberBizExceptionBillOption billOption,
			DataPage<MemberBizExceptionBill> dataPage) {
		PageResult<MemberBizExceptionBill> pageResult = new PageResult<MemberBizExceptionBill>();
		if (billOption == null || dataPage == null) {
			return pageResult.withError("param.not.null","参数不能为null");
		}
		try {
			DataPage<MemberBizExceptionBill> retDataPage = memberBizExceptionBillManager.pageBizExceptonBill(billOption, dataPage);
			pageResult.setPage(retDataPage);
		} catch (Exception e) {
			logger.error("接口MemberBizExceptionBillWriteServiceImpl.pageBizExceptonBill异常:",e);
			pageResult.withError("runtime.exception","运行时异常!");
		}
		return pageResult;
	}

	@Override
	public ModelResult<MemberBizExceptionBill> queryBizExceptionBillById(Long id) {
		ModelResult<MemberBizExceptionBill> modelResult = new ModelResult<MemberBizExceptionBill>();
		if (id == null ) {
			return modelResult.withError("param.not.null","参数不能为null");
		}
		try {
			MemberBizExceptionBill memberBizExceptionBill = memberBizExceptionBillManager.queryBizExceptionBillById(id);
			modelResult.setModel(memberBizExceptionBill);
		} catch (Exception e) {
			logger.error("接口MemberBizExceptionBillWriteServiceImpl.queryBizExceptionBillById异常:",e);
			modelResult.withError("runtime.exception","运行时异常!");
		}
		return modelResult;
	}

}
