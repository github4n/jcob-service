package com.aicai.jcob.adver.common.domain;

import java.io.Serializable;
import java.util.Calendar;

public class ClientTemplate implements Serializable {
    private static final long serialVersionUID = 5075834677775004305L;
    private Integer id;

    /**
     * @see com.aicai.jcob.common.constant.ClientType
     */
    private Integer clientType;

    /**
     * 子版本 现在保留 暂不使用
     */
    private Integer clientTypeSub;

    /**
     * 模版状态 1：启用 2：暂停
     * @see com.aicai.jcob.adver.common.constant.UsingStatus
     */
    private Integer status;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板可以显示的位置
     * @see com.aicai.jcob.common.constant.PageType
     */
    private Integer pageId;

    private String updateUser;

    private Calendar updateDate;

    private String expand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getClientTypeSub() {
        return clientTypeSub;
    }

    public void setClientTypeSub(Integer clientTypeSub) {
        this.clientTypeSub = clientTypeSub;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand == null ? null : expand.trim();
    }
}