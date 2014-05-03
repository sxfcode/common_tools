package designpattern.action.iterator;

/**
 * The Class CustomContainerImpl.
 * 具体容器-自定义容器
 *
 * @date 2014-5-3 16:26:22
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
@SuppressWarnings("unchecked")
public class CustomContainerImpl <E> implements CustomContainer<E> {
	
	private Object[] eArray = new Object[10];
	private int length = -1;
	private int cursor = -1;

	@SuppressWarnings("rawtypes")
	@Override
	public CustomIterator<E> iterator() {
		CustomIterator<E> it =new CustomIteratorImpl(eArray,length);
		return it;
		
	}

	// 这里仅供研究，实际运行中需要考虑很多其他方面，比如并发等
	@Override
	public void add(E e) {
		if(length==eArray.length){
			Object[] newArray = new Object[eArray.length+10];
			System.arraycopy(eArray, 0, newArray, 0, length);
			eArray = newArray;
		}
		length++;
		cursor++;
		eArray[cursor]= e;
	}

	@Override
	public E get(int index) {
		return (E)eArray[index];
	}
	

}
