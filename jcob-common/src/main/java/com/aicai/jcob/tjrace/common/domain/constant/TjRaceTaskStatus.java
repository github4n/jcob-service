package com.aicai.jcob.tjrace.common.domain.constant;

import java.util.List;

import com.aicai.jcob.common.constant.BaseType;

public class TjRaceTaskStatus extends BaseType {
    private static final long serialVersionUID = 2173696546704524389L;

    public TjRaceTaskStatus(Integer status, String description) {
        super(status, description);
    }

    public static TjRaceTaskStatus initialize = new TjRaceTaskStatus(0, "初始化");
    public static TjRaceTaskStatus running = new TjRaceTaskStatus(1, "执行中");
    public static TjRaceTaskStatus finish = new TjRaceTaskStatus(2, "执行完毕");
    public static TjRaceTaskStatus unnormal = new TjRaceTaskStatus(3, "执行异常");

    public static List<TjRaceTaskStatus> getAllList() {
        return getAll(TjRaceTaskStatus.class);
    }

    public static TjRaceTaskStatus valueOf(Integer index) {
        return valueOf(TjRaceTaskStatus.class, index);
    }
}
