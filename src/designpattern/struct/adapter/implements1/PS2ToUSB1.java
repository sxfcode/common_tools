package designpattern.struct.adapter.implements1;

import designpattern.struct.adapter.PS2Port;
import designpattern.struct.adapter.USBPort;

/**
 * The Class PS2ToUSB.
 * 通过java的多实现来实现适配器模式
 * 
 * @date 2014-5-1 1:47:43
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class PS2ToUSB1 implements PS2Port, USBPort {
	

	/* (non-Javadoc)
	 * @see designpattern.struct.adapter.USBPort#workWithUSB()
	 */
	@Override
	public void workWithUSB() {
		workWithPS2();
		System.out.println("已经转换成USB模式工作");
	}
	
	/* (non-Javadoc)
	 * @see designpattern.struct.adapter.PS2Port#workWithPS2()
	 */
	@Override
	public void workWithPS2() {
		System.out.println("PS2口正在工作");
	}
}
