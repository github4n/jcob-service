package com.aicai.jcob.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.aicai.jcob.common.constant.ClientType;
import com.aicai.jcob.common.utils.DateUtil;
import com.aicai.jcob.common.utils.MD5;
import com.aicai.jcob.member.common.domain.constant.ChannelProxy;

public class StringTools {

	/**
	 * 产生随机字符串
	 */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	private static Object initLock = new Object();

	private static Random randGenNumber = null;
	private static char[] numbersAndLettersNumber = null;
	private static Object initLockNumber = new Object();

	public static final String parserArrayToString(String[] array, String splter) {
		StringBuffer strBuff = new StringBuffer();
		for (String str : array) {
			strBuff.append(str);
			strBuff.append(splter);
		}
		strBuff.delete(strBuff.toString().length() - splter.length(), strBuff.toString().length());
		return strBuff.toString();
	}

	/**
	 * 根据id产生生 preStr+日期+0000+id 的编号
	 * 
	 * @param preStr
	 * @param id
	 * @param length
	 *            是+0000+id 长度
	 * @return
	 */
	public static String buildYYYYMMDDNo(String preStr, long id, int length) {
		StringBuilder sbNo = new StringBuilder(String.valueOf(id));
		sbNo = sbNo.insert(0, "0000000000000000000000000000000000000000000");

		String no = sbNo.substring(sbNo.length() - length, sbNo.length());
		no = preStr + DateUtil.toYYYYMMDD(Calendar.getInstance()) + no;
		return no;
	}

	/**
	 * 根据id产生生 preStr+日期+0000+id 的编号
	 * 
	 * @param preStr
	 * @param id
	 * @param length
	 *            是+0000+id 长度
	 * @return
	 */
	public static String buildYYMMDDNo(String preStr, long id, int length) {
		StringBuilder sbNo = new StringBuilder(String.valueOf(id));
		sbNo = sbNo.insert(0, "0000000000000000000000000000000000000000000");

		String no = sbNo.substring(sbNo.length() - length, sbNo.length());
		no = preStr + DateUtil.toYYMMDD(Calendar.getInstance()) + no;

		return no;
	}

	public static void main(String[] args) {

		System.out.println(buildYYYYMMDDNo("B", 123, 9));
	}

