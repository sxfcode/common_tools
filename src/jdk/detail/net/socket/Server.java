package jdk.detail.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Class Server. 
 * 一个简单的socket服务器，能接受客户端请求,并将请求返回给客户端.
 * 
 * @date 2013-7-31 16:19:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class Server {

	/** serverSocket. */
	ServerSocket serverSocket = null;

	/** clientSocket. */
	Socket clientSocket = null;

	/** in. */
	BufferedReader in;

	/** out. */
	PrintStream out;

	/**
	 * Instantiates a new Server.
	 * 
	 * @param port
	 *            comments
	 */
	public Server(int port) {
		System.out.println("服务器正在监听端口:" + port);
		// 创建监听socket
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("监听端口" + port + "失败");
			e.printStackTrace();
		}
		// 接受连接请求
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("接受连接请求失败");
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取客户端请求.
	 * 
	 * @return request
	 */
	public String getRequest() {
		String msg = null;
		try {
			msg = in.readLine();
			System.out.println("Server 收到请求:" + msg);
		} catch (Exception e) {
			System.out.println("无法读取端口.....");
			System.exit(0);
		}
		return msg;
	}

	/**
	 * 服务器发送相应.
	 * 
	 * @param response
	 *            comments
	 */
	public void sendResponse(String response) {
		try {
			out.println(response);
			System.out.println("响应请求:" + response);
		} catch (Exception e) {
			System.out.println("服务器响应失败");
		}
	}

	public static void main(String[] args) {
		// 启动服务器
		Server server = new Server(10086);
		try {
			while (true) {
				server.sendResponse(server.getRequest());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
