package designpattern.action.chainofresponsbility;

/**
 * The Class Response.
 *
 * @date 2014-5-7 23:00:54
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Response {
	/** name. */
	private String name;
	
	/**
	 * Instantiates a new Response.
	 *
	 * @param name comments
	 */
	public Response(String name){
		this.name = name;
	}

	/**
	 * Gets name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name comments
	 */
	public void setName(String name) {
		this.name = name;
	}

}
