package core.datastruct.tree.other.digitalsearchtree;



/**
 * The Class DigitalSearchNode.
 *
 * @date 2014-9-21 17:05:39
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class DigitalSearchNode {

	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public abstract Object getData() ;

	/**
	 * Sets data.
	 *
	 * @param data comments
	 */
	public abstract void setData(Object data);

	/**
	 * Gets parent.
	 *
	 * @return parent
	 */
	public abstract DigitalSearchNode getParent();

	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
	public abstract void setParent(DigitalSearchNode parent) ;

	/**
	 * Checks if is word end.
	 *
	 * @return true, if is word end
	 */
	public abstract boolean isWordEnd() ;

	/**
	 * Sets wordEnd.
	 *
	 * @param isWordEnd comments
	 */
	public abstract void setWordEnd(boolean isWordEnd);

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
	public abstract void setVisited(boolean visited) ;

}
