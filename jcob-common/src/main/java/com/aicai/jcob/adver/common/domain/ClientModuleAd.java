package com.aicai.jcob.adver.common.domain;

import com.aicai.jcob.adver.common.constant.RedirectType;

import java.io.Serializable;
import java.util.Calendar;

public class ClientModuleAd  implements Serializable{
    private static final long serialVersionUID = 2635581932990584802L;
    private Integer id;

    /**
     * 标题
     */
    private String firstTitle;

    /**
     * 标题颜色
     */
    private String firstTitleColor;

    /**
     * 副标题
     */
    private String secondTitle;

    /**
     * 副标题颜色
     */
    private String secondTitleColor;

    /**
     * 图片位置
     */
    private String picUrl;

    /**
     * @see com.aicai.jcob.adver.common.constant.UsingStatus
     */
    private Integer status;

    /**
     * 模块id
     */
    private Integer moduleId;

    /**
     * 在模块中的位置
     */
    private Integer modulePosition;

    /**
     * 跳转类型
     * @see com.aicai.jcob.adver.common.constant.RedirectType
     */
    private Integer redirectType;

    //跳内页的内容
    private Integer inner;
    //响应链接
    private String responseUrl;
    //响应推荐方案
    private String responseRecommend;
    //响应专家ID
    private Long responseExperts;
    //响应比赛ID
    private Long responseMatch;

    private Calendar startDate;

    private Calendar endDate;

    private String updateUser;

    private Calendar updateDate;

    private String expand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle == null ? null : firstTitle.trim();
    }

    public String getFirstTitleColor() {
        return firstTitleColor;
    }

    public void setFirstTitleColor(String firstTitleColor) {
        this.firstTitleColor = firstTitleColor == null ? null : firstTitleColor.trim();
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle == null ? null : secondTitle.trim();
    }

    public String getSecondTitleColor() {
        return secondTitleColor;
    }

    public void setSecondTitleColor(String secondTitleColor) {
        this.secondTitleColor = secondTitleColor == null ? null : secondTitleColor.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getModulePosition() {
        return modulePosition;
    }

    public void setModulePosition(Integer modulePosition) {
        this.modulePosition = modulePosition;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl == null ? null : responseUrl.trim();
    }

    public String getResponseRecommend() {
        return responseRecommend;
    }

    public void setResponseRecommend(String responseRecommend) {
        this.responseRecommend = responseRecommend == null ? null : responseRecommend.trim();
    }

    public Long getResponseExperts() {
        return responseExperts;
    }

    public void setResponseExperts(Long responseExperts) {
        this.responseExperts = responseExperts;
    }

    public Long getResponseMatch() {
        return responseMatch;
    }

    public void setResponseMatch(Long responseMatch) {
        this.responseMatch = responseMatch;
    }

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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
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
        this.expand = expand == null ? null : expand.trim();
    }

    public Integer getInner() {
        return inner;
    }

    public void setInner(Integer inner) {
        this.inner = inner;
    }
}