package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/18 0018.
 */
public class RedirectType extends BaseType {
    private static final long serialVersionUID = 3956732897177209827L;

    public RedirectType(Integer index, String description) {
        super(index, description);
    }


    public static final RedirectType inner = new RedirectType(1, "跳内页");
    public static final RedirectType responseUrl = new RedirectType(2, "跳响应地址");
    public static final RedirectType responseRecommed = new RedirectType(3, "跳响应推荐");
    public static final RedirectType responseExpert = new RedirectType(4, "跳响应专家");
    public static final RedirectType responseMatch = new RedirectType(5, "跳响应赛事");


    public static List<RedirectType> getAllList() {
        return getAll(RedirectType.class);
    }

    public static RedirectType valueOf(Integer index) {
        return valueOf(RedirectType.class, index);
    }

}
