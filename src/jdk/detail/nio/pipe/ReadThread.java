package jdk.detail.nio.pipe;

import java.io.IOException;
import java.io.PipedReader;

/**
 * The Class ReadThread.
 *
 * @date 2014-7-3 17:44:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class ReadThread extends Thread {
	
	/** pipeReader. */
	private PipedReader pipeReader;
	
	/** cb. */
	private char[] cb = new char[100];
	
	/**
	 * Instantiates a new ReadThread.
	 *
	 * @param reader comments
	 */
	public ReadThread(PipedReader reader){
		this.pipeReader = reader;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("static-access")
	public void run(){
		while (true) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int count = 0;
			try {
				count = pipeReader.read(cb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String tmp = new String(cb,0,count);
			System.out.println("线程:"+Thread.currentThread().getId()+",接收:"+tmp);
			if("exit".equals(tmp)){
				System.out.println("reader退出");
				break;
			}
		}
		try {
			pipeReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
