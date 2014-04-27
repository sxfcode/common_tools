package designpattern.create.factory.simplefc;

import designpattern.create.factory.Product;

/**
 * The Class SimpleFactory.
 * 简单工厂模式，应用于比较简单的场景。
 *  工厂模式，将被创建对象的初始化工作，个性化定制工作，从其构造方法中移除。
 *  同时将一些具有相同或类似功能的操作和对象放到一起。
 * 
 * @date 2014-4-24 11:00:11
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SimpleFactory {

	public static Product createProduct(){
		return new Product();
	}
	public static Product createProduct(int i){
		Product p = new Product();
		if(i==0){
			// todo something
		}
		return p;
	}

}
