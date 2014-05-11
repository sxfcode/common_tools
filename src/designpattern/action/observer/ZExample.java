package designpattern.action.observer;

import designpattern.action.observer.impl.WatchedImpl;
import designpattern.action.observer.impl.WatcherImpl;

/**
 * The Class ZExample.
 * 观察者模式
 * 
 * 定义对象间的一种一对多的依赖关系,当一个对象的状态发生改变时, 所有依赖于它的对象都得到通知并被自动更新。
 * 
 * 当一个抽象模型有两个方面, 其中一个方面依赖于另一方面。将这二者封装在独立的对象中以使它们可以各自独立地改变和复用。
 * 
 * 当对一个对象的改变需要同时改变其它对象, 而不知道具体有多少对象有待改变。
 * 
 *  当一个对象必须通知其它对象，而它又不能假定其它对象是谁。换言之, 你不希望这些对象是紧密耦合的。
 * 
 * 
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
