package datastruct.tree.binary.chain;

import java.util.Arrays;

/**
 * The Class LinkTree.
 * 链式存储方式,三叉链表，在记录孩子结点的同时，记录双亲结点的地址
 * 使用孩子表示法的二叉树
 *
 * @date 2014-8-25 15:21:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ChainBinaryTree {

	/** root. */
	private ChainBinaryNode root;

	// =======================插入操作============================

	/**
	 * 创建一棵二叉树
	 *
	 * @param preArr 二叉树元素的先序遍历
	 * @param inArr 二叉树元素的中序遍历
	 */
	public void init(String[] preArr, String[] inArr){
		this.root = initTree(preArr, inArr);
	}

	/**
	 *  递归构建二叉树.
	 *
	 * @param preArr 二叉树元素的先序遍历
	 * @param inArr 二叉树元素的中序遍历
	 * @return LinkNode
	 */
	private ChainBinaryNode initTree(String[] preArr, String[] inArr) {
		String root = preArr[0];
		ChainBinaryNode myRoot = new ChainBinaryNode(root);
		// 根元素在中根遍历中的索引位置，通过该位置确定其左子树和右子树的元素范围
		int rootIndexIn = -1;
		for (int i = 0; i < inArr.length; i++) {
			if(root.equals(inArr[i])){
				rootIndexIn = i;
			}
		}
		// 构建左子树,条件:根的左边存在元素
		if(rootIndexIn>0){
			String[] preArrLocalLeft = Arrays.copyOfRange(preArr, 1,rootIndexIn+1);
			String[] inArrLocalLeft = Arrays.copyOfRange(inArr, 0,rootIndexIn);
			ChainBinaryNode leftChild =initTree(preArrLocalLeft,inArrLocalLeft);
			insertLeftChild(myRoot, leftChild);
		}
		// 构建右子树,条件:根的右边存在元素
		if(rootIndexIn>=0&&((rootIndexIn+1)<=(inArr.length-1))){
			String[] preArrLocalRight = Arrays.copyOfRange(preArr, rootIndexIn+1,preArr.length);
			String[] inArrLocalRight = Arrays.copyOfRange(inArr, rootIndexIn+1,preArr.length);
			ChainBinaryNode rightChild =initTree(preArrLocalRight,inArrLocalRight);
			insertRightChild(myRoot, rightChild);
		}
		// 返回叶子结点或根结点
		return myRoot;
	}
	/**
	 * 先序遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void preOrderTraverse(ChainBinaryNode node) {
		visit(node);
		if (node.hasLeftChild()) {
			preOrderTraverse(node.getLeftChild());
		}
		if (node.hasRightChild()) {
			preOrderTraverse(node.getRightChild());
		}

	}

	/**
	 * 中序遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void inOrderTraverse(ChainBinaryNode node) {
		if (node.hasLeftChild()) {
			inOrderTraverse(node.getLeftChild());
		}
		visit(node);
		if (node.hasRightChild()) {
			inOrderTraverse(node.getRightChild());
		}
	}

	/**
	 * 后续遍历.
	 *
	 * @param node 二叉树的根
	 */
	public void postOrderTraverse(ChainBinaryNode node) {
		if (node.hasLeftChild()) {
			postOrderTraverse(node.getLeftChild());
		}
		if (node.hasRightChild()) {
			postOrderTraverse(node.getRightChild());
		}
		visit(node);
	}
	public void visit(ChainBinaryNode node){
		System.out.println(node.getData());
	}

	/**
	 * insertLeftChild.
	 *
	 * @param parentNode comments
	 * @param targetNode comments
	 */
	public void insertLeftChild(ChainBinaryNode parentNode, ChainBinaryNode targetNode) {
		targetNode.setParent(parentNode);
		parentNode.setLeftChild(targetNode);
	}

	/**
	 * insertRightChild.
	 *
	 * @param parentNode comments
	 * @param targetNode comments
	 */
	public void insertRightChild(ChainBinaryNode parentNode, ChainBinaryNode targetNode) {
		targetNode.setParent(parentNode);
		parentNode.setRightChild(targetNode);
	}

	// =======================删除操作============================

	/**
	 * deleteLeftChild.
	 *
	 * @param node comments
	 */
	public void deleteLeftChild(ChainBinaryNode node) {
		node.setLeftChild(null);
	}

	/**
	 * deleteRightChild.
	 *
	 * @param node comments
	 */
	public void deleteRightChild(ChainBinaryNode node) {
		node.setRightChild(null);
	}

	/**
	 * 销毁树.
	 */
	public void destroyTree() {
		root = null;
	}

	// =======================查询操作============================

	/**
	 * 查找某个结点，该结点的数据为nodeValue.
	 *
	 * @param nodeValue
	 *            comments
	 * @return LinkNode
	 */
	public ChainBinaryNode search(Object nodeValue) {
		// todo
		return null;
	}

	/**
	 * 获取根结点.
	 *
	 * @return root
	 */
	public ChainBinaryNode getRoot() {
		return root;
	}

	/**
	 * 求树的深度.
	 *
	 * @return depth
	 */
	public int getDepth() {
		// todo
		return -1;
	}

	/**
	 * 判断是否是空树.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// 构造一棵二叉树,结构如下
		//              1
		//           /     \
		//          2       3
		//        /  \     /  \
		//      4     5  6    7
		// 先序遍历序列
		String[] preArr = new String[]{"1","2","4","5","3","6","7"};
		// 中序遍历序列
		String[] inArr = new String[]{"4","2","5","1","6","3","7"};
		
		ChainBinaryTree lt = new ChainBinaryTree();
		lt.init(preArr,inArr);
		System.out.println("先根遍历");
		lt.preOrderTraverse(lt.getRoot());
//		System.out.println("中根遍历");
//		lt.inOrderTraverse(lt.getRoot());
//		System.out.println("后根遍历");
//		lt.postOrderTraverse(lt.getRoot());

	}


}
