package task.base;


/**
 * The Class Task. 任务抽象类。 继承本类需要实现：execute()和getId()方法
 * 
 * @date 2013-3-6 15:04:55
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class Task {

	/**
	 * 执行任务内容,任务具体要执行的业务逻辑需要开发人员自己去实现，这里只提供抽象方法.
	 */
	public abstract boolean execute();

	/**
	 * 任务id,用来标识是否是同一个任务..
	 * 
	 * @return id 使用字符串类型，适用范围更广。
	 */
	public abstract String getId();

	/**
	 * 检查是否同一任务.
	 * 
	 * @param obj
	 *            comments
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Task target = (Task) obj;
		if (this.getId().equals(target.getId())) {
			return true;
		}
		return false;
	}

}
