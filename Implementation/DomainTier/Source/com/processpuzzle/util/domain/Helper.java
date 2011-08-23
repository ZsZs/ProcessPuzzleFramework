package com.processpuzzle.util.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Helper {
	public static String getTodaysDate() {
		Calendar calendar = new GregorianCalendar();
		String month;
		String day;
		if ((calendar.get(Calendar.MONTH)+1)<10)	//januar a 0 
			month="0"+(calendar.get(Calendar.MONTH)+1);
			else month=Integer.toString((calendar.get(Calendar.MONTH)+1));
		if (calendar.get(Calendar.DAY_OF_MONTH)<10)
			day="0"+calendar.get(Calendar.DAY_OF_MONTH);
			else day=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		
		String date = calendar.get(Calendar.YEAR)+"."+month+"."+day;
		return date;
	}
	

}
