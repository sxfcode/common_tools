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
		
		// no change
		if (node.compareTo(getRoot())==0) {
			
		}else if (node.compareTo(getRoot())<0) {
			if (current.hasLeftChild()) {
			}else{
			}
		}else {
			if (current.hasRightChild()) {
			}else{
			}
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
