package com.gaoke.util;

import com.gaoke.view.TimeDialog;
import com.gaoke.view.TimerTest;

public class JugleTime {
	public static int IsSameTime(int Hour, int Minute, int Second, String str) {
		//��ȡ��ǰϵͳʱ��
		int LocalHour = TimeUtil.getLocalHour();
		int LocalMinute = TimeUtil.getLocalMinute();
		int LocalSecond = TimeUtil.getLocalSecond();
		
		if(Hour==LocalHour && Minute==LocalMinute && Second==LocalSecond) {
			TimerTest tt = new TimerTest();
			TimeDialog d = new TimeDialog();
			// tt�ǳ����������࣬�����ĶԻ������������10�����ʧ��10���Ӻ��������
			int m = d.showDialog(tt, str, 10);
			//System.out.println("===m: "+m);
			return m;
			//������ֵ0Ϊ����  1Ϊ�Ƴ�10�����ٴ�����  -5Ϊ������10���Ӻ��ٴ�����
			/*while(m==1 || m==-5) {
				//�������������Ƴ�10���Ӵ������ı����ȡ��ֵ����10����
				Hour = Hour + 10*1000;
				
			}*/
		}
		return -1; //�����
	}
}
