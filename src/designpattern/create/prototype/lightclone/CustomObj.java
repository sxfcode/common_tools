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
public class CustomObj implements Cloneable {

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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomObj other = (CustomObj) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	public CustomObj clone() {
		CustomObj clone = null;
		try {
			clone = (CustomObj) super.clone();
		} catch (CloneNotSupportedException e) {
			// 首先打印异常，然后将异常抛出给外层。
			e.printStackTrace();
			throw new RuntimeException("clone异常");
		}

		return clone;
	}
	
	

}
