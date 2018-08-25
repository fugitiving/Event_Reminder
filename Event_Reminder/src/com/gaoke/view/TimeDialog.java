package com.gaoke.view;
 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class TimeDialog {
	//private String message = null;
    private int secends = 0;
    private JLabel label = new JLabel();
    private JLabel label2 = new JLabel();
    private JButton confirm,cancel; 
    private JDialog dialog = null;
    
    int result = -5;
    public int showDialog(JFrame father, String message, int sec) {
       // this.message = message;
        secends = sec;
        
        int messagelength = message.length();
        
        /**
         * 如果提醒内容超过25个汉字，则分两行显示，如果小于25个汉字，则一行显示
         */
        int y = 0;
        if(messagelength <= 25) {
        	int x = 170-messagelength*7;
        	y = 20;
        	label.setText(message);
        	label.setBounds(x,y,390,60);
        }else {
        	int x = 165-23*7;
        	int x2 = 172-(messagelength-26)*7;
        	y = 10;
        	label.setText(message.substring(0,25));
            label2.setText(message.substring(25));
            label.setBounds(x,y,390,60);
            label2.setBounds(x2, y+20, 390, 60);
        }
       
        //设置处理按钮
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        confirm = new JButton("处理");
        confirm.setBounds(60,100,100,20);
        confirm.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 0;
				TimeDialog.this.dialog.dispose();
			}
		});
        
        //设置推迟10分钟按钮
        cancel = new JButton("推迟10分钟");
        cancel.setBounds(170,100,100,20);
        cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 1;
				TimeDialog.this.dialog.dispose();
			}
		});
        
        //将Label和 Button添加到对话框中
        dialog = new JDialog(father, true);
        dialog.setTitle("提示: 本窗口将在"+secends+"秒后自动关闭");
        dialog.setLayout(null);
        dialog.add(label);
        dialog.add(label2);
        dialog.add(confirm);
        dialog.add(cancel);
        s.scheduleAtFixedRate(new Runnable() {
            
            @Override
            public void run() {
                TimeDialog.this.secends--;
                if(TimeDialog.this.secends == 0) {
                    TimeDialog.this.dialog.dispose();
                }else {
                	dialog.setTitle("提示: 本窗口将在"+secends+"秒后自动关闭");
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
        dialog.pack();
        
        //窗口右下角显示
        //dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-350, 
        //        Toolkit.getDefaultToolkit().getScreenSize().height-140);
        dialog.setSize(new Dimension(350,180));
        dialog.setLocationRelativeTo(null);
       // dialog.setLocationRelativeTo(father);  //取消以父窗口来定义子窗口的位置
        dialog.setVisible(true);
        return result;
    }
}
