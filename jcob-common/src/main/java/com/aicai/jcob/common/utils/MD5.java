package com.aicai.jcob.common.utils;

import java.nio.charset.Charset;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
	
	private static final Charset utf8 = Charset.forName("utf-8");

	
	
	public final static String md5Encode(String s) {
		return DigestUtils.md5Hex(s.getBytes(utf8));
	}

	
	/*public static void main(String [] arr){
	    String str="456014201103189145766020110318172833<body><issueNotify><issue gameName=\"ssl\" number=\"2011031815\" startTime=\"2011-03-18 16:58:00\" stopTime=\"2011-03-18 17:28:00\" status=\"3\" bonusCode=\"\" salesMoney=\"-1.0\" bonusMoney=\"-1.0\"/></issueNotify></body></message>";
        System.out.println(MD5.HPEncode(str,"GBK"));
	}*/
	
}
