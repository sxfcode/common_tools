package core.datastruct.tree.other.digitalsearchtree;

/**
 * The Class DigitalSearchNode.
 *
 * @date 2014-9-21 17:05:39
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DigitalSearchNode {
	
	/** 关键字. */
	private Object data;
	
	/** 第一个孩子结点. */
	private DigitalSearchNode firstChild;
	
	/** 下一个兄弟结点. */
	private DigitalSearchNode nextSibling;
	
	
	
	
	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Sets data.
	 *
	 * @param data comments
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * Gets firstChild.
	 *
	 * @return firstChild
	 */
	public DigitalSearchNode getFirstChild() {
		return firstChild;
	}
	
	/**
	 * Sets firstChild.
	 *
	 * @param firstChild comments
	 */
	public void setFirstChild(DigitalSearchNode firstChild) {
		this.firstChild = firstChild;
	}
	
	/**
	 * Gets nextSibling.
	 *
	 * @return nextSibling
	 */
	public DigitalSearchNode getNextSibling() {
		return nextSibling;
	}
	
	/**
	 * Sets nextSibling.
	 *
	 * @param nextSibling comments
	 */
	public void setNextSibling(DigitalSearchNode nextSibling) {
		this.nextSibling = nextSibling;
	}
	

}
