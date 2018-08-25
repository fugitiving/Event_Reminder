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

		
		//0��������  1������һ  2�����ڶ�  3��������  4��������  5��������  6��������  7������һ��������
		int weekDay = (int) reTest.getPlanWeekDay();
		
		//��ʱ���ַ������н������ж�����ĸ�ʽ�Ƿ����Ҫ��
		String strPlanTime = (String) reTest.getPlanTime();
		String[] strTemp = strPlanTime.split(":|��|-");
		int Hour = Integer.parseInt(strTemp[0]);
		int Minute = Integer.parseInt(strTemp[1]);
		int Second = Integer.parseInt(strTemp[2]);
		int Time = Hour*60*60+Minute*60+Second;
		
		String str = (String) reTest.getPlanContext();
		
		int m = 0;
		int rm = 0;

		int LocalTime = 0;
		while (true) {
			
			//��ȡ��ǰϵͳʱ��
			LocalTime = LocalTotalTime.GetLocalTotalTime();
			
			// 0�������� 1������һ 2�����ڶ� 3�������� 4�������� 5�������� 6��������
			int LocalWeek = TimeUtil.getLocalWeek();
			
			if (weekDay == 7) {
				// ����һ������������
				if (LocalWeek == 1 || LocalWeek == 2 || LocalWeek == 3 || LocalWeek == 4 || LocalWeek == 5) {
					m = JugleTime.IsSameTime(Hour, Minute, Second, str);
					if(m == 1 || m == -5) {
						int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
						int TempTime =LocalTime1 - LocalTime; 
						Time = Time + 600 + TempTime;  //����600��
					}
					if(m != 0 || rm != 0) {
						TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time, str);
						Time = tm.getTime();
						rm = tm.getM();
					}
				}
			} else if (LocalWeek == weekDay) {
				// ����ĳһ������
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
