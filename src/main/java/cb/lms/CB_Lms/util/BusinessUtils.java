package cb.lms.CB_Lms.util;

import java.util.Date;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class BusinessUtils {
	
	private static String pattern = "dd MMM yyyy";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	public static String formatDate(Date date) {
		
		return simpleDateFormat.format(date);
	}
	
	public static LocalDate toLocalDate(Date date) {
	    return date.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	
	public static String numberOrdinal(Integer value) {
		
		int hunRem = value % 100;
		int tenRem = value % 10;
		if (hunRem - tenRem == 10) {
			return  value+"th";
		}
		switch (tenRem) {
		case 1:
			return value+"st";
		case 2:
			return value+"nd";
		case 3:
			return value+"rd";
		default:
			return value+"th";
		}
	}
	
	

}
