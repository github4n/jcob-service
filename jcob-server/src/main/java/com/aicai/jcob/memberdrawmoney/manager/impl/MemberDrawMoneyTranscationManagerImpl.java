package com.aicai.jcob.memberdrawmoney.manager.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyLogManager;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyTranscationManager;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
/**
 * 提款操作事物管理实现
 * @project jcob-server
 * @author duannp
 * @date 2016年2月23日
 */
public class MemberDrawMoneyTranscationManagerImpl implements MemberDrawMoneyTranscationManager {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("transactionTemplateMember")
	private TransactionTemplate transactionTemplateMember;
	@Autowired
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	@Autowired
	private MemberDrawMoneyLogManager memberDrawMoneyLogManager;
	@Override
	public ModelResult<MemberDrawMoneyLog> memberDrawMoneyWithTranscation(
			MemberDrawMoneyLog memberDrawMoneyLog) {
		ModelResult<MemberDrawMoneyLog> modelResult = new ModelResult<MemberDrawMoneyLog>();
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//保存提款记录
					MemberDrawMoneyLog memberDrawMoneyLogReturn  = memberDrawMoneyLogManager.saveDrawMoneyLog(memberDrawMoneyLog);
					//钱包操作
					ModelResult<Boolean> walletOpModelResult = memberBizDirverWalletManager.memberDrawMoney(memberDrawMoneyLogReturn);
					if (!walletOpModelResult.isSuccess()) {
						throw new JcobServerException(new ExceptionCode("member.wallet.op.exception", "用户钱包操作异常!"));
					}
					modelResult.setModel(memberDrawMoneyLogReturn);
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],提款[{}]操作异常：",memberDrawMoneyLog.getMemberId(),memberDrawMoneyLog.getDrawMoneyNo());
			logger.error(errMsg,e);
			modelResult.withError("member.drawmoney.op.exeception", "用户提款操作异常!");
		}
		return modelResult;
	}
	
	/**
	 * 自动审核提款
	 * @param drawMoneyLogId
	 */
	public void autoAuditDrawMoneyLog(Long drawMoneyLogId){
		BigDecimal allowAutoAuditMaxAmount = BigDecimal.valueOf(1000);
		MemberDrawMoneyLog drawMoneyLogFromDb = memberDrawMoneyLogManager.queryDrawMoneyLogById(drawMoneyLogId);
		logger.info("提款审核-ID[{}],amount[{}],allowAutoAuditMaxAmount[{}]自动审核!",drawMoneyLogFromDb.getId(),drawMoneyLogFromDb.getAmount(),allowAutoAuditMaxAmount);
		if (drawMoneyLogFromDb.getAuditStatus() != AuditStatus.auditing.getIndex()
				|| drawMoneyLogFromDb.getStatus() != DrawMoneyStatus.submit.getIndex()) {
			//不是初始化状态，不需要审核了
			return ;
		}
		if (drawMoneyLogFromDb.getAmount().compareTo(allowAutoAuditMaxAmount) > 0) {
			//金额不符合，不自动审核
			return;
		}
		//初略条件判断完了，直接审核通过
		memberDrawMoneyLogManager.updateDrawMoneyLogAuditInfo(drawMoneyLogId, AuditStatus.audit_pass.getIndex(), "系统", "提款自动审核通过");
	}

}
