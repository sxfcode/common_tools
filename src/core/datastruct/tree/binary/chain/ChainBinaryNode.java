package core.datastruct.tree.binary.chain;

import core.datastruct.tree.binary.BinaryNode;

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
public class ChainBinaryNode extends BinaryNode{

	/** 数据. */
	private Object data;

	/** 左孩子. */
	private ChainBinaryNode leftChild;

	/** 右孩子. */
	private ChainBinaryNode rightChild;

	/** 双亲. */
	private ChainBinaryNode parent;
	
	/** 前驱. */
	private ChainBinaryNode pre;
	
	/** 后继. */
	private ChainBinaryNode next;
	
	/** 权值. */
	private Integer weight;
	
	/** 编号. */
	private Integer number;
	
	/** 结点编码-用于生成霍夫曼编码. */
	private String code;

	/**
	 * Instantiates a new LinkNode.
	 *
	 * @param data
	 *            comments
	 */
	public ChainBinaryNode(Object data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getData()
	 */
	public Object getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setData(java.lang.Object)
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getLeftChild()
	 */
	public ChainBinaryNode getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets leftChild.
	 *
	 * @param leftChild comments
	 */
	public void setLeftChild(ChainBinaryNode leftChild) {
		this.leftChild = leftChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getRightChild()
	 */
	public ChainBinaryNode getRightChild() {
		return rightChild;
	}

	/**
	 * Sets rightChild.
	 *
	 * @param rightChild comments
	 */
	public void setRightChild(ChainBinaryNode rightChild) {
		this.rightChild = rightChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getParent()
	 */
	public ChainBinaryNode getParent() {
		return parent;
	}

	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
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

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setLeftChild(core.datastruct.tree.Node)
	 */
	@Override
	public void setLeftChild(BinaryNode leftChild) {
		this.leftChild = (ChainBinaryNode)leftChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setRightChild(core.datastruct.tree.Node)
	 */
	@Override
	public void setRightChild(BinaryNode rightChild) {
		this.rightChild = (ChainBinaryNode)rightChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setParent(core.datastruct.tree.Node)
	 */
	@Override
	public void setParent(BinaryNode parent) {
		this.parent = (ChainBinaryNode)parent;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getWeight()
	 */
	public Integer getWeight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setWeight(java.lang.Integer)
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#getNumber()
	 */
	public Integer getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.Node#setNumber(java.lang.Integer)
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ChainBinaryNode [data=" + data + ", weight=" + weight
				+ ", code=" + code + "]";
	}

	@Override
	public BinaryNode getPre() {
		return pre;
	}

	@Override
	public void setPre(BinaryNode pre) {
		this.pre = (ChainBinaryNode)pre;
	}

	@Override
	public BinaryNode getNext() {
		return next;
	}

	@Override
	public void setNext(BinaryNode next) {
		this.next = (ChainBinaryNode)next;
	}
	
}
