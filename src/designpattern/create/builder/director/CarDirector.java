package designpattern.create.builder.director;

import designpattern.create.builder.builder.CarBuilder;
import designpattern.create.builder.product.Car;

/**
 * The Class CarDirector.
 *
 * @date 2014-4-28 12:54:14
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class CarDirector {
	private CarBuilder builder;
	
	public CarDirector(CarBuilder builder){
		this.builder = builder;
	}
	// 设置生成器
	public void setBuilder(CarBuilder builder){
		this.builder = builder;
	}
	// 开始构造
	public void constructCar(){
		builder.buildNewCar();
		builder.buildBody();
		builder.buildEngine();
		builder.buildWheel();
	}
	// 获取构造结果
	public Car getCar(){
		return builder.getCar();
	}
}
