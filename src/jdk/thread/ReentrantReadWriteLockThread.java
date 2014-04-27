package jdk.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The Class ReentrantReadWriteLockThread.
 * 可重入读写锁.
 * 读写锁即可以放在线程对象中，也可以放在被线程使用的对象中。
 * 
 * ReadWriteLock内置两个Lock，一个是读的Lock，一个是写的Lock。
 * 多个线程可同时得到读的Lock，但只有一个线程能得到写的Lock，
 * 而且写的Lock被锁定后，任何线程都不能得到Lock。ReadWriteLock提供的方法有：
 * readLock(): 返回一个读的lock 
 * writeLock(): 返回一个写的lock, 此lock是排他的。
 * ReadWriteLockTest很适合处理类似文件的读写操作。
 * 读的时候可以同时读，但不能写；写的时候既不能同时写也不能读。
 * 
 * 	
 *
 * @date 2013-11-25 19:02:00
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ReentrantReadWriteLockThread {
	
	/**
	 * 持有读写锁的对象.
	 *
	 * @date 2013-11-25 19:13:50
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserData{
		
		/** rwLock. */
		private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		
		/** read. */
		private final Lock read = rwLock.readLock();
		
		/** write. */
		private final Lock write = rwLock.writeLock();
		
		/** data. */
		private Map<Integer,Integer> data = new HashMap<Integer,Integer>();
		// 已处理数据数,数据指针
		/** haveProcessedCount. */
		private int  haveProcessedCount = 0;
		
		/**
		 * 读取数据.
		 *
		 * @param i comments
		 * @return int
		 */
		public int get(int i){
			read.lock();
			System.out.println(Thread.currentThread().getId()+":获取读锁");
			try {
				int temp = data.get(i);
				// 模拟网络延时，时间稍长可以发现读锁可以同时被多个线程获取
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getId()+":准备返回");
				return temp;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return -1;
			}finally{
				System.out.println(Thread.currentThread().getId()+":释放读锁");
				read.unlock();
			}
		}
		
		/**
		 * 写入数据.
		 *
		 * @param i comments
		 * @param d comments
		 * @return int
		 */
		public int put(int i,int d){
			write.lock();
			System.out.println(Thread.currentThread().getId()+":获取写锁===1");
			try {
				data.put(i,d);
				// 无论如何延时，只有在一个写锁被释放后，才能获得新的写锁。
				Thread.sleep(1000);
				System.out.println(+Thread.currentThread().getId()+":准备返回");
				return d;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return -1;
			}finally{
				System.out.println(+Thread.currentThread().getId()+":释放写锁====1");
				write.unlock();
			}
		}
		
		/**
		 * Gets haveProcessed.
		 *
		 * @return haveProcessed
		 */
		public int getHaveProcessed(){
			read.lock();
			System.out.println("获取读锁");
			try {
				System.out.println("准备返回");
				return haveProcessedCount;
			}finally{
				System.out.println("释放读锁");
				read.unlock();
			}
		}
		
		/**
		 * 处理数据.
		 * 使用了锁的可重入性。
		 *
		 * @param index comments
		 * @return int
		 */
		public int processData(int index){
			write.lock();
			System.out.println(Thread.currentThread().getId()+":获取写锁====2");
			try {
				int temp = get(index);
				haveProcessedCount = haveProcessedCount+1;
				int result = put(index, 1+temp);
				System.out.println(+Thread.currentThread().getId()+":准备返回");
				return result;
			} finally{
				System.out.println(+Thread.currentThread().getId()+":释放写锁====2");
				write.unlock();
			}
		}
		
		
		/**
		 * init.
		 */
		public void init(){
			for (int i = 0; i < 100; i++) {
				data.put(i, 0);
			}
		}
		
	}

	/**
	 * 处理数据的线程.
	 *
	 * @date 2013-11-25 19:13:50
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class UserThread extends Thread{
		
		/** userData. */
		private UserData userData = null;
		
		/** taskId. */
		private int taskId;
		
		/** type. */
		private int type;
		
		/**
		 * Instantiates a new UserThread.
		 *
		 * @param userData comments
		 * @param taskId comments
		 * @param type comments
		 */
		public UserThread(UserData userData,int taskId,int type){
			this.userData = userData; 
			this.taskId = taskId;
			this.type = type;
		}
		
		@SuppressWarnings("static-access")
		public void run(){
			if (type==0) {
				System.out.println("读线程:"+Thread.currentThread().getId()+",taskId:"+taskId+",start");
			}else{
				System.out.println("处理线程"+Thread.currentThread().getId()+",taskId:"+taskId+",start");
			}
			Random r = new Random();
			try {
				Thread.currentThread().sleep(r.nextInt(5)*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (type==0) {
				System.out.println("读线程:"+Thread.currentThread().getId()+",taskId:"+taskId+",data:"+userData.get(taskId));
			}else{
				System.out.println("处理线程"+Thread.currentThread().getId()+",taskId:"+taskId+",data:"+userData.processData(taskId));
			}
			if (type==0) {
				System.out.println("读线程:"+Thread.currentThread().getId()+",taskId:"+taskId+",end");
			}else{
				System.out.println("处理线程"+Thread.currentThread().getId()+",taskId:"+taskId+",end");
			}
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UserData ud = new UserData();
		ud.init();
		// 演示读锁可以同时被多个线程持有
		for (int i = 0; i < 10; i++) {
			UserThread ut = new UserThread(ud,i,0);
			ut.start();
		}
		// 演示写锁只有在被释放后，才能被一个线程获取。
		for (int i = 0; i < 10; i++) {
			UserThread ut = new UserThread(ud,i,1);
			ut.start();
		}
	}

}
