package core.datastruct.tree.other.complete;

import java.util.Arrays;
import java.util.Stack;

/**
 * The Class CompleteBinaryThree. 
 * 完全二叉树,通过数组实现
 * 少变更，多查找
 * 
 * @date 2014-8-21 10:04:52
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CompleteBinaryThree {
	// 默认大小为100个元素
	/** nodes. */
	private CBTNode[] nodes = new CBTNode[100];
	// 当前树中与元素的个数
	/** length. */
	private int size = 0;

	public CompleteBinaryThree() {
	}

	public CompleteBinaryThree(CBTNode[] newNodes) {
		add(newNodes);
	}

	/**
	 * Gets nodes.
	 *
	 * @return nodes
	 */
	public CBTNode[] getNodes() {
		return nodes;
	}

	/**
	 * Sets nodes.
	 *
	 * @param nodes
	 *            comments
	 */
	public void setNodes(CBTNode[] nodes) {
		this.nodes = nodes;
	}

	/**
	 * Checks if is empty tree.
	 *
	 * @return true, if is empty tree
	 */
	public boolean isEmptyTree() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 完全二叉树，只能向数组尾部添加结点
	 *
	 * @param nodes
	 *            comments
	 * @return true, if successful
	 */
	public void add(CBTNode[] newNodes) {
		protectCapacity(size + newNodes.length);
		System.arraycopy(newNodes, 0, nodes, size, newNodes.length);
		size = size + newNodes.length;
	}

	public void add(CBTNode newNodes) {
		protectCapacity(size + 1);
		nodes[size++] = newNodes;
	}

	/**
	 * protectLength. 保证数组的空间够用
	 *
	 * @param minCapacity
	 *            数组需要的最小空间
	 */
	private void protectCapacity(int minCapacity) {
		int oldCapacity = nodes.length;
		if (minCapacity > oldCapacity) {
			// 增长率为1.5倍
			int newLength = (oldCapacity * 3) / 2 + 1;
			// 若1.5倍空间不满足，则直接去数组必须满足的最小空间
			if (newLength < oldCapacity) {
				newLength = minCapacity;
			}
			nodes = Arrays.copyOf(nodes, newLength);
		}
	}

	public int search(CBTNode node) {
		for (int i = 0; i < nodes.length; i++) {
			if (node.equals(nodes[i])) {
				return i;
			}
		}
		return -1;
	}

	public int getParentIndex(CBTNode node) {
		int index = search(node);
		if(index==0||index==-1){
			return -1;
		}
		return index/2;
	}

	public int getLeftChildIndex(CBTNode node) {
		int index = search(node);
		int left = 2*(index+1);
		if (left<=size) {
			return left-1;
		}
		return -1;
	}

	public int geRightChildIndex(CBTNode node) {
		int index = search(node);
		int right = 2*(index+1)+1;
		if (right<=size) {
			return right-1;
		}
		return -1;
	}
	
	public CBTNode geRightChild(CBTNode node) {
		int index = geRightChildIndex(node);
		if (index>=0) {
			return nodes[index];
		}
		return null;
	}
	
	public CBTNode geLeftChild(CBTNode node) {
		int index = getLeftChildIndex(node);
		if (index>=0) {
			return nodes[index];
		}
		return null;
	}
	
	
	public CBTNode getElemens(int index){
		return nodes[index];
	}

	public CBTNode getRoot(){
		return nodes[0];
	}
	
	public void levelOrder(){
		for (CBTNode cbtNode : nodes) {
			System.out.println(cbtNode.getValue());
		}
	}
	public void preOrder(){
		CBTNode root = getRoot();
		Stack<CBTNode> stack= new Stack<CBTNode>();
		stack.push(root);
		while (stack.size()>0) {
			CBTNode current = stack.pop();
			CBTNode right = this.geRightChild(current);
			// 先入栈的后访问
			if (right!=null) {
				stack.push(right);
			}
			CBTNode left = this.geLeftChild(current);
			if (left!=null) {
				stack.push(left);
				// 遍历子树的根节点
				System.out.println("visit:"+current.getValue());
			}else {
				// 遍历子树的叶子结点
				// 没有左子树，则说明是叶子结点
				System.out.println("visit:"+current.getValue());
			}
		}
	}
	public void middleOrder(){
		CBTNode root = getRoot();
		Stack<CBTNode> stack= new Stack<CBTNode>();
		stack.push(root);
		while (stack.size()>0) {
			CBTNode current = stack.pop();
			CBTNode right = this.geRightChild(current);
			// 先入栈的后访问
			if (right!=null) {
				// 避免遍历回归时，重复加入结点
				if (stack.size()>0&&!stack.peek().equals(right)) {
					stack.push(right);
					stack.push(current);
				}else{
					stack.push(right);
					stack.push(current);
				}
			}
			CBTNode left = this.geLeftChild(current);
			if (left!=null) {
				// 避免遍历回归时，重复加入结点
				if(!stack.peek().equals(right)){
					stack.push(left);
				}
				
			}else {
				// 遍历子树的叶子结点
				// 没有左子树，则说明是叶子结点
				System.out.println("visit:"+current.getValue());
			}
		}
	}
	
	public void postOrder(){
		CBTNode root = getRoot();
		Stack<CBTNode> stack= new Stack<CBTNode>();
		stack.push(root);
		while (stack.size()>0) {
			CBTNode current = stack.pop();
			CBTNode right = this.geRightChild(current);
			if (right!=null) {
				if(!stack.peek().equals(right)){
					stack.push(right);
					stack.push(current);
				}
				
			}
			CBTNode left = this.geLeftChild(current);
			if (left!=null) {
				stack.push(left);
			}else {
				// 遍历子树的叶子结点
				// 没有左子树，则说明是叶子结点
				System.out.println("visit:"+current.getValue());
			}
		}
	}
	
	public static void main(String[] args) {
		CBTNode[] newNodes = new CBTNode[7];
		for (int i = 0; i < newNodes.length; i++) {
			newNodes[i] = new CBTNode(new String((i+1)+""));
		}
		CompleteBinaryThree cbt = new CompleteBinaryThree(newNodes);
		cbt.middleOrder();
	}
	
	

}
