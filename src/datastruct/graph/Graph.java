package datastruct.graph;

import java.util.List;

/**
 * The Class Graph.
 *
 * @param <T> the generic type
 * @date 2014-8-28 13:48:41
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class Graph<T> {
	
	/**
	 * 通过存储区获得所有顶点.
	 *
	 * @return allVertexes
	 */
	public abstract List<T> getAllVertexes();
	
	/**
	 * 获取顶点的所有邻接点.
	 *
	 * @param vertex comments
	 * @return allAdjacencyVertexes
	 */
	public abstract List<T> getAllAdjacencyVertexes(T vertex);
	
	/**
	 * Checks if is visited.
	 *
	 * @param vertex comments
	 * @return true, if is visited
	 */
	public abstract boolean isVisited(T vertex);
	
	/**
	 * visit.
	 *
	 * @param vertex comments
	 * @return true, if successful
	 */
	public abstract void visit(T vertex);
	
	/**
	 * Sets visited.
	 *
	 * @param vertex comments
	 */
	public abstract void setVisited(T vertex);
	

	/**
	 * Sets unVisited.
	 *
	 * @param vertex comments
	 */
	public abstract void setUnVisited(T vertex);
	

	
	/**
	 * 深度优先搜索。
	 * 从起始顶点开始，访问起始顶点的邻接点，之后访问邻接点的邻接点，之后嵌套执行，到达一个最末尾的顶点后，退回查找，访问另一个未被访问过的顶点，依次执行该遍历操作。.
	 */
	public void dfsGraph(){
		for (T v :getAllVertexes()) {
			if (!isVisited(v)) {
				dfsGraph(v);	
			}
		}
	}
	
	/**
	 * dfsGraph.
	 *
	 * @param vertex comments
	 */
	public void dfsGraph(T vertex){
		if (!isVisited(vertex)) {
			visit(vertex);
			setVisited(vertex);
			// 依次对邻接点进行深度遍历
			for (T adjaVertex :getAllAdjacencyVertexes(vertex)) {
				dfsGraph(adjaVertex);
			}
		}
		
	}
	
	/**
	 * 广度优先搜索,
	 * 从起始顶点开始，访问他的所有邻接点，之后遍历各邻接点的所有邻接点，
	 * 这个遍历过程呈现辐射状，类似层次遍历.
	 */
	public void bfsGraph(){
		for (T v :getAllVertexes()) {
			if (!isVisited(v)) {
				bfsGraph(v);	
			}
		}
	}
	
	/**
	 * dfsGraph.
	 *
	 * @param vertex comments
	 */
	public void bfsGraph(T vertex){
		if (!isVisited(vertex)) {
			visit(vertex);
			setVisited(vertex);
			// 先做层次遍历
			for (T adjaVertex :getAllAdjacencyVertexes(vertex)) {
				if(!isVisited(adjaVertex)){
					visit(adjaVertex);
					setVisited(adjaVertex);	
				}
				
			}
			// 分别对邻接点进行层次遍历
			for (T adjaVertex :getAllAdjacencyVertexes(vertex)) {
				bfsGraph(adjaVertex);
			}
		}
	}
	
	/**
	 * 将所有结点初始化为未访问状态.
	 */
	public void initUnVisited(){
		for (T v :getAllVertexes()) {
			setUnVisited(v);
		}
	}

}
