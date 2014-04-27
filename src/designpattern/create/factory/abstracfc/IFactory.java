package designpattern.create.factory.abstracfc;

/**
 * The Interface IFactory.
 *
 * @date 2014-4-24 11:18:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public interface IFactory {
	
	/**
	 * Creates a new I object.
	 *
	 * @return the i product
	 */
	public IProduct createProduct();

}
