package jdk.detail.j2se5;

//注意静态导入的写法: import static
import static java.lang.Math.max;//导入了Math的max方法
import static java.lang.Integer.*;//导入了Integer的所有静态方法和属性


/**
 * The Class StaticImport.
 * 静态导入，通常是静态导入静态方法和静态属性,可以不通过类名，直接在类中使用。
 * 需要注意的是静态导入的方法和属性必须保证唯一性。
 *
 * @date 2013-8-2 15:45:20
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class StaticImport {
	public static void main(String[] args) {
		System.out.println(max(5, 10));
		System.out.println(MAX_VALUE);
	}

}
