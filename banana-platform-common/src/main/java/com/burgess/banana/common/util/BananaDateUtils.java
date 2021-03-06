package com.burgess.banana.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.common.utils
 * @file_name BananaDateUtils.java
 * @description 日期处理
 * @create 2018-04-18 22:39
 */
public class BananaDateUtils {

	/**时间格式(yyyy-MM-dd)*/
	public final static String DATE_TIME_PATTERN_YMD = "yyyy-MM-dd";
	/**时间格式(yyyy-MM-dd HH:mm:ss)*/
	public final static String DATE_TIME_PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	/**时间格式(yyyy-MM-dd HH:mm)*/
	public final static String DATE_TIME_PATTERN_YMDHM = "yyyy-MM-dd HH:mm";
	/**时间格式(HH:mm:ss)*/
	public final static String DATE_TIME_PATTERN_HMS = "HH:mm:ss";

	//私有化构造方法
	private BananaDateUtils() {}
	
	/**
	 * @package com.burgess.banana.common.util
	 * @file BananaDateUtils.java
	 * @author burgess.zhang
	 * @time 下午4:15:52 2018年4月26日
	 * @desc  计算两个日期相差的分钟数
	 * @param start
	 * @param end
	 * @return
	 */
	public static long calcMinutesOfTwoDate(Date start,Date end) {
		long longStart = start.getTime();
		long longEnd = end.getTime();
		
		long longMinute = (longEnd - longStart) / (1000 * 60);
		
		return longMinute;
	}
	
	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 *
	 * @param date
	 *            日期
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date) {
		return format(date, DATE_TIME_PATTERN_YMD);
	}

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 *
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 *
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期的格式，如：DateUtils.DATE_TIME_PATTERN
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseLocalDateTime(strDate).toDate();
	}

	/**
	 * 根据周数，获取开始日期、结束日期
	 *
	 * @param week
	 *            周期 0本周，-1上周，-2上上周，1下周，2下下周
	 * @return 返回date[0]开始日期、date[1]结束日期
	 */
	public static Date[] getWeekStartAndEnd(int week) {
		DateTime dateTime = new DateTime();
		LocalDate date = new LocalDate(dateTime.plusWeeks(week));

		date = date.dayOfWeek().withMinimumValue();
		Date beginDate = date.toDate();
		Date endDate = date.plusDays(6).toDate();
		return new Date[] { beginDate, endDate };
	}

	/**
	 * 对日期的【秒】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param seconds
	 *            秒数，负数为减
	 * @return 加/减几秒后的日期
	 */
	public static Date addDateSeconds(Date date, int seconds) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusSeconds(seconds).toDate();
	}

	/**
	 * 对日期的【分钟】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param minutes
	 *            分钟数，负数为减
	 * @return 加/减几分钟后的日期
	 */
	public static Date addDateMinutes(Date date, int minutes) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMinutes(minutes).toDate();
	}

	/**
	 * 对日期的【小时】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param hours
	 *            小时数，负数为减
	 * @return 加/减几小时后的日期
	 */
	public static Date addDateHours(Date date, int hours) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusHours(hours).toDate();
	}

	/**
	 * 对日期的【天】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param days
	 *            天数，负数为减
	 * @return 加/减几天后的日期
	 */
	public static Date addDateDays(Date date, int days) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusDays(days).toDate();
	}

	/**
	 * 对日期的【周】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param weeks
	 *            周数，负数为减
	 * @return 加/减几周后的日期
	 */
	public static Date addDateWeeks(Date date, int weeks) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusWeeks(weeks).toDate();
	}

	/**
	 * 对日期的【月】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param months
	 *            月数，负数为减
	 * @return 加/减几月后的日期
	 */
	public static Date addDateMonths(Date date, int months) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMonths(months).toDate();
	}

	/**
	 * 对日期的【年】进行加/减
	 *
	 * @param date
	 *            日期
	 * @param years
	 *            年数，负数为减
	 * @return 加/减几年后的日期
	 */
	public static Date addDateYears(Date date, int years) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusYears(years).toDate();
	}
}
