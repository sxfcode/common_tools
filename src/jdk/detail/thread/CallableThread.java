package jdk.detail.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * The Class CallableThread.
 *  Callable对象类似runnable接口，不同的是，它可以返回结果,并且是可以抛出异常的。
 * FutureTask 同样是带返回结果的，主要供Executors使用。
 * 实际上我们可以通过实现Future接口实现同样的效果,甚至定义自己的任务类，结果查询方法，通过自己的线程池调用。
 * 
 * @date 2013-11-20 15:37:57
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CallableThread {

	/**
	 * The Class UserCallable.
	 * 
	 * @date 2013-11-20 15:40:16
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	@SuppressWarnings("rawtypes")
	static class UserCallable implements Callable {

		/** taskType. */
		private int taskType = 0;

		/**
		 * Instantiates a new UserCallable.
		 *
		 * @param taskType comments
		 */
		public UserCallable(int taskType) {
			this.taskType = taskType;
		}

		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Boolean call() throws Exception {
			System.out.println("UserCallable begin");
			if (taskType == 0) {
				System.out.println("taskType 1 run");
				return true;
			} else {
				int i = 30;
				while (i > 0) {
					i = i - 1;
					System.out.println("taskType 2 run,i=" + i);
					Thread.sleep(1000);
				}
				return true;
			}
		}
	}

	/**
	 * The Class UserFutureTask.
	 *
	 * @date 2013-11-20 17:38:48
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	@SuppressWarnings("rawtypes")
	static class UserFutureTask extends FutureTask {

		/**
		 * Instantiates a new UserFutureTask.
		 *
		 * @param callable comments
		 */
		@SuppressWarnings("unchecked")
		public UserFutureTask(Callable callable) {
			super(callable);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		System.out.println("主线程开始");
		UserFutureTask uf = new UserFutureTask(new UserCallable(1));
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future result = es.submit(uf);
		Thread.sleep(5000);
		// 检查任务执行结果
		System.out.println("task status ,is done:" + result.isDone()
				+ ",is cancel:" + result.isCancelled());
		Thread.sleep(5000);
		// 调用get方法会一直阻塞线程，直到线程执行结束
		// System.out.println("result:"+result.get());
		// 取消任务的执行,如果任务已经执行完毕，则取消操作失败，不会抛出异常。
		result.cancel(true);
		System.out.println("task status ,is done:" + result.isDone()
				+ ",is cancel:" + result.isCancelled());
		// 已取消的任务，如果获取结果则会抛出异常
		// System.out.println("result:"+result.get());
		
		// 关闭Executors
		es.shutdown();
		System.out.println("主线程结束");

	}
}
