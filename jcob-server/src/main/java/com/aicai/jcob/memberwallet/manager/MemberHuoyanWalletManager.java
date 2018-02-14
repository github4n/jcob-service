package com.aicai.jcob.memberwallet.manager;

import java.math.BigDecimal;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.membercharge.common.domain.MemberChargeLog;
import com.aicai.jcob.memberdrawmoney.common.domain.MemberDrawMoneyLog;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWallet;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.domain.MemberWalletContext;
import com.aicai.jcob.tjplan.common.domain.TjOrder;
import com.aicai.jcob.tjplan.common.domain.TjPlan;

/**
 * 火眼钱包管理接口
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月26日
 */
public interface MemberHuoyanWalletManager {
	
	/**
	 * memberId查询用户火眼钱包
	 * @param memberId
	 * @return
	 */
	public MemberHuoyanWallet queryMemberHuoyanWalletByMemberId(Long memberId);
	/**
	 * 修改火眼钱包期末
	 * @param memberHuoyanWalletEnd
	 * @return
	 * @create_time 2016年1月27日 下午5:56:25
	 */
	public int updateMemberHuoyanWalletEnd(MemberHuoyanWallet memberHuoyanWalletEnd);
	/**
	 * 查询火眼钱包并且加锁
	 * @param memeberId
	 * @return
	 * @create_time 2016年1月27日 下午2:17:15
	 */
	public MemberHuoyanWallet lockMemberHuoyanWallet(Long memeberId);
	

	/**
	 * 用户注册初始化火眼钱包
	 * @param member
	 * @return
	 */
	public MemberHuoyanWallet initMemberHuoyanWallet(Member member);

	/**
	 * 分页查询用户钱包列表
	 * @param pageWalletOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageWalletVo> pageMemberHuoyanWallet(PageWalletOption pageWalletOption ,DataPage<PageWalletVo> dataPage);
	/**
	 * 统计火眼总额
	 * @param pageWalletOption
	 * @return
	 */
	public BigDecimal sumMemberHuoyanWallet(PageWalletOption pageWalletOption);
	
	/**
	 * 用户火眼钱包加钱-内部无事物
	 * @param memberId
	 * @param happenAmount
	 * @param bizType
	 * @param bizId
	 * @param bizNo
	 * @param walletContext
	 */
	public void addAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount,BizType bizType,Long bizId,String bizNo,MemberWalletContext walletContext);
	/**
	 * 用户火眼钱包减钱-内部无事物
	 * @param memberId
	 * @param happenAmount
	 * @param bizType
	 * @param bizId
	 * @param bizNo
	 * @param walletContext
	 */
	public void subtractAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount,BizType bizType,Long bizId,String bizNo,MemberWalletContext walletContext);
}
