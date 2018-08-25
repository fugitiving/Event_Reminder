package com.gaoke.view;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gaoke.entity.ReTest2;

public class MainFrm extends JFrame {

	private static final long serialVersionUID = 1L;

	static ReTest2 reTest = new ReTest2();
	
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	
	private static MainFrm frame;
	public static void main(String[] args) {
		
		//设置系统自带的三个提醒事件
		new TimePlan();
		
		//加载自己设置的事件提醒文件
		LoadTime();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					frame = new MainFrm();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void LoadTime() {
		File f=new File("C:\\EventManage\\add.txt");
		if(f.exists()) {
	        BufferedReader br=null;
	        try{
	            br=new BufferedReader(new FileReader(f));
	            String temp;
	            int i = 0;
	            while((temp=br.readLine())!=null){
					if (i == 0) {
						reTest.setPlanWeekDay(Integer.parseInt(temp));
					}
					if (i == 1) {
						reTest.setPlanTime(temp);
					}
					if (i == 2) {
						reTest.setPlanContext(temp);
					}
					++i;
					if (i == 3) {
						ThreadCommon threadCommon = new ThreadCommon(reTest);
						threadCommon.start();
						reTest = new ReTest2();
						i = 0;
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

	/**
	 * Create the frame.
	 */
	public MainFrm() {
		setResizable(false);
		setTitle("\u4E3B\u754C\u9762");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/Signal128.jpg")));
		setBounds(100, 100, 501, 378);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u8BBE\u7F6E");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u63D0\u9192\u4E8B\u4EF6\u7BA1\u7406");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				TimeManageFrm timeManageFrm = new TimeManageFrm();
				timeManageFrm.setLocationRelativeTo(null);
				timeManageFrm.setVisible(true);
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem menuItem_3 = new JMenuItem("\u63D0\u9192\u4E8B\u4EF6\u6DFB\u52A0");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeAddFrm timeAddFrm = new TimeAddFrm();
				timeAddFrm.setLocationRelativeTo(null);
				timeAddFrm.setVisible(true);
			}
		});
		menu.add(menuItem_3);
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u5173\u4E8E");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutFrm aboutFrm = new AboutFrm();
				aboutFrm.setLocationRelativeTo(null);
				aboutFrm.setVisible(true);
			}
		});
		menu_1.add(menuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//系统托盘
		Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/Signal128.jpg"));
		TrayIcon trayIcon = new TrayIcon(image,"提醒助手");
		this.setIconImage(image);
		trayIcon.setImageAutoSize(true);
		
		//鼠标双击打开主界面
		trayIcon.addMouseListener(new MouseAdapter()
        {
           // 鼠标事件
           public void mouseClicked(MouseEvent e)
           {
              // 判断是否双击了鼠标
              if (e.getClickCount() == 2)
              {
            	  if (!frame.isShowing())
                  {
                	  frame.setVisible(true);
                  }
              }
           }
        });
		
		PopupMenu popupMenu = new PopupMenu();
		MenuItem openItem = new MenuItem("打开");
        popupMenu.add(openItem);
        popupMenu.addSeparator();
        MenuItem setItem = new MenuItem("设置");
        popupMenu.add(setItem);
        popupMenu.addSeparator();
        MenuItem exitItem = new MenuItem("退出");
        popupMenu.add(exitItem);
        
        //右击打开应用程序
        openItem.addActionListener(new ActionListener()
        {
           // 鼠标事件
           public void actionPerformed(ActionEvent e)
           {
              if (!frame.isShowing())
              {
            	  frame.setVisible(true);
              }
           }
        });
        
      //右击设置应用程序
        setItem.addActionListener(new ActionListener()
        {
           // 鼠标事件
           public void actionPerformed(ActionEvent e)
           {
        	   TimeManageFrm timeManageFrm = new TimeManageFrm();
        	   timeManageFrm.setLocationRelativeTo(null);
        	   timeManageFrm.setVisible(true);
           }
        });
        
        //右击退出关闭应用程序
        exitItem.addActionListener(new ActionListener()
        {
            // 鼠标事件
            public void actionPerformed(ActionEvent e)
            {
             	  System.exit(0);
            }
         });
        
        // 为托盘图标加弹出菜弹
        trayIcon.setPopupMenu(popupMenu);
		
		SystemTray systemTray = SystemTray.getSystemTray();
		try {
			systemTray.add(trayIcon);
		} catch (AWTException exception) {
			exception.printStackTrace();
		}
		
		label1.setBounds(187, 270, 150, 30);
        label1.setForeground(Color.BLUE);
        getContentPane().add(label1);
		MyPanel clockPanel = new MyPanel();
        getContentPane().add(clockPanel);  
        Timer t = new Timer();  
        Task task = new Task();
        t.schedule(task, 0, 1000);  
        
	}
	

