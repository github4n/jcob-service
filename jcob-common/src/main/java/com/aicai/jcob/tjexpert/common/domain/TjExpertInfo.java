package com.aicai.jcob.tjexpert.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TjExpertInfo implements Serializable{

	private static final long serialVersionUID = 1027382147669565610L;

	/**  */
    private Long id;

    /**  */
    private Long memberId;

    /** 等级：0普通用户，1实验室专家，2正式专家,3带v的正式专家 */
    private Integer level ;

    /** 战绩信息，记录规则：开奖后维护中了就追加1没中就追加0 格式：01101 */
    private String record;

    /** 命中率(默认7天命中率) */
    private BigDecimal winRatio ;
    /** 15天命中率 */
    private BigDecimal halfMonthWinRatio ;
    /** 30天命中率 */
    private BigDecimal monthWinRatio ;
    /**30天发布且已开奖总方案数*/
    private Integer monthOpenedCount ;
    /**15天发布且已开奖总方案数*/
    private Integer halfMonthOpenedCount ;
    /**7天发布且已开奖总方案数*/
    private Integer openedCount ;

    /** 关注我的人数 */
    private Integer attentionMe ;

    /** 我关注的人数 */
    private Integer attentionOther ;

    /** 专家描述 */
    private String description;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public BigDecimal getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(BigDecimal winRatio) {
        this.winRatio = winRatio;
    }

    public Integer getAttentionMe() {
        return attentionMe;
    }

    public void setAttentionMe(Integer attentionMe) {
        this.attentionMe = attentionMe;
    }

    public Integer getAttentionOther() {
        return attentionOther;
    }

    public void setAttentionOther(Integer attentionOther) {
        this.attentionOther = attentionOther;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public BigDecimal getHalfMonthWinRatio() {
		return halfMonthWinRatio;
	}

	public BigDecimal getMonthWinRatio() {
		return monthWinRatio;
	}

	public void setHalfMonthWinRatio(BigDecimal halfMonthWinRatio) {
		this.halfMonthWinRatio = halfMonthWinRatio;
	}

	public void setMonthWinRatio(BigDecimal monthWinRatio) {
		this.monthWinRatio = monthWinRatio;
	}

	public Integer getMonthOpenedCount() {
		return monthOpenedCount;
	}

	public Integer getHalfMonthOpenedCount() {
		return halfMonthOpenedCount;
	}

	public Integer getOpenedCount() {
		return openedCount;
	}

	public void setMonthOpenedCount(Integer monthOpenedCount) {
		this.monthOpenedCount = monthOpenedCount;
	}

	public void setHalfMonthOpenedCount(Integer halfMonthOpenedCount) {
		this.halfMonthOpenedCount = halfMonthOpenedCount;
	}

	public void setOpenedCount(Integer openedCount) {
		this.openedCount = openedCount;
	}
	
	

}