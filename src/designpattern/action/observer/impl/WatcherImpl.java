package designpattern.action.observer.impl;

import designpattern.action.observer.Watcher;

/**
 * The Class WatcherImpl.
 * 观察者-实现类 观察者可以很多个不同类型或者同类型的实现类，
 * @date 2014-5-12 0:13:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class WatcherImpl implements Watcher {
	
	private String name;
	
	public WatcherImpl(String name){
		this.name =name;
	}

	@Override
	public void update() {
		System.out.println("观察者"+name+"收到通知");
	}
}
