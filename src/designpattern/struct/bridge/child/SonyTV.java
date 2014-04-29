package designpattern.struct.bridge.child;

public class SonyTV implements TV {

	@Override
	public void on() {
		System.out.println("sony on");
	}

	@Override
	public void off() {
		System.out.println("sony off");
	}

	@Override
	public void switchChannel(int number) {
		System.out.println("sony switchChannel"+number);
	}
	
}
