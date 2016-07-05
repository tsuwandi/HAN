package module.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public static String setFormatedDate(Date pDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(pDate);
	}
	
	public static Date toDate(Timestamp timestamp) {
		long milliseconds = timestamp.getTime()
				+ (timestamp.getNanos() / 1000000);
		return new Date(milliseconds);
	}
	
	public static Timestamp setTimeStamp() {
		long time = System.currentTimeMillis();
		return new java.sql.Timestamp(time);
	}
	
	public static Timestamp setTimeStamp(Date pDate, int pHour, int pMinute, int pSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, pHour);
		cal.set(Calendar.MINUTE, pMinute);
		cal.set(Calendar.SECOND, pSecond);
		cal.setTime(pDate);
		
		Date date = cal.getTime();
		
		return new java.sql.Timestamp(date.getTime());

	}

}
