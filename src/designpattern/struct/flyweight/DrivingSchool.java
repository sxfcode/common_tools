package designpattern.struct.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class DrivingSchool. 
 * 这是一个工厂类。用来生成享元。这里用来从仓库提取汽车
 * 
 * @date 2014-5-1 3:29:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class DrivingSchool {
	
	/** The Constant cars. */
	private final static Map<String,Car> cars = new HashMap<String,Car>();
	
	/** The Constant instance. */
	private final static DrivingSchool instance= new DrivingSchool();
	
	/**
	 * Instantiates a new DrivingSchool.
	 */
	private DrivingSchool(){
		// 汽车入库
		cars.put("京A001", new Car("京A001", "宝马"));
		cars.put("京A002", new Car("京A002", "宝马"));
		cars.put("京A003", new Car("京A003", "奔驰"));
		cars.put("京A004", new Car("京A004", "法拉利"));

	}
	
	/**
	 * Gets the single instance of DrivingSchool.
	 *
	 * @return single instance of DrivingSchool
	 */
	public static DrivingSchool getInstance(){
		return instance;
	}
	
	/**
	 * 提取车辆.
	 *
	 * @param carNumber 车牌号
	 * @return Car
	 */
	public  Car pickCar(String carNumber){
		return cars.get(carNumber);
	}
}
