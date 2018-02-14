package com.aicai.jcob.test.tjexpert;  

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjexpert.common.domain.TjAttentionExpert;
import com.aicai.jcob.tjexpert.common.domain.option.TjAttentionExpertQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertAdminResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionExpertResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjAttentionLogAdminResult;
import com.aicai.jcob.tjexpert.common.service.TjAttentionExpertWriteService;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月27日 下午1:59:18 
 * 程序的简单说明 
 */
public class TjAttentionExpertTest extends TestBase{
	
	@Autowired
	@Qualifier("tjAttentionExpertWriteServiceImpl")
	private TjAttentionExpertWriteService tjAttentionExpertWriteService ;
	@Test
	public void pageMyAttention(){
		TjAttentionExpertQueryOption option = new TjAttentionExpertQueryOption() ;
		option.setMemberId(1130l);
		option.setNickName("");
		option.setPhone("");
		DataPage<TjAttentionExpert> page = new DataPage<TjAttentionExpert>() ;
	    PageResult<TjAttentionExpertAdminResult> pageResult = tjAttentionExpertWriteService.pageMyAttention(page, option) ;
	
	    System.out.println(pageResult.getPage().getTotalCount()) ;
	}
	
	@Test
	public void listAttentionMemberId(){
		ModelResult<List<Long>> list = tjAttentionExpertWriteService.queryAttentionMemberIdListByMemberId(1139l);
		System.out.println("list size:========"+list.getModel().size()) ;
		System.out.println("memberId:==========="+list.getModel().get(0).longValue()) ;
	}
	
	@Test
	public void pageExpertAttention(){
		DataPage<TjAttentionExpertResult> page  = new DataPage<TjAttentionExpertResult>() ;
		PageResult<TjAttentionExpertResult> pageResult  = tjAttentionExpertWriteService.pageExpertAttention(null, 1139l, page);
		
		System.out.println(pageResult.getPage().getDataList().size());
		
	}
	@Test
	public void pageExpertFans(){
		DataPage<TjAttentionExpertResult> page  = new DataPage<TjAttentionExpertResult>() ;
		PageResult<TjAttentionExpertResult> pageResult  = tjAttentionExpertWriteService.pageExpertFans(null, 1139l, page);
		
		System.out.println(pageResult.getPage().getDataList().size());
		
	}
	@Test
	public void pageMyAttentionOper(){
		DataPage<TjAttentionLogAdminResult> page = new DataPage<TjAttentionLogAdminResult>();
		TjAttentionExpertQueryOption option = new TjAttentionExpertQueryOption() ;
		option.setMemberId(1131l);
		option.setAttentionMemberId(1135l);
		PageResult<TjAttentionLogAdminResult> pageResult = tjAttentionExpertWriteService.pageMyAttentionOper(page, option) ;
		System.out.println(pageResult.getPage().getDataList().get(0).getAttentionMemberId());
		System.out.println(pageResult.getPage().getDataList().get(0).getAttentionNickName());
	}
}
 