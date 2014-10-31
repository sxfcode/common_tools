package core.datastruct.tree.other.redblacktree;

import core.datastruct.tree.other.search.BinarySearchNode;

/**
 * The Class RedBlackNode.
 *
 * @date 2014-10-29 17:31:41
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class RedBlackNode {
	
	public final static int BLACK=0;
	public final static int RED=1;
	
	/** leftChild. */
	private RedBlackNode leftChild;
	
	/** rightChild. */
	private RedBlackNode rightChild;
	
	/** parent. */
	private RedBlackNode parent;
	
	/** 颜色，0表示黑色，1表示红色,新建结点默认为黑色 */
	private int color = BLACK;

	public RedBlackNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RedBlackNode leftChild) {
		this.leftChild = leftChild;
	}

	public RedBlackNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(RedBlackNode rightChild) {
		this.rightChild = rightChild;
	}

	public RedBlackNode getParent() {
		return parent;
	}

	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public boolean isBlack(){
		if (color==BLACK) {
			return true;
		}
		return false;
	}
	public boolean isRed(){
		if (color==RED) {
			return true;
		}
		return false;
	}
	

}
