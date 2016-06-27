package xmlutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//! Brief description, Time/date routines
/**
 * 
 * 
 * This class contains the standard string parsing routines to convert time/date
 * To and from a Date object. This include the yyyy/MM/dd HH:mm:ss so lists can be ordered
 * correctly

 *
 */
/**
 * 
 * @author 501726576
 *
 */
public class DateUtils {

	static final String formatDDMMYYYY = "dd/MM/yyyy HH:mm:ss";
	static final String formatYYYYMMDD = "yyyy/MM/dd HH:mm:ss";

	/**
	 * Creates a date object from a String day first.
	 * 
	 * @param dateString
	 * @return Date object
	 * @throws ParseException
	 */
	public static Date parseDDMMYYYY(String dateString) throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(formatDDMMYYYY);
		return dateFormat.parse(dateString);
	}
	
	/**
	 * Creates a date object from a String, year first.
	 * 
	 * @param dateString
	 * @return Date object
	 * @throws ParseException
	 */
	public static Date parseYYYYMMDD(String dateString) throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(formatYYYYMMDD);
		return dateFormat.parse(dateString);
	}
	
	/**
	 * Creates a String from a Date object, day first.
	 * 
	 * @param date 
	 * @return String
	 * @throws ParseException
	 */
	public static String formatDDMMYYYY(Date date) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(formatDDMMYYYY);
		return dateFormat.format(date);
	}

	/**
	 * Creates a String from a Date object, year first.
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatYYYYMMDD(Date date) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(formatYYYYMMDD);
		return dateFormat.format(date);
	}
}
