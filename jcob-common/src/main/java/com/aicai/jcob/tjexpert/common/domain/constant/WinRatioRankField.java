package com.aicai.jcob.tjexpert.common.domain.constant;  

import com.aicai.jcob.common.constant.BaseType;

/** 
 * @author jing.ming
 * @version 创建时间：2016年3月3日 下午2:51:05 
 * 前端专家命中率排序选择
 */
public class WinRatioRankField extends BaseType{
	private static final long serialVersionUID = -2030757990338760025L;
	protected WinRatioRankField(Integer index, String description) {
		super(index, description);
	}
	/**
	 * 一个月排序
	 */
	public final static WinRatioRankField month_rank = new WinRatioRankField(0,"一个月排序") ;
	/**
	 * 半个月排序
	 */
	public final static WinRatioRankField half_month_rank = new WinRatioRankField(1,"半个月排序") ;
	/**
	 * 7天排序
	 */
	public final static WinRatioRankField week_rank = new WinRatioRankField(2,"7天排序") ;
	
	
}
 