package designpattern.create.builder.builder;

import designpattern.create.builder.product.Car;

/**
 * The Interface CarBuilder. 
 * 生成器模式-生成器角色
 * 可以用接口，也可用抽象类来实现。
 * 
 * @date 2014-4-28 12:45:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public abstract class CarBuilder {
	protected Car car;
	
	public Car getCar(){
		return car;
	}
	public void buildNewCar(){
		this.car = new Car();
	}

	/**
	 * 制造车身.
	 */
	public abstract void buildBody();

	/**
	 * 制造轮子.
	 */
	public abstract void buildWheel();

	/**
	 * 制造引擎.
	 */
	public abstract void buildEngine();
}
