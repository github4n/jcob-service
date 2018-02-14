package com.aicai.jcob.member.manager.impl.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;



/**
 * 用户中心工具类
 * 
 */
public class MemberCenterValidUtil {



	/**
	 * 是否包含非法字符(包括空格)
	 *
	 * @param str
	 * @return
	 */
	public static boolean containInvalidChars(String str) {
		String SPECIAL_STR = "#~!@%^&*();'\"?><[]{}\\|,:/=-+—“”‘.`$；，。！@#￥%……&*（）——+？～＠＃￥％……＆×（）——＋｜｛｝？《》：“”、；‘’【】";
		for (int i = 0; i < str.length(); i++) {
			if (SPECIAL_STR.indexOf(str.charAt(i)) != -1||String.valueOf(str.charAt(i)).equals(" ")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 有效的注册长度：用户名(4--14位)
	 * @param str 用户名
	 * @return
	 */
	public static boolean validUserNameLength(final String str) {
		return validUserNameLength(str,14);
	}
	/**
	 * 有效的注册长度：用户名(4--lenthMax位)
	 * @param str 用户名
	 * @return
	 */
	public static boolean validUserNameLength(final String str,int lenthMax) {
		int sum = 0;
		for (int i = 0, len = str.trim().length(); i < len; i++) {
			if ((str.charAt(i) >= 0) && (str.charAt(i) <= 255)) {
				sum++;
			} else {
				sum += 2;
			}
		}
		if (sum >= 4 && sum <= lenthMax)
			return true;
		return false;
	}
	
	/**
	 * 有效的密码长度（6到15位）
	 * @param str 密码字符串
	 * @return
	 */
	public static boolean validPasswordLength(String str) {
		String regex = "([a-zA-Z0-9]){6,15}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * 验证邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean validateInvalidEmail(String email){
		if(email == null){
			return false;
		}
		String regex = "\\w+(\\.\\w+)?@\\w+\\.\\w+(\\.\\w+{2})?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()){
			return true;
		}
		return false;
	}

	/***
	 * 验证身份证号 包最后一位为X
	 * @param cardID
	 * @return
	 */
	public static boolean validateInvalidCardID(String cardID){
		if(cardID == null){
			return false;
		}
		String regex = "[1-9]\\d{16}[\\d,X,x]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cardID);
		if (matcher.matches()){
			return true;
		}
		return false;
	}

	/***
	 * 验证String是否为空
	 * @param cardID
	 * @return
	 */
	public static boolean validateString(String str){
		if(str == null){
			return true;
		}
		String regex = "\\s*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
			return true;
		}
		return false;
	}

	/***
	 * 验证String是否为整数
	 * @param str
	 * @return
	 */
	public static boolean validateNumber(String str){
		if(str == null){
			return false;
		}
		String regex = "[-]?\\d*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
			return true;
		}
		return false;
	}

	/***
	 * 验证String是否为时间格式
	 * @param str
	 * @return
	 */
	public static boolean validateDateTime(String str) {
		if (str == null) {
			return false;
		}
		String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static String getContent(Map<String,String> params, String privateKey) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        List<String> prestr = new ArrayList<String>();
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);
            prestr.add(key + "=" + value);
        }
        return StringUtils.join(prestr,"&") + privateKey;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getContent(Properties properties) throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer();
		List keys = new ArrayList(properties.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = properties.getProperty(key);
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}

		return content.toString();
	}

    /**
     * 创建8位数的密码
     * @return
     */
	public static String create8p() {
		StringBuffer password = new StringBuffer();
		Random rand = new Random();
		while (password.length() < 8) {
			int x = rand.nextInt(91);
			if (x >= 0 && x <= 9) {
				String n = "" + x;
				password.append(n);
			}
			if (x >= 65 && x <= 90) {
				char c = (char) x;
				password.append(c);
			}
		}
		return password.toString();
	}
	/***
	 * 验证String 是否重复
	 * 
	 * @param str
	 * @return true重复，false不重复
	 */
	public static boolean validateRepeat(String str){
		if(str == null){
			return true;
		}
		char[] cArr = str.toLowerCase().toCharArray();
		Set<Character> charSet = new HashSet<Character>();
		for (char c : cArr) {
			charSet.add(c);
		}
		if (charSet.size() == 1) {
			return true;
		}
		return false;
	}
	/**
	 * 验证string是否连续
	 * @param str
	 * @return true 连续，false 不连续
	 */
	public static boolean validateContinuous(String str){
		if(str == null){
			return true;
		}
		char[] cArr = str.toLowerCase().toCharArray();
		int count = 0;
		for (int i = 0; i < cArr.length -1; i++) {
			if (Math.abs(cArr[i+1] -cArr[i]) == 1 ) {
				count ++;
			}
		}
		if (count == cArr.length -1) {
			return true;
		}
		return false;
	}

}

