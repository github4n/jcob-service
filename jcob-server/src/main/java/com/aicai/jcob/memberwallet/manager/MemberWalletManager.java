package com.aicai.jcob.memberwallet.manager;

import java.math.BigDecimal;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.Member;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.domain.MemberWalletContext;

/**
 * 钱包管理接口
 * 
 * @project jcob-server
 * @author duannp
 * @date 2016年1月26日
 */
public interface MemberWalletManager {
	
	/**
	 * memberId查询用户钱包
	 * @param memberId
	 * @return
	 */
	public MemberWallet queryMemberWalletByMemberId(Long memberId);
	/**
	 * 修改钱包期末
	 * @param memberWalletEnd
	 * @return
	 */
	public int updateMemberWalletEnd(MemberWallet memberWalletEnd);
	
	/**
	 * 查询钱包并且加锁
	 * @param memeberId
	 * @return
	 * @create_time 2016年1月27日 下午2:17:15
	 */
	public MemberWallet lockMemberWallet(Long memeberId);
	
	/**
	 * 用户注册初始化钱包
	 * @param member
	 * @return
	 */
	public MemberWallet initMemberWallet(Member member);
	
	/**
	 * 分页查询用户钱包列表
	 * @param pageWalletOption
	 * @param dataPage
	 * @return
	 */
	public DataPage<PageWalletVo> pageMemberWallet(PageWalletOption pageWalletOption ,DataPage<PageWalletVo> dataPage);
	/**
	 * 统计总额
	 * @param pageWalletOption
	 * @return
	 */
	public BigDecimal sumMemberWallet(PageWalletOption pageWalletOption);
	
	/**
	 * 用户钱包加钱-内部无事物
	 * @param memberId
	 * @param happenAmount
	 * @param bizType
	 * @param bizId
	 * @param bizNo
	 * @param walletContext
	 */
	public void addAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount,BizType bizType,Long bizId,String bizNo,MemberWalletContext walletContext);
	/**
	 * 用户钱包减钱-内部无事物
	 * @param memberId
	 * @param happenAmount
	 * @param bizType
	 * @param bizId
	 * @param bizNo
	 * @param walletContext
	 */
	public void subtractAbleBalanceWithoutTransaction(Long memberId,BigDecimal happenAmount,BizType bizType,Long bizId,String bizNo,MemberWalletContext walletContext);
}
