package com.aicai.jcob.member.manager.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 */
public class RegexUtils {
    public static boolean vd(String str) {
        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;
                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;
    }

    /**
     * 鏄惁鏄眽瀛?
     * @param words 杈撳叆瀛楃涓?
     * @return
     */
    public static boolean isChinese(String words) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(words);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }
        if (words.length() == count) {
            return true;
        }
        return false;
    }

    /**
     * 楠岃瘉鏄惁鏄韩浠借瘉鍙?
     *
     * @param idcard
     * @return
     */
    public static boolean isIdCard(String idcard) {
        boolean IS_IDCARD = false;
        if (StringUtils.isBlank(idcard) || (idcard.length() != 15 && idcard.length() != 18)) {
            return IS_IDCARD;
        }
        String regex = "";
        // 鍒ゆ柇鏄惁鏄?15浣嶈韩浠借瘉鍙?
        if (idcard.length() == 15) {
            regex = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
            IS_IDCARD = match(regex, idcard);
        } else if (idcard.length() == 18) {
            regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
            IS_IDCARD = match(regex, idcard);
        }
        if (!IS_IDCARD)
            return IS_IDCARD;

        IS_IDCARD = false;
        int[] monthPerDays = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        String certid = idcard.toLowerCase();
        String birthDate = certid.length() == 15 ? "19" + certid.substring(6, 12) : certid.substring(6, 14);
        String year = birthDate.substring(0, 4);
        String month = null;
        String day = null;
        if (birthDate.substring(4, 5).equals("0"))
            month = birthDate.substring(5, 6);
        else
            month = birthDate.substring(4, 6);
        if (birthDate.substring(6, 7).equals("0"))
            day = birthDate.substring(7, 8);
        else
            day = birthDate.substring(6, 8);
        int dd = Integer.parseInt(day);
        int mm = Integer.parseInt(month);
        int yy = Integer.parseInt(year);
        if (mm > 12 || mm < 1 || dd > 31 || dd < 1)
            return IS_IDCARD;

        if (yy % 100 != 0) {
            if (yy % 4 == 0)
                monthPerDays[1] = 29;
        } else {
            if (yy % 400 == 0)
                monthPerDays[1] = 29;
        }
        if (monthPerDays[mm - 1] < dd)
            return IS_IDCARD;

        if (certid.length() == 15)
            return true;

        int[] arTemp = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        int num = 0;
        String proof = null;
        for (int i = 0; i < 17; i++) {
            num = num + Integer.parseInt(certid.substring(i, i + 1)) * arTemp[i];
        }
        num = num % 11;
        switch (num) {
        case 0:
            proof = "1";
            break;
        case 1:
            proof = "0";
            break;
        case 2:
            proof = "x";
            break;
        case 3:
            proof = "9";
            break;
        case 4:
            proof = "8";
            break;
        case 5:
            proof = "7";
            break;
        case 6:
            proof = "6";
            break;
        case 7:
            proof = "5";
            break;
        case 8:
            proof = "4";
            break;
        case 9:
            proof = "3";
            break;
        case 10:
            proof = "2";
            break;
        }
        if (StringUtils.isBlank(proof) || !certid.substring(17, 18).equals(proof)) {
            return IS_IDCARD;
        } else {
            return true;
        }
    }

    /**
     * 楠岃瘉鏄惁鏄數璇濆彿鐮?
     *
     * @param str
     * @return
     */
    public static boolean isphoneNum(String str) {
        String regex = "(\\d{11})$|^(\\d{3,5}[-]?\\d{6,8})$";// 鎵嬫満,鐢佃瘽
        return match(regex, str);
    }

    /**
     * 楠岃瘉姝ｅ垯琛ㄨ揪寮忔槸鍚﹀悎娉?
     *
     * @param regex
     * @param str
     * @return
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isEmail(String str) {
        // 鍒ゆ柇鏄惁涓洪偖绠卞湴鍧?
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }
    /**
     * 姝ゆ柟娉曞彧杩斿洖group涓?1鐨勫唴瀹?
     * @param regex
     * @param str
     * @return
     */
    public static String getRegexGroupContent(String regex, String str) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
    /**
     * 瀵圭敤鎴峰悕鍖呭惈杩炵画5涓暟瀛楃殑瀛楃涓?(闀垮害涓簂en)杞崲鎴恖en-4涓瓧绗?+4涓?*杩涜灞忚斀
     * @param account
     * @return
     * @create_time 2011-2-28 涓婂崍09:25:10
     */
    public static String regExpReplace(String account) {
        if (StringUtils.isBlank(account)) {
            return account;
        }
        String accountReg = account.replaceAll("([\\d]{5,})", "^$0#");
        int indexBegin = accountReg.indexOf("^");
        int indexEnd = accountReg.indexOf("#");
        StringBuffer sb = new StringBuffer();
        if (indexBegin != -1 && indexEnd != -1) {
            sb.append(accountReg.substring(0, indexBegin));
            sb.append(accountReg.substring(indexBegin + 1, indexEnd- 4));
            sb.append("****");
            sb.append(accountReg.substring(indexEnd+1));
            return sb.toString();
        }
        return account;
    }
}

