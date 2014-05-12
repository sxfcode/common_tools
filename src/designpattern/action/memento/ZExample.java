package designpattern.action.memento;

/**
 * The Class ZExample. 
 * 备忘录模式 
 * 1) 备忘录（Memento）角色：备忘录角色存储“备忘发起角色”的内部状态。“备忘发起角色”根据需要决定备忘录角色存储
 * “备忘发起角色”的哪些内部状态。为了防止“ 备忘发起角色”以外的其他对象访问备忘录。
 *   备忘录实际上有两个接口，“备忘录管理者角色”只能看到备忘录提供的窄接口——
 *   对于备忘录角色中存放的属性是不可见的。“备忘发起角色”则能够看到一个宽接口——能够得到自己放入备忘录角色中属性。
 * 
 * 2) 备忘发起（Originator）角色：“备忘发起角色”创建一个备忘录，用以记录当前时刻它的内部状态。在需要时使用备忘录恢复内部状态。
 * 
 * 3) 备忘录管理者（Caretaker）角色：负责保存好备忘录。不能对备忘录的内容进行操作或检查。
 * 
 * 
 * @date 2014-5-12 21:13:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		// 初始化
		PlayRole role = new PlayRole(0, 0, 0);
		// 打怪升级
		role.setHeath(100);
		role.setLevel(50);
		role.setMoney(10000);
		// 管理者
		Taker taker = new Taker();
		// 打boss前存档
		taker.putMeme("01", role.createMeme());
		System.out.println("存档:");
		System.out.println(role.toString());

		// 打boss失败，数值归零
		role.setHeath(0);
		role.setLevel(0);
		role.setMoney(0);
		System.out.println("打boss失败:");
		System.out.println(role.toString());

		// 读取存档
		role.setMeme(taker.getMeme("01"));
		System.out.println("读取存档:");
		System.out.println(role.toString());
	}

}
