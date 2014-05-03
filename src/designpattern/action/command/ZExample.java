package designpattern.action.command;

/**
 * The Class ZExample.
 * 命令模式，
 * 个人认为，命令对象应该是一个包含了具体请求信息的对象，接收者根据这些请求信息来执行命令。
 *  将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；
 *  对请求排队或记录请求日志，以及支持可撤消的操作。
 * 
 * 
 * 玉帝传美猴王上天，玉帝创建了一个命令就是圣旨，然后指出圣旨的接受者美猴王，而太白金星只是传达命令的人。这个过程就是命令模式的应用。
 * 
 * @date 2014-5-2 14:05:21
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		Receiver re = new Receiver();
		Command command = new CommandImpl(re);
		Invoker invoker = new Invoker(command);
		invoker.exeCommand();
	}

}
