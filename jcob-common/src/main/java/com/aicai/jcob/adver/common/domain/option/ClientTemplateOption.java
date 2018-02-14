package com.aicai.jcob.adver.common.domain.option;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/1/30 0030.
 */
public class ClientTemplateOption implements  java.io.Serializable{


    private static final long serialVersionUID = -6001613633224387402L;
    private String templateName;
    private Integer clientType ;
    private Integer status ;
    private Integer agentIdInt ;
    private String agentId ;
    private Integer pageId ;
    private String clientVersion ;
    private String updateUser;
    private List<Integer> ids;

    private List<Integer> agentIds ;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<Integer> agentIds) {
        this.agentIds = agentIds;
    }

    public Integer getAgentIdInt() {
        return agentIdInt;
    }

    public void setAgentIdInt(Integer agentIdInt) {
        this.agentIdInt = agentIdInt;
    }

    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }
}
