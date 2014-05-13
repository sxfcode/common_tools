package designpattern.action.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ZExample. 
 * 访问者模式. 
 * 访问者模式实现的基本思路：
 * 定义一个就接口来代表要新加入的功能，为了通用，也就是定义一个通用的功能方法来代表新加入的功能
 * 。在对象结构上添加一个方法，作为通用的方法，也就是可以代表要添加的功能
 * ，在这个方法中传入具体的实现新功能的对象。在对象实现的具体实现对象中实现这个方法，回调传入具体的实现新功能的对象，就相当于调用到新功能上了。
 * 
 * 
 * 访问者的功能：能给一系列对象透明第添加新功能，从而避免在维护期间对一系列对象进行修改，而且还能变相实现复用访问者所具有的功能。
 * 
 * 访问者模式的本质：预留通路，实现回调。
 * 
 * 访问者模式的优缺点：
 * 
 * 好的扩展性：能在不修改对象结构的前提下，为对象结构中的元素添加新功能。
 * 
 * 好的复用性：可以通过访问者来定义整个对象结构通用的功能，从而提高复用的程度。
 * 
 * @date 2014-5-13 22:06:43
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		List<VisitNode> lc = new ArrayList<VisitNode>();
		for (int i = 0; i < 6; i++) {
			lc.add(new VisitNode("node "+i));
		}
		VisitorImpl vi = new VisitorImpl();
		for (VisitNode vn :lc) {
			vn.accept(vi);
		}

	}

}
