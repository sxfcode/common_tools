package core.datastruct.graph.adjacencymatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.datastruct.graph.Graph;

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
public class AMGraph extends Graph<AMVertex>{

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

	@Override
	public List<AMVertex> getAllVertexes() {
		return Arrays.asList(vertexes);
	}

	@Override
	public List<AMVertex> getAllAdjacencyVertexes(AMVertex vertex) {
		int vertexIndex =-1;
		for (int i = 0; i < vertexes.length; i++) {
			if(vertexes[i].equals(vertex)){
				vertexIndex = i;
			}
		}
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0;  i< adjacencyMatrix[vertexIndex].length; i++) {
			if (adjacencyMatrix[vertexIndex][i]!=0) {
				indexes.add(i);
			}
		}
		List<AMVertex> result = new ArrayList<AMVertex>();
		for (Integer verIndex : indexes) {
			result.add(vertexes[verIndex]);
		}
		return result;
	}

	@Override
	public boolean isVisited(AMVertex vertex) {
		return vertex.isVisited();
	}

	@Override
	public void visit(AMVertex vertex) {
		setVisited(vertex);
		System.out.println(vertex.getData());
	}

	@Override
	public void setVisited(AMVertex vertex) {
		vertex.setVisited(true);
	}

	@Override
	public void setUnVisited(AMVertex vertex) {
		vertex.setVisited(false);
	}
	
	public void createTestGraph(){
		this.vertexes = new AMVertex[5];
		vertexes[0] = new AMVertex(1);
		vertexes[1] = new AMVertex(2);
		vertexes[2] = new AMVertex(3);
		vertexes[3] = new AMVertex(4);
		vertexes[4] = new AMVertex(5);
		adjacencyMatrix = new int[5][5];
		adjacencyMatrix[0]=new int[]{0,1,0,1,0};
		adjacencyMatrix[1]=new int[]{1,0,1,0,0};
		adjacencyMatrix[2]=new int[]{0,1,0,0,0};
		adjacencyMatrix[3]=new int[]{1,0,0,0,1};
		adjacencyMatrix[4]=new int[]{0,0,0,1,0};
	}
	public static void main(String[] args) {
		AMGraph graph = new AMGraph();
		graph.createTestGraph();
		System.out.println("深度优先遍历");
		graph.dfsGraph();
		graph.initUnVisited();
		System.out.println("广度优先遍历");
		graph.bfsGraph();
	}
}
