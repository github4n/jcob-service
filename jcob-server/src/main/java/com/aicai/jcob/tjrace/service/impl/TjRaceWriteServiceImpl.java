package com.aicai.jcob.tjrace.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.aicai.appmodel.domain.result.ModelResult;
import com.aicai.appmodel.domain.result.PageResult;
import com.aicai.appmodel.page.DataPage;
import com.aicai.datachange.common.constant.jcob.JcobDataType;
import com.aicai.jcob.datachange.DataChangeFactory;
import com.aicai.jcob.tjrace.common.domain.TjRace;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceStatus;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceTaskType;
import com.aicai.jcob.tjrace.common.domain.option.TjRaceSearchOption;
import com.aicai.jcob.tjrace.common.service.TjRaceWriteService;
import com.aicai.jcob.tjrace.manager.TjRaceManager;
import com.aicai.jcob.tjrace.manager.TjRaceTaskManager;
import com.alibaba.dubbo.common.utils.StringUtils;

public class TjRaceWriteServiceImpl implements TjRaceWriteService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("tjRaceManagerImpl")
	private TjRaceManager tjRaceManager;

	@Autowired
	@Qualifier("tjRaceTaskManagerImpl")
	private TjRaceTaskManager tjRaceTaskManager;

	@Autowired
	@Qualifier("transactionTemplateTjplan")
	private TransactionTemplate transactionTemplate;

	@Autowired
	private DataChangeFactory dataChangeFactory;

	@Override
	public ModelResult<TjRace> queryRaceById(Long id) {
		return new ModelResult<TjRace>(tjRaceManager.queryRaceById(id));
	}

	@Override
	public ModelResult<Boolean> isRaceExist(String matchId) {
		return new ModelResult<Boolean>(tjRaceManager.isRaceExist(matchId));
	}

	@Override
	public ModelResult<TjRace> queryRaceByMatchId(String matchId) {
		return new ModelResult<TjRace>(tjRaceManager.queryRaceByMatchId(matchId));
	}

	@Override
	public ModelResult<Boolean> deleteRaceById(Long id) {
		return new ModelResult<Boolean>(tjRaceManager.deleteRaceById(id));
	}

	@Override
	public ModelResult<Boolean> updateRace(TjRace tjRace) {
		// 如果比赛时间改变了，要重新创建开赛任务
		TjRace localRace = tjRaceManager.queryRaceById(tjRace.getId());
		boolean updateResult = tjRaceManager.updateRace(tjRace);
		if (updateResult) {
			Date matchTime = tjRace.getMatchTime();
			if (!localRace.getMatchTime().equals(matchTime)) {
				tjRaceTaskManager.cancelRaceTaskByRaceId(tjRace.getId(), TjRaceTaskType.race_start.getIndex());
				TjRaceTask tjRaceTask = tjRaceTaskManager.createRaceStartTask(tjRace.getId(), matchTime);
				sendRaceTaskDataChange(tjRaceTask);
			}
		}
		return new ModelResult<Boolean>(updateResult);
	}

	@Override
	public ModelResult<Boolean> updateRaceStatus(Long id, Integer oldStatus, Integer newStatus) {
		boolean updateResult = true;
		if (newStatus == TjRaceStatus.cancel.getIndex()) {
			updateResult = updateRaceMatchCancel(id, oldStatus);
		} else if (newStatus == TjRaceStatus.matched.getIndex()) {
			updateResult = updateRaceMatched(id, oldStatus);
		} else {
			updateResult = tjRaceManager.updateRaceStatus(id, oldStatus, newStatus);
		}
		if (updateResult) {
			logger.info("赛事[id={}]状态从[{}]更新为[{}]", id, TjRaceStatus.valueOf(oldStatus).getDescription(),
					TjRaceStatus.valueOf(newStatus).getDescription());
		} else {
			logger.error("赛事[id={}]更新状态失败", id);
		}
		return new ModelResult<Boolean>(updateResult);
	}

	/**
	 * 取消赛事:取消旧的开赛任务，修改赛事状态，admin里面处理退款和方案赛事状态
	 */
	private boolean updateRaceMatchCancel(Long raceId, Integer oldStatus) {
		boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				tjRaceTaskManager.cancelRaceTaskByRaceId(raceId, TjRaceTaskType.race_start.getIndex());
				return tjRaceManager.updateRaceStatus(raceId, oldStatus, TjRaceStatus.cancel.getIndex());
			}
		});
		return result;
	}

	/**
	 * 停赛:取消旧的开赛任务，修改赛事状态，admin里面处理方案赛事状态
	 */
	private boolean updateRaceMatched(Long raceId, Integer oldStatus) {
		boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				tjRaceTaskManager.cancelRaceTaskByRaceId(raceId, TjRaceTaskType.race_start.getIndex());
				return tjRaceManager.updateRaceStatus(raceId, oldStatus, TjRaceStatus.matched.getIndex());
			}
		});
		return result;
	}

	// 发送消息
	private void sendRaceTaskDataChange(TjRaceTask task) {
		if (task == null) {
			return;
		}
		dataChangeFactory.sendDataChangeForInsert(task.getId(), JcobDataType.jcob_tjrace_task);
		logger.info("{}[id={}]发送datachange消息", task.getTaskName(), task.getId());
	}

	@Override
	public PageResult<TjRace> queryRaceByPage(TjRaceSearchOption option, DataPage<TjRace> page) {
		PageResult<TjRace> pageResult = new PageResult<TjRace>();
		pageResult.setPage(tjRaceManager.queryRaceByPage(option, page));
		return pageResult;
	}

	@Override
	public ModelResult<TjRace> createRace(TjRace tjRace) {
		Integer status = tjRace.getStatus();
		if (status != TjRaceStatus.not_match.getIndex()) {
			tjRaceManager.createRace(tjRace);
			logger.info("创建新的赛事[{}]", tjRace.toString());
			return new ModelResult<TjRace>(tjRace);
		}
		TjRaceTask tjRaceTask = transactionTemplate.execute(new TransactionCallback<TjRaceTask>() {
			@Override
			public TjRaceTask doInTransaction(TransactionStatus status) {
				tjRaceManager.createRace(tjRace);
				logger.info("创建新的赛事[{}]", tjRace.toString());
				return tjRaceTaskManager.createRaceStartTask(tjRace.getId(), tjRace.getMatchTime());
			}
		});
		sendRaceTaskDataChange(tjRaceTask);
		return new ModelResult<TjRace>(tjRace);
	}

	@Override
	public ModelResult<Boolean> updateScoreByMatchId(String matchId, String score) {
		return new ModelResult<Boolean>(tjRaceManager.updateScoreByMatchId(matchId, score));
	}

	@Override
	public ModelResult<Boolean> updateScoreById(Long id, String score) {
		return new ModelResult<Boolean>(tjRaceManager.updateScoreById(id, score));
	}

	@Override
	public ModelResult<Boolean> updateMatchTimeByMatchId(String matchId, Date matchTime) {
		ModelResult<Boolean> model = new ModelResult<Boolean>(false);
		TjRace race = tjRaceManager.queryRaceByMatchId(matchId);
		if (race == null) {
			logger.error("根据[matchId={}]查询不到赛事", matchId);
			model.withError("根据[matchId=" + matchId + "]查询不到赛事");
			return model;
		}
		if (matchTime.equals(race.getMatchTime())) {
			logger.info("修改的时间和原赛事[matchId={}]时间一样，不需要更新", matchId);
			return model;
		}
		boolean result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				tjRaceTaskManager.cancelRaceTaskByRaceId(race.getId(), TjRaceTaskType.race_start.getIndex());
				return tjRaceManager.updateMatchTimeByMatchId(matchId, matchTime);
			}
		});
		if (result) {
			TjRaceTask task = tjRaceTaskManager.createRaceStartTask(race.getId(), matchTime);
			logger.error("更新赛事时间之后创建新的开赛任务[id={}]", task.getId());
			sendRaceTaskDataChange(task);
		}
		model.setModel(result);
		return model;
	}

	@Override
	public ModelResult<Boolean> updateHandicapByMatchId(String matchId, String handicap, String sp) {
		return new ModelResult<Boolean>(tjRaceManager.updateHandicapByMatchId(matchId, handicap, sp));
	}

	@Override
	public ModelResult<Boolean> updateFxIdByMatchId(String matchId, String fxId, String guestShortName, String homeShortName,
			String guestImgPath, String homeImgPath) {
		ModelResult<Boolean> modelResult = new ModelResult<Boolean>(false);
		if (StringUtils.isBlank(matchId)) {
			return modelResult;
		}
		if (StringUtils.isBlank(fxId) && StringUtils.isBlank(guestShortName) && StringUtils.isBlank(homeShortName)) {
			return modelResult;
		}
		return new ModelResult<Boolean>(tjRaceManager.updateFxIdByMatchId(matchId, fxId, guestShortName, homeShortName,
				guestImgPath, homeImgPath));
	}

	@Override
	public ModelResult<TjRaceTask> raceDrawById(Long raceId) {
		TjRaceTask drawTask = tjRaceTaskManager.createRaceDrawTask(raceId);
		sendRaceTaskDataChange(drawTask);
		return new ModelResult<TjRaceTask>(drawTask);
	}

	@Override
	public ModelResult<Boolean> raceCancelById(Long raceId, Integer oldStatus) {
		ModelResult<Boolean> model = new ModelResult<Boolean>(false);
		boolean result = tjRaceManager.updateRaceStatus(raceId, oldStatus, TjRaceStatus.cancel.getIndex());
		if (!result) {
			model.withError("赛事已经取消");
			return model;
		}
		TjRaceTask cancelTask = tjRaceTaskManager.createRaceCancelTask(raceId);
		sendRaceTaskDataChange(cancelTask);
		return model;
	}

	@Override
	public ModelResult<Boolean> stopRaceByMatchId(String matchId) {
		return new ModelResult<Boolean>(tjRaceManager.stopRaceByMatchId(matchId));
	}

}
