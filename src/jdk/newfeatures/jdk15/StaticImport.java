package jdk.newfeatures.jdk15;

import static jdk.newfeatures.jdk15.StaticObject.printName;
import static jdk.newfeatures.jdk15.StaticObject.publicField;


/**
 * The Class StaticImport.
 * 静态导入的是public的静态方法和名称
 *
 * @date 2014-7-17 14:27:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class StaticImport {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		printName("123"+publicField);
	}

}
