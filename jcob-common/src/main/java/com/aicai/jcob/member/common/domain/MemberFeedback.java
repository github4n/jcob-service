package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Date;

public class MemberFeedback  implements Serializable{
    
    private static final long serialVersionUID = 4110146991364492222L;

    /**主键ID*/
    private Long id;

    /**用户ID*/
    private Long memberId;

    /**客户端类型  参考com.aicai.jcob.common.constant.ClientType */
    private Integer clientType;

    /**反馈内容*/
    private String content;

    /**创建时间*/
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MemberFeedback [id=" + id + ", memberId=" + memberId + ", clientType=" + clientType + ", content="
                + content + ", createTime=" + createTime + "]";
    }

}
