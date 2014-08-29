package jdk.detail.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * The Class UrlUtils.
 *
 * @date 2013-7-29 20:01:58
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class UrlUtils {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			printInfo(new URL("http://shopping.lefeng.com/cart/cart.jsp"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * printInfo.
	 *
	 * @param url comments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void printInfo(URL url) throws IOException{
		// url基本信息
		System.out.println("File:"+url.getFile());
		System.out.println("Protocol:"+url.getProtocol());
		System.out.println("DefaultPort:"+url.getDefaultPort());
		System.out.println("Host:"+url.getHost());
		System.out.println("Path:"+url.getPath());
		System.out.println("Port:"+url.getPort());
		System.out.println("Query:"+url.getQuery());
		System.out.println("Ref:"+url.getRef());
		System.out.println("UerInfo:"+url.getUserInfo());
		
		// 连接
		URLConnection c = url.openConnection();
		c.connect();
		
		// 显示连接信息
		System.out.println("Content Type:"+c.getContentType());
		System.out.println("Content Encoding:"+c.getContentEncoding());
		System.out.println("Content Length:"+c.getContentLength());
		System.out.println("Date :"+new Date(c.getDate()));
		System.out.println("Last Modified:"+new Date(c.getLastModified()));
		System.out.println("Expired:"+new Date(c.getExpiration()));
		
		// 若为http连接
		if (c instanceof HttpURLConnection) {
			HttpURLConnection hurl = (HttpURLConnection)c;
			System.out.println("Request Method:"+hurl.getRequestMethod());
			System.out.println("Response Message:"+hurl.getResponseMessage());
			System.out.println("Response Code:"+hurl.getResponseCode());
		}
		
	
		
	}

}
