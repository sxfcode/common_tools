package core.datastruct.tree.common.chain.childrensiblings;

/**
 * The Class ChainNode.
 *
 * @date 2014-8-26 16:28:36
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ChainNode {

	/** data. */
	private Object data;

	/** 最左边孩子结点的指针. */
	private ChainNode leftChild;

	/** 右边第一个兄弟结点的指针(). */
	private ChainNode nextSibling;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ChainNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(ChainNode leftChild) {
		this.leftChild = leftChild;
	}

	public ChainNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(ChainNode nextSibling) {
		this.nextSibling = nextSibling;
	}

}
