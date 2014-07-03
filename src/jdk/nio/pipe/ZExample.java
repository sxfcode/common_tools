package jdk.nio.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * The Class ZExample.
 * PipeReader和PipeWriter是通过synchronized关键字实现同步的
 *
 * @date 2014-7-3 17:44:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class ZExample {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PipedReader reader = new PipedReader();
		PipedWriter writer = new PipedWriter();
		try {
			reader.connect(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ReadThread rt = new ReadThread(reader);
		WriteThread wt = new WriteThread(writer);
		rt.start();
		wt.start();
	}

}
