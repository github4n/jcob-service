package com.aicai.jcob.test.tjexpert;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.exception.common.JcobServerException;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjexpert.common.domain.TjExpertApplyInfo;
import com.aicai.jcob.tjexpert.common.domain.TjExpertInfo;
import com.aicai.jcob.tjexpert.common.domain.constant.WinRatioRankField;
import com.aicai.jcob.tjexpert.common.domain.option.TjExpertWinRatioQueryOption;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertAdminWinRatioResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertInfoResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertRecommendResult;
import com.aicai.jcob.tjexpert.common.domain.result.TjExpertWinRatioResult;
import com.aicai.jcob.tjexpert.common.service.TjAttentionExpertWriteService;
import com.aicai.jcob.tjexpert.common.service.TjExpertApplyWriteService;
import com.aicai.jcob.tjexpert.common.service.TjExpertInfoWriteService;
import com.aicai.jcob.tjexpert.common.service.TjExpertSetInfoWriteService;

/** 
 * @author jing.ming
 * @version 创建时间：2016年1月27日 上午10:57:30 
 * 程序的简单说明 
 */
public class TjExpertTest extends TestBase{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("tjExpertInfoWriteServiceImpl")
	private TjExpertInfoWriteService tjExpertInfoWriteService ;
	
	@Autowired
	@Qualifier("tjExpertApplyWriteServiceImpl")
	private TjExpertApplyWriteService tjExpertApplyWriteService ;
	
	@Autowired
	@Qualifier("tjExpertSetInfoWriteServiceImpl")
	private TjExpertSetInfoWriteService personSetExpertService ;
	@Autowired
	@Qualifier("tjAttentionExpertWriteServiceImpl")
	private TjAttentionExpertWriteService tjAttentionExpertWriteService ;
	@Test
	public void testQueryPage(){
		/*TjExpertInfoQueryOption tjExpertInfoQueryOption = new TjExpertInfoQueryOption();
		tjExpertInfoQueryOption.setMemberId(1110l);
		//tjExpertInfoQueryOption.setLevel(TjExpertLevel.normal.getIndex());
		DataPage<TjExpertInfo> page = new DataPage<TjExpertInfo>();
		//page.setOrderBy("B.update_time DESC");
		PageResult<TjExpertInfo> tjExpertInfoResult =  tjExpertInfoWriteService.pageTjExpertInfo(page, tjExpertInfoQueryOption);
		List<TjExpertInfo> tjExpertInfoList = tjExpertInfoResult.getPage().getDataList();
		for(TjExpertInfo tjExpertInfo:tjExpertInfoList){
			System.out.println("用户:"+tjExpertInfo.getMemberId()+"========成长轨迹:=======");
			List<TjExpertLevelLog> tjExpertLevelLogList = tjExpertInfo.getTjExpertLevelLogList();
			for(TjExpertLevelLog tjExpertLevelLog:tjExpertLevelLogList){
				System.out.println(DateUtil.getDateStringByZdGs(tjExpertLevelLog.getCreateTime(),"yyyy-MM-dd HH:mm")+"  "+tjExpertLevelLog.getHandleType()+" 为"+tjExpertLevelLog.getNewLevel());
			}
		}		*/
	}
	
	@Test
	public void testInsertExpertInfo(){
		//ModelResult<TjExpertSetInfo> model = personSetExpertService.insertPersonSetExpert(1111l, "明静", "4273774775747775", "竞彩大牛", TjExpertLevel.formal_expert, "jing.ming");
		/*if(model.isSuccess()){
			System.out.println("更新成功?用户id=="+model.getModel().getMemberId());
		}*/
		/*ModelResult<Boolean> modelResult = tjExpertInfoWriteService.addTjexpert(1110l, "明静", "420322737472737371", "专注亚盘,大小球,是足彩高手!", TjExpertLevel.test_expert);
	
		Boolean tjExpertInfo = modelResult.getModel();
		System.out.println("更新成功?:"+tjExpertInfo);*/
	}
	@Test
	public void testQueryExpertInfoByMemberId(){
		/*ModelResult<TjExpertApplyInfo> result = tjExpertApplyWriteService.queryExpertByMemberId(1111l);
		if(!result.isSuccess()){
			logger.info("error,error");
		}
		TjExpertApplyInfo tjExpertApplyInfo = result.getModel();
		System.out.println("昵称:"+tjExpertApplyInfo.getMember().getNickName());*/
	}
	@Test
	public void pageTjExpertApplyInfo(){
		/*TjExpertApplyInfoQueryOption tjExpertApplyInfoQueryOption = new TjExpertApplyInfoQueryOption();
		tjExpertApplyInfoQueryOption.setMemberId(1111l);
		//tjExpertApplyInfoQueryOption.setNickName("静静");
		DataPage<TjExpertApplyInfo> page = new DataPage<TjExpertApplyInfo>();
		page.setOrderBy("tj_expert_apply_info.update_time DESC");
		PageResult<TjExpertApplyInfo> pageResult = tjExpertApplyWriteService.pageTjExpertApplyInfo(page, tjExpertApplyInfoQueryOption);
		if(pageResult.isSuccess()){
			List<TjExpertApplyInfo> expertApplyInfoList = pageResult.getPage().getDataList();
			for(TjExpertApplyInfo tjExpertApplyInfo:expertApplyInfoList){
				System.out.println("==============page expert apply info===========");
				System.out.println("nickName:"+tjExpertApplyInfo.getNickName());
				System.out.println("memberId:"+tjExpertApplyInfo.getMemberId());
				System.out.println("checkStatus:"+tjExpertApplyInfo.getCheckStatus());
			}
		}*/
	}
	
