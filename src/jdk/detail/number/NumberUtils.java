package jdk.detail.number;

import java.util.Random;

/**
 * The Class NumberUtils. 数字工具类
 * 
 * @date 2013-11-13 17:42:54
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class NumberUtils {
	
	/**
	 * 进制转换。
	 * 将某种进制的数字转换成指定进制，
	 * 并以字符串的形式输出转换结果。
	 * 例如：convert("11", 10, 16)=b 	将10进制的"11"转换成16进制的数字.
	 *
	 * @param number 需要处理的数字
	 * @param sourceRadix 数字的当前进制
	 * @param targetRadix 需要转换成的进制
	 * @return String
	 */
	public static String convert(String number,int sourceRadix,int targetRadix){
		Long l = Long.parseLong(number, sourceRadix);
		return Long.toString(l, targetRadix);
	}
	
	/**
	 * 获取一个int类型随机数，根据当前系统的毫秒数.
	 *
	 * @return random
	 */
	public static int getRandom(){
		Random r = new Random();
		return r.nextInt();
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println(NumberUtils.convert("11", 10, 16));
		System.out.println(NumberUtils.getRandom());
	}
		

}
