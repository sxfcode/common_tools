package jdk.newfeatures.jdk15;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ForEach.
 *
 * @date 2014-7-17 14:58:47
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class ForEach {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		for (String name : list) {
			System.out.println(name);
		}
	}

}
