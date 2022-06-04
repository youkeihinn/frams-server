package com.projects.core.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 * 公共方法类
 * </p>
 * <p>
 * 提供有关日期的实用方法集
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: NineTowns
 * </p>
 * 
 * @author Weiwenqi
 * @version 1.0
 */

public class DateUtil {
	private static String[] parsePatterns = { "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMddHHmmss" };
	public static java.text.SimpleDateFormat sdfShort = new java.text.SimpleDateFormat(
			"yyyyMMdd");

	public static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	public static java.text.SimpleDateFormat sdfLong1 = new java.text.SimpleDateFormat(
			"yyyy");

	public static java.text.SimpleDateFormat sdfLongCn = new java.text.SimpleDateFormat(
			"yyyy年MM月dd日");

	public static java.text.SimpleDateFormat sdfShortU = new java.text.SimpleDateFormat(
			"MMM dd", Locale.ENGLISH);

	public static java.text.SimpleDateFormat sdfLongU = new java.text.SimpleDateFormat(
			"MMM dd,yyyy", Locale.ENGLISH);

	public static java.text.SimpleDateFormat sdfLongTime = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static java.text.SimpleDateFormat sdfLongTimePlus = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static java.text.SimpleDateFormat sdfShortLongTimePlusCn = new java.text.SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");
	public static java.text.SimpleDateFormat sdfShortLongTimePlusCn1 = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static java.text.SimpleDateFormat sdfLongTimePlusMill = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSSS");

	public static java.text.SimpleDateFormat sdfMd = new java.text.SimpleDateFormat(
			"MM月dd日");

	private static long DAY_IN_MILLISECOND = 0x5265c00L;

	public DateUtil() {
	}

	/**
	 * 获取当前时间30天前的日期
	 */
	public static Date getBefore() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -30);
		return now.getTime();
	}

	/**
	 * 获取时间段内每天的日期
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	/**
	 * @author Pablo Descrption:?????????getgetg get Date format
	 *         Example：2008-05-15
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateLong(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * @author Pablo Descrption:?????????getgetg get Date format
	 *         Example：2008年-05月-15日
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateLongCn(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLongCn.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * @author vowo Descrption:?????????getgetg get Date format Example：05月-15日
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateMD(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfMd.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * @author Pablo Descrption:?????????getgetg get Date format
	 *         Example：2008年-05月-15日 11:05
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateShortLongTimeCn(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfShortLongTimePlusCn.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * @author Pablo Descrption:?????????getgetg get Date format Example：Aug 28,
	 *         2007
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateUS(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLongU.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * @author Pablo Descrption:?????????getgetg get Date format Example：Aug 28,
	 *         2007
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateUSShort(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfShortU.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}

	/**
	 * 简单转换日期类型到字符串类型，本地信息设为UK
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String getFomartDate(Date date, String format) {
		try {
			return new SimpleDateFormat(format, Locale.UK).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return (date == null) ? new Date().toString() : date.toString();
		}
	}

	/**
	 * Descrption:取得当前日期时间,格式为:YYYYMMDDHHMISS
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowLongTime() throws Exception {
		String nowTime = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期,格式为:YYYYMMDD
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowShortDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfShort.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期,格式为:YYYY-MM-DD
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowFormateDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTime() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTime(Date date) throws Exception {
		if (date == null)
			return null;
		try {
			String nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期,格式为:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getPlusTime2(Date date) {

		if (date == null)
			return null;
		try {
			String nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Descrption:取得当前日期到毫秒极,格式为:yyyyMMddHHmmssSSSS
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTimeMill() {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlusMill.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	
	

}
