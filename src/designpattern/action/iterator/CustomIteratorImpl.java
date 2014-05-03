package designpattern.action.iterator;

/**
 * The Class CustomIteratorImpl.
 *  具体迭代器-自定义具体迭代器。
 *
 * @param <E> the element type
 * @date 2014-5-3 16:01:29
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class CustomIteratorImpl <E> implements CustomIterator<E> {
	
	/** earray. */
	private E[] earray;
	
	/** cursor. */
	private int cursor =-1;
	
	private int length = -1;
	
	/**
	 * Instantiates a new CustomIteratorImpl.
	 *
	 * @param earray comments
	 */
	public CustomIteratorImpl(E[] earray,int length){
		this.earray = earray;
		this.length = length;
	}

	@Override
	public boolean hasNext() {
		if(cursor>=length){
			return false;
		}
		return true;
	}

	@Override
	public E next() {
		cursor++;
		return earray[cursor];
	}

	/**
	 * 这里没有实现该方法，只是做演示用。
	 */
	@Override
	public void remove() {
		System.out.println("删除元素");
	}

}
