package com.aicai.jcob.test.tjplan;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.appmodel.page.DataPage;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjplan.common.service.TjPlanWriteService;
import com.aicai.jcob.tjplan.manager.TjPlanItemManager;

public class TjPlanItemTest extends TestBase {
	@Autowired
	@Qualifier("tjPlanItemManagerImpl")
	private TjPlanItemManager tjPlanItemManager;

	@Autowired
	@Qualifier("tjPlanWriteServiceImpl")
	private TjPlanWriteService tjPlanWriteService;

	@Test
	public void queryPlanIdsByRaceId() {
		DataPage<Long> page = new DataPage<Long>();
		DataPage<Long> model = tjPlanItemManager.queryPlanIdsByRaceId(48L, page);
		long total = model.getTotalPages();
		if (total == 0) {
			return;
		}
		for (int i = 1; i <= total; i++) {
			page.setPageNo(i);
			model = tjPlanItemManager.queryPlanIdsByRaceId(48L, page);
		}
	}

}
