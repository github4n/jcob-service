package com.aicai.jcob.audit.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/3/7 0007.
 */
public class AuditSwitchVo implements Serializable {

    private static final long serialVersionUID = 8489110453369687517L;

    private Integer id ;

    /**
     * 审核开关类型
     * @see com.aicai.jcob.audit.common.constant.AuditSwitchType
     */
    private Integer type ;

    private Integer clientType;

    private String version ;

    private String  agentIds ;

    private Calendar createTime ;

    private String operator ;

    private Calendar updateTime ;

    private Integer status ;

    private List<Integer> agentIdList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getAgentIdList() {
        return agentIdList;
    }

    public void setAgentIdList(List<Integer> agentIdList) {
        this.agentIdList = agentIdList;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAgentIds() {
        return agentIds;
    }

    public void setAgentIds(String agentIds) {
        this.agentIds = agentIds;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}
