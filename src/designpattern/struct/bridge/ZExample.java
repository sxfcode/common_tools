package designpattern.struct.bridge;

import designpattern.struct.bridge.child.SonyTV;
import designpattern.struct.bridge.child.TV;
import designpattern.struct.bridge.parent.Controller;
import designpattern.struct.bridge.parent.HuaWeiController;

/**
 * The Class BridgeExample.
 *  桥接模式的要点是解耦抽象层和实现层，
 *  实现层根据实际需要选择抽象成进行实现，同时与原抽象层保持一致。
 *  
 *  抽象共同部分和行为共同部分。
 *  
 *  
 * @date 2014-4-28 13:36:17
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		TV tv = new SonyTV();
		Controller ct = new HuaWeiController(tv);
		ct.turnOn();
		ct.setChannel(01);
	}

}
