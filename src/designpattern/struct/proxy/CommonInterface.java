package designpattern.struct.proxy;

/**
 * The Interface CommonInterface.
 *  抽象角色-共同接口,代理类和真实类(被代理) 都要实现。
 *  既可以用接口，也可使用抽象类。
 *  
 * @date 2014-4-29 14:26:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface CommonInterface {
	
	/**
	 * sayName.
	 */
	public void sayName();
	
	/**
	 * dance.
	 */
	public void dance();
}
