package core.datastruct.tree.binary.search;

import java.util.ArrayList;
import java.util.List;

import core.datastruct.tree.binary.BinaryTree;

public class BinarySearchTree extends BinaryTree<BinarySearchNode> {
	
	private BinarySearchNode root;
	
	public void init(int[] sourceArray){
		
	}
	
	public void addNode(BinarySearchNode node){
		if (getRoot()==null) {
			this.root = node;
		}
		BinarySearchNode current = getRoot();
		while (true) {
			// no change
			if (node.compareTo(current)==0) {
				break;
				// node小于当前结点
			}else if (node.compareTo(current)<0) {
				if (current.hasLeftChild()) {
					current = current.getLeftChild();
					continue;
				}else{
					insertLeftChild(current, node);
					break;
				}
				// node大于当前结点
			}else {
				if (current.hasRightChild()) {
					current =current.getRightChild();
					continue;
				}else{
					insertRightChild(current, node);
					break;
				}
			}
		}
	}
	
	public void deleteNode(BinarySearchNode node){
		if (node.isLeaf()) {
			BinarySearchNode parent = node.getParent();
			if (node.equals(parent.getLeftChild())) {
				parent.setLeftChild(null);
			}else if(node.equals(parent.getRightChild())){
				parent.setRightChild(null);
			}
			node.setParent(null);
		}else if(node.hasLeftChild()&&node.hasRightChild()) {
			
		}else{
			
		}
		
	}
	
	
	
	

	public BinarySearchNode getRoot() {
		return root;
	}

	public void setRoot(BinarySearchNode root) {
		this.root = root;
	}

	public int getDepth() {
		return 0;
	}

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

	@Override
	public BinarySearchNode buildHuffmanNode(int sourceWeight) {
		BinarySearchNode node = new BinarySearchNode();
		node.setWeight(sourceWeight);
		node.setData(sourceWeight);
		return node;
	}

	@Override
	public void visit(BinarySearchNode n) {
		System.out.println(n.getData());
	}

}
