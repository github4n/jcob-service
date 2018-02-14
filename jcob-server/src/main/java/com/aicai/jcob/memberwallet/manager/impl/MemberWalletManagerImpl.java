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
import com.aicai.jcob.common.GlobalParam;
import com.aicai.jcob.common.utils.MD5;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.MemberWalletLog;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.constant.WalletOpType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.domain.MemberWalletContext;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;
/**
 * 钱包管理实现
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月26日
 */
public class MemberWalletManagerImpl implements MemberWalletManager {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	@Qualifier("memberDbDao")
	private GenericMybatisDao  memberDbDao;
	@Autowired
	private MemberWalletLogManager memberWalletLogManager;
	@Autowired
	private GlobalParam globalParam;
	@Override
	public MemberWallet queryMemberWalletByMemberId(Long memberId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memberId);
		return (MemberWallet) memberDbDao.queryObject("MemberWalletMapper.queryMemberWalletByMemberId", paramMap);
	}

	@Override
	public int updateMemberWalletEnd(MemberWallet memberWalletEnd) {
		return memberDbDao.updateByObj("MemberWalletMapper.updateMemberWallet", memberWalletEnd);
	}

	@Override
	public MemberWallet lockMemberWallet(Long memeberId) {
		int retryCount = 2; //改成2 不做重试
		boolean isFirst = true;
		boolean isFail = false;
		MemberWallet mmMemberWallet = null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("memberId", memeberId);
		while ((retryCount < 3 && isFail) || isFirst) {
			isFirst = false;
			try {
				if (isFail) {
					//锁获取失败 休眠100毫秒继续获取
					Thread.sleep(100);	
				}
				mmMemberWallet = (MemberWallet) memberDbDao.queryObject("MemberWalletMapper.lockMemberWallet", paramMap);
			} catch (Exception e) {
				retryCount ++;
				isFail = true;
				String errMsg = String.format("memberId[%s]lockMemberWallet失败,重试[%s]", memeberId,retryCount);
				logger.error(errMsg,e);
			}
		}
		return mmMemberWallet;
	}

	@Override
	public MemberWallet initMemberWallet(Member member) {
		MemberWallet memberWallet = new MemberWallet();
		memberWallet.setMemberId(member.getId());
		DecimalFormat df = new DecimalFormat("0.0000");
		memberWallet.setVerifyMd5(MD5.md5Encode(String.valueOf(memberWallet.getId()) + df.format(memberWallet.getAbleBalance()) + df.format(memberWallet.getFreezedBalance())));
		memberDbDao.insertAndSetupId("MemberWalletMapper.saveMemberWallet",memberWallet);
		return memberWallet;
	}
	
	@Override
	public DataPage<PageWalletVo> pageMemberWallet(PageWalletOption pageWalletOption, DataPage<PageWalletVo> dataPage) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletOption);
		return memberDbDao.queryPage("MemberWalletMapper.countMemberWallet", "MemberWalletMapper.pageMemberWallet", paraMap,
				dataPage);
	}

	@Override
	public BigDecimal sumMemberWallet(PageWalletOption pageWalletOption) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("option", pageWalletOption);
		return memberDbDao.queryUnique("MemberWalletMapper.sumMemberWallet", paraMap);
	}
	
	/**
	 * 用户钱包加钱-内部无事物
	 * @param memberId
	 * @param happenAmount
	 * @param bizType
	 * @param bizId
	 * @param bizNo
	 * @param walletContext
	 */
	public void addAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount,BizType bizType,Long bizId,String bizNo,MemberWalletContext walletContext){
		//专家用户现金钱包减钱
		MemberWallet memberWalletFromDB = lockMemberWallet(memberId);
		//签名验证
		if (!memberWalletFromDB.getVerifyMd5().equals(memberWalletFromDB.getCalculateVerifyMd5())) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",memberId,bizType.getDescription(),bizId,memberWalletFromDB.getVerifyMd5(),memberWalletFromDB.getCalculateVerifyMd5());
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
		}
		boolean isHappend = happedMemberWalletLog(memberId, WalletOpType.add_money, bizType,bizId);
		if (isHappend) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包操作重复!：",memberId,bizType.getDescription(),bizId);
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.duplicate", "用户钱包操作业务重复!"));
		}
		MemberWallet zjmemberWalletEnd = calculateMemberWalletEnd(memberWalletFromDB, WalletOpType.add_money, happenAmount);
		if (bizType.getIndex() == BizType.draw_money.getIndex()) {
			//减去累计消费
			zjmemberWalletEnd.setHeapBalance(zjmemberWalletEnd.getHeapBalance().subtract(happenAmount));
		}
		
		MemberWalletLog zjmemberWalletLog = createMemberWalletLog(zjmemberWalletEnd, WalletOpType.add_money, bizType,bizId, bizNo, happenAmount,walletContext.getClientType(),walletContext.getChannel());
		if (bizType.getIndex() == BizType.look_plan.getIndex()) {
			//设置扩展自动
			zjmemberWalletLog.setupFeature("planNo", walletContext.getTjPlan().getTjPlanNo());
		}
		//修改专家现金钱包
		updateMemberWalletEnd(zjmemberWalletEnd);
		memberWalletLogManager.saveWalletLog(zjmemberWalletLog);
	}
	
	@Override
	public void subtractAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount, BizType bizType, Long bizId, String bizNo,
			MemberWalletContext walletContext) {
		//查询钱包
		MemberWallet walletFromDB = lockMemberWallet(memberId);
		//签名验证
		if (!walletFromDB.getVerifyMd5().equals(walletFromDB.getCalculateVerifyMd5())) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包签名verifyMd5[%s],calculateVerifyMd5[%s]!：",memberId,bizType.getDescription(),bizId,walletFromDB.getVerifyMd5(),walletFromDB.getCalculateVerifyMd5());
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.verify.invalid", "用户钱包签名异常!"));
		}
		boolean isHappend = happedMemberWalletLog(memberId, WalletOpType.deduct_money, bizType,bizId);
		if (isHappend) {
			String errMsg = String.format("memberId[%s],bizType[%s],bizId[%s]钱包操作重复!：",memberId,bizType.getDescription(),bizId);
			logger.error(errMsg);
			throw new JcobServerException(new ExceptionCode("member.wallet.op.duplicate", "用户钱包操作业务重复!"));
		}
		//减钱
		MemberWallet walletEnd = calculateMemberWalletEnd(walletFromDB, WalletOpType.deduct_money, happenAmount);
		if (bizType.getIndex() == BizType.draw_money.getIndex() || bizType.getIndex() == BizType.exception.getIndex()) {
			//累计消费
			walletEnd.setHeapBalance(walletEnd.getHeapBalance().add(happenAmount));
		}
		if (bizType.getIndex() == BizType.look_plan.getIndex() && globalParam.getSysMemberId() == memberId) {//系统
			//累计消费
			walletEnd.setHeapBalance(walletEnd.getHeapBalance().add(happenAmount));
		}
	
		MemberWalletLog memberWalletLog = createMemberWalletLog(walletEnd, WalletOpType.deduct_money, bizType, bizId,bizNo, happenAmount,walletContext.getClientType(),walletContext.getChannel());
		if (bizType.getIndex() == BizType.look_plan.getIndex()) {
			//设置扩展自动
			memberWalletLog.setupFeature("planNo", walletContext.getTjPlan().getTjPlanNo());
		}
		//修改系统现金钱包
		updateMemberWalletEnd(walletEnd);
		memberWalletLogManager.saveWalletLog(memberWalletLog);
		
	}

	private boolean happedMemberWalletLog(Long memberId,WalletOpType walletOpType,BizType bizType,Long bizId){
		return memberWalletLogManager.queryMemberWalletLogByBiz(memberId, bizType, bizId, walletOpType) == null?false:true;
	}
	private MemberWallet calculateMemberWalletEnd(MemberWallet memberWallet,WalletOpType walletOpType,BigDecimal happenAmout){
		MemberWallet walletEnd = null;
		try {
			 walletEnd = (MemberWallet) BeanUtils.cloneBean(memberWallet);
			walletEnd.setUpdateTime(Calendar.getInstance());
			if (walletOpType.getIndex() == WalletOpType.add_money.getIndex()) {
				walletEnd.setAbleBalance(walletEnd.getAbleBalance().add(happenAmout));
			}else if (walletOpType.getIndex() == WalletOpType.deduct_money.getIndex()) {
				if (happenAmout.compareTo(memberWallet.getAbleBalance()) > 0) {
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
	
	private MemberWalletLog createMemberWalletLog(MemberWallet memberWalletEnd,WalletOpType walletOpType,BizType bizType,Long bizId,String bizNo,BigDecimal happenAmout,Integer clientType,Integer channel){
		MemberWalletLog memberWalletLog = new MemberWalletLog();
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
}
