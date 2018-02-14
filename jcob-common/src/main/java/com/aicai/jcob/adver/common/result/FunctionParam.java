package com.aicai.jcob.adver.common.result;

/**
 * Created by tianlun.wu on 2016/2/25 0025.
 */
public class FunctionParam {

    /**
     * 业务参数json解析对象
     *
     */
    private static final long serialVersionUID = 5538932213529786477L;

    private String title;

    //图片地址
    private String imgUrl;

    /**
     * 跳转类型
     * @see com.aicai.jcob.adver.common.constant.RedirectType
     */
    private Integer redirectType ;

    /**
     * 内页
     * @see com.aicai.jcob.adver.common.constant.InnerPage
     */
    private Integer inner ;

    private String responseUrl ;

    private String recommondNo;

    private String expertId ;

    private String matchId ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getRecommondNo() {
        return recommondNo;
    }

    public void setRecommondNo(String recommondNo) {
        this.recommondNo = recommondNo;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
