package designpattern.action.iterator;

/**
 * The Interface CustomIterator.
 * 迭代器接口-自定义迭代器接口，其实可以直接使用JDK的迭代器接口，这里为了演示需要，自定义了一个接口
 *
 * @param <E> the element type
 * @date 2014-5-3 15:54:44
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public  interface CustomIterator <E>{
	
	/**
	 * hasNext.
	 *
	 * @return true, if successful
	 */
	public boolean hasNext();
	
	/**
	 * next.
	 *
	 * @return E
	 */
	public E next();
	
	/**
	 * remove.
	 */
	public void remove();
	

}
