package designpattern.struct.decorator;

/**
 * The Class Manager.
 * 装饰抽象类.通过装饰抽象类，来确定要添加的装饰功能入口
 *
 * @date 2014-4-30 1:23:38
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Manager implements Project {
	
	/** project. */
	private Project project;
	
	public Manager(Project project){
		this.project = project;
	}

	public void doCoding() {
		doEarlyWork();
		project.doCoding();
		doEndWork();
	}

	public void doEarlyWork(){
	}
	public void doEndWork(){
		
	}
}
