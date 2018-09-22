package com.zhaozhiguang.component.security.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 * @author zhaozhiguang
 */
public class DateUtil {

    /**
     * 默认时间格式
     */
    private DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    /**
     * 当前时间加秒数
     * @param seconds
     * @return
     */
    public LocalDateTime currentAddSeconds(long seconds) {
        return LocalDateTime.now().plusSeconds(seconds);
    }

    /**
     * 当前时间加分钟数
     * @param min
     * @return
     */
    public LocalDateTime currentAddMin(long min) {
        return LocalDateTime.now().plusMinutes(min);
    }

    /**
     * LocalDateTime 转 Date
     * @param dateTime
     * @return
     */
    public Date fromLocalDateTime(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     * @param date
     * @return
     */
    public LocalDateTime fromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * String 转化为 LocalDateTime
     * @param timeStr 被转化的字符串
     * @return LocalDateTime
     */
    public LocalDateTime parseTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转化为 LocalDateTime
     * @param timeStr 被转化的字符串
     * @param timeFormat 转化的时间格式
     * @return LocalDateTime
     */
    public LocalDateTime parseTime(String timeStr, TimeFormat timeFormat) {
        return LocalDateTime.parse(timeStr, timeFormat.formatter);

    }

    /**
     * LocalDateTime 转化为String
     * @param time LocalDateTime
     * @return String
     */
    public String parseTime(LocalDateTime time) {
        return DEFAULT_DATETIME_FORMATTER.format(time);
    }

    /**
     * LocalDateTime 时间转 String
     * @param time LocalDateTime
     * @param format 时间格式
     * @return String
     */
    public String parseTime(LocalDateTime time, TimeFormat format) {
        return format.formatter.format(time);
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getCurrentDateTime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取当前时间
     * @param timeFormat 时间格式
     * @return
     */
    public String getCurrentDateTime(TimeFormat timeFormat) {
        return timeFormat.formatter.format(LocalDateTime.now());
    }

    /**
     * 内部枚举类
     *
     * @author zhaozhiguang
     */
    public enum TimeFormat {
        /**
         *时间格式：yyyy-MM-dd
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        /**
         *时间格式：yyyy/MM/dd
         */
        SHORT_DATE_PATTERN_SLASH("yyyy/MM/dd"),
        /**
         *时间格式：yyyy\\MM\\dd
         */
        SHORT_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
        /**
         *时间格式：yyyyMMdd
         */
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        // 长时间格式 年月日时分秒
        /**
         *时间格式：yyyy-MM-dd HH:mm:ss
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),

        /**
         *时间格式：yyyy/MM/dd HH:mm:ss
         */
        LONG_DATE_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
        /**
         *时间格式：yyyy\\MM\\dd HH:mm:ss
         */
        LONG_DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
        /**
         *时间格式：yyyyMMdd HH:mm:ss
         */
        LONG_DATE_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

        // 长时间格式 年月日时分秒 带毫秒
        LONG_DATE_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),

        LONG_DATE_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);

        }
    }

}
