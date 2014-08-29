package jdk.detail.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The Class SimpleDialog.
 * 简单的对话框应用
 *
 * @date 2013-7-22 14:58:10
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("serial")
public class SimpleDialog extends JFrame implements ActionListener {
	public static void main(String[] args) {
		SimpleDialog dialog = new SimpleDialog();
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private MyDialog dialog;
	private JTextArea textArea;
	String lineSeparator;
	
	public SimpleDialog(){
		super("对话框实例");
		
		// 5行30个字符的文本框
		textArea = new JTextArea(5,30);
		textArea.setEditable(false);
		getContentPane().add("Center",new JScrollPane(textArea));
		
		// 添加一个按钮
		JButton button = new JButton("添加内容");
		button.addActionListener(this);
		JPanel panel = new JPanel();
		panel.add(button);
		getContentPane().add("South",panel);
		lineSeparator = System.getProperty("line.separator");
		
		//
		this.pack();
	}

	/**	
	 * actionPerformed.
	 *
	 * @param arg0 comments
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (dialog==null) {
			dialog = new MyDialog(this, "输入对话框");
		}
		dialog.setVisible(true);
	}
	
	public void setText(String text){
		textArea.append(text+lineSeparator);
	}
	
	/**
	 * 内部类
	* @date 2013-7-22 下午03:01:16 
	* @author 宿晓斐 
	* @version 1.0
	* @since jdk 1.6,common_tools 1.0
	 */
	class MyDialog extends JDialog implements ActionListener{
		
		// 文本框，用于输入字符串
		JTextField field;
		// 父窗口
		SimpleDialog parent;
		// 确定
		JButton setButton;
		MyDialog(JFrame parentFrame,String title){
			// false表示允许激活其他窗体
			super(parentFrame,title,false);
			parent = (SimpleDialog) parentFrame;
			
			// 添加Lable和输入文本框
			JPanel p1= new JPanel();
			JLabel label = new JLabel("请输入添加的文本");
			p1.add(label);
			field = new JTextField(30);
			field.addActionListener(this);
			p1.add(field);
			getContentPane().add("Center",p1);
			
			// 添加确定和取消按钮
			JPanel p2 = new JPanel();
			p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton cancel = new JButton("取消");
			setButton = new JButton("确认");
			cancel.addActionListener(this);
			setButton.addActionListener(this);
			p2.add(setButton);
			p2.add(cancel);
			
			getContentPane().add("South",p2);
			
			// 调整布局大小
			pack();
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source== setButton) {
				parent.setText(field.getText());
			}
			field.selectAll();
			// 隐藏对话框
			setVisible(false);
		}
		
	}

}
