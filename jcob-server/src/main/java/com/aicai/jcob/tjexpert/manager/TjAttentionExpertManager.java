package com.aicai.jcob.tjexpert.manager;  

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjexpert.common.domain.TjAttentionExpert;
import com.aicai.jcob.tjexpert.common.domain.option.TjAttentionExpertQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月16日 下午2:09:54 
 * 程序的简单说明 
 */
public interface TjAttentionExpertManager {

	/**
	 * 关注操作
	 * @param memberId
	 * @param expertMemberId
	 * @param attentionStatus
	 * @return
	 */
	int updateAttentionMemberId(Long memberId,Long expertMemberId,Integer attentionStatus);
	
	//int insertTjAttentionExpert(TjAttentionExpert tjAttentionExpert);
	
	/**
	 * 查询关注的专家
	 * @param memberId
	 * @return
	 */
	List<Long> queryAttentionExpertByMemberId(Long memberId);
	
	/**
	 * 查询专家的粉丝
	 * @param expertMemberId
	 * @return
	 */
	List<Long> queryFansByExpertMemberId(Long expertMemberId);
	/**
	 * 查询是否关注某个专家
	 * @param memberId 粉丝
	 * @param expertMemberId 明星
	 * @return
	 */
	boolean isAttention(Long memberId,Long expertMemberId);
	
/*	int queryAttentionCount(Long expertMemberId);*/
	/**
	 * 查询之前是否有关注某个专家的操作
	 * @param memberId 粉丝
	 * @param expertMemberId 明星
	 * @return
	 */
	boolean hasAttentionLog(Long memberId,Long expertMemberId) ;
	
	DataPage<TjAttentionExpertAdminResult> pageMyAttentionByOption(TjAttentionExpertQueryOption option,DataPage<TjAttentionExpert> page) ;
	
	DataPage<TjAttentionExpertAdminResult> pageAttentionMeByOption(TjAttentionExpertQueryOption option,DataPage<TjAttentionExpert> page) ;
	
	DataPage<TjAttentionLogAdminResult> pageMyAttentionOperByOption(TjAttentionExpertQueryOption option,DataPage<TjAttentionLogAdminResult> page) ;
	
	/*DataPage<TjAttentionLogAdminResult> pageAttentionMeOperByOption(TjAttentionExpertQueryOption option,DataPage<TjAttentionLogAdminResult> page) ;*/
	
	DataPage<TjAttentionExpertResult> pageExpertAttention(Long memberId,Long expertMemberId, DataPage<TjAttentionExpertResult> page) ;
	DataPage<TjAttentionExpertResult> pageExpertFans(Long memberId,Long expertMemberId,DataPage<TjAttentionExpertResult> page) ;
	
	ModelResult<Boolean> attentionOperByMemberId(Long memberId,
			Long expertMemberId, Integer attentionStatus,MemberOperOption memberOperOption) ;
}
 