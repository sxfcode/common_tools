package designpattern.struct.bridge.parent;

import designpattern.struct.bridge.child.TV;

public class HuaWeiController extends Controller {

	public HuaWeiController(TV tv) {
		super(tv);
	}

	// 个性化调整接口
	@Override
	public void turnOn() {
		super.turnOn();
		System.out.println("这里是华为 turn on");
	}

	@Override
	public void turnOff() {
		super.turnOff();
		System.out.println("这里是华为 turn off");
	}

	@Override
	public void setChannel(int number) {
		super.setChannel(number);
		System.out.println("这里是华为 setChannel");
	}
	

}
