package jdk.detail.xml;

/**
 * The Class UserBean.
 * JAXP即 java api for xml process.
 * SAX 即simple api for xml.轻量级，事件驱动。
 *  
 * @date 2013-7-8 15:57:54
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class UserBean {
	
	/** id. */
	private String id;
	
	/** name. */
	private String name;
	
	public UserBean(){
	}
	
	public UserBean(String id,String name){
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id comments
	 */
	public void setId(String id) {
		this.id = id;
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
