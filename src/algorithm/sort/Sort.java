package algorithm.sort;

/**
 * The Interface Sort.
 * 该接口主要用来演示排序方法。
 * 同时相当于应用策略模式，只是没有业务主体来持有策略对象而已。
 * @date 2014-6-5 10:27:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public interface Sort {
	
	/**
	 * 对源数组进行排序，输出结果为排序后的数组.
	 *
	 * @param sourceArray 待排序原始数据
	 * @return int[] 排序完成后的数据
	 */
	public int[] sort(int[] sourceArray);
}
