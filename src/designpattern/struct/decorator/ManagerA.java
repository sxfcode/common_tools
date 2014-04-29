package designpattern.struct.decorator;

/**
 * The Class ManagerA.
 * 装饰具体类。通过装饰具体类，来添加实际的新增功能
 *
 * @date 2014-4-30 1:28:33
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ManagerA extends Manager {

	/**
	 * Instantiates a new ManagerA.
	 *
	 * @param project comments
	 */
	public ManagerA(Project project) {
		super(project);
	}

	@Override
	public void doEarlyWork() {
		System.out.println("项目经理A在做需求分析");
	}

	@Override
	public void doEndWork() {
		System.out.println("项目经理A在做收尾工作");
	}
	

}
