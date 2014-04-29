package designpattern.struct.decorator;

/**
 * The Class DecoratorExample. 装饰模式
 * 
 * 动态地给一个对象添加一些额外的职责。就增加功能来说，Decorator模式相比生成子类更为灵活。
 * 
 * 1在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。
 * 
 * 2 处理那些可以撤消的职责。
 * 
 * 3 当不能采用生成子类的方法进行扩充时。一种情况是，可能有大量独立的扩展，为支持每一种组合将产生大量的子类，使得子类数目呈爆炸性增长。
 * 另一种情况可能是因为类定义被隐藏，或类定义不能用于生成子类。
 * 
 * @date 2014-4-30 1:34:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class DecoratorExample {
	
	public static void main(String[] args) {
		Project em = new Employe();// 代码工人
		Project ma = new ManagerA(em); // 项目经理,给代码工人贴上项目经理的标签，摇身一变，成为项目经理。
		Project mb = new ManagerB(em); // 项目经理,给代码工人贴上项目经理的标签，摇身一变，成为项目经理。
		// 以经理的名义将编码完成，功劳都是经理的，实际编码的是工人
		ma.doCoding();
		mb.doCoding();
	}

}
