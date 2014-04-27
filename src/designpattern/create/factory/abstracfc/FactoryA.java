package designpattern.create.factory.abstracfc;

public class FactoryA implements IFactory {

	@Override
	public IProduct createProduct() {
		return new ProductA();
	}

}
