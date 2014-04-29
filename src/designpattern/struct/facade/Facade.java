package designpattern.struct.facade;

/**
 * The Class Facade. 
 *    外观接口,通过外观接口来访问子系统的方法。
 * 
 * @date 2014-4-30 0:50:47
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Facade {
	
	private SubSystemA sa;
	
	private SubSystemB sb;

	public Facade() {
		sa = new SubSystemA();
		sb = new SubSystemB();
	}

	public void methodBuinessA() {
		sa.methodOne();
		sb.methodOne();

	}

	public void methodBuinessB() {
		sa.methodOne();
		sb.methodOne();
		sb.methodTwo();
	}
}
