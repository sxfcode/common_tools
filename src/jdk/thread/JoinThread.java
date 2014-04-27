package jdk.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Class JoinThread.
 * 线程的结合。
 * 当一个线程需要等待另一个线程结束时，叫做线程的结合。
 * 
 * @date 2013-11-18 16:43:10
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class JoinThread {

	/**
	 * The Class UserThread.
	 * 
	 * @date 2013-11-18 16:53:53
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserThread extends Thread {

		/** id. */
		private int id = 0;

		/** 睡眠次数. */
		private int waitTime = 0;

		/**
		 * Instantiates a new UserThread.
		 * 
		 * @param id
		 *            comments
		 * @param waitTime
		 *            comments
		 */
		public UserThread(int id, int waitTime) {
			this.id = id;
			this.waitTime = waitTime;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@SuppressWarnings("static-access")
		public void run() {
			System.out.println("UserThread:" + id + " start.");
			try {
				for (int i = 0; i < waitTime; i++) {
					System.out.println("UserThread:" + id + " sleep "+i+"=========");
					Thread.currentThread().sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("UserThread:" + id + " end.");
		}

	}
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread start");
		List<UserThread> threads = new ArrayList<UserThread>();
		Random ran = new Random(10L);
		for (int i = 0; i < 4; i++) {
			UserThread ut = new UserThread(i, ran.nextInt(10));
			threads.add(ut);
		}
		for (UserThread userThread : threads) {
			userThread.start();
		}
		for (UserThread userThread : threads) {
			// 主线程会等待该线程执行完毕，才会继续执行
			userThread.join();
		}
		System.out.println("Main Thread end");
	}

}
