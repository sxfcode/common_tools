package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

import algorithm.sort.change.BubbleSort;
import algorithm.sort.change.CockTailSort;
import algorithm.sort.change.ComSort;
import algorithm.sort.change.GnomeSort;
import algorithm.sort.change.OddEvenSort;
import algorithm.sort.change.QuickSort;
import algorithm.sort.distribute.RadixSort;
import algorithm.sort.insert.InsertSort;
import algorithm.sort.insert.ShellSort;
import algorithm.sort.select.SelectSort;

/**
 * The Class ZExample.
 * 
 * @date 2014-6-5 10:18:42
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {
	public static void main(String[] args) {
		// step 1:组装测试数据
		int[] sourceArray1 = new int[] { 1, 4, 7, 3, 4, 5, 7, 8, 9, 11, 20, 50,	8, 2 };
		int[] sourceArray2 = new int[] { 73, 22, 93, 43, 55, 14, 28, 65, 39, 81 };
		int[] sourceArray3 = new int[] { 1, 10, 11, 12, 13, 9, 8, 7, 6 };
		int[] sourceArray4 = new int[] { 8, 7, 6, 5, 4, 3, 2, 1, };
		int[] sourceArray5 = new int[] { 1, 8, 9, 11, 4, 3, 2 };
		int[] sourceArray6 = new int[] { 6, 2, 4, 1, 5, 9 };
		int[] sourceArray7 = new int[] { 4, 3, 2, 1 };
		List<int[]> list = new ArrayList<int[]>();
		list.add(sourceArray1);
		list.add(sourceArray2);
		list.add(sourceArray3);
		list.add(sourceArray4);
		list.add(sourceArray5);
		list.add(sourceArray6);
		list.add(sourceArray7);
		// 添加正确结果，用来测试排序是否正确
		List<String> resultList = new ArrayList<String>();
		resultList.add("1,2,3,4,4,5,7,7,8,8,9,11,20,50,");
		resultList.add("14,22,28,39,43,55,65,73,81,93,");
		resultList.add("1,6,7,8,9,10,11,12,13,");
		resultList.add("1,2,3,4,5,6,7,8,");
		resultList.add("1,2,3,4,8,9,11,");
		resultList.add("1,2,4,5,6,9,");
		resultList.add("1,2,3,4,");		
		
		// step 2:添加排序方式
		List<Sort> sortMethods = new ArrayList<Sort>();
		sortMethods.add(new GnomeSort());
		
		
		sortMethods.add(new BubbleSort());
		sortMethods.add(new CockTailSort());
		sortMethods.add(new ComSort());
		sortMethods.add(new GnomeSort());
		sortMethods.add(new OddEvenSort());
		sortMethods.add(new QuickSort());
		sortMethods.add(new RadixSort());

		sortMethods.add(new InsertSort());
		sortMethods.add(new ShellSort());
		sortMethods.add(new SelectSort());

		// 调用不同的排序方法对数组进行排序
		for (Sort sort : sortMethods) {
			System.out.println("====================");
			System.out.println("当前执行算法:" + sort.toString());
			for (int i= 0; i < list.size(); i++) {
				// 对测试数组，进行排序
				int[] targetArray = sort.sort(list.get(i));
				if (targetArray == null) {
					System.out.println("发现未处理算法" + sort.toString());
					break;
				}
				// 排序结果
				String result = "";
				for (int j= 0;j < targetArray.length; j++) {
					result = result +targetArray[j]+",";
				}
				// 验证排序结果
				if(result.equals(resultList.get(i))){
					System.out.println("排序结果正确");
				}else{
					System.out.println("排序结果错误");
				}
				//  输出排序结果
				System.out.println(result);
			}
		}
		
	}

}
