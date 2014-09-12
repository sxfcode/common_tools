package core.datastruct.tree.binary.search;

import core.datastruct.tree.binary.BinaryNode;

public class BinarySearchNode extends BinaryNode  {
	
	/** 数据. */
	private Object data;

	/** 左孩子. */
	private BinarySearchNode leftChild;

	/** 右孩子. */
	private BinarySearchNode rightChild;

	/** 双亲. */
	private BinarySearchNode parent;
	
	/** 权值. */
	private Integer weight;
	
	/** 编号. */
	private Integer number;
	
	/** 结点编码-用于生成霍夫曼编码. */
	private String code;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BinarySearchNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinarySearchNode leftChild) {
		this.leftChild = leftChild;
	}

	public BinarySearchNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinarySearchNode rightChild) {
		this.rightChild = rightChild;
	}

	public BinarySearchNode getParent() {
		return parent;
	}

	public void setParent(BinarySearchNode parent) {
		this.parent = parent;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getNumber() {
		return number;
	}

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
	public void setLeftChild(BinaryNode leftChild) {
		if(leftChild!=null){
			this.leftChild = (BinarySearchNode)leftChild;
		}
	}

	@Override
	public void setRightChild(BinaryNode rightChild) {
		if (rightChild!=null) {
			this.rightChild = (BinarySearchNode) rightChild;	
		}
	}

	@Override
	public void setParent(BinaryNode parent) {
		this.parent = (BinarySearchNode) parent;
	}
	
	public Integer getIntegerValue(){
		return (Integer)this.data;
	}

	public int compareTo(BinarySearchNode o) {
		return this.getIntegerValue()-o.getIntegerValue();
	}
	
}
