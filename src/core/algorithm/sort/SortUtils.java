package core.algorithm.sort;

public class SortUtils {
	/**
	 * 
	 * 交换src[i]和src[j]元素
	 *
	 * @param src 源数组
	 * @param i 
	 * @param j comments
	 */
	public static void swap(int[] src,int i,int j){
		int a =src[i];
		src[i]=src[j];
		src[j] = a;
	}

}
