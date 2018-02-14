package com.aicai.jcob.adver.common.domain.option;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/4 0004.
 */
public class ClientVersionAgentIdVo implements Serializable{


    private static final long serialVersionUID = 1236774798837034944L;
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
    private Integer agentIdInt;

    private String agentId;

    private List<Integer> agentIds ;

    /**
     * 与或条件
     *@see com.aicai.jcob.adver.common.constant.IsAnd
     */
    private Integer isAnd ;


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

    public Integer getAgentIdInt() {
        return agentIdInt;
    }

    public void setAgentIdInt(Integer agentIdInt) {
        this.agentIdInt = agentIdInt;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public List<Integer> getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(List<Integer> agentIds) {
        this.agentIds = agentIds;
    }

    public Integer getIsAnd() {
        return isAnd;
    }

    public void setIsAnd(Integer isAnd) {
        this.isAnd = isAnd;
    }
}
