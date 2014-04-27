package task.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import task.base.Task;
import task.base.TaskLoader;
import task.base.TaskProcessor;
import task.example.HelloTaskLoader;

/**
 * The Class ConsoleService. 
 * 控制台服务器类，用来启动和关闭业务功能。
 * 
 * @date 2013-3-21 18:08:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,order_sync_v1 1.0
 */
public class WorkUnitCore {

	/** instance. */
	private volatile static WorkUnitCore instance = null;

	/** 工作单元. */
	private Map<Integer, WorkUnit> workUnits = new HashMap<Integer, WorkUnit>();
	
	private WorkUnitCore() {
	}

	/**
	 * Gets the single instance of ConsoleUtils.
	 * 
	 * @return single instance of ConsoleUtils
	 */
	public static WorkUnitCore getInstance() {
		if (instance == null) {
			instance = new WorkUnitCore();
		}
		return instance;

	}

	/**
	 * init.
	 */
	public void init() {
		workUnits.put(1, createWorkUnit(1));
	}

	/**
	 *
	 * @param workUnitType comments
	 * @return WorkUnit
	 */
	private WorkUnit createWorkUnit(int workUnitType) {
		Queue<Task> taskQueue = new LinkedBlockingQueue<Task>();
		// 启动任务处理器
		TaskProcessor taskProcessor = new TaskProcessor(taskQueue);
		// 启动任务加载器
		TaskLoader taskLoader=null;
		switch (workUnitType) {
		case 1:
			taskLoader = new HelloTaskLoader(taskProcessor);
			break;
		
		default:
			break;
		}
		WorkUnit wu = new WorkUnit(workUnitType,taskLoader, taskProcessor);
		wu.startAllWork();
		return wu;
	}

	public String getAllWorkUnitShorInfo(){
		String info = "";
		for (WorkUnit workUnit : workUnits.values()) {
			WorkUnitStatus wus =workUnit.getWorkUnitStatus() ;
			info = info+wus.getWorkUnitTypeName()+":taskLoader-"+wus.getTaskLoaderStatus().get(0).getRunStatus()+",taskProcessor-"+wus.getTaskProcessorStatus().get(0).getRunStatus()+";";
		}
		return info;
	}
	
	/**
	 * Gets workUnit.
	 *
	 * @param workUnitType comments
	 * @return workUnit
	 */
	public WorkUnit getWorkUnit(int workUnitType) {
		return workUnits.get(workUnitType);
	}
	
	/**
	 * Gets allWorkUnit.
	 *
	 * @return allWorkUnit
	 */
	public Map<Integer, WorkUnit> getAllWorkUnit(){
		return workUnits;
	}
	
	
}