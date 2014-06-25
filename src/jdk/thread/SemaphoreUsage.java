package jdk.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * The Class SemaphoreUsage. 信号里，用来控制同时访问资源的线程数量。
 * 
 * @date 2014-6-25 16:09:04
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class SemaphoreUsage {

	static class User extends Thread {
		private Semaphore sm;
		private int userNumber;

		private User(Semaphore sm, int n) {
			this.sm = sm;
			this.userNumber = n;
		}

		public void run() {
			try {
				//System.out.println("用户" + userNumber + "准备访问资源");
				sm.acquire();
				int rand =new Random(10).nextInt(5);
				System.out.println("用户" + userNumber + "使用资源=>"+rand);
				Thread.sleep(rand*1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sm.release();
			System.out.println("用户" + userNumber + "释放资源");
		}

	}

	public static void main(String[] args) {
		final Semaphore sm = new Semaphore(5);

		for (int i = 0; i < 20; i++) {
			User u = new User(sm, i);
			u.start();

		}
	}

}
