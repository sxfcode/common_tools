package datastruct.tree.binary.chain;

/**
 * The Class LinkNode.
 *	链式存储方式,三叉链表，在记录孩子结点的同时，记录双亲结点的地址.
 * 
 *
 * @date 2014-8-25 14:52:38
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ChainBinaryNode {

	/** data. */
	private Object data;

	/** leftChild. */
	private ChainBinaryNode leftChild;

	/** rightChild. */
	private ChainBinaryNode rightChild;

	/** parentChild. */
	private ChainBinaryNode parent;

	/**
	 * Instantiates a new LinkNode.
	 *
	 * @param data
	 *            comments
	 */
	public ChainBinaryNode(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ChainBinaryNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(ChainBinaryNode leftChild) {
		this.leftChild = leftChild;
	}

	public ChainBinaryNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(ChainBinaryNode rightChild) {
		this.rightChild = rightChild;
	}

	public ChainBinaryNode getParent() {
		return parent;
	}

	public void setParent(ChainBinaryNode parent) {
		this.parent = parent;
	}

	/**
	 * hasLeftChild.
	 *
	 * @return true, if successful
	 */
	public boolean hasLeftChild() {
		if (getLeftChild() == null) {
			return false;
		}
		return true;
	}

	/**
	 * hasRightChild.
	 *
	 * @return true, if successful
	 */
	public boolean hasRightChild() {
		if (getRightChild() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is leaf.
	 *
	 * @return true, if is leaf
	 */
	public boolean isLeaf() {
		if (!hasLeftChild() && !hasRightChild()) {
			return true;
		}
		return false;
	}

}
