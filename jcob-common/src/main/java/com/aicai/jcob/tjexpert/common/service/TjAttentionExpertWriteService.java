package com.aicai.jcob.tjexpert.common.service;  

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjexpert.common.domain.TjAttentionExpert;
import com.aicai.jcob.tjexpert.common.domain.option.TjAttentionExpertQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月16日 下午1:43:11 
 * 程序的简单说明 
 */
public interface TjAttentionExpertWriteService {

	/**
	 * 关注和取关操作
	 * @param memberId 粉丝
	 * @param expertMemberId 明星
	 * @param attentionStatus {@link com.aicai.jcob.tjexpert.common.domain.constant.AttentionStatus}
	 * @param memberOperOption {@link com.aicai.jcob.member.common.domain.option.MemberOperOption}  写出明确的oper_type,remark写出memberId和昵称,用逗号隔开,形式:1111,昵称
	 * @return
	 */
	ModelResult<Boolean> attentionOperByMemberId(Long memberId,Long expertMemberId,Integer attentionStatus,MemberOperOption memberOperOption);


	/**
	 * 根据memberId查询关注的专家的memberId List
	 * @param memberId
	 * @return
	 */
	ModelResult<List<Long>> queryAttentionMemberIdListByMemberId(Long memberId);
	
	/**
	 * 我关注的人查询(我的关注查询)
	 * @param page
	 * @param option
	 * @return
	 */
	PageResult<TjAttentionExpertAdminResult> pageMyAttention(DataPage<TjAttentionExpert> page,TjAttentionExpertQueryOption option);
	
	/**
	 * 关注我的人查询
	 * @param page
	 * @param option
	 * @return
	 */
	PageResult<TjAttentionExpertAdminResult> pageAttentionMe(DataPage<TjAttentionExpert> page,TjAttentionExpertQueryOption option);

	/**
	 * 我的关注操作记录,也叫关注操作记录
	 * @param page
	 * @param option
	 * @return
	 */
	PageResult<TjAttentionLogAdminResult> pageMyAttentionOper(DataPage<TjAttentionLogAdminResult> page,TjAttentionExpertQueryOption option) ;
	
	/**
	 * 前台ta的关注()
	 * @param memberId 登录用户id
	 * @param expertMemberId 专家memberId
	 * @param page
	 * @return
	 */
	public PageResult<TjAttentionExpertResult> pageExpertAttention(Long memberId,Long expertMemberId,DataPage<TjAttentionExpertResult> page) ;
	
	/**
	 * 前台ta的粉丝()
	 * @param memberId 登录用户id
	 * @param expertMemberId 专家memberId
	 * @param page
	 * @return
	 */
	public PageResult<TjAttentionExpertResult> pageExpertFans(Long memberId,Long expertMemberId,DataPage<TjAttentionExpertResult> page) ;
	
	/**
	 * 查询是否关注某个专家
	 * @param memberId 粉丝
	 * @param expertMemberId 明星
	 * @return
	 */
	boolean isAttention(Long memberId,Long expertMemberId);
}
 