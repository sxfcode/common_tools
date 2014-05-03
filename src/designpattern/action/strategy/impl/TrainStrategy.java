package designpattern.action.strategy.impl;

import designpattern.action.strategy.TravelStrategy;

/**
 * The Class TrainStrategy.
 *
 * @date 2014-5-3 17:42:02
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class TrainStrategy implements TravelStrategy {

	/* (non-Javadoc)
	 * @see designpattern.action.strategy.TravelStrategy#travel()
	 */
	@Override
	public void travel() {
		System.out.println("坐车旅游，省钱，便捷。");
	}

}
