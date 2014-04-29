package designpattern.struct.bridge.child;

/**
 * The Interface TV.
 *  电视机接口
 *  
 * @date 2014-4-29 12:28:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface TV {
	
	/**
	 * on.
	 */
	public void on();
	
	/**
	 * off.
	 */
	public void off();
	
	/**
	 * switchChannel.
	 *
	 * @param number comments
	 */
	public void switchChannel(int number);
}
