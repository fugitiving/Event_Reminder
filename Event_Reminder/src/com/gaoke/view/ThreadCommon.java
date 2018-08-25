package com.gaoke.view;

import com.gaoke.entity.ReTest2;
import com.gaoke.entity.TimeAndM;
import com.gaoke.util.JugleTime;
import com.gaoke.util.LocalTotalTime;
import com.gaoke.util.TimeUtil;

public class ThreadCommon extends Thread {
	
	ReTest2 reTest;
	
	public ThreadCommon(ReTest2 reTest) {
		this.reTest = reTest;
	}
	
	public void run() {

		
		//0是星期天  1是星期一  2是星期二  3是星期三  4是星期四  5是星期五  6是星期六  7是星期一到星期五
		int weekDay = (int) reTest.getPlanWeekDay();
		
		//对时间字符串进行解析，判断输入的格式是否符合要求
		String strPlanTime = (String) reTest.getPlanTime();
		String[] strTemp = strPlanTime.split(":|：|-");
		int Hour = Integer.parseInt(strTemp[0]);
		int Minute = Integer.parseInt(strTemp[1]);
		int Second = Integer.parseInt(strTemp[2]);
		int Time = Hour*60*60+Minute*60+Second;
		
		String str = (String) reTest.getPlanContext();
		
		int m = 0;
		int rm = 0;

		int LocalTime = 0;
		while (true) {
			
			//获取当前系统时间
			LocalTime = LocalTotalTime.GetLocalTotalTime();
			
			// 0是星期天 1是星期一 2是星期二 3是星期三 4是星期四 5是星期五 6是星期六
			int LocalWeek = TimeUtil.getLocalWeek();
			
			if (weekDay == 7) {
				// 星期一到星期五提醒
				if (LocalWeek == 1 || LocalWeek == 2 || LocalWeek == 3 || LocalWeek == 4 || LocalWeek == 5) {
					m = JugleTime.IsSameTime(Hour, Minute, Second, str);
					if(m == 1 || m == -5) {
						int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
						int TempTime =LocalTime1 - LocalTime; 
						Time = Time + 600 + TempTime;  //增加600秒
					}
					if(m != 0 || rm != 0) {
						TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time, str);
						Time = tm.getTime();
						rm = tm.getM();
					}
				}
			} else if (LocalWeek == weekDay) {
				// 设置某一天提醒
				m = JugleTime.IsSameTime(Hour, Minute, Second, str);
				if(m == 1 || m == -5) {
					int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
					int TempTime =LocalTime1 - LocalTime; 
					Time = Time + 600 + TempTime;
				}
				if(m != 0 || rm != 0) {
					TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time, str);
					Time = tm.getTime();
					rm = tm.getM();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
