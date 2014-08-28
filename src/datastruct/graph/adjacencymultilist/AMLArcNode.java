package datastruct.graph.adjacencymultilist;


/**
 * The Class AMLArcNode.
 * 对于无向图，边是没有方向的，这里的tail和head没有方向，只是代表两端，地位是平等的
 *
 * @date 2014-8-27 17:43:37
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMLArcNode {
	/** 边的A点的索引. */
	private int tailIndex;
	
	/** 边的B点的索引. */
	private int headIndex;
	
	/** 连接到A点的下一边的地址. */
	private AMLArcNode tailLink;
	
	/** 连接到B点的下一边的地址. */
	private AMLArcNode headLink;

	public int getTailIndex() {
		return tailIndex;
	}

	public void setTailIndex(int tailIndex) {
		this.tailIndex = tailIndex;
	}

	public int getHeadIndex() {
		return headIndex;
	}

	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

	public AMLArcNode getTailLink() {
		return tailLink;
	}

	public void setTailLink(AMLArcNode tailLink) {
		this.tailLink = tailLink;
	}

	public AMLArcNode getHeadLink() {
		return headLink;
	}

	public void setHeadLink(AMLArcNode headLink) {
		this.headLink = headLink;
	}
	
	

}
