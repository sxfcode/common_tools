package core.datastruct.graph.adjacencymatrix;

import core.datastruct.graph.Vertex;

/**
 * The Class AMVertex. 顶点
 *
 * @date 2014-8-27 16:59:04
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMVertex extends Vertex {

	/** data. */
	private Object data;
	
	private Object number;

	private boolean visited = false;

	public AMVertex() {
	}

	public AMVertex(Object data) {
		this.data = data;
	}

	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets data.
	 *
	 * @param data
	 *            comments
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Object getNumber() {
		return number;
	}

	public void setNumber(Object number) {
		this.number = number;
	}

}
