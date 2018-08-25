package com.gaoke.test;

import java.awt.Color;
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.geom.Ellipse2D;  
import java.awt.geom.Line2D;  
import java.util.Calendar;  
import java.util.GregorianCalendar;  
import java.util.Timer;  
import java.util.TimerTask;
 
import javax.swing.JLabel;
  
 
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
//画时钟类
@SuppressWarnings("serial")
public class DrawClock extends JFrame {  
    MyPanel clockPanel;  
    Ellipse2D.Double e;  
    int x;  
    int y;  
    Line2D.Double hourLine;
    int hourLinex;
    int hourLiney;
    Line2D.Double minLine; 
    int minLinex;
    int minLiney;
    Line2D.Double secondLine; 
    int secondLinex;
    int secondLiney;
    GregorianCalendar calendar;  
    int hour;  
    int minute;  
    int second;  
    public static final int X = 125;  
    public static final int Y = 125; 
    public static final int RADIAN = 100;  
 
    JLabel label1 = new JLabel(hour + ":" + minute + ":" + second);
  
    public DrawClock() {
        setBounds(400,200,300,400);
        clockPanel = new MyPanel();
        label1.setBounds(50, 235, 200, 30);
        label1.setForeground(Color.red);
        add(label1);
        add(clockPanel);  
        Timer t = new Timer();  
        Task task = new Task();  
        t.schedule(task, 0, 1000);  
    }  
  
    public static void main(String[] args) {  
        DrawClock t = new DrawClock();  
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        t.setSize(300, 300);
        t.setVisible(true);  
  
    }  
	class MyPanel extends JPanel 
    {  
        public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            Graphics2D g2 = (Graphics2D) g; 
            int i = 0;
            for(i = 0; i < 60; i ++)
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
            g2.drawString("12", 120, 22); //设置数字12的位置  
            g2.drawString("6", 122, 238);  //设置数字6的位置
            g2.drawString("9", 15, 130);  //设置数字9的位置
            g2.drawString("3", 228, 130);  //设置数字3的位置
            g2.drawOval(25, 25, 200, 200);  //设置画圆的位置及半径
            g2.setColor(Color.green);
            g2.drawLine(X, Y, hourLinex, hourLiney);
            g2.setColor(Color.blue); 
            g2.drawLine(X, Y, minLinex, minLiney);
            g2.setColor(Color.red);
            g2.drawLine(X, Y, secondLinex, secondLiney);
        }  
    }  
  
    class Task extends TimerTask {  
        public void run() {  
            calendar = new GregorianCalendar();  
            hour = calendar.get(Calendar.HOUR);  
            minute = calendar.get(Calendar.MINUTE);  
            second = calendar.get(Calendar.SECOND);
            
            hourLinex = (int) (X + 60 * Math.sin(hour * (Math.PI / 6)));
            hourLiney = (int) (Y - 60 * Math.cos(hour * (Math.PI / 6))); 
             
            minLinex = (int) (X + 75  * Math.sin(minute * (Math.PI / 30)));
            minLiney = (int) (Y - 75  * Math.cos(minute * (Math.PI / 30)));
             
            secondLinex = (int) (X + 85  * Math.sin(second * (Math.PI / 30)) );
            secondLiney = (int) (Y - 85  * Math.cos(second * (Math.PI / 30)) );
            
            label1.setText("Hour:" + hour + "  " + "Minute:"+ minute + "  " + "second:" + second);
            repaint();  
            
        }  
    }  
  
}