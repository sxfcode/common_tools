package core.algorithm.sort.merge;

import core.algorithm.sort.SortUtils;

/**
 * 
 * 归并排序的算法核心是递归合并相邻元素，在合并的时候执行排序操作，最终合并成一个大的有序的数据集合。
 * 
 * 归并排序有递归和非递归两种实现方式， 递归到最后，可能要比较相邻两个元素的数据。
 * 非递归的实现方式，空间占用较小，不需要维护额外的堆栈信息，适合处理较多的数据。
 * 
 * 归并排序（Merge）是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。
 * 然后再把有序子序列合并为整体有序序列。
 * 
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 * 
 * 归并排序算法稳定，数组需要O(n)的额外空间，链表需要O(log(n))的额外空间，时间复杂度为O(nlog(n))，算法不是自适应的，
 * 不需要对数据的随机读取。
 * 
 * 归并排序 算法描述[编辑] 归并操作的过程如下： 1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 2,设定两个指针，最初位置分别为两个已经排序序列的起始位置 3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 4.重复步骤3直到某一指针达到序列尾 5.将另一序列剩下的所有元素直接复制到合并序列尾
 * 
 * ----------------------------------------------------------------------------
 * --
 * -----------------------------------------------------------------------------
 * 除了直接申请空间外，也可以申请较小的空间，将指定元素数组排好序后，再写回原数组。
 * 
 * 
 * 
 * @author suxiaofei
 * 
 */
public class MergeSort {

	private static int count = 0;

	/**
	 * Sort.
	 *
	 * @param source
	 *            the source
	 */
	public static void sort(int[] source, int start, int end) {
		if (end - start <= 1) {
			if (source[start] > source[end]) {
				SortUtils.swap(source, start, end);
			}
		} else {
			// 通过递归拆分数组。
			int mid = start + (end - start + 1) / 2;
			sort(source, start, mid);
			sort(source, mid + 1, end);
			merge(source, start, mid, end);
		}
	}

	/**
	 * Merge. 合并两个数组，左边的数组为s[start,mid],右边的数组为s[mid+1,end]
	 *
	 * @param s
	 *            the s
	 * @param start
	 *            左边数组的开始下标
	 * @param mid
	 *            左边数组的结束下标(右边数组的开始下标=mid+1)
	 * @param end
	 *            右边数组的结束 下标
	 */
	public static void merge(int[] s, int start, int mid, int end) {
		int[] temp = new int[end - start + 1];
		// 左边数组开始下标
		int i = start;
		// 右边数组开始下标
		int j = mid + 1;
		int k = 0;
		while (i <= (mid + 1) || j <= (end + 1)) {
			// 某一数组已经比较完毕。
			// 左边数组已经全部进入temp队列，插入右边数组的剩余元素
			if (i == (mid + 1)) {
				for (; j <= end; k++, j++) {
					temp[k] = s[j];
				}
				break;
			}
			// 右边数组已经全部进入temp队列，插入左边数组的剩余元素
			if (j == (end + 1)) {
				for (; i <= mid; k++, i++) {
					temp[k] = s[i];
				}
				break;
			}

			// 从两个数组的当前元素中挑出较小的一个，放入临时数组，较小元素所在数组的下标+1
			if (s[i] < s[j]) {
				temp[k] = s[i];
				k++;
				i++;
			} else {
				temp[k] = s[j];
				k++;
				j++;
			}
		}
		// 将排好序的数组写回s数组（也可以直接返回）
		for (int m = 0; m < temp.length; m++) {
			s[start + m] = temp[m];
		}
	}

	/**
	 * 归并排序的递归实现
	 * 
	 * @param sourceArray
	 * @return
	 */
	public static int[] sortOld(int[] sourceArray) {
		if (sourceArray.length <= 1) {
			return sourceArray;
		}
		count++;
		int mid = sourceArray.length / 2;
		int[] left = new int[mid];
		for (int i = 0; i < mid; i++) {
			left[i] = sourceArray[i];
		}
		// 注意余数的情况
		int[] right = new int[sourceArray.length - mid];
		for (int i = 0; i + mid < sourceArray.length; i++) {
			right[i] = sourceArray[mid + i];
		}
		left = sortOld(left);
		right = sortOld(right);
		return mergeOld(left, right);
	}

	public static int[] mergeOld(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;
		while (leftIndex <= left.length && rightIndex <= right.length) {
			// 某一组数据已经比较完毕,则把另一组剩下的数据直接放到结果集合中,因为未比较的元素都比前一组大，并且是有序的
			if (leftIndex == left.length) {
				while (resultIndex < result.length) {
					result[resultIndex] = right[rightIndex];
					resultIndex++;
					rightIndex++;
				}
				break;
			} else if (rightIndex == right.length) {
				while (resultIndex < result.length) {
					result[resultIndex] = left[leftIndex];
					resultIndex++;
					leftIndex++;
				}
				break;
			}
			// 将较小的数字放入返回结果集中
			if (left[leftIndex] < right[rightIndex]) {
				result[resultIndex] = left[leftIndex];
				leftIndex++;
				resultIndex++;
			} else {
				result[resultIndex] = right[rightIndex];
				rightIndex++;
				resultIndex++;
			}
		}
		return result;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args) {
		// int[] left = new int[]{1,2,3,8,10};
		// int[] right = new int[]{3,4,5,7,8,9};
		// int[] result = merge(left, right);
		// for (int i = 0; i < result.length; i++) {
		// System.out.print(result[i]+",");
		// }
		// int[] sourceArray = new int[] { 8, 7, 6, 5, 4, 3, 2, 1,
		// 1,2,3,4,5,6,7,8};
		// int[] targetArray = MergeSort.mergeSortRecursive(sourceArray);
		// for (int i = 0; i < targetArray.length; i++) {
		// System.out.print(targetArray[i]+",");
		// }
		// System.out.println("程序体执行总次数:"+MergeSort.count);
		// int[] sourceArray = new int[] { 8, 7, 6, 5, 4, 3, 2, 1,
		// 1,2,3,4,5,6,7,8};
		int[] sourceArray = new int[] { 3, 2, 1 };
		sort(sourceArray, 0, sourceArray.length - 1);
		for (int i = 0; i < sourceArray.length; i++) {
			System.out.println(sourceArray[i]);
		}

	}

}
