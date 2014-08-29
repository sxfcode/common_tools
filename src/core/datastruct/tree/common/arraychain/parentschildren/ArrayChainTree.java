package core.datastruct.tree.common.arraychain.parentschildren;

public class ArrayChainTree {
	private ArrayChainNode root;
	private ArrayChainNode[] nodes;
	public ArrayChainNode getRoot() {
		return root;
	}
	public void setRoot(ArrayChainNode root) {
		this.root = root;
	}
	public ArrayChainNode[] getNodes() {
		return nodes;
	}
	public void setNodes(ArrayChainNode[] nodes) {
		this.nodes = nodes;
	}
}
