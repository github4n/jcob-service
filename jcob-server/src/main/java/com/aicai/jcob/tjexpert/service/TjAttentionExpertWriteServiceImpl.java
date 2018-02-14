package com.aicai.jcob.tjexpert.service;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.member.common.domain.option.MemberOperOption;
import com.aicai.jcob.tjexpert.common.domain.TjAttentionExpert;
import com.aicai.jcob.tjexpert.common.domain.option.TjAttentionExpertQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;
import com.aicai.jcob.tjexpert.common.service.TjAttentionExpertWriteService;
import com.aicai.jcob.tjexpert.manager.TjAttentionExpertManager;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月16日 下午2:15:15 
 * 程序的简单说明 
 */
public class TjAttentionExpertWriteServiceImpl implements
		TjAttentionExpertWriteService {

	@Autowired
	@Qualifier("tjAttentionExpertManagerImpl")
	private TjAttentionExpertManager tjAttentionExpertManager ;
	
	@Override
	public ModelResult<Boolean> attentionOperByMemberId(Long memberId,
			Long expertMemberId, Integer attentionStatus,MemberOperOption memberOperOption) {
		return tjAttentionExpertManager.attentionOperByMemberId(memberId, expertMemberId, attentionStatus, memberOperOption) ;
	}
	
	@Override
	public ModelResult<List<Long>> queryAttentionMemberIdListByMemberId(
			Long memberId) {
		List<Long> memberIdList = tjAttentionExpertManager.queryAttentionExpertByMemberId(memberId);
		return new ModelResult<List<Long>>(memberIdList);
	}

	@Override
	public PageResult<TjAttentionExpertAdminResult> pageMyAttention(
			DataPage<TjAttentionExpert> page,
			TjAttentionExpertQueryOption option) {
		PageResult<TjAttentionExpertAdminResult> pageResult = new PageResult<TjAttentionExpertAdminResult>() ;
		pageResult.setPage(tjAttentionExpertManager.pageMyAttentionByOption(option, page));
		return pageResult;
	}

	@Override
	public PageResult<TjAttentionExpertAdminResult> pageAttentionMe(
			DataPage<TjAttentionExpert> page,
			TjAttentionExpertQueryOption option) {
		PageResult<TjAttentionExpertAdminResult> pageResult = new PageResult<TjAttentionExpertAdminResult>() ;
		pageResult.setPage(tjAttentionExpertManager.pageAttentionMeByOption(option, page));
		return pageResult;
	}

	@Override
	public PageResult<TjAttentionLogAdminResult> pageMyAttentionOper(
			DataPage<TjAttentionLogAdminResult> page,
			TjAttentionExpertQueryOption option) {
		PageResult<TjAttentionLogAdminResult> pageResult = new PageResult<TjAttentionLogAdminResult>() ;
		if(option==null){
			if (page == null) {
				return pageResult.withError("param.not.null","参数不能为null");
			}
			if (option == null) {
				option = new TjAttentionExpertQueryOption();
			}
		}
		pageResult.setPage(tjAttentionExpertManager.pageMyAttentionOperByOption(option, page));
		return pageResult;
	}

	@Override
	public PageResult<TjAttentionExpertResult> pageExpertAttention(Long memberId,
			Long expertMemberId, DataPage<TjAttentionExpertResult> page) {
		PageResult<TjAttentionExpertResult> pageResult = new PageResult<TjAttentionExpertResult>() ;
		pageResult.setPage(tjAttentionExpertManager.pageExpertAttention(memberId, expertMemberId, page));
		return pageResult;
	}

	@Override
	public PageResult<TjAttentionExpertResult> pageExpertFans(Long memberId,
			Long expertMemberId, DataPage<TjAttentionExpertResult> page) {
		PageResult<TjAttentionExpertResult> pageResult = new PageResult<TjAttentionExpertResult>() ;
		pageResult.setPage(tjAttentionExpertManager.pageExpertFans(memberId, expertMemberId, page));
		return pageResult;
	}

	@Override
	public boolean isAttention(Long memberId, Long expertMemberId) {	
		return tjAttentionExpertManager.isAttention(memberId, expertMemberId);
	}

}
 