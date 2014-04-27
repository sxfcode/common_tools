package jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConditionUsage.
 * Lock是一种逻辑锁，而并非语法锁。可以通过编程的方式进行扩展，比synchronized语法灵活的多。
 * Lock是针对对象方法的锁定,多个Condition适用于复杂条件下的并发控制	.
 * 同一对象可以由不同的条件唤醒。
 * 示例中methodA和methodB互相影响和唤醒。
 * 
 * @date 2013-11-20 17:44:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ConditionUsage {
	
	/**
	 * The Class UserData.
	 *
	 * @date 2013-11-26 16:37:54
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	public static class UserData{
		private Lock lock = new ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		public UserData(){
		}
		
		public void methodA(){
			lock.lock();
			try {
				System.out.println(1);
				boolean flag = true;
				while (flag) {
					condition1.await();
				}
				condition2.signal();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(2);
			} finally{
				System.out.println(3);
				lock.unlock();
			}
			return;
		}
		
		public void methodB(){
			lock.lock();
			try {
				boolean flag = true;
				while (flag) {
					condition2.await();
				}
				condition1.signal();
			} catch (Exception e) {
				// TODO: handle exception
			} finally{
				lock.unlock();
			}
		}
		
		
	}
	
	/**
	 * The Class UserThread.
	 *
	 * @date 2013-11-25 16:53:09
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	public static class UserThread extends Thread{
		
		/**
		 * run.
		 */
		public void run(){
			
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UserData u = new UserData();
		u.methodA();
		
	}

}
