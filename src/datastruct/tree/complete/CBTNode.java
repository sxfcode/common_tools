package datastruct.tree.complete;

/**
 * The Class Node.
 *
 * @date 2014-8-21 10:06:22
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CBTNode {
	
	/** value. */
	private Object value;
	
	
	public CBTNode(Object value){
		this.value = value;
	}

	/**
	 * Gets value.
	 *
	 * @return value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets value.
	 *
	 * @param value comments
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
