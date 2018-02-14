package com.aicai.jcob.memberdrawmoney.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.member.common.domain.constant.MemberStatus;
import com.aicai.jcob.member.common.domain.constant.OperType;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.member.manager.MemberManager;
import com.aicai.jcob.member.manager.MemberOperLogManager;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBank;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyBranchBankVo;
import com.aicai.jcob.memberdrawmoney.common.domain.DrawMoneyProvinceCity;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyWay;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.AuditStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.DrawMoneyStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.MembeDrawAndChargeConstant;
import com.aicai.jcob.memberdrawmoney.common.domain.constant.MemberDrawMoneyWayStatus;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyAuditInfoOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyInfoUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyLogQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyMannualUpdateOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayAddOption;
import com.aicai.jcob.memberdrawmoney.common.domain.option.MemberDrawMoneyWayQueryOption;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogDetail;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyLogResult;
import com.aicai.jcob.memberdrawmoney.common.domain.result.MemberDrawMoneyWayResult;
import com.aicai.jcob.memberdrawmoney.common.service.MemberDrawMoneyWriteService;
import com.aicai.jcob.memberdrawmoney.manager.DrawMoneyBankManager;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyLogManager;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyTranscationManager;
import com.aicai.jcob.memberdrawmoney.manager.MemberDrawMoneyWayManager;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.manager.MemberBizDirverWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;

public class MemberDrawMoneyWriteServiceImpl implements MemberDrawMoneyWriteService {
	private transient final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberWalletManager memberWalletManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberDrawMoneyTranscationManager memberDrawMoneyTranscationManager;
	@Autowired
	private MemberOperLogManager memberOperLogManager;
	@Autowired
	private MemberDrawMoneyWayManager memberDrawMoneyWayManager;
	@Autowired
	private MemberDrawMoneyLogManager memberDrawMoneyLogManager;
	@Autowired
	private MemberBizDirverWalletManager memberBizDirverWalletManager;
	@Autowired
	private DrawMoneyBankManager drawMoneyBankManager;
	@Override
	public ModelResult<MemberDrawMoneyLog> memberDrawMoney(Long memberId,BigDecimal drawAmount, BigDecimal handingCost, Long drawMoneyWayId,
			MemberOperOption operOption) {
		ModelResult<MemberDrawMoneyLog> modelResult = new ModelResult<MemberDrawMoneyLog>();
		if (memberId == null || drawAmount == null || drawMoneyWayId == null || operOption == null) {
			return modelResult.withError("param.not.null","memberId/drawAmount/drawMoneyWayId/drawMoneyWayId参数不能为null");
		}
		//有效校验
		ModelResult<Member> memberModelResult = memberManager.queryMemberById(memberId);
		Member member = memberModelResult.getModel();
		if (member == null || member.getStatus() != MemberStatus.valid) {
			return modelResult.withError("member.invalid","用户状态异常!");
		}
		MemberWallet memberWallet = memberWalletManager.queryMemberWalletByMemberId(memberId);
		if (memberWallet == null || memberWallet.getAbleBalance().compareTo(drawAmount) < 0) {
			return modelResult.withError("member.nsufficient.balance","钱包余额不足!");
		}
		//判断最低，最多提款金额
		if (MembeDrawAndChargeConstant.drawMoneyMin.compareTo(drawAmount)>0 ||MembeDrawAndChargeConstant.drawMoneyMax.compareTo(drawAmount) < 0) {
			return modelResult.withError("member.drawmoney.over.limit","提款金额范围:"+MembeDrawAndChargeConstant.drawMoneyMin+"-"+MembeDrawAndChargeConstant.drawMoneyMax);
		}
		//重新计算手续费
		handingCost = MembeDrawAndChargeConstant.getDrawMoneyFee(drawAmount);
		//构造提款记录
		MemberDrawMoneyLog memberDrawMoneyLog = new MemberDrawMoneyLog();
		memberDrawMoneyLog.setAmount(drawAmount.setScale(3, RoundingMode.DOWN));
		memberDrawMoneyLog.setHandingCost(handingCost);
		memberDrawMoneyLog.setMemberId(memberId);
		memberDrawMoneyLog.setDrawMoneyWayId(drawMoneyWayId);
		memberDrawMoneyLog.setClientType(operOption.getClientType());
		memberDrawMoneyLog.setChannel(operOption.getChannel());
		modelResult = memberDrawMoneyTranscationManager.memberDrawMoneyWithTranscation(memberDrawMoneyLog);
		//写操作流水
		operOption.setOperType(OperType.draw_money.getIndex());
		memberOperLogManager.asyncSaveMemberOperLog(member.getId(), operOption, OperType.draw_money.getDescription());
		return modelResult;
	}

