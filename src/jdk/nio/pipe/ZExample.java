package jdk.nio.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
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
		
		HashMap<String,String> m1 = new HashMap<String,String>();
		m1.put("n1", "number1");
		m1.put("n2", "number2");
		ArrayList<String> al =new ArrayList<String>();
		al.add("AL01");
		al.add("AL02");
		Set<String> st = new HashSet<String>();
		st.add("set01");
		st.add("set02");
		String temp = "temp01";
		System.out.println(temp);
		rt.join();
		
	}

}
