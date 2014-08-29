package core.datastruct.graph.orthogonallist;

/**
 * The Class OLGraph.
 * 基于十字链表存储结构的图
 * 主要用于有向图
 *
 * @date 2014-8-27 16:40:23
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class OLGraph {
	
	/** 顶点数组. */
	private OLVertex[] vertexes;
	
	/** 弧数组. */
	private OLArcNode[] arcNodes;

	public OLVertex[] getVertexes() {
		return vertexes;
	}

	public void setVertexes(OLVertex[] vertexes) {
		this.vertexes = vertexes;
	}

	public OLArcNode[] getArcNodes() {
		return arcNodes;
	}

	public void setArcNodes(OLArcNode[] arcNodes) {
		this.arcNodes = arcNodes;
	}
	

}
