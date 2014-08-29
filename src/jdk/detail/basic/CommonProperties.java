package jdk.detail.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * The Class CommonProperties.
 * 工具类用来读取properties中配置的属性。
 *
 * @date 2013-11-14 16:15:37
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CommonProperties {
	
	/** properties. */
	private static Properties properties = new Properties();
	static{
		try {
			FileInputStream in = new FileInputStream(new File("D:\\dev\\workspace\\common_tools\\resource\\common.properties"));
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets property.
	 *
	 * @param name comments
	 * @return property
	 */
	public static String getProperty(String name){
		return properties.getProperty(name);
	}
	public static void main(String[] args) {
		System.out.println(CommonProperties.getProperty("user"));
	}

}
