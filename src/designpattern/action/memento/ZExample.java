package designpattern.action.memento;

/**
 * The Class ZExample.
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
