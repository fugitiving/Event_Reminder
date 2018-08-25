package com.gaoke.entity;

public enum WeekDayEnum {
	Sun("������"),Mon("����һ"),Tues("���ڶ�"),Wed("������"),Thur("������"),Fri("������"),Sat("������"),MontoFri("��һ������");
	private final String name;
	private WeekDayEnum(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
