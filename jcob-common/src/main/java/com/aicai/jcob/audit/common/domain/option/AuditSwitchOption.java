package com.aicai.jcob.audit.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tianlun.wu on 2016/2/28 0028.
 */
public class AuditSwitchOption implements Serializable {

    private static final long serialVersionUID = 812135219611113236L;

    private Integer clientType ;

    private String version ;

    private Integer agentId;

    private Integer status ;

    private Calendar startTime ;

    private Calendar endTime ;

    /**
     * @see com.aicai.jcob.audit.common.constant.AuditSwitchType
     */
    private Integer type;


    private List<Integer> ids ;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
