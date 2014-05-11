package designpattern.action.chainofresponsbility;

/**
 * The Class Request.
 *
 * @date 2014-5-7 23:00:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Request {
	
	/** name. */
	private String name;
	
	public Request(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
