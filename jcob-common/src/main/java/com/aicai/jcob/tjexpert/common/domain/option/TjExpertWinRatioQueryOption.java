package com.aicai.jcob.tjexpert.common.domain.option;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author jing.ming
 */
public class TjExpertWinRatioQueryOption implements Serializable {

	private static final long serialVersionUID = -2617919985233039222L;

	/** 用户id */
	private Long memberId;

	/** 手机号 */
	private String phone;

	/** 昵称 */
	private String nickName;

	/** 专家等级 */
	private Integer level;

	/** N天命中率 7 / 15 / 30 */
	private Integer day = 7;

	/** 命中率范围查询起始 */
	private BigDecimal queryStartWinRatio;

	/** 命中率范围查询结束 */
	private BigDecimal queryEndWinRatio;

	/** 查询开始开始 */
	private Calendar startTime;

	/** 查询结束时间 */
	private Calendar endTime;

	/** 排序方式 1 按命中率从高到低  2 按命中率从低到高 */
	private Integer orderType = 1;

	public Long getMemberId() {
		return memberId;
	}

	public String getPhone() {
		return phone;
	}

	public String getNickName() {
		return nickName;
	}

	public Integer getLevel() {
		return level;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public BigDecimal getQueryStartWinRatio() {
		return queryStartWinRatio;
	}

	public void setQueryStartWinRatio(BigDecimal queryStartWinRatio) {
		this.queryStartWinRatio = queryStartWinRatio;
	}

	public BigDecimal getQueryEndWinRatio() {
		return queryEndWinRatio;
	}

	public void setQueryEndWinRatio(BigDecimal queryEndWinRatio) {
		this.queryEndWinRatio = queryEndWinRatio;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

}
