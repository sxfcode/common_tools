package designpattern.struct.flyweight;

/**
 * The Class FlyWeightExample. 享元模式。 享元模式的核心是通过共享对象来避免资源浪费。
 * 
 * 这里我们以学生到驾校学开车为例，驾校不能为每一个学生都买一部车，
 * 驾校通过车库每次有学员要学车，就从车库提1辆车出来，共学员练习，所有学员共享驾校车库中的车。
 * 驾校车库中的车辆是固定的。
 * 这里驾校车库中的车就是享元，
 * 学车时的学员就是外部状态，是可变的。
 * 车子的车牌，型号，颜色等就是内部状态保持不变。
 * 
 * 享元模式采用一个共享来避免大量拥有相同内容对象的开销。
 * 
 * 这种开销最常见、最直观的就是内存的损耗。享元对象能做到共享的关键是区分内蕴状态(Internal State)和外蕴状态(External State)。
 * 　　一个内蕴状态是存储在享元对象内部的，并且是不会随环境的改变而有所不同。因此，一个享元可以具有内蕴状态并可以共享。
 * 　　一个外蕴状态是随环境的改变而改变的、不可以共享的。享元对象的外蕴状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候再传入到享元对象内部。
 * 外蕴状态不可以影响享元对象的内蕴状态，它们是相互
 * 
 * 单纯享元模式所涉及到的角色如下：
 * 
 * 　　●　　抽象享元(Flyweight)角色 ：给出一个抽象接口，以规定出所有具体享元角色需要实现的方法。
 * 
 * 　　●　　具体享元(ConcreteFlyweight)角色：实现抽象享元角色所规定出的接口。如果有内蕴状态的话，必须负责为内蕴状态提供存储空间。
 * 
 * 　　●　　享元工厂(FlyweightFactory)角色
 * ：本角色负责创建和管理享元角色。本角色必须保证享元对象可以被系统适当地共享。当一个客户端对象调用一个享元对象的时候
 * ，享元工厂角色会检查系统中是否已经有一个符合要求的享元对象
 * 。如果已经有了，享元工厂角色就应当提供这个已有的享元对象；如果系统中没有一个适当的享元对象的话，享元工厂角色就应当创建一个合适的享元对象。
 * 
 * @date 2014-5-1 3:08:55
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		DrivingSchool sc = DrivingSchool.getInstance();
		Car c1 = sc.pickCar("京A001");
		c1.start("张三");
		c1.stop("张三");
		
		c1.start("李四");
		c1.stop("李四");
		
		Car c2 = sc.pickCar("京A003");
		c2.start("张三");
		c2.stop("张三");
		
		c2.start("李四");
		c2.stop("李四");

	}

}
