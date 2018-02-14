package com.aicai.jcob.tjrace.service.impl;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.service.TjRaceTaskWriteService;
import com.aicai.jcob.tjrace.manager.TjRaceTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class TjRaceTaskWriteServiceImpl implements TjRaceTaskWriteService {
	@Autowired
	@Qualifier("tjRaceTaskManagerImpl")
	private TjRaceTaskManager tjRaceTaskManager;

	@Override
	public ModelResult<TjRaceTask> queryRaceTaskById(Long id) {
		return new ModelResult<TjRaceTask>(tjRaceTaskManager.queryRaceTaskById(id));
	}

	@Override
	public ModelResult<TjRaceTask> createRaceTask(TjRaceTask tjRaceTask) {
		return new ModelResult<TjRaceTask>(tjRaceTaskManager.createRaceTask(tjRaceTask));
	}

	@Override
	public ModelResult<Boolean> updateRaceTaskStatus(Long id, Integer oldStatus, Integer newStatus) {
		return new ModelResult<Boolean>(tjRaceTaskManager.updateRaceTaskStatus(id, oldStatus, newStatus));
	}

	@Override
	public ModelResult<List<TjRaceTask>> queryAvailableTask() {
		return new ModelResult<List<TjRaceTask>>(tjRaceTaskManager.queryAvailableTask());
	}

	@Override
	public ModelResult<Boolean> isRaceTaskExist(Long raceId, Integer taskType) {
		return new ModelResult<Boolean>(tjRaceTaskManager.isRaceTaskExist(raceId, taskType));
	}

}
