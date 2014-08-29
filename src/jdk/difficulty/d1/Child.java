package jdk.difficulty.d1;

public class Child extends Parent {

	private int x = 200;
	public Child(int x) {
		System.out.println("child construct");
		this.x = x;
		this.print();
	}

	@Override
	void print() {
		System.out.println("chid print x ="+x);
	}

}
