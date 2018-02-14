package com.aicai.jcob.memberwallet.manager.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.common.GlobalParam;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
import com.aicai.jcob.memberwallet.common.domain.MemberBizExceptionBill;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWalletLog;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.AmountType;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.MemberBizExceptionBillStatus;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.domain.MemberWalletContext;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberBizExceptionBillManager;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.constant.TjOrderPayStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.alibaba.fastjson.JSON;

public class MemberBizDirverWalletManagerImpl implements MemberBizDirverWalletManager {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GlobalParam globalParam;
	
	@Autowired
	@Qualifier("transactionTemplateMember")
	private TransactionTemplate transactionTemplateMember;
	@Autowired
	private MemberWalletManager memberWalletManager;
	@Autowired
	private MemberHuoyanWalletManager memberHuoyanWalletManager;
	@Autowired
	private MemberWalletLogManager memberWalletLogManager;
	@Autowired
	private MemberHuoyanWalletLogManager memberHuoyanWalletLogManager;
	@Autowired
	private MemberBizExceptionBillManager memberBizExceptionBillManager;
	
	@Override
	public ModelResult<Boolean> memberCharge(MemberChargeLog memberChargeLog) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			MemberWalletContext walletContext = new MemberWalletContext();
			walletContext.setMemberChargeLog(memberChargeLog);
			walletHandleWithCharge(BizType.charge, walletContext);
		} catch (JcobServerException e) {
			modelResult.setModel(false);
			modelResult.withError(e.getCode(), e.gerMsg());
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> memberDrawMoney(
			MemberDrawMoneyLog memberDrawMoneyLog) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			MemberWalletContext walletContext = new MemberWalletContext();
			walletContext.setMemberDrawMoneyLog(memberDrawMoneyLog);
			walletHandleWithDrawMoney(BizType.draw_money, walletContext);
		} catch (JcobServerException e) {
			modelResult.setModel(false);
			modelResult.withError(e.getCode(), e.gerMsg());
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> memberLookPlan(TjPlan tjPlan, TjOrder tjOrder) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			MemberWalletContext walletContext = new MemberWalletContext();
			walletContext.setTjPlan(tjPlan);
			walletContext.setTjOrder(tjOrder);
			walletHandleWithLookPlan(BizType.look_plan, walletContext);
		} catch (JcobServerException e) {
			modelResult.setModel(false);
			modelResult.withError(e.getCode(), e.gerMsg());
		}
		return modelResult;
	}
	
	@Override
	public ModelResult<Boolean> refundMemberDrawMoney(MemberDrawMoneyLog memberDrawMoneyLog) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			MemberWalletContext walletContext = new MemberWalletContext();
			walletContext.setMemberDrawMoneyLog(memberDrawMoneyLog);
			walletHandleWithRefundDrawMoney(BizType.draw_money, walletContext);
		} catch (JcobServerException e) {
			modelResult.setModel(false);
			modelResult.withError(e.getCode(), e.gerMsg());
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> refundMemberLookPlan(TjPlan tjPlan,
			TjOrder tjOrder) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			MemberWalletContext walletContext = new MemberWalletContext();
			walletContext.setTjPlan(tjPlan);
			walletContext.setTjOrder(tjOrder);
			walletHandleWithRefundLookPlan(BizType.look_plan, walletContext);
		} catch (JcobServerException e) {
			modelResult.setModel(false);
			modelResult.withError(e.getCode(), e.gerMsg());
		}
		return modelResult;
	}

	
	
	/**
	 * 充值业务钱包操作
	 * @param member
	 * @param walletOpType
	 * @param bizType
	 * @param walletContext
	 */
	private void walletHandleWithCharge(BizType bizType,MemberWalletContext walletContext){
		MemberChargeLog memberChargeLog = walletContext.getMemberChargeLog();
		if (memberChargeLog == null) {
			throw new JcobServerException(new ExceptionCode("biz.obj.notnull", "业务对象不能为空!"));
		}
		if (memberChargeLog.getStatus() != ChargeStatus.success.getIndex()) {
			throw new JcobServerException(new ExceptionCode("biz.status.error", "业务状态错误!"));
		}
		logger.info("memberId[{}],充值钱包操作!chargeLog[{}]",memberChargeLog.getMemberId(),JSON.toJSONString(walletContext.getMemberChargeLog()));
		
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					BigDecimal happenCashAmout = memberChargeLog.getAmount().subtract(memberChargeLog.getHandingCost());
					//现金到火眼有个兑换比率
					BigDecimal happenHuoyanAmout = happenCashAmout.multiply(memberChargeLog.getCashTOHuoyanRatio());
					walletContext.setClientType(memberChargeLog.getClientType());
					walletContext.setChannel(memberChargeLog.getChannel());
					//用户火眼增加
					memberHuoyanWalletManager.addAbleBalanceWithoutTransaction(memberChargeLog.getMemberId(), happenHuoyanAmout, bizType, memberChargeLog.getId(), memberChargeLog.getChargeNo(), walletContext);
					//系统钱包加钱
					memberWalletManager.addAbleBalanceWithoutTransaction(globalParam.getSysMemberId(), happenCashAmout, bizType, memberChargeLog.getId(), memberChargeLog.getChargeNo(), walletContext);
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],充值[%s]钱包操作异常：",memberChargeLog.getMemberId(),walletContext.getMemberChargeLog().getChargeNo());
			logger.error(errMsg,e);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "用户钱包操作异常!"));
		}
		
	}
	/**
	 * 查看付费方案业务钱包操作
	 * @param member
	 * @param walletOpType
	 * @param bizType
	 * @param walletContext
	 */
	private void walletHandleWithLookPlan(BizType bizType,MemberWalletContext walletContext){
		TjPlan tjPlan = walletContext.getTjPlan();
		TjOrder tjOrder = walletContext.getTjOrder();
		if (tjPlan == null || tjOrder == null) {
			throw new JcobServerException(new ExceptionCode("biz.obj.notnull", "业务对象不能为空!"));
		}
		if (tjPlan.getRaceStatus() != TjRaceStatus.not_match.getIndex() || tjOrder.getPayStatus() == TjOrderPayStatus.payed || tjPlan.getAmount().compareTo(BigDecimal.valueOf(0)) == 0) {
			throw new JcobServerException(new ExceptionCode("biz.status.error", "业务状态错误!"));
		}
		logger.info("memberId[{}],查看付费方案钱包操作!tjPlan[{}],tjOrder[{}]",tjOrder.getMemberId(),JSON.toJSONString(walletContext.getTjPlan()),JSON.toJSONString(walletContext.getTjOrder()));
		
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//现金到火眼有个兑换比率
					BigDecimal happenCashAmout = tjOrder.getAmount().divide(tjOrder.getCashTOHuoyanRatio());
					BigDecimal happenHuoyanAmout = tjOrder.getAmount();
					walletContext.setClientType(tjOrder.getClientType());
					walletContext.setChannel(tjOrder.getChannel());
					//用户火眼钱包扣钱
					memberHuoyanWalletManager.subtractAbleBalanceWithoutTransaction(tjOrder.getMemberId(), happenHuoyanAmout, bizType, tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
					//专家钱包加钱
					memberWalletManager.addAbleBalanceWithoutTransaction(tjPlan.getMemberId(), happenCashAmout, bizType, tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
					//系统钱包减钱
					memberWalletManager.subtractAbleBalanceWithoutTransaction(globalParam.getSysMemberId(), happenCashAmout, bizType,tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],查看付费方案[%s]钱包操作异常：", tjOrder.getMemberId(),walletContext.getTjPlan().getTjPlanNo());
			logger.error(errMsg,e);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "用户钱包操作异常!"));
		}
		
	}
	
	/**
	 * 查看付费方案退回业务钱包操作
	 * @param member
	 * @param walletOpType
	 * @param bizType
	 * @param walletContext
	 */
	private void walletHandleWithRefundLookPlan(BizType bizType,MemberWalletContext walletContext){
		TjPlan tjPlan = walletContext.getTjPlan();
		TjOrder tjOrder = walletContext.getTjOrder();
		if (tjPlan == null || tjOrder == null) {
			throw new JcobServerException(new ExceptionCode("biz.obj.notnull", "业务对象不能为空!"));
		}
		if ( tjOrder.getPayStatus() != TjOrderPayStatus.payed || tjPlan.getAmount().compareTo(BigDecimal.valueOf(0)) == 0) {
			throw new JcobServerException(new ExceptionCode("biz.status.error", "业务状态错误!"));
		}
		logger.info("memberId[{}],退款查看付费方案钱包操作!tjPlan[{}],tjOrder[{}]",tjOrder.getMemberId(),JSON.toJSONString(walletContext.getTjPlan()),JSON.toJSONString(walletContext.getTjOrder()));
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					MemberHuoyanWalletLog deductHuoyanWalletLog = memberHuoyanWalletLogManager.queryMemberWalletLogByBiz(tjOrder.getMemberId(), bizType, tjOrder.getId(), WalletOpType.deduct_money);
					if (deductHuoyanWalletLog == null) {
						String errMsg = String.format("memberId[%s],退款查看付费方案[{}]付费钱包流水为null!：",tjOrder.getMemberId(),walletContext.getTjOrder().getTjOrderNo());
						logger.error(errMsg);
						throw new JcobServerException(new ExceptionCode("biz.wallet.log.null", "业务操作钱包流水为null!"));
					}
					//签名验证
					if (!deductHuoyanWalletLog.getVerifyMd5().equals(deductHuoyanWalletLog.getCalculateVerifyMd5())) {
						String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",deductHuoyanWalletLog.getMemberId(),bizType.getDescription(),deductHuoyanWalletLog.getId(),deductHuoyanWalletLog.getVerifyMd5(),deductHuoyanWalletLog.getCalculateVerifyMd5());
						logger.error(errMsg);
						throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
					}
					//现金到火眼有个兑换比率
					BigDecimal happenCashAmout = tjOrder.getAmount().divide(tjOrder.getCashTOHuoyanRatio());
					BigDecimal happenHuoyanAmout = tjOrder.getAmount();
					walletContext.setClientType(tjOrder.getClientType());
					walletContext.setChannel(tjOrder.getChannel());
					//用户火眼钱包加
					memberHuoyanWalletManager.addAbleBalanceWithoutTransaction(tjOrder.getMemberId(), happenHuoyanAmout, bizType, tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
					//专家钱包减钱
					memberWalletManager.subtractAbleBalanceWithoutTransaction(tjPlan.getMemberId(), happenCashAmout, bizType, tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
					//系统钱包加钱
					memberWalletManager.addAbleBalanceWithoutTransaction(globalParam.getSysMemberId(), happenCashAmout, bizType,tjOrder.getId(), tjOrder.getTjOrderNo(), walletContext);
				
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],查看付费方案[%s]钱包操作异常：", tjOrder.getMemberId(),walletContext.getTjPlan().getTjPlanNo());
			logger.error(errMsg,e);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "用户钱包操作异常!"));
		}
		
	}
	/**
	 * 提款案业务钱包操作
	 * @param member
	 * @param walletOpType
	 * @param bizType
	 * @param walletContext
	 */
	private void walletHandleWithDrawMoney(BizType bizType,MemberWalletContext walletContext){
		MemberDrawMoneyLog memberDrawMoneyLog = walletContext.getMemberDrawMoneyLog();
		if (memberDrawMoneyLog == null ) {
			throw new JcobServerException(new ExceptionCode("biz.obj.notnull", "业务对象不能为空!"));
		}
		if (memberDrawMoneyLog.getStatus() != DrawMoneyStatus.submit.getIndex()) {
			throw new JcobServerException(new ExceptionCode("biz.status.error", "业务状态错误!"));
		}
		logger.info("memberId[{}],提款钱包操作!memberDrawMoneyLog[{}]",memberDrawMoneyLog.getMemberId(),JSON.toJSONString(memberDrawMoneyLog));
		
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					//现金到火眼有个兑换比率
					BigDecimal happenCashAmout = memberDrawMoneyLog.getAmount();
					walletContext.setClientType(memberDrawMoneyLog.getClientType());
					walletContext.setChannel(memberDrawMoneyLog.getChannel());
					//用户钱包减钱
					memberWalletManager.subtractAbleBalanceWithoutTransaction(memberDrawMoneyLog.getMemberId(), happenCashAmout, bizType, memberDrawMoneyLog.getId(), memberDrawMoneyLog.getDrawMoneyNo(), walletContext);
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],提款[%s]钱包操作异常：", memberDrawMoneyLog.getMemberId(),memberDrawMoneyLog.getDrawMoneyNo());
			logger.error(errMsg,e);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "用户钱包操作异常!"));
		}
		
	}
	/**
	 * 提款退回业务钱包操作
	 * @param member
	 * @param walletOpType
	 * @param bizType
	 * @param walletContext
	 */
	private void walletHandleWithRefundDrawMoney(BizType bizType,MemberWalletContext walletContext){
		MemberDrawMoneyLog memberDrawMoneyLog = walletContext.getMemberDrawMoneyLog();
		if (memberDrawMoneyLog == null ) {
			throw new JcobServerException(new ExceptionCode("biz.obj.notnull", "业务对象不能为空!"));
		}
		logger.info("memberId[{}],提款钱包操作!memberDrawMoneyLog[{}]",memberDrawMoneyLog.getMemberId(),JSON.toJSONString(memberDrawMoneyLog));
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					MemberWalletLog deductWalletLog = memberWalletLogManager.queryMemberWalletLogByBiz(memberDrawMoneyLog.getMemberId(), bizType, memberDrawMoneyLog.getId(), WalletOpType.deduct_money);
					if (deductWalletLog == null) {
						String errMsg = String.format("memberId[%s],退款提款[{}]提款钱包流水为null!：",memberDrawMoneyLog.getMemberId(),memberDrawMoneyLog.getDrawMoneyNo());
						logger.error(errMsg);
						throw new JcobServerException(new ExceptionCode("biz.wallet.log.null", "业务操作钱包流水为null!"));
					}
					//签名验证
					if (!deductWalletLog.getVerifyMd5().equals(deductWalletLog.getCalculateVerifyMd5())) {
						String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",memberDrawMoneyLog.getMemberId(),bizType.getDescription(),memberDrawMoneyLog.getId(),deductWalletLog.getVerifyMd5(),deductWalletLog.getCalculateVerifyMd5());
						logger.error(errMsg);
						throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
					}
					//现金到火眼有个兑换比率
					BigDecimal happenCashAmout = memberDrawMoneyLog.getAmount();
					walletContext.setClientType(memberDrawMoneyLog.getClientType());
					walletContext.setChannel(memberDrawMoneyLog.getChannel());
					memberWalletManager.addAbleBalanceWithoutTransaction(memberDrawMoneyLog.getMemberId(), happenCashAmout, bizType, memberDrawMoneyLog.getId(), memberDrawMoneyLog.getDrawMoneyNo(), walletContext);
				}
			});
		} catch (Exception e) {
			String errMsg = String.format("memberId[%s],退款-提款[%s]钱包操作异常：", memberDrawMoneyLog.getMemberId(),memberDrawMoneyLog.getDrawMoneyNo());
			logger.error(errMsg,e);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "用户钱包操作异常!"));
		}
		
	}

	@Override
	public ModelResult<Boolean> handleMemberBizExceptionBill(Long billId) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(true);
		try {
			transactionTemplateMember.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					MemberBizExceptionBill billFromDb = memberBizExceptionBillManager.queryBizExceptionBillById(billId);
					if (billFromDb == null || billFromDb.getStatus() != MemberBizExceptionBillStatus.create.getIndex()) {
						throw new JcobServerException(new ExceptionCode("biz.exception.bill.invalid", "无效的异常单据!"));
					}
					if (billFromDb.getAmountType() == AmountType.cash.getIndex()) {
						if (billFromDb.getWalletOpType() == WalletOpType.add_money.getIndex()) {
							memberWalletManager.addAbleBalanceWithoutTransaction(billFromDb.getMemberId(), billFromDb.getAmount(),BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
						}else if (billFromDb.getWalletOpType() == WalletOpType.deduct_money.getIndex()) {
							memberWalletManager.subtractAbleBalanceWithoutTransaction(billFromDb.getMemberId(), billFromDb.getAmount(),BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
						}
					}else if (billFromDb.getAmountType() == AmountType.huoyan.getIndex()) {
						if (billFromDb.getWalletOpType() == WalletOpType.add_money.getIndex()) {
							//用户加火眼
							memberHuoyanWalletManager.addAbleBalanceWithoutTransaction(billFromDb.getMemberId(), billFromDb.getAmount(),BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
							//系统钱包加钱
							memberWalletManager.addAbleBalanceWithoutTransaction(globalParam.getSysMemberId(), billFromDb.getAmount(), BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
						}else if (billFromDb.getWalletOpType() == WalletOpType.deduct_money.getIndex()) {
							memberHuoyanWalletManager.subtractAbleBalanceWithoutTransaction(billFromDb.getMemberId(), billFromDb.getAmount(),BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
//							系统钱包减
							memberWalletManager.subtractAbleBalanceWithoutTransaction(globalParam.getSysMemberId(), billFromDb.getAmount(), BizType.exception , billFromDb.getId(), billFromDb.getId()+"", new MemberWalletContext());
						}
					}
				}
			});
		} catch (JcobServerException e) {
			modelResult.withError(e.getExceptionCode().getCode(),e.getExceptionCode().getMsg());
			String errMsg = String.format("billId[%s],异常单据操作异常：",billId);
			logger.error(errMsg,e);
		} catch (Exception e) {
			modelResult.withError("runtime.exception",e.toString());
			String errMsg = String.format("billId[%s],异常单据操作异常：",billId);
			logger.error(errMsg,e);
		}
		return modelResult;
	}
	
	
}
