package com.gaoke.view;

import com.gaoke.entity.ReTest;
import com.gaoke.entity.TimeAndM;
import com.gaoke.util.JugleTime;
import com.gaoke.util.LocalTotalTime;
import com.gaoke.util.TimeUtil;

public class MyThread3 extends Thread {
	
	ReTest reTest;
	
	public MyThread3(ReTest reTest) {
		this.reTest = reTest;
	}
	
	public void run() {
		
		int Hour2 = (int) reTest.getHour();
		int Minute2 = (int) reTest.getMinute();
		int Second2 = (int) reTest.getSecond();
		int Time2 = Hour2*60*60+Minute2*60+Second2;
		
		//0��������  1������һ  2�����ڶ�  3��������  4��������  5��������  6��������  7������һ��������
		int weekDay2 = (int) reTest.getWeekDay();
		
		String str = (String) reTest.getStr();
		
		int m2 = 0;
		int rm2 = 0;
		
		int LocalTime = 0;
		while (true) {
			
			//��ȡ��ǰϵͳʱ��
			LocalTime = LocalTotalTime.GetLocalTotalTime();
			
			// 0�������� 1������һ 2�����ڶ� 3�������� 4�������� 5�������� 6��������
			int LocalWeek = TimeUtil.getLocalWeek();
			
			//�չ�����ʱ������
			if (weekDay2 == 7) {
				// ����һ������������
				if (LocalWeek == 1 || LocalWeek == 2 || LocalWeek == 3 || LocalWeek == 4 || LocalWeek == 5) {
					m2 = JugleTime.IsSameTime(Hour2, Minute2, Second2, str);
					if(m2 == 1 || m2 == -5) {
						int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
						int TempTime =LocalTime1 - LocalTime; 
						Time2 = Time2 + 600 + TempTime;  //����600��
					}
					if(m2 != 0 || rm2 != 0) {
						TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time2, str);
						Time2 = tm.getTime();
						rm2 = tm.getM();
					}
				}
			} else if (LocalWeek == weekDay2) {
				// ����ĳһ������
				m2 = JugleTime.IsSameTime(Hour2, Minute2, Second2, str);
				if(m2 == 1 || m2 == -5) {
					int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
					int TempTime =LocalTime1 - LocalTime; 
					Time2 = Time2 + 600 + TempTime;  //����600��
				}
				if(m2 != 0 || rm2 != 0) {
					TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time2, str);
					Time2 = tm.getTime();
					rm2 = tm.getM();
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
