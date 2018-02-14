package com.aicai.jcob.membercharge.manager.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.dao.GenericDao;
import com.aicai.jcob.common.GlobalParam;
import com.aicai.jcob.common.domain.BankType;
import com.aicai.jcob.common.utils.ThrowableUtil;
import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.member.common.domain.MemberUseChargeWay;
import com.aicai.jcob.member.common.domain.constant.ChannelProxy;
import com.aicai.jcob.member.common.domain.constant.MemberChargeWayFeeStatus;
import com.aicai.jcob.member.common.domain.constant.OperType;
import com.aicai.jcob.member.common.domain.option.MemberChargeLogOption;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.membercharge.common.domain.MemberChargeChannel;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeReturnLog;
import com.aicai.jcob.membercharge.common.domain.MemberChargeWay;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeChannel;
import com.aicai.jcob.membercharge.common.domain.constant.ChargeStatus;
import com.aicai.jcob.membercharge.common.domain.constant.MemberUseChargeWayStatus;
import com.aicai.jcob.membercharge.common.option.MemberChargeOption;
import com.aicai.jcob.membercharge.common.result.MemberChargeResult;
import com.aicai.jcob.membercharge.manager.MemberChargeLogManager;
import com.aicai.jcob.membercharge.manager.MemberChargeReturnLogManager;
import com.aicai.jcob.membercharge.manager.MemberUseChargeWayManager;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.alibaba.fastjson.JSON;

