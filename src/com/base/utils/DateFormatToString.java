package com.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author zhangLibo on 2015年3月5日 下午6:18:38
 *
 */
public class DateFormatToString {
	/**
	 * 按YYYY-MM-DD HH:MM:SS格式返回今天的日期.
	 * 
	 * @return String
	 */
	public static String getToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 计算出与指定时间papaDay n天之后的时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDay(String papaDay, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calender = Calendar.getInstance();
		try {
			calender.setTime(sdf.parse(papaDay));
			calender.add(Calendar.DATE, num);
			return sdf.format(calender.getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 计算出与指定时间papaDay n月之后的时间
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getAddMonth(String papaDay, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calender = Calendar.getInstance();
		try {
			calender.setTime(sdf.parse(papaDay));
			calender.add(Calendar.MONTH, num);
			return sdf.format(calender.getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 返回今天的日期(yyyy-MM-dd)
	 * 
	 * @return String
	 */
	public static String getToday1() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日期(yyyy年MM月dd日HH时mm分ss秒).
	 * 
	 * @return String
	 */
	public static String getToday2() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日期(yyyyMMddHHmmss).
	 * 
	 * @return String
	 */
	public static String getToday3() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日期(yyyy-MM-dd HH:mm:ss.SSS).
	 * 
	 * @return String
	 */
	public static String getToday4() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日期(yyyyMMddHHmmsssss).
	 * 
	 * @return String
	 */
	public static String getToday6() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsssss"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日期(yyyyMMdd).
	 * 
	 * @time : 2015年3月5日 下午6:16:04
	 * @return
	 */
	public static String getToday5() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的年份(yyyy).
	 * 
	 * @return String
	 */
	public static String getYear() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的月份(MM).
	 * 
	 * @return String
	 */
	public static String getMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 返回今天的日子数(dd).
	 * 
	 * @return String
	 */
	public static String getDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd"); // 规定日期格式
		Date date = new Date(); // 将符合格式的String转换为Date
		String today = formatter.format(date); // 将Date转换为符合格式的String
		// System.out.print(today);
		return today;
	}

	/**
	 * 计算给定的两个时间相差的秒数:end - start
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDiffNum(String start, String end) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d_start = ft.parse(start);
			Date d_end = ft.parse(end);
			quot = d_end.getTime() - d_start.getTime();
			quot = quot / 1000;
			// / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Math.abs(quot);
	}

	public static String getTodayToString() {
		return getYear() + getMonth() + getDay();

	}

	public static Date getDateByString(String dateTimeStr) {
		Date date = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = ft.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @time : 2015年3月5日 下午6:17:57
	 * @param data
	 * @return
	 * @throws ParseException
	 * @Description: 返回指定日期字符串
	 */
	public static String getStringByDate(Date data) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = ft.format(data);
		return date;
	}

	public static Date getDateByString1(String dateTimeStr) {
		Date date = null;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		try {
			date = ft.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public static void main(String[] args) {

		// System.out.println(getDiffNum("2009-11-06 09:10:15","2009-11-06 09:09:56"));
		// System.out.println(getDiffNum("2009-11-06 09:10:15","2009-11-06 09:10:56"));
		// System.out.println(getDiffNum("2009-11-06 09:10:15","2009-11-06 09:12:56"));

		// System.out.println(getDateByString("2009-11-06 09:10:15"));
	}
}