	public static final String randomString(int length) {

		if (length < 1) {
			return null;
		}
		// if (randGen == null) {
		synchronized (initLock) {
			if (randGen == null) {
				randGen = new Random();
				numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
			}
		}
		// }
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(62)];
		}
		return new String(randBuffer);
	}

	public static final String randomNumberString(int length) {

		if (length < 1) {
			return null;
		}
		// if (randGenNumber == null) {
		synchronized (initLockNumber) {
			if (randGenNumber == null) {
				randGenNumber = new Random();
				numbersAndLettersNumber = ("0123456789").toCharArray();
			}
		}
		// }
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLettersNumber[randGenNumber.nextInt(10)];
		}
		return new String(randBuffer);
	}

	final static public String getStringFillZero(String str, int length) {
		if (str.length() == length) {
			return str;
		}
		int fillLength = length - str.length();
		for (int i = 0; i < fillLength; i++) {
			str = "0" + str;
		}
		return str;
	}

	final static public boolean isValueIdTokenString(String tokenString, String split, String value) {
		String[] str = tokenString.split(split);
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @描述:解析http头里的参数值
	 * @param header
	 * @return
	 */
	final static public HashMap<String, String> getParametersByHttpHead(String header) {

		String data = header.substring(header.indexOf("?") + 1, header.indexOf(" HTTP"));
		return getParametersByContents(data);
	}

	/**
	 * @描述:解析 key=value&key=value的键值
	 * @param contents
	 * @return
	 */
	final static public HashMap<String, String> getParametersByContents(String contents) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] keyValues = contents.split("&");
		for (int i = 0; i < keyValues.length; i++) {
			if (StringUtils.isBlank(keyValues[i]) || StringUtils.isBlank(keyValues[i].trim())) {
				continue;
			}
			String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
			String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * @描述:解析 key=value&key=value的键值
	 * @param contents
	 * @return
	 */
	final static public HashMap<String, String> getParametersByContents(String contents, String charset) {
		System.out.println(contents);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] keyValues = contents.split("&");
		for (int i = 0; i < keyValues.length; i++) {
			String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
			String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);

			if (charset != null && !"".equals(charset)) {
				try {
					value = java.net.URLDecoder.decode(value, charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			map.put(key, value);
		}
		return map;
	}

	/**
	 * 
	 * @描述:将map转成key=value&key=value
	 * @param contents
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final static public String getStringByHashMap(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		// Iterator<?> it = map.keySet().iterator();
		// while(it.hasNext()){
		// Object key = it.next();
		// Object value = map.get(key);
		// sb.append(key.toString()).append("=").append(value.toString()).append("&");
		// }
		// if(sb.length()>0){
		// sb.deleteCharAt(sb.length()-1);
		// }
		for (Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			sb.append(entry.getKey()).append("=").append(entry.getValue());
			if (iter.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * @作者:wuxh
	 * @描述:将ActionContext 里而的Parameter map转成key=value&key=value|value1|value2
	 *                    parameter若为数组的，则转换成value|value2| 格式
	 * @param contents
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	final static public String getStringByActionHashMap(Map map) {
		StringBuffer sb = new StringBuffer();
		// Iterator it = map.keySet().iterator();
		// while(it.hasNext()){
		// Object key = it.next();
		// Object value = map.get(key);
		// if(value instanceof String[]){
		// String [] values = (String[])value;
		//
		// StringBuffer subSb = new StringBuffer();
		// if(values!=null){
		// for(int i=0;i<values.length;i++){
		// subSb.append(values[i]).append("|");
		// }
		// if(subSb.length()>0){
		// subSb.deleteCharAt(subSb.length()-1);
		// }
		// }
		// sb.append(key.toString()).append("=").append(subSb.toString()).append("&");
		// }
		//
		// }
		// if(sb.length()>0){
		// sb.deleteCharAt(sb.length()-1);
		// }

		for (Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			StringBuffer subSb = new StringBuffer();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String[]) {
				String[] values = (String[]) value;
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						subSb.append(values[i]).append("|");
					}
					if (subSb.length() > 0) {
						subSb.deleteCharAt(subSb.length() - 1);
					}
				}
				sb.append(key.toString()).append("=").append(subSb.toString());
			} else {
				sb.append(key.toString()).append("=").append(value);
			}
			if (iter.hasNext()) {
				sb.append("&");
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 * @描述:将map转成key=value&key=value
	 * @param contents
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final static public String getStringByHashMap(HashMap<String, String> map, String chartset) throws Exception {
		StringBuffer sb = new StringBuffer();
		// Iterator it = map.keySet().iterator();
		// while(it.hasNext()){
		// String key = (String)it.next();
		// String value = map.get(key);
		// sb.append(key).append("=").append(URLEncoder.encode(value,
		// chartset)).append("&");
		// }
		// if(sb.length()>0){
		// sb.deleteCharAt(sb.length()-1);
		// }

		for (Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			sb.append(entry.getKey()).append("=")
					.append(URLEncoder.encode(entry.getValue() == null ? "" : entry.getValue().toString(), chartset));
			if (iter.hasNext()) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 把给定的字符串转换为Base64编码
	 * 
	 * @param source
	 *            需要进行转换的字符串
	 * @return 转换后的结果，如果source为null，则返回null
	 */
	public static String encodeBase64(String source) {
		if (source == null)
			return null;
		return new String(org.apache.commons.codec.binary.Base64.encodeBase64(source.getBytes()));
	}

	/**
	 * 把base64编码的字符串进行解码并返回原字符串
	 * 
	 * @param source
	 *            需要进行解码的Base64编码的字符串
	 * @return 解码后的原字符串，如果错误则返回null
	 */
	public static String decodeBase64(String source) {
		if (source == null)
			return null;
		try {
			return new String(Base64.decodeBase64(source));
		} catch (Exception ex) {
			return null;
		}
	}

	public static String[] split(String str, char delim) {

		if (str == null)
			return null;
		if (str.trim().equals(""))
			return new String[0];

		int count = 1;
		char c[] = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == delim) {
				count++;
			}
		}

		String s[] = new String[count];
		int index = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == delim) {
				s[index] = sb.toString();
				sb = new StringBuffer();
				index++;
			} else {
				sb.append(c[i]);
			}
		}
		s[index] = sb.toString();
		return s;
	}

	static public String decodeForwardUrl(String forwardUrl) {
		forwardUrl = StringTools.decodeBase64(forwardUrl);

		/**
		 * 以下的代码是为了让转向地址携带的参数能正常传递 如号码格式里面的 % 这些字符必须经过URLEncode才能被正常解析
		 */
		String pre = "";
		String bak = forwardUrl.substring(forwardUrl.indexOf("?") + 1, forwardUrl.length());
		String area[] = bak.split("\\&");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < area.length; i++) {
			System.out.println("area" + area[i]);
			String[] peace = area[i].split("\\=");
			if (peace.length == 2) {
				try {
					sb.append(peace[0]).append("=").append(URLEncoder.encode(peace[1], "GBK")).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else if (peace.length == 1) {
				System.out.println("Warning the empty parameter is:" + peace[0]);
				// sb.append(peace[0]).append("=").append("").append("&");
			}

		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
			pre = forwardUrl.substring(0, forwardUrl.indexOf("?") + 1);
		} else {
			// 后面没有参数，不用带 ?
			if (forwardUrl.indexOf('?') != -1) {
				pre = forwardUrl.substring(0, forwardUrl.indexOf("?"));
			}

		}

		forwardUrl = pre + sb.toString();
		return forwardUrl;
	}

	public boolean isNumber(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * zip压缩
	 * 
	 * @param data
	 *            String
	 * @return byte[]
	 */
	public static byte[] compress(byte[] data) {
		byte[] result = new byte[0];
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DeflaterOutputStream dos = new DeflaterOutputStream(baos);
			dos.write(data);
			dos.finish();
			result = baos.toByteArray();
			dos.close();
			baos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static byte[] decompress(byte[] data) {
		// byte[] result = new byte[0];
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		try {
			byte[] temp = new byte[8192];
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			int got;
			while (!inflater.finished()) {
				got = inflater.inflate(temp);
				o.write(temp, 0, got);
			}

			// int size = inflater.inflate(temp);
			// System.out.println("size:" + size + ",max limit:" + temp.length
			// );
			inflater.end();
			// result = new byte[size];
			// System.arraycopy(temp,0,result,0,size);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return o.toByteArray();
	}

	/**
	 * 通过会员账号来解析嘟嘟牛的ID
	 * 
	 * @param str
	 * @return 返回嘟嘟牛的ID
	 */
	public static Long getDudunewId(String str) {
		if (null == str || "".equals(str))
			return 0L;
		else {
			int index = str.indexOf("dudu");
			Long id = 0L;
			if (index == 0) {
				String tempStr = str.substring(4);
				try {
					id = Long.valueOf(tempStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return id;
		}

	}

	public static String getDomain(String str) {
		if (str.indexOf("http://") != 0) {
			return "";
		}
		if (str.indexOf(".com") != -1 || str.indexOf(".net") != -1 || str.indexOf(".gov") != -1
				|| str.indexOf(".org") != -1 || str.indexOf(".cn") != -1) {
			return str.substring(str.indexOf(".") + 1, str.indexOf(".", str.indexOf(".") + 1));
		} else {
			return "";
		}
	}

	/*
	 * 将汉字转换为Unicode Converts unicodes to encoded &#92;uxxxx and escapes special
	 * characters with a preceding slash
	 */
	public static String toUnicode(String theString, boolean escapeSpace, boolean escapeUnicode) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if (((aChar < 0x0020) || (aChar > 0x007e)) & escapeUnicode) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * Convert a nibble to a hex character
	 * 
	 * @param nibble
	 *            the nibble to convert.
	 */
	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	// static public void main(String[] args)throws Exception {
	// //43532
	// //5000
	// String str = "http://sesd.2caipiao.com/sss/index.jsp";
	// str =str.substring(str.indexOf(".")+1,
	// str.indexOf(".",str.indexOf(".")+1));
	// // System.out.println(str);
	//
	// //System.out.println("dudu123456".indexOf("dudu"));
	// //System.out.println("dudu123456".substring(4));
	// String[] s = {"a","b","c","dd"};
	// String[] ss = {"xx","cc","vv","bb"};
	// // s[0] = "aa";
	// // s[1] = "bb";
	// // s[2] = "cc";
	// HashMap<String,Object> map = new HashMap<String,Object>();
	// // map.put("name", s);
	// // map.put("ss", ss);
	// map.put("email", "中文");
	// map.put("ge", "xx");
	// // String st = StringTools.getStringByHashMap(map, "utf-8");
	// String st=StringTools.getStringByActionHashMap(map);
	// System.out.println(st);
	// // String url = URLEncoder.encode(null);
	// // System.out.println(url);
	// System.out.println("..................edn");
	// }

	/**
	 * 替换字符串中存在手机号码的中间几位为****
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceMobile(String str) {
		try {
			Pattern p = Pattern.compile("^(?:13\\d|15\\d|18[056789])-?\\d{5}(\\d{3}|\\*{3})$");
			Matcher m = p.matcher(str);
			if (m.matches()) {
				return str.substring(0, 3) + "****" + str.substring(7, 11);
			} else {
				return str;
			}
		} catch (Exception e) {
			return str;
		}
	}

	/**
	 * 取前length个字符，后面用...
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String cutString(String str, int length) {
		if (str == null || str.equals(""))
			return str;
		int strLen = str.length();
		if (strLen - 1 < length) {
			return str;
		}
		try {
			return str.substring(0, length) + "...";
		} catch (Exception e) {
			return str;
		}
	}

	public static boolean compareString(String str1, String str2) {
		if (str1 == null)
			str1 = "";
		;

		if (str2 == null)
			str2 = "";
		;
		return !str1.equals(str2);
	}

	/**
	 * 支付网关加密规则：不为空的数据加密 按照参数传递顺序加密，各应用只需调用此方法
	 * 
	 * @param channel
	 *            充值渠道，不为空
	 * @param sellclient
	 *            充值客户端，不为空
	 * @param payAccount
	 *            被充值账户，不为空
	 * @param byAccount
	 *            充值账户 不为空
	 * @param failUrl
	 *            提交失败返回URL,不为空
	 * @param sucUrl
	 *            提交成功返回URL,不为空
	 * @param amount
	 *            充值成功金额 不为空
	 * @param cardNum1
	 *            卡号 可空
	 * @param cardNum2
	 *            卡号 可空
	 * @param bank
	 *            银行名称 可空
	 * @param doCredit
	 *            可空
	 * @param cardType
	 *            卡类型，可空
	 * @param mobile
	 *            移动号码 可空
	 * @param bankCard
	 *            银行卡号 可空
	 * @return
	 */
	public static String getPaymentMD5Encode(ChannelProxy proxy, ClientType sellclient, String payAccount,
			String byAccount, String failUrl, String sucUrl, double amount, String cardNum1, String cardNum2,
			String bank, String doCredit, String cardType, String mobile, String bankCard, String bankco) {
		StringBuffer md5Data = new StringBuffer();
		md5Data.append(proxy.getDescription());
		md5Data.append(sellclient.getDescription());
		md5Data.append(payAccount);
		md5Data.append(byAccount);
		if (!StringUtils.isBlank(failUrl))
			md5Data.append(failUrl);
		if (!StringUtils.isBlank(sucUrl))
			md5Data.append(sucUrl);
		md5Data.append(amount);

		if (!StringUtils.isBlank(cardNum1))
			md5Data.append(cardNum1);

		if (!StringUtils.isBlank(cardNum2))
			md5Data.append(cardNum2);

		if (!StringUtils.isBlank(bank))
			md5Data.append(bank);

		if (!StringUtils.isBlank(doCredit))
			md5Data.append(doCredit);

		if (!StringUtils.isBlank(cardType))
			md5Data.append(cardType);

		if (!StringUtils.isBlank(mobile))
			md5Data.append(mobile);

		if (!StringUtils.isBlank(bankCard))
			md5Data.append(bankCard);

		if (!StringUtils.isBlank(bankco))
			md5Data.append(bankco);

		md5Data.append(SystemConstants.INNER_PAYMENT_KEY);
		return MD5.md5Encode(md5Data.toString());	//wait for check
	}

	/**
	 * 将request中的参数map转化成普通的map
	 * 
	 * @param parm
	 * @return
	 */
	public static Map<String, String> getGMap(final Map<String, String[]> parm) {
		Map<String, String> parmMap = new HashMap<String, String>();
		for (String s : parm.keySet()) {
			String[] values = parm.get(s);
			parmMap.put(s, values[0]);
		}
		return parmMap;
	}

	/**
	 * 支付网关URL地址构造
	 * 
	 * @param channel
	 *            充值渠道，不为空
	 * @param sellclient
	 *            充值客户端，不为空
	 * @param payAccount
	 *            被充值账户，不为空
	 * @param byAccount
	 *            充值账户 不为空
	 * @param failUrl
	 *            提交失败返回URL,不为空
	 * @param sucUrl
	 *            提交成功返回URL,不为空
	 * @param amount
	 *            充值成功金额 不为空
	 * @param cardNum1
	 *            卡号 可空
	 * @param cardNum2
	 *            卡号 可空
	 * @param bank
	 *            银行名称 可空
	 * @param doCredit
	 *            可空
	 * @param cardType
	 *            卡类型，可空
	 * @param mobile
	 *            移动号码 可空
	 * @param bankCard
	 *            银行卡号 可空
	 * @return
	 */
	public static String getPaymentGateWay(ChannelProxy proxy, ClientType sellclient, String payAccount,
			String byAccount, String failUrl, String sucUrl, double amount, String cardNum1, String cardNum2,
			String bank, String doCredit, String cardType, String mobile, String bankCard, String bankco) {
		StringBuffer urlsdb = new StringBuffer();
		try {
			urlsdb.append("channel=").append(proxy.getDescription()).append("&");
			urlsdb.append("sellclient=").append(sellclient.getDescription()).append("&");
			urlsdb.append("payAccount=").append(URLEncoder.encode(payAccount, SystemConstants.GOBAL_ENCODE))
					.append("&");
			urlsdb.append("byAccount=").append(URLEncoder.encode(byAccount, SystemConstants.GOBAL_ENCODE)).append("&");

			if (!StringUtils.isBlank(failUrl))
				urlsdb.append("failUrl=").append(failUrl).append("&");
			if (!StringUtils.isBlank(sucUrl))
				urlsdb.append("sucUrl=").append(sucUrl).append("&");
			urlsdb.append("amount=").append(amount).append("&");

			if (!StringUtils.isBlank(cardNum1))
				urlsdb.append("cardNum1=").append(cardNum1).append("&");

			if (!StringUtils.isBlank(cardNum2))
				urlsdb.append("cardNum2=").append(cardNum2).append("&");

			if (!StringUtils.isBlank(bank))
				urlsdb.append("bank=").append(URLEncoder.encode(bank, SystemConstants.GOBAL_ENCODE)).append("&");

			if (!StringUtils.isBlank(doCredit))
				urlsdb.append("doCredit=").append(doCredit).append("&");

			if (!StringUtils.isBlank(cardType))
				urlsdb.append("cardType=").append(cardType).append("&");

			if (!StringUtils.isBlank(mobile))
				urlsdb.append("mobile=").append(mobile).append("&");

			if (!StringUtils.isBlank(bankCard))
				urlsdb.append("bankCard=").append(bankCard).append("&");

			if (!StringUtils.isBlank(bankco))
				urlsdb.append("bankco=").append(bankco).append("&");

			String md5Sign = getPaymentMD5Encode(proxy, sellclient, payAccount, byAccount, failUrl, sucUrl, amount,
					cardNum1, cardNum2, bank, doCredit, cardType, mobile, bankCard, bankco);

			urlsdb.append("sign=").append(md5Sign);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlsdb.toString();
	}

	public static String Md5Sign(Map<String, String> sourceMap, String privateKey) {
		if (sourceMap == null || sourceMap.isEmpty()) {
			return null;
		}
		List<String> keys = new ArrayList<String>(sourceMap.keySet());
		Collections.sort(keys);
		StringBuffer sbf = new StringBuffer(privateKey);
		for (String key : keys) {
			sbf.append(sourceMap.get(key));
		}
		String sourceStr = sbf.toString();
		return MD5.md5Encode(sourceStr);	//TODO wait for check
	}
	
	public static String getMd5SourceSignStr(Map<String, String> sourceMap) {
		if (sourceMap == null || sourceMap.isEmpty()) {
			return null;
		}
		List<String> keys = new ArrayList<String>(sourceMap.keySet());
		Collections.sort(keys);
		StringBuffer sbf = new StringBuffer();
		for (String key : keys) {
			sbf.append(sourceMap.get(key));
		}
		String sourceStr = sbf.toString();
		return sourceStr;
	}
}
