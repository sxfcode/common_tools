package core.datastruct.tree.common.chain.children;

/**
 * The Class ChainNode.
 *
 * @date 2014-8-26 16:28:36
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ChainNode {
	
	/** data. */
	private Object data;
	
	/** 孩子结点指针数组(分为固定和非固定两种). */
	private ChainNode[] children;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ChainNode[] getChildren() {
		return children;
	}

	public void setChildren(ChainNode[] children) {
		this.children = children;
	}
}
