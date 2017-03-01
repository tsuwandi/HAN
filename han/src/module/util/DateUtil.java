package module.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public static String setFormatedDate(Date pDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(pDate);
	}

	public static Date toDate(Timestamp timestamp) {
		long milliseconds = timestamp.getTime()
				+ (timestamp.getNanos() / 1000000);
		return new Date(milliseconds);
	}

	public static java.sql.Date toDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Timestamp setTimeStamp() {
		long time = System.currentTimeMillis();
		return new java.sql.Timestamp(time);
	}

	public static Timestamp setTimeStamp(Date pDate, int pHour, int pMinute,
			int pSecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pDate);
		calendar.set(Calendar.HOUR, pHour);
		calendar.set(Calendar.MINUTE, pMinute);
		calendar.set(Calendar.SECOND, pSecond);

		return new java.sql.Timestamp(calendar.getTimeInMillis());

	}

	public static Timestamp convertStringToTimestamp(String str_date) {
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			// you can change format of date
			Date date = formatter.parse(str_date);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}

}
