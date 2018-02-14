package com.aicai.jcob.tjrace.common.service;

import java.util.List;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;

public interface TjRaceTaskWriteService {
	/**
	 * 根据ID查询赛事任务
	 * 
	 * @param id
	 * @return
	 */
	public ModelResult<TjRaceTask> queryRaceTaskById(Long id);

	/**
	 * 创建任务
	 * 
	 * @param tjRaceTask
	 * @return
	 */
	public ModelResult<TjRaceTask> createRaceTask(TjRaceTask tjRaceTask);

	/**
	 * 更新状态
	 * 
	 * @param tjRaceTask
	 * @return
	 */
	public ModelResult<Boolean> updateRaceTaskStatus(Long id, Integer oldStatus, Integer newStatus);

	/**
	 * 判断任务是否存在
	 * 
	 */
	public ModelResult<Boolean> isRaceTaskExist(Long raceId, Integer taskType);

	/**
	 * 查询所有未执行的任务,排除已取消的，已过期的，已执行过的
	 * 
	 * @param option
	 * @return
	 */
	public ModelResult<List<TjRaceTask>> queryAvailableTask();
}
