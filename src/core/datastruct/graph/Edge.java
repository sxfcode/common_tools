package core.datastruct.graph;


/**
 * The Class Edge.
 * 图的抽象边
 *
 * @date 2014-8-30 15:15:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class Edge {
	
//	/** 边的权重. */
//	private int weight;
//	
//	/** 边的尾部(边的方向为从尾部指向头部,对于无向图是没有方向的). */
//	private Vertex tail;
//	
//	/** 边的头部. */
//	private Vertex head;
	
	

	public abstract int getWeight();

	public abstract void setWeight(int weight);

	public abstract Vertex getTail();

	public abstract void setTail(Vertex tail);

	public abstract Vertex getHead();

	public abstract void setHead(Vertex head) ;
	
	public boolean contains(Vertex v){
		if(getTail().equals(v)||getHead().equals(v)){
			return true;
		}
		return false;
	}
	
	public boolean containsTail(Vertex v){
		if(getTail().equals(v)){
			return true;
		}
		return false;
	}
	
	public boolean containsHead(Vertex v){
		if(getHead().equals(v)){
			return true;
		}
		return false;
	}
	
	public Vertex[] getVertexes(){
		Vertex[] vs = new Vertex[2];
		vs[0] = getTail();
		vs[1]=getHead();
		return vs;
	}
	
	public Vertex getAnother(Vertex v){
		if(v.equals(getTail())){
			return getHead();
		}else if (v.equals(getHead())) {
			return getTail();
		}else{
			return null;
		}
	}
}
