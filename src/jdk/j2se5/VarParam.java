package jdk.j2se5;

/**
 * The Class VarParam.
 * 可变长度参数
 *
 * @date 2013-8-2 16:50:00
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class VarParam {
	
	public static void main(String[] args) {
		print("h1");
		print("h1","h2","h3");
	}
	
	/**
	 * 可变长参数.
	 *
	 * @param msg comments
	 */
	public static void print(String ... msg){
		for (String m : msg) {
			System.out.println(m);
		}
	}
}
