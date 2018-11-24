package com.imp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {

    public static final String DEFAULT_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    public static String getFormatTime(Date date){
        return new SimpleDateFormat(DEFAULT_FORMAT_STRING).format(date);
    }
    public static String toDefaultDateString(Date date) {
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        format.format(date);
        return format.format(date);
    }
    public static String getDateString(Date date) {
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE);
        format.format(date);
        return format.format(date) + " 00:00:00";
    }
    public static String getDateTimeString(Date date) {
        if(date == null){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        format.format(date);
        return format.format(date);
    }
    /**
     * 两个时间之间相差距离多少天
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2){
        DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    public static Date string2Date(String datetime) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两个时间之间相加减时间
     * @param time 时间参数 ：
     * @param timeMillis 加减时间 ：
     * @return 相加时间结果
     */
    public static String plusTimeMillis(String time, Long timeMillis){
        String result = null;
        try {
            result = toDefaultDateString(new Date(string2Date(time).getTime() + timeMillis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
