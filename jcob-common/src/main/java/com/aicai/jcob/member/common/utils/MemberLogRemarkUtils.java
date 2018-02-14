package com.aicai.jcob.member.common.utils;  
/** 
 * @author jing.ming
 * @version 创建时间：2016年3月1日 下午5:02:31 
 * memberOperLog remark字段获取关注专家membrId和nickName
 */
public class MemberLogRemarkUtils {

	/**
	 * 获取nickName
	 * @param remark
	 * @return
	 */
	public static String getNickName(String remark){
		if(remark==null||remark.equals("")){
			return "" ;
		}
		String[] attenSplit = remark.split(",");	
		if(attenSplit.length<=0){
			return "" ;
		}
		return attenSplit[1];
		
	}
	
	/**
	 * 获取memberId
	 * @param remark
	 * @return
	 */
	public static Long getMemberId(String remark){
		if(remark==null||remark.equals("")){
			return null ;
		}
		String[] attenSplit = remark.split(",");	
		if(attenSplit.length<=0){
			return null ;
		}
		return Long.valueOf(attenSplit[0]) ;
		
	}
}
 