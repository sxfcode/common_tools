package jdk.newfeatures.jdk16;

import java.awt.Desktop;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The Class CDSUsage
 * . CDS,Console,DeskTop,SystemTray
 * 
 * eg2命令范例如下:
 * D:\dev\workspace\trunk\src>javac newfeatures\jdk16\CDSUsage.java
 * D:\dev\workspace\trunk\src>java newfeatures.jdk16.CDSUsage
 * 
 * @date 2014-7-17 15:42:14
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class CDSUsage {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws URISyntaxException
	 *             the uRI syntax exception
	 */
	public static void main(String[] args) throws IOException,
			URISyntaxException {
		// eg1 desktop
		 Desktop dk = Desktop.getDesktop();
		 dk.browse(new URI("http://www.baidu.com"));
		 
		// eg2 console
		Console cs = System.console();
		String temp = cs.readLine();
		while (!temp.equals("exit")) {
			temp = cs.readLine();
			if (!temp.equals("exit")) {
				cs.writer().write("its over \n");
				cs.flush();
			}
		}
		// eg3 systemTray 貌似只能用于创建新的托盘图标不能遍历所有的托盘图标
		SystemTray st = SystemTray.getSystemTray();
		System.out.println(SystemTray.isSupported());
		TrayIcon[] ti =st.getTrayIcons();
		for (TrayIcon trayIcon : ti) {
			System.out.println("托盘图标:"+trayIcon.getPopupMenu().getName());
		}

	}

}
