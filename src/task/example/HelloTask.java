package task.example;

import task.base.Task;

/**
 * The Class HelloTask.
 *
 * @date 2013-11-15 15:03:11
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class HelloTask extends Task {

	@Override
	public boolean execute() {
		return false;
	}

	/* (non-Javadoc)
	 * @see task.base.Task#getId()
	 */
	@Override
	public String getId() {
		return null;
	}

}
