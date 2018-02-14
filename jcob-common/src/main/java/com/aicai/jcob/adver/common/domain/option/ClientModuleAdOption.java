package com.aicai.jcob.adver.common.domain.option;

import java.io.Serializable;

/**
 * Created by tianlun.wu on 2016/2/17 0017.
 */
public class ClientModuleAdOption implements Serializable{
    private static final long serialVersionUID = 5963671860605743106L;

    public ClientModuleAdOption() {
    }

    public ClientModuleAdOption(Integer moduleId, Integer status) {
        this.moduleId = moduleId;
        this.status = status;
    }

    private Integer moduleId;

    private Integer moduleStyleId;

    private Integer id ;

    /**
     * 查询的时候 是否考虑时间因素
     * 1：是
     * 2：否
     */
    private Integer needTime ;

    /**
     * @see com.aicai.jcob.adver.common.constant.UsingStatus
     */
    private Integer status ;

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getModuleStyleId() {
        return moduleStyleId;
    }

    public void setModuleStyleId(Integer moduleStyleId) {
        this.moduleStyleId = moduleStyleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
