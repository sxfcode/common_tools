package core.datastruct.tree.other.digitalsearchtree.trie;

import core.datastruct.tree.other.digitalsearchtree.DigitalSearchNode;


/**
 * The Class DigitalSearchTrieNode.
 *  键树的结点
 * @date 2014-9-28 11:14:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DigitalSearchTrieNode extends DigitalSearchNode {
	
	/** 关键字. */
	private Object data;
	
	/** 父结点. */
	private DigitalSearchTrieNode parent;
	
	/** 子结点. */
	private DigitalSearchTrieNode[] children;
	
	/** 当前结点是否是某个单词的结尾. */
	private boolean isWordEnd = false;
	
	/** 是否已被访问. */
	private boolean visited = false;
	
	/**
	 * Instantiates a new DigitalSearchTrieNode.
	 *
	 * @param data comments
	 */
	public DigitalSearchTrieNode(Object data){
		this.data = data;
	}

	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data comments
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Gets parent.
	 *
	 * @return parent
	 */
	public DigitalSearchTrieNode getParent() {
		return parent;
	}

	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
	public void setParent(DigitalSearchTrieNode parent) {
		this.parent = parent;
	}

	/**
	 * Gets children.
	 *
	 * @return children
	 */
	public DigitalSearchTrieNode[] getChildren() {
		return children;
	}

	/**
	 * Sets children.
	 *
	 * @param children comments
	 */
	public void setChildren(DigitalSearchTrieNode[] children) {
		this.children = children;
	}

	/**
	 * Checks if is word end.
	 *
	 * @return true, if is word end
	 */
	public boolean isWordEnd() {
		return isWordEnd;
	}

	/**
	 * Sets wordEnd.
	 *
	 * @param isWordEnd comments
	 */
	public void setWordEnd(boolean isWordEnd) {
		this.isWordEnd = isWordEnd;
	}

	/**
	 * Checks if is visited.
	 *
	 * @return true, if is visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets visited.
	 *
	 * @param visited comments
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public void setParent(DigitalSearchNode parent) {
		this.parent = (DigitalSearchTrieNode) parent;
	}

	@Override
	public String toString() {
		return "DigitalSearchTrieNode [data=" + data + "]";
	}
	
}
