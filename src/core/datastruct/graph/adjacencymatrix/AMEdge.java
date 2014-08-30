package core.datastruct.graph.adjacencymatrix;

import core.datastruct.graph.Edge;
import core.datastruct.graph.Vertex;

public class AMEdge extends Edge {
	/** 边的权重. */
	private int weight;
	
	/** 边的尾部(边的方向为从尾部指向头部,对于无向图是没有方向的). */
	private AMVertex tail;
	
	/** 边的头部. */
	private AMVertex head;
	
	public AMEdge(){
	}
	
	public AMEdge(AMVertex tail,AMVertex head,int weight){
		this.tail = tail;
		this.head = head;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public AMVertex getTail() {
		return tail;
	}

	public void setTail(AMVertex tail) {
		this.tail = tail;
	}

	public AMVertex getHead() {
		return head;
	}

	public void setHead(AMVertex head) {
		this.head = head;
	}
	
	public boolean contains(AMVertex v){
		if(tail.equals(v)||head.equals(v)){
			return true;
		}
		return false;
	}
	
	public AMVertex[] getVertexes(){
		AMVertex[] vs = new AMVertex[2];
		vs[0] = tail;
		vs[1]=head;
		return vs;
	}
	
	public AMVertex getAnother(AMVertex v){
		if(v.equals(tail)){
			return head;
		}else if (v.equals(head)) {
			return tail;
		}else{
			return null;
		}
	}

	@Override
	public void setTail(Vertex tail) {
		this.tail = (AMVertex)tail;
	}

	@Override
	public void setHead(Vertex head) {
		this.head = (AMVertex)head;
	}
}
