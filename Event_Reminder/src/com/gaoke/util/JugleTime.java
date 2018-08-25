package com.gaoke.util;

import com.gaoke.view.TimeDialog;
import com.gaoke.view.TimerTest;

public class JugleTime {
	public static int IsSameTime(int Hour, int Minute, int Second, String str) {
		//获取当前系统时间
		int LocalHour = TimeUtil.getLocalHour();
		int LocalMinute = TimeUtil.getLocalMinute();
		int LocalSecond = TimeUtil.getLocalSecond();
		
		if(Hour==LocalHour && Minute==LocalMinute && Second==LocalSecond) {
			TimerTest tt = new TimerTest();
			TimeDialog d = new TimeDialog();
			// tt是程序主窗口类，弹出的对话框如果不处理10秒后消失，10分钟后继续提醒
			int m = d.showDialog(tt, str, 10);
			//System.out.println("===m: "+m);
			return m;
			//当返回值0为处理  1为推迟10分钟再次提醒  -5为不处理，10分钟后再次提醒
			/*while(m==1 || m==-5) {
				//如果不处理或者推迟10分钟处理，则将文本框获取的值增加10分钟
				Hour = Hour + 10*1000;
				
			}*/
		}
		return -1; //不相等
	}
}
