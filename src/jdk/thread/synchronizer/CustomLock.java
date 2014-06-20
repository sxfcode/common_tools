package jdk.thread.synchronizer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CustomLock implements Lock, Serializable {
	
	private static final long serialVersionUID = -8830155811186564689L;
	
	private static class Sync extends AbstractQueuedSynchronizer{
		// Report whether in locked state
	     protected boolean isHeldExclusively() {
	       return getState() == 1;
	     }
	 
	     // Acquire the lock if state is zero
	     public boolean tryAcquire(int acquires) {
	       assert acquires == 1; // Otherwise unused
	       if (compareAndSetState(0, 1)) {
	         setExclusiveOwnerThread(Thread.currentThread());
	         return true;
	       }
	       return false;
	     }
	 
	     // Release the lock by setting state to zero
	     protected boolean tryRelease(int releases) {
	       assert releases == 1; // Otherwise unused
	       if (getState() == 0) throw new IllegalMonitorStateException();
	       setExclusiveOwnerThread(null);
	       setState(0);
	       return true;
	     }
	 
	     // Provide a Condition
	     Condition newCondition() { return new ConditionObject(); }
	 
	     // Deserialize properly
	     private void readObject(ObjectInputStream s)
	         throws IOException, ClassNotFoundException {
	       s.defaultReadObject();
	       setState(0); // reset to unlocked state
	     }
										 
		
	}

	public CustomLock() {
	}

	private Sync sync = new Sync();
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
	}

	@Override
	public Condition newCondition() {
		return null;
	}

}
