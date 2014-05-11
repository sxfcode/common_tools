package designpattern.action.state.impl;

import designpattern.action.state.MobilePhone;
import designpattern.action.state.State;

/**
 * The Class ConnectState.
 * 连通状态
 *
 * @date 2014-5-11 23:48:27
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ConnectState implements State {
	
    @SuppressWarnings("unused")
	private MobilePhone mp;
	
	public ConnectState(MobilePhone mp){
		this.mp = mp;
	}

	@Override
	public void call() {
		System.out.println("已在通话中无法连接");
	}

}
