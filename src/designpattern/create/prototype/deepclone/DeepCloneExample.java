package designpattern.create.prototype.deepclone;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class DeepCloneExample. 
 * 深度克隆示例对象， 深度克隆的对象需要实现序列化接口
 * 
 * 
 * @date 2014-4-24 20:14:00
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DeepCloneExample implements Cloneable,Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1702487071995830254L;
	private Long id;
	private String name;
	private String msg;
	private Date create;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create == null) ? 0 : create.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		DeepCloneExample other = (DeepCloneExample) obj;
		if (create == null) {
			if (other.create != null)
				return false;
		} else if (!create.equals(other.create))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeepCloneExample [id=" + id + ", name=" + name + ", msg=" + msg
				+ ", create=" + create + "]";
	}
	
	public String toString2() {
		return "DeepCloneExample [id=" + id.hashCode() + ", name=" + name.hashCode() + ", msg=" + msg.hashCode()
				+ ", create=" + create.hashCode() + "]";
	}

	public DeepCloneExample(Long id, String name, String msg, Date create) {
		super();
		this.id = id;
		this.name = name;
		this.msg = msg;
		this.create = create;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DeepCloneExample a = new DeepCloneExample(new Long(1), new String("deep1"), new String("haha"), new Date());
		DeepCloneExample b = CloneUtils.cloneObj(a);
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		System.out.println(a.toString2());
		System.out.println(b.toString2());
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
	}
	
}
