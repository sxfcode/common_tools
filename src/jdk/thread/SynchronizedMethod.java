package jdk.thread;

/**
 * The Class SynchronizedMethod.
 * 同步方法和非同步方法.
 *
 * @date 2013-11-18 17:46:28
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SynchronizedMethod {
	
	/**
	 * The Class Account.
	 *
	 * @date 2013-11-18 17:50:37
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class Account{
		
		/** money. */
		private int money = 0;
		
		/**
		 * putMoneyNonSync.
		 *
		 * @param count comments
		 */
		@SuppressWarnings("static-access")
		public void putMoneyNoSync(int count){
			// 模拟网络延迟
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money = money + count;
			
		}
		
		/**
		 * Gets moneynonSync.
		 *
		 * @param count comments
		 * @return moneynonSync
		 */
		@SuppressWarnings("static-access")
		public void getMoneyNoSync(int count){
			// 模拟网络延迟
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money = money - count;
			
		}
		
		/**
		 * putMoneySync.
		 *
		 * @param count comments
		 */
		@SuppressWarnings("static-access")
		public synchronized void putMoneySync(int count){
			// 模拟网络延迟
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money = money + count;
			
		}
		
		/**
		 * nonSyncGetMoney.
		 *
		 * @param count comments
		 * @return moneySync
		 */
		@SuppressWarnings("static-access")
		public synchronized void getMoneySync(int count){
			// 模拟网络延迟
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			money = money - count;
		}

		/**
		 * Gets money.
		 *
		 * @return money
		 */
		public int getMoney() {
			return money;
		}

		/**
		 * Sets money.
		 *
		 * @param money comments
		 */
		public void setMoney(int money) {
			this.money = money;
		}
	}
	
	/**
	 * 访问帐号的线程.
	 *
	 * @date 2013-11-18 18:26:09
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	static class AccessThread extends Thread{
		
		/** account. */
		private Account account = null;
		
		/** method. */
		private String method = null;
		
		/**
		 * Instantiates a new AccessThread.
		 *
		 * @param account comments
		 * @param method comments
		 */
		public AccessThread(Account account,String method){
			this.account = account;
			this.method = method;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			if (method.equals("putMoneyNoSync")) {
				account.putMoneyNoSync(200);
			}else if (method.equals("putMoneySync")) {
				account.putMoneySync(200);
			}else if (method.equals("getMoneyNoSync")) {
				account.getMoneyNoSync(200);
			}else if (method.equals("getMoneySync")) {
				account.getMoneySync(200);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Account account = new Account();
		// 非同步方法执行时，有可能两次计算同时发生，使用了后一次赋值，导致计算不正确
		// 经过10次存钱和取钱后，正确的剩余金钱应为0元,同步方法总能正确计算剩余金钱为0，非同步方法计算后可能得到金钱为非0.
		for (int i = 0; i < 10; i++) {
			Thread t1 = new AccessThread(account, "putMoneyNoSync");
			Thread t2 = new AccessThread(account, "getMoneyNoSync");
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		}
		System.out.println("非同步方法执行,account.getMoney:"+account.getMoney());
		account = new Account();
        for (int i = 0; i < 10; i++) {
        	Thread t1 = new AccessThread(account, "putMoneySync");
        	Thread t2 = new AccessThread(account, "getMoneySync");
    		t1.start();
    		t2.start();
    		t1.join();
    		t2.join();	
		}
		System.out.println("同步方法执行,account.getMoney:"+account.getMoney());
		
	}

}
