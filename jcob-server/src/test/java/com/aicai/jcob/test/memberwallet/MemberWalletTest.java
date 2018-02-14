package com.aicai.jcob.test.memberwallet;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.memberwallet.common.domain.MemberHuoyanWallet;
import com.aicai.jcob.memberwallet.common.domain.MemberWallet;
import com.aicai.jcob.memberwallet.common.domain.constant.BizType;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletLogOption;
import com.aicai.jcob.memberwallet.common.domain.option.PageWalletOption;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletLogVo;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletResult;
import com.aicai.jcob.memberwallet.common.domain.result.PageWalletVo;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletLogWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberWalletLogWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberWalletWriteService;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberHuoyanWalletManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;
import com.aicai.jcob.memberwallet.manager.MemberWalletManager;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.option.PageLookedTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyAttentionExpertTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageTjPlanToHallOption;
import com.aicai.jcob.tjplan.common.domain.result.PageMyLookedTjplanResult;
import com.aicai.jcob.tjplan.common.domain.result.PageTjplanToHallResult;
import com.aicai.jcob.tjplan.common.service.TjPlanWriteService;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;


public class MemberWalletTest extends TestBase{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberWalletLogWriteService memberWalletLogWriteService;
	@Autowired
	private MemberWalletWriteService memberWalletWriteService;
	@Autowired
	private MemberWalletLogManager memberWalletLogManager;
	@Autowired
	private TjPlanWriteService tjPlanWriteService;
	@Autowired
	private TjPlanManager tjPlanManager;
	@Autowired
	private MemberHuoyanWalletWriteService memberHuoyanWalletWriteService;
	@Autowired
	private MemberHuoyanWalletLogWriteService memberHuoyanWalletLogWriteService;
	@Autowired
	private MemberHuoyanWalletLogManager memberHuoyanWalletLogManager;
	@Autowired
	private MemberHuoyanWalletManager memberHuoyanWalletManager;
	
	@Autowired
	private MemberWalletManager memberWalletManager;
	@Test
	public void testQueryPage(){
//		System.out.println("------------------------------");
//		PageMyTjPlanOption pageMyTjPlanOption = new PageMyTjPlanOption();
//		pageMyTjPlanOption.setEndDate(Calendar.getInstance());
//		pageMyTjPlanOption.setStartDate(DateUtil.getTheDayZero());
//		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
//		PageResult<PageTjplanToHallResult> result = tjPlanWriteService.pageMyPublishPlan(1134L, pageMyTjPlanOption, dataPage);
//		System.out.println(result.getPage().getDataList());
		
//		System.out.println("_________+++++++++++++++----------------");
//		PageLookedTjPlanOption pageMyTjPlanOption = new PageLookedTjPlanOption();
//		pageMyTjPlanOption.setEndDate(Calendar.getInstance());
//		pageMyTjPlanOption.setStartDate(DateUtil.getTheDayZero());
//		DataPage<PageMyLookedTjplanResult> dataPage = new DataPage<PageMyLookedTjplanResult>();
//		PageResult<PageMyLookedTjplanResult> result  = tjPlanWriteService.pageMyLookedPlan(1134L, pageMyTjPlanOption, dataPage);
//		System.out.println(result.getPage().getDataList());
		
//		List<BizType> bizTypeList = new ArrayList<BizType>();
//		bizTypeList.add(BizType.draw_money);
//		bizTypeList.add(BizType.look_plan);
//		List<WalletOpType> walletOpTypeList = null;
//		Calendar beginTime = Calendar.getInstance();
//		beginTime.add(Calendar.DATE, -7);
//		Calendar endTime = Calendar.getInstance();
//		DataPage<MemberWalletLog> dataPage = new DataPage<MemberWalletLog>();
//		memberWalletLogWriteService.pageMemberWalletLog(1126L, bizTypeList, walletOpTypeList, beginTime, endTime, dataPage);
//		
		
		System.out.println("++++++++++++++++++++++++++++++++++");
//		Calendar beginTime = Calendar.getInstance();
//		beginTime.add(Calendar.DATE, -7);
//		Calendar endTime = Calendar.getInstance();
//		Map<Integer, BigDecimal> resultMap = memberWalletLogManager.sumMemberWalletLogAmount(1126L, beginTime, endTime);
//		for (Entry<Integer, BigDecimal> reMap : resultMap.entrySet()) {
//			System.out.println(reMap.getKey());
//			System.out.println(reMap.getValue().compareTo(BigDecimal.valueOf(0)));
//			System.out.println(reMap.getKey() == BizType.draw_money.getIndex());
//		}
//		System.out.println(resultMap.get(Integer.valueOf(BizType.draw_money.getIndex())) +"");
//		System.out.println(resultMap.get(BizType.charge.getIndex()));
//		System.out.println(resultMap.get(BizType.look_plan.getIndex()));
		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
		PageTjPlanToHallOption pageTjPlanToHallOption = new PageTjPlanToHallOption();
		pageTjPlanToHallOption.setMemberId(1135L);
		tjPlanWriteService.pageTjPlanToHall(pageTjPlanToHallOption, dataPage);

	}
	
