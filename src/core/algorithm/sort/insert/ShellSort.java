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
		int step = sourceArray.length/2;
		while (step>0) {
			// if step ==1直接进行插入排序
			if(step ==1){
				//sourceArray = InsertSort.insertSort(sourceArray);
				for(int i=0;i<sourceArray.length;i++){
					int current = sourceArray[i];
					// 第i个元素跟前i-1个元素进行比较，并插入到合适的位置，前i-1个元素认为已经排序完毕
					for(int j=i-1;j>=0;j=j-1){
						if(current>=sourceArray[j]){
							// 插入并向后移动元素,由于最后元素的索引不方便确认，所以采用向后推进的方式，而不常规的向前推进的方式。
							// mov1
							int last = current;
							for(int k=j+1;k<=i;k=k+1){
								int temp = sourceArray[k];
								sourceArray[k] = last;
								last = temp;
							}
							// 若不执行break,则k==0的操作会再次执行，导致逻辑错误。
							break;
						}else{
							// 所有元素都比current大的情况，则将其放到数组开头
							if(j==0){
								// mov2:注意mov2这里要比mov1多移动一个元素
								int last = current;
								for(int k=j;k<=i;k=k+1){
									int temp = sourceArray[k];
									sourceArray[k] = last;
									last = temp;
								}
							}
						}
					}
				}
				// 输出插入排序结果
				for (int i = 0; i < sourceArray.length; i++) {
					System.out.print(sourceArray[i]+",");
				}
				System.out.println("<br>");
				break;
			}else{
				for (int i = 0; i <step; i++) {
					// 对每个分组进行插入排序
					for(int j= i;j<sourceArray.length;j=j+step){
						int current = sourceArray[j];
						// (j之前的元素为已排序数组，j之后的元素为未排序数组)
						// 从未排序数组中拿出一个元素与已排序数组进行比较
						for(int k=j-step;k>=i;k=k-step){
							// 跟已排序数组中的上一个元素进行比较
							if(current>=sourceArray[k]){
								// 插入并向后移动元素,由于最后元素的索引不方便确认，所以采用向后推进的方式，而不常规的向前推进的方式。
								int last = current;
								for(int l=k+step;l<=j;l=l+step){
									int temp = sourceArray[l];
									sourceArray[l] = last;
									last = temp;
								}
								break;
							}else{
								// 所有元素都比current大的情况，则将其放到纵向数组开头
								if(k==i){
									int last = current;
									for(int l=k;l<=j;l=l+step){
										int temp = sourceArray[l];
										sourceArray[l] = last;
										last = temp;
									}
								}
							}
						}
					}
				}
				// 输出插入排序结果
				for (int i = 0; i < sourceArray.length; i++) {
					System.out.print(sourceArray[i]+",");
				}
				System.out.println("<br>");
				step = step /2;
			}
		}
		return sourceArray;
	}
	
	/**
	 *模拟希尔排序,本方法主要用于理解希尔排序，性能存在问题，实际开发中不要直接使用该方法。
	 * @param sourceArray
	 * @return
	 */
	public static int[] shellSortShow(int[] sourceArray){
		int step = sourceArray.length/2;
		while (step>0) {
			// if step ==1直接进行插入排序
			if(step ==1){
				sourceArray = InsertSort.insertSort(sourceArray);
				break;
			}else{
				for (int i = 0; i <step; i++) {
					
					// 使用每个分组组成新的数组数据,便于排序
					int tempLength = 0;
					for(int j= i;j<sourceArray.length;j=j+step){
						tempLength =tempLength +1;
					}
					int[] temp = new int[tempLength];
					int tempIndex = 0;
					for(int j= i;j<sourceArray.length;j=j+step){
						temp[tempIndex] =sourceArray[j];
						tempIndex = tempIndex+1;
					}
					// 对每个分组进行插入排序
					temp = InsertSort.insertSort(temp);
					
					// 排序后的数据回填
					tempIndex = 0;
					for(int j= i;j<sourceArray.length;j=j+step){
						sourceArray[j]=temp[tempIndex];
						tempIndex = tempIndex+1;
					}
				}
				step = step /2;
			}
		}
		return sourceArray;
	}

} 	 	
