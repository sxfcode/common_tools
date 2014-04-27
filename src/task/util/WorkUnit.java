package task.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import task.base.TaskLoader;
import task.base.TaskProcessor;

/**
 * The Class WorkUnit. 每个业务工作通过一个工作单元表示。 包括若干个任务加载器和任务处理器.
 * 通常情况下一个工作单元包含一个任务加载器和一个任务处理器。可以视情况增加或减少。
 * 
 * @date 2013-3-21 16:51:32
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class WorkUnit {
	
	/** 工作单元类型(用来区分不同的工作单元). */
	private int workUnitType;

	/** 任务加载器组. */
	private List<TaskLoader> taskLoaders = new ArrayList<TaskLoader>();

	/** 任务处理器组. */
	private List<TaskProcessor> taskProcessors = new ArrayList<TaskProcessor>();
	

	/** The Constant workUnitNames. */
	public static final Map<Integer, String> workUnitNames = new HashMap<Integer, String>();

	
	static {
		init();
	}
	
	/**
	 * 初始化.
	 */
	private static void init(){
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_ORDER, TaskConstants.WORKUNIT_TYPE_NAME_ORDER);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_ORDER_ACCOUNT,
				TaskConstants.WORKUNIT_TYPE_NAME_ORDER_ACCOUNT);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_SHOP_ORDER,
				TaskConstants.WORKUNIT_TYPE_NAME_SHOP_ORDER);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_SHOP_ORDER_COMPLETED,
				TaskConstants.WORKUNIT_TYPE_NAME_SHOP_ORDER_COMPLETED);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_PRODUCT_COUPON,
				TaskConstants.WORKUNIT_TYPE_NAME_PRODUCT_COUPON);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_VOUCHER_USED,
				TaskConstants.WORKUNIT_TYPE_NAME_VOUCHER_USED);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_VOUCHER_USED_RETURN,
				TaskConstants.WORKUNIT_TYPE_NAME_VOUCHER_USED_RETURN);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_POP_SHOP_ORDER,
				TaskConstants.WORKUNIT_TYPE_NAME_POP_SHOP_ORDER);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_ORDER_PRE_SALE,
				TaskConstants.WORKUNIT_TYPE_NAME_ORDER_PRE_SALE);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_EXCEP_RECORD,
				TaskConstants.WORKUNIT_TYPE_NAME_EXCEP_RECORD);
		workUnitNames.put(TaskConstants.WORKUNIT_TYPE_CLOUD_INVENTORY,
				TaskConstants.WORKUNIT_TYPE_NAME_CLOUD_INVENTORY);
	}

	/**
	 * Instantiates a new WorkUnit. 默认创建工作单元时，不启动工作单元。
	 * 
	 * @param workUnitType
	 *            comments
	 * @param taskLoader
	 *            comments
	 * @param taskProcessor
	 *            comments
	 */
	public WorkUnit(int workUnitType, TaskLoader taskLoader,
			TaskProcessor taskProcessor) {
		this.workUnitType = workUnitType;
		// 加入工作单元
		taskLoaders.add(taskLoader);
		taskProcessors.add(taskProcessor);
	}

	/**
	 * Instantiates a new WorkUnit. 默认创建工作单元时，不启动工作单元。
	 * 
	 * @param workUnitType
	 *            comments
	 * @param taskLoaders
	 *            comments
	 * @param taskProcessors
	 *            comments
	 */
	public WorkUnit(int workUnitType, List<TaskLoader> taskLoaders,
			List<TaskProcessor> taskProcessors) {
		this.workUnitType = workUnitType;
		// 加入工作单元
		this.taskLoaders.addAll(taskLoaders);
		this.taskProcessors.addAll(taskProcessors);

	}

	/**
	 * 添加任务加载器.
	 * 
	 * @param taskLoader
	 *            任务加载器
	 */
	public void addTaskLoader(TaskLoader taskLoader) {
		taskLoaders.add(taskLoader);
	}

	/**
	 * 添加任务处理器.
	 * 
	 * @param taskProcessor
	 *            任务处理器
	 */
	public void addTaskProcessor(TaskProcessor taskProcessor) {
		taskProcessors.add(taskProcessor);
	}

	/**
	 * 启动所有任务加载器和任务处理器 为了避免任务堆积，启动时先启动任务处理器，再启动任务加载器..
	 */
	public void startAllWork() {
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessor.startProcess();
		}
		for (TaskLoader taskLoader : taskLoaders) {
			taskLoader.startLoad();
		}
	}

	/**
	 * 关闭所有任务加载器和任务处理器 为了保证现有任务执行完毕，停止工作单元时，先停止任务加载器，再停止任务处理器.
	 * 注意工作单元虽然停止，但是工作单元中的正常线程仍然存活，除非服务器停止，否则将一直存在，以避免频繁创建和销毁线程带来的性能消耗。
	 */
	public void stopAllWork() {
		for (TaskLoader taskLoader : taskLoaders) {
			taskLoader.stopLoad();
		}
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessor.stopProcess();
		}
	}

	/**
	 * 获取工作单元的工作状态.
	 * 
	 * @return workUnitStatus
	 */
	public WorkUnitStatus getWorkUnitStatus() {
		// 为了保持松散耦合，保证base目录下的3个基本类可以单独工作,
		// 这里不在LafasoTaskLoader和LafasoTaskProcessor中添加一次性读取所有状态的方法。
		// 当需要提高效率时可以酌情修改。
		List<TaskLoaderStatus> taskLoaderStatus = new ArrayList<TaskLoaderStatus>();
		List<TaskProcessorStatus> taskProcessorStatus = new ArrayList<TaskProcessorStatus>();
		for (TaskLoader taskLoader : taskLoaders) {
			taskLoaderStatus.add(createTaskLoaderStatus(taskLoader));
		}
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessorStatus.add(createTaskProcessorStatus(taskProcessor));
		}
		return new WorkUnitStatus(workUnitType, taskLoaderStatus,
				taskProcessorStatus);
	}

	/**
	 * 获取指定编号的任务加载器的状态.
	 * 
	 * @param i
	 *            comments
	 * @return taskLoaderStatus
	 */
	public TaskLoaderStatus getTaskLoaderStatus(int i) {
		return createTaskLoaderStatus(taskLoaders.get(i));

	}

	/**
	 * 获取指定编号的任务处理器的状态.
	 * 
	 * @param i
	 *            comments
	 * @return taskProcessorStatus
	 */
	public TaskProcessorStatus getTaskProcessorStatus(int i) {
		return createTaskProcessorStatus(taskProcessors.get(i));
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
	 * Gets workUnitName.
	 * 
	 * @param workUnitType
	 *            comments
	 * @return workUnitName
	 */
	public static String getWorkUnitName(int workUnitType) {
		return workUnitNames.get(workUnitType);
	}

	/**
	 * 增加number个工作线程.
	 * 
	 * @param number
	 *            工作线程个数
	 */
	public void addWorkder(int number) {
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessor.addWorker(number);
		}
	}

	/**
	 * 杀死number个工作线程.
	 * 
	 * @param number
	 *            工作线程个数
	 * @return true, if successful
	 */
	public void killWorker(int number) {
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessor.killWorker(number);
		}
	}

	/**
	 * Sets taskLoaderFrequency.
	 * 
	 * @param interval
	 *            comments
	 */
	public void setTaskLoaderFrequency(long interval) {
		for (TaskLoader taskLoader : taskLoaders) {
			taskLoader.setInterval(interval);
		}
	}

	/**
	 * Gets taskProcessorRunningThreadSize.
	 * 
	 * @param i
	 *            comments
	 * @return taskProcessorRunningThreadSize
	 */
	public int getTaskProcessorRunningThreadSize(int i) {
		return taskProcessors.get(i).getRunningThreadSize();

	}

	/**
	 * Gets maxLoadTaskNumber.
	 * 
	 * @param i
	 *            comments
	 * @return maxLoadTaskNumber
	 */
	public int getMaxLoadTaskNumber(int i) {
		return taskLoaders.get(i).getMaxLoadTaskNumber();
	}

	/**
	 * Sets maxLoadTaskNumber.
	 * 
	 * @param maxLoadTaskNumber
	 *            comments
	 */
	public void setMaxLoadTaskNumber(int maxLoadTaskNumber) {
		for (TaskLoader taskLoader : taskLoaders) {
			taskLoader.setMaxLoadTaskNumber(maxLoadTaskNumber);
		}
	}
	
	public void setTaskProcessorWorkerSleepTime(long workerSleepTime){
		for (TaskProcessor taskProcessor : taskProcessors) {
			taskProcessor.setWorkerSleepTime(workerSleepTime);
		}
	}

	/**
	 * 创建任务加载器对象.
	 * 
	 * @param taskLoader
	 *            任务加载器
	 * @return TaskLoaderStatus
	 */
	private TaskLoaderStatus createTaskLoaderStatus(TaskLoader taskLoader) {
		TaskLoaderStatus tls = new TaskLoaderStatus();
		tls.setMaxLoadTaskNumber(taskLoader.getMaxLoadTaskNumber());
		tls.setRunStatus(taskLoader.getRunStatus() == 0 ? "运行中"
				: "停止");
		tls.setFrequency(taskLoader.getInterval());
		tls.setWorkUnitType(getWorkUnitType());
		tls.setTaskListCostTime(taskLoader.getTaskListCostTime());
		return tls;
	}

	/**
	 * 创建任务处理器状态对象.
	 * 
	 * @param taskProcessor
	 *            任务处理器
	 * @return TaskProcessorStatus
	 */
	private TaskProcessorStatus createTaskProcessorStatus(
			TaskProcessor taskProcessor) {
		TaskProcessorStatus tps = new TaskProcessorStatus();
		tps.setRunningThreadSize(taskProcessor
				.getRunningThreadSize() + "");
		tps.setMaxThread(taskProcessor.getMaxSize() + "");
		tps.setMinThread(taskProcessor.getMinSize() + "");
		tps.setRemainder(taskProcessor.getQueueSize() + "");
		tps.setRunStatus(taskProcessor.getRunStatus() == 0 ? "运行中"
				: "停止");
		tps.setIsWaitingNewTask(taskProcessor.isWaitingNewTask() ? "等待新任务"
				: "任务处理中");
		tps.setTaskListCostTime(taskProcessor.getTaskListCostTime() + "");
		tps.setTotalOfSuccessfulTaskNumber(taskProcessor.getTotalOfSuccessfulTaskNumber()+"");
		tps.setWorkerSleepTime(taskProcessor.getWorkerSleepTime()+"");
		return tps;
	}
	
	public String getShortTaskLoaderStatuses(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < taskLoaders.size(); i++) {
			TaskLoader taskLoader = taskLoaders.get(i);
			sb.append(i).append(":");
			sb.append(taskLoader.getRunStatus() == 0 ? "运行中"
					: "停止");
			sb.append(",");
		}
		String result = sb.toString();
		if (result.endsWith(",")) {
			result.substring(0, result.length()-1);
		}
		return result;
	}
	public String getShortTaskProcessorStatuses(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < taskProcessors.size(); i++) {
			TaskProcessor taskProcessor = taskProcessors.get(i);
			sb.append(i).append(":");
			sb.append(taskProcessor.getRunStatus() == 0 ? "运行中"
					: "停止");
			sb.append(",");
		}
		String result = sb.toString();
		if (result.endsWith(",")) {
			result.substring(0, result.length()-1);
		}
		return result;
	}
}
