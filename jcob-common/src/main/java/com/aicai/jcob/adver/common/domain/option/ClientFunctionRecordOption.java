package com.aicai.jcob.adver.common.domain.option;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public class ClientFunctionRecordOption implements Serializable {

    private static final long serialVersionUID = 8199433905717484257L;

    private Integer clientType ;
    private String version ;
    private Integer agentId ;
    private Integer recordState ;
    private Integer adLocation ;
    private Calendar startDate;
    private Calendar endDate ;


    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
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

    public Integer getRecordState() {
        return recordState;
    }

    public void setRecordState(Integer recordState) {
        this.recordState = recordState;
    }

    public Integer getAdLocation() {
        return adLocation;
    }

    public void setAdLocation(Integer adLocation) {
        this.adLocation = adLocation;
    }

    @Override
    public String toString() {
        return "clientType:"+clientType+":version:"+version+":agentId:"+agentId+":recordState:"+recordState+":adLocation:"+adLocation ;
    }
}
