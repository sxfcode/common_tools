package core.datastruct.tree.binary.search.balanced;

import core.datastruct.tree.binary.search.BinarySearchNode;
import core.datastruct.tree.binary.search.BinarySearchTree;

/**
 * The Class BalancedBinarySearchTree. 平衡二叉查找树. AVL树
 * BBST,AVL树，是通过归纳得出树，应该存在更简单的平衡方式。
 * 
 * @date 2014-9-16 17:21:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class BalancedBinarySearchTree extends BinarySearchTree {

	/** 调整类型左左. */
	private static final int ROTATE_TYPE_LL = 0;

	/** 调整类型左右. */
	private static final int ROTATE_TYPE_LR = 1;

	/** 调整类型右右. */
	private static final int ROTATE_TYPE_RR = 2;

	/** 调整类型右左. */
	private static final int ROTATE_TYPE_RL = 3;

	/**
	 * 对树进行平衡操作 四种情况 LL 右旋 RR 左旋 LR 左旋，然后右旋 RL 右旋，然后左旋..
	 *
	 * @param node
	 *            被添加的结点
	 */
	public void doBalanced(BinarySearchNode node) {

		int rotateType = -1;
		// 插入点的父节点
		BinarySearchNode parent = node.getParent();
		// 插入点的祖先结点
		BinarySearchNode grandpa = parent.getParent();
		BinarySearchNode parentRightChild = parent.getRightChild();
		BinarySearchNode parentLeftChild = parent.getLeftChild();
		BinarySearchNode grandpaParent = grandpa.getParent();
		BinarySearchNode leftChild = node.getLeftChild();
		BinarySearchNode rightChild = node.getRightChild();

		if (parent.equals(grandpa.getLeftChild())) {
			if (node.equals(parent.getLeftChild())) {
				rotateType = ROTATE_TYPE_LL;
			} else {
				rotateType = ROTATE_TYPE_LR;
			}
		} else {
			if (node.equals(parent.getLeftChild())) {
				rotateType = ROTATE_TYPE_RL;
			} else {
				rotateType = ROTATE_TYPE_RR;
			}
		}
		System.out.println("调整平衡:" + node.getData() + ",平衡类型:" + rotateType+",调整元素:"+node.getData()+","+parent.getData()+","+grandpa.getData());

		switch (rotateType) {
		case ROTATE_TYPE_LL:
			// 祖先元素和双亲元素交换位置
			parent.setParent(grandpaParent);
			if (grandpaParent != null) {
				if (grandpa.equals(grandpaParent.getLeftChild())) {
					grandpaParent.setLeftChild(parent);
				} else {
					grandpaParent.setRightChild(parent);
				}
			}
			parent.setRightChild(grandpa);
			grandpa.setParent(parent);

			// 双亲元素的右子树成为祖先元素的左子树
			grandpa.setLeftChild(parentRightChild);
			if (parentRightChild != null) {
				parentRightChild.setParent(grandpa);
			}
			// 可能影响到根元素
			if (grandpa.equals(getRoot())) {
				setRoot(parent);
			}
			break;
		case ROTATE_TYPE_LR:
			// 左旋+右旋,直接更换元素
			node.setParent(grandpaParent);
			if (grandpaParent != null) {
				if (grandpa.equals(grandpaParent.getLeftChild())) {
					grandpaParent.setLeftChild(node);
				} else {
					grandpaParent.setRightChild(node);
				}
			}
			node.setLeftChild(parent);
			parent.setParent(node);
			node.setRightChild(grandpa);
			grandpa.setParent(node);

			parent.setRightChild(leftChild);
			if (leftChild!=null) {
				leftChild.setParent(parent);
			}
			grandpa.setLeftChild(rightChild);
			if (rightChild!=null) {
				rightChild.setParent(grandpa);
			}
			// 可能影响到根元素
			if (grandpa.equals(getRoot())) {
				setRoot(node);
			}
			break;
		case ROTATE_TYPE_RL:
			// 左旋+右旋,直接更换元素
			node.setParent(grandpaParent);
			if (grandpaParent != null) {
				if (grandpa.equals(grandpaParent.getLeftChild())) {
					grandpaParent.setLeftChild(node);
				} else {
					grandpaParent.setRightChild(node);
				}
			}
			node.setLeftChild(grandpa);
			parent.setParent(node);
			node.setRightChild(parent);
			grandpa.setParent(node);

			grandpa.setRightChild(leftChild);
			if (leftChild!=null) {
				leftChild.setParent(grandpa);
			}
			parent.setLeftChild(rightChild);
			if (rightChild!=null) {
				rightChild.setParent(parent);
			}
			// 可能影响到根元素
			if (grandpa.equals(getRoot())) {
				setRoot(node);
			}

			break;
		case ROTATE_TYPE_RR:
			// 祖先元素和双亲元素交换位置
			parent.setParent(grandpaParent);
			if (grandpaParent != null) {
				if (grandpa.equals(grandpaParent.getLeftChild())) {
					grandpaParent.setLeftChild(parent);
				} else {
					grandpaParent.setRightChild(parent);
				}
			}
			parent.setLeftChild(grandpa);
			grandpa.setParent(parent);

			// 双亲元素的左子树成为祖先元素的右子树
			grandpa.setRightChild(parentLeftChild);
			if (parentLeftChild != null) {
				parentLeftChild.setParent(grandpa);
			}

			// 可能影响到根元素
			if (grandpa.equals(getRoot())) {
				setRoot(parent);
			}
			break;

		default:
			break;
		}

	}

	/**
	 * init.
	 *
	 * @param sourceArray
	 *            comments
	 */
	public void init(int[] sourceArray) {
		for (int i = 0; i < sourceArray.length; i++) {
			BalancedBinarySearchNode b = new BalancedBinarySearchNode();
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
			setRoot(node);
			return;
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
		// 重新构建结点的深度和平衡因子
		reCheckDepth();
		reCheckBalanceFactor();
		// 检查平衡因子，若平衡因子的绝对值大于1，则开始调整
		if (node.getParent() != null && node.getParent().getParent() != null) {
			BalancedBinarySearchNode ancestor = (BalancedBinarySearchNode) node
					.getParent().getParent();
			if (ancestor.getBalanceFactor() < -1
					|| ancestor.getBalanceFactor() > 1) {
				doBalanced(node);
			}
		}
	}

	/**
	 * 在二叉查找树的操作中，结点的删除是相对复杂的~ 设要删除的结点为X，主要分三种情况~
	 * 1.X为叶子结点，也就是X没有左右孩子，这种情况直接删掉X即可~
	 * 2.X只有一个孩子，左孩子或右孩子，这种情况下将X的双亲结点原来指向X的指针指向X的孩子，而后删除X即可~
	 * 3.X有左右两个孩子。这是值得注意，也是最复杂的情况。可以首先对二叉查找树进行中序遍历，这种遍历保持了结点大小的相对顺序。
	 * 找到X的后继元素（前驱也可
	 * ），设为X_SUCC，使得X_SUCC代替X的位置，从而X的左子树即为X_SUCC的左子树，X的右子树即为X_SUCC的右子树；
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
			BinarySearchNode next = (BinarySearchNode) node.getNext();
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
			if (oldParent != null) {
				if (node.equals(oldParent.getLeftChild())) {
					oldParent.setLeftChild(next);
				} else if (node.equals(oldParent.getRightChild())) {
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
			if (parent.hasLeftChild() && parent.getLeftChild().equals(node)) {
				currentIsLeftChild = true;
			}

			if (node.hasLeftChild()) {
				if (currentIsLeftChild) {
					parent.setLeftChild(node.getLeftChild());
				} else {
					parent.setRightChild(node.getLeftChild());
				}
			} else {
				if (currentIsLeftChild) {
					parent.setLeftChild(node.getRightChild());
				} else {
					parent.setRightChild(node.getRightChild());
				}
			}
		}
	}

	/**
	 * 重新构建所有结点的深度.
	 */
	public void reCheckDepth() {
		getDepth((BalancedBinarySearchNode) getRoot());
	}

	/**
	 * reCheckBalanceFactor.
	 */
	public void reCheckBalanceFactor() {
		getALLBalanceFactor((BalancedBinarySearchNode) getRoot());
	}

	/**
	 * 为某结点及其子结点生成平衡因子
	 *
	 * @param bbsn
	 *            comments
	 * @return ALLBalanceFactor
	 */
	public void getALLBalanceFactor(BalancedBinarySearchNode bbsn) {
		bbsn.setBalanceFactor(getBalanceFactor(bbsn));
		if (bbsn.hasLeftChild()) {
			getALLBalanceFactor((BalancedBinarySearchNode) bbsn.getLeftChild());
		}
		if (bbsn.hasRightChild()) {
			getALLBalanceFactor((BalancedBinarySearchNode) bbsn.getRightChild());
		}
	}

	/**
	 * 计算平衡因子，左子树的深度减去右子树的深度.
	 *
	 * @param bbsn
	 *            comments
	 * @return balanceFactor
	 */
	public int getBalanceFactor(BalancedBinarySearchNode bbsn) {
		int leftChildDepth = 0;
		int rightChildDepth = 0;
		if (bbsn.hasLeftChild()) {
			leftChildDepth = ((BalancedBinarySearchNode) bbsn.getLeftChild())
					.getDepth();
		}
		if (bbsn.hasRightChild()) {
			rightChildDepth = ((BalancedBinarySearchNode) bbsn.getRightChild())
					.getDepth();
		}
		return leftChildDepth - rightChildDepth;
	}

	/**
	 * 获取结点的深度.
	 *
	 * @param bbsn
	 *            comments
	 * @return depth
	 */
	public int getDepth(BalancedBinarySearchNode bbsn) {
		if (bbsn == null) {
			return 0;
		}

		// 默认深度为1
		int result = 1;
		int leftChildDepth = 0;
		int rightChildDepth = 0;
		if (bbsn.hasLeftChild()) {
			leftChildDepth = getDepth((BalancedBinarySearchNode) bbsn
					.getLeftChild());
		}
		if (bbsn.hasRightChild()) {
			rightChildDepth = getDepth((BalancedBinarySearchNode) bbsn
					.getRightChild());
		}
		// 选择深度大的子树的深度，来计算当前结点的深度
		if (leftChildDepth > 0 || rightChildDepth > 0) {
			if (leftChildDepth > rightChildDepth) {
				result = result + leftChildDepth;
			} else {
				result = result + rightChildDepth;
			}
		}
		bbsn.setDepth(result);
		return result;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		//int[] sourceArray = new int[] { 8, 5, 3, 2, 1, 10, 12};
		int[] sourceArray = new int[] { 8, 5, 3, 2, 1, 10, 12,6,7 };
		BalancedBinarySearchTree bbst = new BalancedBinarySearchTree();
		bbst.init(sourceArray);
		System.out.println("根节点为" + bbst.getRoot().getData());
		bbst.inOrderTraverse(bbst.getRoot());
		System.out.println();
		bbst.preOrderTraverse(bbst.getRoot());
	}

}
