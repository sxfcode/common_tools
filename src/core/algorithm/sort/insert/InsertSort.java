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
	

	@Override
	public int[] sort(int[] sourceArray) {
		return InsertSort.insertSort(sourceArray);
	}
	
	
	/**
	 * 插入排序
	 * (节省空间的算法,在同一数组上进行排序)
	 * 空间复杂度 O(n)
	 * 时间复杂度 比较
	 * 
	 * @param sourceArray
	 * @return
	 */
	public static int[] insertSort(int[] sourceArray){
		// step 1:遍历源数组，以i元素为边界，将源数组分成两部分,前半部分(不包含i)和后半部分(包含i)。前半部分为已排序数组，后半部分为未排序数
		for(int i=0;i<sourceArray.length;i++){
			int current = sourceArray[i];
			// step 2:将i元素与前半部分的最后一个元素比较。第i个元素跟前i-1个元素进行比较，并插入到合适的位置，前i-1个元素认为已经排序完毕
			for(int j=i-1;j>=0;j--){
				// step 3:若i元素大于当前元素，则将i插入到当前元素后边，否则继续向前比较。
				if(current>=sourceArray[j]){
					// 插入并向后移动元素,由于最后元素的索引不方便确认，所以采用向后推进的方式，而不常规的向前推进的方式。
					// mov1 移动从j+1到i之间的元素
					int last = current;
					for(int k=j+1;k<=i;k++){
						int temp = sourceArray[k];
						sourceArray[k] = last;
						last = temp;
					}
					// 若不执行break,则k==0的操作会再次执行，导致逻辑错误。
					break;
				}else{
					// 所有元素都比current大的情况，则将其放到数组开头
					if(j==0){
						// mov2:移动从0到i之间的元素(注意mov2这里要比mov1多移动一个元素)
						int last = current;
						for(int k=j;k<=i;k++){
							int temp = sourceArray[k];
							sourceArray[k] = last;
							last = temp;
						}
					}
				}
			}
		}
		return sourceArray;
	}
	
	/**
	 * 插入排序
	 * (额外需要2个数组的存储空间，主要用于演示)
	 * @param sourceArray
	 * @return
	 */
	public static int[] insertSortShow(int[] sourceArray){
		if (sourceArray==null||sourceArray.length<=1) {
			return sourceArray;
		}
		// 已排序数组(按从小到大的顺序排,其length等于源数组)
		int[] sortedArray = new int[sourceArray.length];
		// 未排序数组(其length等于源数组)
		int[] notSortedArray = new int[sourceArray.length];
		System.arraycopy(sourceArray, 0, notSortedArray, 0, sourceArray.length);
		sortedArray[0]= sourceArray[0];
		for (int i = 1; i < notSortedArray.length; i++) {
			int current = notSortedArray[i];
			// 从已排序数组的最末位置开始比较,
			// 若当前元素(未排序)，大于已排序数组末尾的元素last，则将其插入到排序数组，
			// 否则，与已排序数组的前一个元素(last-1)比较，若其大于(last-1),则将其插入到last位置，否则继续迁移进行比较，直到第一个元素。
			for (int j = i-1; j < sortedArray.length && j>=0; j--) {
				if(current>=sortedArray[j]){
					// 移动后边(i-j)个的元素
					for (int k = i-1; k < sortedArray.length && k > j ; k--) {
						sortedArray[k+1] = sortedArray[k];
					}
					sortedArray[j+1] = current;
					break;
				}else{
					if(j==0){
						// 移动后边(i-j)个的元素,将当前元素插入到数组头部。小于0时，第j个元素也要后移，同同时将current赋给j
						for (int k = i-1; k < sortedArray.length && k >= j ; k--) {
							sortedArray[k+1] = sortedArray[k];
						}
						sortedArray[j] = current;
					}
				}
			}
		}
		return sortedArray;
	}
}
