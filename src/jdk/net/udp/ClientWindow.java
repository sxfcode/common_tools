package jdk.net.udp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The Class ClientWindow.
 * udp测试
 * @date 2013-7-31 16:43:51
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ClientWindow extends JFrame implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 发送按钮. */
	JButton sendButton;
	
	/** 输入发送内容文本框. */
	JTextField inputField;
	
	/** 服务器返回内容的文本域. */
	JTextArea outputTextArea;
	
	/**
	 * 构造方法.
	 */
	public ClientWindow(){
		// 输入
		JLabel inputLabel = new JLabel("输入:");
		inputField = new JTextField(20);
		JPanel inputPanel = new JPanel();
		inputPanel.add(inputLabel);
		inputPanel.add(inputField);
		
		// 服务器消息
		JLabel outputLabel = new JLabel("服务器消息:");
		outputTextArea = new JTextArea(6, 20);
		JScrollPane crollPane = new JScrollPane(outputTextArea);
		JPanel outPanel = new JPanel();
		outPanel.setLayout(new BorderLayout());
		outPanel.add(outputLabel,BorderLayout.NORTH);
		outPanel.add(crollPane,BorderLayout.CENTER);
		
		// 发送
		sendButton = new JButton("发送");
		sendButton.addActionListener(this);
		
		// 总体布局
		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new BorderLayout());
		finalPanel.add(inputPanel,BorderLayout.NORTH);
		finalPanel.add(sendButton,BorderLayout.CENTER);
		finalPanel.add(outPanel,BorderLayout.PAGE_END);
		
		setTitle("我的客户端");

		// 添加到容器中
		this.getContentPane().add(finalPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getUdp(10089);
		
	}

	/**
	 * actionPerformed.
	 *
	 * @param e comments
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 执行发送操作
		if (e.getSource()==sendButton) {
			try {
				// 发送文本框中的文字
				//client.sendRequest(inputField.getText());
				sendUdp(inputField.getText());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			// 接收服务器响应，并显示到文本框中
			// outputTextArea.append(client.getResponse()+"\r\n");
		}
	}
	
	/**
	 * 发送UDP数据包.
	 *
	 * @param testMsg comments
	 */
	public void sendUdp(String testMsg){
		System.out.println("发送UDP数据包:"+testMsg);
		String host = "127.0.0.1";
		int sendPort = 10088;
		int receivePort = 10089;
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			byte[] msg = testMsg.getBytes();
			// 创建报文
			DatagramPacket packet = new DatagramPacket(msg, msg.length,address,receivePort);
			// 创建socket
			DatagramSocket socket = new DatagramSocket(sendPort);
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 启动一个线程接收UDP数据包.
	 *
	 * @param listenPort comments
	 * @return udp
	 */
	public void getUdp(int listenPort){
		Thread thread = new Thread(){
			public void run(){
				
				int port = 10089;
				// 创建socket
				try {
					DatagramSocket socket;
					socket = new DatagramSocket(port);
					// 接收数据缓冲区
					byte[] buffer = new byte[2048];
					
					// 创建最大长度为buffer.length的数据包
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					
					// 接收数据包
					try {
						while (true) {
							socket.receive(packet);
							// 将接收到的报文封装成字符串
							String msg = new String(buffer, 0, packet.getLength());
							System.out.println("接收UDP数据包,host:"+packet.getAddress().getHostAddress()+",port:"+packet.getPort()+",msg:"+msg);
							outputTextArea.append(msg+"\r\n");
							if (msg.equals("quit")) {
								System.out.println("退出接收UDP数据包");
								break;
							}
							// 重设接收数据包的长度
							packet.setLength(buffer.length);
							Thread.sleep(1000);
						}
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ClientWindow window = new ClientWindow();
		window.pack();
		// 连接服务器
		// window.client = new Client("127.0.0.1", 10086);
		window.setVisible(true);
	}

}
