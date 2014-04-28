package designpattern.create.prototype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import designpattern.create.prototype.deepclone.CloneUtils;
import designpattern.create.prototype.deepclone.DeepCustomObj;
import designpattern.create.prototype.deepclone.DeepParentObj;
import designpattern.create.prototype.lightclone.LightCustomObj;
import designpattern.create.prototype.lightclone.LightParentObj;

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
 * ================================= =================================== 使用场景
 * 有的时候我们可能在实际的项目中需要一个对象在某个状态下的副本，这个前提很重要，这点怎么理解呢，
 * 例如有的时候我们需要对比一个对象过处理后的状态和处理前的状态是否发生过改变，
 * 可能我们就需要在执行某段处理之前，克隆这个对象此时状态的副本，然后等执行后的状态进行相应的对比， 这样的应用在项目中也是经常会出现的。
 * 
 * 
 * @date 2014-4-24 14:06:28
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class PrototypeObj {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		//deepClone();
		 lightClone();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static void deepClone() throws IOException, ClassNotFoundException {
		// 通过修改克隆对象的属性值，检查被克隆对象的对应值是否被修改，来鉴定是否是深度clone.
		// hashCode不能作为内存地址的对应，因为已经被改写过了。
		// 泛型集合
		List<String> data = new ArrayList<String>();
		data.add("test1");
		data.add("test2");
		// 非泛型集合及复杂对象。
		List comData = new ArrayList();
		Map m = new HashMap();
		m.put("1", "2");
		comData.add("haha");
		comData.add(m);

		// 自定义对象
		DeepCustomObj cus = new DeepCustomObj();
		cus.setUserName("CustomObj");

		// 原始对象
		DeepParentObj a = new DeepParentObj(1, "deepa", data, comData,
				new Date(), 2L, cus);
		a.setData(data);
		a.setComData(comData);
		a.setCreateTime(new Date());
		a.setCustomObj(cus);
		a.setPrice(new Long(1L));
		a.setName("name a");

		// 克隆对象
		DeepParentObj b = CloneUtils.cloneObj(a);
		// 修改克隆对象
		b.setId(2);
		b.getComData().add("testb");
		b.getCreateTime().setHours(10);
		b.getCustomObj().setUserName("CustomObj by b");
		b.getData().add("testb");
		b.setName("name b");
		b.setPrice(2L);

		System.out.println(a.toString());
		System.out.println(b.toString());

	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public static void lightClone() throws IOException, ClassNotFoundException {
		// 通过修改克隆对象的属性值，检查被克隆对象的对应值是否被修改，来鉴定是否是深度clone.
		// hashCode不能作为内存地址的对应，因为已经被改写过了。
		// 泛型集合
		List<String> data = new ArrayList<String>();
		data.add("test1");
		data.add("test2");
		// 非泛型集合及复杂对象。
		List comData = new ArrayList();
		Map m = new HashMap();
		m.put("1", "2");
		comData.add("haha");
		comData.add(m);

		// 自定义对象
		LightCustomObj cus = new LightCustomObj();
		cus.setUserName("CustomObj");

		// 原始对象
		LightParentObj a = new LightParentObj(1, "deepa", data, comData,
				new Date(), 2L, cus);
		a.setData(data);
		a.setComData(comData);
		a.setCreateTime(new Date());
		a.setCustomObj(cus);
		a.setPrice(new Long(1L));
		a.setName("name a");

		// 克隆对象
		LightParentObj b = a.clone();
		// 修改克隆对象
		b.setId(2);
		b.getComData().add("testb");
		b.getCreateTime().setHours(10);
		b.getCustomObj().setUserName("CustomObj by b");
		b.getData().add("testb");
		b.setName("name b");
		b.setPrice(2L);

		System.out.println(a.toString());
		System.out.println(b.toString());

	}
}
