package core.datastruct.tree.binary;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Class Tree. 
 * 二叉树的抽象类,提供一些常用方法的抽象实现, 例如：先序遍历，中序遍历，后续遍历
 *
 * @param <N>
 *            the number type
 * @date 2014-9-10 15:39:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("unchecked")
public abstract class BinaryTree<N extends BinaryNode> {

	/**
	 * 获取根结点.
	 *
	 * @return root
	 */
	public abstract N getRoot();

	/**
	 * Sets root.
	 *
	 * @param root comments
	 */
	public abstract void setRoot(N root);

	/**
	 * 求树的深度.
	 *
	 * @return depth
	 */
	public abstract int getDepth();

	/**
	 * 判断是否是空树.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (getRoot() == null) {
			return true;
		}
		return false;
	}

	/**
	 * 使用给点的权值创建霍夫曼节点.
	 *
	 * @param sourceArray
	 *            霍夫曼节点的对应的权值
	 * @return List
	 */
	public abstract List<N> buildHuffmanNodes(int[] sourceArray);

	/**
	 * 使用给定的权值创建霍夫曼节点.
	 *
	 * @param sourceWeight comments
	 * @return List
	 */
	public abstract N buildHuffmanNode(int sourceWeight);

	/**
	 * visit.
	 *
	 * @param n
	 *            comments
	 */
	public abstract void visit(N n);

	/** 比较器,通过权值比较. */
	private Comparator<N> comparator = new Comparator<N>() {
		@Override
		public int compare(N o1, N o2) {
			return o1.getWeight() - o2.getWeight();
		}
	};

	/**
	 * 使用给定的权值创建霍夫曼树.
	 *
	 * @param sourceArray
	 *            comments
	 */
	public void buildHuffmanTree(int[] sourceArray) {
		List<N> nodes = buildHuffmanNodes(sourceArray);
		Collections.sort(nodes, comparator);
		// 使用权值最小的两个节点构建新结点
		while (nodes.size() > 1) {
			N n0 = nodes.get(0);
			N n1 = nodes.get(1);
			N newRoot = buildHuffmanNode(n0.getWeight() + n1.getWeight());

			n0.setParent(newRoot);
			n1.setParent(newRoot);
			n0.setCode("0");
			n1.setCode("1");
			newRoot.setLeftChild(n0);
			newRoot.setRightChild(n1);

			// 移除原结点，添加新结点
			nodes.remove(n0);
			nodes.remove(n1);
			nodes.add(newRoot);

			// 排序
			Collections.sort(nodes, comparator);
		}
		// 设置根节点
		setRoot(nodes.get(0));
	}

	/**
	 * 先序遍历.
	 *
	 * @param node
	 *            二叉树的根
	 */
	public void preOrderTraverse(N node) {
		visit(node);
		if (node.hasLeftChild()) {
			preOrderTraverse((N) node.getLeftChild());
		}
		if (node.hasRightChild()) {
			preOrderTraverse((N) node.getRightChild());
		}
	}
	
	
	/**
	 * 先序查找.
	 * 从上往下查找.
	 *
	 * @param data 查找的数据
	 * @param node 起始查找结点，一般使用根节点
	 * @return Node
	 */
	public BinaryNode search(Object data,BinaryNode node){
		if(data.equals(node.getData())){
			return node;
		}
		BinaryNode result = null;
		if(node.hasLeftChild()){
			result = search(data,node.getLeftChild());
			if (result!=null) {
				return result;
			}
		}
		if(node.hasRightChild()){
			result = search(data,node.getRightChild());
			if (result!=null) {
				return result;
			}
		}
		return result;
	}
	
	/**
	 * 查找结点的霍夫曼编码.
	 *
	 * @param node comments
	 * @return huffmanCode
	 */
	public String findHuffmanCode(BinaryNode node){
		String result = "";
		while (!node.equals(getRoot())) {
			result = node.getCode()+result;
			node = node.getParent();
		}
		return result;
	}
	
	/**
	 * 根据霍夫曼编码查找结点
	 *
	 * @param node comments
	 * @return huffmanCode
	 */
	public BinaryNode findHuffmanCode(String huffmanCode){
		huffmanCode = huffmanCode.toLowerCase();
		char[] chars = huffmanCode.toCharArray();
		BinaryNode result =getRoot();
		for (char c : chars) {
			if (c=='0'&&result.hasLeftChild()) {
				result = result.getLeftChild();
			}else if(c=='1'&&result.hasRightChild()){
				result = result.getRightChild();
			}else{
				result=null;
				break;
			}
		}
		return result;
	}
	
	
	

	/**
	 * 中序遍历.
	 *
	 * @param node
	 *            二叉树的根
	 */
	public void inOrderTraverse(N node) {
		if (node.hasLeftChild()) {
			inOrderTraverse((N) node.getLeftChild());
		}
		visit(node);
		if (node.hasRightChild()) {
			inOrderTraverse((N) node.getRightChild());
		}
	}

	/**
	 * 后续遍历.
	 *
	 * @param node
	 *            二叉树的根
	 */
	public void postOrderTraverse(N node) {
		if (node.hasLeftChild()) {
			postOrderTraverse((N) node.getLeftChild());
		}
		if (node.hasRightChild()) {
			postOrderTraverse((N) node.getRightChild());
		}
		visit(node);
	}

}
