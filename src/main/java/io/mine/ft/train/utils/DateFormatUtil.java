package io.mine.ft.train.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 日期处理工具类
 * 
 */

public class DateFormatUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

	/** 默认时间格式：yyyy-MM-dd */
	public static final String DEFAULT_DATE_FOMART = "yyyy-MM-dd";

	public static final String DEFAULT_DATE_TIME_FOMART = "yyyy-MM-dd HH:mm:ss";

	public static final String FROMAT_CHINESE_DATE = "yyyy年MM月dd日";

	public static long NANO_ONE_SECOND = 1000;
	public static long NANO_ONE_MINUTE = 60 * NANO_ONE_SECOND;
	public static long NANO_ONE_HOUR = 60 * NANO_ONE_MINUTE;
	public static long NANO_ONE_DAY = 24 * NANO_ONE_HOUR;

	/**
	 * java.sql.Timestamp/java.util.Date 转化为默认日期时间格式字符串 yyyy-MM-dd
	 */
	public static String dateToDefaultTimeStr(Date datetime) {
		return dateToFormatStr(datetime, DEFAULT_DATE_TIME_FOMART);
	}

	/**
	 * java.sql.Timestamp/java.util.Date 转化为默认日期格式字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToDefaultDateStr(Date date) {

		return dateToFormatStr(date, DEFAULT_DATE_FOMART);
	}

	/**
	 * 时间戳转化为默认日期格式字符串 yyyy-MM-dd
	 */
	public static String longToDefaultDateStr(Long time) {
		return longToFormatStr(time, DEFAULT_DATE_FOMART);

	}

	/**
	 * 时间戳转化为默认时间格式字符串 yyyy-MM-dd HH:mm:ss
	 */
	public static String longToDefaultTimeStr(Long time) {
		return longToFormatStr(time, DEFAULT_DATE_TIME_FOMART);

	}

	/**
	 * 默认日期格式字符串 yyyy-MM-dd转化为时间戳
	 */
	public static long defaultDateStrToLong(String dateStr) {
		return formatStrToLong(dateStr, DEFAULT_DATE_FOMART);
	}

	/**
	 * 默认时间格式字符串 yyyy-MM-dd HH:mm:ss转化为时间戳
	 */
	public static long defaultTimeStrToLong(String datetimeStr) {
		return formatStrToLong(datetimeStr, DEFAULT_DATE_TIME_FOMART);
	}

	/**
	 * 默认日期格式字符串 yyyy-MM-dd转化为Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp defaultDateStrToTimestamp(String dateStr) {

		return new Timestamp(defaultDateStrToLong(dateStr));
	}

	/**
	 * 默认时间格式字符串 yyyy-MM-dd HH:mm:ss转化为Timestamp
	 * 
	 * @return
	 */
	public static Timestamp defaultTimeStrToTimestamp(String datetimeStr) {

		return new Timestamp(defaultTimeStrToLong(datetimeStr));
	}

	/**
	 * 默认日期格式字符串 yyyy-MM-dd转化为java.util.Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date defaultDateStrToJavaDate(String dateStr) {

		return defaultDateStrToTimestamp(dateStr);
	}

	/**
	 * 默认时间格式字符串 yyyy-MM-dd HH:mm:ss转化为java.util.Date
	 * 
	 * @return
	 */
	public static Date defaultTimeStrToJavaDate(String datetimeStr) {

		return defaultTimeStrToTimestamp(datetimeStr);
	}

	/**
	 * 时间戳转化为指定时间格式字符串
	 * 
	 * @param time
	 * @param formatStr
	 * @return
	 */
	public static String longToFormatStr(Long time, String formatStr) {
		if (time == null || StringUtils.isEmpty(formatStr)) {
			return null;
		}
		return dateToFormatStr(new Date(time), formatStr);
	}

	/**
	 * java.sql.Timestamp/java.util.Date 转化为指定时间格式字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToFormatStr(Date date, String formatStr) {
		if (date == null || StringUtils.isEmpty(formatStr)) {
			return null;
		}
		return new SimpleDateFormat(formatStr).format(date);
	}

	/**
	 * 指定日期时间字符转化为时间戳
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static long formatStrToLong(String datetimeStr, String formatStr) {
		if (StringUtils.isEmpty(datetimeStr) || StringUtils.isEmpty(formatStr)) {
			return 0;
		}
		try {
			return new SimpleDateFormat(formatStr).parse(datetimeStr).getTime();
		} catch (ParseException e) {
			logger.error("DateFormatUtil formatStrToLong error: ", e);
		}
		return 0;
	}
}
