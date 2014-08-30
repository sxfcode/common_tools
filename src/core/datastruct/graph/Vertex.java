package core.datastruct.graph;

/**
 * The Class Vertex.
 * 图的抽象顶点
 *
 * @date 2014-8-30 16:43:49
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class Vertex {
	
	/**
	 * Checks if is visited.
	 *
	 * @return true, if is visited
	 */
	public abstract boolean isVisited();

	/**
	 * Sets visited.
	 *
	 */
	public abstract void setVisited(boolean visited);


}
