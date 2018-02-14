package com.aicai.jcob.adver.common.domain;

import com.aicai.jcob.adver.common.result.FunctionParam;
import net.sf.json.JSONObject;

import java.util.Calendar;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public class ClientFunctionRecord implements java.io.Serializable {


    /**
     * 无线按版本渠道设置条件查询实体
     */
    private static final long serialVersionUID = 652059422343335469L;

    /**
     * 主键
     */
    private Integer Id;

    /**
     * 客户端
     * @see com.aicai.jcob.common.constant.ClientType
     */
    private Integer clientType;

    /**
     * 功能模块业务类别(广告位置)
     * @see com.aicai.jcob.adver.common.constant.AdvertiseLocation
     **/
    private Integer adLocation;
    /**
     * 显示顺序
     **/
    private Integer topNumber;
    /**
     * 有效状态
     * @see com.aicai.jcob.adver.common.constant.UsingStatus
     **/
    private Integer recordState;
    /**
     * 有效开始时间
     **/
    private Calendar startDate;
    /**
     * 有效结束时间
     **/
    private Calendar endDate;
    /**
     * 创建时间
     **/
    private Calendar createDate;
    /**
     * 请求查询特定标识，用来优先排序（如某类用户要将此条内容排在最前面）
     * 1表示有此项设置
     **/
    private Integer requestTop;
    /**
     * 正反选设置条件(0:符合所有条件)
     * @see com.aicai.jcob.adver.common.constant.ConditionType
     */
    private Integer tipsStatusType;


    /**
     * 广告内容
     */
    //private String functionContent;
    //标题
    private String title;

    //图片地址
    private String picUrl;

    /**
     * 跳转类型
     * @see com.aicai.jcob.adver.common.constant.RedirectType
     */
    private Integer redirectType ;

    /**
     * 内页
     * @see com.aicai.jcob.adver.common.constant.InnerPage
     */
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

    private String expand;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Integer getAdLocation() {
        return adLocation;
    }

    public void setAdLocation(Integer adLocation) {
        this.adLocation = adLocation;
    }

    public Integer getTopNumber() {
        return topNumber;
    }

    public void setTopNumber(Integer topNumber) {
        this.topNumber = topNumber;
    }

    public Integer getRecordState() {
        return recordState;
    }

    public void setRecordState(Integer recordState) {
        this.recordState = recordState;
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

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Integer getRequestTop() {
        return requestTop;
    }

    public void setRequestTop(Integer requestTop) {
        this.requestTop = requestTop;
    }

    public Integer getTipsStatusType() {
        return tipsStatusType;
    }

    public void setTipsStatusType(Integer tipsStatusType) {
        this.tipsStatusType = tipsStatusType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public Integer getInner() {
        return inner;
    }

    public void setInner(Integer inner) {
        this.inner = inner;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getResponseRecommend() {
        return responseRecommend;
    }

    public void setResponseRecommend(String responseRecommend) {
        this.responseRecommend = responseRecommend;
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

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }
}
