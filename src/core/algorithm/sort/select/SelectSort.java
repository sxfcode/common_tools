package core.algorithm.sort.select;

import core.algorithm.sort.Sort;

/**
 * The Class SelectSort.
 *  选择排序的算法核心是选择，即在原始数据中每次选出最大或最小的元素，在输出数据中组成有序的数据集合.
 *  
 * 每一趟从待排序的数据元素中选出最小（或最大）的一个元素，顺序放在已排好序的数列的最后，
 * 直到全部待排序的数据元素排完。
 * 
 * 选择排序和冒泡排序
 * 选择排序和冒泡排序还是非常相似的。
 * 1.冒泡排序，在每一次比较的时候，如果发现相邻两数的次序不对，都会马上就把两数进行对调。
 * 2.选择排序，则在比较过程中（内循环里面）并不进行对调，而是先记录下最小（大）数的下标，在一次扫描完成后再进行对调。
 *
 * 相同点都是查找最大或最小的那个数据然后进行处理.不同点，选择排序是记下需要交换数据的索引，只在最后比较完毕时进行交换，而冒泡排序是每次比较都有可能进行交换。
 *
 * @date 2014-4-17 9:54:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class SelectSort implements Sort{
	
	public int[] sort(int[] sourceArray) {
		return SelectSort.selectSort(sourceArray);
	}
	

	/**
	 * 选择排序.
	 *空间复杂度n
	 * @param sourceArray comments
	 * @return int[]
	 */
	public static int[] selectSort(int[] sourceArray){
		// 标记将要进行数据交换的元素,从最后一个开始进行数据交换
		int flag = sourceArray.length -1;
		// 最大数据的索引为max，假定最后一个元素最大
		int max = sourceArray.length -1;
		// step 1：从大到小推进。
		while (flag>0) {
			// step 2:从未排序的元素中找到最大的元素,并记录索引。检查索引为0到flag之间的中最大的元素，查找完毕后，将其与索引为flag的元素交换位置。
			for (int i = 0; i <= flag ; i++) {
				//第i个元素大于当前的最大的元素，则将最大元素索引修改为i元素
				if(sourceArray[i]>sourceArray[max]){
					max = i;
				}
			}
			// step 3:交换最大值与标记元素
			int temp = sourceArray[flag];
			sourceArray[flag] = sourceArray[max]; 
			sourceArray[max] = temp;
			// step 4:重置标记
			flag--;
			max = flag;
		}
		return sourceArray;
	}

	

}
