package com.aicai.jcob.test.tjplan;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.game.common.domain.CheckPlanEffectContext;
import com.aicai.jcob.game.common.domain.constant.GameType;
import com.aicai.jcob.game.manager.impl.CheckPlanEffectManagerImpl;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletLogWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberHuoyanWalletWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberWalletLogWriteService;
import com.aicai.jcob.memberwallet.common.service.MemberWalletWriteService;
import com.aicai.jcob.memberwallet.manager.MemberWalletLogManager;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjexpert.common.domain.constant.TjExpertLevel;
import com.aicai.jcob.tjplan.common.domain.TjLookLog;
import com.aicai.jcob.tjplan.common.domain.TjPlan;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminLookLogOption;
import com.aicai.jcob.tjplan.common.domain.option.PageAdminPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageLookedTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyAttentionExpertTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageMyTjPlanOption;
import com.aicai.jcob.tjplan.common.domain.option.PageTjPlanToHallOption;
import com.aicai.jcob.tjplan.common.domain.result.AdminLookPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.LookerVo;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminLookLogVo;
import com.aicai.jcob.tjplan.common.domain.result.PageAdminPlanVo;
import com.aicai.jcob.tjplan.common.domain.result.PageMyLookedTjplanResult;
import com.aicai.jcob.tjplan.common.domain.result.PageTjplanToHallResult;
import com.aicai.jcob.tjplan.common.domain.result.TjPlanVo;
import com.aicai.jcob.tjplan.common.service.TjPlanWriteService;
import com.aicai.jcob.tjplan.manager.TjPlanManager;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceLeagueType;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceType;
import com.aicai.jcob.tjrace.common.domain.result.RaceScoreResult;
import com.alibaba.fastjson.JSON;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月24日 上午10:32:23 
 * 程序的简单说明 
 */
public class TjPlanTest extends TestBase{
	@Autowired
	@Qualifier("tjPlanWriteServiceImpl")
	private TjPlanWriteService tjPlanWriteService;
	@Autowired
	private MemberWalletLogWriteService memberWalletLogWriteService;
	@Autowired
	private MemberWalletWriteService memberWalletWriteService;
	@Autowired
	private MemberWalletLogManager memberWalletLogManager;
	@Autowired
	private TjPlanManager tjPlanManager;
	@Autowired
	private MemberHuoyanWalletWriteService memberHuoyanWalletWriteService;
	@Autowired
	private MemberHuoyanWalletLogWriteService memberHuoyanWalletLogWriteService;
	@Autowired
	private CheckPlanEffectManagerImpl checkPlanEffect;

	@Test
	public void queryIdListByMaxRaceId() {
		DataPage<Long> dataPage = new DataPage<Long>();
		tjPlanWriteService.queryIdListByMaxRaceId(10L, dataPage);
		long total = dataPage.getTotalPages();
		System.out.println(total);
	}

