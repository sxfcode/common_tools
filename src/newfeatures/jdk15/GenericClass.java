package newfeatures.jdk15;

/**
 * The Class GenericClass.
 * 泛型包括接口泛型，类泛型，方法泛型。
 * 需要注意泛型边界
 * 泛型通配符，对于通配符类型的泛型，只可读取元素不可添加元素。
 *
 * @date 2014-7-17 14:45:55
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class GenericClass {
	public <T> void test(T o){
		System.out.println(o.toString());
	}
}
