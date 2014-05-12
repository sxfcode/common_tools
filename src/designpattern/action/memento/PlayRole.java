package designpattern.action.memento;

/**
 * The Class PlayRole.
 *
 * @date 2014-5-12 21:17:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class PlayRole {
	
	/** 金钱. */
	private int money =0;
	
	/** 级别. */
	private int level=0;
	
	/** 生命值. */
	private int heath = 0;

	public PlayRole(int money, int level, int heath) {
		super();
		this.money = money;
		this.level = level;
		this.heath = heath;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHeath() {
		return heath;
	}

	public void setHeath(int heath) {
		this.heath = heath;
	}
	
	public RoleMemento createMeme(){
		RoleMemento m = new RoleMemento(this.money,this.level,this.heath);
		return m;
	}
	public void setMeme(RoleMemento rm){
		this.money = rm.getMoney();
		this.level = rm.getLevel();
		this.heath = rm.getHeath();
	}

	@Override
	public String toString() {
		return "当前角色状态 [金钱=" + money + ", 等级=" + level + ", 生命值="
				+ heath + "]";
	}
	
	

}
