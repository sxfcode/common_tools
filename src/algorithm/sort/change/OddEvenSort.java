package algorithm.sort.change;


/**
 * The Class OddEvenSort.
 * 奇偶排序。
 * 该算法主要用于并行计算，该算法由Habermann在1972年最初发表并展现了在并行处理上的效率
 * 
 * 该算法中，通过比较数组中相邻的（奇-偶）位置数字对，如果该奇偶对是错误的顺序（第一个大于第二个），则交换。
 * 下一步重复该操作，但针对所有的（偶-奇）位置数字对。如此交替进行下去.
 * 
 * 
 * 奇偶排序法的思路是在数组中重复两趟扫描。
 * 第一趟扫描选择所有的数据项对，a[j]和a[j+1]，j是奇数(j=1, 3, 5……)。如果它们的关键字的值次序颠倒，就交换它们。
 * 第二趟扫描对所有的偶数数据项进行同样的操作(j=2, 4,6……)。重复进行这样两趟的排序直到数组全部有序。
 * 
 * 奇偶排序实际上在多处理器环境中很有用，处理器可以分别同时处理每一个奇偶对，然后又同时处理偶对。
 * 因为奇数对是彼此独立的，每一刻都可以用不同的处理器比较和交换。这样可以非常快速地排序。
 * 
 * @date 2014-4-18 16:49:32
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class OddEvenSort {

	/**
	 * oddEvenSort.
	 *
	 * @param sourceArray comments
	 * @return int[]
	 */
	public static int[] oddEvenSort(int[] sourceArray){
		//是否已经排序完成
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < sourceArray.length&&(i+1<sourceArray.length); i=i+2) {
				// 比较相邻元素
				if(sourceArray[i]>sourceArray[i+1]){
					// 交换相邻元素
					int temp = sourceArray[i];
					sourceArray[i] = sourceArray[i+1];
					sourceArray[i+1] = temp;
					// 如果发生交换，则认为整个排序过程尚未完成。
					sorted = false;
				}
			}
			for (int i = 1; i < sourceArray.length&&(i+1<sourceArray.length); i=i+2) {
				// 比较相邻元素
				if(sourceArray[i]>sourceArray[i+1]){
					// 交换相邻元素
					int temp = sourceArray[i];
					sourceArray[i] = sourceArray[i+1];
					sourceArray[i+1] = temp;
					// 如果发生交换，则认为整个排序过程尚未完成。
					sorted = false;
				}
			}
		}
		return sourceArray;
	}
	
	public static void main(String[] args) {
		int[] sourceArray = new int[]{1,10,11,12,13,9,8,7,6};
		//int[] sourceArray = new int[]{8,7,6,5,4,3,2,1,};
		//int[] sourceArray = new int[]{1,8,9,11,4,3,2};
		int[] targetArray = OddEvenSort.oddEvenSort(sourceArray);
		for (int i = 0; i < targetArray.length; i++) {
			System.out.print(targetArray[i]+",");
		}
	}

}