	/*private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}*/
	
	
	Ellipse2D.Double e;  
    int hourLinex;
    int hourLiney;
    int minLinex;
    int minLiney;
    int secondLinex;
    int secondLiney;
    GregorianCalendar calendar = new GregorianCalendar();
    int hour = 0;  
    int minute = 0;  
    int second = 0; 
    int X = 243;  
    int Y = 141; 
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String strFirstStartTime = df.format(new Date());
    JLabel label1 = new JLabel(strFirstStartTime);
    
	class MyPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {  
	        super.paintComponent(g);  
	        Graphics2D g2 = (Graphics2D) g; 
	        for(int i = 0; i < 60; ++i)
	        {
	            int zx1 = (int) (X + 100  * Math.sin(i * (Math.PI / 30) ));  
	            int zy1 = (int) (Y + 100  * Math.cos(i * (Math.PI / 30) ));
	            int h;
	            if( i % 5 == 0)
	            	h = 90;
	            else
	            	h = 95;
	            int zx2 = (int) (X + h  * Math.sin(i * (Math.PI / 30) ));
	            int zy2 = (int) (Y + h  * Math.cos(i * (Math.PI / 30) ));
	            g2.setColor(Color.red);
	            g2.drawLine(zx1, zy1, zx2, zy2);
	        }
	        g2.setColor(Color.black);
	        g2.drawString("12", 235, 38);  
	        g2.drawString("6", 239, 255);  
	        g2.drawString("9", 134, 145);  
	        g2.drawString("3", 344, 145);
	        g2.drawOval(143, 41, 200, 200);
	        
	        //设置时针、分针、秒针的颜色及粗细
	        g2.setColor(Color.green);
	        g2.setStroke(new BasicStroke(3.0f));
	        g2.drawLine(X, Y, hourLinex, hourLiney);
	        g2.setColor(Color.blue); 
	        g2.setStroke(new BasicStroke(2.0f));
	        g2.drawLine(X, Y, minLinex, minLiney);
	        g2.setColor(Color.red);
	        g2.setStroke(new BasicStroke(1.0f));
	        g2.drawLine(X, Y, secondLinex, secondLiney);
	    }
	}
	
	class Task extends TimerTask{
		public void run() {  
            calendar = new GregorianCalendar();  
            hour = calendar.get(Calendar.HOUR);  
            minute = calendar.get(Calendar.MINUTE);  
            second = calendar.get(Calendar.SECOND);
           
            minLinex = (int) (X + 75  * Math.sin(minute * (Math.PI / 30)));
            minLiney = (int) (Y - 75  * Math.cos(minute * (Math.PI / 30)));
             
            secondLinex = (int) (X + 85  * Math.sin(second * (Math.PI / 30)) );
            secondLiney = (int) (Y - 85  * Math.cos(second * (Math.PI / 30)) );
            
            hourLinex = (int) (X + 60 * Math.sin(hour * (Math.PI / 6) + (minute * (Math.PI / 30))/12));
            hourLiney = (int) (Y - 60 * Math.cos(hour * (Math.PI / 6)+ (minute * (Math.PI / 30))/12)); 
            
        	String strEverySecondTime = df.format(new Date());
            label1.setText(strEverySecondTime);
            repaint();  
        }
	}
}