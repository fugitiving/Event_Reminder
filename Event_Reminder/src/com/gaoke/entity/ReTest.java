package com.gaoke.entity;

public class ReTest {
	private int Hour;
	private int Minute;
	private int Second;
	
	//0��������  1������һ  2�����ڶ�  3��������  4��������  5��������  6��������  7������һ��������
	private int weekDay;

	private String str;
	
	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}

	public int getMinute() {
		return Minute;
	}

	public void setMinute(int minute) {
		Minute = minute;
	}

	public int getSecond() {
		return Second;
	}

	public void setSecond(int second) {
		Second = second;
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
