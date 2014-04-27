package task.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class WorkUnitStatus. 工作单元运行状态信息，包括任务加载器和任务处理器信息。
 * 
 * @date 2013-3-21 17:28:42
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class WorkUnitStatus {

	/** 工作单元类型(用来区分不同的工作单元). */
	private int workUnitType;

	/** 工作单元的名称. */
	private String workUnitTypeName;

	/** 任务加载器状态. */
	private List<TaskLoaderStatus> taskLoaderStatus = new ArrayList<TaskLoaderStatus>();

	/** 任务处理器状态. */
	private List<TaskProcessorStatus> taskProcessorStatus = new ArrayList<TaskProcessorStatus>();

	/**
	 * Instantiates a new WorkUnitStatus.
	 *
	 * @param workUnitType comments
	 * @param taskLoaderStatus comments
	 * @param taskProcessorStatus comments
	 */
	public WorkUnitStatus(int workUnitType,
			List<TaskLoaderStatus> taskLoaderStatus,
			List<TaskProcessorStatus> taskProcessorStatus) {
		this.workUnitType = workUnitType;
		this.workUnitTypeName = WorkUnit.getWorkUnitName(workUnitType);
		this.taskLoaderStatus = taskLoaderStatus;
		this.taskProcessorStatus = taskProcessorStatus;
	}

	/**
	 * Gets taskLoaderStatus.
	 * 
	 * @return taskLoaderStatus
	 */
	public List<TaskLoaderStatus> getTaskLoaderStatus() {
		return taskLoaderStatus;
	}

	/**
	 * Sets taskLoaderStatus.
	 * 
	 * @param taskLoaderStatus
	 *            comments
	 */
	public void setTaskLoaderStatus(List<TaskLoaderStatus> taskLoaderStatus) {
		this.taskLoaderStatus = taskLoaderStatus;
	}

	/**
	 * Gets taskProcessorStatus.
	 * 
	 * @return taskProcessorStatus
	 */
	public List<TaskProcessorStatus> getTaskProcessorStatus() {
		return taskProcessorStatus;
	}

	/**
	 * Sets taskProcessorStatus.
	 * 
	 * @param taskProcessorStatus
	 *            comments
	 */
	public void setTaskProcessorStatus(
			List<TaskProcessorStatus> taskProcessorStatus) {
		this.taskProcessorStatus = taskProcessorStatus;
	}

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
	 * Gets workUnitTypeName.
	 *
	 * @return workUnitTypeName
	 */
	public String getWorkUnitTypeName() {
		return this.workUnitTypeName;
	}

	/**
	 * Sets workUnitTypeName.
	 *
	 * @param workUnitTypeName comments
	 */
	public void setWorkUnitTypeName(String workUnitTypeName) {
		this.workUnitTypeName = workUnitTypeName;
	}	
}
