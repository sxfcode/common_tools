package core.datastruct.tree.binary.search;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import core.datastruct.tree.binary.BinaryTree;

/**
 * The Class BinarySearchTree.
 * 二叉搜索树
 *
 * @date 2014-9-15 17:43:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class BinarySearchTree extends BinaryTree<BinarySearchNode> {

	/** root. */
	private BinarySearchNode root;

	/**
	 * init.
	 *
	 * @param sourceArray
	 *            comments
	 */
	public void init(int[] sourceArray) {
		for (int i = 0; i < sourceArray.length; i++) {
			BinarySearchNode b = new BinarySearchNode();
			b.setData(sourceArray[i]);
			addNode(b);
		}
	}

	/**
	 * addNode.
	 *
	 * @param node
	 *            comments
	 */
	public void addNode(BinarySearchNode node) {
		if (getRoot() == null) {
			this.root = node;
		}
		BinarySearchNode current = getRoot();
		while (true) {
			// no change
			if (node.compareTo(current) == 0) {
				break;
				// node小于当前结点
			} else if (node.compareTo(current) < 0) {
				if (current.hasLeftChild()) {
					current = current.getLeftChild();
					continue;
				} else {
					insertLeftChild(current, node);
					break;
				}
				// node大于当前结点
			} else {
				if (current.hasRightChild()) {
					current = current.getRightChild();
					continue;
				} else {
					insertRightChild(current, node);
					break;
				}
			}
		}
	}

	/**
	 * 在二叉查找树的操作中，结点的删除是相对复杂的~ 设要删除的结点为X，主要分三种情况~
	 * 1.X为叶子结点，也就是X没有左右孩子，这种情况直接删掉X即可~
	 * 2.X只有一个孩子，左孩子或右孩子，这种情况下将X的双亲结点原来指向X的指针指向X的孩子，而后删除X即可~
	 * 3.X有左右两个孩子。这是值得注意，也是最复杂的情况。可以首先对二叉查找树进行中序遍历，这种遍历保持了结点大小的相对顺序。
	 * 找到X的后继元素（前驱也可），设为X_SUCC，使得X_SUCC代替X的位置，从而X的左子树即为X_SUCC的左子树，X的右子树即为X_SUCC的右子树；
	 * 之后将原来X_SUCC结点的双亲结点指向X_SUCC的指针指向X_SUCC的右孩子
	 * （如果是找前驱替代，即指向左孩子）；最后把X删掉就OK了~~~~.
	 *
	 * @param node
	 *            comments
	 */
	public void deleteNode(BinarySearchNode node) {
		if (node.isLeaf()) {
			BinarySearchNode parent = node.getParent();
			if (node.equals(parent.getLeftChild())) {
				parent.setLeftChild(null);
			} else if (node.equals(parent.getRightChild())) {
				parent.setRightChild(null);
			}
			node.setParent(null);
		} else if (node.hasLeftChild() && node.hasRightChild()) {
			// 注意:需要考虑node为根节点，next为node右结点的情况
			
			// step 1:中序线索化
			this.doInThread();
			BinarySearchNode next = (BinarySearchNode)node.getNext();
			// step 2: 保存中间变量 被删除结点的双亲和左右孩子
			BinarySearchNode oldParent = node.getParent();
			BinarySearchNode oldLeftChild = node.getLeftChild();
			BinarySearchNode oldRightChild = node.getRightChild();
			
			// 被删除结点的后继结点的双亲和右孩子(无左孩子)
			BinarySearchNode nextRightChild = next.getRightChild();
			BinarySearchNode nextParent = next.getParent();
			
			// step 3:后继结点代替原结点
			next.setLeftChild(oldLeftChild);
			oldLeftChild.setParent(next);
			// 有可能node的右孩子=next
			if (!next.equals(oldRightChild)) {
				next.setRightChild(oldRightChild);
				oldRightChild.setParent(next);
			}
			
			// 原结点存在父结点
			next.setParent(oldParent);
			if (oldParent!=null) {
				if(node.equals(oldParent.getLeftChild())){
					oldParent.setLeftChild(next);
				}else if(node.equals(oldParent.getRightChild())){
					oldParent.setRightChild(next);
				}
			}
			// step 4: 原来X_SUCC结点的双亲结点指向X_SUCC的指针指向X_SUCC的右孩子
			if (!node.equals(nextParent)) {
				nextParent.setLeftChild(nextRightChild);
			}
			
			// step 5:判断被删除的结点是否是根节点
			if (node.equals(getRoot())) {
				setRoot(next);
			}
			
		} else {
			// 只有左孩子 或者只有 右孩子
			BinarySearchNode parent = node.getParent();
			boolean currentIsLeftChild = false;
			if (parent.hasLeftChild()&&parent.getLeftChild().equals(node)) {
				currentIsLeftChild = true;
			}
			
			if (node.hasLeftChild()) {
				if (currentIsLeftChild) {
					parent.setLeftChild(node.getLeftChild());
				}else{
					parent.setRightChild(node.getLeftChild());
				}
			}else{
				if (currentIsLeftChild) {
					parent.setLeftChild(node.getRightChild());
				}else{
					parent.setRightChild(node.getRightChild());
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datastruct.tree.binary.BinaryTree#getRoot()
	 */
	public BinarySearchNode getRoot() {
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * core.datastruct.tree.binary.BinaryTree#setRoot(core.datastruct.tree.binary
	 * .BinaryNode)
	 */
	public void setRoot(BinarySearchNode root) {
		this.root = root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datastruct.tree.binary.BinaryTree#getDepth()
	 */
	public int getDepth() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datastruct.tree.binary.BinaryTree#buildHuffmanNodes(int[])
	 */
	@Override
	public List<BinarySearchNode> buildHuffmanNodes(int[] sourceArray) {
		List<BinarySearchNode> nodes = new ArrayList<BinarySearchNode>();
		for (int i : sourceArray) {
			BinarySearchNode node = new BinarySearchNode();
			node.setWeight(i);
			node.setData(i);
			nodes.add(node);
		}
		return nodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see core.datastruct.tree.binary.BinaryTree#buildHuffmanNode(int)
	 */
	@Override
	public BinarySearchNode buildHuffmanNode(int sourceWeight) {
		BinarySearchNode node = new BinarySearchNode();
		node.setWeight(sourceWeight);
		node.setData(sourceWeight);
		return node;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * core.datastruct.tree.binary.BinaryTree#visit(core.datastruct.tree.binary
	 * .BinaryNode)
	 */
	@Override
	public void visit(BinarySearchNode n) {
		System.out.print(n.getData() + ",");
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		//int[] sourceArray = new int[] { 3, 1, 2, 4, 5 };
		int[] sourceArray = new int[] { 5,2,7,1,3,6,8 };
		BinarySearchTree bst = new BinarySearchTree();
		bst.init(sourceArray);
		bst.doInThread();
//		bst.inOrderTraverse(bst.getRoot());
		BinarySearchNode beDelete =(BinarySearchNode)bst.search(5, bst.getRoot());
		bst.deleteNode(beDelete);
		bst.inOrderTraverse(bst.getRoot());
		System.out.println();
		System.out.println(bst.getRoot().getData());
	}

}
