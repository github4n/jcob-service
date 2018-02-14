package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

public class TjRaceLeagueType extends BaseType {
    private static final long serialVersionUID = 6951899805071962710L;

    public TjRaceLeagueType(Integer status, String description) {
        super(status, description);
    }
    public static TjRaceLeagueType og = new TjRaceLeagueType(1, "欧冠");
    public static TjRaceLeagueType yc = new TjRaceLeagueType(2, "英超");
    public static TjRaceLeagueType xj = new TjRaceLeagueType(3, "西甲");
    public static TjRaceLeagueType yj = new TjRaceLeagueType(4, "意甲");
    public static TjRaceLeagueType dj = new TjRaceLeagueType(5, "德甲");
    public static TjRaceLeagueType fj = new TjRaceLeagueType(6, "法甲");
    public static TjRaceLeagueType hj = new TjRaceLeagueType(7, "荷甲");
    public static TjRaceLeagueType pc = new TjRaceLeagueType(8, "葡超");

    public static List<TjRaceLeagueType> getAllList() {
        return getAll(TjRaceLeagueType.class);
    }

    public static TjRaceLeagueType valueOf(Integer index) {
        return valueOf(TjRaceLeagueType.class, index);
    }
    
	public static TjRaceLeagueType valeOfByDesc(String description) {
		try {
			List<TjRaceLeagueType> list = getAllList();
			for (TjRaceLeagueType t : list) {
				if (t.getDescription().equals(description)) {
					return t;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
    
}
