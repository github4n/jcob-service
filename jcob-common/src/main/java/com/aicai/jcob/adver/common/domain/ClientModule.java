package com.aicai.jcob.adver.common.domain;

import com.aicai.jcob.adver.common.constant.ModuleStyle;
import com.aicai.jcob.adver.common.constant.UsingStatus;

import java.io.Serializable;
import java.util.Calendar;

public class ClientModule  implements Serializable{

    private static final long serialVersionUID = 5829539628911020570L;
    private Integer id;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 模块样式
     * @see com.aicai.jcob.adver.common.constant.ModuleStyle
     */
    private Integer moduleStyle;

    /**
     * 模块图片数量
     */
    private Integer modulePicNum;

    /**
     * 模块备用位置数量
     */
    private Integer moduleBackupLocNum;

    /**
     * 模块在模板中的位置
     */
    private Integer topNumber;

    /**
     * 模板id
     */
    private Integer templateId;

    /**
     * 模块状态
     * @see UsingStatus
     */
    private Integer status;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Calendar updateDate;

    /**
     * 当模块为推荐位5（推荐单）
     * @see com.aicai.jcob.adver.common.constant.DataType
     */
    private Integer dataType;

    private Integer saveAmount ;

    private String expand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getModuleStyle() {
        return moduleStyle;
    }

    public void setModuleStyle(Integer moduleStyle) {
        this.moduleStyle = moduleStyle;
    }

    public Integer getModulePicNum() {
        return modulePicNum;
    }

    public void setModulePicNum(Integer modulePicNum) {
        this.modulePicNum = modulePicNum;
    }

    public Integer getModuleBackupLocNum() {
        return moduleBackupLocNum;
    }

    public void setModuleBackupLocNum(Integer moduleBackupLocNum) {
        this.moduleBackupLocNum = moduleBackupLocNum;
    }

    public Integer getTopNumber() {
        return topNumber;
    }

    public void setTopNumber(Integer topNumber) {
        this.topNumber = topNumber;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }


    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(Integer saveAmount) {
        this.saveAmount = saveAmount;
    }

    public static ClientModule getInstance(Integer moduleStyleId){
        if(moduleStyleId == ModuleStyle.title.getIndex()){
            ModuleStyle title = ModuleStyle.title ;
            ClientModule clientModule = new ClientModule();
            clientModule.setModuleName(title.getOutDescription());
            clientModule.setModulePicNum(1);
            clientModule.setModuleBackupLocNum(0);
            clientModule.setModuleStyle(title.getIndex());
            clientModule.setStatus(UsingStatus.stopping.getIndex());
            return clientModule;
        } else if(moduleStyleId == ModuleStyle.tonglan.getIndex()){
            ModuleStyle tonglan = ModuleStyle.tonglan ;
            ClientModule clientModule = new ClientModule();
            clientModule.setModuleName(tonglan.getOutDescription());
            clientModule.setModulePicNum(1);
            clientModule.setModuleStyle(tonglan.getIndex());
            clientModule.setModuleBackupLocNum(0);
            clientModule.setStatus(UsingStatus.stopping.getIndex());
            return clientModule;
        } else if(moduleStyleId == ModuleStyle.shuanglan.getIndex()){
            ModuleStyle shuanglan = ModuleStyle.shuanglan ;
            ClientModule clientModule = new ClientModule();
            clientModule.setModuleName(shuanglan.getOutDescription());
            clientModule.setModulePicNum(2);
            clientModule.setModuleStyle(shuanglan.getIndex());
            clientModule.setModuleBackupLocNum(0);
            clientModule.setStatus(UsingStatus.stopping.getIndex());
            return clientModule;
        } else if(moduleStyleId == ModuleStyle.tuijiandan.getIndex()){
            ModuleStyle tuijiandan = ModuleStyle.tuijiandan ;
            ClientModule clientModule = new ClientModule();
            clientModule.setModuleName(tuijiandan.getOutDescription());
            clientModule.setModulePicNum(5);
            clientModule.setModuleStyle(tuijiandan.getIndex());
            clientModule.setModuleBackupLocNum(3);
            clientModule.setStatus(UsingStatus.stopping.getIndex());
            return clientModule;
        } else if(moduleStyleId == ModuleStyle.zhuangjia.getIndex()){
            ModuleStyle zhuangjia = ModuleStyle.zhuangjia ;
            ClientModule clientModule = new ClientModule();
            clientModule.setModuleName(zhuangjia.getOutDescription());
            clientModule.setModulePicNum(10);
            clientModule.setModuleStyle(zhuangjia.getIndex());
            clientModule.setModuleBackupLocNum(0);
            clientModule.setStatus(UsingStatus.stopping.getIndex());
            return clientModule;
        }

        return null ;
    }
}