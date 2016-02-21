package core.algorithm.sort;

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

	public int[] doSort(int[] sourceArray);
}
