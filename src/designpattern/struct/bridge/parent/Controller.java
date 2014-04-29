package designpattern.struct.bridge.parent;

import designpattern.struct.bridge.child.TV;

/**
 * The Class Controller.
 * 遥控器接口
 *
 * @date 2014-4-29 12:28:11
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public abstract class Controller {
	private TV tv;
	public Controller(TV tv){
		this.tv = tv;
	}
	public void turnOn(){
		tv.on();
	}
	public void turnOff(){
		tv.off();
	}
	public void setChannel(int number){
		tv.switchChannel(number);
	}
}
