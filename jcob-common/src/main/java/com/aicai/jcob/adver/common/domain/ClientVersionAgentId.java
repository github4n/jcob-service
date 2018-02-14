package com.aicai.jcob.adver.common.domain;

import java.io.Serializable;

public class ClientVersionAgentId implements Serializable{

    private static final long serialVersionUID = 4005885074376133476L;
    private Integer id;

    //业务类型 1：为模版
    private Integer businessType;

    // 唯一值
    private Integer businessId;

    //客户端
    private Integer clientType ;

    //版本号
    private String clientVersion;

    //渠道  0 代表所有的渠道
    private Integer agentId;

    /**
     * 与或条件
     *@see com.aicai.jcob.adver.common.constant.IsAnd
     */
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

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}