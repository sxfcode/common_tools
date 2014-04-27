package task.util;

/**
 * The Class TaskProcessorStatus.
 * 
 * @date 2013-3-21 17:42:31
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class TaskProcessorStatus {

	/** 工作单元类型(当前任务加载器所属工作单元). */
	private int workUnitType;

	/** 任务加载器编号. */
	private int index;

	/** 任务处理器剩余任务. */
	private String remainder;

	/** 任务处理器运行状态. */
	private String runStatus;

	/** 任务处理器最小线程. */
	private String minThread;

	/** 任务处理器最大线程. */
	private String maxThread;

	/** 任务处理器活动线程. */
	private String runningThreadSize;

	/** 是否等待新任务. */
	private String isWaitingNewTask;

	/** 处理任务列表消耗的时间. */
	private String taskListCostTime;

	/** 成功完成任务的总数. */
	private String totalOfSuccessfulTaskNumber;
	
	/** 工作线程睡眠时间. */
	private String workerSleepTime;

	/**
	 * Gets workUnitType.
	 *
	 * @return workUnitType
	 */
	public int getWorkUnitType() {
		return workUnitType;
	}

	/**
	 * Sets workUnitType.
	 *
	 * @param workUnitType comments
	 */
	public void setWorkUnitType(int workUnitType) {
		this.workUnitType = workUnitType;
	}

	/**
	 * Gets index.
	 *
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets index.
	 *
	 * @param index comments
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets remainder.
	 *
	 * @return remainder
	 */
	public String getRemainder() {
		return remainder;
	}

	/**
	 * Sets remainder.
	 *
	 * @param remainder comments
	 */
	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}

	/**
	 * Gets runStatus.
	 *
	 * @return runStatus
	 */
	public String getRunStatus() {
		return runStatus;
	}

	/**
	 * Sets runStatus.
	 *
	 * @param runStatus comments
	 */
	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	/**
	 * Gets minThread.
	 *
	 * @return minThread
	 */
	public String getMinThread() {
		return minThread;
	}

	/**
	 * Sets minThread.
	 *
	 * @param minThread comments
	 */
	public void setMinThread(String minThread) {
		this.minThread = minThread;
	}

	/**
	 * Gets maxThread.
	 *
	 * @return maxThread
	 */
	public String getMaxThread() {
		return maxThread;
	}

	/**
	 * Sets maxThread.
	 *
	 * @param maxThread comments
	 */
	public void setMaxThread(String maxThread) {
		this.maxThread = maxThread;
	}

	/**
	 * Gets runningThreadSize.
	 *
	 * @return runningThreadSize
	 */
	public String getRunningThreadSize() {
		return runningThreadSize;
	}

	/**
	 * Sets runningThreadSize.
	 *
	 * @param runningThreadSize comments
	 */
	public void setRunningThreadSize(String runningThreadSize) {
		this.runningThreadSize = runningThreadSize;
	}

	/**
	 * Gets isWaitingNewTask.
	 *
	 * @return isWaitingNewTask
	 */
	public String getIsWaitingNewTask() {
		return isWaitingNewTask;
	}

	/**
	 * Sets isWaitingNewTask.
	 *
	 * @param isWaitingNewTask comments
	 */
	public void setIsWaitingNewTask(String isWaitingNewTask) {
		this.isWaitingNewTask = isWaitingNewTask;
	}

	/**
	 * Gets taskListCostTime.
	 *
	 * @return taskListCostTime
	 */
	public String getTaskListCostTime() {
		return taskListCostTime;
	}

	/**
	 * Sets taskListCostTime.
	 *
	 * @param taskListCostTime comments
	 */
	public void setTaskListCostTime(String taskListCostTime) {
		this.taskListCostTime = taskListCostTime;
	}

	/**
	 * Gets totalOfSuccessfulTaskNumber.
	 *
	 * @return totalOfSuccessfulTaskNumber
	 */
	public String getTotalOfSuccessfulTaskNumber() {
		return totalOfSuccessfulTaskNumber;
	}

	/**
	 * Sets totalOfSuccessfulTaskNumber.
	 *
	 * @param totalOfSuccessfulTaskNumber comments
	 */
	public void setTotalOfSuccessfulTaskNumber(
			String totalOfSuccessfulTaskNumber) {
		this.totalOfSuccessfulTaskNumber = totalOfSuccessfulTaskNumber;
	}

	/**
	 * Gets workerSleepTime.
	 *
	 * @return workerSleepTime
	 */
	public String getWorkerSleepTime() {
		return workerSleepTime;
	}

	/**
	 * Sets workerSleepTime.
	 *
	 * @param workerSleepTime comments
	 */
	public void setWorkerSleepTime(String workerSleepTime) {
		this.workerSleepTime = workerSleepTime;
	}
	
	

}
