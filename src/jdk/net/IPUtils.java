package jdk.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The Class IPUtils.
 * 
 * @date 2013-7-29 19:45:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class IPUtils {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
//		System.out.println(getLocalIp());
//		System.out.println(getLocalHostName());
//		System.out.println(getIpByName(getLocalHostName()));
//		System.out.println(getAllIpByName(getLocalHostName()));
		//System.out.println(getAllIpByName("www.baidu.com"));
		System.out.println(getAllIpByName("order.lefeng.com"));
	}

	/**
	 * Gets 获取本地ip.
	 * 
	 * @return localIp
	 */
	public static String getLocalIp() {
		String result = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			result = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Gets 获取本机主机名.
	 * 
	 * @return localHostName
	 */
	public static String getLocalHostName() {
		String result = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			result = addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets 根据主机获取ip.
	 *
	 * @param name comments
	 * @return ipByName
	 */
	public static String getIpByName(String name) {
		String result = "";
		try {
			InetAddress addr = InetAddress.getByName(name);
			result = addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取当前主机的所有ip.
	 *
	 * @param name comments
	 * @return ipByName
	 */
	public static String getAllIpByName(String name) {
		String result = "";
		try {
			InetAddress[] addr = InetAddress.getAllByName(name);
			for (int i = 0; i < addr.length; i++) {
				result = result+","+addr[i].getHostAddress();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return result;
	}

}
