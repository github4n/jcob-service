package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/26 0026.
 */
public class IsAnd extends BaseType {
    protected IsAnd(Integer index, String description) {
        super(index, description);
    }

    public static final IsAnd and = new IsAnd(1, "与");

    public static final IsAnd or = new IsAnd(2, "或");

    public static List<IsAnd> getAllList() {
        return getAll(IsAnd.class);
    }

    public static IsAnd valueOf(Integer index) {
        return valueOf(IsAnd.class, index);
    }
}
