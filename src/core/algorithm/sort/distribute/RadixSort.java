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
	 * @param n the n  n 数组中位数最大的数字。
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
	

	
	
	/**
	 * 基数排序(桶排序)，
	 * 
	 * 空间复杂度 11n
	 * 时间复杂度
	 * 
	 * 在已知最大数字位数的情况下，能有效减少排序时间。
	 * 注意，暂不考虑负数的情况,并且本方法会改变源数组，注意并发。
	 * LSD方式，从低位到高位的排序.
	 *
	 * @param sourceArray comments 源数组
	 * @param maxWidth comments 数组中最大数字的位数，例如，数组中最大数字为100，100为3位数，则传3。
	 * 若不知道最大数，则传-1即可。
	 * @return the int[]
	 */
	public static int[] sortOld(int[] sourceArray,int maxWidth){
		// step 1:确定最大数。
		// 计算最大数的位数start.
		int max = 1; // 
		if (maxWidth==-1) {
			int temp = sourceArray[0];
			for (int i = 0; i < sourceArray.length; i++) {
				if(sourceArray[i]>temp){
					temp = sourceArray[i];
				}
			}
			maxWidth = (""+temp).length();
		}
		// 计算最大数。比数组中的数字大1位，例如数组中最大数是99，则max=100.
		while (maxWidth>0) {
			max = max*10;
			maxWidth--;
		}
		// 计算最大数的位数end.
		// step 2:初始化变量
		int sourceIndex = 0;// 源数组索引
		int n = 1; // 代表位数1,10,100
		int length = sourceArray.length;
		int[][] bucket = new int[10][length];// 用来排序的桶。这里采用常规的0到9，10个桶。
		int[] bucketCounter = new int[10];// 每个桶里存放的数字的个数,默认初始化为0.
		// 初始化
		for (int i = 0; i < bucketCounter.length; i++) {
			bucketCounter[i]=0;
		}
		// step 3:开始桶排序
		// 从低位开始循环所有数位。利用桶的编号本身代表了一种排序。
		while(n<max){
			// step 3.1:根据余数，将数据放在不同的桶里边。
			for (int i = 0; i < sourceArray.length; i++) {
				int digit = (sourceArray[i]/n)%10;
				bucket[digit][bucketCounter[digit]]= sourceArray[i];
				bucketCounter[digit]++;
			}
			// step 3.2:遍历桶，用排序后的数据覆盖源数组。
			for (int i = 0; i < bucketCounter.length; i++) {
				if (bucketCounter[i]>0) {
					for (int j = 0; j < bucketCounter[i]; j++) {
						sourceArray[sourceIndex] = bucket[i][j];
						sourceIndex++;
					}
					// 重置计数器
					bucketCounter[i] = 0;
				}
			}
			n = 10*n;
			// 重置计数器
			sourceIndex = 0;
			// step 3.3 重复3.1和3.2操作
		}
		return sourceArray;
	}
	
	
	
	
	public static void main(String[] args) {
		int[] sourceArray = new int[]{1,200,10,25,123,42,91};
		sort(sourceArray, 3);
		for (int i = 0; i < sourceArray.length; i++) {
			System.out.println(sourceArray[i]);
			
		}
	}


	@Override
	public int[] sort(int[] sourceArray) {
		return new int[0];
	}
}
