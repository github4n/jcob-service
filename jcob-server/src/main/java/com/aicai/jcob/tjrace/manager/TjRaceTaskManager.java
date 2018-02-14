package com.aicai.jcob.tjrace.manager;

import java.util.Date;
import java.util.List;

import com.aicai.jcob.tjrace.common.domain.TjRaceTask;

public interface TjRaceTaskManager {

	public TjRaceTask queryRaceTaskById(Long id);

	public TjRaceTask createRaceTask(TjRaceTask tjRaceTask);

	public TjRaceTask createRaceStartTask(Long raceId, Date executeTime);

	public TjRaceTask createRaceDrawTask(Long raceId);

	public TjRaceTask createRaceCancelTask(Long raceId);

	public boolean cancelRaceTaskByRaceId(Long raceId, Integer taskType);

	public boolean isRaceTaskExist(Long raceId, Integer taskType);

	public boolean updateRaceTaskStatus(Long id, Integer oldStatus, Integer newStatus);

	public boolean updateFeatures(Long id, String features, long oldFeaturesVersion);

	public List<TjRaceTask> queryAvailableTask();
}
