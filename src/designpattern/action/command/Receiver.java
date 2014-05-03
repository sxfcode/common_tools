package designpattern.action.command;

public class Receiver {
	public void sendMessage(){
		System.out.println("接收者执行命令");
	}
	public void unsendMessage(){
		System.out.println("接收者取消命令");
	}
}
