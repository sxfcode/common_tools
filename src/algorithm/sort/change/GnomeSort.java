package algorithm.sort.change;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class GnomeSort.
 *  侏儒排序，又叫地精排序。
 *  该算法是受园丁侏儒排花盆的过程启发。
 *  
 *  号称最简单的排序算法,
 *  只有一层循环,默认情况下前进冒泡,一旦遇到冒泡的情况发生就往回冒,  直到把这个数字放好为止
 *  
 * @date 2014-4-18 17:59:59
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class GnomeSort {

	 
	/**
	 * gnomeSort.
	 *
	 * @param sourceArray comments
	 * @return int[]
	 */
	public static int[] gnomeSort(int[] sourceArray) {
		int i = 0;
		while (i<sourceArray.length) {
			// 正确的顺序，园丁往前走
			if(i==0||sourceArray[i-1]<sourceArray[i]){
				i++;
			}else{
				// 错误的顺序，园丁交换花盆位置，并后退一步，继续比较。
				int temp = sourceArray[i-1];
				sourceArray[i-1]= sourceArray[i];
				sourceArray[i] = temp;
				i--;
			}
		}
		return sourceArray;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List<int[]> list = new ArrayList<int[]>();
		int[] arr1 = new int[]{1,10,11,12,13,9,8,7,6};
		int[] arr2 = new int[]{8,7,6,5,4,3,2,1};
		int[] arr3 = new int[]{1,8,9,11,4,3,2};
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		for (int[] arr : list) {
			int[] targetArray = GnomeSort.gnomeSort(arr);
			for (int i = 0; i < targetArray.length; i++) {
				System.out.print(targetArray[i]+",");
			}
			System.out.println();
		}
	}

}
