package org.blackboa.utils.time;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class TimeUtils {

	/**
	 * 
	 * 
	 * 得到系统当前时间
	 * 
	 * @return
	 */
	public static long getCurrentTime() {

		return System.currentTimeMillis();

	}

	/**
	 * 生成平台统一的timeId
	 * 
	 * @param shotDate
	 * @param shotTime
	 * @return
	 */
	public static long getTimeId(String shotDate, String shotTime) {
		String date = TimeUtils.getDateToString(TimeUtils.getStringToDate(shotDate), "yyyyMMdd");
		if (!StringUtils.isEmpty(shotTime)) {
			// 转时间
			String time = shotTime.substring(0, 2);
			return Long.parseLong(date + time);
		}
		return Long.valueOf(date);

	}

	/**
	 * long转时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getDate(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt = new Date(time);
		return sdf.format(dt);
	}

	public static String getDate(long time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date dt = new Date(time);
		return sdf.format(dt);
	}

	/**
	 * 得到当天日期
	 * 
	 * @return
	 */
	public static String getCurrentDay(String format) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	/**
	 * 得到当天日期
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);
	}

	/**
	 * 得到当天周几
	 * 
	 * @param dateStr,formatStr
	 * @return
	 */
	public static String getWeekOfDate(String dateStr, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);
		try {
			Date date = format.parse(dateStr);
			String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0) {
				w = 0;
			}
			return weekDays[w];
		} catch (ParseException e) {

			e.printStackTrace();
			return "周日";
		}

	}

	public static String getWeekOfDate(String dateStr) {
		if (dateStr.contains(".")) {
			return getWeekOfDate(dateStr, "yyyy.MM.dd");
		} else {
			return getWeekOfDate(dateStr, "yyyy-MM-dd");
		}
	}

	public static String getHour(long time) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cal.setTimeInMillis(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			return new String("0" + hour + ":00");
		}
		return new String(hour + ":00");
	}

	public static long getLongValueTime(String time, String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		java.util.Date dt = null;
		try {
			dt = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt.getTime();
	}

	/**
	 * 得到指定日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 得到指定日期字符串（MM月dd日）
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * String 转 Date
	 * 
	 * <p>
	 * 
	 * @param date
	 * @return
	 * @jira TODO
	 * @author 贲国龙
	 * @date 2016年8月8日上午10:49:33
	 * @see 1.5.0
	 * @modified TODO
	 */
	public static Date getStringToDate(String date) {
		Date d = null;
		SimpleDateFormat sdf = null;
		if (date.contains(".")) {
			sdf = new SimpleDateFormat("yyyy.MM.dd");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}

		try {
			d = sdf.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return d;
	}

	public static Date getStringToDate(String date, String fomat) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(fomat);

		try {
			d = sdf.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return d;
	}

	public static String[] getStringUserDate(String date) throws UnsupportedEncodingException {
		String[] shotdates = new String[2];
		if (date.contains("|")) {
			return date.replaceAll("\\s*-", ".").replaceAll("\\|", "-").split("-");
		} else {
			shotdates[0] = date.toString().replaceAll("\\s*-", ".");
			shotdates[1] = "";
			return shotdates;
		}

	}

	/**
	 * 日期格式的计算相差天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betWeenDays = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(betWeenDays));
	}

	/**
	 * <p>
	 * 获取星座
	 * 
	 * @param time
	 * @return
	 * @jira TODO
	 * @author 贲国龙
	 * @date 2017年3月15日下午4:23:27
	 * @see 1.5.0
	 * @modified TODO
	 */
	public static String getConstellation(long time) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cal.setTimeInMillis(time);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };
		String[] constellationArr = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
				"天蝎座", "射手座", "摩羯座" };
		return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
	}

	/**
	 * 根据出生日期获得年龄
	 * <p>
	 * 
	 * @param birthDay
	 * @return
	 * @throws Exception
	 * @jira TODO
	 * @author 贲国龙
	 * @date 2017年4月24日下午2:28:23
	 * @see 1.5.0
	 * @modified TODO
	 */
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
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
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 微信朋友圈儿时间计算方式
	 * 
	 * @param time
	 * @return
	 */
	public static String getFriendlytime(long time) {
		Date d = TimeUtils.getStringToDate(TimeUtils.getDate(time, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		long delta = (getCurrentTime() - d.getTime()) / 1000;
		if (delta / (60 * 60 * 24 * 365) > 0) {
			return delta / (60 * 60 * 24 * 365) + "年前";
		}
		if (delta / (60 * 60 * 24 * 30) > 0) {
			return delta / (60 * 60 * 24 * 30) + "个月前";
		}
		if (delta / (60 * 60 * 24 * 7) > 0) {
			return delta / (60 * 60 * 24 * 7) + "周前";
		}
		if (delta / (60 * 60 * 24) > 0) {
			return delta / (60 * 60 * 24) + "天前";
		}
		if (delta / (60 * 60) > 0) {
			return delta / (60 * 60) + "小时前";
		}
		if (delta / (60) > 0) {
			return delta / (60) + "分钟前";
		}
		return "刚刚";
	}
}
