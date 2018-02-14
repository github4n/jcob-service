package com.aicai.jcob.tjexpert.common.domain.utils;  

import java.math.BigDecimal;
import java.text.NumberFormat;

/** 
 * @author jing.ming
 * @version 创建时间：2016年2月23日 下午6:13:01 
 * record转换工具类
 */
public class ExpertRecordUtils {
	/**
	 * 
	 * @param  record 格式"10100"
	 * @return
	 */
	public static String getFormatRecord(String record){
		if(record==null||record.equals("")){
			return "" ;
		}
		String[] splitArr = record.split("");
		int totalNum = splitArr.length ;
		int winNum = 0 ;
		if(totalNum<=0){
			return "" ;
		}
		for(String singleRecord:splitArr){
			if(singleRecord.equals("1")){
				winNum+=1 ;
			}
		}
		return "近"+totalNum+"中"+winNum;
		
	}
	
	/**
	 * 将战绩小数以百分数格式返回
	 * @param number
	 * @return
	 */
	public static String getFormatWinRatio(BigDecimal number){
		//获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		//设置百分数精确度2即保留两位小数
		//nt.setMinimumFractionDigits(2);
		return nt.format(number);
	}
	
	//public static void main(String[] args){
		//System.out.println("======"+getFormatRecord("1")) ;
	//}
}

 