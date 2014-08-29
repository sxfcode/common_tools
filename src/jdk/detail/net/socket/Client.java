package jdk.detail.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * The Class Client.
 * socket客户端
 *
 * @date 2013-7-31 16:35:24
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class Client {
	/** in. */
	BufferedReader in;

	/** out. */
	PrintStream out;
	
	
	/**
	 * 在构造方法中连接服务器.
	 *
	 * @param host comments
	 * @param port comments
	 */
	public Client(String host,int port){
		Socket clientSocket;
		try {
			clientSocket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
		} catch (Exception e) {
			System.out.println("连接服务器失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送请求.
	 *
	 * @param msg comments
	 */
	public void sendRequest(String msg){
		out.println(msg);
		System.out.println("Client 发送请求: " + msg);
	}
	
	/**
	 * 接收响应.
	 *
	 * @return response
	 */
	public String getResponse(){
		String msg = null;
		try {
			msg = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("客户端接收到响应:"+msg);
		return msg;
	}
}
