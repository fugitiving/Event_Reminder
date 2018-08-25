package com.gaoke.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.gaoke.entity.ReTest2;
import com.gaoke.util.StringUtil;

public class TimeAddFrm extends JFrame {

	private static final long serialVersionUID = 1L;

	ReTest2 reTest = new ReTest2();
	
	private JPanel contentPane;
	private JTextField PlanTime;
	private JComboBox PlanWeekDay;
	private JTextArea PlanContext;
	
	/**
	 * Launch the application.
	 */
	private static TimeAddFrm frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TimeAddFrm();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimeAddFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimeAddFrm.class.getResource("/images/Signal128.jpg")));
		setResizable(false);
		setTitle("\u589E\u52A0\u4E8B\u4EF6\u63D0\u9192");
		setBounds(100, 100, 499, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setDragMode(-1);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel label_1 = new JLabel("\u91CD\u590D\u65E5\u671F");
		label_1.setBounds(44, 91, 57, 15);
		desktopPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u63D0\u9192\u65F6\u95F4");
		label_2.setBounds(266, 91, 57, 15);
		desktopPane.add(label_2);
		
		PlanTime = new JTextField();
		PlanTime.setForeground(Color.LIGHT_GRAY);
		PlanTime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				PlanTime.setText("");
				PlanTime.setForeground(Color.BLACK);
			}
		});
		PlanTime.setText("\u683C\u5F0F\uFF1A12\uFF1A12\uFF1A12");
		PlanTime.setBounds(333, 88, 113, 21);
		desktopPane.add(PlanTime);
		PlanTime.setColumns(10);
		
		JLabel label = new JLabel("\u63D0\u9192\u5185\u5BB9");
		label.setBounds(44, 142, 75, 15);
		desktopPane.add(label);
		
		PlanContext = new JTextArea();
		PlanContext.setLineWrap(true);
		PlanContext.setBounds(129, 127, 317, 49);
		desktopPane.add(PlanContext);
		
		JButton AddButton = new JButton("\u6DFB\u52A0");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PerformTimeAdd(e);
					resetValue();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		AddButton.setBounds(133, 194, 67, 23);
		desktopPane.add(AddButton);
		
		JButton CancelButton = new JButton("\u53D6\u6D88");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				//frame.setVisible(false);
				//System.exit(0);
			}
		});
		CancelButton.setBounds(289, 194, 67, 23);
		desktopPane.add(CancelButton);
		
		PlanWeekDay = new JComboBox();
		PlanWeekDay.setModel(new DefaultComboBoxModel(new String[] {"\u661F\u671F\u5929", "\u661F\u671F\u4E00 ", "\u661F\u671F\u4E8C ", "\u661F\u671F\u4E09  ", "\u661F\u671F\u56DB ", "\u661F\u671F\u4E94 ", "\u661F\u671F\u516D", "\u5468\u4E00\u5230\u5468\u4E94"}));
		PlanWeekDay.setSelectedIndex(0);
		PlanWeekDay.setFont(new Font("宋体", Font.PLAIN, 12));
		PlanWeekDay.setEnabled(true);
		PlanWeekDay.setBounds(129, 88, 96, 21);
		desktopPane.add(PlanWeekDay);
	}
	
	//重置表格中的数据
	private void resetValue() {
		this.PlanWeekDay.setSelectedIndex(0);
		this.PlanTime.setText("");
		this.PlanContext.setText("");
	}
	
	/**
	 * 增加提醒事件
	 * @param e
	 * @throws IOException
	 */
	private void PerformTimeAdd(ActionEvent e) throws IOException {
		int planWeekDay = PlanWeekDay.getSelectedIndex();
		String planTime = PlanTime.getText();
		String planContext = PlanContext.getText();
		planContext = planContext.replaceAll("\r|\n", "");
		if(StringUtil.isEmpty(planTime) || StringUtil.isEmpty(planContext)) {
			JOptionPane.showMessageDialog(null, "提醒时间或提醒内容为空！");
			return;
		}
		else{
			Pattern p = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))(\\:|\\：)([0-5]?[0-9])((\\s)|((\\:|\\：)([0-5]?[0-9])))))?$");
			boolean b = p.matcher(planTime).matches();
			if(b==false) {
				JOptionPane.showMessageDialog(null, "输入的时间不符合格式：HH:mm:ss");
				return;
			}
		}
		if(StringUtil.isMoreTwentyFiveChar(planContext)) {
			JOptionPane.showMessageDialog(null, "提醒内容超过50个汉字，最好设置少于50个汉字！");
			return;
		}
		FileWriter fw = null;
		try {
			File file = new File("C:\\EventManage");
			if(!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("C:\\EventManage\\add.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(planWeekDay + "\r\n" + planTime + "\r\n" + planContext);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		reTest.setPlanWeekDay(planWeekDay);
		reTest.setPlanTime(planTime);
		reTest.setPlanContext(planContext);
		
		ThreadCommon threadCommon = new ThreadCommon(reTest);
		threadCommon.start();
		JOptionPane.showMessageDialog(null,"添加成功","提示消息", JOptionPane.INFORMATION_MESSAGE);
	}
}
