package com.qiaosong.baselibrary.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String getFormatDateFileName() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(c.getTime());
    }

    public static String getFormatDateYMD() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(c.getTime());
    }

    public static String getFormatDateYMDHMS() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(c.getTime());
    }

    public static String getFormatDateHM() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("HH：mm ");
        return format.format(c.getTime());
    }

    /**
     * 获取现在年份
     *
     * @return
     */
    public static int getFormatDateYear() {
        Calendar c = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(c.getTime()));
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 转化为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStrToStrYMD(String dateStr) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 转化为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStrToStrYMDHM(String dateStr) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 转化为指定格式
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStrToStr(String dateStr, String formatKey) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format2 = new SimpleDateFormat(formatKey);
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }

    /**
     * 将yyyy-MM-dd转化为指定格式
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStrToStr2(String dateStr, String formatKey) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat(formatKey);
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }


    /**
     * 将任意格式转化为指定格式
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStrToStrAny(String dateStr, String anyKey, String formatKey) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(anyKey);
            SimpleDateFormat format2 = new SimpleDateFormat(formatKey);
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }

    /**
     * 计算2个时间戳相差小时数
     *
     * @param startLong
     * @param endLong
     * @return
     */
    public static String getHourFromLong(long startLong, long endLong) {
        if (endLong < startLong) {
            return "00";
        }
        int hour = (int) ((endLong - startLong) / 1000 / 60 / 60);
        if (hour >= 10)
            return hour + "";
        else
            return "0" + hour;
    }

    /**
     * 计算2个时间戳相差分钟数  满1小时清0
     *
     * @param startLong
     * @param endLong
     * @return
     */
    public static String getMinuteFromLong(long startLong, long endLong) {
        if (endLong < startLong) {
            return "00";
        }
        int min = (int) ((endLong - startLong) / 1000 / 60 % 60);
        if (min >= 10)
            return min + "";
        else
            return "0" + min;
    }

    /**
     * 计算2个时间戳相差小时数
     *
     * @param startLong
     * @param endLong
     * @return
     */
    public static String getSecondFromLong(long startLong, long endLong) {
        if (endLong < startLong) {
            return "00";
        }
        int second = (int) ((endLong - startLong) / 1000 % 60);
        if (second >= 10)
            return second + "";
        else
            return "0" + second;
    }

    /**
     * 将时间戳 转化为指定格式
     *
     * @param date
     * @param formatKey
     * @return
     */
    public static String getFormatLongToStr(long date, String formatKey) {
        DateFormat format = new SimpleDateFormat(formatKey);
        return format.format(date);
    }

    /**
     * 根据year获取年龄
     *
     * @return
     */
    public static String getAgeByYear(String year) {
        String dateString = "";
        if (TextUtils.isEmpty(year)) {
            return dateString;
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                int yearBirth = Integer.parseInt(year);
                int yearNow = Integer.parseInt(format.format(new Date()));
                dateString = (yearNow - yearBirth + 1) + "岁";
            } catch (Exception e) {

            }
            return dateString;
        }
    }

    /**
     * 将yyyy-MM-dd 转化为指定格式
     *
     * @param dateStr
     * @return
     */
    public static String getFormatYMDStrToStr(String dateStr, String formatKey) {
        String dateString = "";
        if (TextUtils.isEmpty(dateStr)) {
            return dateString;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat(formatKey);
            try {
                dateString = format2.format(format.parse(dateStr));
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return dateString;
        }
    }

    /**
     * 将yyyy-MM-dd转化为Date
     *
     * @param dateStr
     * @return
     */
    public static Date getFormatToDate(String dateStr) {
        Date date = null;
        if (TextUtils.isEmpty(dateStr)) {
            return date;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(dateStr);
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
            return date;
        }
    }

    /***
     *str转long
     * @param dateStr
     * @return
     */
    public static long formatDateToLong(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return 0;
        }
        Date d = null;
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            d = sf.parse(dateStr);
        } catch (Exception e) {
        }
        return d == null ? 0 : d.getTime();
    }

    /**
     * 计算2日期相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {   //不同年
            return day2 - day1;
        }
    }

    /**
     * 判断yyyy-MM-dd 时间是否已过
     *
     * @param overTime
     * @return
     */
    public static boolean isOverTime(String overTime) {
        if (TextUtils.isEmpty(overTime))
            return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        Date now = null;
        try {
            time = sdf.parse(overTime);
            now = sdf.parse(sdf.format(new Date()));
            if (time.before(now)) { //time时间是否在当前时间之前，为true则time时间已过
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 将时间戳 转化为年月日
     *
     * @param date
     * @return
     */
    public static String getFormatLongToYMD(long date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date * 1000);
    }

}
