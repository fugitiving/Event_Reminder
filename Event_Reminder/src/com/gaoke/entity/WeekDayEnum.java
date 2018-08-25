package com.gaoke.entity;

public enum WeekDayEnum {
	Sun("星期天"),Mon("星期一"),Tues("星期二"),Wed("星期三"),Thur("星期四"),Fri("星期五"),Sat("星期六"),MontoFri("周一到周五");
	private final String name;
	private WeekDayEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
