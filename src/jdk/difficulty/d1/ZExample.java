package jdk.difficulty.d1;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * The Class ZExample.
 * 子对象继承父对象，实际上子对象和父对象在内存中是两个对象，
 * 子对象继承的父对象属性和方法完全是子对象的，
 * 跟父对象的方法和属性是分开的，只是名称相同而已。
 * 就像父亲和儿子都有身体四肢，只是儿子的身体四肢跟父亲的身体四肢只是长的像，但完全是属于两个人的不同的东西。
 *
 * @date 2014-6-18 14:18:37
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class ZExample {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Parent p = new Child(20);
	}
}
