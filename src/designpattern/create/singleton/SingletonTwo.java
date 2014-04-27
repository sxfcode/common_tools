package designpattern.create.singleton;

/**
 * The Class SingletonTwo.
 * 单例模式-懒汉方式
 *
 * @date 2014-4-22 11:02:17
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SingletonTwo {

	/**
	 * Instantiates a new SingletonTwo.
	 */
	private SingletonTwo() {
	}
	
	/** instance. */
	private static SingletonTwo instance = null;
	
	/**
	 * Gets the single instance of SingletonTwo.
	 *
	 * @return single instance of SingletonTwo
	 */
	public static synchronized SingletonTwo getInstance(){
		if(instance==null){
			instance = new SingletonTwo();
		}
		return instance;
	}

}
