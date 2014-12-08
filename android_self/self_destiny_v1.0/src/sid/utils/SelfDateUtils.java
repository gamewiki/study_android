package sid.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelfDateUtils {
	public static String getDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		return sdf.format(date);
	}
	public static String getDateTime(String format,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getDateFromLong(String format,String id) {
		Date date = new Date(Long.parseLong(id));
		return getDateTime(format,date);
	}
}
