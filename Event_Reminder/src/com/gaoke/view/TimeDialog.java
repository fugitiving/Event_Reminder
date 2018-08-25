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
         * ����������ݳ���25�����֣����������ʾ�����С��25�����֣���һ����ʾ
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
       
        //���ô���ť
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        confirm = new JButton("����");
        confirm.setBounds(60,100,100,20);
        confirm.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 0;
				TimeDialog.this.dialog.dispose();
			}
		});
        
        //�����Ƴ�10���Ӱ�ť
        cancel = new JButton("�Ƴ�10����");
        cancel.setBounds(170,100,100,20);
        cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				result = 1;
				TimeDialog.this.dialog.dispose();
			}
		});
        
        //��Label�� Button��ӵ��Ի�����
        dialog = new JDialog(father, true);
        dialog.setTitle("��ʾ: �����ڽ���"+secends+"����Զ��ر�");
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
                	dialog.setTitle("��ʾ: �����ڽ���"+secends+"����Զ��ر�");
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
        dialog.pack();
        
        //�������½���ʾ
        //dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-350, 
        //        Toolkit.getDefaultToolkit().getScreenSize().height-140);
        dialog.setSize(new Dimension(350,180));
        dialog.setLocationRelativeTo(null);
       // dialog.setLocationRelativeTo(father);  //ȡ���Ը������������Ӵ��ڵ�λ��
        dialog.setVisible(true);
        return result;
    }
}
