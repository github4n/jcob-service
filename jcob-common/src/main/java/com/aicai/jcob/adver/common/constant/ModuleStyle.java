package com.aicai.jcob.adver.common.constant;

import com.aicai.jcob.common.constant.BaseType;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/17 0017.
 */
public class ModuleStyle extends BaseType{
    private static final long serialVersionUID = 5426852500701504343L;

    private String imgUrl ;

    public ModuleStyle(Integer index, String description) {
        super(index, description);
    }

    public ModuleStyle(Integer index, String description, String outDescription){
        super(index, description, outDescription);
    }


    public static final ModuleStyle title = new ModuleStyle(1,"推荐位1","推荐位1（标题）");

    public static final ModuleStyle tonglan = new ModuleStyle(2,"推荐位1","推荐位1（通栏）");

    public static final ModuleStyle shuanglan = new ModuleStyle(3,"推荐位2","推荐位2（双栏）");

    public static final ModuleStyle tuijiandan = new ModuleStyle(4,"推荐位5","推荐位5（推荐单）");

    public static final ModuleStyle zhuangjia = new ModuleStyle(5,"推荐位10","推荐位10（专家）");
    static {
        title.setImgUrl("/resources/images/title.png");
        tonglan.setImgUrl("/resources/images/tonglan.png");
        shuanglan.setImgUrl("/resources/images/shuanglan.png");
        tuijiandan.setImgUrl("/resources/images/tuijiandan.png");
        zhuangjia.setImgUrl("/resources/images/zhuanjia.png");
    }

    public static List<ModuleStyle> getAllList() {
        return getAll(ModuleStyle.class);
    }

    public static ModuleStyle valueOf(Integer index) {
        return valueOf(ModuleStyle.class, index);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
