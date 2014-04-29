package designpattern.struct.proxy;

/**
 * The Class RealObj.
 * 真实对象。
 *
 * @date 2014-4-29 14:29:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class RealObj implements CommonInterface{

	@Override
	public void sayName() {
		System.out.println("haha proxy is sb  by real");
	}

	@Override
	public void dance() {
		System.out.println("haha proxy is dance by real ");
	}

}
