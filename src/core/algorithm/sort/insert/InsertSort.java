package core.algorithm.sort.insert;

import core.algorithm.sort.Sort;

/**
 * The Class InsertSort. 
 * 插入排序算法。
 * 插入排序的算法核心是插入，即顺序从原始数据中获取一个元素，将其插入到输出数据中的有序的位置。
 * 
 * 插入排序思路简单，只是实现时在调整元素位置时需要仔细小心处理。
 * 
 * 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，
 * 算法适用于少量数据的排序，时间复杂度为O(n^2)。
 * 
 * 插入排序的关键在于两个数组，一个有序数组，一个无序数组。
 * 这两个数组可以在源数组上实现，也可以单独创建两个数组用来排序。
 * ================================================================================================
 * 插入排序有点类似冒泡排序，它在已经排好序的数组中插入新的元素，类似冒泡排序的一个小步骤。可以通过二分查找法优化。
 * 包括：直接插入排序，二分插入排序（又称折半插入排序
 * ），链表插入排序，希尔排序（又称缩小增量排序）。属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置） 。
 * 
 * -----关键词，已排序数组和未排序数组。
 * 
 * ============================================================================
 * 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中,从而得到一个新的 、个数加一的有序数据。
 * 比较和交换的时间复杂度为O(n^2)，算法自适应，对于数据已基本有序的情况，时间复杂度为O(n)，算法稳定，开销很低。
 * 算法适合于数据已基本有序或者数据量小的情况。
 * 
 * 插入算法把要排序的数组分成两部分：有序数组和无序数组
 * ========================================================================================
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：

1. 从第一个元素开始，该元素可以认为已经被排序

2. 取出下一个元素，在已经排序的元素序列中从后向前扫描

3. 如果该元素（已排序）大于新元素，将该元素移到下一位置

4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置

5. 将新元素插入到下一位置中

6. 重复步骤2
 * 
 * @date 2013-11-27 10:47:46
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class InsertSort implements Sort{
	


	/**
	 * 插入排序
	 * (节省空间的算法,在同一数组上进行排序)
	 * 空间复杂度 O(n)
	 * 时间复杂度 
	 */
	public int[] sort(int[] sourceArray) {
	     for (int i=0; i<sourceArray.length; i++)
             for (int j=i; j>0 && sourceArray[j-1]>sourceArray[j]; j--)
                 swap(sourceArray, j, j-1);
		return sourceArray;
	}
	
	/**
	 * swap.
	 *
	 * @param src comments
	 * @param i comments
	 * @param j comments
	 */
	public static void swap(int[] src,int i,int j){
		int a =src[i];
		src[i]=src[j];
		src[j] = a;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int[] sr = new int[]{3,4,2};
		InsertSort is = new InsertSort();
		is.sort(sr);
		for (int i = 0; i < sr.length; i++) {
			System.out.println(sr[i]);
		}
		
	}
}
