package designpattern.create.prototype;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import designpattern.create.prototype.deepclone.CloneUtils;
import designpattern.create.prototype.lightclone.CustomObj;

/**
 * The Class PrototypeObj. 原型模式 ==================================
 * 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * Prototype原型模式是一种创建型设计模式,Prototype模式允许一个对象再创建另外一个可定制的对象， 根本无需知道任何如何创建的细节,
 * 工作原理是:通过将一个原型对象传给那个要发动创建的对象 ，这个要发动创建的对象通过请求原型对象拷贝它们自己来实施创建。
 * 
 * 解决什么问题： 它主要面对的问题是：“某些结构复杂的对象”的创建工作；由于需求的变化，这些对象经常面临着剧烈的变化， 但是他们却拥有比较稳定一致的接口。
 * 因为Java中的提供clone()方法来实现对象的克隆，所以Prototype模式实现一下子变得很简单.
 * 
 * 克隆必须满足的条件： a.对任何的对象x,都有：x.clone() != x,即克隆对象与原对象不是同一个对象。
 * b.对任何的对象x,都有：x.clone().getClass() == x.get getClass()，即克隆对象与原对象的类型是一样的。
 * c.如果对象x的equals()方法定义恰当的话，那么x.clone().equals(x)应当是成立的。
 * 在java中实现clone()应该满足这三个条件。
 * 
 * 浅复制：复制了值类型对象，对于引用类型对象，只复制了引用，它指向原来引用的对象。Java中clone为浅复制。
 * 深复制：对值类型和引用类型的对象都生成一份新的拷贝.
 * Java中可以通过串行化来进行深复制,前提是对象以及对象内部所引用的对象都是可串行化的，否则需要考虑将那些不可串行化的对象可否设为transient,排除
 * 在复制过程之外。
 * 
 * 关于java的clone java对象中只要各字段为实现cloneable接口，即可自动对该对象实现深度copy
 * ================================= ===================================
 * 使用场景
 * 有的时候我们可能在实际的项目中需要一个对象在某个状态下的副本，这个前提很重要，这点怎么理解呢，
 * 例如有的时候我们需要对比一个对象过处理后的状态和处理前的状态是否发生过改变，
 * 可能我们就需要在执行某段处理之前，克隆这个对象此时状态的副本，然后等执行后的状态进行相应的对比，
 * 这样的应用在项目中也是经常会出现的。
 * 
 * 
 * @date 2014-4-24 14:06:28
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class PrototypeObj implements Cloneable,Serializable {

	private int id;
	private String name;
	private List<String> data;
	private List comData;
	private Date createTime;
	private Long price;
	private CustomObj customObj;

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

	public CustomObj getCustomObj() {
		return customObj;
	}

	public void setCustomObj(CustomObj customObj) {
		this.customObj = customObj;
	}

	@Override
	public String toString() {
		return "PrototypeObj [id=" + id + ", name=" + name + ", data=" + data
				+ ", comData=" + comData + ", createTime=" + createTime
				+ ", price=" + price + ", customObj=" + customObj + "]";
	}

	public String toString2() {
		return "PrototypeObj [id=" + id + ", name=" + name.hashCode()
				+ ", data=" + data.hashCode() + ", comData="
				+ comData.hashCode() + ", createTime=" + createTime.hashCode()
				+ ", price=" + price.hashCode() + ", customObj="
				+ customObj.hashCode() + "]";
	}

	/**
	 * Instantiates a new PrototypeObj.
	 */
	public PrototypeObj(int a, String b) {
		this.id = a;
		this.name = b;
	}

	public PrototypeObj clone() {
		PrototypeObj clone = null;
		try {
			clone = (PrototypeObj) super.clone();
		} catch (CloneNotSupportedException e) {
			// 首先打印异常，然后将异常抛出给外层。
			e.printStackTrace();
			throw new RuntimeException("clone异常");
		}

		return clone;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<String> data = new ArrayList<String>();
		data.add("test1");
		data.add("test2");
		List comData = new ArrayList();
		Map m = new HashMap();
		m.put("1", "2");
		comData.add("haha");
		comData.add(m);

		CustomObj cus = new CustomObj();
		cus.setUserName("CustomObj");

		PrototypeObj a = new PrototypeObj(1, "test");
		a.setData(data);
		a.setComData(comData);
		a.setCreateTime(new Date());
		a.setCustomObj(cus);
		a.setPrice(new Long(11L));
//		PrototypeObj b = a.clone();
		PrototypeObj b = CloneUtils.cloneObj(a);	
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(a.toString2());
		System.out.println(b.toString2());
		System.out.println(PrototypeObj.checkClone(a, b));

		String s1 = "test1";
		String s2 = s1;

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1);

	}

	public static boolean checkClone(Object a, Object b) {
		boolean result = false;
		System.out.println((a != b) + "," + (a.getClass() == b.getClass())
				+ "," + (a.equals(b)));
		if (a != b && a.getClass() == b.getClass() && a.equals(b)) {
			result = true;
		}
		return result;
	}

}

