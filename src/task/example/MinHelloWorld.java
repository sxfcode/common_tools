package task.example;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import task.base.Task;
import task.base.TaskLoader;
import task.base.TaskProcessor;

/**
 * The Class LafasoTaskManager. 工具类用来启动任务
 * 
 * @date 2013-3-6 15:05:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class MinHelloWorld {

	/**
	 * startBusiness.
	 * 
	 * @param taskLoader
	 *            comments
	 * @param taskProcessor
	 *            comments
	 * @param queue
	 *            comments
	 */
	public static void helloWorld() {
		Queue<Task> taskQueue = new LinkedBlockingQueue<Task>();
		// 启动任务处理器
		TaskProcessor taskProcessor = new TaskProcessor(taskQueue);
		taskProcessor.startProcess();
		// 启动任务加载器
	    TaskLoader taskLoader = new HelloTaskLoader(taskProcessor);
		taskLoader.startLoad();
	}

}
