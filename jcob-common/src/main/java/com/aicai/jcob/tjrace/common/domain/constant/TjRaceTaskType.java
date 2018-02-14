package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

public class TjRaceTaskType extends BaseType {
	private static final long serialVersionUID = 6951899805071962710L;

	public TjRaceTaskType(Integer status, String description) {
		super(status, description);
	}

	public static TjRaceTaskType race_matched = new TjRaceTaskType(0, "完场任务");
	public static TjRaceTaskType race_start = new TjRaceTaskType(1, "开赛任务");
	public static TjRaceTaskType race_draw = new TjRaceTaskType(2, "开奖任务");
	public static TjRaceTaskType race_cancel = new TjRaceTaskType(3, "取消赛事任务");

	public static List<TjRaceTaskType> getAllList() {
		return getAll(TjRaceTaskType.class);
	}

	public static TjRaceTaskType valueOf(Integer index) {
		return valueOf(TjRaceTaskType.class, index);
	}
}
