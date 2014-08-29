package jdk.detail.nio.pipe;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Scanner;

/**
 * The Class WriteThread.
 *
 * @date 2014-7-3 17:44:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class WriteThread extends Thread {
	
	/** pipeWriter. */
	public PipedWriter pipeWriter;
	
	/** scanner. */
	public Scanner scanner;
	
	/**
	 * Instantiates a new WriteThread.
	 *
	 * @param writer comments
	 */
	public WriteThread(PipedWriter writer){
		this.pipeWriter =writer;
		this.scanner = new Scanner(System.in);
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
			String test =scanner.nextLine();
			System.out.println("线程:"+Thread.currentThread().getId()+"发送:"+test);
			try {
				pipeWriter.write(test);
				pipeWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if("exit".equals(test)){
				System.out.println("write退出");
				break;
			}
		}
		try {
			pipeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
