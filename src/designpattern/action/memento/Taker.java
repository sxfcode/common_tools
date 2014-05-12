package designpattern.action.memento;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class Taker.
 *
 * @date 2014-5-12 21:52:33
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Taker {
	Map<String,RoleMemento> rms = new HashMap<String,RoleMemento>();
	public void putMeme(String name,RoleMemento rm){
		rms.put(name, rm);
	}
	
	public RoleMemento getMeme(String name){
		return rms.get(name);
	}
}
