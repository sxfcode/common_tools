package datastruct.graph.adjacencylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import datastruct.graph.Graph;

/**
 * The Class ALGraph.
 * 基于邻接表存储结构的图
 * 无向图有向图均可.在表示有向图时，需要一个逆向邻接表
 *
 * @date 2014-8-27 16:27:28
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ALGraph extends Graph<ALVertex>{
	
	/** 顶点数组. */
	private ALVertex[] vertexes;
	
	/** 弧数组. */
	private ALArcNode[] arcNodes;
	
	public void createTestGraph(){
		ALVertex v1 = new ALVertex(new Integer(1));
		ALVertex v2 = new ALVertex(new Integer(2));
		ALVertex v3 = new ALVertex(new Integer(3));
		ALArcNode arc1 = new ALArcNode();
		ALArcNode arc21 = new ALArcNode();
		ALArcNode arc22 = new ALArcNode();
		ALArcNode arc3 = new ALArcNode();
		// 设置结点1 和结点1的单链表
		v1.setFirstArcNode(arc1);
		arc1.setIndex(1);
		// 设置结点2 和结点2的单链表
		v2.setFirstArcNode(arc21);
		arc21.setIndex(0);
		arc21.setNext(arc22);
		arc22.setIndex(2);
		// 设置结点3 和结点4的单链表
		v3.setFirstArcNode(arc3);
		arc3.setIndex(1);
		vertexes = new ALVertex[3];
		vertexes[0] = v1;
		vertexes[1] = v2;
		vertexes[2] = v3;
	}

	/**
	 * Gets vertexes.
	 *
	 * @return vertexes
	 */
	public ALVertex[] getVertexes() {
		return vertexes;
	}

	/**
	 * Sets vertexes.
	 *
	 * @param vertexes comments
	 */
	public void setVertexes(ALVertex[] vertexes) {
		this.vertexes = vertexes;
	}

	/**
	 * Gets arcNodes.
	 *
	 * @return arcNodes
	 */
	public ALArcNode[] getArcNodes() {
		return arcNodes;
	}

	/**
	 * Sets arcNodes.
	 *
	 * @param arcNodes comments
	 */
	public void setArcNodes(ALArcNode[] arcNodes) {
		this.arcNodes = arcNodes;
	}
	
	/**
	 * visit.
	 *
	 * @param vertex comments
	 */
	public void visit(ALVertex vertex){
		System.out.println(vertex.getData());
		setVisited(vertex);
	}
	
	/**
	 * 深度优先遍历.
	 */
	public void dfsTraverse(){
		// 遍历，防止丢失未连通图
		for (ALVertex vertex : vertexes) {
			dfs(vertex);
		}
	}
	
	/**
	 * 广度优先遍历.
	 */
	public void wfsTraverse(){
		// 遍历，防止丢失未连通图
		for (ALVertex vertex : vertexes) {
			wfs(vertex);
		}
	}
	
	/** count. */
	private static int count = 0;
	
	/**
	 * dfs.
	 *
	 * @param vertex comments
	 */
	public void dfs(ALVertex vertex){
		System.out.println("递归:"+(count++));
		if (!vertex.isVisited()) {
			visit(vertex);
		}
		for (ALArcNode arc= vertex.getFirstArcNode() ; arc!=null ; arc = arc.getNext()) {
			ALVertex v = vertexes[arc.getIndex()];
			if (!v.isVisited()) {
				dfs(v);
			}
		}
	}
	
	
	/**
	 * 广度优先遍历，类似水平遍历
	 *
	 * @param vertex comments
	 */
	public void wfs(ALVertex vertex){
		System.out.println("遍历:"+(count++));
		if (!vertex.isVisited()) {
			visit(vertex);
		}
		List<ALVertex> list = new ArrayList<ALVertex>();
		for (ALArcNode arc= vertex.getFirstArcNode();arc!=null;arc = arc.getNext()) {
			visit(vertexes[arc.getIndex()]);
			list.add(vertexes[arc.getIndex()]);
		}
		for (ALVertex alVertex : list) {
			wfs(alVertex);
		}
	}

	@Override
	public List<ALVertex> getAllVertexes() {
		return Arrays.asList(vertexes);
	}


	@Override
	public List<ALVertex> getAllAdjacencyVertexes(ALVertex vertex) {
		List<ALVertex> list = new ArrayList<ALVertex>();
		for (ALArcNode arc= vertex.getFirstArcNode();arc!=null;arc = arc.getNext()) {
			list.add(vertexes[arc.getIndex()]);
		}
		return list;
	}

	@Override
	public boolean isVisited(ALVertex vertex) {
		return vertex.isVisited();
	}

	@Override
	public void setVisited(ALVertex vertex) {
		vertex.setVisited(true);
	}
	
	@Override
	public void setUnVisited(ALVertex vertex) {
		vertex.setVisited(false);
	}
	public static void main(String[] args) {
		ALGraph al = new ALGraph();
		al.createTestGraph();
		System.out.println("深度优先遍历");
		al.dfsGraph();
		al.initUnVisited();
		System.out.println("广度优先遍历");
		al.bfsGraph();
	}
	
	
}
