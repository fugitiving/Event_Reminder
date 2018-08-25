package com.gaoke.util;

import java.util.Calendar;

public class TimeUtil {
	private static int localHour;
	private static int localMinute;
	private static int localSecond;
	private static int localWeek;
	
	public static int getLocalWeek() {
		Calendar c = Calendar.getInstance();
		localWeek = c.get(Calendar.DAY_OF_WEEK)-1;
		return localWeek;
	}

	public static int getLocalHour() {
		Calendar c = Calendar.getInstance();
		localHour = c.get(Calendar.HOUR_OF_DAY);
		return localHour;
	}
	
	public static int getLocalMinute() {
		Calendar c = Calendar.getInstance();
		localMinute = c.get(Calendar.MINUTE);
		return localMinute;
	}
	
	public static int getLocalSecond() {
		Calendar c = Calendar.getInstance();
		localSecond = c.get(Calendar.SECOND);
		return localSecond;
	}
}
