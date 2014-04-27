package task.example;

import java.util.List;

import task.base.Task;
import task.base.TaskLoader;
import task.base.TaskProcessor;

/**
 * The Class HelloTaskLoader.
 *
 * @date 2013-11-15 15:04:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class HelloTaskLoader extends TaskLoader {

	/**
	 * Instantiates a new HelloTaskLoader.
	 *
	 * @param taskProcessor comments
	 */
	public HelloTaskLoader(TaskProcessor taskProcessor) {
		super(taskProcessor);
	}

	@Override
	public List<Task> getTaskList() {
		return null;
	}

}
