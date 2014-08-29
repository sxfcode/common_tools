package core.datastruct.graph.adjacencymultilist;

/**
 * The Class AMLGraph.
 * 基于邻接多重表的图
 * 
 *
 * @date 2014-8-27 17:29:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMLGraph {
	
	/** 顶点数组. */
	private AMLVertex[] vertexes;

	/** 弧线数组. */
	private AMLArcNode[] arcNodes;

	public AMLVertex[] getVertexes() {
		return vertexes;
	}

	public void setVertexes(AMLVertex[] vertexes) {
		this.vertexes = vertexes;
	}

	public AMLArcNode[] getArcNodes() {
		return arcNodes;
	}

	public void setArcNodes(AMLArcNode[] arcNodes) {
		this.arcNodes = arcNodes;
	}
	
	
	
	
}
