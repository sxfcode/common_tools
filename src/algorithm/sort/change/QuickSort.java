package algorithm.sort.change;

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
public class QuickSort {
	
	
	/**
	 * 快速排序.(递归方式实现)
	 *
	 * @param sourceArray comments
	 * @param start comments
	 * @param end comments
	 */
	public static void quickSortInRe(int[] sourceArray,int start,int end){
		int i= start;
		int j = end;
		// 从i++和j--两个方向搜索不满足条件的值并交换  *
		while (i<j) {
			// 处理右侧数据
			while(i<j&&sourceArray[i]<=sourceArray[j]){
				j--;
			}
			// 交换数据
			if(i<j){
				int temp =sourceArray[i];
				sourceArray[i] = sourceArray[j];
				sourceArray[j] = temp;
			}
			// 处理左侧数据
			while(i<j&&sourceArray[i]<=sourceArray[j]){
				i++;
			}
			// 交换数据
			if(i<j){
				int temp =sourceArray[i];
				sourceArray[i] = sourceArray[j];
				sourceArray[j] = temp;
			}
		}
		if(i-start>1){
			quickSortInRe(sourceArray,start,i);
		}
		if(end-i>1){
			quickSortInRe(sourceArray,i+1,end);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int[] sourceArray = new int[]{1,4,7,3,4,5,7,8,9,11,20,50,8,2};
		QuickSort.quickSortInRe(sourceArray,0,sourceArray.length-1);
		for (int i = 0; i < sourceArray.length; i++) {
			System.out.println(sourceArray[i]);
		}
	}

}
