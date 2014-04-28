package designpattern.create.builder;

import designpattern.create.builder.builder.CarBuilderBaoMa;
import designpattern.create.builder.builder.CarBuilderMini;
import designpattern.create.builder.director.CarDirector;
import designpattern.create.builder.product.Car;

/**
 * The Class BuilderExample.
 * 生成器模式，创建者模式. 
 * 该模式包含3个角色:生成器，产品和指导者,必须有指导者来协调进行工作.构建过程存在相似性. 
 * 
 * 从形式上来讲，通过角色合并，方法功能的转变，抽象工厂可以和生成器模式形式上取得一致
 * （比如抽象工厂只处理一个产品族，工厂方法都处理同一个产品）。但注意，这仅仅是形式上的，实际上，抽象工厂和生成器模式有着本质的区别：
 * 1、生成器模式是为了构造一个复杂的产品，而且购造这个产品遵循一定的规则（相同的过程），而抽象工厂则是为了创建成族的产品（系列产品），
 * 同族产品的构造在逻辑上并不存在必然的联系（唯一必然的联系就是大家都属于一族）。
 * 
 *  2、生成器模式的构造方法是为了构造同一个产品，因此必须有指导者来协调进行工作，构造方法之间存在必然的业务联系， * 而抽象工厂的构造方法都是独立去构建自己的产品对象
 * ，因此他们不存在必然的联系。在生成器模式中客户端不直接调用构建产品部分的方法来获取最终产品，而抽象工厂中客户端是通过调用不同的工厂方法获取不同的产品。
 * 
 * 3.在生成器模式中，那些用来构造产品不同部分的方法一般都实现为Protected形式，以防止客户端通过调用这种方法活得不可预料的结果，
 * 而抽象工厂中的这些方法必须为Public形式。否则客户无法调用来获得产品结果；
 * 
 * 4.生成器模式的角色有生成器，产品和指导者，而抽象工厂的角色有工厂和产品。无论角色和功能怎样变换
 * ，但所含的业务逻辑角色都应该存在，这也是两个模式的业务本质。
 *
 * @date 2014-4-28 12:48:55
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class BuilderExample {
	
	public static void main(String[] args) {
		CarDirector director = new CarDirector(new CarBuilderBaoMa());
		director.constructCar();
		Car car = director.getCar();
		System.out.println(car.toString());
		
		director = new CarDirector(new CarBuilderMini());
		director.constructCar();
		car = director.getCar();
		System.out.println(car.toString());
		
	}

}
