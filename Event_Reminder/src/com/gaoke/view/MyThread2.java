package com.gaoke.view;

import com.gaoke.entity.ReTest;
import com.gaoke.entity.TimeAndM;
import com.gaoke.util.JugleTime;
import com.gaoke.util.LocalTotalTime;
import com.gaoke.util.TimeUtil;

public class MyThread2 extends Thread {
	
	ReTest reTest;
	
	public MyThread2(ReTest reTest) {
		this.reTest = reTest;
	}
	
	public void run() {
		
		int Hour1 = (int) reTest.getHour();
		int Minute1 = (int) reTest.getMinute();
		int Second1 = (int) reTest.getSecond();
		int Time1 = Hour1*60*60+Minute1*60+Second1;
		
		//0��������  1������һ  2�����ڶ�  3��������  4��������  5��������  6��������  7������һ��������
		int weekDay1 = (int) reTest.getWeekDay();
		
		String str = (String) reTest.getStr();
		
		int m1 = 0;
		int rm1 = 0;
		
		int LocalTime = 0;
		while (true) {
			
			//��ȡ��ǰϵͳʱ��
			LocalTime = LocalTotalTime.GetLocalTotalTime();
			
			// 0�������� 1������һ 2�����ڶ� 3�������� 4�������� 5�������� 6��������
			int LocalWeek = TimeUtil.getLocalWeek();
	
			//�ܱ�ʱ������
			if (weekDay1 == 7) {
				// ����һ������������
				if (LocalWeek == 1 || LocalWeek == 2 || LocalWeek == 3 || LocalWeek == 4 || LocalWeek == 5) {
					m1 = JugleTime.IsSameTime(Hour1, Minute1, Second1, str);
					if(m1 == 1 || m1 == -5) {
						int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
						int TempTime =LocalTime1 - LocalTime; 
						Time1 = Time1 + 600 + TempTime;  //����600��
					}
					if (m1 != 0 || rm1 != 0) {
						TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time1, str);
						Time1 = tm.getTime();
						rm1 = tm.getM();
					}
				}
			} else if (LocalWeek == weekDay1) {
				// ����ĳһ������
				m1 = JugleTime.IsSameTime(Hour1, Minute1, Second1, str);
				if(m1 == 1 || m1 == -5) {
					int LocalTime1 = LocalTotalTime.GetLocalTotalTime();					
					int TempTime =LocalTime1 - LocalTime; 
					Time1 = Time1 + 600 + TempTime;  //����600��
				}
				if(m1 != 0 || rm1 != 0) {
					TimeAndM tm = TimeAndM.IsSameLocalTimeandTime(LocalTime, Time1, str);
					Time1 = tm.getTime();
					rm1 = tm.getM();
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
