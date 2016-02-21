package core.algorithm.sort.distribute;

import java.util.Iterator;

import core.algorithm.sort.Sort;

/**
 * The Class RadixSort.
 * 注意：
 * 基数排序需要考虑每个数字的符号
 * 
 *  基数排序 基数排序（英语：Radix
 * sort）是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字， 然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数
 * ，所以基数排序也不是只能使用于整数。
 * 实现：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序
 * 。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。 
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），
 * LSD的排序方式由键值的最右边开始 即最低位开始，而MSD则相反，由键值的最左边开始即最高位开始。
 * 
 * 空间占用比较大。 不适合大规模数据。
 * 
 * @date 2013-12-6 14:46:36
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class RadixSort implements Sort{
	
	/**
	 * Sort.
	 *
	 * @param s the s
	 * @param n the n  n 数组中位数最大的数字的位数。
	 */
	public static void sort(int[] s,int n){
		// 当前处理位数
		int w=1;
		// 存放数据的桶
		int[][] bk=new int[10][s.length];
		// 每个数据桶中保存的数字的个数)；
		int[] bkc= new int[10];
		for (int i = 0; i < bkc.length; i++) {
			 bkc[i]=0;
		}
		// 试用桶过滤数据
		while (w<=n) {
			for (int i = 0; i < s.length; i++) {
				// 10的w次方
				int m10 =(int)Math.pow(10,w-1);
				// 求出s[i],应该存放的桶的位置
				int r=(s[i]/m10)%10;
				bk[r][bkc[r]]=s[i];
				bkc[r]++;
			}
			// 将桶中的数据按桶中的顺序放回数组
			int k =0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < bkc[i]; j++) {
					// 使用第i个数据桶中的数据
					s[k]=bk[i][j];
					k++;
				}
				// 桶清零
				bkc[i]=0;
			}
			w++;
		}
		
	}



	@Override
	public int[] doSort(int[] sourceArray) {
		int maxN = 0;
		for (int temp :sourceArray) {
			String tempS = temp+"";
			if(tempS.length()>maxN){
				maxN =tempS.length();
			}
		}
		sort(sourceArray,maxN);
		return sourceArray;
	}
}
