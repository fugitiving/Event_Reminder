package com.gaoke.util;

public class LocalTotalTime {
	private static int LocalHour;
	private static int LocalMinute;
	private static int LocalSecond;		
	
	public static int GetLocalTotalTime() {
		LocalHour = TimeUtil.getLocalHour();
		LocalMinute = TimeUtil.getLocalMinute();
		LocalSecond = TimeUtil.getLocalSecond();		
		int LocalTime = LocalHour*60*60+LocalMinute*60+LocalSecond;
		return LocalTime;
	}
}
