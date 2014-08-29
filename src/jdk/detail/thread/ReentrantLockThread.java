package jdk.detail.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ReentrantLockThread. ReentrantLock,可重入锁。
 * 锁的可重入性指的是在当前线程未释放锁的情况下，获取锁，并继续执行。
 * (一个携带锁的方法调用另一个携带锁的方法，是否就是可重入锁的一个表现。
 * 假设一个线程对象中 有3个方法，A,B,C,
 * 需要A成为原子操作，B成为原子操作，C为非原子操作，同时B中有调用A的操作。 这种B嵌套A的操作可以看做是一个可重入锁的一种应用。
 * )
 * 
 * 其应用可参考task.base.TaskProcessor.java类的使用。
 * 
 * 
 * 
 * @date 2013-11-25 14:39:05
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ReentrantLockThread {

	
	/**
	 * The Class UserThread.
	 *
	 * @date 2013-11-25 14:48:59
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserThread extends Thread {
		
		/** The Constant lock. */
		private final static ReentrantLock  lock = new ReentrantLock();
		
		
		/**
		 * 可重入互斥锁的用法.
		 *
		 * @param i comments
		 */
		public void methodA(int i){
			lock.lock();
			try {
				// 在methodA中调用methodB，methodA和methodB都需要保持原子性。
				methodB(0);
				// do something others
			} catch (Exception e) {
				// TODO: handle exception
			} finally{
				lock.unlock();
			}
			
		}
		
		/**
		 *
		 * @param i comments
		 */
		public void methodB(int i){
			lock.lock();
			try {
				// do something
			} catch (Exception e) {
				// TODO: handle exception
			} finally{
				lock.unlock();
			}
			
		}
		
		/**
		 * methodC.
		 * 这只是一个普通方法用来跟A,B方法对比
		 */
		public void methodC(){
			System.out.println("c");
		}
		
		/**
		 * run.
		 */
		public void run(){
			methodA(1);
		}

	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UserThread ut = new UserThread();
		ut.start();
	}

}
