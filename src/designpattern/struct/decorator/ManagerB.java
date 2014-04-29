package designpattern.struct.decorator;

/**
 * The Class ManagerB.
 *
 * @date 2014-4-30 1:30:22
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ManagerB extends Manager {

	public ManagerB(Project project) {
		super(project);
	}

	@Override
	public void doEarlyWork() {
		System.out.println("项目经理B在做需求分析");
		System.out.println("项目经理B在做详细设计");
	}

	@Override
	public void doEndWork() {
		System.out.println("项目经理B在做收尾工作");
	}

}
