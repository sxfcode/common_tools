package designpattern.create.prototype.deepclone;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class CustomObj.
 *
 * @date 2014-4-24 15:02:50
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DeepCustomObj implements Serializable{
	
	private static final long serialVersionUID = -2439203612480513662L;
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
		return "DeepCustomObj [id=" + id + ", userName=" + userName
				+ ", created=" + created + "]";
	}
}
