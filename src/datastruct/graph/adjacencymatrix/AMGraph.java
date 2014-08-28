package datastruct.graph.adjacencymatrix;

/**
 * The Class Graph. 
 * 基于邻接矩阵存储结构的图. 
 * 无向图，有向图均可
 *
 * @date 2014-8-27 15:29:53
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMGraph {

	/** 使用数组存放顶点信息. */
	private AMVertex[] vertexes;

	/** 使用邻接矩阵存储边信息. */
	private int[][] adjacencyMatrix;

	public AMVertex[] getVertexes() {
		return vertexes;
	}

	public void setVertexes(AMVertex[] vertexes) {
		this.vertexes = vertexes;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}
}
