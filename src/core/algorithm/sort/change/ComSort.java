package core.algorithm.sort.change;

import core.algorithm.sort.Sort;

/**
 * The Class ComSort. 梳排序(Comb sort)是一种由Wlodzimierz
 * Dobosiewicz于1980年所发明的不稳定排序算法，并由Stephen Lacey和Richard Box于1991年四月号的Byte杂志中推广。
 * 梳排序是改良自泡沫排序和快速排序，其要旨在于消除乌龟，亦即在阵列尾部的小数值，这些数值是造成泡沫排序缓慢的主因。
 * 相对地，兔子，亦即在阵列前端的大数值，不影响泡沫排序的效能。
 * 
 * 在泡沫排序中，只比较阵列中相邻的二项，即比较的二项的间距(Gap)是1， 梳排序提出此间距其实可大于1，改自插入排序的希尔排序同样提出相同观点。
 * 梳排序中，开始时的间距设定为阵列长度，并在循环中以固定比率递减，通常递减率设定为1.3。
 * 在一次循环中，梳排序如同泡沫排序一样把阵列从首到尾扫描一次，比较及交换两项，不同的是两项的间距不固定于1。
 * 如果间距递减至1，梳排序假定输入阵列大致排序好，并以泡沫排序作最后检查及修正。
 * 
 * 
 * 变异形式： 梳排序-11 设定递减率为1.3时，最后只会有三种不同的间距组合：(9, 6, 4, 3, 2, 1)、(10, 7, 5, 3, 2, 1)、或 (11, 8, 6, 4, 3, 2, 1)。
 * 实验证明，如果间距变成9或10时一律改作11，则对效率有明显改善，原因是如果间距曾经是9或10，则到间距变成1时，
 * 数值通常不是递增序列，故此要进行几次泡沫排序循环修正。 加入此指定间距的变异形式称为梳排序-11(Combsort11)。
 * 
 * @date 2014-4-18 17:15:42
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ComSort implements Sort {
	
	public int[] sort(int[] sourceArray) {
		return ComSort.combSort(sourceArray);
	}

	/**
	 * 梳排序.
	 *
	 * @param sourceArray comments
	 * @return int[]
	 */
	public static int[] combSort(int[] sourceArray) {
		// 整个数组是否排序完成
		boolean sorted = false;
		//int step = (int) Math.floor(sourceArray.length/1.3f);
		int step = sourceArray.length;
		while (!sorted) {
			sorted = true;
			// 间距递减
			step = (int) Math.floor(step/1.3f);
			// 进行比较和交换
			for (int i = 0; i < sourceArray.length&&(i+step<sourceArray.length); i++) {
				// 发现需要交换的元素
				if(sourceArray[i]>sourceArray[i+step]){
					int temp = sourceArray[i];
					sourceArray[i] = sourceArray[i+step];
					sourceArray[i+step] = temp;
					// 有次序错误的元素，则认为排序尚未完成。
					sorted = false;
				}
			}
			
			for (int i = 0; i < sourceArray.length; i++) {
				//System.out.print(sourceArray[i]+",");
			}
			//System.out.println();
		}
		return sourceArray;
	}

}
