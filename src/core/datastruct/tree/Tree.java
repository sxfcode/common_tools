package core.datastruct.tree;


/**
 * The Class Tree.
 * 树的抽象类,提供一些常用方法的抽象实现,
 * 例如：先序遍历，中序遍历，后续遍历
 *
 * @param <N> the number type
 * @date 2014-9-10 15:39:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("unchecked")
public abstract class Tree<N extends Node> {
	
	/**
	 * visit.
	 *
	 * @param n comments
	 */
	public abstract void visit(N n);
	
	/**
	 * 先序遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void preOrderTraverse(N node) {
		visit(node);
		if (node.hasLeftChild()) {
			preOrderTraverse((N)node.getLeftChild());
		}
		if (node.hasRightChild()) {
			preOrderTraverse((N)node.getRightChild());
		}

	}

	/**
	 * 中序遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void inOrderTraverse(N node) {
		if (node.hasLeftChild()) {
			inOrderTraverse((N)node.getLeftChild());
		}
		visit(node);
		if (node.hasRightChild()) {
			inOrderTraverse((N)node.getRightChild());
		}
	}

	/**
	 * 后续遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void postOrderTraverse(N node) {
		if (node.hasLeftChild()) {
			postOrderTraverse((N)node.getLeftChild());
		}
		if (node.hasRightChild()) {
			postOrderTraverse((N)node.getRightChild());
		}
		visit(node);
	}

}
