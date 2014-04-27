package datastruct;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * The Class DataStruct.
 * 数据结构
 *
 * @date 2013-12-13 14:26:39
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DataStruct {
	
	/**
	 * 演示用对象.
	 *
	 * @date 2013-12-13 14:31:17
	 * @author 宿晓斐
	 * @version 1.0
	 * @since jdk 1.6,common_tools 1.0
	 */
	public static class MyObject{
		
		/** number. */
		private int number;
		
		/**
		 * Instantiates a new MyObject.
		 *
		 * @param number comments
		 */
		MyObject(int number){
			this.number = number;
		}
		
		/**
		 * Gets number.
		 *
		 * @return number
		 */
		public int getNumber() {
			return number;
		}
		
		/**
		 * Sets number.
		 *
		 * @param number comments
		 */
		public void setNumber(int number) {
			this.number = number;
		}
	}
	
	/**
	 * 数组及引用是java编程语言所提供的最基本的数据结构，
	 * 其他的数据结构都由数组和引用这两种结构扩展而来.
	 */
	@SuppressWarnings("unused")
	public static void useArray(){
		int[] arr = new int[10];
	}
	
	/**
	 * 堆栈.继承自Vector
	 * 
	 */
	public static void useStatck(){
		Stack stack = new Stack();
		MyObject m_0 = new MyObject(0);
		stack.push(m_0);
		stack.push(new MyObject(1));
		stack.push(new MyObject(2));
		System.out.println(stack.search(m_0));
		
	}
	
	
	/**
	 * clear只是从集合中清除元素，并不会销毁元素.
	 */
	public static void useClear(){
		// 从集合中移除元素不代表销毁元素,只要还能得到引用就可以继续使用。
		MyObject m_0 = new MyObject(0);
		Set<MyObject> set = new HashSet<MyObject>();
		set.add(m_0);
		set.clear();
		System.out.println(m_0.getNumber());
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		DataStruct.useStatck();
		DataStruct.useClear();
		
		
	}

}
