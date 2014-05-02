package designpattern.struct.composite;

/**
 * The Class CompositeExample. 
 * 将对象组合成树形结构以表示“部分-整体”的层次结构。Composite使得用户对单个对象和组合对象的使用具有一致性。
 * 
 * 组合模式。
 * 例如经典的文件系统，File既可表示文件夹，也可表示文件。再比如公司架构。
 * 
 * 将对象组合成树形结构以表示“部分-整体”的层次结构。Composite使得用户对单个对象和组合对象的使用具有一致性。
 * 
 * 这里以顺丰公司为例。 
 * 网上购物的时候，我们会发现，快递时一层一层下来的，我在北京朝阳区六里屯街道买了一个东西，物品会依次经过六里屯街道-朝阳区-深圳-
 * 深圳南山这样的顺序邮寄到我手里我们可以看出，每个地区都会有一个大的投递点，然后再往下细分。从中，我抽象了这么几个类。
 * 说明：顺丰快递总公司实际在深圳，但为了更清楚的说明组合模式，我将"顺丰公司"抽象出来作为一个公共的抽象公司。。
 * 
 * 
 * @date 2014-5-1 2:05:41
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		SFCompanyHead head = new SFCompanyHead("总公司", 1);
		SFCompanyHead head21 = new SFCompanyHead("北京分公司", 2);
		SFCompanyHead head22 = new SFCompanyHead("上海分公司", 2);
		SFCompanyHead head23 = new SFCompanyHead("广州分公司", 2);

		SFCompanyHead head31 = new SFCompanyHead("海淀分公司", 3);
		SFCompanyHead head32 = new SFCompanyHead("朝阳分公司", 3);
		SFCompanyHead head33 = new SFCompanyHead("虹桥分公司", 3);
		SFCompanyTail tail34 = new SFCompanyTail("广州投送站", 3);

		SFCompanyTail tail41 = new SFCompanyTail("海淀投送站", 4);
		SFCompanyTail tail42 = new SFCompanyTail("朝阳投送站", 4);
		SFCompanyTail tail43 = new SFCompanyTail("虹桥投送站", 4);

		head.add(head21);
		head.add(head22);
		head.add(head23);

		head21.add(head31);
		head21.add(head32);
		head22.add(head33);
		head23.add(tail34);

		head31.add(tail41);
		head32.add(tail42);
		head33.add(tail43);

		// 打印公司层级关系
		head.display();

	}
}
