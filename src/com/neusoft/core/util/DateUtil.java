package com.neusoft.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 默认日期格式
	 */
	private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 根据毫秒转换为字符串
	 * 
	 * @param mills
	 * @return
	 */
	public static String getDateStr(long mills) {
		Date d = new Date(mills);
		return new SimpleDateFormat(DEFAULT_FORMAT).format(d);
	}

	/**
	 * 根据日期类型转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrByDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 日期字符串格式化
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String formatDateStr(String dateStr) {
		if (dateStr == null || "".equals(dateStr.trim())) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException pe) {
			date = null;
		}
		return sdf.format(date);
	}
	
	/**
	 * 日期字符串格式化
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String formatDateStr(String dateStr,String format) {
		if (dateStr == null || "".equals(dateStr.trim())) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException pe) {
			date = null;
		}
		return sdf.format(date);
	}

}
