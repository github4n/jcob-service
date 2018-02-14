package com.aicai.jcob.common.utils;

/**
 * 
 * @author hailong.zhang
 * 异常信息处理工具类
 */
public class ThrowableUtil {

	public static String getFullExceptionInfo(Throwable e){
		if(null == e) return null;
		return "File : " + e.getStackTrace()[0].getFileName() 
				+ ",Method : " + e.getStackTrace()[0].getMethodName() 
				+ ",Line Number : " + e.getStackTrace()[0].getLineNumber();
	}
}
