package designpattern.struct.composite;

/**
 * The Class SFCompanyTail.
 * 
 * 顺丰子公司（无下属公司）,可以理解为投送基站。
 *
 * @date 2014-5-1 2:21:58
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class SFCompanyTail extends SFCompany {

	/**
	 * Instantiates a new SFCompanyTail.
	 *
	 * @param name comments
	 * @param grade comments
	 */
	public SFCompanyTail(String name, int grade) {
		super(name, grade);
	}

	/* (non-Javadoc)
	 * @see designpattern.struct.composite.SFCompany#add(designpattern.struct.composite.SFCompany)
	 */
	@Override
	public void add(SFCompany c) {
	}

	/* (non-Javadoc)
	 * @see designpattern.struct.composite.SFCompany#remove(designpattern.struct.composite.SFCompany)
	 */
	@Override
	public void remove(SFCompany c) {
	}

}
