package core.algorithm.sort.change;

import core.algorithm.sort.Sort;
import core.algorithm.sort.SortUtils;


/**
 * The Class OddEvenSort.
 * 奇偶排序。
 * 该算法主要用于并行计算，该算法由Habermann在1972年最初发表并展现了在并行处理上的效率
 * 
 * 该算法中，通过比较数组中相邻的（奇-偶）位置数字对，如果该奇偶对是错误的顺序（第一个大于第二个），则交换。
 * 下一步重复该操作，但针对所有的（偶-奇）位置数字对。如此交替进行下去.
 * 
 * 
 * 奇偶排序法的思路是在数组中重复两趟扫描。
 * 第一趟扫描选择所有的数据项对，a[j]和a[j+1]，j是奇数(j=1, 3, 5……)。如果它们的关键字的值次序颠倒，就交换它们。
 * 第二趟扫描对所有的偶数数据项进行同样的操作(j=2, 4,6……)。重复进行这样两趟的排序直到数组全部有序。
 * 
 * 奇偶排序实际上在多处理器环境中很有用，处理器可以分别同时处理每一个奇偶对，然后又同时处理偶对。
 * 因为奇数对是彼此独立的，每一刻都可以用不同的处理器比较和交换。这样可以非常快速地排序。
 * 
 * @date 2014-4-18 16:49:32
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class OddEvenSort implements Sort {
	
	/**
	 *
	 * @param s
	 * @return
     */
	public static int[] sort(int[] s){
		//是否已经排序完成
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < s.length&&(i+1<s.length); i=i+2) {
				// 比较相邻元素
				if(s[i]>s[i+1]){
					// 交换相邻元素
					SortUtils.swap(s, i, i+1);
					// 如果发生交换，则认为整个排序过程尚未完成。
					sorted = false;
				}
			}
			for (int i = 1; i < s.length&&(i+1<s.length); i=i+2) {
				// 比较相邻元素
				if(s[i]>s[i+1]){
					// 交换相邻元素
					SortUtils.swap(s, i, i+1);
					// 如果发生交换，则认为整个排序过程尚未完成。
					sorted = false;
				}
			}
		}
		return s;
	}


	@Override
	public int[] doSort(int[] sourceArray) {
		sort(sourceArray);
		return sourceArray;
	}
}
