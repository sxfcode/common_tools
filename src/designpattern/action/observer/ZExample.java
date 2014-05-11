package designpattern.action.observer;

import designpattern.action.observer.impl.WatchedImpl;
import designpattern.action.observer.impl.WatcherImpl;

/**
 * The Class ZExample.
 * 
 * @date 2014-5-12 0:18:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		// 创建被观察者
		WatchedImpl wi = new WatchedImpl();
		
		// 创建观察者
		WatcherImpl w1 = new WatcherImpl("01");
		WatcherImpl w2 = new WatcherImpl("02");
		WatcherImpl w3 = new WatcherImpl("03");
		WatcherImpl w4 = new WatcherImpl("04");
		
		// 注册观察者
		wi.addWatcher(w1);
		wi.addWatcher(w2);
		wi.addWatcher(w3);
		wi.addWatcher(w4);
		// 通知所有观察者
		wi.notifyAllWatcher();

	}
}
