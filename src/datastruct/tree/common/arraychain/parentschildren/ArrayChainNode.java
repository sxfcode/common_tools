package datastruct.tree.common.arraychain.parentschildren;

/**
 * The Class ArrayChainNode.
 *  数组链式存储方式
 *  双亲孩子表示法
 *
 * @date 2014-8-26 16:46:45
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ArrayChainNode {

	/** data. */
	private Object data;

	/** 双亲结点的位置索引. */
	private int parentIndex;

	/** 右边第一个兄弟的指针. */
	private ArrayChainNode nextSibling;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public ArrayChainNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(ArrayChainNode nextSibling) {
		this.nextSibling = nextSibling;
	}

}
