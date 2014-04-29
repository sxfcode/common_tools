package designpattern.struct.bridge.child;

public class SamsumTV implements TV {

	@Override
	public void on() {
		System.out.println("samsum on");
	}

	@Override
	public void off() {
		System.out.println("samsum off");
	}

	@Override
	public void switchChannel(int number) {
		System.out.println("samsum switchChannel"+number);
	}

}
