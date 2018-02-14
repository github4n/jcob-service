package com.aicai.jcob.member.common.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户关注的赛事
 * 
 */
public class MemberAttentionTeam implements Serializable {

    private static final long serialVersionUID = 440915858159059391L;

    /**主键ID*/
    private Long id;

    /**用户ID*/
    private Long memberId;

    /**赛事ID*/
    private Long raceId;

    /**0取消关注 1已关注*/
    private Integer isAttention;

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

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
