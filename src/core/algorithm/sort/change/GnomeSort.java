package core.algorithm.sort.change;

import core.algorithm.sort.Sort;
import core.algorithm.sort.SortUtils;

/**
 * The Class GnomeSort.
 *  侏儒排序，又叫地精排序。
 *  该算法是受园丁侏儒排花盆的过程启发。
 *  
 *  侏儒排序类似反向冒泡，但冒泡时并没有遍历整个数组,
 *  
 *  号称最简单的排序算法,
 *  只有一层循环,默认情况下前进冒泡,一旦遇到冒泡的情况发生就往回冒,  直到把这个数字放好为止
 *  
 * @date 2014-4-18 17:59:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class GnomeSort implements Sort {
	
	/**
	 * 类似插入排序，向已经排好序的数组中插入元素，直到找到一个坑，能够刚好让该元素小于后一个元素，大于前一个元素。
	 *
	 * @param s comments
	 * @return int[]
	 */
	public static int[] sort(int[] s) {
		int i = 0;
		while (i<s.length) {
			// 正确的顺序，园丁往前走,注意等于号
			if(i==0||s[i-1]<=s[i]){
				i++;
			}else{
				// 错误的顺序，园丁交换花盆位置，并后退一步，继续比较。
				SortUtils.swap(s, i-1, i);
				i--;
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
