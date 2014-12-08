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
	/** 日期时间显示两位数的方法 */
	public static String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}
}
