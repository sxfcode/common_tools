package core.datastruct.graph.adjacencylist;


/**
 * The Class ALVertex.
 * 顶点
 *
 * @date 2014-8-27 16:19:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ALVertex{
	
	/** 数据. */
	private Object data;
	
	/** 邻接点单链表的头结点. */
	private ALArcNode firstArcNode;
	
	/** 是否被访问过. */
	private boolean visited = false;
	
	public ALVertex(){
	}
	
	public ALVertex(Object data){
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
	 * Gets firstArcNode.
	 *
	 * @return firstArcNode
	 */
	public ALArcNode getFirstArcNode() {
		return firstArcNode;
	}

	/**
	 * Sets firstArcNode.
	 *
	 * @param firstArcNode comments
	 */
	public void setFirstArcNode(ALArcNode firstArcNode) {
		this.firstArcNode = firstArcNode;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
