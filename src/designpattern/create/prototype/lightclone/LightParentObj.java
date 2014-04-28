package designpattern.create.prototype.lightclone;

import java.util.Date;
import java.util.List;

/**
 * The Class DeepCloneExample. 
 * 浅克隆，对象及其成员对象需要实现clone接口和重写clone方法
 * 浅克隆只复制了成员对象的引用，需要慎重使用。
 * 
 * 
 * @date 2014-4-24 20:14:00
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("rawtypes")
public class LightParentObj implements Cloneable {

	/** The Constant serialVersionUID. */
	private int id;
	private String name;
	private Date createTime;
	private Long price;
	private List<String> data;
	private List comData;
	private LightCustomObj customObj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public List getComData() {
		return comData;
	}

	public void setComData(List comData) {
		this.comData = comData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public LightCustomObj getCustomObj() {
		return customObj;
	}

	public void setCustomObj(LightCustomObj customObj) {
		this.customObj = customObj;
	}

	@Override
	public String toString() {
		return "LightParentObj [id=" + id + ", name=" + name + ", createTime="
				+ createTime + ", price=" + price + ", data=" + data
				+ ", comData=" + comData + ", customObj=" + customObj + "]";
	}

	public LightParentObj(int id, String name, List<String> data, List comData,
			Date createTime, Long price, LightCustomObj customObj) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.comData = comData;
		this.createTime = createTime;
		this.price = price;
		this.customObj = customObj;
	}
	
	public LightParentObj clone() {
		LightParentObj clone = null;
		try {
			clone = (LightParentObj) super.clone();
		} catch (CloneNotSupportedException e) {
			// 首先打印异常，然后将异常抛出给外层。
			e.printStackTrace();
			throw new RuntimeException("clone异常");
		}

		return clone;
	}
}
