package designpattern.struct.proxy;

/**
 * The Class ProxyExample.
 *  代理模式
 * 
 * 在需要用比较通用和复杂的对象指针代替简单的指针的时候，使用Proxy模式。
 * 下面是一些可以使用Proxy模式常见情况：
 * 
 * u 远程代理（Remote Proxy ）为一个对象在不同的地址空间提供局部代表。
 * 
 * u 虚代理（Virtual Proxy ）根据需要创建开销很大的对象。
 * 
 * u 保护代理（Protection Proxy ）控制对原始对象的访问。保护代理用于对象应该有不同的访问权限的时候。
 * 
 * u )智能指引（Smart Reference ）取代了简单的指针，它在访问对象时执行一些附加操作。
 * 
 * 它的典型用途包括：
 * 
 * u 对指向实际对象的引用计数，这样当该对象没有引用时，可以自动释放它。
 * 
 * u 当第一次引用一个持久对象时，将它装入内存。
 * 
 * u 在访问一个实际对象前，检查是否已经锁定了它，以确保其他对象不能改变它。
 * 
 * 
 * @date 2014-4-29 13:48:31
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		ProxyObj proxy = new ProxyObj(new RealObj());
		proxy.dance();
		proxy.sayName();
	}

}
