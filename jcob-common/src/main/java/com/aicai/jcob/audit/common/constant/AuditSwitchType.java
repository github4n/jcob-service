package com.aicai.jcob.audit.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public class AuditSwitchType extends BaseType {
    public AuditSwitchType(Integer index, String description) {
        super(index, description);
    }

    public static final AuditSwitchType buyHuoYan = new AuditSwitchType(1, "购买火眼审核");

    public static final AuditSwitchType tikuan = new AuditSwitchType(2, "提款审核");

    public static final AuditSwitchType guanggaotu = new AuditSwitchType(3, "广告图审核");

    public static final AuditSwitchType zhifu = new AuditSwitchType(4, "支付审核");

    public static final AuditSwitchType iosUpgrade = new AuditSwitchType(5, "检测新版本");


    public static List<AuditSwitchType> getAllList() {
        return getAll(AuditSwitchType.class);
    }

    public static AuditSwitchType valueOf(Integer index) {
        return valueOf(AuditSwitchType.class, index);
    }



}
