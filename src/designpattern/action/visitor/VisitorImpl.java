package designpattern.action.visitor;

public class VisitorImpl implements Visitor {

	@Override
	public void visit(Visitable visitable) {
		System.out.println("正在访问"+visitable.toString());
	}
}
