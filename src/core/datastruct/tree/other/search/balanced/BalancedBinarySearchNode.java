package core.datastruct.tree.other.search.balanced;

import core.datastruct.tree.binary.search.BinarySearchNode;

public class BalancedBinarySearchNode extends BinarySearchNode {
	private Integer balanceFactor;
	
	private Integer depth = 0;
	

	public Integer getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(Integer balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	
}
