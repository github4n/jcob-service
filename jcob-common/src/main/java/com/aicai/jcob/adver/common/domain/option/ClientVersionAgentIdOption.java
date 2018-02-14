package com.aicai.jcob.adver.common.domain.option;

import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/1 0001.
 */
public class ClientVersionAgentIdOption implements  java.io.Serializable {

    private static final long serialVersionUID = -9167234428307080854L;

    public ClientVersionAgentIdOption() {

    }

    public ClientVersionAgentIdOption(Integer businessType, Integer clientType, String version, Integer businessId, Integer agentId) {
        this.businessType = businessType;
        this.clientType = clientType;
        this.clientVersion = version;
        this.businessId = businessId;
        this.agentId = agentId;
    }

    private Integer clientVersionAgentId;

    private Integer clientType ;

    private String clientVersion ;

    private Integer businessId ;

    private Integer agentId;

    private Integer businessType;

    private List<Integer> businessIds ;

    public List<Integer> getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(List<Integer> businessIds) {
        this.businessIds = businessIds;
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

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getClientVersionAgentId() {
        return clientVersionAgentId;
    }

    public void setClientVersionAgentId(Integer clientVersionAgentId) {
        this.clientVersionAgentId = clientVersionAgentId;
    }
}
