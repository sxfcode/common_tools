package core.datastruct.tree.other.search;

import core.datastruct.tree.binary.BinaryNode;

/**
 * The Class BinarySearchNode.
 *
 * @date 2014-9-15 17:07:29
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class BinarySearchNode extends BinaryNode  {
	
	/** 数据. */
	private Object data;

	/** 左孩子. */
	private BinarySearchNode leftChild;

	/** 右孩子. */
	private BinarySearchNode rightChild;

	/** 双亲. */
	private BinarySearchNode parent;
	
	/** 前驱. */
	private BinarySearchNode pre;
	
	/** 后继. */
	private BinarySearchNode next;
	
	
	/** 权值. */
	private Integer weight;
	
	/** 编号. */
	private Integer number;
	
	/** 结点编码-用于生成霍夫曼编码. */
	private String code;

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getData()
	 */
	public Object getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setData(java.lang.Object)
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getLeftChild()
	 */
	public BinarySearchNode getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets leftChild.
	 *
	 * @param leftChild comments
	 */
	public void setLeftChild(BinarySearchNode leftChild) {
		this.leftChild = leftChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getRightChild()
	 */
	public BinarySearchNode getRightChild() {
		return rightChild;
	}

	/**
	 * Sets rightChild.
	 *
	 * @param rightChild comments
	 */
	public void setRightChild(BinarySearchNode rightChild) {
		this.rightChild = rightChild;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getParent()
	 */
	public BinarySearchNode getParent() {
		return parent;
	}

	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
	public void setParent(BinarySearchNode parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getWeight()
	 */
	public Integer getWeight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setWeight(java.lang.Integer)
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getNumber()
	 */
	public Integer getNumber() {
		return number;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setNumber(java.lang.Integer)
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#getCode()
	 */
	public String getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setCode(java.lang.String)
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setLeftChild(core.datastruct.tree.binary.BinaryNode)
	 */
	@Override
	public void setLeftChild(BinaryNode leftChild) {
		if(leftChild!=null){
			this.leftChild = (BinarySearchNode)leftChild;
		}
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setRightChild(core.datastruct.tree.binary.BinaryNode)
	 */
	@Override
	public void setRightChild(BinaryNode rightChild) {
		if (rightChild!=null) {
			this.rightChild = (BinarySearchNode) rightChild;	
		}
	}

	/* (non-Javadoc)
	 * @see core.datastruct.tree.binary.BinaryNode#setParent(core.datastruct.tree.binary.BinaryNode)
	 */
	@Override
	public void setParent(BinaryNode parent) {
		this.parent = (BinarySearchNode) parent;
	}
	
	/**
	 * Gets integerValue.
	 *
	 * @return integerValue
	 */
	public Integer getIntegerValue(){
		return (Integer)this.data;
	}

	/**
	 * compareTo.
	 *
	 * @param o comments
	 * @return int
	 */
	public int compareTo(BinarySearchNode o) {
		return this.getIntegerValue()-o.getIntegerValue();
	}

	@Override
	public BinaryNode getPre() {
		return pre;
	}

	@Override
	public void setPre(BinaryNode pre) {
		this.pre = (BinarySearchNode)pre;
	}

	@Override
	public BinaryNode getNext() {
		return next;
	}

	@Override
	public void setNext(BinaryNode next) {
		this.next = (BinarySearchNode)next;
	}
	
}
