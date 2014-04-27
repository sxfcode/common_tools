package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class DateUtils.
 *
 * @date 2013-11-15 10:40:27
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public final class DateUtils {
	
	/** format_default. */
	private static SimpleDateFormat format_default = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 默认将日期格式化成字符串.
	 * 默认格式包含年月日和时分秒
	 *
	 * @param src comments
	 * @return String
	 */
	public static String format(Date src){
		return format_default.format(src);
	}
	
	/**
	 * format.
	 *
	 * @param src comments
	 * @param pattern comments
	 * @return String
	 */
	public static String format(Date src,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(src);
		
	}
	
	/**
	 * 解析日期.
	 *
	 * @param src comments
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date parse(String src) throws ParseException{
		return format_default.parse(src);
	}

}
