package designpattern.action.command;

/**
 * The Class CommandImpl.
 * 具体命令，
 * 在实际应用中，具体命令需要承载命令参数以及记录命令执行历史。
 * 这样命令才能正确的重复执行和取消。
 * 
 * @date 2014-5-2 14:24:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class CommandImpl implements Command{
	
	/** receiver. */
	private Receiver receiver;
	
	
	
	/**
	 * Instantiates a new CommandImpl.
	 *
	 * @param receiver comments
	 */
	public CommandImpl(Receiver receiver){
		this.receiver = receiver;
	}

	/* (non-Javadoc)
	 * @see designpattern.action.command.Command#exe()
	 */
	@Override
	public void exe() {
		receiver.sendMessage();
	}
	
	/* (non-Javadoc)
	 * @see designpattern.action.command.Command#unexe()
	 */
	public void unexe() {
		receiver.unsendMessage();
	}

}
