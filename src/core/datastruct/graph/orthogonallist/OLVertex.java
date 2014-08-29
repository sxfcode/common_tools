package core.datastruct.graph.orthogonallist;

/**
 * The Class OLVertex.
 * 顶点
 *
 * @date 2014-8-27 16:46:32
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class OLVertex {
	
	/** 结点数据. */
	private Object data;
	
	/** 入度弧线的单链表头结点. */
	private OLArcNode firstInArcNode;
	
	/** 出度弧线的单链表头结点. */
	private OLArcNode firstOutArcNode;
	
	/** 是否被访问. */
	private boolean visited = false;


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public OLArcNode getFirstInArcNode() {
		return firstInArcNode;
	}

	public void setFirstInArcNode(OLArcNode firstInArcNode) {
		this.firstInArcNode = firstInArcNode;
	}

	public OLArcNode getFirstOutArcNode() {
		return firstOutArcNode;
	}

	public void setFirstOutArcNode(OLArcNode firstOutArcNode) {
		this.firstOutArcNode = firstOutArcNode;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
