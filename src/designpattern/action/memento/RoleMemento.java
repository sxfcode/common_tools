package designpattern.action.memento;

/**
 * The Class Memento.
 *
 * @date 2014-5-12 21:18:07
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class RoleMemento {
	/** 金钱. */
	private int money =0;
	
	/** 级别. */
	private int level=0;
	
	/** 生命值. */
	private int heath = 0;
	

	public RoleMemento(int money, int level, int heath) {
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
	
	

}
