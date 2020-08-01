package common.util.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author jieli
 *
 */
public class TimeUtils {
	public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Date转Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp convert2Timestamp(Date date) {
		if (date == null)
			return null;
		return new Timestamp(date.getTime());
	}

	/**
	 * Calendar转Timestamp
	 *
	 * @param calendar
	 * @return
	 */
	public static Timestamp convert2Timestamp(Calendar calendar) {
		if (calendar == null)
			return null;
		return convert2Timestamp(calendar.getTime());
	}

	/**
	 * Timestamp转Calendar
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Calendar convert2Calendar(Timestamp timestamp) {
		if (timestamp == null)
			return null;
		Calendar ret = Calendar.getInstance();
		ret.setTime(timestamp);
		return ret;
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @param pattern
	 *            如yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String convert2String(Date date, String pattern) {
		if (date == null)
			return null;
		if (pattern == null || pattern.length() == 0)
			return DateFormatUtils.format(date.getTime(), DEFAULT_PATTERN);
		return DateFormatUtils.format(date.getTime(), pattern);
	}

	/**
	 * Timestamp转String
	 * 
	 * @param timestamp
	 * @param pattern
	 *            如yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String convert2String(Timestamp timestamp, String pattern) {
		if (timestamp == null)
			return null;
		if (pattern == null || pattern.length() == 0)
			return DateFormatUtils.format(timestamp.getTime(), DEFAULT_PATTERN);
		return DateFormatUtils.format(timestamp.getTime(), pattern);
	}

	/**
	 * 
	 * Calendar转String
	 * 
	 * @param calendar
	 * @param pattern
	 *            如yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String convert2String(Calendar calendar, String pattern) {
		if (calendar == null)
			return null;
		if (pattern == null || pattern.length() == 0)
			return DateFormatUtils.format(calendar, DEFAULT_PATTERN);
		return DateFormatUtils.format(calendar, pattern);
	}

	/**
	 * String转Date
	 * 
	 * @param str
	 * @param pattern
	 *            如yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static Date convert2Date(String str, String pattern) throws ParseException {
		if (StringUtils.isEmpty(str))
			return null;
		CheckUtils.checkNotNull(pattern, "pattern can NOT be null");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(str);
	}

	/** 当天0:0:0 */
	public static Calendar CalendarTodayDawn() {
		Calendar ret = Calendar.getInstance();
		ret.set(Calendar.HOUR_OF_DAY, 0);
		ret.set(Calendar.SECOND, 0);
		ret.set(Calendar.MINUTE, 0);
		return ret;
	}

	/** 当天23:59:59 */
	public static Calendar CalendarTodayEve() {
		Calendar ret = Calendar.getInstance();
		ret.set(Calendar.HOUR_OF_DAY, 23);
		ret.set(Calendar.SECOND, 59);
		ret.set(Calendar.MINUTE, 59);
		return ret;
	}

	/** 1970-1-1 0:0:0 */
	public static Calendar CalendarMin() {
		Calendar ret = Calendar.getInstance();
		ret.set(1970, 0, 1, 0, 0, 0);
		return ret;
	}

	/** 2099-12-31 23:59:59 */
	public static Calendar CalendarMax() {
		Calendar ret = Calendar.getInstance();
		ret.set(2099, 11, 31, 23, 59, 59);
		return ret;
	}
}
