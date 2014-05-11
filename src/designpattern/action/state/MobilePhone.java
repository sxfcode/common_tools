package designpattern.action.state;

import designpattern.action.state.impl.ConnectState;
import designpattern.action.state.impl.WaitState;

/**
 * The Class MobilePhone.
 *
 * @date 2014-5-11 23:50:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class MobilePhone {
	private State waitState = new WaitState(this);
	private State connectState = new ConnectState(this);
	
	private State state = waitState;
	
	public void doCall(){
		state.call();
	}
	public void changeState(State state){
		this.state = state;
	}
	public State getWaitState() {
		return waitState;
	}
	public void setWaitState(State waitState) {
		this.waitState = waitState;
	}
	public State getConnectState() {
		return connectState;
	}
	public void setConnectState(State connectState) {
		this.connectState = connectState;
	}

}
