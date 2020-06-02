package com.media.his.util;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;

/**
 * @author wenwen.zhang
 * @version 1.0
 * @date 2018/6/4
 * @desc 字符串工具类
 * @see
 * @since 1.0
 */
public class StringUtil {

    /**
     * 空字符
     */
    public static final String EMPTY = "";

    /**
     * 正则校验手机号
     */

    public final static String regExp = "^1\\d{10}$";

    /**
     * <p> 拼接字符串第二个字符串第一个字母大写 </p>
     */
    public static String concatCapitalize(String concatStr, final String str) {
        if (isEmpty(concatStr)) {
            concatStr = EMPTY;
        }
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return str;
        }

        StringBuilder sb = new StringBuilder(strLen);
        sb.append(concatStr);
        sb.append(Character.toTitleCase(firstChar));
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * 字符串是否为空 默认情况下使用这个 当需要空格也过滤掉时，调用方法isEmpty("字符串", true);
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return isEmpty(str, false);
    }

    /**
     * 字符串是否为空
     *
     * @param str              字符串
     * @param ignoreWhitespace 是否认为空格不算字符。比如, "   "也算空串
     * @return 是否空串
     */
    public static boolean isEmpty(CharSequence str, boolean ignoreWhitespace) {
        return ignoreWhitespace ? !StringUtils.hasText(str) : StringUtils.isEmpty(str);
    }

    /**
     * 字符串是否不为空 默认情况下使用这个 当需要空格也过滤掉时，调用方法isEmpty("字符串", true);
     */
    public static boolean notEmpty(CharSequence str) {
        return !isEmpty(str, false);
    }

    /**
     * 转换为大写
     *
     * @param str
     * @return
     */
    public static String upperCase(CharSequence str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.toString().toUpperCase();
    }


    /**
     * 微信支付生成随机字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 兑换码【规则是：8位大写的英文字母，出去I和O】
     */
    public static String getRandomStringByVoucher(int length) {
        String base = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 格式化短信内容
     *
     * @param content
     * @param dataJson
     * @return
     */
    public static String formatSmsContent(String content, Map<String, Object> dataJson) {
        if (dataJson == null || dataJson.size() == 0) {
            return content;
        }
        String resultContent = new String(content);
        for (String key : dataJson.keySet()) {
            if (!resultContent.contains("${" + key + "}")) {
                continue;
            }
            resultContent = resultContent.replaceAll("\\$\\{" + key + "\\}", String.valueOf(dataJson.get(key)));
        }
        return resultContent;
    }


}