package core.datastruct.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	 * 是否为有向图.
	 * true为有向图,false为无向图
	 * @return true, if is directed graph
	 */
	public abstract boolean isDirected();
	
	/**
	 * 是否为连通图。
	 * true为连通图,false为非连通图.
	 *
	 * @return true, if is connected graph
	 */
	public abstract boolean isConnected();
	

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
	 * 获取从顶点的出边.
	 *
	 * @param v comments
	 * @return edges
	 */
	public abstract List<E> getOutEdges(V v);
	
	/**
	 * 获取顶点的入边.
	 *
	 * @param v comments
	 * @return edges
	 */
	public abstract List<E> getInEdges(V v);
	
	/**
	 * 通过存储区获得所有边,按权值升序排列.
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
	 * 访问顶点vertex.
	 *
	 * @param vertex
	 *            comments
	 * @return true, if successful
	 */
	public abstract void visit(V vertex);
	
	/**
	 * 将所有结点初始化为未访问状态.
	 */
	public void initUnVisited() {
		for (V v : getAllVertexes()) {
			v.setVisited(false);
		}
	}


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
	 * 克鲁斯卡尔算法求最小生成树，连通图.
	 *
	 * @return Set
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
			if(checkReach(vs[0], vs[1], minTeeESet)){
				continue;
			}else{
				minTeeESet.add(e);
			}
		}
		return minTeeESet;
	}

	/**
	 * 普利姆算法求最小生成树,连通图。 算法说明，初始化最小生成树为任意一个顶点，从剩余顶点中找到距离最小生成树中权值最小的边，
	 * 将该边的非最小生成树顶点和该边，加入到最小生成树，直到最小生成树的顶点数同图的顶点数相同。.
	 * 贪心算法.
	 *
	 * @return Set
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
	
	
	/**
	 * 获取从start顶点到end订单的最短路径.
	 * 原理：递归+最短路径性质
	 *
	 * @param start 起始顶点
	 * @param end 结束顶点
	 * @return minPaths 返回最短路径上的弧或边
	 */
	@SuppressWarnings("unchecked")
	public Set<E> getMinPaths(V start, V end){
		// 判断顶点之间是否存在路径
		if(!checkReach(start,end,getAllEdges())){
			return null;
		}
		// 最短路径
		Set<E> minPaths = null;
		List<E> adjaEdges = getOutEdges(start);
		if(adjaEdges==null||adjaEdges.size()==0){
			return minPaths;
		}
		// 遍历邻接点,找出从邻接点出发到达终点的最短路径
		for (E e : adjaEdges) {
			if(!checkReach((V)e.getHead(),end,getAllEdges())){
				continue;
			}
			Set<E> adjaMin =  new HashSet<E>();
			if(((V)e.getHead()).equals(end)){
				adjaMin = new HashSet<E>();
			}else{
				adjaMin =getMinPaths((V)e.getHead(),end);
			}
			adjaMin.add(e);
			// 赋值
			if (minPaths==null) {
				minPaths = adjaMin;
			}else{
				// 若当前出边的尾部==最终结点，则当前路径长度为当前边的权值
				int currentMinValue = getPathValue(adjaMin);
				int oldMinValue = getPathValue(minPaths);
				// 判断当前路径是否比上次保存的路径更短，若更短，则替换之
				if(currentMinValue<oldMinValue){
					minPaths = adjaMin;
				}
			}
		}
		return minPaths;
	}
	
	
	
	/**
	 * 计算加权路径长度，假设路径是正确且连通的.
	 *
	 * @param edges comments
	 * @return pathValue
	 */
	private int getPathValue(Set<E> edges){
		int result = 0;
		if(edges==null||edges.size()==0){
			return result;
		}
		for (E e : edges) {
			result = result+ e.getWeight();
		}
		return result;
	}
	
	
	/**
	 * 根据指定的边，判断从source顶点出发能否到达target顶点.
	 * 注意:
	 * 1)对无向图来说也可以用来判断两个订单是否属于同一个连通分量，
	 * 2)是对有向图来说，不能用来判断是否同一个连通分量，因为有可能在source 到target之间存在一个逆向的弧，使source与target不可达，但是却属于同一个连通分量
	 *
	 * @param source 起点
	 * @param target 终点
	 * @param edgeSet 允许使用的边或弧
	 * @return true, if successful
	 */
	public boolean checkReach(Vertex source,Vertex target,Collection<E> edgeSet){
		if(isDirected()){
			return checkReachForDirectived(source,target,edgeSet);
		}else{
			return checkReachForUnDirectived(source,target,edgeSet);
		}
	}
	
	/**
	 * 根据指定的边，判断从source顶点能否到达target顶点
	 * 若source顶点跟target订单为同一顶点返回true.
	 *
	 * @param source comments
	 * @param target comments
	 * @param edgeSet comments
	 * @return true, if successful
	 */
	public boolean checkReachForDirectived(Vertex source,Vertex target,Collection<E> edgeSet){
		if(source.equals(target)){
			return true;
		}
		for (E e : edgeSet) {
			if (e.containsTail(source)) {
				source.setVisited(true);
				Vertex next =e.getHead();
				if(next.isVisited()){
					continue;
				}else{
					if (next.equals(target)) {
						return true;
					}else{
						if(checkReachForDirectived(next,target,edgeSet)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据指定的边，判断两个顶点是否属于同一个连通分量.
	 * 无向图算法
	 *
	 * @param source comments
	 * @param target comments
	 * @param minTeeESet comments
	 * @return true, if successful
	 */
	public boolean checkReachForUnDirectived(Vertex source,Vertex target,Collection<E>minTeeESet){
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
						if(checkReachForUnDirectived(other,target,minTeeESet)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 迪杰斯特拉算法求某顶点到其他顶点的最短路径.
	 * 贪心算法
	 *
	 * @param source comments
	 * @return Map
	 */
	public Map<V,Set<E>> dijkstra(V source){
		// 已经找到最短路径的顶点k,k表示距离source的最短路径的顶点
		Set<V> foundShortestPathVertex = new HashSet<V>();
		// 尚未找到最短路径的顶点
		Set<V> notFoundShortestPathVertex = new HashSet<V>();
		
		// 到达每个顶点的当前最短路径,算法会在遍历中修改该值，若路径不可达，则value为null
		Map<V,Set<E>> result = new HashMap<V,Set<E>>();
		
		// 初始化foundShortestPathVertex集合
		for (V v : getAllVertexes()) {
			// source和source的距离是0，属于最短路径之一
			if (source.equals(v)) {
				foundShortestPathVertex.add(v);
			} else {
				notFoundShortestPathVertex.add(v);
			}
		}
		// 初始化，若source与各顶点之间不存在直接路径，则result中为null;
		for (V v : notFoundShortestPathVertex) {
			E directEdge = getDirectEdge(source, v);
			Set<E> shortest =null;
			if (directEdge!=null) {
				shortest = new HashSet<E>();
				shortest.add(directEdge);
			}
			result.put(v, shortest);
		}
		
		// 从V-S中找到路径最短的顶点
		while (!notFoundShortestPathVertex.isEmpty()) {
			getMinPath(foundShortestPathVertex ,notFoundShortestPathVertex,result);
		}
		return result;
	}
	
	/**
	 * Gets minPath.
	 *
	 * @param foundShortestPathVertex 集合S 已经找到最短路径的顶点
	 * @param notFoundShortestPathVertex 集合V-S 尚未找到最短路径的顶点
	 * @param result comments
	 * @return minPath
	 */
	public void getMinPath(Set<V> foundShortestPathVertex ,Set<V> notFoundShortestPathVertex,Map<V,Set<E>> result){
		int currentMin = -1;
		// V-S集合中顶点
		V currentMinV =null; 
		Set<E> currentMinEdges =new HashSet<E>();
		
		V minV =null; 
		Set<E> minEdges =new HashSet<E>();
		
		for (V notfoundv : notFoundShortestPathVertex) {
			currentMinEdges = result.get(notfoundv);
			int oldMin= -1;
			if (currentMinEdges!=null) {
				oldMin = getPathValue(currentMinEdges);
			}
			
			
			for(V foundv:foundShortestPathVertex){
				E directEdge = getDirectEdge(foundv, notfoundv);
				if (directEdge==null) {
					continue;
				}
				Set<E> foundEs= result.get(foundv);
				// 赋初值
				if(oldMin==-1){
					Set<E> newFoundEs = new HashSet<E>();
					newFoundEs.addAll(foundEs);
					newFoundEs.add(directEdge);
					currentMinEdges =newFoundEs;
					oldMin = getPathValue(currentMinEdges);
					continue;
				}
				// 发现更小的路径，替换
				int newPathWeight = directEdge.getWeight()+getPathValue(foundEs);
				if (newPathWeight<oldMin) {
					Set<E> newFoundEs = new HashSet<E>();
					newFoundEs.addAll(foundEs);
					newFoundEs.add(directEdge);
					currentMinEdges =newFoundEs;
					oldMin = getPathValue(currentMinEdges);
				}
			}
			if(oldMin!=-1){
				// 更新到达某点的暂时最短路径
				result.put(notfoundv, currentMinEdges);
				// 当minV为空时，外层循环为minV赋初值
				if (minV==null) {
					minV = notfoundv;
					minEdges = currentMinEdges;
				}else{
					if(getPathValue(currentMinEdges)<getPathValue(minEdges)){
						minV = notfoundv;
						minEdges = currentMinEdges;
					}
					
				}
			}
		}
		// 更新集合S和V-S，(集合S 已经找到最短路径的顶点,集合V-S 尚未找到最短路径的顶点)
		if (minV!=null) {
			foundShortestPathVertex.add(minV);
			notFoundShortestPathVertex.remove(minV);
		}
	}
	
	
	
	/**
	 * 获取两个顶点的直连边或弧.
	 *
	 * @param start comments
	 * @param end comments
	 * @return directEdge
	 */
	public E getDirectEdge(V start, V end){
		E result = null;
		// 如果是有向图
		if (isDirected()) {
			for (E e:getAllEdges()) {
				if(e.containsTail(start)&&e.containsHead(end)){
					result = e;
					break;
				}
			}
		}else{
			for (E e:getAllEdges()) {
				if(e.contains(start)&&e.contains(end)){
					result = e;
					break;
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void printMinPaths(V start,V end,Set<E> edges){
		if(edges==null||edges.size()==0){
			System.out.print(start.getData()+"->"+end.getData()+"无法到达");
		}
		
		
		int weight = 0;
		while (!start.equals(end)) {
			for (E e : edges) {
				if (isDirected()) {
					if(e.getTail().equals(start)){
						System.out.print(start.getData()+"->");
						start=(V)e.getHead();
						weight = weight+e.getWeight();
						break;
					}
				}else{
					if(e.contains(start)){
						System.out.print(start.getData()+"->");
						start=(V)e.getAnother(start);
						weight = weight+e.getWeight();
						break;
					}
				}
				
			}
		}
		System.out.print(end.getData());
		System.out.print("  权值和为:"+weight);
	}
	
	
}
