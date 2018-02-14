package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/16 0016.
 */
public class UsingStatus extends BaseType {

    private static final long serialVersionUID = -7127103428981364392L;

    public UsingStatus(Integer index, String description) {
        super(index, description);
    }



    public static final UsingStatus using = new UsingStatus(1, "使用");
    public static final UsingStatus stopping = new UsingStatus(2, "暂停");



    public static List<UsingStatus> getAllList() {
        return getAll(UsingStatus.class);
    }

    public static UsingStatus valueOf(Integer index) {
        return valueOf(UsingStatus.class, index);
    }

}
