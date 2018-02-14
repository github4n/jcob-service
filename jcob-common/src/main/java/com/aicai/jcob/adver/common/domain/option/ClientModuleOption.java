package com.aicai.jcob.adver.common.domain.option;

import java.io.Serializable;

/**
 * Created by tianlun.wu on 2016/2/16 0016.
 */
public class ClientModuleOption implements Serializable{

    private static final long serialVersionUID = 6458737772100088165L;


    private Integer templateId ;

    /**
     * @see  com.aicai.jcob.adver.common.constant.UsingStatus
     */
    private Integer status;


    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
