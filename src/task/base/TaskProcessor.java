package task.base;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class TaskProcessor. 任务处理器，这是一个线程池。 完整的工作流程大体如下：
 * 任务加载器作为一个单独的线程，不停的加载任务到任务队列。 任务处理器作为一个线程池，其中的线程不停的从任务队列中取出任务进行处理。
 * 
 * @date 2013-3-6 15:05:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class TaskProcessor {

	/** 运行状态：运行中. */
	private static final int RUN_STATUS_RUNNING = 0;

	/** 运行状态：停止. */
	private static final int RUN_STATUS_STOP = 1;

	/** 默认的任务队列长度. */
	private static final long TASK_QUEUE_LENGTH_DEFAULT = 1000;

	/** 工作线程在从队列抓取任务为空时的默认睡眠时间，单位：毫秒. */
	private static final long WORKER_SLEEP_TIME_DEFAULT = 1000;

	/** 线程组计数器. */
	private static final AtomicInteger threadGroupNumber = new AtomicInteger(0);

	/** 当前运行状态. */
	private volatile int runStatus;

	/** 最小线程数. */
	private volatile int minSize = 5;

	/** 最大线程数. */
	private volatile int maxSize = 10;

	/** 运行中线程数目. */
	private volatile int runningThreadSize = 0;

	/** 工作线程在从队列抓取任务为空时的睡眠时间,单位:毫秒. */
	private volatile long workerSleepTime = WORKER_SLEEP_TIME_DEFAULT;

	/** 线程池可以处理的任务队列最大长度。当队列中的任务数量达到这个值时，线程池将停止向队列中添加任务,以保护队列不会过长。. */
	private volatile long taskQueueLengthMax = TASK_QUEUE_LENGTH_DEFAULT;

	/** 可重入互斥锁. */
	private final ReentrantLock mainLock = new ReentrantLock();

	/** 任务队列. */
	private Queue<Task> taskQueue = null;

	/** 工作线程组. */
	private final HashSet<Worker> workers = new HashSet<Worker>();

	/** 已完成任务总数-预留未启用. */
	private volatile long totalOfCompletedTaskNumber = 0;
	/** 成功完成任务的总数. */
	private volatile long totalOfSuccessfulTaskNumber = 0;

	/** 使用线程组，便于管理具有相同业务逻辑的线程. */
	private ThreadGroup threadGroup;

	/** 工作线程编号. */
	private AtomicInteger workerNumber = new AtomicInteger(0);

	/** 处理任务列表消耗的时间(包含了任务抓取的时间). */
	private volatile long taskListCostTime = 0l;

	/** 上次完成任务时的系统时间. */
	private volatile long lastCompletedTime = System.currentTimeMillis();

	/** 线程开始处理任务的时间. */
	private volatile long workersRunnintStartTime = 0l;

	/** 线程是否正在处理任务. */
	private volatile boolean workersIsRunning = false;

	/**
	 * Instantiates a new TaskProcessor.
	 * 
	 * @param minSize
	 *            线程池的最小线程数
	 * @param maxSize
	 *            线程池的最大线程数
	 * @param taskQueueLengthMax
	 *            线程池的任务队列的最大容量
	 * @param workerSleepTime
	 *            comments
	 * @param taskQueue
	 *            任务队列
	 */
	public TaskProcessor(int minSize, int maxSize,
			int taskQueueLengthMax, long workerSleepTime,
			Queue<Task> taskQueue) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.taskQueueLengthMax = taskQueueLengthMax;
		this.workerSleepTime = workerSleepTime;
		this.taskQueue = taskQueue;
		this.runStatus = RUN_STATUS_STOP;
		this.threadGroup = new ThreadGroup(createThreadGroupName());
	}

	/**
	 * Instantiates a new TaskProcessor.
	 * 
	 * @param taskQueue
	 *            comments
	 */
	public TaskProcessor(Queue<Task> taskQueue) {
		this.taskQueue = taskQueue;
		this.runStatus = RUN_STATUS_STOP;
		this.threadGroup = new ThreadGroup(createThreadGroupName());
	}

	/**
	 * 添加任务. 添加任务时,会做如下检查 1.检查线程池是否已经停止工作，若停止则不再添加任务。 2.检查队列是否已满 3.检查是否有重复任务
	 * 
	 * @param task
	 *            comments
	 * @return true, if successful
	 */
	public boolean addTask(Task task) {
		boolean result = false;
		// 1.检查线程池是否已经停止工作，若停止则不再添加任务。
		// 2.检查队列是否已满
		// 3.检查是否有重复任务
		if (runStatus != RUN_STATUS_STOP
				&& (taskQueue.size() < taskQueueLengthMax)
				&& (!taskQueue.contains(task))) {
			taskQueue.add(task);
			result = true;
		}
		// 维持最小线程数目
		if (workers.size()<minSize) {
			this.addWorker(minSize-workers.size());
		}
		
		return result;
	}

	/**
	 * 启动线程池.
	 */
	public void startProcess() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			// 只有停止状态才会启动
			if (runStatus == RUN_STATUS_STOP) {
				runStatus = RUN_STATUS_RUNNING;
				// 若所有线程都消亡，则创建新线程。
				int workersSize =workers.size();
				if (workersSize<minSize) {
					for (int i = 0; i < (minSize-workersSize); i++) {
						Worker worker = new Worker(this.threadGroup,
								createWorkerName());
						worker.start();
						workers.add(worker);
					}
				}else {
					// 检查是否有尚未启动的线程，因为之前剩余的线程可能尚未启动。
					for (Worker worker : workers) {
						if (!worker.isStarted()) {
							worker.start();
						}
					}
				}
				
			}
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * 关闭线程池，效果不会马上生效。 池中的线程在执行完毕后会自动关闭.
	 */
	public void stopProcess() {
		setRunStatus(RUN_STATUS_STOP);

	}

	/**
	 * 工作线程.
	 * 
	 * @date 2013-3-7 16:50:16
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	private final class Worker extends Thread {

		/** runLock. */
		private final ReentrantLock runLock = new ReentrantLock();

		/** 线程是否已启动. */
		private boolean isStarted = false;

		/** 是否需要退出. */
		private boolean needExit = false;

		/** 是否等待新任务. */
		private boolean isWaitingNewTask = true;

		/**
		 * 构造方法指定线程组和线程名称.
		 * 
		 * @param group
		 *            comments
		 * @param threadName
		 *            comments
		 */
		public Worker(ThreadGroup group, String threadName) {
			super(group, threadName);
		}

		/**
		 * 线程退出条件,比如说多长时间内没有任务需要处理，则推出线程.
		 * 
		 * @return true, if successful
		 */
		private boolean checkExit() {
			return needExit;
		}

		/**
		 * Sets exit.
		 * 
		 * @param needExit
		 *            comments
		 */
		public void setExit(boolean needExit) {
			this.needExit = needExit;
		}

		/**
		 * Checks if is started.
		 * 
		 * @return true, if is started
		 */
		public boolean isStarted() {
			return isStarted;
		}

		/**
		 * Checks if is active.
		 * 
		 * @return true, if is active
		 */
		@SuppressWarnings("unused")
		boolean isActive() {
			return runLock.isLocked();
		}

		/**
		 * Checks if is waiting new task.
		 * 
		 * @return true, if is waiting new task
		 */
		public boolean isWaitingNewTask() {
			return isWaitingNewTask;
		}

		/**
		 * Sets waitingNewTask.
		 * 
		 * @param isWaitingNewTask
		 *            comments
		 */
		public void setWaitingNewTask(boolean isWaitingNewTask) {
			// 只有当状态值发生变化时才修改,未发生变化则返回。
			if (this.isWaitingNewTask && isWaitingNewTask) {
				return;
			}
			this.isWaitingNewTask = isWaitingNewTask;
			// 任务队列为空时
			if (isWaitingNewTask) {
				changeTaskListCostTime();
			}
		}

		/**
		 * runTask.
		 * 
		 * @param task
		 *            comments
		 */
		private void runTask(Task task) {
			boolean isSuccessful = task.execute();
			// 若处理成功则成功处理任务数+1
			if (isSuccessful) {
				increaseTotalOfSuccessfulTaskNumber();
			}
		}

		/**
		 * run.
		 */
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			isStarted = true;
			increaseRunningThreadSize();
			try {
				// 控制线程是否工作,该控制归线程池管理
				while (runStatus != RUN_STATUS_STOP) {
					// 检查线程是否满足退出条件,线程上的管理
					if (checkExit()) {
						break;
					}
					Task task = taskQueue.poll();
					if (task != null) {
						// 检查线程池中的线程是否已经开始处理线程
						if (!workersIsRunning) {
							doWorkersToRunning();
						}
						setWaitingNewTask(false);
						runTask(task);
					} else {
						setWaitingNewTask(true);
						try {
							Thread.currentThread().sleep(workerSleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				endWork(this);
			}
		}
	}

	/**
	 * workerCanExit.
	 * 
	 * @return true, if successful
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private boolean workerCanExit() {
		boolean canExit = false;
		// 线程退出策略，暂时先使用睡眠
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return canExit;
	}

	/**
	 * endWork.
	 * 
	 * @param w
	 *            comments
	 */
	void endWork(Worker w) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			workers.remove(w);
			decreaseRunningThreadSize();
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * Gets task.
	 * 
	 * @return task
	 */
	public Task getTask() {
		return taskQueue.poll();
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
	 * Gets minSize.
	 * 
	 * @return minSize
	 */
	public int getMinSize() {
		return minSize;
	}

	/**
	 * Sets minSize.
	 * 
	 * @param minSize
	 *            comments
	 */
	public void setMinSize(int minSize) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.minSize = minSize;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets maxSize.
	 * 
	 * @return maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * Sets maxSize.
	 * 
	 * @param maxSize
	 *            comments
	 */
	public void setMaxSize(int maxSize) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.maxSize = maxSize;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets runningThreadSize.
	 * 
	 * @return runningThreadSize
	 */
	public int getRunningThreadSize() {
		return runningThreadSize;
	}

	/**
	 * Sets runningThreadSize.
	 * 
	 * @param runningThreadSize
	 *            comments
	 */
	public void setRunningThreadSize(int runningThreadSize) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.runningThreadSize = runningThreadSize;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Sets runningThreadSize.
	 * 
	 */
	public void increaseRunningThreadSize() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.runningThreadSize = this.runningThreadSize + 1;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Sets runningThreadSize.
	 * 
	 */
	public void decreaseRunningThreadSize() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.runningThreadSize = this.runningThreadSize - 1;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets queueSize.
	 * 
	 * @return queueSize
	 */
	public int getQueueSize() {
		return this.taskQueue.size();
	}

	/**
	 * Gets taskQueueLengthMax.
	 * 
	 * @return taskQueueLengthMax
	 */
	public long getTaskQueueLengthMax() {
		return taskQueueLengthMax;
	}

	/**
	 * Sets taskQueueLengthMax.
	 * 
	 * @param taskQueueLengthMax
	 *            comments
	 */
	public void setTaskQueueLengthMax(long taskQueueLengthMax) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.taskQueueLengthMax = taskQueueLengthMax;
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * Gets taskQueue.
	 * 
	 * @return taskQueue
	 */
	public Queue<Task> getTaskQueue() {
		return taskQueue;
	}

	/**
	 * Gets workerSleepTime.
	 *
	 * @return workerSleepTime
	 */
	public long getWorkerSleepTime() {
		return workerSleepTime;
	}

	/**
	 * Sets workerSleepTime.
	 *
	 * @param workerSleepTime comments
	 */
	public void setWorkerSleepTime(long workerSleepTime) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.workerSleepTime = workerSleepTime;
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * 增加工作线程.
	 * 
	 * @param number
	 *            comments
	 */
	public void addWorker(int number) {
		for (int i = 0; i < number; i++) {
			Worker worker = new Worker(this.threadGroup, createWorkerName());
			worker.start();
			workers.add(worker);
		}
	}

	/**
	 * 杀死工作线程.
	 * 
	 * @param number
	 *            comments
	 * @return true, if successful
	 */
	public boolean killWorker(int number) {
		boolean isSuccessFul = false;
		if (number <= 0) {
			isSuccessFul = false;
			return isSuccessFul;
		}
		Iterator<Worker> iterator = workers.iterator();
		while (iterator.hasNext()) {
			Worker worker = iterator.next();
			if (worker.isStarted()) {
				worker.setExit(true);
				iterator.remove();
				number = number - 1;
				if (number <= 0) {
					isSuccessFul = true;
					break;
				}
			}
		}
		return isSuccessFul;
	}


	/**
	 * createThreadGroupName.
	 * 
	 * @return String
	 */
	private String createThreadGroupName() {
		return TaskProcessor.class.getSimpleName() + "-"
				+ TaskProcessor.threadGroupNumber.getAndIncrement();
	}

	/**
	 * createWorkerName.
	 * 
	 * @return String
	 */
	private String createWorkerName() {
		return threadGroup.getName() + "-" + Worker.class.getSimpleName() + "-"
				+ workerNumber.getAndIncrement();
	}

	/**
	 * 当前线程池否正在等待任务.
	 * 
	 * @return true, if is waiting task
	 */
	public boolean isWaitingNewTask() {
		boolean isWaiting = true;
		for (Worker worker : workers) {
			if (!worker.isWaitingNewTask()) {
				isWaiting = false;
				break;
			}
		}
		return isWaiting;
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
	 * @param taskListCostTime
	 *            comments
	 */
	public void setTaskListCostTime(long taskListCostTime) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.taskListCostTime = taskListCostTime;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets lastCompletedTime.
	 * 
	 * @return lastCompletedTime
	 */
	public long getLastCompletedTime() {
		return lastCompletedTime;
	}

	/**
	 * Sets lastCompletedTime.
	 * 
	 * @param lastCompletedTime
	 *            comments
	 */
	public void setLastCompletedTime(long lastCompletedTime) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.lastCompletedTime = lastCompletedTime;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * changeTaskListCostTime.
	 */
	public void changeTaskListCostTime() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			if (isWaitingNewTask()) {
				long current = System.currentTimeMillis();
				this.taskListCostTime = current - workersRunnintStartTime;
				this.workersIsRunning = false;
			}
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets workersRunnintStartTime.
	 *
	 * @return workersRunnintStartTime
	 */
	public long getWorkersRunnintStartTime() {
		return workersRunnintStartTime;
	}

	/**
	 * Sets workersRunnintStartTime.
	 *
	 * @param workersRunnintStartTime comments
	 */
	public void setWorkersRunnintStartTime(long workersRunnintStartTime) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.workersRunnintStartTime = workersRunnintStartTime;
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * Checks if is workers is running.
	 *
	 * @return true, if is workers is running
	 */
	public boolean isWorkersIsRunning() {
		return workersIsRunning;
	}

	/**
	 * Sets workersIsRunning.
	 *
	 * @param workersIsRunning comments
	 */
	public void setWorkersIsRunning(boolean workersIsRunning) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.workersIsRunning = workersIsRunning;
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * 线程开始处理任务时执行的方法.
	 */
	public void doWorkersToRunning() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			// 拿到锁有再次检查workersIsRunning变量
			if (!this.workersIsRunning) {
				this.workersIsRunning = true;
				this.workersRunnintStartTime = System.currentTimeMillis();
			}
		} finally {
			mainLock.unlock();
		}
	}

	/**
	 * Gets totalOfSuccessfulTaskNumber.
	 *
	 * @return totalOfSuccessfulTaskNumber
	 */
	public long getTotalOfSuccessfulTaskNumber() {
		return totalOfSuccessfulTaskNumber;
	}

	/**
	 * Sets totalOfSuccessfulTaskNumber.
	 *
	 * @param totalOfSuccessfulTaskNumber comments
	 */
	public void setTotalOfSuccessfulTaskNumber(long totalOfSuccessfulTaskNumber) {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			this.totalOfSuccessfulTaskNumber = totalOfSuccessfulTaskNumber;
		} finally {
			mainLock.unlock();
		}
	}
	
	/**
	 * increaseTotalOfSuccessfulTaskNumber.
	 */
	public void increaseTotalOfSuccessfulTaskNumber() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			// 当completedTaskNumber的值接近Long的最大表示范围时，将其重置为0，-10000是为了提供一个缓冲范围。
			if (totalOfSuccessfulTaskNumber >= Long.MAX_VALUE - 10000) {
				totalOfSuccessfulTaskNumber = 0;
			}
			this.totalOfSuccessfulTaskNumber = this.totalOfSuccessfulTaskNumber + 1;
		} finally {
			mainLock.unlock();
		}

	}

	/**
	 * Gets totalOfCompletedTaskNumber.
	 *
	 * @return totalOfCompletedTaskNumber
	 */
	public long getTotalOfCompletedTaskNumber() {
		return totalOfCompletedTaskNumber;
	}

	/**
	 * Sets totalOfCompletedTaskNumber.
	 *
	 * @param totalOfCompletedTaskNumber comments
	 */
	public void setTotalOfCompletedTaskNumber(long totalOfCompletedTaskNumber) {
		this.totalOfCompletedTaskNumber = totalOfCompletedTaskNumber;
	}
	
	/**
	 * increaseTotalOfCompletedTaskNumber.
	 */
	public void increaseTotalOfCompletedTaskNumber() {
		final ReentrantLock mainLock = this.mainLock;
		mainLock.lock();
		try {
			// 当completedTaskNumber的值接近Long的最大表示范围时，将其重置为0，-10000是为了提供一个缓冲范围。
			if (totalOfCompletedTaskNumber >= Long.MAX_VALUE - 10000) {
				totalOfCompletedTaskNumber = 0;
			}
			this.totalOfCompletedTaskNumber = this.totalOfCompletedTaskNumber + 1;
		} finally {
			mainLock.unlock();
		}

	}
	
}
