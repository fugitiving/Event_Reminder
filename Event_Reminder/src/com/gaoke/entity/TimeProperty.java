package com.gaoke.entity;

public class TimeProperty {
	private int planWeekDay;
	private String planTime;
	private String planContext;
	public int getPlanWeekDay() {
		return planWeekDay;
	}
	public void setPlanWeekDay(int planWeekDay) {
		this.planWeekDay = planWeekDay;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getPlanContext() {
		return planContext;
	}
	public void setPlanContext(String planContext) {
		this.planContext = planContext;
	}
	public TimeProperty(int planWeekDay, String planTime, String planContext) {
		super();
		this.planWeekDay = planWeekDay;
		this.planTime = planTime;
		this.planContext = planContext;
	}
	public TimeProperty() {
	}
	
}
