package com.aicai.jcob.tjplan.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.aicai.jcob.common.constant.ClientType;

public class TjLookLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -149279646678140729L;

	/**  */
    private Long id;

    /** 方案id */
    private Long planId;

    /** 金额 */
    private BigDecimal amount;

    /** 用户id */
    private Long memberId;

    /**  */
    private Integer gameId;

    /**  */
    private Integer raceType;

    /** 专家级别 */
    private Integer expertLevel;

    /** 创建时间 */
    private Calendar createTime;

    /**  */
    private Calendar updateTime;
    
    private Integer clientType = ClientType.android.getIndex();
    private Integer channel = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getRaceType() {
        return raceType;
    }

    public void setRaceType(Integer raceType) {
        this.raceType = raceType;
    }

    public Integer getExpertLevel() {
        return expertLevel;
    }

    public void setExpertLevel(Integer expertLevel) {
        this.expertLevel = expertLevel;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
    
}