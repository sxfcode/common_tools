package designpattern.action.state.impl;

import designpattern.action.state.MobilePhone;
import designpattern.action.state.State;

/**
 * The Class WaitState.
 * 待命状态
 * 
 * @date 2014-5-11 23:48:15
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class WaitState implements State {
	
	private MobilePhone mp;
	

	public WaitState(MobilePhone mp){
		this.mp = mp;
	}
	
	@Override
	public void call() {
		System.out.println("接通电话");
		System.out.println("状态变更为接通状态");
		mp.changeState(mp.getConnectState());
	}
}
