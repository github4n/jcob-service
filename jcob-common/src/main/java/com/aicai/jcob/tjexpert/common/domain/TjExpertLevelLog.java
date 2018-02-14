package com.aicai.jcob.tjexpert.common.domain;

import java.io.Serializable;
import java.util.Date;

public class TjExpertLevelLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5771549444755970269L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 操作类型：1升级，2降级 */
    private Integer handleType;

    /**  */
    private Integer newLevel;

    /**  */
    private Integer oldLevel;

    /**  */
    private Date createTime ;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public Integer getNewLevel() {
		return newLevel;
	}

	public Integer getOldLevel() {
		return oldLevel;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public void setNewLevel(Integer newLevel) {
		this.newLevel = newLevel;
	}

	public void setOldLevel(Integer oldLevel) {
		this.oldLevel = oldLevel;
	}

	

   
}