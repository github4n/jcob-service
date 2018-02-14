package com.aicai.jcob.client.common.domain;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 客户端更新
 * 
 */
public class ClientUpgrade implements Serializable {
    private static final long serialVersionUID = 3918071304618996097L;

    private Integer id ;

    /**新版本号(用户需要升级到的版本)**/
    private String curVersion;

    /**旧版本号**/
    private String oldVersion;

    /**APP平台类型**/
    private Integer clientType;

    /**APP下载地址**/
    private String appUrl;

    /**版本更新描述**/
    private String appDesc;

    /**1是普通升级 2是强制升级**/
    private Integer upgradeType = 0;
    /**0禁用升级，1启用升级**/
    private Integer upgradeStatus = 0;

    /**0增加新版本，1配置升级,2 渠道包上传记录**/
    private Integer model = 0;
    
    /**0未删除，1 已删除**/
    private Integer isDel = 0;

    /**渠道号**/
    private Integer channel;

    /**操作员**/
    private String operator;

    /**更新时间**/
    private Calendar updateTime;
    
    /**创建时间**/
    private Calendar createTime;

    public String getCurVersion() {
        return curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion;
    }

    public String getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(String oldVersion) {
        this.oldVersion = oldVersion;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(Integer upgradeType) {
        this.upgradeType = upgradeType;
    }

    public Integer getUpgradeStatus() {
        return upgradeStatus;
    }

    public void setUpgradeStatus(Integer upgradeStatus) {
        this.upgradeStatus = upgradeStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ClientUpgrade [curVersion=" + curVersion + ", oldVersion=" + oldVersion + ", clientType=" + clientType
                + ", appUrl=" + appUrl + ", appDesc=" + appDesc + ", upgradeType=" + upgradeType + ", upgradeStatus="
                + upgradeStatus + ", model=" + model + ", channel=" + channel + ", operator=" + operator
                + ", createTime=" + createTime + "]";
    }

}
