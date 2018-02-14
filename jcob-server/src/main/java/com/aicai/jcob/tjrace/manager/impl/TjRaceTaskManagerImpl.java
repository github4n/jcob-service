package com.aicai.jcob.tjrace.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.dao.GenericDao;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceTaskStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceTaskType;
import com.aicai.jcob.tjrace.manager.TjRaceTaskManager;

public class TjRaceTaskManagerImpl implements TjRaceTaskManager {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("tjplanDbDao")
	private GenericDao dao;

	@Override
	public TjRaceTask queryRaceTaskById(Long id) {
		return dao.queryUnique("TjRaceTaskMapper.queryRaceTaskById", id);
	}

	@Override
	public TjRaceTask createRaceTask(TjRaceTask tjRaceTask) {
		dao.insertAndSetupId("TjRaceTaskMapper.insert", tjRaceTask);
		return tjRaceTask;
	}

	@Override
	public TjRaceTask createRaceStartTask(Long raceId, Date executeTime) {
		TjRaceTask tjRaceTask = new TjRaceTask();
		tjRaceTask.setTaskName(TjRaceTaskType.race_start.getDescription());
		tjRaceTask.setTaskType(TjRaceTaskType.race_start.getIndex());
		tjRaceTask.setRaceId(raceId);
		tjRaceTask.setExcuteTime(executeTime);
		tjRaceTask.setExcuteMillisecond(DateUtil.getCalenderByDate(executeTime).getTimeInMillis());
		createRaceTask(tjRaceTask);
		logger.info("赛事[id={}]创建开赛任务,执行时间{}", raceId, DateUtil.getDateString2(executeTime));
		return tjRaceTask;
	}

	@Override
	public TjRaceTask createRaceDrawTask(Long raceId) {
		TjRaceTask tjRaceTask = new TjRaceTask();
		tjRaceTask.setTaskName(TjRaceTaskType.race_draw.getDescription());
		tjRaceTask.setTaskType(TjRaceTaskType.race_draw.getIndex());
		tjRaceTask.setRaceId(raceId);
		Date executeTime = new Date();
		tjRaceTask.setExcuteTime(executeTime);
		tjRaceTask.setExcuteMillisecond(DateUtil.getCalenderByDate(executeTime).getTimeInMillis());
		createRaceTask(tjRaceTask);
		logger.info("赛事[id={}]创建开奖任务,执行时间{}", raceId, DateUtil.getDateString2(executeTime));
		return tjRaceTask;
	}

	@Override
	public TjRaceTask createRaceCancelTask(Long raceId) {
		TjRaceTask tjRaceTask = new TjRaceTask();
		tjRaceTask.setTaskName(TjRaceTaskType.race_cancel.getDescription());
		tjRaceTask.setTaskType(TjRaceTaskType.race_cancel.getIndex());
		tjRaceTask.setRaceId(raceId);
		Date executeTime = new Date();
		tjRaceTask.setExcuteTime(executeTime);
		tjRaceTask.setExcuteMillisecond(DateUtil.getCalenderByDate(executeTime).getTimeInMillis());
		createRaceTask(tjRaceTask);
		logger.info("赛事[id={}]创建取消赛事任务,执行时间{}", raceId, DateUtil.getDateString2(executeTime));
		return tjRaceTask;
	}

	@Override
	public boolean updateRaceTaskStatus(Long id, Integer oldStatus, Integer newStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("oldStatus", oldStatus);
		map.put("newStatus", newStatus);
		return dao.update("TjRaceTaskMapper.updateStatus", map) > 0;
	}

	@Override
	public boolean updateFeatures(Long id, String features, long oldFeaturesVersion) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("features", features);
		map.put("oldFeaturesVersion", oldFeaturesVersion);
		return dao.update("TjRaceTaskMapper.updateFeatures", map) > 0;
	}

	@Override
	public boolean cancelRaceTaskByRaceId(Long raceId, Integer taskType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("raceId", raceId);
		map.put("taskType", taskType);
		boolean cancel = dao.update("TjRaceTaskMapper.cancelByRaceId", map) > 0;
		if (cancel) {
			logger.info("取消赛事[id={}]赛事{}", raceId, TjRaceTaskStatus.valueOf(taskType).getDescription());
		}
		return cancel;
	}

	@Override
	public List<TjRaceTask> queryAvailableTask() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curTime", DateUtil.getDate());
		return dao.queryList("TjRaceTaskMapper.queryAvailableTask", map);
	}

	@Override
	public boolean isRaceTaskExist(Long raceId, Integer taskType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("raceId", raceId);
		map.put("taskType", taskType);
		return dao.queryCount("TjRaceTaskMapper.isRaceTaskExist", map) > 0;
	}

}