	@Test
	public void testCancleRace(){
		System.out.println("=============1=====================");
		List<Long> planIdList = new ArrayList<Long>();
		planIdList.add(23L);
		tjPlanWriteService.cancleRace(planIdList, null);
		System.out.println("================2==================");
	}
	
	@Test
	public void pageMyAttentionExpertPublishPlan(){
		System.out.println("=============1=====================");
		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
		PageMyAttentionExpertTjPlanOption pageMyAttentionExpertTjPlanOption = new PageMyAttentionExpertTjPlanOption();
		pageMyAttentionExpertTjPlanOption.setEndDate(Calendar.getInstance());
		Calendar beginTime = Calendar.getInstance();
		beginTime.add(Calendar.DATE, -7);
		pageMyAttentionExpertTjPlanOption.setStartDate(beginTime);
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1134L);
		memberIdList.add(1126L);
		pageMyAttentionExpertTjPlanOption.setMyAttentionExpertMemberIdList(memberIdList);
		tjPlanWriteService.pageMyAttentionExpertPublishPlan(1126L, pageMyAttentionExpertTjPlanOption, dataPage);
		System.out.println("================2==================");
	}
	@Test
	public void pagePublisPlanToHall(){
		System.out.println("=============1=====================");
		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
		PageTjPlanToHallOption pageTjPlanToHallOption = new PageTjPlanToHallOption();
		pageTjPlanToHallOption.setMemberId(1126L);
		pageTjPlanToHallOption.setTjExpertLevelList(TjExpertLevel.getAllList());
		List<TjRaceLeagueType> leagueTypeList = TjRaceLeagueType.getAllList();
		pageTjPlanToHallOption.setLeagueTypeList(leagueTypeList);
		pageTjPlanToHallOption.setGameTypeList(GameType.getAllList());
		pageTjPlanToHallOption.setTjRaceStatus(TjRaceStatus.not_match);
		tjPlanWriteService.pageTjPlanToHall(pageTjPlanToHallOption, dataPage);
		System.out.println("================2==================");
	}
	@Test
	public void queryIndexTjPlanList(){
		System.out.println("=============1=====================");
		List<String> planNoList = new ArrayList<String>();
		planNoList.add("2016022418");
		planNoList.add("2016022419");
		tjPlanWriteService.queryIndexTjPlanList(planNoList);
		System.out.println("================2==================");
	}
	
	@Test
	public void pageMyLookedPlan(){
		System.out.println("_________+++++++++++++++----------------");
		PageLookedTjPlanOption pageMyTjPlanOption = new PageLookedTjPlanOption();
		pageMyTjPlanOption.setEndDate(Calendar.getInstance());
		Calendar endDate = DateUtil.getTheDayZero();
		endDate.add(Calendar.DATE, -30);
		pageMyTjPlanOption.setStartDate(endDate);
		DataPage<PageMyLookedTjplanResult> dataPage = new DataPage<PageMyLookedTjplanResult>();
		PageResult<PageMyLookedTjplanResult> result  = tjPlanWriteService.pageMyLookedPlan(1134L, pageMyTjPlanOption, dataPage);
		System.out.println(result.getPage().getDataList());
	}
	@Test
	public void pageMyPublishPlan(){
		System.out.println("------------------------------");
		PageMyTjPlanOption pageMyTjPlanOption = new PageMyTjPlanOption();
		pageMyTjPlanOption.setEndDate(Calendar.getInstance());
		pageMyTjPlanOption.setStartDate(DateUtil.getTheDayZero());
		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
		PageResult<PageTjplanToHallResult> result = tjPlanWriteService.pageMyPublishPlan(1134L, pageMyTjPlanOption, dataPage);
		System.out.println(result.getPage().getDataList());
	}
	@Test
	public void pageMemberHuoyanWallet(){
		System.out.println("--------------------start---------------------");
		PageWalletOption pageWalletOption = new PageWalletOption();
		pageWalletOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageWalletOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
//		pageWalletOption.setPhone("13500001111");
//		pageWalletOption.setNickName("竞彩观察师官方帐号");
		pageWalletOption.setTjExpertLevelList(TjExpertLevel.getAllList());
//		List<Long> memberIdList = new ArrayList<Long>();
//		memberIdList.add(1146L);
//		memberIdList.add(1147L);
//		memberIdList.add(1148L);
//		pageWalletOption.setMemberIdList(memberIdList);
		DataPage<PageWalletVo> dataPage = new DataPage<PageWalletVo>();
		PageWalletResult<PageWalletVo> pageResult = memberHuoyanWalletWriteService.pageMemberHuoyanWallet(pageWalletOption, dataPage);
		System.out.println("--------------------end---------------------"+pageResult.getPage().getDataList());
	}
		
	@Test
	public void adminPageMemberHuoyanWalletLog(){
		System.out.println("--------------------start---------------------");
		PageWalletLogOption pageWalletLogOption = new PageWalletLogOption();
		pageWalletLogOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageWalletLogOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		//pageWalletLogOption.setPhone("15976878384");
		//pageWalletLogOption.setNickName("官方帐号");
		pageWalletLogOption.setTjExpertLevelList(TjExpertLevel.getAllList());
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1146L);
		memberIdList.add(1147L);
		memberIdList.add(1148L);
		//pageWalletLogOption.setMemberIdList(memberIdList);
		pageWalletLogOption.setBizTypeList(BizType.getAllList());
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		pageWalletLogOption.setStartDate(DateUtil.getSomeDayFirstTime(-30));
		DataPage<PageWalletLogVo> dataPage = new DataPage<PageWalletLogVo>();
		PageResult<PageWalletLogVo> pageResult = memberHuoyanWalletLogWriteService.adminPageMemberHuoyanWalletLog(pageWalletLogOption, dataPage);
		System.out.println("--------------------end---------------------"+pageResult.getPage().getDataList());
	}
	
	@Test
	public void adminSumMemberHuoyanWalletLog() {
		System.out.println("--------------------start---------------------");
		PageWalletLogOption pageWalletLogOption = new PageWalletLogOption();
		pageWalletLogOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageWalletLogOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		pageWalletLogOption.setTjExpertLevelList(TjExpertLevel.getAllList());
		pageWalletLogOption.setBizTypeList(BizType.getAllList());
		pageWalletLogOption.setStartDate(DateUtil.getSomeDayFirstTime(-30));
		ModelResult<PageWalletLogVo> result = memberHuoyanWalletLogWriteService
				.adminSumMemberHuoyanWalletLog(pageWalletLogOption);
		System.out.println("--------------------end---------------------" + result.getModel().getTotalIncome());
	}
	
	@Test
	public void pageMemberWallet(){
		System.out.println("--------------------start---------------------");
		PageWalletOption pageWalletOption = new PageWalletOption();
		pageWalletOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageWalletOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		pageWalletOption.setPhone("15976878384");
		pageWalletOption.setNickName("官方帐号");
		pageWalletOption.setTjExpertLevelList(TjExpertLevel.getAllList());
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1146L);
		memberIdList.add(1147L);
		memberIdList.add(1148L);
		pageWalletOption.setMemberIdList(memberIdList);
		DataPage<PageWalletVo> dataPage = new DataPage<PageWalletVo>();
		PageResult<PageWalletVo> pageResult = memberWalletWriteService.pageMemberWallet(pageWalletOption, dataPage);
		System.out.println("--------------------end---------------------"+pageResult.getPage().getDataList());
	}
	
	@Test
	public void adminPageMemberWalletLog(){
		System.out.println("--------------------start---------------------");
		PageWalletLogOption pageWalletLogOption = new PageWalletLogOption();
		pageWalletLogOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageWalletLogOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		//pageWalletLogOption.setPhone("15976878384");
		//pageWalletLogOption.setNickName("官方帐号");
		pageWalletLogOption.setTjExpertLevelList(TjExpertLevel.getAllList());
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1146L);
		memberIdList.add(1147L);
		memberIdList.add(1148L);
		//pageWalletLogOption.setMemberIdList(memberIdList);
		pageWalletLogOption.setBizTypeList(BizType.getAllList());
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		pageWalletLogOption.setStartDate(startDate);
		DataPage<PageWalletLogVo> dataPage = new DataPage<PageWalletLogVo>();
		PageResult<PageWalletLogVo> pageResult = memberWalletLogWriteService.adminPageMemberWalletLog(pageWalletLogOption, dataPage);
		System.out.println("--------------------end---------------------"+pageResult.getPage().getDataList());
	}
	@Test
	public void updateHuoyanWalletVerifyMd5(){
		System.out.println("--------------------start---------------------");
		PageWalletOption pageWalletOption = new PageWalletOption();
//		pageWalletOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
//		pageWalletOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
//		pageWalletOption.setPhone("13500001111");
//		pageWalletOption.setNickName("竞彩观察师官方帐号");
		pageWalletOption.setTjExpertLevelList(TjExpertLevel.getAllList());
//		List<Long> memberIdList = new ArrayList<Long>();
//		memberIdList.add(1146L);
//		memberIdList.add(1147L);
//		memberIdList.add(1148L);
//		pageWalletOption.setMemberIdList(memberIdList);
		DataPage<PageWalletVo> dataPage = new DataPage<PageWalletVo>();
		dataPage.setPageSize(1000);
		PageWalletResult<PageWalletVo> pageResult = memberHuoyanWalletWriteService.pageMemberHuoyanWallet(pageWalletOption, dataPage);
		for (PageWalletVo pageWalletVo : pageResult.getPage().getDataList()) {
			MemberHuoyanWallet wallet = memberHuoyanWalletWriteService.queryMemberHuoyanWalletByMemberId(pageWalletVo.getMemberId()).getModel();
			memberHuoyanWalletManager.updateMemberHuoyanWalletEnd(wallet);
		}
		
	}
	@Test
	public void updateWalletVerifyMd5(){
		System.out.println("--------------------start---------------------");
		PageWalletOption pageWalletOption = new PageWalletOption();
//		pageWalletOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
//		pageWalletOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
//		pageWalletOption.setPhone("13500001111");
//		pageWalletOption.setNickName("竞彩观察师官方帐号");
		pageWalletOption.setTjExpertLevelList(TjExpertLevel.getAllList());
//		List<Long> memberIdList = new ArrayList<Long>();
//		memberIdList.add(1146L);
//		memberIdList.add(1147L);
//		memberIdList.add(1148L);
//		pageWalletOption.setMemberIdList(memberIdList);
		DataPage<PageWalletVo> dataPage = new DataPage<PageWalletVo>();
		dataPage.setPageSize(1000);
		PageResult<PageWalletVo> pageResult = memberWalletWriteService.pageMemberWallet(pageWalletOption, dataPage);
		for (PageWalletVo pageWalletVo : pageResult.getPage().getDataList()) {
			MemberWallet wallet = memberWalletWriteService.queryMemberWalletByMemberId(pageWalletVo.getMemberId()).getModel();
			memberWalletManager.updateMemberWalletEnd(wallet);
		}
		
	}
	@Test
	public void sumMemberProfit(){
		System.out.println("--------------------start---------------------");
		Calendar startDate = DateUtil.getTheDayZero();
		Calendar endDate = Calendar.getInstance();
		BigDecimal sumProfit = memberWalletLogManager.sumMemberProfit(1261L, startDate, endDate);
		System.out.println("-------------end--------：" + sumProfit.toString());
	}
	
	
	
	
}
 