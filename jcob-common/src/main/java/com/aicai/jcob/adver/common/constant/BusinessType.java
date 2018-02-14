package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

/**
 * Created by tianlun.wu on 2016/2/29 0029.
 */
public class BusinessType extends BaseType {

    private static final long serialVersionUID = 4621387079114455967L;

    public BusinessType(Integer index, String description) {
        super(index, description);
    }

    public static final BusinessType template = new BusinessType(1, "模板");

    public static final BusinessType adverLocation = new BusinessType(2, "广告位");

    public static final BusinessType audit = new BusinessType(3, "审核开关");


}
