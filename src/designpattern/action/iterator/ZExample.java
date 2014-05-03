package designpattern.action.iterator;

/**
 * The Class ZExample.
 * 迭代器模式。
 *   迭代器模式由以下角色组成： 
　　1) 迭代器角色（Iterator）：迭代器角色负责定义访问和遍历元素的接口。 
　　2) 具体迭代器角色（Concrete Iterator）：具体迭代器角色要实现迭代器接口，并 
        要记录遍历中的当前位置。 
    3) 容器角色（Container）：容器角色负责提供创建具体迭代器角色的接口。 
　　4) 具体容器角色（Concrete Container）：具体容器角色实现创建具体迭代器角色 
        的接口——这个具体迭代器角色于该容器的结构相关。 
    
   迭代器模式我们可以用工厂和花名册来理解。
   1.所有工厂都有花名册
   2.通过花名册我们可以访问工厂中的员工。
   3.每个实际工厂的花名册的形式可能是不一样的，有的是电子的，有的是纸质的。
    
    
 *
 * @date 2014-5-3 17:05:12
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		CustomContainerImpl<String> cc = new CustomContainerImpl<String>();
		cc.add("测试1");
		cc.add("测试2");
		CustomIterator<String> it = cc.iterator();
		// 使用迭代器遍历元素
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

}
