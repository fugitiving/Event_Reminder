package com.gaoke.test;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class TestTray2 {
	public static void main(String[] args) {
        MainFrm frm = new MainFrm();
        frm.setVisible(true);
    }
}
class MainFrm extends JFrame implements WindowListener{

	  private static final long serialVersionUID = -3059928131346032935L;
	  /// 32*32��pngͼ��
	  private static Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/Signal128.jpg"));
	  /// TrayIcon����
	  private static TrayIcon trayIcon = new TrayIcon(image,"xx����");

	  public MainFrm() {
	      super();
	      this.setSize(300, 200);
	      this.getContentPane().setLayout(null);
	      this.setTitle("Hello world!");
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      /// ע�᱾����Window����¼�������
	      this.addWindowListener(this);
	  }

	  public void windowActivated(WindowEvent e) {}

	  public void windowClosed(WindowEvent e) {}

	  public void windowClosing(WindowEvent e) {}

	  public void windowDeactivated(WindowEvent e) {}

	  public void windowDeiconified(WindowEvent e) {
	  }

	  public void windowIconified(WindowEvent e) {
	      /// ������ϵͳ�Ƿ�֧��ϵͳ����
	      if (!SystemTray.isSupported()) {
	          return;
	      }

	      final MainFrm parent = this;
	      final SystemTray systemTray = SystemTray.getSystemTray();

	      /// ����TrayIcon�Զ�����ͼ���С��������ô����֮�����ٹ�עͼƬ�ߴ���
	      trayIcon.setImageAutoSize(true);
	      /// TrayIcon��ӻ��¼��ļ������������ָ������ڣ�ɾ��TrayIcon
	      trayIcon.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent event) {
	              /// ��ʾ�����ڣ����ô���Ϊ����״̬
	              parent.setVisible(true);
	              parent.setExtendedState(JFrame.NORMAL);
	              /// ��ϵͳ����֮���Ƴ�TrayIcon����Ϊͬһ��TrayIcon�����������
	              systemTray.remove(trayIcon);
	          }
	      });

	      try {
	          /// ��ϵͳ����֮�����TrayIcon
	          systemTray.add(trayIcon);
	      } catch (AWTException exception) {
	          exception.printStackTrace();
	      }

	      /// ���ر�����
	      parent.setVisible(false);
	  }

	  public void windowOpened(WindowEvent e) {}
}
