package designpattern.create.factory.abstracfc;

public class FactoryB implements IFactory {

	public FactoryB() {
	}

	@Override
	public IProduct createProduct() {
		return new ProductB();
	}

}
