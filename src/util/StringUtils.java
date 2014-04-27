package util;

import java.io.UnsupportedEncodingException;


/**
 * The Class StringUtils.
 *
 * @date 2013-11-15 10:27:16
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public final class StringUtils {
	/**
	 * 检查文本是否为空，可以用自定义方法进行替换.
	 *
	 * @param src comments
	 * @return true, if successful
	 */
	public static boolean hasText(String src){
		if (src!=null) {
			String temp = src.trim();
			if (temp!="") {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 转换字符串的编码.
	 *
	 * @param src comments
	 * @param charset comments
	 * @return String
	 */
	public static String toCharset(String src,String charset){
		byte[] bs = src.getBytes();
		try {
			return new String(bs, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 判断字符串中是否包含汉字.
	 *
	 * @param src comments
	 * @return true, if successful
	 */
	public static boolean hasChinese(String src){
		if (src.getBytes().length!=src.length()) {
			return true;
		}
		return false;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String test = "你好";
		System.out.println(test);
		System.out.println(StringUtils.toCharset(test, "gb2312"));
		System.out.println(test.length());
		System.out.println(test.getBytes().length);
	}
}