	@Test
	public void checkPlanEffect() {
		List<TjPlan> list = new ArrayList<TjPlan>();
		list.add(tjPlanWriteService.queryTjPlanById(32L).getModel());
		List<RaceScoreResult> raceScoreList = new ArrayList<RaceScoreResult>();
		RaceScoreResult raceScoreResult = new RaceScoreResult();
		raceScoreResult.setUniqueMatchNo("160216010");
		raceScoreResult.setWholeScore("2:0");
		raceScoreList.add(raceScoreResult);

		CheckPlanEffectContext checkPlanEffectContext = new CheckPlanEffectContext();
		checkPlanEffectContext.setTaskId(24l);
		checkPlanEffectContext.setTotalNum(1);
		checkPlanEffect.checkPlanEffect(list, raceScoreList, checkPlanEffectContext);
	}
	
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
		pageTjPlanToHallOption.setTjRaceStatus(TjRaceStatus.matched);
		PageResult<PageTjplanToHallResult> pageResult = tjPlanWriteService.pageTjPlanToHall(pageTjPlanToHallOption, dataPage);
System.out.println("++++++++++++"+pageResult.getPage().getDataList());
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
		Calendar startDate = DateUtil.getTheDayZero();
		startDate.add(Calendar.DATE, -2);
		pageMyTjPlanOption.setStartDate(startDate);
		DataPage<TjPlan> dataPage = new DataPage<TjPlan>();
		System.out.println(JSON.toJSONString(pageMyTjPlanOption));
		System.out.println(JSON.toJSONString(dataPage));
//		PageResult<PageTjplanToHallResult> result = tjPlanWriteService.pageMyPublishPlan(1134L, pageMyTjPlanOption, dataPage);
//		System.out.println(result.getPage().getDataList());
	}
	@Test
	public void adminPageLookLog(){
		System.out.println("------------------------------");
		PageAdminLookLogOption pageAdminLookLogOption = new PageAdminLookLogOption();
		
		pageAdminLookLogOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageAdminLookLogOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		pageAdminLookLogOption.setExpertLevelList(TjExpertLevel.getAllList());
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		pageAdminLookLogOption.setStartDate(startDate);
		pageAdminLookLogOption.setClientTypeList(ClientType.getAllList());
		pageAdminLookLogOption.setGameTypeList(GameType.getAllList());
		pageAdminLookLogOption.setRaceStatuList(TjRaceStatus.getAllList());
		pageAdminLookLogOption.setRaceTypeList(TjRaceType.getAllList());
		DataPage<PageAdminLookLogVo> dataPage = new DataPage<PageAdminLookLogVo>();
		PageResult<PageAdminLookLogVo> result = tjPlanWriteService.adminPageLookLog(pageAdminLookLogOption, dataPage);
		System.out.println(result.getPage().getDataList());
		
		System.out.println(tjPlanWriteService.adminSumPageLookLogAmount(pageAdminLookLogOption).getModel());
	}
	@Test
	public void adminPagePlan(){
		System.out.println("------------------------------");
		PageAdminPlanOption pageAdminLookLogOption = new PageAdminPlanOption();
		
		pageAdminLookLogOption.setLeftAndEqualAmount(BigDecimal.valueOf(0));
		pageAdminLookLogOption.setRightAndEqualAmount(BigDecimal.valueOf(500));
		pageAdminLookLogOption.setExpertLevelList(TjExpertLevel.getAllList());
		List<Long> memberIdList = new ArrayList<Long>();
		memberIdList.add(1146L);
		memberIdList.add(1147L);
		memberIdList.add(1148L);
		memberIdList.add(1134L);
		memberIdList.add(1135L);
		pageAdminLookLogOption.setMemberIdList(memberIdList);
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.DATE, -30);
		pageAdminLookLogOption.setStartDate(startDate);
		pageAdminLookLogOption.setClientTypeList(ClientType.getAllList());
		pageAdminLookLogOption.setGameTypeList(GameType.getAllList());
		pageAdminLookLogOption.setRaceStatuList(TjRaceStatus.getAllList());
		pageAdminLookLogOption.setRaceTypeList(TjRaceType.getAllList());
		pageAdminLookLogOption.setWinstatus(0);
		pageAdminLookLogOption.setTjPlanNo("2016022420");
		DataPage<PageAdminPlanVo> dataPage = new DataPage<PageAdminPlanVo>();
		PageResult<PageAdminPlanVo> result = tjPlanWriteService.adminPagePlan(pageAdminLookLogOption, dataPage);
		System.out.println(result.getPage().getDataList());
	}
	@Test
	public void adminLookPlanDetail(){
		System.out.println("--------------start----------------");
		ModelResult<AdminLookPlanVo> modResult = tjPlanWriteService.adminLookPlanDetail(127L);
		System.out.println("--------------end  ----------------");
	}
	@Test
	public void queryTjplanToIndexAutoRecommend(){
		System.out.println("--------------start----------------");
		ModelResult<List<TjPlanVo>> modelResult = tjPlanWriteService.queryTjplanToIndexAutoRecommend(2, 3);
		System.out.println("--------------end  ----------------" + modelResult.getModel());
	}
	@Test
	public void pagePlanLooker(){
		System.out.println("--------------start----------------");
		PageResult<LookerVo> pageResult = tjPlanWriteService.pagePlanLooker(21L, new DataPage<TjLookLog>());
		System.out.println("--------------end  ----------------" + pageResult.getPage().getDataList());
	}
	@Test
	public void updateNoShowPlanByMemberId(){
		tjPlanManager.updateTjPlanNoShowByMemberId(1223L, 1, 0);
	}
}
 