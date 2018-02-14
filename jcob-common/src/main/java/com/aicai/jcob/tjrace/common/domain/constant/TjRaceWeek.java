package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

public class TjRaceWeek extends BaseType {

	private static final long serialVersionUID = 2019307175449821236L;

	public TjRaceWeek(Integer status, String description) {
		super(status, description);
	}

	public static TjRaceWeek MONDAY = new TjRaceWeek(0, "周一");
	public static TjRaceWeek TUESDAY = new TjRaceWeek(1, "周二");
	public static TjRaceWeek WEDNESDAY = new TjRaceWeek(2, "周三");
	public static TjRaceWeek THURSDAY = new TjRaceWeek(3, "周四");
	public static TjRaceWeek FRIDAY = new TjRaceWeek(4, "周五");
	public static TjRaceWeek SATURDAY = new TjRaceWeek(5, "周六");
	public static TjRaceWeek SUNDAY = new TjRaceWeek(6, "周日");

	public static List<TjRaceWeek> getAllList() {
		return getAll(TjRaceWeek.class);
	}

	public static TjRaceWeek valueOf(Integer index) {
		return valueOf(TjRaceWeek.class, index);
	}
}
