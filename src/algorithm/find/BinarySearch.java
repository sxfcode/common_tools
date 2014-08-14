package algorithm.find;

/**
 * The Class BinarySearch.
 * 二分查找
 * @date 2014-8-14 11:37:35
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class BinarySearch {
	
	/**
	 * search.
	 *  处理算法
	 *
	 * @param array comments
	 * @param number comments
	 * @return int
	 */
	public static int searchOld(int[] array,int number){
		if(array==null){
			return -1;
		}
		int result = -1;
		int start = 0;
		int end = array.length-1;
		int mid;
		// 特殊情况处理，时间复杂度为常数级
		if(array.length==1){
			if(number==array[0]){
				return 0;
			}else{
				return -1;
			}
		}
		while (start<=end) {
			// 处理趋近的情况
			if(end==start+1){
				if(number==array[start]){
					return start;
				}else if(number==array[end]){
					return end;
				}else{
					return -1;
				}
			}
			
			mid = start +(end-start)/2;
			if(number ==array[mid]){
				result = mid;
				break;
			}else if (number<array[mid]) {
				end=mid;
			}else {
				start = mid;
			}
		}
		return  result;
	}
	
	/**
	 * search.
	 *
	 * @param array comments
	 * @param number comments
	 * @return int
	 */
	public static int search(int[] array,int number){
		if(array==null){
			return -1;
		}
		int result = -1;
		int start = 0;
		int end = array.length-1;
		int mid;
		while (start<=end) {
			mid = start +(end-start)/2;
			if(number ==array[mid]){
				result = mid;
				break;
			// 注意+1和-1的操作
			}else if (number<array[mid]) {
				end=mid-1;
			}else {
				start = mid+1;
			}
		}
		return  result;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int[] sourceArray1= new int[]{1,2,3,4,4,5,7,7,8,8,9,11,20,50};
		int[] sourceArray2= new int[]{14,22,28,39,43,55,65,73,81,93};
		System.out.println(BinarySearch.search(sourceArray1, 11));
		System.out.println(BinarySearch.search(sourceArray1, 12));
		System.out.println(BinarySearch.search(sourceArray2, 22));
		
	}

}
