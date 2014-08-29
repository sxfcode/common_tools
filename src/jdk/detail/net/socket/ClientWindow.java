package jdk.detail.net.socket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The Class ClientWindow.
 * socket客户端界面
 * @date 2013-7-31 16:43:51
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ClientWindow extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	/** 发送按钮. */
	JButton sendButton;
	
	/** 输入发送内容文本框. */
	JTextField inputField;
	
	/** 服务器返回内容的文本域. */
	JTextArea outputTextArea;
	
	/** 客户端对象. */
	Client client;
	
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
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 执行发送操作
		if (e.getSource()==sendButton) {
			try {
				// 发送文本框中的文字
				client.sendRequest(inputField.getText());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			// 接收服务器响应，并显示到文本框中
			outputTextArea.append(client.getResponse()+"\r\n");
		}
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
		window.client = new Client("127.0.0.1", 10086);
		window.setVisible(true);
	}

}
