package core.datastruct.graph.adjacencymatrix;


/**
 * The Class AMVertex.
 * 顶点
 *
 * @date 2014-8-27 16:59:04
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AMVertex{
	
	/** data. */
	private Object data;
	
	private boolean visited = false;
	
	public AMVertex(){
	}
	public AMVertex(Object data){
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
	 * @param data comments
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
	
}
