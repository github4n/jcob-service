package com.aicai.jcob.common.constant;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/15 0015.
 */
public class PageType extends BaseType {

    private static final long serialVersionUID = 9016927521090612647L;

    public PageType(Integer index, String description) {
        super(index, description);
    }

    public static final PageType firstPage = new PageType(1, "首页");

    public static final PageType startPage = new PageType(2, "启动页");


    public static List<PageType> getAllList() {
        return getAll(PageType.class);
    }

    public static PageType valueOf(Integer index) {
        return valueOf(PageType.class, index);
    }
}
