package com.aicai.jcob.game.common.util;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * 方案内容格式定义 
 * 
 * 官方胜平负
 * 格式1：场次标识（投注项－赔率） 例如：110212302(1-1.3)
 * 格式2：场次标识（投注项－赔率，投注项－赔率） 例如：110212302(1-1.3,2-2.1)
 * 投注项定义：3主胜，1平，0主负
 * 官方让球胜平负
 * 格式1：场次标识[－让球数]（投注项－赔率） 例如：110212302[-1](1-1.3)
 * 格式2：场次标识[－让球数]（投注项－赔率，投注项－赔率） 例如：110212302[-1](1-1.3,2-2.1)
 * 投注项定义：3主胜，1平，0主负
 * 亚盘
 * 格式1：场次标识[+受球数]（投注项－赔率） 例如：110212302[+0.5](3-1.3)
 * 格式2：场次标识[＋受球数/＋受球数]（投注项－赔率） 例如：110212302[+1/+1.5](0-1.3)
 * 投注项定义：3主胜，0主负
 * 外围大小球
 * 格式1：场次标识[预算总进球]（投注项－赔率） 例如：110212302[2](3-1.3)
 * 格式2：场次标识[预算总进球/预设总进球]（投注项－赔率） 例如：110212302[2/2.5](0-1.3)
 * 投注项定义：3大，0小
 * 
 * 
 * @author fx
 *
 */
public class ItemContentUtil {
	
	
	/**
	 * 
	 * @param matchTouzu 形如“2016001【5】(1-1.3)”
	 * @return
	 */
	public static String[] getTouZuOptions(String matchTouzu) {
		matchTouzu = matchTouzu.trim();
		String options = matchTouzu.substring(matchTouzu.indexOf("(") + 1, matchTouzu.length() - 1);
		String[] option = options.split("\\,");
		for (int i = 0; i < option.length; i++) {
			option[i] = option[i].replaceAll("\\-(.)*$", "");
		}
		return option;
	}
	/**
	 * 
	 * @param matchTouzu 形如“2016001【5】(1-1.3)”
	 * @return
	 */
	public static String getMatchNo(String matchTouzu) {
		String matchNo = matchTouzu.replaceAll("((\\[)|(\\())(.)*$", "");
		return matchNo;
	}
	
	/**
	 * 得到投注时的 让分，大小球预设 盘口信息
	 * @param matchTouzu
	 * @return
	 */
	public static String getHandicap(String matchTouzu) {
		String handicap = StringUtils.substringBetween(matchTouzu, "[", "]");
		return handicap;
	}
	public static void main(String[] args) {
		String str = "2016001[-1](1-1.3,1-0.58)";
		String[] options = getTouZuOptions(str);
		for(String option:options){
			System.out.println("-------------:"+option);
		}
		
		
	}
}
