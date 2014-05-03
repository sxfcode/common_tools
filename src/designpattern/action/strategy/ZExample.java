package designpattern.action.strategy;

import designpattern.action.strategy.impl.CarStrategy;
import designpattern.action.strategy.impl.TrainStrategy;

/**
 * The Class ZExample. 
 * 策略模式 
 * 策略模式中分成三种角色： 
 * 抽象策略角色：通常用一个抽象类或者接口来实现，主要是定义这个算法所完成的功能
 * 具体策略角色：包装了相关算法和行为 
 * 环境角色：持有策略类的引用
 * 
 * 这里以人们要旅游为例，
 * 旅游有多种方式，火车，汽车，步行都可以，
 * 不同的方式之间是可以替换的，都能完成旅游的目标。
 * 所以有
 * 抽象策略角色-旅行策略TravelStrategy
 * 具体策略角色-火车旅行策略TrainStrategy，汽车旅行策略CarStrategy
 * 环境角色-人Person
 * 
 * 
 * 
 * @date 2014-5-3 17:38:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		TravelStrategy ts1 = new CarStrategy();
		TravelStrategy ts2 = new TrainStrategy();
		Person p1 = new Person(ts1);
//		Person p1 = new Person(ts2);
		p1.doTravel();
	}

}
