package com.gaoke.entity;

public class ReTest2 {
	private String planTime;
	
	//0是星期天  1是星期一  2是星期二  3是星期三  4是星期四  5是星期五  6是星期六  7是星期一到星期五
	private int planWeekDay;

	private String planContext;

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public int getPlanWeekDay() {
		return planWeekDay;
	}

	public void setPlanWeekDay(int planWeekDay) {
		this.planWeekDay = planWeekDay;
	}

	public String getPlanContext() {
		return planContext;
	}

	public void setPlanContext(String planContext) {
		this.planContext = planContext;
	}
}
