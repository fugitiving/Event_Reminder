package com.gaoke.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.gaoke.entity.ReTest;

public class TimePlan extends JFrame {

	private static final long serialVersionUID = 1L;
	
	ReTest reTest = new ReTest();
	ReTest reTest2 = new ReTest();
	ReTest reTest3 = new ReTest();
	
	public JPanel contentPane;
	public JSpinner PlanHour;
	public JSpinner PlanMinute;
	public JSpinner PlanSecond;
	public JComboBox PlanWeekDay;
	
	public JComboBox WeekReportDay;
	public JSpinner WeekReportPlanHour;
	public JSpinner WeekReportPlanMinute;
	public JSpinner WeekReportPlanSecond;
	
	public JComboBox DayWorkLoadTime;
	public JSpinner DayWorkLoadHour;
	public JSpinner DayWorkLoadMinute;
	public JSpinner DayWorkLoadSecond;
	
	public JLabel LabelDayPlanTime;
	public JLabel LabelWeekReportTime;
	public JLabel LabelDayWorkLoadTime;
	
	/**
	 * Launch the application.
	 */
	
	/*private static TimePlan frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TimePlan();
					frame.setLocationRelativeTo(null);  //窗口居中
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TimePlan() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimePlan.class.getResource("/images/Signal128.jpg")));
		setResizable(false);
		setTitle("\u5DE5\u4F5C\u63D0\u9192\u5C0F\u5DE5\u5177");
		this.setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 605, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setToolTipText("");
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		LabelDayPlanTime = new JLabel("\u65E5\u8BA1\u5212\u63D0\u9192\u65F6\u95F4\uFF1A");
		LabelDayPlanTime.setFont(new Font("宋体", Font.PLAIN, 14));
		LabelDayPlanTime.setBounds(74, 67, 128, 22);
		desktopPane.add(LabelDayPlanTime);
		
		LabelDayWorkLoadTime = new JLabel("\u65E5\u5DE5\u4F5C\u91CF\u63D0\u9192\u65F6\u95F4\uFF1A");
		LabelDayWorkLoadTime.setFont(new Font("宋体", Font.PLAIN, 14));
		LabelDayWorkLoadTime.setBounds(74, 173, 128, 22);
		desktopPane.add(LabelDayWorkLoadTime);
		
		LabelWeekReportTime = new JLabel("\u5468\u62A5\u63D0\u9192\u65F6\u95F4\uFF1A");
		LabelWeekReportTime.setFont(new Font("宋体", Font.PLAIN, 14));
		LabelWeekReportTime.setBounds(74, 118, 114, 22);
		desktopPane.add(LabelWeekReportTime);
		
		PlanSecond = new JSpinner();
		PlanSecond.setEnabled(false);
		PlanSecond.setFont(new Font("Times New Roman", Font.BOLD, 12));
		PlanSecond.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		PlanSecond.setBounds(478, 67, 46, 22);
		desktopPane.add(PlanSecond);
		
		PlanHour = new JSpinner();
		PlanHour.setEnabled(false);
		PlanHour.setFont(new Font("Times New Roman", Font.BOLD, 12));
		PlanHour.setModel(new SpinnerNumberModel(10, 0, 23, 1));
		PlanHour.setBounds(342, 67, 46, 22);
		desktopPane.add(PlanHour);
		
		PlanMinute = new JSpinner();
		PlanMinute.setEnabled(false);
		PlanMinute.setForeground(Color.WHITE);
		PlanMinute.setBackground(Color.WHITE);
		PlanMinute.setFont(new Font("Times New Roman", Font.BOLD, 12));
		PlanMinute.setModel(new SpinnerNumberModel(30, 0, 59, 1));
		PlanMinute.setBounds(408, 67, 46, 22);
		desktopPane.add(PlanMinute);
		
		PlanWeekDay = new JComboBox();
		PlanWeekDay.setEnabled(false);
		PlanWeekDay.setFont(new Font("宋体", Font.PLAIN, 12));
		PlanWeekDay.setModel(new DefaultComboBoxModel(new String[] {"\u661F\u671F\u5929", "\u661F\u671F\u4E00 ", "\u661F\u671F\u4E8C ", "\u661F\u671F\u4E09  ", "\u661F\u671F\u56DB ", "\u661F\u671F\u4E94 ", "\u661F\u671F\u516D", "\u5468\u4E00\u5230\u5468\u4E94"}));
		PlanWeekDay.setSelectedIndex(1);
		PlanWeekDay.setBounds(218, 68, 96, 21);
		desktopPane.add(PlanWeekDay);
		
		WeekReportDay = new JComboBox();
		WeekReportDay.setEnabled(false);
		WeekReportDay.setFont(new Font("宋体", Font.PLAIN, 12));
		WeekReportDay.setModel(new DefaultComboBoxModel(new String[] {"\u661F\u671F\u5929", "\u661F\u671F\u4E00 ", "\u661F\u671F\u4E8C ", "\u661F\u671F\u4E09  ", "\u661F\u671F\u56DB ", "\u661F\u671F\u4E94 ", "\u661F\u671F\u516D", "\u5468\u4E00\u5230\u5468\u4E94"}));
		WeekReportDay.setSelectedIndex(4);
		WeekReportDay.setBounds(218, 119, 96, 21);
		desktopPane.add(WeekReportDay);
		
		WeekReportPlanHour = new JSpinner();
		WeekReportPlanHour.setEnabled(false);
		WeekReportPlanHour.setModel(new SpinnerNumberModel(16, 0, 23, 1));
		WeekReportPlanHour.setFont(new Font("Times New Roman", Font.BOLD, 12));
		WeekReportPlanHour.setBounds(342, 118, 46, 22);
		desktopPane.add(WeekReportPlanHour);
		
		WeekReportPlanMinute = new JSpinner();
		WeekReportPlanMinute.setEnabled(false);
		WeekReportPlanMinute.setModel(new SpinnerNumberModel(30, 0, 59, 1));
		WeekReportPlanMinute.setFont(new Font("Times New Roman", Font.BOLD, 12));
		WeekReportPlanMinute.setBounds(408, 118, 46, 22);
		desktopPane.add(WeekReportPlanMinute);
		
		WeekReportPlanSecond = new JSpinner();
		WeekReportPlanSecond.setEnabled(false);
		WeekReportPlanSecond.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		WeekReportPlanSecond.setFont(new Font("Times New Roman", Font.BOLD, 12));
		WeekReportPlanSecond.setBounds(478, 118, 46, 22);
		desktopPane.add(WeekReportPlanSecond);
		
		DayWorkLoadTime = new JComboBox();
		DayWorkLoadTime.setEnabled(false);
		DayWorkLoadTime.setFont(new Font("宋体", Font.PLAIN, 12));
		DayWorkLoadTime.setModel(new DefaultComboBoxModel(new String[] {"\u661F\u671F\u5929", "\u661F\u671F\u4E00 ", "\u661F\u671F\u4E8C ", "\u661F\u671F\u4E09  ", "\u661F\u671F\u56DB ", "\u661F\u671F\u4E94 ", "\u661F\u671F\u516D", "\u5468\u4E00\u5230\u5468\u4E94"}));
		DayWorkLoadTime.setSelectedIndex(7);
		DayWorkLoadTime.setBounds(218, 174, 96, 21);
		desktopPane.add(DayWorkLoadTime);
		
		DayWorkLoadHour = new JSpinner();
		DayWorkLoadHour.setEnabled(false);
		DayWorkLoadHour.setModel(new SpinnerNumberModel(17, 0, 23, 1));
		DayWorkLoadHour.setFont(new Font("Times New Roman", Font.BOLD, 12));
		DayWorkLoadHour.setBounds(342, 173, 46, 22);
		desktopPane.add(DayWorkLoadHour);
		
		DayWorkLoadMinute = new JSpinner();
		DayWorkLoadMinute.setEnabled(false);
		DayWorkLoadMinute.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		DayWorkLoadMinute.setFont(new Font("Times New Roman", Font.BOLD, 12));
		DayWorkLoadMinute.setBounds(408, 173, 46, 22);
		desktopPane.add(DayWorkLoadMinute);
		
		DayWorkLoadSecond = new JSpinner();
		DayWorkLoadSecond.setEnabled(false);
		DayWorkLoadSecond.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		DayWorkLoadSecond.setFont(new Font("Times New Roman", Font.BOLD, 12));
		DayWorkLoadSecond.setBounds(478, 173, 46, 22);
		desktopPane.add(DayWorkLoadSecond);
		
		
		/**
		 * 设置系统默认的三个事件
		 * 初始化的时候绑定
		 */
		setTime();
	}

	private void setTime() {
		/**
		 * 获取文本框设置的提醒时间
		 * 日计划时间
		 */
		int Hour = (int) PlanHour.getValue();
		int Minute = (int) PlanMinute.getValue();
		int Second = (int) PlanSecond.getValue();
		
		//0是星期天  1是星期一  2是星期二  3是星期三  4是星期四  5是星期五  6是星期六  7是星期一到星期五
		int weekDay = PlanWeekDay.getSelectedIndex();
		
		//获取JLabel标签的前n-1个文字，去掉最后一个省略号
		String strDayPlanTime = LabelDayPlanTime.getText();
		strDayPlanTime = strDayPlanTime.substring(0, strDayPlanTime.length()-1);
		
		reTest.setHour(Hour);
		reTest.setMinute(Minute);
		reTest.setSecond(Second);
		reTest.setWeekDay(weekDay);
		reTest.setStr(strDayPlanTime);

		/**
		 * 获取文本框设置的提醒时间
		 * 周报时间
		 */
		int Hour1 = (int) WeekReportPlanHour.getValue();
		int Minute1 = (int) WeekReportPlanMinute.getValue();
		int Second1 = (int) WeekReportPlanSecond.getValue();
		//0是星期天  1是星期一  2是星期二  3是星期三  4是星期四  5是星期五  6是星期六  7是星期一到星期五
		int weekDay1 = WeekReportDay.getSelectedIndex();
		String strWeekReportTime = LabelWeekReportTime.getText();
		strWeekReportTime = strWeekReportTime.substring(0, strWeekReportTime.length()-1);
		
		reTest2.setHour(Hour1);
		reTest2.setMinute(Minute1);
		reTest2.setSecond(Second1);
		reTest2.setWeekDay(weekDay1);
		reTest2.setStr(strWeekReportTime);
		
		/**
		 * 获取文本框设置的提醒时间
		 * 日工作量提交使时间
		 */
		int Hour2 = (int) DayWorkLoadHour.getValue();
		int Minute2 = (int) DayWorkLoadMinute.getValue();
		int Second2 = (int) DayWorkLoadSecond.getValue();
		//0是星期天  1是星期一  2是星期二  3是星期三  4是星期四  5是星期五  6是星期六  7是星期一到星期五
		int weekDay2 = DayWorkLoadTime.getSelectedIndex();
		
		String strDayWorkLoadTime = LabelDayWorkLoadTime.getText();
		strDayWorkLoadTime = strDayWorkLoadTime.substring(0, strDayWorkLoadTime.length()-1);
		
		reTest3.setHour(Hour2);
		reTest3.setMinute(Minute2);
		reTest3.setSecond(Second2);
		reTest3.setWeekDay(weekDay2);
		reTest3.setStr(strDayWorkLoadTime);
		
		MyThread myThread = new MyThread(reTest);
		MyThread2 myThread2 = new MyThread2(reTest2);
		MyThread3 myThread3 = new MyThread3(reTest3);
		myThread.start();
		myThread2.start();
		myThread3.start();
	}
}