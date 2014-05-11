package designpattern.action.observer.impl;

import java.util.ArrayList;
import java.util.List;

import designpattern.action.observer.Watched;
import designpattern.action.observer.Watcher;

/**
 * The Class WatchedImpl.
 *
 * @date 2014-5-12 0:16:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class WatchedImpl implements Watched {
	
	private List<Watcher> watcherList = new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher watcher) {
		watcherList.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		watcherList.remove(watcher);
	}

	@Override
	public void removeAllWatcher() {
		watcherList.clear();
	}
	@Override
	public void notifyAllWatcher() {
		for (Watcher watcher : watcherList) {
			watcher.update();
		}
	}

}
