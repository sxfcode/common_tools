package jdk.thread;

/**
 * The Class DaemonThread.
 * 本示例并不是一个很好的演示示例。
 * 守护线程.
 * 当所有用户线程退出时，守护线程也退出。
 * Daemon(守护)线程 
 * Daemon线程区别一般线程之处是： 
 * 只有虚拟机中的用户线（非Daim程on线程）全部结束，Daemon线程就会立即结束,并且也不会调用finally里的语句。
 * daemon线程所产生的所有线程都是daemon的
 *
 * @date 2013-11-18 10:09:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DaemonThread {
	static class SubThread extends Thread{
		@SuppressWarnings("static-access")
		public void run() {
			System.out.println("SubThread start");
			boolean run = true;
			while (run) {
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("SubThread end");
		}
	}
	static class MainThread extends Thread{
		@SuppressWarnings("static-access")
		public void run() {
			System.out.println("MainThread start");
			//启动子线程
			Thread sub1 = new SubThread();
			//sub1线程为守护线程
			sub1.setDaemon(true);
			sub1.start();
			try {
				Thread.currentThread().sleep(3000);
				System.out.println("子线程存活1:"+sub1.isAlive());
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程存活2:"+sub1.isAlive());
			System.out.println("MainThread end");
		}
	}
	public static void main(String[] args) {
		Thread mainThread = new MainThread();
		//启动mainThread线程
		mainThread.start();
		
	}

}
