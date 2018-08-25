package com.gaoke.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.gaoke.entity.ReTest2;
import com.gaoke.entity.WeekDayEnum;
import com.gaoke.util.StringUtil;

public class TimeManageFrm extends JFrame {

	private static final long serialVersionUID = 1L;

	ReTest2 reTest = new ReTest2();
	
	private JPanel contentPane;
	private JTextField planTimeTx;
	private JTable timeTable;
	private JComboBox planWeekDayComBo;
	private JTextArea planContextArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeManageFrm frame = new TimeManageFrm();
					/*Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
					int x = (int) screensize.getWidth() / 2 - frame.getWidth() / 2;
					int y = (int) screensize.getHeight() / 2 - frame.getHeight() / 2;	
					frame.setLocation(x, y);*/
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimeManageFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimeManageFrm.class.getResource("/images/Signal128.jpg")));
		setResizable(false);
		setTitle("\u63D0\u9192\u4E8B\u4EF6\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 498, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 10, 459, 136);
		desktopPane.add(scrollPane);
		
		timeTable = new JTable();
		
		timeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				timeTableMousePressed(me);
			}
		});
		timeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u91CD\u590D\u65E5\u671F", "\u63D0\u9192\u65F6\u95F4", "\u63D0\u9192\u5185\u5BB9"
			}
		));
		timeTable.getColumnModel().getColumn(0).setResizable(false);
		timeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		timeTable.getColumnModel().getColumn(0).setMinWidth(80);
		timeTable.getColumnModel().getColumn(0).setMaxWidth(80);
		timeTable.getColumnModel().getColumn(1).setResizable(false);
		timeTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		timeTable.getColumnModel().getColumn(1).setMinWidth(80);
		timeTable.getColumnModel().getColumn(1).setMaxWidth(80);
		timeTable.getColumnModel().getColumn(2).setPreferredWidth(85);
		scrollPane.setViewportView(timeTable);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u63D0\u9192\u4E8B\u4EF6\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 156, 459, 181);
		desktopPane.add(panel);
		
		JLabel label = new JLabel("\u91CD\u590D\u65E5\u671F");
		
		JLabel label_2 = new JLabel("\u63D0\u9192\u65F6\u95F4");
		
		planTimeTx = new JTextField();
		planTimeTx.setForeground(new Color(0, 0, 0));
		planTimeTx.setColumns(10);
		
		JLabel label_6 = new JLabel("\u63D0\u9192\u5185\u5BB9");
		
		planContextArea = new JTextArea();
		planContextArea.setLineWrap(true);
		
		JButton Modify = new JButton("\u4FEE\u6539");
		Modify.setIcon(null);
		Modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeModifyActionPerformed(e);
			}
		});
		
		JButton Delete = new JButton("\u5220\u9664");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeDeleteActionPerformed(e);
			}
		});
		
		planWeekDayComBo = new JComboBox();
		planWeekDayComBo.setModel(new DefaultComboBoxModel(new String[] {"\u661F\u671F\u5929", "\u661F\u671F\u4E00 ", "\u661F\u671F\u4E8C ", "\u661F\u671F\u4E09  ", "\u661F\u671F\u56DB ", "\u661F\u671F\u4E94 ", "\u661F\u671F\u516D", "\u5468\u4E00\u5230\u5468\u4E94"}));
		planWeekDayComBo.setSelectedIndex(0);
		planWeekDayComBo.setFont(new Font("宋体", Font.PLAIN, 12));
		planWeekDayComBo.setEnabled(true);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_6)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(planWeekDayComBo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(50)
									.addComponent(label_2)
									.addGap(18)
									.addComponent(planTimeTx, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
								.addComponent(planContextArea, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(124)
							.addComponent(Modify)
							.addGap(99)
							.addComponent(Delete)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(planTimeTx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2)
								.addComponent(label))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(planContextArea, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(Modify)
										.addComponent(Delete)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(33)
									.addComponent(label_6))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(planWeekDayComBo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		this.fillTable();
	}
	
	/**
	 * 点击Table表中的事件之后，填充下面的提醒事件设置
	 * @param me
	 */
	private void timeTableMousePressed(MouseEvent me) {
		int row = this.timeTable.getSelectedRow();

		String value = timeTable.getValueAt(row, 0).toString();
		for(int i = 0; i< 8; ++i) {
			if(value.equals("星期天")){
				this.planWeekDayComBo.setSelectedIndex(0);
			}else if(value.equals("星期一")){
				this.planWeekDayComBo.setSelectedIndex(1);
			}else if(value.equals("星期二")){
				this.planWeekDayComBo.setSelectedIndex(2);
			}else if(value.equals("星期三")){
				this.planWeekDayComBo.setSelectedIndex(3);
			}else if(value.equals("星期四")){
				this.planWeekDayComBo.setSelectedIndex(4);
			}else if(value.equals("星期五")){
				this.planWeekDayComBo.setSelectedIndex(5);
			}else if(value.equals("星期六")){
				this.planWeekDayComBo.setSelectedIndex(6);
			}else if(value.equals("周一到周五")){
				this.planWeekDayComBo.setSelectedIndex(7);
			}
		}
		
		this.planTimeTx.setText((String)timeTable.getValueAt(row, 1));
		this.planContextArea.setText((String)timeTable.getValueAt(row, 2));
	}
	
	//删除提醒事件
	private void TimeDeleteActionPerformed(ActionEvent e) {
		String repeat = planWeekDayComBo.getSelectedIndex()+"";
		String strTimeTx = planTimeTx.getText();
		String strContext = planContextArea.getText();
		
		//判断是否选中了Table表中的数据
		if(StringUtil.isEmpty(repeat) || StringUtil.isEmpty(strTimeTx) || StringUtil.isEmpty(strContext)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的事件");
			return;
		}
		ArrayList<String> arr = new ArrayList<String>();
        File f=new File("C:\\EventManage\\add.txt");
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(f));
            String temp;
            int i = 0;
            boolean b1 = false, b2=false, b3=false;
            String str1 = null, str2 = null, str3 = null;
            while((temp=br.readLine())!=null){
            	if(i==0) {
            		str1 = temp;
            		b1 =temp.equals(repeat);
            	}
            	if(i==1) {
            		str2 = temp;
            		b2 =temp.equals(strTimeTx);
            	}
            	if(i==2) {
            		str3 = temp;
            		b3 =temp.equals(strContext);
            	}
            	i++;
            	if(i == 3) {
            		if(!(b1==true && b2 == true && b3 == true)) {
            			arr.add(str1);
            			arr.add(str2);
            			arr.add(str3);
            		}
            		i = 0;
            	}
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        //清空文本文件内容
		try {
			FileOutputStream testfile = new FileOutputStream("C:\\EventManage\\add.txt");
			try {
				testfile.write(new String("").getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}
		
        FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f1 = new File("C:\\EventManage\\add.txt");
			fw = new FileWriter(f1, true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		String str;
		for(int i = 0; i < arr.size(); ++i) {
			str = arr.get(i);
			pw.println(str);
		}
		
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JOptionPane.showMessageDialog(null,"删除成功","提示消息", JOptionPane.INFORMATION_MESSAGE);
		this.fillTable();
		resetValue();
	}
	
	//修改提醒事件
	private void TimeModifyActionPerformed(ActionEvent e) {
		int n = timeTable.getSelectedRow();
		if(n<0) {
			JOptionPane.showMessageDialog(null, "请选择要修改的事件");
			resetValue();
			return;
		}
		String s1 = timeTable.getValueAt(n, 0).toString();
		String s2 = timeTable.getValueAt(n, 1).toString();
		String s3 = timeTable.getValueAt(n, 2).toString();
		
		String repeat = planWeekDayComBo.getSelectedIndex()+"";
		String strTimeTx = planTimeTx.getText();
		String strContext = planContextArea.getText();
		strContext = strContext.replaceAll("\r|\n", "");
		if(StringUtil.isMoreTwentyFiveChar(strContext)) {
			JOptionPane.showMessageDialog(null, "修改提醒内容超过50个汉字，最好设置少于50个汉字！");
			return;
		}
		
		ArrayList<String> arr = new ArrayList<String>();
        File f=new File("C:\\EventManage\\add.txt");
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(f));
            String temp;
            int i = 0;
            boolean b1 = false, b2=false, b3=false;
            String str1 = null, str2 = null, str3 = null;
            while((temp=br.readLine())!=null){
            	if(i==0) {
            		str1 = temp;
            		if(temp.equals("0")){
            			temp="星期天";
            		}else if(temp.equals("1")){
            			temp="星期一";
            		}else if(temp.equals("2")){
            			temp="星期二";
            		}else if(temp.equals("3")){
            			temp="星期三";
            		}else if(temp.equals("4")){
            			temp="星期四";
            		}else if(temp.equals("5")){
            			temp="星期五";
            		}else if(temp.equals("6")){
            			temp="星期六";
            		}else if(temp.equals("7")){
            			temp="周一到周五";
            		}
            		b1 =temp.equals(s1);
            	}
            	if(i==1) {
            		str2 = temp;
            		b2 =temp.equals(s2);
            	}
            	if(i==2) {
            		str3 = temp;
            		b3 =temp.equals(s3);
            	}
            	i++;
            	if(i == 3) {
            		if(!(b1==true && b2 == true && b3 == true)) {
            			arr.add(str1);
            			arr.add(str2);
            			arr.add(str3);
            		}
            		else {
            			arr.add(repeat);
            			arr.add(strTimeTx);
            			arr.add(strContext);
            			/*//修改事件之后增加一个新线程，使得修改之后就生效，但是修改了的那个线程如何释放？
            			reTest.setPlanWeekDay(Integer.parseInt(repeat));
            			reTest.setPlanTime(strTimeTx);
            			reTest.setPlanContext(strContext);
            			
            			ThreadCommon threadCommon = new ThreadCommon(reTest);
    					threadCommon.start();*/
            		}
            		i = 0;
            	}
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
      //清空文本文件内容
  		try {
  			FileOutputStream testfile = new FileOutputStream("C:\\EventManage\\add.txt");
  			try {
  				testfile.write(new String("").getBytes());
  			} catch (IOException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
  		} catch (FileNotFoundException e3) {
  			// TODO Auto-generated catch block
  			e3.printStackTrace();
  		}
  		
        FileWriter fw = null;
  		try {
  			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
  			File f1 = new File("C:\\EventManage\\add.txt");
  			fw = new FileWriter(f1, true);
  		} catch (IOException e1) {
  			e1.printStackTrace();
  		}
  		PrintWriter pw = new PrintWriter(fw);
  		String str;
  		for(int i = 0; i < arr.size(); ++i) {
  			str = arr.get(i);
  			pw.println(str);
  		}
  		
  		pw.flush();
  		try {
  			fw.flush();
  			pw.close();
  			fw.close();
  		} catch (IOException e2) {
  			e2.printStackTrace();
  		}
  		JOptionPane.showMessageDialog(null,"修改成功，重启之后生效","提示消息", JOptionPane.INFORMATION_MESSAGE);
  		resetValue();
  		this.fillTable();
	}
	
	private void resetValue() {
		this.planWeekDayComBo.setSelectedIndex(0);
		this.planTimeTx.setText("");
		this.planContextArea.setText("");
	}
	/**
	 * 填充提醒事件表格
	 * @param timeProperty
	 */
	private void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel)timeTable.getModel();
		dtm.setRowCount(0);//设置成0行
		
        File f=new File("C:\\EventManage\\add.txt");
        if(f.exists()) {
	        BufferedReader br=null;
	        try{
	            br=new BufferedReader(new FileReader(f));
	            String temp;
	            int i = 0;
	            Vector<String> v = new Vector<String>();
	            while((temp=br.readLine())!=null){
	            	if(i==0) {
	            		if(temp.equals("0")){
	            			v.add(WeekDayEnum.Sun.getName());
	            		}else if(temp.equals("1")){
	            			v.add(WeekDayEnum.Mon.getName());
	            		}else if(temp.equals("2")){
	            			v.add(WeekDayEnum.Tues.getName());
	            		}else if(temp.equals("3")){
	            			v.add(WeekDayEnum.Wed.getName());
	            		}else if(temp.equals("4")){
	            			v.add(WeekDayEnum.Thur.getName());
	            		}else if(temp.equals("5")){
	            			v.add(WeekDayEnum.Fri.getName());
	            		}else if(temp.equals("6")){
	            			v.add(WeekDayEnum.Sat.getName());
	            		}else if(temp.equals("7")){
	            			v.add(WeekDayEnum.MontoFri.getName());
	            		}
	            		i++;
	            	}
	            	else {
	    			v.add(temp);
	    			i++;
	    			if(i == 3) {
	            		dtm.addRow(v);
	            		i=0;
	            		v = new Vector<String>();
	            	}
	            	}
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        }  
	}
	
}
