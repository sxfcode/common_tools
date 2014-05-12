package designpattern.action.mediator;

import designpattern.action.mediator.components.CPU;
import designpattern.action.mediator.components.Disk;
import designpattern.action.mediator.components.Memory;
import designpattern.action.mediator.components.Power;
import designpattern.action.mediator.components.VideoCard;

/**
 * The Class MainBoard.
 *  主板协调所有配件，并提供各配件间的通信。
 * @date 2014-5-12 20:44:57
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class MainBoard {
	
	/** cpu. */
	private CPU cpu = new CPU();
	
	/** disk. */
	private Disk disk = new Disk();
	
	/** memory. */
	private Memory memory = new Memory();
	
	/** power. */
	private Power power = new Power();
	
	/** vc. */
	private VideoCard vc = new VideoCard();
	
	/**
	 * 开机.
	 */
	public void start(){
		// 系统家电
		power.doWork();
		cpu.doWork();
	    memory.doWork();
	    disk.doWork();
	    vc.doWork();
		
	}
	
	/**
	 * 关机.
	 */
	public void stop(){
		cpu.doWork();
	    memory.doWork();
	    disk.doWork();
	    vc.doWork();
	    power.doWork();
	}

}
