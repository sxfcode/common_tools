package designpattern.action.observer;

/**
 * The Interface Watched.
 * 被观察者抽象类
 *
 * @date 2014-5-12 0:09:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface Watched {
	
	/**
	 * 添加一个观察者.
	 *
	 * @param watcher comments
	 */
	public void addWatcher(Watcher watcher);
	
	/**
	 * 删除一个观察者.
	 *
	 * @param watcher comments
	 */
	public void removeWatcher(Watcher watcher);
	
	/**
	 * 移除所有观察者.
	 */
	public void removeAllWatcher();
	
	/**
	 * 通知所有观察者.
	 */
	public void notifyAllWatcher();
}
