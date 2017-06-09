package com.guanshaoye.mylibrary.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    /**
     * 获取当前日期的时间差 2015-12-12格式时间比较
     *
     * @param dateOld
     * @return
     * @throws ParseException
     */
    public static String getTime(String dateOld) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1;
        try {
            d1 = sdf.parse(dateOld);
            Date d2 = sdf.parse(sdf.format(new Date()));

            int days = daysBetween(d1, d2);
            if (days / 365 > 0) {
                return days / 365 + "年前";
            } else if (days / 30 > 0) {
                return days / 30 + "个月前";
            } else {
                return days + "天前";
            }
        } catch (ParseException e) {
            return "未知时间";
        }
    }
    public static String changeMonth(int month) {
        String[] months = { "", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
                "九月", "十月", "十一月", "十二月" };
        return month > 0 && month < 13 ? months[month] : months[0];
    }
    /**
     * 获取当前日期差
     *
     * @param dateOld
     * @return
     * @throws ParseException
     */
    public static String getHour(String dateOld) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(dateOld);
            Date d2 = sdf.parse(sdf.format(new Date()));

            int days = daysBetween(d1, d2);
            if (days / 365 > 0) {
                return days / 365 + "年前";
            } else if (days / 30 > 0) {
                return days / 30 + "个月前";
            } else {
                if (days <= 0) {
                    return "今天";
                }
                return days + "天前";
            }
        } catch (ParseException e) {
            return "今天";
        }
    }

    /**
     * 执业年限
     *
     * @param dateOld
     * @return
     * @throws ParseException
     */
    public static String getWorkAge(String dateOld) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1;
        try {
            d1 = sdf.parse(dateOld);
            Date d2 = sdf.parse(sdf.format(new Date()));
            int days = daysBetween(d1, d2);
            if (days / 365 > 80) {
                return "执业年限未知";
            }
            if (days / 365 > 0 && days / 365 < 80) {
                return "执业" + days / 365 + "年";
            } else if (days / 30 > 0) {
                return "执业" + days / 30 + "个月";
            } else {
                return "新手上任";
            }
        } catch (ParseException e) {
            return "执业年限未知";
        }

    }

    /**
     * 执业年限
     *
     * @param dateOld
     * @return
     * @throws ParseException
     */
    public static String getWorkAge2(String dateOld) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d1;
        try {
            d1 = sdf.parse(dateOld);
            Date d2 = sdf.parse(sdf.format(new Date()));
            int days = daysBetween(d1, d2);
            if (days / 365 > 80) {
                return "执业年限未知";
            }
            if (days / 365 > 0 && days / 365 < 80) {
                return "执业" + days / 365 + "年";
            } else if (days / 30 > 0) {
                return "执业" + days / 30 + "个月";
            } else {
                return "新手上任";
            }
        } catch (ParseException e) {
            return "执业年限未知";
        }

    }

    /**
     * 评论时间
     *
     * @param dateOld
     * @return
     * @throws ParseException
     */
    public static String getSecond(String dateOld) {
        try {
            SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begin = dfs.parse(dateOld);
            Date end = dfs.parse(dfs.format(new Date()));

            long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            long day = between / (24 * 3600);
            long hour = between % (24 * 3600) / 3600;
            long minute = between % 3600 / 60;
            // long second = between % 60 / 60;
            if (day / 365 > 0) {
                return day / 365 + "年前";
            }
            if (day / 30 > 0) {
                return day / 30 + "个月前";
            }
            if (day > 0) {
                return day + "天前";
            }

            if (hour > 0) {
                return hour + "小时前";
            }
            if (hour > 0) {
                return day + "天前";
            }
            if (minute > 0) {
                return minute + "分钟前";
            } else {
                return "刚刚";
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "刚刚";

    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2012-09-08");

        Date d2 = sdf.parse(sdf.format(new Date()));
        System.out.println(daysBetween(d1, d2));
        System.out.println(daysBetween("2012-09-08", "2012-09-15"));

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * Date d1 = sdf.parse("2012-09-08 10:10:10"); Date d2 =
		 * sdf.parse("2012-09-15 00:00:00"); System.out.println(daysBetween(d1,
		 * d2)); System.out.println(daysBetween("2012-09-08 10:10:10",
		 * "2012-09-15 00:00:00"));
		 */
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}
