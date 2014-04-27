package task.base;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class TaskLoader. 
 * 任务加载器，其主要好处作为一个单独线程启动,可以每隔一定时间自动抓取任务。支持多线程。 
 * 另外用户也可以不实现自己的任务加载器，直接通过任务处理器添加任务。
 * ---继承本类需要实现getTaskList()方法,任务加载器通过该方法抓取需要处理的任务， 然后到任务队列中，交给线程池处理。
 * 
 * @date 2013-3-6 15:04:58
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class TaskLoader extends Thread {

	/** 运行状态:运行中. */
	protected static final int RUN_STATUS_RUNNING = 0;

	/** 运行状态:停止. */
	protected static final int RUN_STATUS_STOP = 1;

	/** 默认添加任务的时间间隔(单位:毫秒). */
	protected static final long INTERVAL_DEFAULT = 10000;
	
	/** 默认每次加载任务的最大数量. */
	protected static final int MAX_LOAD_TASK_NUMBER = 1000;

	/** 可重入互斥锁. */
	private final ReentrantLock mainLock = new ReentrantLock();

	/** 运行状态. */
	private volatile int runStatus = RUN_STATUS_STOP;
	
	/** 添加任务的时间间隔(单位:毫秒). */
	private volatile long interval = INTERVAL_DEFAULT;
	
	/** 每次加载任务的最大数量(超过该值时,系统会自动截取). */
	private volatile int maxLoadTaskNumber = MAX_LOAD_TASK_NUMBER;
	
	/** 使用线程组，便于管理具有相同业务逻辑的线程. */
	protected static ThreadGroup threadGroup = new ThreadGroup(createThreadGroupName());
	
	/** 线程组计数器. */
	private static final AtomicInteger threadGroupNumber = new AtomicInteger(0);
	
	/** 判断线程是否已经启动. */
	private boolean isStarted = false;
	
	/** 获取任务列表消耗的时间. */
	private long  taskListCostTime = 0l;

	/** 处理任务的线程池. */
	private TaskProcessor taskProcessor;

	/**
	 * Instantiates a new TaskLoader.
	 *
	 * @param taskProcessor comments
	 */
	public TaskLoader(TaskProcessor taskProcessor) {
		super(threadGroup,createThreadName());
		this.taskProcessor = taskProcessor;
		
		
	}

	/**
	 * 获取待处理任务列表,该方法需要用户实现。.
	 *
	 * @return taskList
	 */
	public abstract List<Task> getTaskList();

	/**
	 * run.
	 */
	public void run() {
		isStarted = true;
		long beforeGetTaskList = 0l;
		long afterGetTaskList = 0l;
		// 任务加载器和任务处理器的工作状态
		while (true) {
			// 若当前状态不为运行状态则休眠interval秒,或者队列中还有任务未处理时
			if (getRunStatus() != RUN_STATUS_RUNNING||!taskProcessor.isWaitingNewTask()) {
				localSleep(interval);
				continue;
			}
			beforeGetTaskList = System.currentTimeMillis();
			// 若获取的任务列表超长，则截取前maxLoadTaskNumber个任务。
			try {
				// 使用try-catch保证当前线程不会被异常终止.
				List<Task> taskList = getTaskList();
				if (taskList!=null&&taskList.size()>0) {
					afterGetTaskList = System.currentTimeMillis();
					setTaskListCostTime(afterGetTaskList-beforeGetTaskList);
					if (taskList.size()>maxLoadTaskNumber) {
						taskList = taskList.subList(0, maxLoadTaskNumber);
					}
					for (Task task : taskList) {
						taskProcessor.addTask(task);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			localSleep(interval);
		}
	}

	/**
	 * Gets interval.
	 * 
	 * @return interval
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * Sets interval.
	 * 
	 * @param interval
	 *            comments
	 */
	public void setInterval(long interval) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.interval = interval;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * 启动任务加载器.
	 */
	public void startLoad() {
		setRunStatus(RUN_STATUS_RUNNING);
		if (!isStarted) {
			super.start();
		}
	}

	/**
	 * 关闭任务加载器，.
	 */
	public void stopLoad() {
		setRunStatus(RUN_STATUS_STOP);
	}

	/**
	 * Gets runStatus.
	 * 
	 * @return runStatus
	 */
	public int getRunStatus() {
		return runStatus;
	}

	/**
	 * Sets runStatus.
	 * 
	 * @param runStatus
	 *            comments
	 */
	public void setRunStatus(int runStatus) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.runStatus = runStatus;
		} finally {
			mainLock.unlock();
		}
	}

	
	
	/**
	 * Checks if is started.
	 *
	 * @return true, if is started
	 */
	public boolean isStarted(){
		return isStarted;
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
	 * @param maxLoadTaskNumber comments
	 */
	public void setMaxLoadTaskNumber(int maxLoadTaskNumber) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.maxLoadTaskNumber = maxLoadTaskNumber;
		} finally {
			mainLock.unlock();
		}
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
	
	/**
	 * localSleep.
	 *
	 * @param time comments
	 */
	@SuppressWarnings("static-access")
	private void localSleep(long time){
		try {
			Thread.currentThread().sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * createThreadGroupName.
	 * 
	 * @return String
	 */
	protected static String createThreadGroupName() {
		return TaskLoader.class.getSimpleName();
	}
	/**
	 * createThreadGroupName.
	 * 
	 * @return String
	 */
	protected static String createThreadName() {
		return createThreadGroupName() + "-"
				+ TaskLoader.threadGroupNumber.getAndIncrement();
	}

	/**
	 * Gets taskProcessor.
	 *
	 * @return taskProcessor
	 */
	public TaskProcessor getTaskProcessor() {
		return taskProcessor;
	}

	/**
	 * Sets taskProcessor.
	 *
	 * @param taskProcessor comments
	 */
	public void setTaskProcessor(TaskProcessor taskProcessor) {
		this.taskProcessor = taskProcessor;
	}
	
}
