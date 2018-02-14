package com.aicai.jcob.adver.common.result;

import java.io.Serializable;

/**
 * Created by tianlun.wu on 2016/2/19 0019.
 */
public class ClientVersionAndAgentId implements Serializable {
    private static final long serialVersionUID = 1474186061189792801L;

    private Integer id ;

    private Integer clientType ;

    private String version;

    private Integer agentId ;

    private Integer isAnd ;

    public Integer getIsAnd() {
        return isAnd;
    }

    public void setIsAnd(Integer isAnd) {
        this.isAnd = isAnd;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}
