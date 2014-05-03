package designpattern.action.strategy;

/**
 * The Class Person.
 *
 * @date 2014-5-3 17:47:57
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Person {
	
	public TravelStrategy ts;
	
	public Person(TravelStrategy ts){
		this.ts = ts;
	}
	public void doTravel(){
		ts.travel();
	}
}
