package core.datastruct.tree.binary.sequence;

/**
 * The Class SeqTree.
 * 顺序存储结构，双亲表示法，树
 *
 * @date 2014-8-26 16:26:57
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SeqTree {
	
	/** nodes. */
	private SeqNode[] nodes;
	
	/** root. */
	private SeqNode root;
	
	/**
	 * Gets nodes.
	 *
	 * @return nodes
	 */
	public SeqNode[] getNodes() {
		return nodes;
	}
	
	/**
	 * Sets nodes.
	 *
	 * @param nodes comments
	 */
	public void setNodes(SeqNode[] nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * Gets root.
	 *
	 * @return root
	 */
	public SeqNode getRoot() {
		return root;
	}
	
	/**
	 * Sets root.
	 *
	 * @param root comments
	 */
	public void setRoot(SeqNode root) {
		this.root = root;
	}
}
