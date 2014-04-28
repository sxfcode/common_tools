package designpattern.create.prototype.lightclone;

import java.util.Date;

/**
 * The Class CustomObj.
 *
 * @date 2014-4-24 15:02:50
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class LightCustomObj implements Cloneable{

	private String id;
	private String userName;
	private Date created;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "CustomObj [id=" + id + ", userName=" + userName + ", created="
				+ created + "]";
	}
	
	public LightCustomObj clone() {
		LightCustomObj clone = null;
		try {
			clone = (LightCustomObj) super.clone();
		} catch (CloneNotSupportedException e) {
			// 首先打印异常，然后将异常抛出给外层。
			e.printStackTrace();
			throw new RuntimeException("clone异常");
		}

		return clone;
	}
}
