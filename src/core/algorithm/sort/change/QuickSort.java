package core.algorithm.sort.change;

import core.algorithm.sort.Sort;
import core.algorithm.sort.SortUtils;

/**
 * The Class QuickSort. 快速排序 快速排序（Quicksort）是对冒泡排序的一种改进。
 * 其性能提升主要在于跨越式的交换元素，减少了元素比较次数。
 * 
 * 由C. A. R.
 * Hoare在1962年提出。它的基本思想是
 * ：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序
 * ，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 
 * @date 2013-12-2 10:10:18
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class QuickSort implements Sort {
	
	public static void sort(int[] s,int start,int end){
		if (start==end) {
			return;
		}
		if ((end-start)==1) {
			if (s[start]>s[end]) {
				SortUtils.swap(s, start,end);
			}
			return;
		}
		int i = start;
		int j = end;
		while (i<j) {
			while (s[i]<=s[j] && i<j) {
				j--;
			}
			SortUtils.swap(s, i, j);

			while (s[i]<=s[j] && i<j) {
				i++;
			}
			SortUtils.swap(s, i, j);
		}
		sort(s,start,i);
		if ((i+1)<end) {
			sort(s,i+1,end);	
		}

	}
	

	@Override
	public int[] doSort(int[] sourceArray) {
		sort(sourceArray,0,sourceArray.length-1);
		return sourceArray;
	}
}
