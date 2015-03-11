package core.algorithm.sort.insert;

import core.algorithm.sort.Sort;


/***
 * 希尔排序，
 * 
 * 希尔排序(缩小增量法)
 * 属于插入类排序,是将整个无序列分割成若干小的子序列分别进行插入排序。希尔排序并不稳定，O(1)的额外空间，时间复杂度为O(N*(logN )^2)。
 * 最坏的情况下的执行效率和在平均情况下的执行效率相比相差不多。
 * 基本思想：
 * 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；然后，
 * 取第二个增量d2<d1重复上述的分组和排序，直至所取的增量dt=1(dt<dt-l<…<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
 * 
 * 
 * 
 * 希尔排序（Shell Sort）又称为缩小增量排序，输入插入排序算法，是对直接排序算法的一种改进。本文介绍希尔排序算法。
    对于插入排序算法来说，如果原来的数据就是有序的，那么数据就不需要移动，而插入排序算法的效率主要消耗在数据的移动中。
    因此可知：如果数据的本身就是有序的或者本身基本有序，那么效率就会得到提高。
    希尔排序的基本思想是：将需要排序的序列划分成为若干个较小的子序列，对子序列进行插入排序，通过则插入排序能够使得原来序列成为基本有序。
    这样通过对较小的序列进行插入排序，然后对基本有序的数列进行插入排序，能够提高插入排序算法的效率。
    
    
    希尔排序的性能关键在于步长的选择。
    
    
    13 14 94 33 82
    25 59 94 65 23
    45 27 73 25 39
    10
 */
public class ShellSort implements Sort  {
	
	public int[] sort(int[] sourceArray) {
		return ShellSort.shellSort(sourceArray);
	}
	
	
	/**
	 * 希尔排序,
	 * 
	 * 空间复杂度O(n)
	 * 时间复杂度
	 * 
	 * 注意尽可能的简化算法中的赋值和交换操作，否则虽然思想上希尔排序的实际执行速度会比预计的要慢。
	 * 之所以不使用子方法，写重复代码，是因为子方法会保存堆栈等信息，影响执行效率.
	 * @param sourceArray
	 * @return
	 */
	public static int[] shellSort(int[] sourceArray){
		int step =sourceArray.length/2;
		while (step>0) {
			// 数据分组 x,x+d,x+2d,x+3d为一组数据
			for (int x = 0; x < step; x++) {
				// 对分组数据使用插入排序
				for(int i=x;i<sourceArray.length;i=x+step){
					for (int j=i;j>x&&sourceArray[j-step]>sourceArray[j];j=j-step) {
						swap(sourceArray, j-step, j);
					}
				}
			}		
			step=step/2;
		}
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


} 	 	
