package com.project.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 字符-工具类
 *
 * @author gpy
 */
public class StrUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true:字符串为空，false:字符串非空
     * @author lss
     * @date 2017-7-6
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0)
                || "null".equals(str) || "".equals(str));
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true:字符串为空，false:字符串非空
     * @author lss
     * @date 2017-7-6
     */
    public static boolean isNotEmpty(String str) {
        return !StrUtils.isEmpty(str);
    }

    /**
     * 首字母大写
     *
     * @param target 目标字符
     * @return
     */
    public static String toUpperCase(String target) {
        if (!Character.isUpperCase(target.charAt(0))) {
            StringBuilder builder = new StringBuilder(target);
            builder.setCharAt(0, Character.toUpperCase(target.charAt(0)));
            target = builder.toString();
        }
        return target;
    }

    /**
     * 首字母大写(前缀条件)
     *
     * @param prefix 前缀条件
     * @param target 目标字符
     * @return
     */
    public static String toUpperCase(String prefix, String target) {
        String[] targets = target.split(prefix);
        StringBuffer sb = new StringBuffer();
        for (String key : targets) {
            if (StringUtils.isNotBlank(key)) {
                sb.append(StrUtils.toUpperCase(key));
            }
        }
        return sb.toString();
    }

    /**
     * 首字母小写
     *
     * @param target 目标字符
     * @return
     */
    public static String toLowerCase(String target) {
        if (!Character.isLowerCase(target.charAt(0))) {
            StringBuilder builder = new StringBuilder(target);
            builder.setCharAt(0, Character.toLowerCase(target.charAt(0)));
            target = builder.toString();
        }
        return target;
    }

    /**
     * 获取唯一标识
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将金额字符串保留两位小数
     *
     * @author lss
     * @date 2017-8-9
     * @return
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    public static String getmoney(String amount) throws Exception {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }


    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     * @author lss
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * String转换Map
     *
     * @param mapText :需要转换的字符串
     * @return Map<?, ?>
     */
    public static Map<String, Object> StringToMap(String mapText) {
        if (mapText == null || mapText.equals("")) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        String[] text = mapText.split("\\" + "&"); // 转换为数组
        for (String str : text) {
            String[] keyText = str.split("="); // 转换key与value的数组
            if (keyText.length < 1) {
                continue;
            }
            String key = keyText[0]; // key
            String value = keyText[1]; // value
            if (value.charAt(0) == 'M') {
                Map<?, ?> map1 = StringToMap(value);
                map.put(key, map1);
            } else if (value.charAt(0) == 'L') {
                List<?> list = StringToList(value);
                map.put(key, list);
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * String转换List
     *
     * @param listText :需要转换的文本
     * @return List<?>
     */
    public static List<Object> StringToList(String listText) {
        if (listText == null || listText.equals("")) {
            return null;
        }
        listText = listText.substring(1);
        List<Object> list = new ArrayList<Object>();
        String[] text = listText.split("\\" + ",");
        String listStr = "";
        boolean flag = false;
        for (String str : text) {
            if (!str.equals("")) {
                if (str.charAt(0) == 'M') {
                    Map<?, ?> map = StringToMap(str);
                    list.add(map);
                } else if (str.charAt(0) == 'L' || flag) {
                    flag = true;
                    listStr += str + ",";
                } else {
                    list.add(str);
                }
            }
            if (str.equals("")) {
                flag = false;
                List<?> lists = StringToList(listStr);
                list.add(lists);
            }
        }
        return list;
    }

    private static final String mobile_regex = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$";

    /**
     * 验证手机号是否合 ?
     *
     * @param value
     * @return true:合法手机号，false:非法手机 ?
     * @author wq
     * @date 2015-8-17
     */
    public static boolean isMobile(String value) {
        if (value == null || "".equals(value)) {
            return false;
        }
        return value.matches(mobile_regex);
    }
}