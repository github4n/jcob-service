package com.aicai.jcob.audit.common.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public class AuditSwitch implements Serializable {
    private static final long serialVersionUID = -6617456709298183554L;

    private Integer id ;

    /**
     * 审核开关类型
     * @see com.aicai.jcob.audit.common.constant.AuditSwitchType
     */
    private Integer type ;


    private Integer clientType;

    private Integer version ;

    private Integer agentId ;

    private Calendar createTime ;

    private String operator ;

    private Calendar updateTime ;

    /**
     * 状态
     * @see com.aicai.jcob.adver.common.constant.UsingStatus
     */
    private Integer status ;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
