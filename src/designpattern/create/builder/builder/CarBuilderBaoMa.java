package designpattern.create.builder.builder;

/**
 * The Class BaoMaCarBuilder. 
 * 生成器模式-生成器角色-子类
 * 
 * @date 2014-4-28 12:52:08
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class CarBuilderBaoMa extends CarBuilder {

	@Override
	public void buildBody() {
		this.car.setBody("宝马的外壳");
	}

	@Override
	public void buildWheel() {
		this.car.setWheel("宝马的轮子");
	}

	@Override
	public void buildEngine() {
		this.car.setEngine("宝马的引擎");
	}

}
