package core.datastruct.tree.binary.sequence;

/**
 * The Class SeqNode.
 * 顺序存储结构.
 * 对于完全二叉树和满二叉树，可以去掉parentIndex,直接根据完全二叉树的性质，计算子结点和父结点。
 * 因此对于完全二叉树来说，顺序存储结构，即节省存储空间，又简单，是非常好用的存储结构
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
