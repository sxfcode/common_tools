package jdk.thread;

/**
 * The Class WaitThread.
 * 线程的等待与唤醒,
 * 注意wait和notify是Object对象的方法，在调用时，必须持有Object对象的锁，才能对其进行wait和notify
 * 线程本质也是一个对象，线程的wait和notify实际上是对象的wait和notify.
 *
 * @date 2013-11-19 11:23:05
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class WaitThread {
	
	static class WaitNotifyThread extends Thread{
		
		public void run(){
			System.out.println("子线程 begin");
			System.out.println("子线程-- 挂起");
			try {
				synchronized (this) {
					wait();
					System.out.println("子线-- 唤醒");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程  end");
		}
		
		public void notifySelf(){
			synchronized (this) {
				notify();
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("主线程开始");
		WaitNotifyThread wn = new WaitNotifyThread();
		System.out.println("子线程开始");
		wn.start();
		System.out.println("主线程睡眠5秒，5秒后唤醒子线程");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 唤醒方法1，在线程外部持有锁
		synchronized (wn) {
			wn.notify();
		}
		
		// 唤醒方法2，在线程内部持有锁
		wn.notifySelf();
		System.out.println("主线程结束");
	}
}
