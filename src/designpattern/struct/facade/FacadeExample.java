package designpattern.struct.facade;

/**
 * The Class FacadeExample.
 * 外观模式
 *  为子系统中的一组接口提供一个统一接口。
 * Facade模式定义了一个高层接口，这个接口使得这子系统更容易使用。
 * 
 * 1 当你要为一个复杂子系统提供一个简单接口时。子系统往往因为不断演化而变得越来越复杂。
 * 大多数模式使用时都会产生更多更小的类。这使得子系统更具可重用性，也更容易对子系统进行定制
 * ，但这也给那些不需要定制子系统的用户带来一些使用上的困难。Facade可以提供一个简单的缺省视图
 * ，这一视图对大多数用户来说已经足够，而那些需要更多的可定制性的用户可以越过Facade层。
 * 
 * 2 客户程序与抽象类的实现部分之间存在着很大的依赖性。引入Facade将这个子系统与客户以及其他的子系统分离，可以提高子系统的独立性和可移植性。
 * 
 * 3 当你需要构建一个层次结构的子系统时，使用Facade模式定义子系统中每层的入口点。如果子系统之间是相互依赖的，你可以让它们仅通过Facade进行通讯，
 * 从而简化了它们之间的依赖关系。
 * 
 * 
 * 
 * @date 2014-4-30 0:45:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class FacadeExample {
	public static void main(String[] args) {
		// 通过外观接口来实现对子系统的访问,而不是直接访问子系统。
		Facade fa = new Facade();
		fa.methodBuinessA();
		fa.methodBuinessB();
	}
}
