package designpattern.create.factory.abstracfc;

public class Test {

	public Test() {
	}
	public static void main(String[] args) {
		IFactory factory = new FactoryA();
		IProduct pA = factory.createProduct();
		System.out.println(pA.getName());
		
		factory = new FactoryB();
		IProduct pB = factory.createProduct();
		System.out.println(pB.getName());
	}

}
