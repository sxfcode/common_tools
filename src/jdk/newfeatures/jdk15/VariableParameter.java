package jdk.newfeatures.jdk15;

/**
 * The Class VariableParameter.
 *
 * @date 2014-7-17 15:08:56
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class VariableParameter {

	/**
	 * print.
	 *
	 * @param ps comments
	 */
	public static void print(String ... ps){
		for (int i = 0; i < ps.length; i++) {
			System.out.println("参数:"+i+"="+ps[i]);
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		VariableParameter.print("a","b","c");
	}

}
