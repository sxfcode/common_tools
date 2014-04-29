package designpattern.struct.proxy;

/**
 * The Class ProxyObj.
 * 代理对象，用于通过代理对象实现对真实对象的访问。
 *
 * @date 2014-4-29 14:37:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ProxyObj implements CommonInterface {
	
	/** ci. */
	private CommonInterface ci;
	
	/**
	 * Instantiates a new ProxyObj.
	 *
	 * @param ci comments
	 */
	public ProxyObj(CommonInterface ci){
		this.ci = ci;
	}

	/**
	 * sayName.
	 */
	@Override
	public void sayName() {
		ci.sayName();
	}

	/**
	 * dance.
	 */
	@Override
	public void dance() {
		ci.dance();
	}
}
