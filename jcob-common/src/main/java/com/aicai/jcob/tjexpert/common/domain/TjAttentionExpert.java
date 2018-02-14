package com.aicai.jcob.tjexpert.common.domain;

import java.io.Serializable;
import java.util.Date;

import com.aicai.jcob.tjexpert.common.domain.constant.AttentionStatus;

public class TjAttentionExpert implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8979722181825889422L;

	/**  */
    private Long id;

    /** 粉丝*/
    private Long memberId;

    /** 明星 */
    private Long attentionMemberId;

    /** 关注状态:0取消关注，1已关注 */
    private Integer status = AttentionStatus.attention;

    /**  */
    private Date createTime ;

    /**  */
    private Date updateTime ;

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

    public Long getAttentionMemberId() {
        return attentionMemberId;
    }

    public void setAttentionMemberId(Long attentionMemberId) {
        this.attentionMemberId = attentionMemberId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

   
}