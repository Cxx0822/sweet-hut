package com.sweethut.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 日期工具类
 */
public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取上周一的日期
     *
     * @return 日期
     */
    public static String getLastMonday() {
        Calendar cal = Calendar.getInstance();

        // 设置周一为第一天
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 日期减去7天
        cal.add(Calendar.DATE, -7);
        // 时间设置到周一，此时时间为周一的日期
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * 获取上周日的日期
     *
     * @return 日期
     */
    public static String getLastSunday() {
        Calendar cal = Calendar.getInstance();

        // 设置周一为第一天
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 日期减去7天
        cal.add(Calendar.DATE, -7);
        // 时间设置到周日，此时时间为周日的日期
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * 获取今天日期
     *
     * @return 日期
     */
    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取昨天日期
     *
     * @return 日期
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        // 日期减去1天
        calendar.add(Calendar.DATE, -1);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前星期的周日
     *
     * @return 当前星期的周日
     */
    public static String getWeekend(String weekStart) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(weekStart));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // 日期加上7天
        calendar.add(Calendar.DATE, 6);
        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 获取当前月份
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getCurrentMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        return month + 1;
    }

    /**
     * 根据年份获取年份下所有月份
     *
     * @param year 年份
     * @return 月份列表
     */
    public static List<String> getYearFullMonth(String year) {
        List<String> monthList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, 0);
        for (int i = 0; i < 12; i++) {
            monthList.add(sdf.format(cal.getTime()));
            cal.add(Calendar.MONTH, 1);
        }

        return monthList;
    }

    /**
     * 判断当前日期是否是指定月份
     * @param currentDate 当前日期
     * @param desMonth 目标月份
     * @return 是否满足
     */
    public static boolean isSameMonth(Date currentDate, String desMonth) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentMonth = sdf.format(currentDate);

        return currentMonth.equals(desMonth);
    }

    /**
     * 判断2个日期是否相等
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否相等
     */
    public static boolean isSameDate(Date date1, Date date2) {
        return simpleDateFormat.format(date1).equals(simpleDateFormat.format(date2));
    }

    /**
     * 获取2个日期的时间差 单位：分钟
     *
     * @param startDate 开始时间
     * @param endDate   终止时间
     * @return 时间差
     */
    public static double getDateDiff(Date startDate, Date endDate) {
        // 获得两个时间的毫秒时间差异
        long millisecondDiff = endDate.getTime() - startDate.getTime();
        double minutes = (double) millisecondDiff / 1000 / 60;
        return Math.round(minutes * 100) * 0.01;
    }
}

