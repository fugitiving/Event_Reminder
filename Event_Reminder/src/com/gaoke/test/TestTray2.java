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
	  /// 32*32的png图像
	  private static Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/Signal128.jpg"));
	  /// TrayIcon对象
	  private static TrayIcon trayIcon = new TrayIcon(image,"xx助手");

	  public MainFrm() {
	      super();
	      this.setSize(300, 200);
	      this.getContentPane().setLayout(null);
	      this.setTitle("Hello world!");
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      /// 注册本窗口Window相关事件监听器
	      this.addWindowListener(this);
	  }

	  public void windowActivated(WindowEvent e) {}

	  public void windowClosed(WindowEvent e) {}

	  public void windowClosing(WindowEvent e) {}

	  public void windowDeactivated(WindowEvent e) {}

	  public void windowDeiconified(WindowEvent e) {
	  }

	  public void windowIconified(WindowEvent e) {
	      /// 检测操作系统是否支持系统托盘
	      if (!SystemTray.isSupported()) {
	          return;
	      }

	      final MainFrm parent = this;
	      final SystemTray systemTray = SystemTray.getSystemTray();

	      /// 设置TrayIcon自动调整图像大小，看来这么设置之后不用再关注图片尺寸了
	      trayIcon.setImageAutoSize(true);
	      /// TrayIcon添加击事件的监听器，点击后恢复主窗口，删除TrayIcon
	      trayIcon.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent event) {
	              /// 显示主窗口，设置窗口为正常状态
	              parent.setVisible(true);
	              parent.setExtendedState(JFrame.NORMAL);
	              /// 从系统托盘之中移除TrayIcon，因为同一个TrayIcon不能添加两次
	              systemTray.remove(trayIcon);
	          }
	      });

	      try {
	          /// 往系统托盘之中添加TrayIcon
	          systemTray.add(trayIcon);
	      } catch (AWTException exception) {
	          exception.printStackTrace();
	      }

	      /// 隐藏本窗口
	      parent.setVisible(false);
	  }

	  public void windowOpened(WindowEvent e) {}
}
