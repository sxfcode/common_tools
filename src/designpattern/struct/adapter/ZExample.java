package designpattern.struct.adapter;

import designpattern.struct.adapter.implements2.PS2ToUSB2;

/**
 * The Class AdapterExample.
 * 适配器模式。
 * 最经典的例子，转接头。
 * 适配器模式将一个接口转换成客户希望的另外一个接口。它使得原来由于接口不兼容而不能在一起工作的那些类可以一起工作。
 * 
 * 两种实现适配器模式的实现方式
 * 1.通过java的多实现来实现适配器模式,如PS2ToUSB1
 * 2.通过保持被适配目标的引用来实现适配器模式,如PS2ToUSB2
 * 
 * @date 2014-5-1 1:43:24
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		// 1.我有一个ps口
		PS2Port ps2port = new PS2Port() {
			@Override
			public void workWithPS2() {
				System.out.println("ps2口正在工作");
			}
		};
		
		// 2.通过适配器模式获取USB接口
		USBPort usbPort = new PS2ToUSB2(ps2port);
		//USBPort usbPort = new PS2ToUSB1();
		// 3.usb接口工作
		usbPort.workWithUSB();
	}

}
