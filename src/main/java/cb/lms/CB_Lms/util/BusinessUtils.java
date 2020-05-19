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
	
	
	

}
