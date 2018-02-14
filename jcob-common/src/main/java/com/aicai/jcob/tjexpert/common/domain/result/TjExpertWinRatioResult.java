package com.aicai.jcob.tjexpert.common.domain.result;  

import java.io.Serializable;
import java.math.BigDecimal;

import com.aicai.jcob.tjexpert.common.domain.utils.ExpertRecordUtils;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月3日 上午11:42:34 
 * 提供给前端的一个月胜率排行
 */
public class TjExpertWinRatioResult implements Serializable{
	private static final long serialVersionUID = 4742732892564409815L;


	private Long memberId ;
	private String nickName ;
	private String phone ;
	private String icon ;
	/**
	 * 专家一个月胜率0.XX格式
	 * 获取百分比%格式.通过TjExpertWinRatioResult对象的getFormatMonthWinRatio()方法
	 */
	private BigDecimal monthWinRatio ;
	/**
	 * 专家半个月胜率0.XX格式
	 * 获取百分比%格式.通过TjExpertWinRatioResult对象的getFormatHalfMonthWinRatio()方法
	 */
	private BigDecimal halfMonthWinRatio ;
	/**
	 * 7天胜率0.XX格式
	 * 获取百分比%格式.通过TjExpertWinRatioResult对象的getFormatWinRatio()方法
	 */
	private BigDecimal winRatio ;
	public Long getMemberId() {
		return memberId;
	}
	public String getNickName() {
		return nickName;
	}
	public String getPhone() {
		return phone;
	}
	public String getIcon() {
		return icon;
	}
	public String getFormatMonthWinRatio() {
		return ExpertRecordUtils.getFormatWinRatio(monthWinRatio);
	}
	public String getFormatHalfMonthWinRatio() {
		return ExpertRecordUtils.getFormatWinRatio(halfMonthWinRatio);
	}
	
	public String getFormatWinRatio() {
		return ExpertRecordUtils.getFormatWinRatio(winRatio);
	}
	
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setMonthWinRatio(BigDecimal monthWinRatio) {
		this.monthWinRatio = monthWinRatio;
	}
	public BigDecimal getMonthWinRatio() {
		return monthWinRatio;
	}
	public BigDecimal getHalfMonthWinRatio() {
		return halfMonthWinRatio;
	}
	public BigDecimal getWinRatio() {
		return winRatio;
	}
	public void setHalfMonthWinRatio(BigDecimal halfMonthWinRatio) {
		this.halfMonthWinRatio = halfMonthWinRatio;
	}
	public void setWinRatio(BigDecimal winRatio) {
		this.winRatio = winRatio;
	} 
	
	
	
	
}
 