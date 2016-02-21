package core.algorithm.sort.change;

import core.algorithm.sort.Sort;
import core.algorithm.sort.SortUtils;

/**
 * The Class BubbleSort.
 * 每一趟从待排序的数据元素中选出最小（或最大）的一个元素，顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完。
 * 
 * 选择排序和冒泡排序
 * 选择排序和冒泡排序还是非常相似的。
 * 1.冒泡排序，在每一次比较的时候，如果发现相邻两数的次序不对，都会马上就把两数进行对调。
 * 2.选择排序，则在比较过程中（内循环里面）并不进行对调，而是先记录下最小（大）数的下标，在一次扫描完成后再进行对调。
 *
 * 相同点都是查找最大或最小的那个数据然后进行处理.不同点，选择排序是记下需要交换数据的索引，只在最后比较完毕时进行交换，而冒泡排序是每次比较都有可能进行交换。
 * 
 * 冒泡排序，比较时间复杂度，n(n+1)/2
 *
 * @date 2014-4-17 9:52:47
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class BubbleSort implements Sort {
	
	/**
	 * 冒泡排序.
	 * 空间复杂度 n
	 *
	 * @param s comments
	 * @return int[]
	 */
	public static int[] sort(int[] s){
		// 标记将要进行数据交换的元素,从最后一个开始进行数据交换
		int flag = s.length -1;
		// step 1:从大到小推进
		while (flag>0) {
			// step 2:通过相邻元素比较与交换，找到最大元素，并将其放到flag的位置。
			for (int i = 0; i < flag ; i++) {
				if(s[i]>s[i+1]){
					// 交换最大值
					SortUtils.swap(s, i, i+1);
				}
			}
			// 重置标记
			flag--;
		}
		return s;
	}

	@Override
	public int[] doSort(int[] sourceArray) {
		sort(sourceArray);
		return sourceArray;
	}
}
