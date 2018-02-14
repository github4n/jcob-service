package com.aicai.jcob.game.common.domain.constant;

public class GameOptionConstant {
	
	public static final String jzspf_zs="3";
	public static final String jzspf_zp="1";
	public static final String jzspf_zf="0";
	
	public static final String jzrqspf_zs="3";
	public static final String jzrqspf_zp="1";
	public static final String jzrqspf_zf="0";
	
	public static final String jzdxq_d="3";
	public static final String jzdxq_x="0";
	
	
	public static final String yapan_zs="3";
	public static final String yapan_zf="0";
	
	/**
	 * 特殊的Option,代表盘口开奖结果走盘，
	 */
	public static final String zp_result="0.1";
	
	
	/**
	 * 特殊的Option,代表盘口开奖结果前一半是主胜,后一半走盘
	 */
	public static final String zszp_result="31";
	/**
	 * 特殊的Option,代表盘口开奖结果前一半走盘,后一半主胜
	 */
	public static final String zpzs_result="13";
	
	/**
	 * 特殊的Option,代表盘口开奖结果前一半是主负,后一半走盘
	 */
	public static final String zfzp_result="01";
	/**
	 * 特殊的Option,代表盘口开奖结果前一半走盘,后一半主负
	 */
	public static final String zpzf_result="10";
	
	/**
	 * 特殊的Option,代表盘口开奖结果前一半是大球,后一半走盘
	 */
	public static final String dqzp_result="31";
	/**
	 * 特殊的Option,代表盘口开奖结果前一半走盘,后一半大球
	 */
	public static final String zpdq_result="13";
	
	
	/**
	 * 特殊的Option,代表盘口开奖结果前一半是小球,后一半走盘
	 */
	public static final String xqzp_result="01";
	/**
	 * 特殊的Option,代表盘口开奖结果前一半走盘,后一半小球
	 */
	public static final String zpxq_result="10";
	
	
	/**
	 * 特殊的Option,代表赛事取消的开盘结果
	 */
	public static final String race_cancel_result="-0.1";
}
