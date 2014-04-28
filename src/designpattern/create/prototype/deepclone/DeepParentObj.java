package designpattern.create.prototype.deepclone;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Class DeepCloneExample.
 *  深度克隆示例对象， 深度克隆的对象及其成员对象需要实现序列化接口
 * 此克隆方式存在性能问题,大概需要几十秒的执行时间。
 * 
 * 通过修改克隆对象的属性值，检查被克隆对象的对应值是否被修改，来鉴定是否是深度clone. hashCode不能作为内存地址的对应，因为已经被改写过了。
 * 通过读写流克隆出来的对象，hashcode与原对象相同。
 * 
 * 
 * @date 2014-4-24 20:14:00
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("rawtypes")
public class DeepParentObj implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1702487071995830254L;
	private int id;
	private String name;
	private Date createTime;
	private Long price;
	private List<String> data;
	private List comData;
	private DeepCustomObj customObj;

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

	public DeepCustomObj getCustomObj() {
		return customObj;
	}

	public void setCustomObj(DeepCustomObj customObj) {
		this.customObj = customObj;
	}

	

	@Override
	public String toString() {
		return "DeepParentObj [id=" + id + ", name=" + name + ", createTime="
				+ createTime + ", price=" + price + ", data=" + data
				+ ", comData=" + comData + ", customObj=" + customObj + "]";
	}

	public DeepParentObj(int id, String name, List<String> data,
			List comData, Date createTime, Long price, DeepCustomObj customObj) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.comData = comData;
		this.createTime = createTime;
		this.price = price;
		this.customObj = customObj;
	}
	
}
