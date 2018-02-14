package com.aicai.jcob.member.manager.impl.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.aicai.jcob.exception.common.ExceptionCode;
import com.aicai.jcob.exception.common.JcobServerException;

/**
 * 敏感词处理方法
 * @project coreService
 * @author szk
 * @date 2010-11-27
 * Copyright (C) 2010-2012 www.2caipiao.com Inc. All rights reserved.
 */
@Component
@Lazy(value=false)
public class WordCensor  {

	private static  final Logger LOGGER = LoggerFactory.getLogger(WordCensor.class);
    /**保存敏感词的列表*/
	private static List<String> wordCensor = new ArrayList<String>();
	/**过滤词文件名*/
	private static final String FILTER_FILE_NAME = "sensitivewords.txt";
	
	static{
	        ClassLoader loader = WordCensor.class.getClassLoader();
	        InputStream wordFilterInput = loader.getResourceAsStream(FILTER_FILE_NAME);
	        String line;
	        BufferedReader reader =  null;
	        try {
	            reader =  new BufferedReader(new InputStreamReader(wordFilterInput, "UTF-8"));
	            while ((line = reader.readLine()) != null) {
	                if (StringUtils.isNotBlank(line)){
	                    wordCensor.add(line);
	                }
	            }
	        } catch (FileNotFoundException e) {
	            LOGGER.warn("敏感词文件没有找到:"+e.getMessage());
	        } catch (IOException e) {
	            LOGGER.warn("读取敏感词文件IO异常:"+e.getMessage());
	        }finally{
	            IOUtils.closeQuietly(reader);
	            IOUtils.closeQuietly(wordFilterInput);
	        }
	}

	/**
	 * 敏感词验证
	 * 
	 * @param account
	 * @create_time 2010-12-2 上午11:03:01
	 */
	public static void validwordcensor(String account) {
		List<String> list = wordCensor;
		if (list != null) {
			for (String key : list) {
				if (RegexUtils.isChinese(key)) {
					if (account.indexOf(key) != -1) {
						throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_ILLEGALITY);
					}
				} else {
					if (account.equals(key) || account.contains(key)) {
						throw new JcobServerException(ExceptionCode.MEMBER_NICKNAME_ILLEGALITY);
					}
				}
			}
		}
	}

	/**
	 * 将敏感字符替换为*
	 * 
	 * 2011-6-23
	 */
	public static  String replaceWordCensor(String text){
		List<String> list = wordCensor;
		String replacedStr = text;
        if (text!=null && list != null) {
            for (String key : list) {
                if (text.indexOf(key)!=-1) {
                	replacedStr =  replacedStr.replaceAll(key, "*********");
                  
                }
            }
        }
        return replacedStr;
	}
	
	
}

