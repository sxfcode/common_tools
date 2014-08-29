package core.datastruct.tree.common.sequence.parents;

/**
 * The Class SeqNode.
 * 顺序存储结构
 *
 * @date 2014-8-26 16:25:21
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SeqNode {
	
	/** 结点数据. */
	private Object data;
	
	/** 双亲结点的索引. */
	private int parentIndex;

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
	

}
