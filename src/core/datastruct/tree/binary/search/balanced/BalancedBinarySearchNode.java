package core.datastruct.tree.binary.search.balanced;

import core.datastruct.tree.binary.search.BinarySearchNode;

public class BalancedBinarySearchNode extends BinarySearchNode {
	private Integer balanceFactor;

	public Integer getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(Integer balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
}