	@Override
	public ModelResult<List<MemberDrawMoneyWay>> queryMemberDrawMoneyWayListByMemberId(Long memberId) {
		ModelResult<List<MemberDrawMoneyWay>> modelResult = new ModelResult<List<MemberDrawMoneyWay>>();
		if (memberId == null) {
			return modelResult.withError("param.not.null","memberId参数不能为null");
		}
		try {
			List<MemberDrawMoneyWay> drawMoneyWayList = memberDrawMoneyWayManager.queryMemberDrawMoneyWayListByMemberId(memberId);
			modelResult.setModel(drawMoneyWayList);
		} catch (Exception e) {
			logger.error("memberDrawMoneyWayManager.queryMemberDrawMoneyWayListByMemberId调用异常!",e);
			modelResult.withError("runtime.exception","系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<MemberDrawMoneyWay> saveMemberDrawMoneyWay(
			Long memberId, MemberDrawMoneyWayAddOption drawMoneyWayAddOption,MemberOperOption operOption) {
		ModelResult<MemberDrawMoneyWay> modelResult = new ModelResult<MemberDrawMoneyWay>();
		if (memberId == null || drawMoneyWayAddOption == null || operOption == null) {
			return modelResult.withError("param.not.null","memberId/drawMoneyWayAddOption/operOption参数不能为null");
		}
		try {
			//校验数据,正确，TODO 
			
			//是否已经存在相同数据
			MemberDrawMoneyWay memberDrawMoneyWayFromDb = memberDrawMoneyWayManager.queryDrawMoneyWayByBankNameAndMemberId(memberId, drawMoneyWayAddOption.getBankName());
			if (memberDrawMoneyWayFromDb != null) {
				modelResult.setModel(memberDrawMoneyWayFromDb);
				return modelResult;
			}
			//组装数据
			MemberDrawMoneyWay memberDrawMoneyWay = new MemberDrawMoneyWay();
			String drawBankInfo = String.format("%s|%s|%s|%s", new Object[]{drawMoneyWayAddOption.getProvince(),drawMoneyWayAddOption.getCity()
					,drawMoneyWayAddOption.getSubbankInfo(),drawMoneyWayAddOption.getBankCardNo()});
			memberDrawMoneyWay.setBankName(drawMoneyWayAddOption.getBankName());
			memberDrawMoneyWay.setDrawBankInfo(drawBankInfo);
			memberDrawMoneyWay.setMemberId(memberId);
			memberDrawMoneyWay.setBankCode(drawMoneyWayAddOption.getBankCode());
			//保存
			MemberDrawMoneyWay memberDrawMoneyWayReturn = memberDrawMoneyWayManager.saveDrawMoneyWay(memberDrawMoneyWay);
			modelResult.setModel(memberDrawMoneyWayReturn);
			operOption.setOperType(OperType.add_draw_money_way.getIndex());
			memberOperLogManager.asyncSaveMemberOperLog(memberId, operOption, OperType.add_draw_money_way.getDescription());
		} catch (Exception e) {
			logger.error("memberDrawMoneyWayManager.saveMemberDrawMoneyWay调用异常!",e);
			modelResult.withError("runtime.exception","系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<MemberDrawMoneyLogDetail> queryDrawMoneyLogDetailById(Long drawMoneyLogId) {
		ModelResult<MemberDrawMoneyLogDetail> modelResult = new ModelResult<MemberDrawMoneyLogDetail>();
		if (drawMoneyLogId == null) {
			return modelResult.withError("param.not.null","drawMoneyLogId参数不能为null");
		}
		try {
			MemberDrawMoneyLogDetail memberDrawMoneyLogDetail = new MemberDrawMoneyLogDetail();
			MemberDrawMoneyLog drawMoneyLog = memberDrawMoneyLogManager.queryDrawMoneyLogById(drawMoneyLogId);
			memberDrawMoneyLogDetail.setMemberDrawMoneyLog(drawMoneyLog);
			if (drawMoneyLog != null && drawMoneyLog.getDrawMoneyWayId() != null) {
				MemberDrawMoneyWay drawMoneyWay = memberDrawMoneyWayManager.queryDrawMoneyWayById(drawMoneyLog.getDrawMoneyWayId());
				memberDrawMoneyLogDetail.setMemberDrawMoneyWay(drawMoneyWay);
			}
			modelResult.setModel(memberDrawMoneyLogDetail);
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.queryDrawMoneyLogDetailById调用异常!",e);
			modelResult.withError("runtime.exception","系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> deleteMemberDrawMoneyWay(Long memberId,Long drawMoneyWayId,MemberOperOption operOption) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		if (memberId == null || drawMoneyWayId == null) {
			return modelResult.withError("param.not.null","memberId/drawMoneyLogId参数不能为null");
		}
		try {
		boolean updateResult = memberDrawMoneyWayManager.updateDrawMoneyWayStatus(drawMoneyWayId, MemberDrawMoneyWayStatus.valid
					, MemberDrawMoneyWayStatus.invalid);
		operOption.setOperType(OperType.del_draw_money_way.getIndex());
		memberOperLogManager.asyncSaveMemberOperLog(memberId, operOption, OperType.del_draw_money_way.getDescription());
		modelResult.setModel(updateResult);
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.deleteMemberDrawMoneyWay调用异常!",e);
			modelResult.withError("runtime.exception","系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public PageResult<MemberDrawMoneyWayResult> queryMemberDrawMoneyWayByPage(MemberDrawMoneyWayQueryOption option,
			DataPage<MemberDrawMoneyWayResult> page) {
		PageResult<MemberDrawMoneyWayResult> pageResult = new PageResult<MemberDrawMoneyWayResult>();
		pageResult.setPage(memberDrawMoneyWayManager.queryMemberDrawMoneyWayByPage(option, page));
		return pageResult;
	}

	@Override
	public PageResult<MemberDrawMoneyLogResult> queryMemberDrawMoneyLogByPage(MemberDrawMoneyLogQueryOption option,
			DataPage<MemberDrawMoneyLogResult> page) {
		PageResult<MemberDrawMoneyLogResult> pageResult = new PageResult<MemberDrawMoneyLogResult>();
		pageResult.setPage(memberDrawMoneyLogManager.queryMemberDrawMoneyLogByPage(option, page));
		return pageResult;
	}
	
	@Override
	public ModelResult<MemberDrawMoneyLogResult> statistMemberDrawMoneyLog(MemberDrawMoneyLogQueryOption option) {
		ModelResult<MemberDrawMoneyLogResult> modelResult = new ModelResult<MemberDrawMoneyLogResult>();
		modelResult.setModel(memberDrawMoneyLogManager.statistMemberDrawMoneyLog(option));
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> updateDrawMoneyLogAuditInfo(MemberDrawMoneyAuditInfoOption option) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		try {
			modelResult.setModel(memberDrawMoneyLogManager.updateDrawMoneyLogAuditInfo(option.getDrawMoneyLogId(),
					option.getAuditStatus(), option.getAuditor(), option.getAuditInfo()));
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.updateDrawMoneyLogAuditInfo调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> updateDrawMoneyStatus(MemberDrawMoneyInfoUpdateOption updateInfoOption) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(false);
		try {
			boolean updateRet = memberDrawMoneyLogManager.updateDrawMoneyStatus(updateInfoOption);
			if (updateInfoOption.getNewStatus() == DrawMoneyStatus.success.getIndex()) {
				//提款成功，更新一下提款方式为最新
				MemberDrawMoneyLog drawMoneyLog = memberDrawMoneyLogManager.queryDrawMoneyLogById(updateInfoOption.getDrawMoneyLogId());
				memberDrawMoneyWayManager.updateDrawMoneyWayRecentlyUse(drawMoneyLog.getDrawMoneyWayId());
			}
			modelResult.setModel(updateRet);
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.updateDrawMoneyStatus调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<Boolean> refundDrawMoney(Long drawMoneyLogId) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(false);
		ModelResult<MemberDrawMoneyLogDetail> queryModelResult = queryDrawMoneyLogDetailById(drawMoneyLogId);
		if (queryModelResult.getModel() == null || queryModelResult.getModel().getMemberDrawMoneyLog() == null) {
			logger.info("提款[{}]订单不存在!",drawMoneyLogId);
			return modelResult.withError("drawmoneyLog.not.find","提款订单不存在");
		}
		MemberDrawMoneyLog drawMoneyLog = queryModelResult.getModel().getMemberDrawMoneyLog();
		if (drawMoneyLog.getStatus() != DrawMoneyStatus.fail.getIndex()) {
			if (drawMoneyLog.getAuditStatus() != AuditStatus.audit_unpass.getIndex()) {
				logger.info("提款[{}]订单状态[{}]不是失败不能退款!",drawMoneyLogId,drawMoneyLog.getStatus());
				return modelResult.withError("drawmoneyLog.status.invalid","退款订单状态不是失败状态!");
			}
		}
		return memberBizDirverWalletManager.refundMemberDrawMoney(drawMoneyLog);
	}

	@Override
	public ModelResult<MemberDrawMoneyLog> queryDrawMoneyLogByDrawMoneyNo(String drawMoneyNO) {
		ModelResult<MemberDrawMoneyLog> modelResult = new ModelResult<MemberDrawMoneyLog>();
		if (StringUtils.isBlank(drawMoneyNO)) {
			return modelResult.withError("param.not.null", "参数不能为null");
		}
		try {
			MemberDrawMoneyLog drawMoneyLog = memberDrawMoneyLogManager.queryDrawMoneyLogById(Long.valueOf(StringUtils.substring(drawMoneyNO, 8)));
			modelResult.setModel(drawMoneyLog);
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.queryDrawMoneyLogByDrawMoneyNo调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

	@Override
	public ModelResult<List<DrawMoneyBank>> queryDrawMoneyBank() {
		ModelResult<List<DrawMoneyBank>> modelResult = new ModelResult<List<DrawMoneyBank>>();
		List<DrawMoneyBank> drawMoneyBankList = drawMoneyBankManager.queryDrawMoneyBank();
		modelResult.setModel(drawMoneyBankList);
		return modelResult;
	}
	

	@Override
	public PageResult<DrawMoneyBranchBank> queryDrawMoneyBranchBank(
			int cityId, int bankId, DataPage<DrawMoneyBranchBank> dataPage) {
		PageResult<DrawMoneyBranchBank> pr = new PageResult<DrawMoneyBranchBank>();
		if (cityId <= 0 || bankId <= 0) {
			pr.setDetailStack("cityId 或bankId参数小于等于0");
			return pr;
		}
		
		pr.setPage(drawMoneyBankManager.queryDrawMoneyBranchBank(cityId,
				bankId, dataPage));
		return pr;

	}

	@Override
	public PageResult<DrawMoneyProvinceCity> queryDrawMoneyProvince(
			DataPage<DrawMoneyProvinceCity> dataPage, int includeCity) {
		
		PageResult<DrawMoneyProvinceCity> pr = new PageResult<DrawMoneyProvinceCity>();
		pr.setPage(drawMoneyBankManager.queryDrawMoneyProvince(dataPage,
				includeCity));
		return pr;

	}

	@Override
	public PageResult<DrawMoneyProvinceCity> queryDrawMoneyCity(int provinceId,
			DataPage<DrawMoneyProvinceCity> dataPage) {
		PageResult<DrawMoneyProvinceCity> pr = new PageResult<DrawMoneyProvinceCity>();
		pr.setPage(drawMoneyBankManager
				.queryDrawMoneyCity(provinceId, dataPage));
		return pr;
	}

	@Override
	public void insertBranchBank(DrawMoneyBranchBank drawMoneyBranchBank) {
		drawMoneyBankManager.insertBranchBank(drawMoneyBranchBank);
	}
	@Override
	public void updateBranchBank(DrawMoneyBranchBank drawMoneyBranchBank) {
		drawMoneyBankManager.updateBranchBank(drawMoneyBranchBank);
	}
	@Override
	public DataPage<DrawMoneyBranchBankVo> queryDrawMoneyBranchBankDataPage(
			Integer cityId, Integer bankId, String name,
			DataPage<DrawMoneyBranchBankVo> dataPage) {
		return drawMoneyBankManager.queryDrawMoneyBranchBankDataPage(cityId, bankId, name, dataPage);
	}
	@Override
	public List<DrawMoneyProvinceCity> queryProvinceCityList() {
		return drawMoneyBankManager.queryProvinceCityList();
	}
	@Override
	public DrawMoneyBranchBank queryBranchBankById(long id) {
		return drawMoneyBankManager.queryBranchBankById(id);
	}

	@Override
	public PageResult<DrawMoneyBranchBank> queryDrawMoneyBranchBank(int cityId,
			int bankId, String branchName, DataPage<DrawMoneyBranchBank> dataPage) {
		PageResult<DrawMoneyBranchBank> pageResult = new PageResult<DrawMoneyBranchBank>();
		DataPage<DrawMoneyBranchBank> p = drawMoneyBankManager.queryDrawMoneyBranchBank(cityId, bankId,dataPage);
		
		if (StringUtils.isNotBlank(branchName)) {
			List<DrawMoneyBranchBank> l = p.getDataList();
			List<DrawMoneyBranchBank> lnew = new ArrayList<DrawMoneyBranchBank>();
			int totalrow = 0;
			for (DrawMoneyBranchBank d : l) {
				if (d.getName().contains(branchName)) {
					lnew.add(d);
					totalrow = totalrow + 1;
				}
			}
			p.setDataList(lnew);
			p.setTotalCount(totalrow);
		}
		pageResult.setPage(p);
		return pageResult;
	}

	@Override
	public ModelResult<Boolean> updateDrawMoneyWithManual(MemberDrawMoneyMannualUpdateOption mannualUpdateOption) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>();
		modelResult.setModel(false);
		if (mannualUpdateOption == null || mannualUpdateOption.getDrawMoneyLogId() == null) {
			return modelResult.withError("param.not.null", "参数不能为null");
		}
		try {
			boolean retUpdate = memberDrawMoneyLogManager.updateDrawMoneyWithManual(mannualUpdateOption);
			modelResult.setModel(retUpdate);
		} catch (Exception e) {
			logger.error("MemberDrawMoneyWriteService.updateDrawMoneyWithManual调用异常!", e);
			modelResult.withError("runtime.exception", "系统运行时异常!");
		}
		return modelResult;
	}

}
