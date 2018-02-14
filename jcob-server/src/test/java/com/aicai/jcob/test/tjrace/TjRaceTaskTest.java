package com.aicai.jcob.test.tjrace;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.test.TestBase;
import com.aicai.jcob.tjrace.common.domain.TjRaceTask;
import com.aicai.jcob.tjrace.common.domain.constant.TjRaceTaskType;
import com.aicai.jcob.tjrace.manager.TjRaceTaskManager;

public class TjRaceTaskTest extends TestBase {
    @Autowired
    @Qualifier("tjRaceTaskManagerImpl")
    private TjRaceTaskManager tjRaceTaskManager;

    @Test
    public void createRaceTask() {
        TjRaceTask task = new TjRaceTask();
        Date date = DateUtil.getDate2("2015-12-14 00:00:00");
        task.setExcuteMillisecond(1000L);
        task.setTaskType(TjRaceTaskType.race_start.getIndex());
        task.setTaskName(TjRaceTaskType.race_start.getDescription());
        task.setExcuteTime(date);
        task.setStatus(1);
        task.setRaceId(1L);
        tjRaceTaskManager.createRaceTask(task);
    }

}
