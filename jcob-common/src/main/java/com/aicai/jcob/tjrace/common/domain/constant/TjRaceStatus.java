package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

/**
 * 赛事状态
 * 
 */
public class TjRaceStatus extends BaseType {

	private static final long serialVersionUID = 2173696546704524389L;

	public TjRaceStatus(Integer status, String description) {
		super(status, description);
	}

	public static TjRaceStatus not_match = new TjRaceStatus(0, "未开赛");
	public static TjRaceStatus matching = new TjRaceStatus(1, "比赛中");
	public static TjRaceStatus matched = new TjRaceStatus(2, "已完场");
	public static TjRaceStatus cancel = new TjRaceStatus(3, "已取消");
	public static TjRaceStatus no_draw = new TjRaceStatus(4, "未开奖");// 有了赛果之后变为未开奖
	public static TjRaceStatus drawing = new TjRaceStatus(5, "开奖中");
	public static TjRaceStatus draw = new TjRaceStatus(6, "已开奖");

	public static TjRaceStatus match_stop = new TjRaceStatus(7, "已停赛");//人工干预的停赛
	public static TjRaceStatus match_delay = new TjRaceStatus(8, "已延期");
	public static List<TjRaceStatus> getAllList() {
		return getAll(TjRaceStatus.class);
	}

	public static TjRaceStatus valueOf(Integer index) {
		return valueOf(TjRaceStatus.class, index);
	}

}
