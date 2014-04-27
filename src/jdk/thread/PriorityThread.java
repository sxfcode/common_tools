package jdk.thread;

/**
 * The Class PriorityThread.
 * 线程最高优先级10，最低优先级1，默认优先级5，通过常量可以发现。
 * MAX_PRIORITY = 10
 * NORM_PRIORITY = 5
 * MIN_PRIORITY = 1
 *
 * @date 2013-11-18 17:19:18
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class PriorityThread {
	static class UserThread extends Thread {

		/** id. */
		private int id = 0;

		/**
		 * Instantiates a new UserThread.
		 * 
		 * @param id
		 *            comments
		 * @param waitTime
		 *            comments
		 */
		public UserThread(int id) {
			this.id = id;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			System.out.println("UserThread:" + id + " start,priority is"+this.getPriority());
			System.out.println("UserThread:" + id + " end.");
		}

	}
	public static void main(String[] args) {
		UserThread[] uts = new UserThread[20];
		for (int i = 0; i < uts.length; i++) {
			uts[i] = new UserThread(i);
			uts[i].setPriority((i%5)*2+1);
		}
		for (int i = 0; i < uts.length; i++) {
			uts[i].start();
		}
		
	}
	
	

}
