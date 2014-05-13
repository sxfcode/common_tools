package designpattern.action.visitor;

/**
 * The Class VisitNode.
 *
 * @date 2014-5-13 22:09:53
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class VisitNode implements Visitable {
	
	/** name. */
	private String name;

	/**
	 * Instantiates a new VisitNode.
	 *
	 * @param name comments
	 */
	public VisitNode(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor vistor) {
		vistor.visit(this);
	}

	/**
	 * Gets name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name comments
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "VisitNode [name=" + name + "]";
	}

}
