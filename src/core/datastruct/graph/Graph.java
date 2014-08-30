package core.datastruct.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * The Class Graph. V表示顶点，E表示边
 *
 * @param <V>
 *            the generic type
 * @param <E>
 *            the element type
 * @date 2014-8-28 13:48:41
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class Graph<V extends Vertex, E extends Edge> {

	/**
	 * 通过存储区获得所有顶点.
	 *
	 * @return allVertexes
	 */
	public abstract List<V> getAllVertexes();

	/**
	 * 获取顶点的所有邻接点.
	 *
	 * @param vertex
	 *            comments
	 * @return allAdjacencyVertexes
	 */
	public abstract List<V> getAllAdjacencyVertexes(V vertex);

	/**
	 * 获取边两端的顶点.
	 *
	 * @param e
	 *            comments
	 * @return edgeVertexes
	 */
	public abstract List<V> getEdgeVertexes(E e);

	/**
	 * 通过存储区获得所有边.
	 *
	 * @return allVertexes
	 */
	public abstract List<E> getAllEdges();
	
	/**
	 * 通过存储区获得所有边,按权值升序排列
	 *
	 * @return allVertexes
	 */
	public abstract List<E> getAllAscEdges();
	
	/**
	 * 通过存储区获得所有边,按权值降序排列.
	 *
	 * @return allDescEdges
	 */
	public abstract List<E> getAllDescEdges();


	/**
	 * visit.
	 *
	 * @param vertex
	 *            comments
	 * @return true, if successful
	 */
	public abstract void visit(V vertex);


	/**
	 * 深度优先搜索。
	 * 从起始顶点开始，访问起始顶点的邻接点，之后访问邻接点的邻接点，之后嵌套执行，到达一个最末尾的顶点后，退回查找，访问另一个未被访问过的顶点
	 * ，依次执行该遍历操作。.
	 */
	public void dfsGraph() {
		for (V v : getAllVertexes()) {
			if (!v.isVisited()) {
				dfsGraph(v);
			}
		}
	}

	/**
	 * dfsGraph.
	 *
	 * @param vertex
	 *            comments
	 */
	public void dfsGraph(V vertex) {
		if (!vertex.isVisited()) {
			visit(vertex);
			vertex.setVisited(true);
			// 依次对邻接点进行深度遍历
			for (V adjaVertex : getAllAdjacencyVertexes(vertex)) {
				dfsGraph(adjaVertex);
			}
		}

	}

	/**
	 * 广度优先搜索, 从起始顶点开始，访问他的所有邻接点，之后遍历各邻接点的所有邻接点， 这个遍历过程呈现辐射状，类似层次遍历.
	 */
	public void bfsGraph() {
		for (V v : getAllVertexes()) {
			if (!v.isVisited()) {
				bfsGraph(v);
			}
		}
	}

	/**
	 * dfsGraph.
	 *
	 * @param vertex
	 *            comments
	 */
	public void bfsGraph(V vertex) {
		if (!vertex.isVisited()) {
			visit(vertex);
			vertex.setVisited(true);
			// 先做层次遍历
			for (V adjaVertex : getAllAdjacencyVertexes(vertex)) {
				if (!adjaVertex.isVisited()) {
					visit(adjaVertex);
					adjaVertex.setVisited(true);
				}

			}
			// 分别对邻接点进行层次遍历
			for (V adjaVertex : getAllAdjacencyVertexes(vertex)) {
				bfsGraph(adjaVertex);
			}
		}
	}

	/**
	 * 将所有结点初始化为未访问状态.
	 */
	public void initUnVisited() {
		for (V v : getAllVertexes()) {
			v.setVisited(false);
		}
	}

	/**
	 * 克鲁斯卡尔算法求最小生成树，连通图.
	 */
	public Set<E> kruskalsMinTree() {
		// minTeeVSet+minTeeVSetOther是图的顶点全集
		// minTeeESet+minTeeESetOther是图的边全集

		// 最小生成树的顶点集合,初始化为空
		Set<V> minTeeVSet = new HashSet<V>();
		minTeeVSet.addAll(getAllVertexes());
		// 最小生成树的边集合，初始化为空
		Set<E> minTeeESet = new HashSet<E>();

//		// 非最小生成树的顶点集合,初始化为全集
//		Set<V> minTeeVSetOther = new HashSet<V>();
		// 非最小生成树的边集合，初始化为全集,按照权值排序
		List<E> minTeeESetOther = new ArrayList<E>();
		minTeeESetOther.addAll(getAllAscEdges());
		
		int maxVertexNumber = minTeeVSet.size();
		
		// 堆栈,注意:元素出栈是按照入栈的逆序顺序进行的
		Stack<E> minTeeESetOtherStack = new Stack<E>();
		minTeeESetOtherStack.addAll(getAllDescEdges());
		// 按照权值从小到大遍历所有边，遍历结束条件当最小生成树的边的数=n-1时
		while (minTeeESetOtherStack.size()>0&&minTeeESet.size()<maxVertexNumber-1) {
			E e = minTeeESetOtherStack.pop();
			Vertex[] vs =e.getVertexes();
			// 是否属于同一个连通分量
			if(checkInOne(vs[0], vs[1], minTeeESet)){
				continue;
			}else{
				minTeeESet.add(e);
			}
		}
		return minTeeESet;
	}
	
	/**
	 * 判断两个顶点是否属于同一个连通分量.
	 *
	 * @param source comments
	 * @param target comments
	 * @param minTeeESet comments
	 * @return true, if successful
	 */
	public boolean checkInOne(Vertex source,Vertex target,Set<E>minTeeESet){
		for (E e : minTeeESet) {
			if (e.contains(source)) {
				source.setVisited(true);
				Vertex other =e.getAnother(source);
				if(other.isVisited()){
					continue;
				}else{
					if (other.equals(target)) {
						return true;
					}else{
						if(checkInOne(other,target,minTeeESet)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 普利姆算法求最小生成树,连通图。 算法说明，初始化最小生成树为任意一个顶点，从剩余顶点中找到距离最小生成树中权值最小的边，
	 * 将该边的非最小生成树顶点和该边，加入到最小生成树，直到最小生成树的顶点数同图的顶点数相同。.
	 */
	public Set<E> primMinTree() {
		// minTeeVSet+minTeeVSetOther是图的顶点全集
		// minTeeESet+minTeeESetOther是图的边全集

		// 最小生成树的顶点集合,初始化为空
		Set<V> minTeeVSet = new HashSet<V>();
		// 最小生成树的边集合，初始化为空
		Set<E> minTeeESet = new HashSet<E>();

		// 非最小生成树的顶点集合,初始化为全集
		Set<V> minTeeVSetOther = new HashSet<V>();
		minTeeVSetOther.addAll(getAllVertexes());
		// 非最小生成树的边集合，初始化为全集
		Set<E> minTeeESetOther = new HashSet<E>();
		minTeeESetOther.addAll(getAllEdges());

		int maxVertexNumber = minTeeVSetOther.size();
		// 初始化最小生成树的顶点
		V firstV = getAllVertexes().get(0);
		minTeeVSet.add(firstV);
		minTeeVSetOther.remove(firstV);
		
		
		// 当最小生成树的顶点数跟图的顶点数相同时，循环结束
		while (minTeeVSet.size() < maxVertexNumber) {
			E e = getMinCost(minTeeVSet, minTeeVSetOther, minTeeESetOther);
			// 将查找到的边和顶点，加入最小生成树
			minTeeVSet.addAll(getEdgeVertexes(e));
			minTeeESet.add(e);
			// 从其他顶点和边的集合中移除，查找到的边和顶点
			minTeeVSetOther.removeAll(getEdgeVertexes(e));
			minTeeESetOther.remove(e);
		}
		return minTeeESet;
	}

	/**
	 * 边的顶点是否在顶点集合中存在.
	 *
	 * @param e
	 *            comments
	 * @param vSet
	 *            comments
	 * @return true, if successful
	 */
	public boolean edgeContainsVertexes(E e, Set<V> vSet) {
		for (V v : vSet) {
			if (e.contains(v)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取构成最小生成树的最小权值的边.
	 *
	 * @param minTeeVSet
	 *            最小生成树的顶点
	 * @param minTeeVSetOther
	 *            非最小生成树的顶点
	 * @param minTeeESetOther
	 *            非最小生成树的边
	 * @return minCost
	 */
	public E getMinCost(Set<V> minTeeVSet, Set<V> minTeeVSetOther,
			Set<E> minTeeESetOther) {
		E min = null;
		for (E e : minTeeESetOther) {
			// 边的两个顶点必须一个是最小生成树的点，另一个是非最小生成树的顶点，即该边是连接最小生成树和非最小生成树的
			if (edgeContainsVertexes(e, minTeeVSet)
					&& edgeContainsVertexes(e, minTeeVSetOther)) {
				if (min == null) {
					min = e;
				}
				// 找到权值最小的边
				if (e.getWeight() < min.getWeight()) {
					min = e;
				}
			}
		}
		return min;
	}

}
