package jdk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

//import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * The Class SimpleCalculator. 
 * 这是一个简单的计算器。
 * 
 * @date 2013-7-14 15:28:13
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SimpleCalculator extends JFrame implements ActionListener {
	
	/** 按钮文本:1组按钮文本. */
	private final String[] button_text_part1 = { "7", "8", "9", "/", "sqrt",
			"4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-",
			".", "+", "=" };
	
	/** 按钮文本:2组按钮文本. */
	private final String[] button_text_part2 = { "Backspace", "CE", "C" };
	
	/** 按钮文本:3组按钮文本. */
	private final String[] button_text_part3 = { " ", "MC", "MR", "MS", "M+" };

	/** 功能按钮:1组按钮. */
	private JButton[] button_part1 = new JButton[button_text_part1.length];
	
	/** 功能按钮:2组按钮. */
	private JButton[] button_part2 = new JButton[button_text_part2.length];
	
	/** 功能按钮:3组按钮. */
	private JButton[] button_part3 = new JButton[button_text_part3.length];

	/** 在操作统计框直接显示的按钮. */
	private List<String> directDisplayButtons = new ArrayList<String>();

	/** 在操作统计框非直接显示的按钮. */
	private List<String> notDirectDisplayButtons = new ArrayList<String>();

	/** 计算结果文本框. */
	private JTextField result = new JTextField("0");

	/** 操作监控文本框. */
	private JTextField monitor_opt = new JTextField("");

	/** monitor_str. */
	private StringBuffer monitor_opt_str = new StringBuffer("");
	
	/** 操作步骤分隔文本框. */
	private JTextField monitor_opt_split = new JTextField("");

	/** monitor_str. */
	private StringBuffer monitor_opt_split_str1 = new StringBuffer("");
	
	/** str0t9. */
	private String str0t9 = "0123456789";
	
	/** isReset. */
	private boolean isReset = true;
	
	/** isFirstNumber. */
	private boolean isFirstNumber = true;

	/** memory. */
	private double memory = 0.0;

	/** 临时结果. */
	private double tempReult = 0.0;
	
	/** tempReultStr. */
	private String tempReultStr = "";

	/** 当前运算符号. */
	private String operator = "=";

	/**
	 * Instantiates a new SimpleCalculator.
	 */
	public SimpleCalculator() {
		super();
		initComponents();
		initData();
		initUI();
		
		
		
	}
	
	/**
	 * 初始化外观，皮肤等.
	 */
	public void initUI(){
		// 背景颜色
		this.setBackground(Color.LIGHT_GRAY);
		// 标题
		this.setTitle("简单计算器");
		// 设置显示坐标
		this.setLocation(500, 300);
		// 是否修改计算器 的大小。
		this.setResizable(false);
		// 自适应各组件大小。
		this.pack();
		try {
			// 设置皮肤
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			//UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * initData.
	 */
	private void initData(){
		directDisplayButtons.add("0");
		directDisplayButtons.add("1");
		directDisplayButtons.add("2");
		directDisplayButtons.add("3");
		directDisplayButtons.add("4");
		directDisplayButtons.add("5");
		directDisplayButtons.add("6");
		directDisplayButtons.add("7");
		directDisplayButtons.add("8");
		directDisplayButtons.add("9");
		directDisplayButtons.add("+");
		directDisplayButtons.add("-");
		directDisplayButtons.add("*");
		directDisplayButtons.add("/");
		directDisplayButtons.add(".");
		directDisplayButtons.add("=");
	}

	/**
	 * 初始化各种组件.
	 */
	private void initComponents() {
		// step 1:设置文本框
		// 文本框右对齐
		result.setHorizontalAlignment(JTextField.RIGHT);
		// 文本框不可编辑。
		result.setEditable(false);
		// 文本框背景色
		result.setBackground(Color.WHITE);

		monitor_opt.setHorizontalAlignment(JTextField.RIGHT);
		// 文本框不可编辑。
		monitor_opt.setEditable(false);
		// 文本框背景色
		monitor_opt.setBackground(Color.WHITE);
		
		monitor_opt_split.setHorizontalAlignment(JTextField.RIGHT);
		// 文本框不可编辑。
		monitor_opt_split.setEditable(false);
		// 文本框背景色
		monitor_opt_split.setBackground(Color.WHITE);

		// step 2:第一组按钮
		// 创建画板1
		JPanel panel_1 = new JPanel();
		// 使用网格布局器，4行5列布局，网隔之间水平方向间隔3个像素，垂直方向间隔3个像素
		panel_1.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < button_part1.length; i++) {
			button_part1[i] = new JButton(button_text_part1[i]);
			panel_1.add(button_part1[i]);
			button_part1[i].setForeground(Color.BLUE);
		}

		// 运算符用红色，其他按键用蓝色显示
		button_part1[3].setForeground(Color.RED);
		button_part1[8].setForeground(Color.RED);
		button_part1[13].setForeground(Color.RED);
		button_part1[18].setForeground(Color.RED);
		button_part1[19].setForeground(Color.RED);

		// step 3:第二组按钮
		// 创建画板2
		JPanel panel_2 = new JPanel();
		// 使用网格布局器，1行3列布局，网隔之间水平方向间隔3个像素，垂直方向间隔3个像素
		panel_2.setLayout(new GridLayout(1, 3, 3, 3));
		for (int i = 0; i < button_part2.length; i++) {
			button_part2[i] = new JButton(button_text_part2[i]);
			panel_2.add(button_part2[i]);
			button_part2[i].setForeground(Color.RED);
		}

		// step 4:第三组按钮
		// 创建画板3
		JPanel leftPanel = new JPanel();
		// 使用网格布局器，5行1列布局，网隔之间水平方向间隔3个像素，垂直方向间隔3个像素
		leftPanel.setLayout(new GridLayout(5, 1, 3, 3));
		for (int i = 0; i < button_part3.length; i++) {
			button_part3[i] = new JButton(button_text_part3[i]);
			leftPanel.add(button_part3[i]);
			button_part3[i].setForeground(Color.RED);
		}

		// step 5:整体布局
		// 创建框架画板，用来放置上面创建的组件。
		JPanel centerPanel = new JPanel();
		// 边界布局管理器，画板组件之间水平和垂直方向上的间隔均为3像素。
		centerPanel.setLayout(new BorderLayout(3, 3));
		centerPanel.add("North", panel_2);
		centerPanel.add("Center", panel_1);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(3, 3));
		topPanel.add("North", monitor_opt);
		topPanel.add("Center", monitor_opt_split);
		topPanel.add("South", result);

		// 整体布局
		Container container = getContentPane();
		container.setLayout(new BorderLayout(3, 5));
		container.add("North", topPanel);
		container.add("Center", centerPanel);
		container.add("West", leftPanel);

		// 为按钮添加监听事件
		for (int i = 0; i < button_part1.length; i++) {
			button_part1[i].addActionListener(this);
		}
		for (int i = 0; i < button_part2.length; i++) {
			button_part2[i].addActionListener(this);
		}
		for (int i = 0; i < button_part3.length; i++) {
			button_part3[i].addActionListener(this);
		}
	}

	/**
	 * actionPerformed.
	 * 
	 * @param e
	 *            comments
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取事件源的标签
		String label = e.getActionCommand();
		// 操作监控
		// if (isReset) 考虑重置功能
		if (label.equals("CE")) {
			handleCE();
		}else if (label.equals("C")) {
			handleC();
		}else if (label.equals("Backspace")) {
			handleBackSpace();
		}else if(str0t9.indexOf(label)!=-1){
			handleNumber(label);
		}else {
			handleOperation(label);
		}
		monitor_opt.setText(monitor_opt_str.toString());

	}
	
	/**
	 * handleCE.
	 *
	 * @return String
	 */
	private String handleCE(){
		result.setText("0");
		isReset = true;
		monitor_opt_str.setLength(0);
		isFirstNumber = true;
		return "";
	}
	
	/**
	 * handleC.
	 *
	 * @return String
	 */
	private String handleC(){
		monitor_opt_str.substring(0, monitor_opt_str.length()-result.getText().length());
		result.setText("0");
		isFirstNumber = true;
		if (tempReultStr!=null&&tempReultStr!="") {
			monitor_opt_str.append(",");
		}
		return "";
	}
	
	/**
	 * handleNumber.
	 *
	 * @param key comments
	 * @return String
	 */
	private String handleNumber(String key){
		if (isFirstNumber) {
			
			if (tempReultStr!=null&&tempReultStr!=""&&operator.equals("=")) {
				monitor_opt_str.append(",");
			}
			monitor_opt_str.append(key);
			result.setText(key);
			// 输入小数点，并且之前没有输入过小数点。
		} else if ((key.equals(".")&&(result.getText().indexOf(".")==-1))) {
			result.setText(result.getText()+key);
			monitor_opt_str.append(key);
		} else if(!key.equals(".")){
			result.setText(result.getText()+key);
			monitor_opt_str.append(key);
		}
		isFirstNumber = false;
		
		return "";
	}
	
	/**
	 * handleBackSpace.
	 *
	 * @return String
	 */
	private String handleBackSpace(){
		String text = result.getText();
		int i = text.length();
		if (i > 0) {
			//退格，将文本最后一个字符去掉
			text = text.substring(0, i - 1);
			if (text.length() == 0) {
				//如果文本没有了内容，则初始化计算器的各种值
				result.setText("0");
			} else {
				//显示新的文本
				result.setText(text);
			}
			monitor_opt_str.substring(0, monitor_opt_str.length()-1);
		}
		return "";
	}
	
	/**
	 * handleOperation.
	 *
	 * @param key comments
	 * @return String
	 */
	private String handleOperation(String key){
		isFirstNumber = true;
		//tempReultStr = result.getText();
		if (key.equals("=")) {
			double first = Double.parseDouble(tempReultStr);
			double two = Double.parseDouble(result.getText());
			double three = 0.0;
			if ("+".equals(operator)) {
				three = first +two;
			}else if ("-".equals(operator)) {
				three = first -two;
			}else if ("*".equals(operator)) {
				three = first*two;
			}else if ("/".equals(operator)) {
				three = first/two;
			}
			result.setText(three+"");
			monitor_opt_str.append(key);
			monitor_opt_str.append(three);
			monitor_opt_str.append(",");
			operator=key;
			
		}else if ("+-*/".indexOf(key)!=-1) {
			tempReultStr = result.getText();
			operator=key;
			monitor_opt_str.append(key);
		}
		
		return "";
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SimpleCalculator sc = new SimpleCalculator();
		sc.setVisible(true);
		sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
