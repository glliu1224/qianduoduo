package com.media.his.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {

    // 时间的毫秒表示
    //一天转化为小时。单位：小时
    public static final int DAY_2_HOUR = 24;
    public static final long SECOND_LONG = 1000L; // 1秒 == 1000毫秒
    public static final long MINUTE_LONG = 60 * SECOND_LONG;
    public static final long HOUR_LONG = 60 * MINUTE_LONG;
    public static final long DAY_LONG = DAY_2_HOUR * HOUR_LONG;
    public static final long MONTH_LONG = 30 * DAY_LONG;
    public static final long YEAR_LONG = 365 * DAY_LONG;

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_HOURS_FORMAT = "HH:mm";
    public final static String DATE_HOURS_FORMAT_MM = "HHmm";
    public final static String DATE_MONTH_FORMAT = "yyyy-MM";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY = "yyyyMMdd";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY2 = "yyMMdd";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT_HIS = "yyyy/MM/dd";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE2 = "yyyyMMddHHmm";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_FORMAT_STR = "yyyy年MM月dd日 HH:mm";


    /**
     * @param date1 时间A
     * @param date2 时间B
     * @return 是否2个为同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return !(date1 == null || date2 == null) && diffDay(date1, date2) == 0;
    }

    /**
     * 计算两个日期之间相差的天数.不包括今天。
     *
     * @param date1 时间A
     * @param date2 时间B
     * @return 时间A与时间B的差值(前者 - 后者)。当有任何一个时间为空时，返回null
     * @see #diffDay(Date, Date, boolean) 取代。
     * 原因：该方法不精准。
     */
    public static Integer diffDay(Date date1, Date date2) {
        return diffDay(date1, date2, false);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param fDate        时间A
     * @param oDate        时间B
     * @param includeToday 是否包括今天
     * @return 时间A与时间B的差值(前者 - 后者)。当有任何一个时间为空时，返回null
     */
    public static Integer diffDay(Date fDate, Date oDate, boolean includeToday) {
        if (fDate == null || oDate == null) {
            return null;
        }
        DateTime begin = new DateTime(fDate);
        DateTime end = new DateTime(oDate);
        int days = Days.daysBetween(end.toLocalDate(), begin.toLocalDate()).getDays();
        return includeToday ? days + 1 : days;
    }

    /**
     * 计算量个日期之间相差的小时数
     *
     * @param fDate       时间A
     * @param oDate       时间B
     * @param includeHour 是否包括当前小时
     * @return 时间A与时间B的差值(前者 - 后者)。当有任何一个时间为空时，返回null
     */
    @SuppressWarnings("UnusedDeclaration")
    public static Integer diffHour(Date fDate, Date oDate, boolean includeHour) {
        if (fDate == null || oDate == null) {
            return null;
        }
        DateTime begin = new DateTime(fDate);
        DateTime end = new DateTime(oDate);
        int hours = Hours.hoursBetween(end.toLocalDateTime(), begin.toLocalDateTime()).getHours();
        return includeHour ? hours + 1 : hours;
    }

    /**
     * 比较两个时间的分差
     *
     * @param fDate 开始时间
     * @param oDate 结束时间
     * @return
     */
    public static Integer diffMinutes(Date fDate, Date oDate) {
        if (fDate == null || oDate == null) {
            return null;
        }
        DateTime begin = new DateTime(fDate);
        DateTime end = new DateTime(oDate);
        return Minutes.minutesBetween(begin.toLocalDateTime(), end.toLocalDateTime()).getMinutes();
    }

    /**
     * @param date 时间
     * @return 参数日期是否为今天
     */
    public static boolean isToday(Date date) {
        return isSameDay(date, new Date());
    }

    /**
     * 获取指定天开始时间
     *
     * @param date 日期
     * @return 获得该日期的开始
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 0, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayBegin() {
        return getDayBegin(new Date());
    }

    /**
     * 获取指定天结束时间
     *
     * @param date 日期
     * @return 获得该日期的结束
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 23, 59, 59, 999);
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 取得日期所在周的下星期一<br />
     * 注：若参数date为空，则取得第当前日期所对应的下一周
     *
     * @param date 所在日期
     */
    public static Date getNextMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        // 没有设置日期,则取当前时间
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day != Calendar.SUNDAY) {
            cal.add(Calendar.WEEK_OF_MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return cal.getTime();
    }

    /**
     * 取得日期所在周的下星期日<br />
     * 注：若参数date为空，则取得第当前日期所对应的下一周
     *
     * @param date 所在日期
     */
    public static Date getNextSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        // 没有设置日期,则取当前时间
        if (date != null) {
            cal.setTime(date);
        }
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day != Calendar.SUNDAY) {
            cal.add(Calendar.WEEK_OF_MONTH, 2);
        } else {
            cal.add(Calendar.WEEK_OF_MONTH, 1);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 取得日期所在周的上个星期一<br />
     * 注：若参数date为空，则取得第当前日期所对应的上一周
     *
     * @param date
     * @return
     */
    public static Date getBeforeWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = -1;
        String monday;
        cal.add(Calendar.DATE, n * 7);

        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 取得日期所在月的上个月同一天<br />
     * 注：若参数date为空，则取得第当前月所对应的上一月
     *
     * @param date
     * @return
     */
    public static Date getBeforeMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 取得日期所在周的周一
     *
     * @param date
     * @return
     */
    public static Date getWeekBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }


    /**
     * 取得日期所在周的周末<br />
     * 注：若参数date为空，则取得第当前日期所对应的下一周
     *
     * @param date
     * @return
     */
    public static Date getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = 1;
        cal.add(Calendar.DATE, n * 7);

        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * 取得日期所在的前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    /**
     * 取得日期所在的前/后几天
     * +值后几天  -值前几天
     *
     * @param date
     * @return
     */
    public static Date getDayBeforeOrNext(Date date, Integer phase) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + phase);
        return c.getTime();
    }

    /**
     * 取得所在日期的下一天<br/>
     * 注：如date为空,则取到为明天
     *
     * @param date 所在日期
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 获取日期的小时分钟字符串
     *
     * @param date
     * @return
     */
    public static Integer getHourMinute(Date date) {
        int beginHour = DateUtil.getField(date, Calendar.HOUR_OF_DAY);
        int beginMinute = DateUtil.getField(date, Calendar.MINUTE);
        Integer result = Integer.valueOf(String.valueOf(beginHour) + String.valueOf(beginMinute));
        return result;
    }

    /**
     * 获取日期所在月的天数
     *
     * @param date
     * @return
     */
    public static int getDayAccountOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        if (date != null) {
            calendar.setTime(date);
        }
        int day = calendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 取得日期所在月的下个月同一天<br />
     * 注：若参数date为空，则取得第当前月所对应的下一月
     *
     * @param date
     * @return
     */
    public static Date getNextMonthDay(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 设置Calendar的小时、分钟、秒、毫秒
     *
     * @param calendar    日历
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     */
    public static void setCalender(Calendar calendar, int hour, int minute, int second, int milliSecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
    }

    public static String formatTimestamp(Long time, String pattern) {
        return DateUtil.format(new Date(time), pattern);
    }

    /**
     * @param date 时间。不允许空
     * @return 时间对应的int值。比如说2013/3/4转换为20130304
     */
    public static Integer formatDate2Int(Date date) {
        Assert.notNull(date, "date is null!");
        return Integer.valueOf(format(date, "yyyyMMdd"));
    }

    /**
     * 将int形日期转为Date
     *
     * @param dateInt 日期int形式，如20150909
     */
    public static Date parseInt2Date(Integer dateInt) {
        return parse(String.valueOf(dateInt), DATE_FORMAT_YEAR_MONTH_DAY);
    }


    /**
     * @param year        年
     * @param mouth       月
     * @param day         日
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     * @return 指定时间
     */
    @SuppressWarnings("UnusedDeclaration")
    public static Date buildTime(Integer year, Integer mouth, Integer day, Integer hour, Integer minute,
                                 Integer second, Integer milliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, mouth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }

    /**
     * 判断当时时间是否在某个时间范围内
     *
     * @param minHour
     * @param minMin
     * @param maxHour
     * @param maxMin
     * @return
     */
    public static boolean isBetweenTheTimeZone(int minHour, int minMin, int maxHour, int maxMin) {
        return isBetweenTheTimeZone(minHour * 100 + minMin, maxHour * 100 + maxMin);
    }

    public static boolean isBetweenTheTimeZone(int beginTime, int endTime) {
        return isBetweenTheTimeZone(null, beginTime, endTime);
    }

    public static boolean isBetweenTheTimeZone(Date date, int beginTime, int endTime) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int now = calendar.get(Calendar.HOUR_OF_DAY) * 100 + calendar.get(Calendar.MINUTE);
        return beginTime <= now && now <= endTime;
    }

    /**
     * 获得时间指定的field
     *
     * @param time  时间
     * @param field filed
     * @return 值
     */
    public static int getField(Date time, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(field);
    }


    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDate(String date, String format) {
        Date dt = null;
        try {
            dt = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }

    public static long getCurrentUnixtime() {
        long unixtime = System.currentTimeMillis() / 1000L;
        return unixtime;
    }


    /**
     * 通过日期的时间戳计算年龄
     */
    public static int getAgeByBirthday(Date birthDaySec) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDaySec.getTime())) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        cal.setTimeInMillis(birthDaySec.getTime());

        int yearBirth = cal.get(Calendar.YEAR);

        int age = yearNow - yearBirth;

        return age;
    }


    /**
     * @param date    时间。若为空，则返回空串
     * @param pattern 时间格式化
     * @return 格式化后的时间字符串.
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * 获取时间戳
     */
    public static long getLongstamp(Integer year, Integer month, Integer date, Integer hour, Integer minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, minutes);
        return calendar.getTimeInMillis() / 1000;
    }


    /**
     * 获取某月的第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获得该月第一天
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //初始化开始时间
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //初始化结束时间
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    //得到本月的第一天
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat firstDay = new SimpleDateFormat("yyyy-MM-dd");
        return firstDay.format(calendar.getTime()) + " 00:00:00";
    }

    //得到本月的最后一天
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat lastDay = new SimpleDateFormat("yyyy-MM-dd");
        return lastDay.format(calendar.getTime()) + " 23:59:59";
    }

    /**
     * hour && min join拼接
     */
    public static Map<String, String> getSplitTime(String time) {
        Map<String, String> stringMap = new HashMap<>();
        String hour, min;
        if (time.length() == 1) {
            hour = "00";
            min = "0" + time;
        } else {
            hour = time.substring(0, time.length() - 2);
            min = time.substring(time.length() - 2);
            if (hour.length() > 0 && hour.length() < 2) {
                hour = "0" + hour;
            } else if (hour.length() == 0) {
                hour = "00";
            }
        }
        stringMap.put("hour", hour);
        stringMap.put("min", min);
        return stringMap;
    }

    /**
     * 转换年月日为string
     *
     * @param date 格式 yyyyMMdd
     */
    public static String splitYearMonthDay(Integer date) {
        String stringDate = String.valueOf(date);
        String year = stringDate.substring(0, 4);
        String month = stringDate.substring(4, 6);
        String day = stringDate.substring(6);
        StringBuilder sb = new StringBuilder();
        return sb.append(year).append(".").append(month).append(".").append(day).toString();
    }


    /**
     * 转换小时分钟为string
     *
     * @param time 格式 HHmm
     */
    public static String splitHourMin(Integer time) {
        String stringTime = String.valueOf(time);
        String hour = "";
        String min = "";
        if (stringTime.length() == 4) {
            hour = stringTime.substring(0, 2);
            min = stringTime.substring(2);
        } else if (stringTime.length() == 3) {
            hour = stringTime.substring(0, 1);
            min = stringTime.substring(1);
        } else if (stringTime.length() == 2) {
            hour = "00";
            min = stringTime;
        } else if (stringTime.length() == 1) {
            hour = "00";
            min = "0" + stringTime;
        } else if (stringTime == null || stringTime.length() == 0) {
            hour = "00";
            min = "00";
        }
        StringBuilder sb = new StringBuilder();
        return sb.append(hour).append(":").append(min).toString();
    }


    /**
     * 获取指定时间的后多少分钟
     *
     * @param date   指定日期
     * @param minute 分钟
     */
    public static Date futureDate(Date date, Integer minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * 获取当前时间前多少分钟
     */
    public static Date pastDate(Date currentDate, Integer minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        return calendar.getTime();
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param date   设置时间
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }


    /**
     * 格式化成指定的日期string类型
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }


    /**
     * 未来N天后的日期
     *
     * @param currentDate 当前日期
     * @param day         天数
     * @return
     */
    public static Date getFutureDay(Date currentDate, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 未来N月后的日期
     */
    public static Date getFutureMonth(Date currentDate, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取N年后的日期
     *
     * @param currentDate
     * @param year
     * @return
     */
    public static Date getFutureYear(Date currentDate, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取时间搓
     *
     * @param timeDay 日期
     * @param time    时间
     * @return
     */
    public static Long getTime(Integer timeDay, Integer time) {
        String date = timeDay.toString();

        Integer year = Integer.parseInt(date.substring(0, 4));
        Integer month = Integer.parseInt(date.substring(4, 6)) - 1;
        Integer day = Integer.parseInt(date.substring(6, 8));
        Map<String, String> splitTime = DateUtil.getSplitTime(time.toString());

        return DateUtil.getLongstamp(year, month, day, Integer.parseInt(splitTime.get("hour")),
                Integer.parseInt(splitTime.get("min")));

    }

    /**
     * 获取当前时间一年以后的时间
     *
     * @return
     */
    public static Date getOneYearLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    /**
     * 将分钟转换成时分格式
     *
     * @param min
     * @return hour min
     */
    public static String minConvertDayHourMin(Double min) {
        String html = "0分";
        if (min != null) {
            Double m = (Double) min;
            String format;
            Object[] array;
            //Integer days =(int) (m/(60*24));
            Integer hours = (int) (m / (60));
            Integer minutes = (int) (m - hours * 60);
            if (hours > 0) {
                if (minutes > 0) {
                    format = "%1$,d小时%2$,d分";
                    array = new Object[]{hours, minutes};
                } else {
                    format = "%1$,d小时";
                    array = new Object[]{hours};
                }
            } else {
                format = "%1$,d分";
                array = new Object[]{minutes};
            }
            html = String.format(format, array);
        }
        return html;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        if (beginDate.getTime() != endDate.getTime()) {
            lDate.add(endDate);// 把结束时间加入集合
        }
        return lDate;
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static Integer getWeekOfDate(Date date) {
        Integer[] weekDays = {0, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 计算年龄
     *
     * @param birthday
     * @return
     */
    public static String calculateAgeByBirthday(String birthday) {
        try {
            if (CheckUtil.isEmpty(birthday)) {
                return null;
            }
            Date birthdayDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_YEAR_MONTH_DAY).parse(birthday);
            Calendar now = Calendar.getInstance();
            Calendar b = Calendar.getInstance();
            b.setTime(birthdayDate);
            int year = now.get(Calendar.YEAR) - b.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) - b.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH) - b.get(Calendar.DAY_OF_MONTH);
            if (month < 0) {
                month = 12 - b.get(Calendar.MONTH) + now.get(Calendar.MONTH);
                year -= 1;
            }
            if (day < 0) {
                day = b.getMaximum(Calendar.DAY_OF_MONTH) - b.get(Calendar.DAY_OF_MONTH) + now.get(Calendar.DAY_OF_MONTH);
                month -= 1;
            }
            // 0岁情况下
            if (year == 0 && month == 0 && day == 0) {
                return "0岁";
            }
            if (year == 0 && month != 0 && day == 0) {
                return "0岁" + month + "月";
            }
            if (year == 0 && month != 0 && day != 0) {
                return "0岁" + month + "月";
            }
            //非0岁情况下
            if (year != 0 && month == 0 && day == 0) {
                return year + "岁";
            }
            if (year != 0 && month != 0 && day == 0) {
                return year + "岁" + month + "月";
            }
            if (year != 0 && month != 0 && day != 0) {
                return year + "岁" + month + "月" + day + "天";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据出生日期获取年龄（整数）
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

}