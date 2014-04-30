package designpattern.struct.adapter.implements2;

import designpattern.struct.adapter.PS2Port;
import designpattern.struct.adapter.USBPort;


/**
 * The Class PS2ToUSB2.
 * 通过保持被适配目标的引用来实现适配器模式
 *
 * @date 2014-5-1 1:53:18
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class PS2ToUSB2 implements USBPort {
	
	/** ps2port. */
	private PS2Port ps2port;
	
	/**
	 * Instantiates a new PS2ToUSB2.
	 *
	 * @param ps2port comments
	 */
	public PS2ToUSB2(PS2Port ps2port){
		this.ps2port =ps2port;
	}

	/* (non-Javadoc)
	 * @see designpattern.struct.adapter.USBPort#workWithUSB()
	 */
	@Override
	public void workWithUSB() {
		ps2port.workWithPS2();
		System.out.println("已经转换成USB模式工作");
	}

}
