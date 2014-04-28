package designpattern.create.builder.product;

/**
 * The Class Car.
 * 创建者模式-角色-产品
 *
 * @date 2014-4-28 12:56:32
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Car {
	private String body;
	private String wheel;
	private String engine;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getWheel() {
		return wheel;
	}
	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	@Override
	public String toString() {
		return "Car [body=" + body + ", wheel=" + wheel + ", engine=" + engine
				+ "]";
	}
	
}
