package datastruct.graph.orthogonallist;

/**
 * The Class OLArcNode.
 *
 * @date 2014-8-27 16:56:10
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class OLArcNode {
	
	/** 弧的起点(弧的尾部)的索引. */
	private int tailIndex;
	
	/** 弧的终点(弧的头部)的索引. */
	private int headIndex;
	
	/** 起点相同的下一条弧. */
	private OLArcNode tailLink;
	
	/** 终点相同的下一条弧. */
	private OLArcNode headLink;
	
	/** 权重. */
	private int weight;

	/**
	 * Gets tailIndex.
	 *
	 * @return tailIndex
	 */
	public int getTailIndex() {
		return tailIndex;
	}

	/**
	 * Sets tailIndex.
	 *
	 * @param tailIndex comments
	 */
	public void setTailIndex(int tailIndex) {
		this.tailIndex = tailIndex;
	}

	/**
	 * Gets headIndex.
	 *
	 * @return headIndex
	 */
	public int getHeadIndex() {
		return headIndex;
	}

	/**
	 * Sets headIndex.
	 *
	 * @param headIndex comments
	 */
	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

	/**
	 * Gets tailLink.
	 *
	 * @return tailLink
	 */
	public OLArcNode getTailLink() {
		return tailLink;
	}

	/**
	 * Sets tailLink.
	 *
	 * @param tailLink comments
	 */
	public void setTailLink(OLArcNode tailLink) {
		this.tailLink = tailLink;
	}

	/**
	 * Gets headLink.
	 *
	 * @return headLink
	 */
	public OLArcNode getHeadLink() {
		return headLink;
	}

	/**
	 * Sets headLink.
	 *
	 * @param headLink comments
	 */
	public void setHeadLink(OLArcNode headLink) {
		this.headLink = headLink;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

}
