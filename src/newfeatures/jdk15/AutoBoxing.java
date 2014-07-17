package newfeatures.jdk15;

/**
 * The Class AutoBoxing.
 * 关于==和equals的不同，最本质的是:
 * ==是一个操作符，遵循jdk规范执行操作
 * (关于==操作符的jdk规范如下:
 * 当==符号两边为基本数据类型，进行基于数值的比较，
 * 当==两边是对象类型则进行基于对象地址的判定，
 * 当对象两边是基本布尔值时，则全真或全假时返回真，其他返回假
 * )
 * equals是属于一个对象的方法，其本质是一个方法，执行结果依赖具体实现
 *  
 * @date 2014-7-17 11:42:48
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class AutoBoxing {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//编写代码，请使用执行含义明确的代码，不编写奇怪花哨的代码
		
		// eg1
		int int100 = 100;
		Integer integer100 = 100;
		System.out.println("eg1:");
		System.out.println(int100==integer100);
		System.out.println(integer100.equals(int100));
		// eg2
		Integer integer201 = 200;
		Integer integer202 = 200;
		System.out.println("eg2:");
		System.out.println(integer201==integer202);
		System.out.println(integer202.equals(integer202));
	}

}
