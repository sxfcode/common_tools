package jdk.detail.j2se5;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Map.Entry;

/**
 * The Class MBean.
 * 虚拟机管理bean
 *
 * @date 2013-7-10 10:04:20
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class MBean {
	
	/**
	 * 内存信息.
	 */
	public static void printMemoryMXBean(){
		// 单例
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
		bean.setVerbose(true);
		
		System.out.printf("%n--%s--%n",bean.getClass().getName());
		
		// 用于对象分配的堆的当前内存使用量。
		System.out.println(bean.getHeapMemoryUsage());
		System.out.printf("%s: %s%n","NonHeap",bean.getNonHeapMemoryUsage());
		
		// 垃圾回收。
		bean.gc();
	}
	
	/**
	 * 类加载信息.
	 */
	public static void printClassloaderMXBean(){
		ClassLoadingMXBean bean = ManagementFactory.getClassLoadingMXBean();
		bean.setVerbose(true);
		System.out.printf("%s:%s%n","loadedClassCount",bean.getLoadedClassCount());
		System.out.printf("%s:%s%n","unloadedClassCount",bean.getUnloadedClassCount());
		System.out.printf("%s:%s%n","totalloadedClassCount",bean.getTotalLoadedClassCount());
		
	}
	
	/**
	 * 线程信息.
	 */
	public static void printThreadMXBean(){
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
	    long[] threadIds = bean.getAllThreadIds();
	    for (long threadId : threadIds) {
			ThreadInfo info = bean.getThreadInfo(threadId);
			System.out.println("线程名称:"+info.getThreadName());
		}
	}
	
	/**
	 * 运行时信息.
	 */
	public static void printRuntimeMXBean(){
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		//System.out.printf("%s:%s%n","",);
		// 用于搜索类文件的引导类路径
		System.out.printf("%s:%s%n","bootClassPath",bean.getBootClassPath());
		// classpath
		System.out.printf("%s:%s%n","classPath",bean.getClassPath());
		
		// 虚拟机参数
		System.out.printf("%s%n","InputArgs");
		List<String> args = bean.getInputArguments();
		for (String arg : args) {
		 System.out.println(arg);	
		}
		
		System.out.printf("%s:%s%n","LibrarayPath",bean.getLibraryPath());
		System.out.printf("%s:%s%n","ManagementSpecVersion",bean.getManagementSpecVersion());
		System.out.printf("%s:%s%n","Name",bean.getName());
		System.out.printf("%s:%s%n","specName",bean.getSpecName());
		System.out.printf("%s:%s%n","specvendor",bean.getSpecVendor());
		System.out.printf("%s:%s%n","specVersion",bean.getSpecVersion());
		System.out.printf("%s:%s%n","startTime",bean.getStartTime());
		System.out.printf("%s:%s%n","",bean.getVmName());
		System.out.printf("%s:%s%n","",bean.getVmVendor());
		System.out.printf("%s:%s%n","",bean.getVmVersion());
		System.out.printf("%s:%s%n","",bean.getSystemProperties());
		for (Entry<String, String> entry : bean.getSystemProperties().entrySet()) {
			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
			
		}
	}
	
	/**
	 * printOSMXBean.
	 */
	public static void printOSMXBean(){
		OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		System.out.println(bean.getArch());
		System.out.println(bean.getAvailableProcessors());
		System.out.println(bean.getName());
		System.out.println(bean.getSystemLoadAverage());
		System.out.println(bean.getVersion());
	}
	
	
	/**
	 * printCompilationMXBean.
	 */
	public static void printCompilationMXBean(){
		CompilationMXBean bean = ManagementFactory.getCompilationMXBean();
		System.out.println("Compilation："+bean.getName());
		System.out.println("Compilation TotalCompilationTime："+bean.getTotalCompilationTime());
		
	}
	
	public static void printGC(){
		// 获得所有实例
		List<GarbageCollectorMXBean> instances = ManagementFactory
				.getGarbageCollectorMXBeans();
		System.out.printf("%n---%s---%n", GarbageCollectorMXBean.class
				.getName());
		// 遍历每个实例
		for (GarbageCollectorMXBean instance : instances) {
			// 返回垃圾收集器的名字
			System.out.printf("***%s: %s***%n", "Name", instance.getName());
			// 返回已发生的回收的总次数
			System.out.printf("%s: %s%n", "CollectionCount", instance
					.getCollectionCount());
			// 返回近似的累积回收时间
			System.out.printf("%s: %s%n", "CollectionTime", instance
					.getCollectionTime());
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// MBean.printMemoryMXBean();
		MBean.printClassloaderMXBean();
		// MBean.printThreadMXBean();
		// MBean.printRuntimeMXBean();
		// MBean.printOSMXBean();
		// MBean.printCompilationMXBean();
		//MBean.printGC();
	}
}

