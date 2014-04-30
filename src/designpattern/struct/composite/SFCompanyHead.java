package designpattern.struct.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SFCompanyHead.
 * 顺丰子公司（有下属公司）,例如投送基站的上级公司，上上级公司等。
 *
 * @date 2014-5-1 2:23:01
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class SFCompanyHead extends SFCompany {
	
	private List<SFCompany> children = new ArrayList<SFCompany>();

	public SFCompanyHead(String name, int grade) {
		super(name, grade);
	}

	// 实际的添加和删除可能需要增加很多逻辑判断，这里只做演示用。
	@Override
	public void add(SFCompany c) {
		children.add(c);
	}

	@Override
	public void remove(SFCompany c) {
		children.remove(c);
	}

	@Override
	public void display() {
		super.display();
		for (SFCompany com : children) {
			com.display();
		}
	}

}
