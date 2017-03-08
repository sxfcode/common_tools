package jdk.detail.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * The Class CyclicBarrierUsage.
 * 一组线程调用CyclicBarrier的await方法，当所有线程都await时，执行CyclicBarrier中的指定方法(在所有线程释放前执行)，最后各线程继续执行各自的流程。
 * CyclicBarrier是可以复用的,当等待的线程都释放后,可以继续使用.
 * 使用all none策略,类似事务,要么等所有线程都到达,然后执行,要么有一个异常离开,则全部离开.
 *
 * 本类演示周期栅栏CyclicBarrier的用法，并演示了周期栅栏的超时用法,其中导致超时的线程会抛出超时异常，其他线程则抛出栅栏损坏异常。
 *
 * @date 2013-8-2 19:18:49
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CyclicBarrierUsage {
	
	/**
	 * The Class UserThread.
	 *
	 * @date 2013-11-19 14:39:16
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserThread extends Thread{
		private CyclicBarrier cyclicBarrier = null;
		private int id;
		public UserThread(CyclicBarrier cyclicBarrier,int id){
			this.id = id;
			this.cyclicBarrier = cyclicBarrier;
		}
		
		public void run(){
			System.out.println("UserThread"+id +"start");
			try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("UserThread"+id +"end");
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int count = 10;
		final CyclicBarrier cb = new CyclicBarrier(3,new Runnable() {
			@Override
			public void run() {
				System.out.println("main CyclicBarrier run,threadId:"+Thread.currentThread().getId());
			}
		});
		for (int i = 0; i < count; i++) {
			UserThread w = new UserThread(cb,i);
			w.start();
		}
		System.out.println("cb.isBroken():"+cb.isBroken());
	}

}
