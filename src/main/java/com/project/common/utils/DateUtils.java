package com.project.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期-工具类
 *
 * @author syl
 */
public class DateUtils {

    /**
     * 日期格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式(yyyyMMddHHmmss)
     */
    public final static String FILE_DATE_FORMAT = "yyyyMMddHHmmss";
    /**
     * 日期格式(yyyy-MM-dd)
     */
    public final static String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期格式(yyyy-MM-dd)
     */
    public final static String SHORT_DATE_FORMAT_ZH = "yyyy年MM月dd日";
    /**
     * 日期格式(HH:mm)
     */
    public final static String HOUR_MIN_FORMAT = "HH:mm";

    /**
     * 日期类型(天数)
     */
    public static final String DATE_TYPE_DAY = "day";
    /**
     * 日期类型(小时)
     */
    public static final String DATE_TYPE_HOUR = "hour";
    /**
     * 日期类型(分钟)
     */
    public static final String DATE_TYPE_MIN = "min";
    /**
     * 日期类型(毫秒)
     */
    public static final String DATE_TYPE_SEC = "sec";

    /**
     * 获取格式化日期
     *
     * @param date    日期对象
     * @param pattern 格式模版
     * @return
     */
    public static String getDateFormat(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.format(date);
        } catch (Exception e) {
            return "";
        }


    }

    public static Date parseToDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//小写的mm表示的是分钟
        return sdf.parse(date);
    }

    /**
     * 获取日期相差
     *
     * @param sdate 开始时间
     * @param edate 结束时间
     * @param type  返回类型
     * @return
     */
    public static long getDateDiffer(Date sdate, Date edate, String type) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;

        long diff = edate.getTime() - sdate.getTime();
        if (type.equals(DATE_TYPE_DAY)) {
            return diff / nd;
        } else if (type.equals(DATE_TYPE_HOUR)) {
            return diff / nh;
        } else if (type.equals(DATE_TYPE_MIN)) {
            return diff / nm;
        } else if (type.equals(DATE_TYPE_SEC)) {
            return diff / ns;
        } else {
            return -1;
        }
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat(FILE_DATE_FORMAT);
        String s = outFormat.format(now);
        return s;
    }
}