	@Test
	public void testCheckStatus(){
		ModelResult<Boolean> result = tjExpertApplyWriteService.checkExpert(1, "", 1111l);
		if(!result.isSuccess()){
			logger.info("error,error");
		}
		if(result.getModel()==true){
			System.out.println("更新成功!");
		}else{
			System.out.println("更新失败!");
		}
	}
	@Test
	public void applytobeExpert(){
		TjExpertApplyInfo tjExpertApplyInfo = new TjExpertApplyInfo();
		tjExpertApplyInfo.setMemberId(1110l);
		tjExpertApplyInfo.setApplyReason("足彩高手,希望给大家更多的中奖方案,也希望更多人看到我!");
		tjExpertApplyWriteService.applyTobeExpert(tjExpertApplyInfo, 1110l, "明静", "42737474737474747");
	}
	@Test
	public void checkExpert(){
		ModelResult<Boolean> result = tjExpertApplyWriteService.checkExpert(1, "申请理由不充分", 1110l);
		if(result.getModel()==true){
			System.out.println("更新专家申请信息!");
		}		
	}
	
	@Test
	public void queryApplyStatus(){
		tjExpertApplyWriteService.queryApplyProgressInfo(1135l);
	}
	
	@Test
	public void updateDesc(){
		tjExpertInfoWriteService.updateExpertDesc(1110l, "update desc test.");
	}
	
	
	@Test
	public void pageTjExpertByLevel(){
		Integer[] levelArray = {2,3} ;
		DataPage<TjExpertInfo> page = new DataPage<TjExpertInfo>() ;
		page.setPageNo(1);
		page.setPageSize(10);
		PageResult<TjExpertInfoResult> list = tjExpertInfoWriteService.pageTjExpertByLevel(levelArray, 0l, page);
		System.out.println("是否还有下一页:"+list.getPage().isHasNext());
		
		System.out.println("================================"+list.getPage().getDataList().size());
	}
	@Test
	public void pageWinRatioByTime(){
		/*DataPage<TjExpertAdminWinRatioResult> page = new DataPage<TjExpertAdminWinRatioResult>() ;
		page.setPageSize(1); 
		TjExpertInfoQueryOption tjExpertInfoQueryOption = new TjExpertInfoQueryOption() ;
		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DATE, -7);
		Calendar endTime = Calendar.getInstance();
		tjExpertInfoQueryOption.setStartTime(startTime.getTime());
		tjExpertInfoQueryOption.setEndTime(endTime.getTime());
		PageResult<TjExpertAdminWinRatioResult>  pageResult = tjExpertInfoWriteService.pageExpertWinRatio(page, tjExpertInfoQueryOption) ;
		if(pageResult.isSuccess()){
			List<TjExpertAdminWinRatioResult> list = pageResult.getPage().getDataList() ;
			System.out.println(list.size()) ;
		}*/
	}
	@Test
	public void hasAttentionLog(){
		//memberId 1139l
		//expertMemberId 1130l
		//tjAttentionExpertWriteService.attentionOperByMemberId(1139l, 1130l, 1);
	}
	
	@Test
	public void queryRecommendExpertByMemberId(){
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1110l) ;
		memberIdList.add(1125l) ;
		memberIdList.add(1126l) ;
		ModelResult<Map<Long, TjExpertRecommendResult>>  modelResult = tjExpertInfoWriteService.queryRecommendExpertByMemberId(memberIdList);
	    if(!modelResult.isSuccess()){
	    	throw  new JcobServerException(modelResult.getErrorCode(), modelResult.getErrorMsg()) ;
	    }
	    Map<Long, TjExpertRecommendResult> map = modelResult.getModel() ;
	    System.out.println(map.get(1110l).getNickName());
	}
	@Test
	public void pageExpertWinRatioByRank(){
		DataPage<TjExpertWinRatioResult> dataPage = new DataPage<TjExpertWinRatioResult>() ;
		dataPage.setPageNo(1);
		dataPage.setPageSize(10);
		Integer winRatioRankField = WinRatioRankField.month_rank.getIndex() ;
		Integer leastCountPlan=5 ;
		tjExpertInfoWriteService.pageExpertWinRatioByRank(dataPage, winRatioRankField, leastCountPlan);
	}
	
	@Test
	public void pageExpertWinRatio() {
		DataPage<TjExpertAdminWinRatioResult> dataPage = new DataPage<TjExpertAdminWinRatioResult>();
		TjExpertWinRatioQueryOption queryOption = new TjExpertWinRatioQueryOption();
	/*	queryOption.setOrderType(1);
		queryOption.setDay(7);
		queryOption.setQueryStartWinRatio(new BigDecimal(1));
		queryOption.setQueryEndWinRatio(new BigDecimal(100));
		*/
		queryOption.setNickName("曾丹丹1");
		PageResult<TjExpertAdminWinRatioResult> pageResult = tjExpertInfoWriteService.pageExpertWinRatio(dataPage, queryOption);
		DataPage<TjExpertAdminWinRatioResult> page = pageResult.getPage();
		
	}
	
	@Test
	public void queryExpertMemberId() {
		DataPage<Long> dataPage = new DataPage<Long>();
		PageResult<Long> modelResult = tjExpertInfoWriteService.queryExpertMemberId(dataPage);
		DataPage<Long> data = modelResult.getPage();
	}
}
 