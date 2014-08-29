package jdk.detail.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 * The Class SimpleWelcome.
 * 简单的欢迎窗口
 *
 * @date 2013-7-22 15:34:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("serial")
public class SimpleWelcome extends JWindow{
	
	/**
	 * Instantiates a new SimpleWelcome.
	 *
	 * @param fileName comments
	 * @param frame comments
	 * @param waitTime comments
	 */
	public SimpleWelcome(String fileName,JFrame frame,int waitTime){
		super(frame);
		//建立一个用来显示图片的标签x
		JLabel label = new JLabel(new ImageIcon(fileName));
		getContentPane().add(label,BorderLayout.CENTER);
		pack();
		
		//获取屏幕大小
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = label.getPreferredSize();
		
		//将欢迎屏幕放在屏幕中间
		setLocation(screenSize.width/2 -(labelSize.width/2), screenSize.height/2-labelSize.height-2);
		
	    // 增加一个鼠标事件，如果用户点击了欢迎屏幕则关闭
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				setVisible(false);
				dispose();
			}
		});
		
		final int pause = waitTime;
		/**
		 * Swing线程在同一时刻仅能被一个线程所访问。一般来说，这个线程是事件派发线程（event-dispatching thread）。 
		 * 如果需要从事件处理（event-handling）或绘制代码以外的地方访问UI，
		 * 那么可以使用SwingUtilities类的invokeLater()或invokeAndWait()方法。
		 */
		// 关闭欢迎屏幕
		final Runnable closerRunner = new Runnable() {
			@Override
			public void run() {
				setVisible(false);
				dispose();
			}
		};
		
		// 等待关闭
		Runnable waitRunner = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		};
		setVisible(true);
		Thread SplashThread = new Thread(waitRunner,"SplashThread");
		SplashThread.start();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		JFrame frame = new JFrame("欢迎屏幕");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SimpleWelcome welcome = new SimpleWelcome("D:/test2/test.jpg", frame, 6000);
		frame.pack();
		frame.setVisible(true);
	}

}
