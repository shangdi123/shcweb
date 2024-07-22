/* ******************************************************
 * Copyright (C) 2016 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-api
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential
 *
 * @Date: 2017/12/22
 * @Author: zhaoxu <zhaoxu@qiyi.com>
 * ******************************************************/
package com.hyl.qixiao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter YMD_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter YMDHMS_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final ThreadLocal<DateFormat> yyyyMMddFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Date plus(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static void main(String[] args) throws Exception {
//        Date start = toDate(LocalDateTime.parse("2020-12-24 12:00:00", TIME_FORMATTER));
//        System.out.println(dateDiffDays(start, new Date()));
//        System.out.println(8 % 5);
//        int p = (int)Math.ceil(9 / 3.0);
//        System.out.println(p);
//        System.out.println(8/3);
//        System.out.println(getStartOfDay(new Date(), -1));
        long firstStageDays =
                DateUtils.dateDiffDays(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                        "2021-01-13 20:00:00"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                                "2021-01-25 20:00:00"));
        System.out.println(firstStageDays);
    }

    public static long dateDiffDays(Date startDate, Date endDate){
        return (endDate.getTime() - startDate.getTime()) / (24*60*60*1000);
    }

    public static long getStartOfDay(long date, int offset) {
        return getStartOfDayCalendar(date, offset).getTimeInMillis();
    }

    public static Calendar getStartOfDayCalendar(long date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Date getStartOfDay(Date date, int offset) {
        return getStartOfDayCalendar(date.getTime(), offset).getTime();
    }

    public static Date getEndOfDay(Date date, int offset) {
        return getEndOfDayCalendar(date.getTime(), offset).getTime();
    }

    public static Calendar getEndOfDayCalendar(long date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    public static List<Date> getDateGapExcludeMinDateIncludeMaxDate(Date date1, Date date2) {
        List<Date> dateGap = new ArrayList<>();
        Date minDate, maxDate;
        if(date1.before(date2)) {
            minDate = date1;
            maxDate = date2;
        } else {
            minDate = date2;
            maxDate = date1;
        }

        while (maxDate.after(minDate)) {
            dateGap.add(maxDate);
            maxDate = DateUtils.getStartOfDay(maxDate, -1);
        }
        return dateGap;
    }

    public static String parseDateToYMD(LocalDate localDate){
        return localDate.format(YMD_TIME_FORMATTER);
    }

    public static String parseDateToYMDHMS(LocalDateTime localDateTime) {
        return localDateTime.format(YMDHMS_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(Date input) {
        return input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String parseDate(Date date) {
        return TIME_FORMATTER.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    public static Date toDate(LocalDateTime input) {
        return Date.from(input.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date pareDate(String dateStr) {
        return toDate(LocalDateTime.parse(dateStr, TIME_FORMATTER));
    }

    public static Calendar getCalendar(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static int getWeekDay(long date) {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekDay(Date date) {
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }
}
