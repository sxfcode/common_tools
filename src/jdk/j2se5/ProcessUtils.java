package jdk.j2se5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Class ProcessUtils.
 * 调用系统进程.
 * @date 2013-8-2 16:10:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ProcessUtils {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec(new String[]{"cmd /c dir"});
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"gb2312"));
		String read ="";
		while ((read=in.readLine())!=null) {
			System.out.println(read);
		}
		//in.close();
		//p.destroy();
		
	}
	
	/**
	 * execute.
	 *
	 * @param commandName comments
	 * @param param comments
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void execute(String commandName,String param) throws IOException, InterruptedException{
		ProcessBuilder pb = new ProcessBuilder(commandName);
		//pb.directory(new File("D:\\"));
		Process p = pb.start();
		BufferedReader in = null;
		// 0表示正常退出,非0表示非正常退出
		if (p.waitFor()!=0) {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		}else {
			in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		}
		String read ="";
		while ((read=in.readLine())!=null) {
			System.out.println(read);
		}
		p.destroy();
	}

}
