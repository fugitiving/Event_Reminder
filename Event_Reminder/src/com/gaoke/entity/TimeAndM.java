package com.gaoke.entity;

import com.gaoke.util.LocalTotalTime;
import com.gaoke.view.TimeDialog;
import com.gaoke.view.TimerTest;

/**
 * 用来返回两个值
 * @author gaoke
 *
 */
public class TimeAndM {
	private int m = 0;
	private int Time = 0;
	TimeAndM(){
		
	}
	
	public TimeAndM(int m, int time) {
		super();
		this.m = m;
		Time = time;
	}

	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	/**
	 * 
	 * @param LocalTime
	 * @param Time
	 * @param str  是倒计时10秒提示框的文字
	 * @return
	 */
	public static TimeAndM IsSameLocalTimeandTime(int LocalTime, int Time, String str) {
		int m1 = 0;
		if(LocalTime == Time) {
			TimerTest tt = new TimerTest();
			TimeDialog d = new TimeDialog();
			m1 = d.showDialog(tt, str, 10);
			if(m1 == 1 || m1 == -5) {
				int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
				int TempTime =LocalTime1 - LocalTime;
				Time = Time + 600 + TempTime;
			}
		}
		TimeAndM tm = new TimeAndM(0,0);
		tm.m = m1;
		tm.Time = Time;
		return tm;
	}
}
