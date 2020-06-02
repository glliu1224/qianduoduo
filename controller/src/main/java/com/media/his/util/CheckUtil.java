package com.media.his.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoyi.xie
 * @version 1.0
 * @date 2018/6/29
 * @desc 检查工具类
 * @see
 * @since 1.0
 */
public class CheckUtil {

    /**
     * 判断字符串长度(字符串全为空格的为false)
     */
    public static Boolean checkStrLength(String str, Integer min, Integer max) {
        return (!isEmpty(str) && str.length() >= min && str.length() <= max);
    }

    /**
     * 判断字符串是不是数字
     */
    public static Boolean checkNumber(String str) {
        return !isEmpty(str) && str.matches("^\\d+$");
    }

    public static Boolean checkNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return !isEmpty(str) && pattern.matcher(str).matches();
    }

    public static Boolean checkDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return !isEmpty(str) && pattern.matcher(str).matches();
    }

    /**
     * 判断数组是否全部为空
     */
    public static Boolean isAllEmpty(Object[] o) {
        if (o == null) {
            return true;
        }

        for (Object tmp : o) {
            if (!isEmpty(tmp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是不是null或无字符（trim后）
     */
    public static Boolean isEmpty(String o) {
        return (o == null || o.equals("") || o.trim().length() == 0);
    }

    /**
     * 判断Double是否为null或0
     */
    public static Boolean isEmpty(Double value) {
        return (value == null || value == 0);
    }

    /**
     * 判断整形是否为null或0
     */
    public static Boolean isEmpty(Integer value) {
        return (value == null || value == 0);
    }

    /**
     * 判断整形是否为null或0
     */
    public static Boolean isEmpty(Long value) {
        return (value == null || value == 0);
    }

    /**
     * 判断List是否为空
     */
    public static Boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断Map是否为空
     */
    public static Boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 判断Set是否为空
     */
    public static Boolean isEmpty(Set<?> set) {
        return (set == null || set.size() == 0);
    }

    /**
     * 判断Object是否为空
     */
    public static Boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }

        boolean isEmpty = false;
        if (o instanceof String) {
            isEmpty = isEmpty((String) o);
        } else if (o instanceof Integer) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof List<?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Map<?, ?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Set<?>) {
            isEmpty = isEmpty((Integer) o);
        }

        return isEmpty;
    }

    /**
     * 判断数组是否为空
     */
    public static Boolean isEmpty(Object[] o) {
        return (o == null || o.length == 0);
    }

    /**
     * 根据指定的正则表达式验证字符串
     *
     * @param regex 正则表达式
     * @param str   检验内容
     */
    public static Boolean checkRegex(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断Object数组中的值是否为空,只要其中有一个为空就返回true<br/>
     * Integer为0会判断为空
     */
    public static boolean oneMoreEmpty(Object[] o) {
        boolean b = false;

        for (int i = 0; i < o.length; i++) {
            if (isEmpty(o[i])) {
                b = true;
                break;
            }
        }

        return b;
    }


    /**
     * 字符串长度校验
     *
     * @param s     被校验字符串
     * @param limit 最大限制常量
     * @return
     */
    public static boolean beyondLimit(String s, Integer limit) {
        if (s.length() > limit) {
            return true;
        }
        return false;
    }

}