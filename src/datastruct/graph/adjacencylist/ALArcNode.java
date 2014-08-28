package datastruct.graph.adjacencylist;

/**
 * The Class ALList.
 * 使用邻接表的图.
 * 
 *
 * @date 2014-8-27 16:22:12
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ALArcNode {
	
	/** 单链表中结点所代表的邻接点在数组中的索引. */
	private int index;
	
	/** 单链表中下一个邻接点的指针. */
	private ALArcNode next;
	
	/** 边的权值. */
	private int weight;

	/**
	 * Gets index.
	 *
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets index.
	 *
	 * @param index comments
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets next.
	 *
	 * @return next
	 */
	public ALArcNode getNext() {
		return next;
	}

	/**
	 * Sets next.
	 *
	 * @param next comments
	 */
	public void setNext(ALArcNode next) {
		this.next = next;
	}

	/**
	 * Gets weight.
	 *
	 * @return weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Sets weight.
	 *
	 * @param weight comments
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

}
