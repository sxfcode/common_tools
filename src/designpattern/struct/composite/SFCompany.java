package designpattern.struct.composite;

/**
 * The Class SFCompany. 单个对象与组合对象的公共部分。
 * 
 * 抽象公司。 顺丰公司为例。
 * 
 * @date 2014-5-1 2:15:50
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public abstract class SFCompany {
	private String name;
	private int grade;

	public SFCompany(String name, int grade) {
		super();
		this.name = name;
		this.grade = grade;
	}

	public abstract void add(SFCompany c);
	public abstract void remove(SFCompany c);

	public void display() {
		for (int i = 1; i < grade; i++) {
			System.out.print("----");
		}
		System.out.println(name);
	}

}
