package designpattern.action.iterator;

/**
 * The Interface CustomContainer.
 * 抽象容器接口-定义容器接口
 * @param <E> the element type
 * @date 2014-5-3 16:26:40
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface CustomContainer <E>{
	
	/**
	 * iterator.
	 *
	 * @return CustomIterator
	 */
	public CustomIterator <E> iterator();
	
	public void add(E e);
	
	public E get(int index);
}
