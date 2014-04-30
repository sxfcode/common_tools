package designpattern.struct.flyweight;

/**
 * The Class Car.
 * 抽象享元角色.
 *
 * @date 2014-5-1 3:22:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public  final class Car {
	
	/** 车牌号码(京A0001等). */
	private String carNumber;
	
	/** 车子型号（宝马，奔驰。。。想多了）. */
	private String carType;
	
	
	public Car(String carNumber, String carType) {
		super();
		this.carNumber = carNumber;
		this.carType = carType;
	}
	/**
	 * 驾驶员，在运行时由外部提供。
	 *
	 * @param driverName comments
	 */
	public void start(String driverName){
		System.out.println(driverName+"启动编号为"+carNumber+"的"+carType+"车辆");
	}
	public void stop(String driverName){
		System.out.println(driverName+"关闭编号为"+carNumber+"的"+carType+"车辆");
	} 

}
