package task.util;

/**
 * The Class TaskLoaderStatus.
 * 
 * @date 2013-3-21 17:43:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class TaskLoaderStatus {

	/** 工作单元类型(当前任务加载器所属工作单元). */
	private int workUnitType;

	/** 任务加载器编号. */
	private int index;

	/** 任务加载器运行状态. */
	private String runStatus;

	/** 任务加载器加载频率. */
	private long frequency;

	/** 每次加载任务的最大数量. */
	private int maxLoadTaskNumber;

	/** 获取任务列表耗费的时间. */
	private long taskListCostTime;

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
	 * @param workUnitType
	 *            comments
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
	 * @param index
	 *            comments
	 */
	public void setIndex(int index) {
		this.index = index;
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
	 * Gets frequency.
	 *
	 * @return frequency
	 */
	public long getFrequency() {
		return frequency;
	}

	/**
	 * Sets frequency.
	 *
	 * @param frequency comments
	 */
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets maxLoadTaskNumber.
	 * 
	 * @return maxLoadTaskNumber
	 */
	public int getMaxLoadTaskNumber() {
		return maxLoadTaskNumber;
	}

	/**
	 * Sets maxLoadTaskNumber.
	 * 
	 * @param maxLoadTaskNumber
	 *            comments
	 */
	public void setMaxLoadTaskNumber(int maxLoadTaskNumber) {
		this.maxLoadTaskNumber = maxLoadTaskNumber;
	}

	/**
	 * Gets taskListCostTime.
	 *
	 * @return taskListCostTime
	 */
	public long getTaskListCostTime() {
		return taskListCostTime;
	}

	/**
	 * Sets taskListCostTime.
	 *
	 * @param taskListCostTime comments
	 */
	public void setTaskListCostTime(long taskListCostTime) {
		this.taskListCostTime = taskListCostTime;
	}

}
