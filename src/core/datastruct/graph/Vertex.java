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
	 * @param visited comments
	 */
	public abstract void setVisited(boolean visited);
	
	
	/**
	 * Gets number.
	 *
	 * @return number
	 */
	public abstract Object getNumber() ;

	/**
	 * Sets number.
	 *
	 * @param number comments
	 */
	public abstract void setNumber(Object number) ;
	
	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public abstract Object getData();
	
	/**
	 * Sets data.
	 *
	 * @param data
	 *            comments
	 */
	public abstract  void setData(Object data);


}
