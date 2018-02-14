package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/27 0027.
 */
public class ConditionType extends BaseType {
    public ConditionType(Integer index, String description) {
        super(index, description);
    }

    public static final ConditionType ALL = new ConditionType(1, "全选");

    public static final ConditionType zhengxuan = new ConditionType(2, "正选");

    public static final ConditionType fanxuan = new ConditionType(3, "反选");

    public static List<ConditionType> getAllList() {
        return getAll(ConditionType.class);
    }

    public static ConditionType valueOf(Integer index) {
        return valueOf(ConditionType.class, index);
    }

}
