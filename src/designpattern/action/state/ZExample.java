package designpattern.action.state;

/**
 * The Class ZExample. 
 * 
 * 状态模式：又称对象模式, * 允许一个对象在其内部状态改变时改变其行为。这个对象看上去就像是改变了他的类一样。
 * 状态模式把跟状态关联的逻辑操作封装到提取出来的状态对象中去，由各状态对象处理逻辑。
 * 
 * 状态模式和策略模式的最大区别在于它有状态间的切换，一个状态完了，就要切换到它下一个状态
 * 
 * 举一个例子：我们给一部手机打电话，就可能出现这几种情况：用户开机，用户关机，用户欠费停机， 用户消户等。
 * 所以当我们拨打这个号码的时候：系统就要判断，该用户是否在开机且不忙状态
 * ，又或者是关机，欠费等状态。但不管是那种状态我们都应给出对应的处理操作。下面我们用代码来模拟一下这个过程。
 * 
 * 
 * @date 2014-5-11 23:59:58
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
		MobilePhone mp = new MobilePhone();
		mp.doCall();
		mp.doCall();
	}
}
