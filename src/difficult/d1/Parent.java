package difficult.d1;

public abstract class Parent {
	@SuppressWarnings("unused")
	private int x = 100;

	public Parent() {
		System.out.println("parent construct");
		print();
		
	}
	
	abstract void print();
}
