package designpattern.action.observer;

/**
 * The Interface Watcher.
 * 观察者-抽象类
 *
 * @date 2014-5-12 0:08:51
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface Watcher {
	
	/**
	 * 当被观察者通知观察者，自己发生变化时，观察者需要做出反应，即更新操作.
	 */
	public void update();

}
