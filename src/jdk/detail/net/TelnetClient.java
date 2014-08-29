package jdk.detail.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The Class TelNetClient.
 *
 * @date 2013-7-31 15:27:34
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class TelnetClient {
	
	/** 默认端口. */
	public static final int DEFAULT_PORT=23;
	
	/** 默认主机地址. */
	public static final String DEFAULT_HOST = "127.0.0.1";
	
	/** 连接主机的host和端口. */
	private String host = "";
	
	/** port. */
	private int port;
	
	/** 连接主机的socket. */
	Socket socket= null;
	
	/** 发送和接收数据的管道. */
	Pipe sendPipe = null;
	
	/** receivePipe. */
	Pipe receivePipe = null;
	
	/**
	 * TelnetCient.
	 */
	public TelnetClient(){
		host = DEFAULT_HOST;
		port = DEFAULT_PORT;
	}
	
	/**
	 * Instantiates a new TelnetClient.
	 *
	 * @param host comments
	 * @param port comments
	 */
	public TelnetClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	/**
	 * 登陆 远程服务器.
	 */
	public void telnet(){
		System.out.println("连接远程服务器"+host+":"+port+"开始!");
		try {
			socket = new Socket(host,port);
			// 将socket的输入端数据（来自服务器端）流向本地标准输出，即接收数据
			receivePipe = new Pipe(socket.getInputStream(),System.out);
			receivePipe.start();
			// 将本地的标准输入数据流向socket的输出端，即流向服务器端，发送数据
			sendPipe = new Pipe(System.in,socket.getOutputStream());
			sendPipe.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("连接失败：" + e);
			e.printStackTrace();
		}
		
		System.out.println("连接远程服务器"+host+":"+port +"成功!");
		System.out.println("开始通信---");
	}
	
	/**
	 * 断开连接.
	 */
	public void disconnect(){
		if (socket!=null) {
			try {
				socket.close();
				System.out.println("中止通信");
				System.out.println("关闭服务器连接");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 管道类，将输入流的数据，输出到输出流
	 * 单独的线程.
	 *
	 * @date 2013-7-31 15:39:56
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	class Pipe extends Thread{
		//管道输入流，输出流
		/** is. */
		BufferedReader is;
		
		/** os. */
		PrintStream os;
		
		/**
		 * Instantiates a new Pipe.
		 *
		 * @param in comments
		 * @param out comments
		 */
		Pipe(InputStream in,OutputStream out){
			this.is = new BufferedReader(new InputStreamReader(in));
			this.os = new PrintStream(out);
		}
		
		/**
		 * 线程run方法，用来将输入流的数据写入到输出流.
		 */
		public void run(){
			String line;
			try {
				// 读取输入流的数据
				while ((line=is.readLine())!=null) {
					os.print(line);
					os.print("\r\n");
					os.flush();
					if (line.equals("exit")) {
						disconnect();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		new TelnetClient().telnet();
	}
}
