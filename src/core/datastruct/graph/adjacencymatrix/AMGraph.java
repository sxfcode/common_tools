package core.datastruct.graph.adjacencymatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.datastruct.graph.Graph;

/**
 * The Class Graph. 
 * 基于邻接矩阵存储结构的图. 
 * 无向图，有向图均可,
 * 若AMGraph为有向图，则有adjacencyMatrix[i][j]表示，
 * 从顶点i到顶点j的有向权,注意对于同一对顶点，边的方向相反时，可能权值是不一样的。
 *
 * @date 2014-8-27 15:29:53
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMGraph extends Graph<AMVertex,AMEdge>{

	/** 使用数组存放顶点信息. */
	private AMVertex[] vertexes;

	/** 使用邻接矩阵存储边信息. */
	private int[][] adjacencyMatrix;
	
	/** 使用数组存放边的信息，该数组用来生成最小生成树. */
	private AMEdge[] edges;
	
	/** 是否有向图. */
	private boolean isDirected;
	
	/** 是否连通. */
	private boolean isConnected;

	/**
	 * Gets vertexes.
	 *
	 * @return vertexes
	 */
	public AMVertex[] getVertexes() {
		return vertexes;
	}

	/**
	 * Sets vertexes.
	 *
	 * @param vertexes comments
	 */
	public void setVertexes(AMVertex[] vertexes) {
		this.vertexes = vertexes;
	}

	/**
	 * Gets adjacencyMatrix.
	 *
	 * @return adjacencyMatrix
	 */
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	/**
	 * Sets adjacencyMatrix.
	 *
	 * @param adjacencyMatrix comments
	 */
	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	/* (non-Javadoc)
	 * @see core.datastruct.graph.Graph#getAllVertexes()
	 */
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


	public void visit(AMVertex vertex) {
		vertex.setVisited(true);
		System.out.print(vertex.getData()+",");
	}
	
	public List<AMVertex> getEdgeVertexes(AMEdge e) {
		return Arrays.asList(e.getVertexes());
	}

	public List<AMEdge> getAllEdges() {
		return Arrays.asList(edges);
	}

	
	

	@Override
	public List<AMEdge> getAllAscEdges() {
		List<AMEdge>result = getAllEdges();
		Collections.sort(result,new Comparator<AMEdge>() {
			@Override
			public int compare(AMEdge o1, AMEdge o2) {
				return o1.getWeight()-o2.getWeight();
			}
		});
		return result;
	}

	@Override
	public List<AMEdge> getAllDescEdges() {
		List<AMEdge>result = getAllEdges();
		Collections.sort(result,new Comparator<AMEdge>() {
			@Override
			public int compare(AMEdge o1, AMEdge o2) {
				return o2.getWeight()-o1.getWeight();
			}
		});
		return result;
	}


	/**
	 * Gets edges.
	 *
	 * @return edges
	 */
	public AMEdge[] getEdges() {
		return edges;
	}

	/**
	 * Sets edges.
	 *
	 * @param edges comments
	 */
	public void setEdges(AMEdge[] edges) {
		this.edges = edges;
	}

	public boolean isDirected() {
		return isDirected;
	}

	/**
	 * Sets directed.
	 *
	 * @param isDirected comments
	 */
	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * Sets connected.
	 *
	 * @param isConnected comments
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	@Override
	public List<AMEdge> getOutEdges(AMVertex v) {
		List<AMEdge> edges = new ArrayList<AMEdge>();
		for (AMEdge amEdge : this.edges) {
			if (amEdge.getTail().equals(v)) {
				edges.add(amEdge);
			}
		}
		return edges;
	}

	@Override
	public List<AMEdge> getInEdges(AMVertex v) {
		List<AMEdge> edges = new ArrayList<AMEdge>();
		for (AMEdge amEdge : this.edges) {
			if (amEdge.getHead().equals(v)) {
				edges.add(amEdge);
			}
		}
		return edges;
	}
	
	/**
	 * 最小生成树测试图,对应图7.16(a)
	 */
	public void createMinTreeTestGraph(){
//		this.vertexes = new AMVertex[5];
//		vertexes[0] = new AMVertex(1);
//		vertexes[1] = new AMVertex(2);
//		vertexes[2] = new AMVertex(3);
//		vertexes[3] = new AMVertex(4);
//		vertexes[4] = new AMVertex(5);
//		adjacencyMatrix = new int[5][5];
//		adjacencyMatrix[0]=new int[]{0,1,0,1,0};
//		adjacencyMatrix[1]=new int[]{1,0,1,0,0};
//		adjacencyMatrix[2]=new int[]{0,1,0,0,0};
//		adjacencyMatrix[3]=new int[]{1,0,0,0,1};
//		adjacencyMatrix[4]=new int[]{0,0,0,1,0};
		
		// 测试最小生成树
		this.vertexes = new AMVertex[6];
		vertexes[0] = new AMVertex(1);
		vertexes[1] = new AMVertex(2);
		vertexes[2] = new AMVertex(3);
		vertexes[3] = new AMVertex(4);
		vertexes[4] = new AMVertex(5);
		vertexes[5] = new AMVertex(6);
		adjacencyMatrix = new int[6][6];
		adjacencyMatrix[0]=new int[]{0,	6,	1,	5,	0,	0};
		adjacencyMatrix[1]=new int[]{6,	0,	5,	0,	3,	0};
		adjacencyMatrix[2]=new int[]{1,	5,	0,	5,	6,	4};
		adjacencyMatrix[3]=new int[]{5,	0,	5,	0,	0,	2};
		adjacencyMatrix[4]=new int[]{0,	3,	6,	0,	0,	6};
		adjacencyMatrix[5]=new int[]{0,	0,	4,	2,	6,	0};
		edges = new AMEdge[10];
		edges[0] = new AMEdge(vertexes[0],vertexes[1],6);
		edges[1] = new AMEdge(vertexes[0],vertexes[2],1);
		edges[2] = new AMEdge(vertexes[0],vertexes[3],5);
		edges[3] = new AMEdge(vertexes[1],vertexes[2],5);
		edges[4] = new AMEdge(vertexes[1],vertexes[4],3);
		edges[5] = new AMEdge(vertexes[2],vertexes[3],5);
		edges[6] = new AMEdge(vertexes[2],vertexes[4],6);
		edges[7] = new AMEdge(vertexes[2],vertexes[5],4);
		edges[8] = new AMEdge(vertexes[3],vertexes[5],2);
		edges[9] = new AMEdge(vertexes[4],vertexes[5],6);
	}
	
	
	/**
	 * 最短路径树测试图,对应图7.34
	 */
	public void createMinPathTestGraph(){
		// 测试最短路径
		this.vertexes = new AMVertex[6];
		vertexes[0] = new AMVertex(0);
		vertexes[1] = new AMVertex(1);
		vertexes[2] = new AMVertex(2);
		vertexes[3] = new AMVertex(3);
		vertexes[4] = new AMVertex(4);
		vertexes[5] = new AMVertex(5);
		adjacencyMatrix = new int[6][6];
		adjacencyMatrix[0]=new int[]{0,0,10,0,30,100};
		adjacencyMatrix[1]=new int[]{0,0,5,0,0,0};
		adjacencyMatrix[2]=new int[]{0,0,0,50,0,0};
		adjacencyMatrix[3]=new int[]{0,0,0,0,0,10};
		adjacencyMatrix[4]=new int[]{0,0,0,20,0,0};
		adjacencyMatrix[5]=new int[]{0,0,0,0,0,60};
		edges = new AMEdge[8];
		edges[0] = new AMEdge(vertexes[0],vertexes[2],10);
		edges[1] = new AMEdge(vertexes[0],vertexes[4],30);
		edges[2] = new AMEdge(vertexes[0],vertexes[5],100);
		edges[3] = new AMEdge(vertexes[1],vertexes[2],5);
		edges[4] = new AMEdge(vertexes[2],vertexes[3],50);
		edges[5] = new AMEdge(vertexes[3],vertexes[5],10);
		edges[6] = new AMEdge(vertexes[4],vertexes[3],20);
		edges[7] = new AMEdge(vertexes[4],vertexes[5],60);

	}
	
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		AMGraph graph = new AMGraph();
		graph.setDirected(false);
		// 创建最小生成树测试图
		graph.createMinTreeTestGraph();
		System.out.println("深度优先遍历");
		graph.dfsGraph();
		graph.initUnVisited();
		System.out.println();
		System.out.println("广度优先遍历");
		graph.bfsGraph();
		System.out.println();
		graph.initUnVisited();
		System.out.println("普利姆算法输出最小生成树");
		Set<AMEdge> edges = graph.primMinTree();
		for (AMEdge e:edges) {
			System.out.println(e.getTail().getData()+"->"+e.getHead().getData()+":"+e.getWeight());
		}
		System.out.println();
		graph.initUnVisited();
		System.out.println("克鲁斯卡尔输出最小生成树");
		edges = graph.kruskalsMinTree();
		for (AMEdge e:edges) {
			System.out.println(e.getTail().getData()+"->"+e.getHead().getData()+":"+e.getWeight());
		}
		// 创建最短路径测试图
		graph.setDirected(true);
		graph.createMinPathTestGraph();
		AMVertex[] vs = graph.getVertexes();
		edges = graph.getMinPaths(vs[0], vs[5]);
		System.out.println("递归生成最短路径，v0到v5的最短路径为:");
		graph.printMinPaths(vs[0], vs[5], edges);
		System.out.println();
		
		System.out.println("迪杰斯特拉算法生成最短路径，v0到v5的最短路径为:");
		Map<AMVertex,Set<AMEdge>> result = graph.dijkstra(vs[0]);
		for (AMVertex target : result.keySet()) {
			graph.printMinPaths(vs[0], target, result.get(target));	
		}
		
	}
	
}
