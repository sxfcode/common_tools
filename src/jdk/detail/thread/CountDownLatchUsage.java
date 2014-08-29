package jdk.detail.thread;

import java.util.concurrent.CountDownLatch;

/**
 * The Class CountDownLatchUsage.
 * CountDownLatch是一个计数器，等待他的线程只有在CountDownLatch的计数为0时，才继续执行。
 * CountDownLatch不阻塞调用countDown的线程， 只阻塞调用await的线程.
 * 
 * @date 2013-8-2 19:23:24
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CountDownLatchUsage {
	
	/**
	 * The Class UserThread.
	 *
	 * @date 2013-11-19 14:19:19
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserThread extends Thread {
		
		/** count. */
		private CountDownLatch count;
		
		/** id. */
		private int id;

		/**
		 * Instantiates a new UserThread.
		 *
		 * @param count comments
		 * @param id comments
		 */
		public UserThread(CountDownLatch count, int id) {
			this.count = count;
			this.id = id;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			System.out.println("thread" + id + " process bussiness data.");
			count.countDown();
			System.out.println("thread" + id + " continue.");
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("主线程开始");
		CountDownLatch count = new CountDownLatch(3);
		UserThread u1 = new UserThread(count, 1);
		UserThread u2 = new UserThread(count, 2);
		UserThread u3 = new UserThread(count, 3);
		u1.start();
		u2.start();
		u3.start();
		System.out.println("主线程await");
		count.await();
		System.out.println("主线程结束");
	}

}
