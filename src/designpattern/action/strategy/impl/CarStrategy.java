package designpattern.action.strategy.impl;

import designpattern.action.strategy.TravelStrategy;

public class CarStrategy implements TravelStrategy {

	@Override
	public void travel() {
		System.out.println("开车旅游，走走停停，非常自由");
	}

}
