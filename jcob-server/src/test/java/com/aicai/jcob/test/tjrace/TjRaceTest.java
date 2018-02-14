package com.aicai.jcob.test.tjrace;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.common.utils.RaceUtil;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.option.TjRaceSearchOption;
import com.aicai.jcob.tjrace.common.service.TjRaceWriteService;
import com.aicai.jcob.tjrace.manager.TjRaceManager;

public class TjRaceTest extends TestBase {
	@Autowired
	@Qualifier("tjRaceManagerImpl")
	private TjRaceManager tjRaceManager;

	@Autowired
	@Qualifier("tjRaceWriteServiceImpl")
	private TjRaceWriteService tjRaceWriteService;

	@Test
	public void createRace() {
		TjRace race = new TjRace();
		Date publishTime = DateUtil.getDate2("2015-12-14 00:00:00");
		race.setStatus(0);
		race.setMatchNo(1);
		race.setRaceType(1);
		race.setPublishTime(publishTime);
		race.setUniqueMatchNo(RaceUtil.buildUniqueMatchNo(race));
		race.setMatchName("亚青U23");
		race.setHomeTeam("卡塔尔U23");
		race.setGuestTeam("伊拉克U23");
		race.setHandicap("test");
		race.setSp("test");
		race.setMatchShortName("亚青");
		race.setHomeTeamShortName("伊拉克");
		race.setGuestTeamShortName("伊拉克");
		race.setWeekOfYear(RaceUtil.getWeekOfYear(publishTime));
		race.setIsMiddle(0);
		race.setIsOwner(0);
		tjRaceManager.createRace(race);
		System.out.println(race.getId());
	}

	@Test
	public void queryRaceById() {
		System.out.println(tjRaceManager.queryRaceById(1L).toString());
	}

	@Test
	public void updateRaceStatus() {
		tjRaceWriteService.updateRaceStatus(71L, TjRaceStatus.matching.getIndex(), TjRaceStatus.matched.getIndex());
	}

	@Test
	public void queryRaceByMatchId() {
		System.out.println(tjRaceWriteService.queryRaceByMatchId("77776").getModel().toString());
	}

	@Test
	public void updateHandicapAndSp() {
		tjRaceWriteService.updateHandicapByMatchId("76790", "0;-2;半球;两球/两球半", "_;_;8800,10000;9300,9300");
	}

	@Test
	public void queryRaceByPage() {
		TjRaceSearchOption option = new TjRaceSearchOption();
		option.setFxIdExist(false);
		long totalPage = 0;
		int pageNo = 1;
		DataPage<TjRace> dataPage = new DataPage<TjRace>(pageNo, 100);
		do {
			System.out.println(dataPage.getPageNo());
			PageResult<TjRace> model = tjRaceWriteService.queryRaceByPage(option, dataPage);
			dataPage = model.getPage();
			totalPage = dataPage.getTotalPages();
			List<TjRace> list = dataPage.getDataList();
			if (CollectionUtils.isEmpty(list)) {
				break;
			}
			for (TjRace item : list) {
				System.out.println("fxId:" + item.getFxId() + ";handicap:" + item.getHandicap());
			}
			pageNo++;
			dataPage.setPageNo(pageNo);
		} while (totalPage >= pageNo);

	}

	@Test
	public void updateLeagueType() {
		DataPage<TjRace> dataPage = new DataPage<TjRace>(Integer.MAX_VALUE);
		TjRaceSearchOption option = new TjRaceSearchOption();
		PageResult<TjRace> pageResult = tjRaceWriteService.queryRaceByPage(option, dataPage);
		dataPage = pageResult.getPage();
		List<TjRace> list = dataPage.getDataList();
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (TjRace item : list) {
			item.setLeagueType(RaceUtil.getLeagueTypeIndex(item.getMatchName()));
			tjRaceWriteService.updateRace(item);
		}
	}
}
