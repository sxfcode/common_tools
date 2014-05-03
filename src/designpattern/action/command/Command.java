package designpattern.action.command;

/**
 * The Interface Command.
 *  抽象命令。
 * @date 2014-5-2 13:46:14
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface Command {
	
	/**
	 * exe.
	 */
	public void exe();
	
	public void unexe();
}
