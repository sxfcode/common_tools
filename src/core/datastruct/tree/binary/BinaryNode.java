package core.datastruct.tree.binary;


/**
 * The Class Node.
 * 树的抽象节点
 *
 * @date 2014-9-10 15:36:17
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public abstract class BinaryNode {
	
	/**
	 * Gets data.
	 *
	 * @return data
	 */
	public abstract Object getData();
	
	/**
	 * Sets data.
	 *
	 * @param data comments
	 */
	public abstract void setData(Object data);

	/**
	 * Gets leftChild.
	 *
	 * @return leftChild
	 */
	public abstract BinaryNode getLeftChild() ;
	
	/**
	 * Sets leftChild.
	 *
	 * @param leftChild comments
	 */
	public abstract void setLeftChild(BinaryNode leftChild) ;
	
	/**
	 * Gets rightChild.
	 *
	 * @return rightChild
	 */
	public abstract BinaryNode getRightChild() ;
	
	/**
	 * Sets rightChild.
	 *
	 * @param rightChild comments
	 */
	public abstract void setRightChild(BinaryNode rightChild);

	/**
	 * Gets parent.
	 *
	 * @return parent
	 */
	public abstract BinaryNode getParent();
	
	/**
	 * Sets parent.
	 *
	 * @param parent comments
	 */
	public abstract void setParent(BinaryNode parent) ;
	
	/**
	 * Gets weight.
	 *
	 * @return weight
	 */
	public abstract Integer getWeight() ;

	/**
	 * Sets weight.
	 *
	 * @param weight comments
	 */
	public abstract void setWeight(Integer weight) ;

	/**
	 * Gets number.
	 *
	 * @return number
	 */
	public abstract Integer getNumber() ;

	/**
	 * Sets number.
	 *
	 * @param number comments
	 */
	public abstract void setNumber(Integer number);
	
	
	public abstract String getCode();

	public abstract void setCode(String code);

	/**
	 * hasLeftChild.
	 *
	 * @return true, if successful
	 */
	public boolean hasLeftChild() {
		if (getLeftChild() == null) {
			return false;
		}
		return true;
	}

	/**
	 * hasRightChild.
	 *
	 * @return true, if successful
	 */
	public boolean hasRightChild() {
		if (getRightChild() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if is leaf.
	 *
	 * @return true, if is leaf
	 */
	public boolean isLeaf() {
		if (!hasLeftChild() && !hasRightChild()) {
			return true;
		}
		return false;
	}

}
