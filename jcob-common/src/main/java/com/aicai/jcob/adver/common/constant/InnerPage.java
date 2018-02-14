package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/18 0018.
 */
public class InnerPage extends BaseType {

    private static final long serialVersionUID = 1127504945417794455L;

    protected InnerPage(Integer index, String description) {
        super(index, description);
    }

    public static final InnerPage shouye = new InnerPage(1,"推荐-首页");
    public static final InnerPage yapan = new InnerPage(2,"推荐-亚盘");
    public static final InnerPage daxiaoqiu = new InnerPage(3, "推荐-大小球");
    public static final InnerPage danguan = new InnerPage(4, "推荐-单关");
    public static final InnerPage bifen = new InnerPage(5, "比分");
    public static final InnerPage qbtj = new InnerPage(6, "实验室推荐-全部推荐");
    public static final InnerPage mzlph = new InnerPage(7, "实验室推荐-命中率排行");
    public static final InnerPage syzjy = new InnerPage(8, "所有专家页");


    public static List<InnerPage> getAllList() {
        return getAll(InnerPage.class);
    }

    public static InnerPage valueOf(Integer index) {
        return valueOf(InnerPage.class, index);
    }
}