public class MemberChargeLogManagerImpl implements MemberChargeLogManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    @Qualifier("memberDbDao")
    private GenericDao memberChargeLogDao;
	
	@Autowired
	@Qualifier("memberOperLogManagerImpl")
	private MemberOperLogManager memberOperLogManager;
	
	@Autowired
	@Qualifier("memberChargeReturnLogManagerImpl")
	private MemberChargeReturnLogManager memberChargeReturnLogManager;
	
	@Autowired
	@Qualifier("memberBizDirverWalletManagerImpl")
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	
	@Autowired
	@Qualifier("memberUseChargeWayManagerImpl")
	private MemberUseChargeWayManager memberUseChargeWayManager;
	
	@Autowired
	@Qualifier("memberManagerImpl")
	private MemberManager memberManager;
	
	@Autowired
	@Qualifier("transactionTemplateMember")
	protected TransactionTemplate transactionTemplate;
	
	@Autowired
	@Qualifier("globalParam")
	private GlobalParam globalParam;
	
	@Override
	public MemberChargeLog insertMemberChargeLog(MemberChargeLog memberChargeLog) {
		memberChargeLogDao.insertAndSetupId("MemberChargeLogMapper.insertSelective", memberChargeLog);
		return memberChargeLog;
	}

	@Override
	public MemberChargeLog queryMemberChargeLogById(long id) {
		return (MemberChargeLog)memberChargeLogDao.queryUnique("MemberChargeLogMapper.selectByPrimaryKey", id);
	}

	@Override
	public int updateMemberChargeLogStatusById(long id, int status) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		return memberChargeLogDao.update("MemberChargeLogMapper.updateMemberChargeLogStatusById", param);
	}

	@Override
	public ModelResult<MemberChargeLog> queryMemberChargeLogByChargeNo(String chargeNo) {
		ModelResult<MemberChargeLog> result = new ModelResult<MemberChargeLog>();
		Map<String,String> errMap = new HashMap<String,String>();
		MemberChargeLog log = null;
		
		try {
			if(StringUtils.isBlank(chargeNo) || chargeNo.length() < 9) 
				throw new JcobServerException(ExceptionCode.PAYMENT_CHARGENO_FORMAT_INCORRECT);
			log = queryMemberChargeLogById(Long.valueOf(chargeNo.substring(8)));
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统操作异常");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(log);
		return result;
	}

	@Override
	public ModelResult<MemberChargeLog> saveMemberChargeLog(MemberChargeLog memberChargeLog,
			MemberOperOption memberOperOption) {
		ModelResult<MemberChargeLog> result = new ModelResult<MemberChargeLog>();
		Map<String,String> errMap = new HashMap<String,String>();
		MemberChargeLog memLog = null;
		try {
			memLog = this.insertMemberChargeLog(memberChargeLog);
			if(null == memLog) {
				logger.info("充值操作失败");
				throw new JcobServerException(ExceptionCode.MEMBER_CHARGELOG_ISNULL);
			}
			logger.info("充值成功,MemberChargeLog Id : [{}]",memLog.getId());
		} catch (JcobServerException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", e.getMessage());
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "系统操作异常");
			result.setErrorList(errMap);
			return result;
		}
		
		memberOperLogManager.asyncSaveMemberOperLog(memberChargeLog.getMemberId(), memberOperOption, memberOperOption.getRemark());
		result.setModel(memLog);
		return result;
	}

	@Override
	public boolean chargeThird(MemberChargeReturnLog returnLog,MemberOperOption memberOperOption,String thirdChargeNo,
			String acctName,String certNo) {
		long chargeId = returnLog.getChargeId();
		MemberChargeLog chargeLog = this.queryMemberChargeLogById(chargeId);
		logger.info("null == chargeLog,[{}]",(null == chargeLog));
		
		try {
			if(null == chargeLog) {
				logger.info("chargeLog is null,chargeId : [{}]",chargeId);
				throw new JcobServerException(ExceptionCode.PAYMENT_CHARGELOG_NOTFOUND);
			}
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateMemberChargeLogStatusThirdChargeNoRespTimeById(chargeId, ChargeStatus.success.getIndex(), thirdChargeNo,Calendar.getInstance());
					MemberChargeReturnLog rLog = memberChargeReturnLogManager.queryMemberChargeReturnLogByChargeId(chargeId);
					logger.info("null == rLog,[{}]",(null == rLog));
					
					if(null == rLog) {
						logger.info("Create MemberChargeReturnLog,chargeId : [{}]",chargeLog.getId());
						memberChargeReturnLogManager.insertMemberChargeReturnLog(returnLog);
					}
				}
			});
			
			//如果用户未实名认证，充值返回结果中有账户名和证件号码，更新member信息
			/*Member member = memberManager.queryMemberById(chargeLog.getMemberId()).getModel();
			if(null == member) logger.info("member is null,memberId : [{}]",chargeLog.getMemberId());
			if(StringUtils.isBlank(member.getRealName()) && StringUtils.isNotBlank(acctName)) {
				memberManager.updateMemberCertNoRealNameById(member.getId(), certNo, acctName);
				member.addFlagBit(MemberFlagBit.certification);
				memberManager.updateMemberFlagBit(member.getId(), member.getFlagBit());
			}*/
			
			logger.info("chargeWallet begin ==");
			
			//获取更新状态后的memberchargelog
			MemberChargeLog chargeLog2 = this.queryMemberChargeLogById(chargeLog.getId());
			chargeWallet(chargeLog2,memberOperOption);
			logger.info("chargeWallet end ==");
		} catch (TransactionException e) {
			logger.error("通知返回操作失败chargeId : [{}],error : [{}-{}]",chargeId,e,ThrowableUtil.getFullExceptionInfo(e));
			return false;
		} catch(Exception e){
			logger.error("通知返回操作失败,error : [{}-{}]",e,ThrowableUtil.getFullExceptionInfo(e));
			return false;
		}
		
		return true;
	}

	@Override
	public boolean chargeThird(MemberChargeReturnLog returnLog,
			MemberChargeLog chargeLog, MemberOperOption memberOperOption) {
		try {
			if(null == chargeLog) {
				logger.info("chargeLog is null,chargeId : [{}]");
				throw new JcobServerException(ExceptionCode.PAYMENT_CHARGELOG_NOTFOUND);
			}
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateMemberChargeLogStatusHandCostRespTimeById(chargeLog.getId(),ChargeStatus.success.getIndex(),
							chargeLog.getHandingCost(),Calendar.getInstance());
					MemberChargeReturnLog rLog = memberChargeReturnLogManager.queryMemberChargeReturnLogByChargeId(chargeLog.getId());
					if(null == rLog) {
						logger.info("Create MemberChargeReturnLog,chargeId : [{}]",chargeLog.getId());
						memberChargeReturnLogManager.insertMemberChargeReturnLog(returnLog);
					}
				}
			});
			
			MemberChargeLog chargeLog2 = this.queryMemberChargeLogById(chargeLog.getId());
			chargeWallet(chargeLog2,memberOperOption);
		} catch (TransactionException e) {
			logger.error("通知返回操作失败,error : [{}-{}]",e,ThrowableUtil.getFullExceptionInfo(e));
			return false;
		} catch (Exception e){
			logger.error("通知返回操作失败,error : [{}-{}]",e,ThrowableUtil.getFullExceptionInfo(e));
			return false;
		}
		
		return true;
	}
	
	/*@Override
	public ModelResult<Boolean> chargeThird(String chargeNo,String nickName,Long memberId,BigDecimal amount,BigDecimal handingCost,MemberOperOption memberOperOption) {
		ModelResult<Boolean> result = new ModelResult<Boolean>(Boolean.FALSE);
		MemberChargeLog chargeLog = this.queryMemberChargeLogByChargeNo(chargeNo).getModel();
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			if(null == chargeLog) {
				logger.error("chargeLog is null,chargeNo : [{}]",chargeNo);
				throw new JcobServerException(ExceptionCode.CHARGE_LOG_NOT_FOUND);
			}
			
			优先根据memberId验证
			if(null != memberId){
				if(chargeLog.getMemberId() != memberId) throw new JcobServerException(ExceptionCode.MEMBER_ID_UNEQUALS);
			}else{
				//根据昵称查询用户信息
				Member member = memberManager.queryMemberByNickName(nickName).getModel();
				if(null == member) {
					logger.error("member is null,nickName : [{}]",nickName);
					throw new JcobServerException(ExceptionCode.MEMBER_NOT_EXIST);
				}
				
				if(chargeLog.getMemberId() != member.getId()) throw new JcobServerException(ExceptionCode.MEMBER_ID_UNEQUALS);
			}
			if(amount.compareTo(chargeLog.getAmount()) != 0) throw new JcobServerException(ExceptionCode.AMOUNT_NOT_EQUALS);
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateMemberChargeLogStatusHandCostRespTimeById(chargeLog.getId(),ChargeStatus.success.getIndex(),
							handingCost,Calendar.getInstance());
					MemberChargeReturnLog rLog = memberChargeReturnLogManager.queryMemberChargeReturnLogByChargeId(chargeLog.getId());
					if(null == rLog) {
						logger.info("Create MemberChargeReturnLog,chargeId : [{}]",chargeLog.getId());
						MemberChargeReturnLog returnLog = new MemberChargeReturnLog();
						returnLog.setChargeId(chargeLog.getId());
						returnLog.setChargeReturnInfo("手工补单");
						memberChargeReturnLogManager.insertMemberChargeReturnLog(returnLog);
					}
				}
			});
		} catch (TransactionException e) {
			logger.error(e.getMessage());
			errMap.put("error", "补单操作失败!");
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e.getMessage());
			errMap.put("error", "补单操作失败!");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
	}*/
	
	@Override
	public ModelResult<MemberChargeLog> saveMemberChargeLog(
			MemberChargeWay memberChargeWay, MemberChargeChannel chargeChannel,
			MemberChargeLogOption option, Map<String, Object> extraMap,String userIp, String frontServerIp) {
		logger.info("saveMemberChargeLog begin ...");
		// 初始化充值流水
		MemberChargeLog memberChargeLog = new MemberChargeLog();
		memberChargeLog.setMemberId(option.getMemberId());
		memberChargeLog.setAmount(option.getAmount());
		if(null != option.getClientType()) memberChargeLog.setClientType(option.getClientType());
		memberChargeLog.setChargeWayIndex(memberChargeWay.getChargeWayIndex());
		memberChargeLog.setChargeChannelIndex(chargeChannel.getChargeChannelIndex());
		
		BigDecimal feeAmout = BigDecimal.ZERO;
		//判断chargeWay是否收费
		if(memberChargeWay.getFeeFlag() == MemberChargeWayFeeStatus.charge){
			//充值手续费 = 收费百分比*充值金额 
			feeAmout = chargeChannel.getFeeRatio().multiply(option.getAmount());
			
			if (feeAmout.compareTo(chargeChannel.getMaxFeeAmount()) > 0)// 是否大于最大手续费
				feeAmout = chargeChannel.getMaxFeeAmount();
			if (feeAmout.compareTo(chargeChannel.getMinFeeAmount()) < 0)// 是否小于最小的手续费
				feeAmout = chargeChannel.getMinFeeAmount();
			
			feeAmout = feeAmout.setScale(2, RoundingMode.CEILING);
		}
		memberChargeLog.setHandingCost(feeAmout);
		memberChargeLog.setReturnUrl(option.getReturnUrl());
		memberChargeLog.setNotifyUrl(option.getNotifyUrl());
		memberChargeLog.setStatus(ChargeStatus.fail.getIndex());
		memberChargeLog.setCashTOHuoyanRatio(globalParam.getCashTOHuoyanRatio());
		
		if(StringUtils.isNotBlank((String)extraMap.get("bankCode"))) memberChargeLog.setBankCode((String)extraMap.get("bankCode"));
		
		if(StringUtils.isNoneBlank((String)extraMap.get("bankCard"))) memberChargeLog.setBankCard((String)extraMap.get("bankCard"));
		
		//保存memberchargelog记流水
		MemberOperOption memberOperOption = new MemberOperOption(1,option.getClientType(),userIp,frontServerIp);
		memberOperOption.setOperType(OperType.charge.getIndex());
		memberOperOption.setRemark("用户充值操作");
		return saveMemberChargeLog(memberChargeLog, memberOperOption);
	}

	@Override
	public int updateMemberChargeLogStatusHandCostRespTimeById(long id,
			int status, BigDecimal handingCost, Calendar respTime) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		param.put("responseTime", respTime);
		param.put("handingCost", handingCost);
		return memberChargeLogDao.update("MemberChargeLogMapper.updateMemberChargeLotStatusHandCostRespTimeById", param);
	}

	
	private void chargeWallet(MemberChargeLog chargeLog,MemberOperOption memberOperOption){
		//更新memberwallet与memberhuoyanwallet信息
		memberBizDirverWalletManager.memberCharge(chargeLog);
		
		//记录用户所使用的充值方式记录,如果已经存在,不再记录
		MemberUseChargeWay chargeWay = new MemberUseChargeWay(chargeLog.getMemberId(),getChannelPorxy(chargeLog.getChargeChannelIndex()),
				chargeLog.getClientType(),MemberUseChargeWayStatus.success);
		chargeWay.setBankCode(chargeLog.getBankCode());
		chargeWay.setBankCard(chargeLog.getBankCard());
		chargeWay.setPayType(chargeLog.getPayType());
		
		String bankInfo = BankType.getInnerCodeAndBankNameByLianLianCode(chargeLog.getBankCode());
		if(StringUtils.isNoneBlank(bankInfo)) {
			logger.info("bankInfo : [{}]",bankInfo);
			chargeWay.setBankName(bankInfo.split("-")[1]);
		}
		
		memberUseChargeWayManager.saveMemberUseChargeWay(chargeWay);
		
		if(null != memberOperOption) 
			memberOperLogManager.asyncSaveMemberOperLog(chargeLog.getMemberId(), memberOperOption, memberOperOption.getRemark());
		else
			logger.info("memberOperOption is null !");
	}

	@Override
	public DataPage<MemberChargeResult> queryMemberChargeByPage(MemberChargeOption option, DataPage<MemberChargeResult> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		map.put("startIndex", (page.getPageNo() - 1) * page.getPageSize());
		map.put("pageSize", page.getPageSize());
		return memberChargeLogDao.queryPage("MemberChargeLogMapper.queryMemberChargeByPageCount",
				"MemberChargeLogMapper.queryMemberChargeByPage", map, page);
	}

	@Override
	public MemberChargeResult queryMemberChargeAmountSum(MemberChargeOption option) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", option);
		return memberChargeLogDao.queryUnique("MemberChargeLogMapper.queryMemberChargeAmountSum", map);
	}
	
	@Override
	public ModelResult<MemberChargeReturnLog> initChargeReturnLog(
			MemberChargeLog chargeLog, String queryString,
			String responseJsonTxt) {
		MemberChargeReturnLog returnLog = new MemberChargeReturnLog();
		returnLog.setChargeId(chargeLog.getId());
		returnLog.setChargeReturnInfo(!StringUtils.isBlank(responseJsonTxt) && responseJsonTxt.length() < 800 ? responseJsonTxt : queryString);
		return new ModelResult<MemberChargeReturnLog>(returnLog);
	}

	@Override
	public ModelResult<Boolean> chargeThird(long memberChargeLogId) {
		ModelResult<Boolean> result = new ModelResult<Boolean>(Boolean.FALSE);
		MemberChargeLog chargeLog = this.queryMemberChargeLogById(memberChargeLogId);
		Map<String,String> errMap = new HashMap<String,String>();
		
		try {
			if(null == chargeLog) {
				logger.info("chargeLog is null,id : [{}]",memberChargeLogId);
				throw new JcobServerException(ExceptionCode.PAYMENT_CHARGELOG_NOTFOUND);
			}
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					updateMemberChargeLogStatusHandCostRespTimeById(chargeLog.getId(),ChargeStatus.success.getIndex(),
							chargeLog.getHandingCost(),Calendar.getInstance());
					MemberChargeReturnLog rLog = memberChargeReturnLogManager.queryMemberChargeReturnLogByChargeId(chargeLog.getId());
					if(null == rLog) {
						logger.info("Create MemberChargeReturnLog,chargeId : [{}]",chargeLog.getId());
						MemberChargeReturnLog returnLog = new MemberChargeReturnLog();
						returnLog.setChargeId(chargeLog.getId());
						returnLog.setChargeReturnInfo("手工补单");
						memberChargeReturnLogManager.insertMemberChargeReturnLog(returnLog);
					}
				}
			});
			
			MemberOperOption memberOperOption = new MemberOperOption(chargeLog.getChannel(), chargeLog.getClientType(), null, null);
			
			MemberChargeLog chargeLog2 = this.queryMemberChargeLogById(chargeLog.getId());
			chargeWallet(chargeLog2,memberOperOption);
		} catch (TransactionException e) {
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "补单操作失败!");
			result.setErrorList(errMap);
			return result;
		} catch (Exception e){
			logger.error(e + "-" + ThrowableUtil.getFullExceptionInfo(e));
			errMap.put("error", "补单操作失败!");
			result.setErrorList(errMap);
			return result;
		}
		
		result.setModel(Boolean.TRUE);
		return result;
	}

	@Override
	public ModelResult<Boolean> updateMemberChargeLogBankCodePayType(Long id,
			String bankCode,Integer payType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", id);
		param.put("bankCode", bankCode);
		param.put("payType", payType);
		return new ModelResult<Boolean>(memberChargeLogDao.updateByObj("MemberChargeLogMapper.updateMemberChargeLogBankCodePayType", param) > 0);
	}

	private int getChannelPorxy(int chargeChannel){
		if(ChargeChannel.zfb_sdk.getIndex() == chargeChannel) return ChannelProxy.ALIPAY.getIndex();
		if(ChargeChannel.zfb_wap.getIndex() == chargeChannel) return ChannelProxy.ALIPAY.getIndex();
		if(ChargeChannel.zfb_web.getIndex() == chargeChannel) return ChannelProxy.ALIPAY.getIndex();
		if(ChargeChannel.wx.getIndex() == chargeChannel) return ChannelProxy.WEIXIN_PAY.getIndex();
		if(ChargeChannel.lianlian_sdk.getIndex() == chargeChannel) return ChannelProxy.LIANLIAN_PAY.getIndex();
		if(ChargeChannel.lianlian_wap.getIndex() == chargeChannel) return ChannelProxy.LIANLIAN_PAY.getIndex();
		return 0;
	}

	@Override
	public int updateMemberChargeLogStatusThirdChargeNoById(long id,
			int status, String thirdChargeNo) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		param.put("thirdChargeNo", thirdChargeNo);
		return memberChargeLogDao.update("MemberChargeLogMapper.updateMemberChargeLogStatusThirdChargeNoById", param);
	}

	@Override
	public int updateMemberChargeLogStatusThirdChargeNoRespTimeById(long id,
			int status, String thirdChargeNo, Calendar respTime) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("status", status);
		param.put("thirdChargeNo", thirdChargeNo);
		param.put("responseTime", respTime);
		return memberChargeLogDao.update("MemberChargeLogMapper.updateMemberChargeLogStatusThirdChargeNoRespTimeById", param);
	}

	@Override
	public ModelResult<BigDecimal> queryMemberCardChargeDayTotalAmount(
			long memberId, String bankCard, int status, Calendar createDate,int[] channelArray) {
		ModelResult<BigDecimal> result = new ModelResult<BigDecimal>(BigDecimal.ZERO);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("memberId", memberId);
		param.put("status", status);
		param.put("bankCard", bankCard);
		Date beginDate = null;
		try {
			beginDate = sdf.parse(sdf2.format(createDate.getTime()));
		} catch (ParseException e) {
			logger.error("[{}]",e);
		}
		Calendar beginTime = Calendar.getInstance();
		beginTime.setTime(beginDate);
		Calendar endTime = createDate;
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("channelArray", channelArray);
		
		logger.info("beginTime : [{}],endTime : [{}]",sdf.format(beginTime.getTime()),sdf.format(endTime.getTime()));
		logger.info("queryMemberCardChargeDayTotalAmount : [{}]",JSON.toJSON(param));
		
		BigDecimal amount = (BigDecimal)memberChargeLogDao.queryObject("MemberChargeLogMapper.queryMemberCardChargeDayTotalAmount", param);
		result.setModel(amount != null ? amount : BigDecimal.ZERO);
		return result;
	}

	@Override
	public ModelResult<BigDecimal> queryMemberCardChargeMonthTotalAmount(
			long memberId, String bankCard, int status, Calendar createDate,
			int[] channelArray) {
		ModelResult<BigDecimal> result = new ModelResult<BigDecimal>(BigDecimal.ZERO);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("memberId", memberId);
		param.put("status", status);
		param.put("bankCard", bankCard);
		Date beginDate = null;
		try {
			beginDate = sdf.parse(sdf2.format(createDate.getTime()));
		} catch (ParseException e) {
			logger.error("[{}]",e);
		}
		Calendar beginTime = Calendar.getInstance();
		beginTime.setTime(beginDate);
		Calendar endTime = createDate;
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("channelArray", channelArray);
		
		logger.info("beginTime : [{}],endTime : [{}]",sdf.format(beginTime.getTime()),sdf.format(endTime.getTime()));
		logger.info("queryMemberCardChargeDayTotalAmount : [{}]",JSON.toJSON(param));
		
		BigDecimal amount = (BigDecimal)memberChargeLogDao.queryObject("MemberChargeLogMapper.queryMemberCardChargeDayTotalAmount", param);
		result.setModel(amount != null ? amount : BigDecimal.ZERO);
		return result;
	}
}
