package core.datastruct.tree.other.digitalsearchtree.sibling;

import core.datastruct.tree.other.digitalsearchtree.DigitalSearchNode;

/**
 * The Class DigitalSearchSiblingNode.
 *
 * @date 2014-9-28 10:54:29
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DigitalSearchSiblingNode extends DigitalSearchNode {
	
	/** 关键字. */
	private Object data;

	/** 第一个孩子结点. */
	private DigitalSearchNode firstChild;

	/** 下一个兄弟结点. */
	private DigitalSearchNode nextSibling;

	/** parent. */
	private DigitalSearchNode parent;

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

	/**
	 * Gets parent.
	 *
	 * @return parent
	 */
	public DigitalSearchNode getParent() {
		return parent;
	}

	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
	public void setParent(DigitalSearchNode parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.other.digitalsearchtree.DigitalSearchNode#getData()
	 */
	public Object getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.other.digitalsearchtree.DigitalSearchNode#setData(java.lang.Object)
	 */
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public boolean isWordEnd() {
		return false;
	}

	@Override
	public void setWordEnd(boolean isWordEnd) {
	}

	@Override
	public boolean isVisited() {
		return false;
	}

	@Override
	public void setVisited(boolean visited) {
	}

}
