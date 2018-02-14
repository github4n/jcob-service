package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/3 0003.
 */
public class DataType extends BaseType {
    private static final long serialVersionUID = 4958964507722585362L;

    public DataType(Integer index, String description) {
        super(index, description);
    }

    public static final DataType person = new DataType(1,"人工推送");
    public static final DataType beforePerson = new DataType(2, "自动调用查看人数前X条未开赛的付费推荐");
    public static final DataType pushIntime = new DataType(3, "自动调用最近发布的前X条未开赛的付费推荐");

    public static List<DataType> getAllList() {
        return getAll(DataType.class);
    }

    public static DataType valueOf(Integer index) {
        return valueOf(DataType.class, index);
    }
}
