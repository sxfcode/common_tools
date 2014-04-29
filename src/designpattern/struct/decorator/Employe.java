package designpattern.struct.decorator;

/**
 * The Class Employe.
 * 被装饰者-需要添加附加功能的对象。去掉跟Project的实现关系，就是一个原始的操作类
 *
 * @date 2014-4-30 1:21:05
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Employe implements Project {

	public void doCoding() {
		System.out.println("代码工人在写代码,苦逼呀");
	}

}
