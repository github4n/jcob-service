package com.aicai.jcob.memberwallet.manager.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericMybatisDao;
import com.aicai.jcob.common.utils.MD5;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWallet;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.domain.MemberWalletContext;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletManager;

public class MemberHuoyanWalletManagerImpl implements MemberHuoyanWalletManager {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Autowired
	private MemberHuoyanWalletLogManager memberHuoyanWalletLogManager;
	@Override
	public MemberHuoyanWallet queryMemberHuoyanWalletByMemberId(Long memberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		return (MemberHuoyanWallet) memberDbDao.queryObject("MemberHuoyanWalletMapper.queryMemberHuoyanWalletByMemberId", paramMap);
	}

	@Override
	public int updateMemberHuoyanWalletEnd(MemberHuoyanWallet memberHuoyanWalletEnd) {
		return memberDbDao.updateByObj("MemberHuoyanWalletMapper.updateMemberHuoyanWallet", memberHuoyanWalletEnd);
	}

	@Override
	public MemberHuoyanWallet lockMemberHuoyanWallet(Long memeberId) {
		int retryCount = 2; //改成2不做重试
		boolean isFirst = true;
		boolean isFail = false;
		MemberHuoyanWallet mmMemberWallet = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memeberId);
		while ((retryCount < 3 && isFail) || isFirst) {
			isFirst = false;
			try {
				if (isFail) {
					//锁获取失败 休眠100毫秒继续获取
					Thread.sleep(100);	
				}
				mmMemberWallet = (MemberHuoyanWallet) memberDbDao.queryObject("MemberHuoyanWalletMapper.lockMemberHuoyanWallet", paramMap);
			} catch (Exception e) {
				retryCount ++;
				isFail = true;
				String errMsg = String.format("memberId[%s]lockMemberHuoyanWallet失败,重试[%s]", memeberId,retryCount);
				logger.error(errMsg,e);
			}
		}
		return mmMemberWallet;
	}

	@Override
	public MemberHuoyanWallet initMemberHuoyanWallet(Member member) {
		MemberHuoyanWallet memberHuoyanWallet = new MemberHuoyanWallet();
		memberHuoyanWallet.setMemberId(member.getId());
		DecimalFormat df = new DecimalFormat("0.0000");
		memberHuoyanWallet.setVerifyMd5(MD5.md5Encode(String.valueOf(memberHuoyanWallet.getMemberId() + df.format(memberHuoyanWallet.getAbleBalance()) + df.format(memberHuoyanWallet.getFreezedBalance()))));
		memberDbDao.insertAndSetupId("MemberHuoyanWalletMapper.saveMemberHuoyanWallet",memberHuoyanWallet);
		return memberHuoyanWallet;
	}

	@Override
	public DataPage<PageWalletVo> pageMemberHuoyanWallet(PageWalletOption pageWalletOption, DataPage<PageWalletVo> dataPage) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletOption);
		return memberDbDao.queryPage("MemberHuoyanWalletMapper.countMemberHuoyanWallet", "MemberHuoyanWalletMapper.pageMemberHuoyanWallet", paraMap, dataPage);
	}

	@Override
	public BigDecimal sumMemberHuoyanWallet(PageWalletOption pageWalletOption) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletOption);
		return memberDbDao.queryUnique("MemberHuoyanWalletMapper.sumMemberHuoyanWallet", paraMap);
	}

	@Override
	public void addAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount, BizType bizType, Long bizId, String bizNo,
			MemberWalletContext walletContext) {
		//查询用户火眼钱包
		MemberHuoyanWallet memberHuoyanWalletFromDB = lockMemberHuoyanWallet(memberId);
		//签名验证
		if (!memberHuoyanWalletFromDB.getVerifyMd5().equals(memberHuoyanWalletFromDB.getCalculateVerifyMd5())) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",memberId,bizType.getDescription(),bizId,memberHuoyanWalletFromDB.getVerifyMd5(),memberHuoyanWalletFromDB.getCalculateVerifyMd5());
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
		}
		//给火眼钱包加钱
		MemberHuoyanWallet memberHuoyanWalletEnd = calculateMemberHuoyanWalletEnd(memberHuoyanWalletFromDB, WalletOpType.add_money, happenAmount);
		MemberHuoyanWalletLog memberHuoyanWalletLog = createMemberHuoyanWalletLog(memberHuoyanWalletEnd, WalletOpType.add_money, bizType,bizId, bizNo, happenAmount,walletContext.getClientType(),walletContext.getChannel());
		
		boolean isHappend = happedMemberHuoyanWalletLog(memberId, WalletOpType.add_money, bizType, bizId);
		if (isHappend) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包操作重复!：",memberId,bizType.getDescription(),bizId);
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.duplicate", "用户钱包操作业务重复!"));
		}
		if (bizType.getIndex() == BizType.look_plan.getIndex()) {
			//设置扩展自动
			memberHuoyanWalletLog.setupFeature("planNo", walletContext.getTjPlan().getTjPlanNo());
		}
		if (bizType.getIndex() == BizType.look_plan.getIndex()) {
			//累计消费
			memberHuoyanWalletEnd.setHeapBalance(memberHuoyanWalletEnd.getHeapBalance().subtract(happenAmount));
		}
		//修改火眼钱包
		updateMemberHuoyanWalletEnd(memberHuoyanWalletEnd);
		//保存火眼钱包流水
		memberHuoyanWalletLogManager.saveWalletLog(memberHuoyanWalletLog);
	}
	@Override
	public void subtractAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount, BizType bizType, Long bizId, String bizNo,
			MemberWalletContext walletContext) {
		//查询用户火眼钱包
		MemberHuoyanWallet memberHuoyanWalletFromDB = lockMemberHuoyanWallet(memberId);
		//签名验证
		if (!memberHuoyanWalletFromDB.getVerifyMd5().equals(memberHuoyanWalletFromDB.getCalculateVerifyMd5())) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",memberId,bizType.getDescription(),bizId,memberHuoyanWalletFromDB.getVerifyMd5(),memberHuoyanWalletFromDB.getCalculateVerifyMd5());
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
		}
		boolean isHappend = happedMemberHuoyanWalletLog(memberId, WalletOpType.deduct_money, bizType, bizId);
		if (isHappend) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包操作重复!：",memberId,bizType.getDescription(),bizId);
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.duplicate", "用户钱包操作业务重复!"));
		}
		//用户火眼钱包减钱
		MemberHuoyanWallet memberHuoyanWalletEnd = calculateMemberHuoyanWalletEnd(memberHuoyanWalletFromDB, WalletOpType.deduct_money, happenAmount);
		//累计消费
		memberHuoyanWalletEnd.setHeapBalance(memberHuoyanWalletEnd.getHeapBalance().add(happenAmount));
		MemberHuoyanWalletLog memberHuoyanWalletLog = createMemberHuoyanWalletLog(memberHuoyanWalletEnd, WalletOpType.deduct_money, bizType,bizId,bizNo, happenAmount,walletContext.getClientType(),walletContext.getChannel());
		if (bizType.getIndex() == BizType.look_plan.getIndex()) {
			//设置扩展自动
			memberHuoyanWalletLog.setupFeature("planNo", walletContext.getTjPlan().getTjPlanNo());
		}
		if (bizType.getIndex() == BizType.look_plan.getIndex() || bizType.getIndex() == BizType.exception.getIndex()) {
			//累计消费
			memberHuoyanWalletEnd.setHeapBalance(memberHuoyanWalletEnd.getHeapBalance().add(happenAmount));
		}
		//修改用户火眼钱包
		updateMemberHuoyanWalletEnd(memberHuoyanWalletEnd);
		memberHuoyanWalletLogManager.saveWalletLog(memberHuoyanWalletLog);
	}
	
	private boolean happedMemberHuoyanWalletLog(Long memberId,WalletOpType walletOpType,BizType bizType,Long bizId){
		return memberHuoyanWalletLogManager.queryMemberWalletLogByBiz(memberId, bizType, bizId, walletOpType) == null?false:true;
	}
	
	private MemberHuoyanWalletLog createMemberHuoyanWalletLog(MemberHuoyanWallet memberWalletEnd,WalletOpType walletOpType,BizType bizType,Long bizId,String bizNo,BigDecimal happenAmout,Integer clientType,Integer channel){
		MemberHuoyanWalletLog memberWalletLog = new MemberHuoyanWalletLog();
		memberWalletLog.setBizId(bizId);
		memberWalletLog.setBizNo(bizNo);
		memberWalletLog.setBizType(bizType.getIndex());
		memberWalletLog.setWalletOpType(walletOpType.getIndex());
		memberWalletLog.setMemberId(memberWalletEnd.getMemberId());
		memberWalletLog.setHappenAmount(happenAmout);
		memberWalletLog.setEndAbleBalance(memberWalletEnd.getAbleBalance());
		memberWalletLog.setEndFreezedBalance(memberWalletEnd.getFreezedBalance());
		if (clientType != null) {
			memberWalletLog.setClientType(clientType);
		}
		if (channel != null) {
			memberWalletLog.setChannel(channel);
		}
		return memberWalletLog;
	}
	
	private MemberHuoyanWallet calculateMemberHuoyanWalletEnd(MemberHuoyanWallet memberHuoyanWallet,WalletOpType walletOpType,BigDecimal happenAmout){
		MemberHuoyanWallet walletEnd = null;
		try {
			walletEnd = (MemberHuoyanWallet) BeanUtils.cloneBean(memberHuoyanWallet);
			walletEnd.setUpdateTime(Calendar.getInstance());
			if (walletOpType.getIndex() == WalletOpType.add_money.getIndex()) {
				walletEnd.setAbleBalance(walletEnd.getAbleBalance().add(happenAmout));
			}else if (walletOpType.getIndex() == WalletOpType.deduct_money.getIndex()) {
				if (happenAmout.compareTo(memberHuoyanWallet.getAbleBalance()) > 0) {
					throw new JcobServerException(new ExceptionCode("member.wallet.insufficient.balance", "用户钱包余额不足!"));
				}
				walletEnd.setAbleBalance(walletEnd.getAbleBalance().subtract(happenAmout));
			}
		} catch (JcobServerException e) {
			throw  e;
		} catch (Exception e) {
			throw new JcobServerException(new ExceptionCode("member.wallet.op.exeception", "钱包操作异常!"));
		}
		return walletEnd;
	}
}
