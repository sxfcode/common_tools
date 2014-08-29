package jdk.detail.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Class Timer. 
 * 一种工具，线程用其安排以后在后台线程中执行的任务。可安排任务执行一次，或者定期重复执行。 
 * 与每个 Timer对象相对应的是单个后台线程，用于顺序地执行所有计时器任务。计时器任务应该迅速完成。如果完成某个计时器任务的时间太长，那么它会“独占”
 * 计时器的任务执行线程。因此，这就可能延迟后续任务的执行，而这些任务就可能“堆在一起”，并且在上述不友好的任务最终完成时才能够被快速连续地执行。
 * 
 * 此类是线程安全的：多个线程可以共享单个 Timer 对象而无需进行外部同步。 
 * 此类不 提供实时保证：它使用 Object.wait(long) 方法来安排任务。 
 * 
 * @date 2013-11-19 10:43:02
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class TimerUsage {
	static class UserTimerTask extends TimerTask {
		private String taskName;

		public UserTimerTask(String taskName) {
			this.taskName = taskName;
		}

		@Override
		public void run() {
			System.out.println("task <" + taskName + "> 执 行");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("====== main begin");
		Timer timer = new Timer();
		// 创建一个3秒后执行，只执行3秒的任务
		timer.schedule(new UserTimerTask("延迟3秒"), 3000);
		// 创建一个1秒后执行，每隔1秒执行的定时任务
		timer.schedule(new UserTimerTask("延迟1秒,周期1秒"), 1000, 1000);
		Thread.sleep(5000);
		// 清除任务队列
		timer.purge();
		// 终止任务执行
		timer.cancel();
		System.out.println("====== main end");
	}

}
