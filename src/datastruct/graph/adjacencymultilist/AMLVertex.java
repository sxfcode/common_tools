package datastruct.graph.adjacencymultilist;

/**
 * The Class AMLVertex.
 * 顶点
 *
 * @date 2014-8-27 17:33:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMLVertex {
	
	/** 数据. */
	private Object data;
	
	/** 第一个依附于该顶点的边. */
	private AMLArcNode firstArcNode;
	
	/** 是否被访问. */
	private boolean visited = false;

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
	 * Gets firstArcNode.
	 *
	 * @return firstArcNode
	 */
	public AMLArcNode getFirstArcNode() {
		return firstArcNode;
	}

	/**
	 * Sets firstArcNode.
	 *
	 * @param firstArcNode comments
	 */
	public void setFirstArcNode(AMLArcNode firstArcNode) {
		this.firstArcNode = firstArcNode;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	

}
