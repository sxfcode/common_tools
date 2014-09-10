package core.datastruct.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Set<E> buildMinTreeByKruskals() {
		// 最小生成树的顶点集合,初始化为空
		Set<V> minTeeVSet = new HashSet<V>();
		minTeeVSet.addAll(getAllVertexes());
		// 最小生成树的边集合，初始化为空
		Set<E> minTeeESet = new HashSet<E>();

		// 非最小生成树的边集合，初始化为全集,按照权值排序
		List<E> minTeeESetOther = new ArrayList<E>();
		minTeeESetOther.addAll(getAllAscEdges());
		int maxVertexNumber = minTeeVSet.size();
		
		// 按照权值从小到大遍历所有边，遍历结束条件当最小生成树的边的数=n-1时
		for (E e : minTeeESetOther) {
			if(minTeeESet.size()<maxVertexNumber-1){
				Vertex[] vs =e.getVertexes();
				// 是否属于同一个连通分量,若属于同一个连通分量，则跳过，检查下一条边
				if(checkReach(vs[0], vs[1], minTeeESet)){
					continue;
				}else{
					minTeeESet.add(e);
				}
			}else{
				break;
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
	public Set<E> buildMinTreeByPrim() {
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
			E e = getMinEdgeForPrim(minTeeVSet, minTeeVSetOther, minTeeESetOther);
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
	 * 获取从start顶点到end订单的最短路径.
	 * 原理：递归+最短路径性质
	 *
	 * @param start 起始顶点
	 * @param end 结束顶点
	 * @return minPaths 返回最短路径上的弧或边
	 */
	@SuppressWarnings("unchecked")
	public Set<E> buildMinPathBySxf(V start, V end){
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
				adjaMin =buildMinPathBySxf((V)e.getHead(),end);
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
	 * 迪杰斯特拉算法求某顶点到其他顶点的最短路径.
	 * 贪心算法
	 *
	 * @param source comments
	 * @return Map
	 */
	public Map<V,Set<E>> buildMinPathByDijkstra(V source){
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
		int i =notFoundShortestPathVertex.size();
		while (i>0) {
			i--;
			processMinPathByDijkstra(foundShortestPathVertex ,notFoundShortestPathVertex,result);
		}
		return result;
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
	private boolean edgeContainsVertexes(E e, Set<V> vSet) {
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
	private E getMinEdgeForPrim(Set<V> minTeeVSet, Set<V> minTeeVSetOther,
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
	 * 3)注意该方法会修改顶点的内部访问状态，请谨慎使用。
	 *
	 * @param source 起点
	 * @param target 终点
	 * @param edgeSet 允许使用的边或弧
	 * @return true, if successful
	 */
	public boolean checkReach(Vertex source,Vertex target,Collection<E> edgeSet){
		// 重置访问状态
		for (E e : edgeSet) {
			e.getHead().setVisited(false);
			e.getTail().setVisited(false);
		}
		boolean result = false;
		if(isDirected()){
			result = checkReachForDirectived(source,target,edgeSet);
		}else{
			result = checkReachForUnDirectived(source,target,edgeSet);
		}
		// 重置访问状态
		for (E e : edgeSet) {
			e.getHead().setVisited(false);
			e.getTail().setVisited(false);
		}
		return result;
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
	 * Gets minPath.
	 *
	 * @param foundShortestPathVertex 集合S 已经找到最短路径的顶点
	 * @param notFoundShortestPathVertex 集合V-S 尚未找到最短路径的顶点
	 * @param result comments
	 * @return minPath
	 */
	public void processMinPathByDijkstra(Set<V> foundShortestPathVertex ,Set<V> notFoundShortestPathVertex,Map<V,Set<E>> result){
		// V-S集合中顶点
		// 外层循环变量
		V outerMinV =null; 
		Set<E> outerMinEdges =new HashSet<E>();
		
		// step 1:外层循环     从到达n个顶点的最短路径中找到最短的一个
		for (V notfoundv : notFoundShortestPathVertex) {
			// 内层循环变量,初始化
			Set<E> innerCurrentMinEdges =result.get(notfoundv);
			int innerOldMin= -1;
			if (innerCurrentMinEdges!=null) {
				innerOldMin = getPathValue(innerCurrentMinEdges);
			}
			
			// step 2: 内层循环    从多个已知的最短路径到达同一个顶点，从这多个最短路径中找到到达该顶点的最短路径
			for(V foundv:foundShortestPathVertex){
				// 只取直接路径
				E directEdge = getDirectEdge(foundv, notfoundv);
				if (directEdge==null) {
					continue;
				}
				// 已知的最短路径
				Set<E> foundEs= result.get(foundv);
				int newPathWeight = directEdge.getWeight()+getPathValue(foundEs);
				// 初始化或者发现更短的路径时
				if(innerOldMin==-1||newPathWeight<innerOldMin){
					Set<E> newFoundEs = new HashSet<E>();
					newFoundEs.addAll(foundEs);
					newFoundEs.add(directEdge);
					innerCurrentMinEdges =newFoundEs;
					innerOldMin = getPathValue(innerCurrentMinEdges);
				}
			}
			
			// step 3:更新到达某点的最短路径
			if(innerOldMin!=-1){
				// 更新到达某点的暂时最短路径
				result.put(notfoundv, innerCurrentMinEdges);
				//初始化或者发现更短的路径时
				if (outerMinV==null||getPathValue(innerCurrentMinEdges)<getPathValue(outerMinEdges)) {
					outerMinV = notfoundv;
					outerMinEdges = innerCurrentMinEdges;
				}
			}
		}
		// 本次大循环找打的最短路径
		// step 4:更新最短路径和非最短路径集合  。更新集合S和V-S，(集合S 已经找到最短路径的顶点,集合V-S 尚未找到最短路径的顶点)
		if (outerMinV!=null) {
			foundShortestPathVertex.add(outerMinV);
			notFoundShortestPathVertex.remove(outerMinV);
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
	
	/**
	 * printMinPaths.
	 *
	 * @param start comments
	 * @param end comments
	 * @param edges comments
	 */
	@SuppressWarnings("unchecked")
	public void printMinPaths(V start,V end,Set<E> edges){
		if(edges==null||edges.size()==0){
			System.out.print(start.getData()+"->"+end.getData()+" 无法到达");
			System.out.println();
			return;
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
		System.out.println();
	}
	
	
	/**
	 * 弗洛伊德算法支持负权值.
	 */
	public void floyd(){
		List<V> allVertexes = getAllVertexes();
		int count = allVertexes.size();
		Map<V,Map<V,Set<E>>> result = new HashMap<V,Map<V,Set<E>>>();
		// 初始化路径
		for (V startV : allVertexes) {
			for (V endV : allVertexes) {
				if (startV.equals(endV)) {
					continue;
				}
				// paths为start到达其他顶点的最短路径
				Map<V,Set<E>> toOtherPaths = result.get(startV);
				if(toOtherPaths==null){
					toOtherPaths = new HashMap<V,Set<E>>();
				}
				// 通过直接路径，初始化
				E directE = getDirectEdge(startV, endV);
				if (directE!=null) {
					Set<E> directPath = new HashSet<E>();
					toOtherPaths.put(endV, directPath);
					result.put(endV, toOtherPaths);
				}
			}
		}
		
		//
		for (V startV : allVertexes) {
			for (V mid : allVertexes) {
				if (startV.equals(mid)) {
					continue;
				}
				for (V endV : allVertexes) {
					if (startV.equals(endV)) {
						continue;
					}
					// paths为start到达其他顶点的最短路径
					Map<V,Set<E>> paths = result.get(startV);
					if(paths==null){
						paths = new HashMap<V,Set<E>>();
					}
					// path为null表示startV到endV不存在可达路径，path.size()==0表示startV和endV是同一个顶点
					Set<E> path = getMinPathByFloyd(startV,endV);
					paths.put(endV, path);
				}
			}
		}

	}
	
	/**
	 * Gets minPath.
	 *
	 * @param startV comments
	 * @param endV comments
	 * @return minPath
	 */
	public Set<E> getMinPathByFloyd(V startV,V endV){
		Set<E> result = new HashSet<E>();
		if(!checkReach(startV, endV, getAllEdges())){
			 return null;
		}
		E directE = getDirectEdge(startV, endV);
		if (directE!=null) {
			result.add(directE);
		}
		List<V> allV = getAllVertexes();
		
		return result;
	}
	
	
	
}
